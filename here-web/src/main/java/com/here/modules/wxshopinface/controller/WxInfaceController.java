package com.here.modules.wxshopinface.controller;

import com.alibaba.fastjson.JSONObject;
import com.here.common.aop.Log;
import com.here.common.api.ResultObject;
import com.here.common.utils.RestTemplateUtil;
import com.here.modules.oauth.dto.PhoneInfo;
import com.here.modules.wxshopinface.service.WxInfaceService;
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

    @Autowired
    private WxInfaceService wxInfaceService;

    /**
     * 获取token
     */
    @PostMapping(value = "getWxToken")
    @ResponseBody
    @Log
    @ApiOperation("获取token")
    public String getWxToken() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid",appId);
        map.put("secret",secret);
        map.put("grant_type","client_credential");
        String str = restTemplateUtil.getRequest(map,accessTokn);
        return str;
    }

    @GetMapping("/getUserPhone")
    @ResponseBody
    @Log
    public ResultObject<PhoneInfo> weChartLogin(
            // @Validated @RequestBody WechatVO wechatVO
            @RequestParam("code") String code) {
        return wxInfaceService.getPhoneNum(code);
        // map.put("firstLogin",false);
    }

    @ApiOperation("获取优惠券")
    @PostMapping("/getlist")
    public ResultObject<Void> getCouponList() {
        //getCoupon
        return ResultObject.success(null);
    }

    @ApiOperation("JSAPI下单")
    @PostMapping("/getlist")
    public ResultObject<Void> jsApi() {
        //getCoupon
        return ResultObject.success(null);
    }
}
