package com.here.modules.wxshopinface.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.here.common.api.ResultObject;
import com.here.common.exception.BizException;
import com.here.common.utils.HttpClientUtil;
import com.here.common.utils.JwtTokenUtil;
import com.here.common.utils.RestTemplateUtil;
import com.here.modules.oauth.dto.PhoneInfo;
import com.here.modules.oauth.service.impl.HereUserServiceImpl;
import com.here.modules.wxshopinface.dto.JsApiDTO;
import com.here.modules.wxshopinface.dto.ParamDTO;
import com.here.modules.wxshopinface.dto.PrepayIdDTO;
import com.here.modules.wxshopinface.service.WxInfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.security.rsa.RSAUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;


import java.util.Base64;
import java.util.UUID;


import java.security.*;
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

    @Value("${wechart.jsapi}")
    private String jsapi;

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
            access_token = (String) resultStr.getData();
        }

        if (StringUtils.isBlank(access_token)){
            throw new BizException("accss_token为空");
        }
        JSONObject jsonObject = JSONUtil.createObj().set("code", code);
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token="+access_token;
        String phoneNum = HttpClientUtil.doPostJson(url, jsonObject.toString());

        LOGGER.info("微信返回:[{}]", phoneNum);
        JSONObject phoneNumResData = new JSONObject(phoneNum);
        String errmsg = phoneNumResData.getStr("errmsg");
        if (!StringUtils.isBlank(errmsg) && !errmsg.equals("ok")){
            resultObject.setSuccess(false);
            resultObject.setMessage("失败原因：[{}]"+errmsg);
            LOGGER.info("获取微信用户手机号失败原因=======end");
            return resultObject;
        }
        PhoneInfo phoneInfo = phoneNumResData.get("phone_info", PhoneInfo.class);
        resultObject.setData(phoneInfo);
        resultObject.setMessage("获取微信用户手机号完成");
        LOGGER.info("获取微信用户手机号=======end");
        return resultObject;
    }

    /**
     * @return
     */
    @Override
    public ResultObject<Object> getJsApi() {
        return null;
    }

    /**
     * 小程序下单JSAPI
     * @param jsApiDTO
     * @return
     */
    @Override
    public ResultObject<PrepayIdDTO> jsApi(JsApiDTO jsApiDTO) {
        LOGGER.info("小程序下单JSAPI=======START");
        ResultObject<PrepayIdDTO> resultObject = new ResultObject<>();
        ResultObject<String> resultStr = getAccToken();
        String access_token = null;
        if (resultStr.isSuccess()){
            access_token = (String) resultStr.getData();
        }

        if (StringUtils.isBlank(access_token)){
            throw new BizException("accss_token为空");
        }

        JSONObject jsonObject = new JSONObject(jsApiDTO);
        String url = jsapi;
        String jsApiResult = HttpClientUtil.doPostJson(url, jsonObject.toString());

        LOGGER.info("微信返回:[{}]", jsApiResult);
        JSONObject phoneNumResData = new JSONObject(jsApiResult);
        String errmsg = phoneNumResData.getStr("errmsg");
        if (!StringUtils.isBlank(errmsg) && !errmsg.equals("ok")){
            resultObject.setSuccess(false);
            resultObject.setMessage("失败原因：[{}]"+errmsg);
            LOGGER.info("小程序下单JSAPI失败原因：[{}]", errmsg);
            return resultObject;
        }
        PrepayIdDTO phoneInfo = phoneNumResData.get("prepay_Id", PrepayIdDTO.class);
        resultObject.setData(phoneInfo);
        resultObject.setMessage("小程序下单JSAPI完成");
        LOGGER.info("小程序下单JSAPI=======END");
        return resultObject;
    }

    /**
     * 小程序调起支付
     * 通过JSAPI下单接口获取到发起支付的必要参数prepay_id，然后使用微信支付提供的小程序方法调起小程序支付。
     * 接口说明：此API无后台接口交互，需要将列表中的数据签名
     * -------------------------------------
     * 小程序ID	appId
     * 时间戳	timeStamp
     * 随机字符串	nonceStr
     * 订单详情扩展字符串 package（prepayId）
     * 签名方式	signType
     * 签名	paySign
     * --------------------------------------
     * @param prepayId
     * **/
    public ResultObject<ParamDTO> getParamDTO(String prepayId){
        ParamDTO paramDTO = new ParamDTO();
        // appId;
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String nonceStr = uuid.toString().replace("-", "");
        // 订单详情扩展字符串 prepayId
        String signType = "signType";
        String signStr = appId+"\n"+timestamp+"\n"+nonceStr+"\n"+prepayId;
        LOGGER.info("构造签名串:[{}]", signStr);


        RSA rsa = new RSA();
        String privateKey = rsa.getPrivateKeyBase64(); // 获取私钥
        LOGGER.info("获取私钥:[{}]", privateKey);
        String publicKey = rsa.getPublicKeyBase64(); // 获取公钥
        LOGGER.info("获取公钥:[{}]", publicKey);

        LOGGER.info("RSA加密数据");
        RSA rsaEncrypt = new RSA(null, publicKey);
        byte[] encryptBytes = rsa.encrypt(signStr.getBytes(), KeyType.PublicKey);
        String paySign = Base64.getEncoder().encodeToString(encryptBytes);
        paramDTO.setAppId(appId);
        paramDTO.setTimeStamp(String.valueOf(timestamp));
        paramDTO.setPrepayId(prepayId);
        paramDTO.setNonceStr(nonceStr);
        paramDTO.setSignType(signType);
        paramDTO.setPaySign(paySign);
        paramDTO.setPublicKey(publicKey);
        paramDTO.setPrivateKey(privateKey);
        ResultObject resultObject = new ResultObject();
        resultObject.setSuccess(true);
        resultObject.setData(paramDTO);
        return resultObject;
    }
}
