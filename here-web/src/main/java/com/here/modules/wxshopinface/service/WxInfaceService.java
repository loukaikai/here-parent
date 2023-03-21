package com.here.modules.wxshopinface.service;

import com.here.common.api.ResultObject;
import com.here.modules.oauth.dto.PhoneInfo;
import com.here.modules.wxshopinface.dto.JsApiDTO;
import com.here.modules.wxshopinface.dto.ParamDTO;
import com.here.modules.wxshopinface.dto.PrepayIdDTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WxInfaceService.java
 * @Description TODO
 * @createTime 2023年01月05日 16:49:00
 */
public interface WxInfaceService {

    public ResultObject<String> getAccToken();

    public ResultObject<PhoneInfo> getPhoneNum(String code);

    public ResultObject<Object> getJsApi();

    ResultObject<PrepayIdDTO> jsApi(JsApiDTO jsApiDTO);

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
    ResultObject<ParamDTO> getParamDTO(String prepayId);
}
