package com.here.modules.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.here.common.aop.Log;
import com.here.common.api.ResultObject;
import com.here.common.utils.RestTemplateUtil;
import com.here.modules.entity.HereUser;
import com.here.modules.service.HereUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
public class LoginController {
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
    @Value("${wechart.appid}")
    private String appId;
    @Value("${wechart.secret}")
    private String secret;
    @Autowired
    private RestTemplateUtil restTemplateUtil;
    @Resource
    private HereUserService userService;



    /**
     * 登记邀请码
     * @return
     */
    @PostMapping("writeCode")
    public ResultObject writeCode(@RequestParam("code") String code){
        return new ResultObject(userService.writeCode(code),"success",200);
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
        return restTemplateUtil.PostRequest(JSONObject.parseObject(map.toString()),dyTokenUrl);
    }

    /**
     * 微信登录
     * @param code
     * @param rawData
     * @param signature
     * @return
     */
    @PostMapping("/weChart")
    @Log
    public ResultObject weChartLogin(@RequestParam(value = "code", required = true) String code, //小程序code
                                   @RequestParam(value = "rawData", required = true) String rawData, // 用户非敏感信息
                                   @RequestParam(value = "signature", required = true) String signature // 签名
    ) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid", appId);
        map.put("secret", secret);
        map.put("js_code",code);
        JSONObject rawDataJson = JSON.parseObject(rawData);
        //开发者服务器 登录凭证校验接口 appi + appsecret + code
        String SessionKeyOpenId = restTemplateUtil.PostRequest(rawDataJson,weChartLoginUrl);
        JSONObject jsonData = JSONObject.parseObject(SessionKeyOpenId);
        //接收微信接口服务 获取返回的参数
        String openid = jsonData.getString("openid");
        String sessionKey = jsonData.getString("session_key");

        // 校验签名
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return new ResultObject("签名校验失败",null,500);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        LambdaQueryWrapper<HereUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(HereUser::getOpenId, openid);
        HereUser user = userService.getOne(lqw);
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            user = new HereUser();
            user.setOpenId(openid);
            user.setWecharHeadsUrl(avatarUrl);
            user.setWecharName(nickName);
            userService.save(user);
            map.put("firstLogin",true);
            map.put("user",user);
        }
        map.put("firstLogin",false);
        return new ResultObject(map,"success",200);
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
