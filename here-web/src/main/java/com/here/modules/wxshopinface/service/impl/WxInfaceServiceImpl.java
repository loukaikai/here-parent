package com.here.modules.wxshopinface.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.here.common.api.ResultObject;
import com.here.common.exception.BizException;
import com.here.common.utils.HttpClientUtil;
import com.here.common.utils.JwtTokenUtil;
import com.here.common.utils.RestTemplateUtil;
import com.here.modules.oauth.dto.PhoneInfo;
import com.here.modules.oauth.service.impl.HereUserServiceImpl;
import com.here.modules.wxshopinface.service.WxInfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WxInfaceServiceImpl.java
 * @Description TODO
 * @createTime 2023年01月05日 16:50:00
 */

@Service
public class WxInfaceServiceImpl implements WxInfaceService {

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
    /**
     * @return
     */
    @Override
    public ResultObject<String> getAccToken() {
        LOGGER.info("获取accessToken=======start");
        ResultObject<String> resultObject = new ResultObject<>();
        HashMap<String, String> accTokenMap = new HashMap<>();
        accTokenMap.put("appid",appId);
        accTokenMap.put("secret",secret);
        accTokenMap.put("grant_type","client_credential");
        String accessToken = HttpClientUtil.doGet(acctokenUrl, accTokenMap);
        LOGGER.info("微信返回:[{}]", accessToken);

        JSONObject accessTokenResData = new JSONObject(accessToken);


        //接收微信接口服务 获取返回的参数
        String access_token = accessTokenResData.getStr("access_token");
        LOGGER.info("微信返回:[{}]", access_token);
        resultObject.setData(access_token);
        return resultObject;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public ResultObject<PhoneInfo> getPhoneNum(String code) {
        LOGGER.info("获取微信用户手机号=======start");
        ResultObject<PhoneInfo> resultObject = new ResultObject<>();

        ResultObject<String> resultStr = getAccToken();
        String access_token = null;
        if (resultStr.isSuccess()){
            access_token = (String) resultObject.getData();
        }

        if (StringUtils.isBlank(access_token)){
            throw new BizException("accss_token为空");
        }
        HashMap<String, String> phoneNumMap = new HashMap<>();
        phoneNumMap.put("code",code);
        String phoneNum = HttpClientUtil.doPost("https://api.weixin.qq.com/wxa/business/getuserphonenumber?authorizer_access_token="+ access_token, phoneNumMap);
        LOGGER.info("微信返回:[{}]", phoneNum);
        JSONObject phoneNumResData = new JSONObject(phoneNum);

        PhoneInfo phoneInfo = phoneNumResData.get("phone_info", PhoneInfo.class);
        resultObject.setData(phoneInfo);
        resultObject.setMessage("获取微信用户手机号完成");
        LOGGER.info("获取微信用户手机号=======end");
        return resultObject;
    }
}
