package com.here.modules.oauth.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.here.common.aop.Log;
import com.here.common.api.ResultObject;
import com.here.common.utils.HttpClientUtil;
import com.here.common.utils.JwtTokenUtil;
import com.here.common.utils.RestTemplateUtil;
import com.here.modules.oauth.dto.WxchatDtailDTO;
import com.here.modules.oauth.entity.HereUser;
import com.here.modules.oauth.service.HereUserService;
import com.here.modules.oauth.vo.ThirdOauthVO;
import com.here.modules.oauth.vo.WechatVO;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatEnterpriseThirdQrcodeRequest;
import me.zhyd.oauth.request.AuthWeChatOpenRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Value("${douyin.login_url}")
    private String dyLoginUrl;
    @Value("${douyin.token_url}")
    private String dyTokenUrl;
    @Value("${douyin.client_key}")
    private String dyClientKey;
    @Value("${douyin.client_secret}")
    private String dyClientSecret;
    @Value("${wechart.login_url}")
    private String weChartLoginUrl;

    @Value("${wechart.acctoken_url}")
    private String acctoknUrl;
    @Value("${wechart.appid}")
    private String appId;
    @Value("${wechart.secret}")
    private String secret;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Resource
    private HereUserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private HereUserService hereUserService;



    /**
     * 登记邀请码
     * @return
     */
    @PostMapping("writeCode")
    public ResultObject writeCode(@RequestParam("code") String code){
        return new ResultObject(userService.writeCode(code),"success",200, true);
    }

    /**
     * 获取token
     */
    @PostMapping(value = "getWxToken")
    @ResponseBody
    @Log
    public String getWxToken(@RequestBody JSONObject jsonObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid",appId);
        map.put("secret",secret);
        map.put("grant_type","client_credential");
        String str = restTemplateUtil.getRequest(map,acctoknUrl);
        return str;
    }

    /**
     * 微信登录
     * @param code
     * @return
     */
    @GetMapping("/weChart")
    @ResponseBody
    @Log
    public ResultObject weChartLogin(
            // @Validated @RequestBody WechatVO wechatVO
            @RequestParam("code") String code) {

       // map.put("firstLogin",false);
        return hereUserService.weChartLogin(code);
    }

    /**
     * 微信第三方登录
     * * @return
     */
    @PostMapping("/weauthlogin")
    @Log
    @ResponseBody
    public ResultObject weAuthLogin(@Validated @RequestBody ThirdOauthVO thirdOauthVO) {
        // 创建授权request
        AuthRequest authRequest = new AuthWeChatOpenRequest(AuthConfig.builder()
                .clientId(appId)
                .clientSecret(secret)
                .redirectUri("redirectUri")
                .build());
        // 生成授权页面

        return new ResultObject(authRequest,"success",200, true);
    }

    /**
     * 获取token
     */
    @PostMapping(value = "getDyToken")
    @ResponseBody
    @Log
    public String getDyToken(@RequestBody JSONObject jsonObject) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid",dyClientKey);
        map.put("secret",dyClientSecret);
        map.put("grant_type","client_credential");
        return restTemplateUtil.PostRequest(JSONObject.parseObject(map.toString()),dyTokenUrl);
    }

    @PostMapping("/dyLogin")
    @Log
    public String dyLogin(@RequestParam(value = "code", required = true) String code //前端调用抖音接口返回的code
    ) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid",dyClientKey);
        map.put("secret",dyClientSecret);
        map.put("code",code);
        return restTemplateUtil.PostRequest(JSONObject.parseObject(map.toString()),dyLoginUrl);
    }


}
