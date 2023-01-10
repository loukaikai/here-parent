package com.here.modules.oauth.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.core.JsonParser;
import com.here.common.api.ResultObject;
import com.here.common.utils.AiDouGenerateUtils;
import com.here.common.utils.HttpClientUtil;
import com.here.common.utils.JwtTokenUtil;
import com.here.common.utils.RestTemplateUtil;
import com.here.domain.AdminUserDetails;
import com.here.modules.award.entity.PmsAwardRule;
import com.here.modules.oauth.controller.LoginController;
import com.here.modules.oauth.dto.PhoneInfo;
import com.here.modules.oauth.dto.WxchatDtailDTO;
import com.here.modules.oauth.entity.HereUser;
import com.here.modules.oauth.mapper.HereUserMapper;
import com.here.modules.oauth.service.HereUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.oauth.vo.HereUserVo;
import com.here.modules.oauth.vo.InitVO;
import com.here.modules.oauth.vo.WriteInviCodeVO;
import com.here.modules.wxshopinface.service.WxInfaceService;
import com.here.security.SecurityUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Service
@Transactional
public class HereUserServiceImpl extends ServiceImpl<HereUserMapper, HereUser> implements HereUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HereUserServiceImpl.class);

    @Value("${wechart.appid}")
    private String appId;
    @Value("${wechart.secret}")
    private String secret;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${wechart.login_url}")
    private String weChartLoginUrl;

    @Value("${wechart.acctoken_url}")
    private String acctokenUrl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private WxInfaceService wxInfaceService;

    @Override
    public Object getUser() {
        HereUserVo userVo = new HereUserVo();
        HereUser hereUser = SecurityUtil.getCurrentUser();
        //暂时没定头部有哪些数据，先用某个唯一值查db
        HereUser user = getOne(new LambdaQueryWrapper<HereUser>().eq(HereUser::getHereCode, hereUser.getHereCode()));
        BeanUtils.copyProperties(user,userVo);
        //todo 商品订单还没确认
        return null;
    }

    /**
     * 初始化用户信息
     *
     */
    @Override
    public ResultObject<Object> initUser(InitVO initVO){
        LOGGER.info("初始化用户信息");
        ResultObject<Object> resultObject = new ResultObject<>();
        LambdaQueryWrapper<HereUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(HereUser::getId, initVO.getUserId());
        HereUser user = getOne(lqw);
        if (StringUtils.isBlank(user.getPhone())){
            LOGGER.info("手机号为空，初始化用户手机号");
            String code = initVO.getCode();
            ResultObject<PhoneInfo> phoneNum = wxInfaceService.getPhoneNum(initVO.getCode());
            PhoneInfo phoneInfo = (PhoneInfo) phoneNum.getData();
            if (Objects.nonNull(phoneInfo)){
                user.setPhone(phoneInfo.getPhoneNumber());
                user.setPurePhoneNumber(phoneInfo.getPurePhoneNumber());
                user.setCountryCode(phoneInfo.getCountryCode());
                saveOrUpdate(user);
            }
        }
        LOGGER.info("初始化用户信息完成");
        resultObject.setMessage("初始化用户信息完成");
        return resultObject;
    }


    @Override
    public Boolean writeCode(WriteInviCodeVO writeInviCodeVO) {
        int userId = writeInviCodeVO.getUserId();
        int inviId = writeInviCodeVO.getInviId();
        String inviCode = writeInviCodeVO.getInviCode();

        if (Objects.isNull(userId)){
            HereUser hereUser = SecurityUtil.getCurrentUser();
            userId = hereUser.getId();
        }

        if (Objects.isNull(inviId)){
            inviId = Integer.parseInt(String.valueOf(AiDouGenerateUtils.codeToId(inviCode)));
        }
        Integer changeCt = baseMapper.updateInvitationCode(inviId, inviCode, userId);
        if (changeCt<=1){
            return true;
        }
        return false;
    }

    /**
     * 根据微信号查询
     *
     * @param wechatNo
     **/
    @Override
    public HereUser getHereUserByWchatNo(String wechatNo) {
        return null;
    }

    @Override
    public UserDetails loadUserByHereCod(String userId){
        //获取用户信息
        LambdaQueryWrapper<HereUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(HereUser::getOpenId, userId);
        HereUser user = getOne(lqw);
        if (user != null) {

            return new WxchatDtailDTO(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * @param code 小程序code*
     * @return
     */
    @Override
    public ResultObject<Object> weChartLogin(String code) {
        LOGGER.info("小程序code:[{}]", code);
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", appId);
        map.put("secret", secret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");

        //开发者服务器 登录凭证校验接口 appi + appsecret + code
       String responseContent = HttpClientUtil.doGet(weChartLoginUrl, map);
        LOGGER.info("微信返回:[{}]", responseContent);
       JSONObject jsonData = new JSONObject(responseContent);


        //接收微信接口服务 获取返回的参数
        String openid = jsonData.getStr("openid");
        // String openid = "openid"+UUID.fastUUID();
        String sessionKey = jsonData.getStr("session_key");
        //String sessionKey = "session_key"+UUID.fastUUID();

        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        LambdaQueryWrapper<HereUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(HereUser::getOpenId, openid);
        HereUser user = getOne(lqw);
        // map.put("firstLogin",false);
        if (user == null) {
            LOGGER.info("查询用户数据为空，用户信息入库");
            // 用户信息入库
            // String nickName = rawDataJson.getString("nickName");
            String nickName = "nickName";
            // String avatarUrl = rawDataJson.getString("avatarUrl");
            String avatarUrl = "avatarUrl";
            user = new HereUser();
            user.setOpenId(openid);
         //   user.setWecharHeadsUrl(avatarUrl);
         //   user.setWecharName(nickName);
          //  user.setWechatNo(UUID.fastUUID().toString());

            user.setHereCode(UUID.fastUUID().toString());

            LOGGER.info("用户信息保存成功：usrId【{}】", user.getId());
            if (null != user.getId() ){
                user.setMyCode(AiDouGenerateUtils.toSerialCode(user.getId()));
            }

        }else{
            LOGGER.info("用户信息更新：openId【{}】", openid);
            user.setOpenId(openid);
            updateById(user);
        }

        // 更新邀请码手机号
        LOGGER.info("更新邀请码手机号");
        saveOrUpdate(user);

        WxchatDtailDTO wxchatDtailDTO = new WxchatDtailDTO(user);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenUtil.generateToken(wxchatDtailDTO);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("user", new JSONObject(user).toString());
        LOGGER.info("token:[{}] tokenHead:[{tokenHead}]", token);
        return ResultObject.success(tokenMap);
    }
}

