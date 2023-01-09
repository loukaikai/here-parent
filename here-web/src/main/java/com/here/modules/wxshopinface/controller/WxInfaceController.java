package com.here.modules.wxshopinface.controller;

import com.alibaba.fastjson.JSONObject;
import com.here.common.aop.Log;
import com.here.common.api.ResultObject;
import com.here.common.utils.RestTemplateUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.here.modules.wxshopinface.contanst.WxInfaceConsta.accessTokn;
import static com.here.modules.wxshopinface.contanst.WxInfaceConsta.getCoupon;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WxInfaceController.java
 * @Description TODO
 * @createTime 2023年01月05日 16:47:00
 */
@RestController
@RequestMapping("/wxinface")
@Tag(name = "WxInfaceController",description = "微信接口管理")
public class WxInfaceController {

    @Value("${wechart.appid}")
    private String appId;
    @Value("${wechart.secret}")
    private String secret;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

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
        String str = restTemplateUtil.getRequest(map,accessTokn);
        return str;
    }


    @ApiOperation("获取优惠券")
    @PostMapping("/getlist")
    public ResultObject<Void> getCouponList() {
        //getCoupon
        return ResultObject.success(null);
    }
}
