package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName JsApiDto.java
 * @Description TODO
 * @createTime 2023年03月05日 21:04:00
 */

@Data
@ApiModel(value="JsApiDTO", description="JSAPI参数")
public class JsApiDTO {

    @ApiModelProperty(value = "appid", name = "应用ID", required = true, notes = "由微信生成的应用ID，全局唯一")
    @NotEmpty(message = "appid不能为空")
    private String appid;

    @ApiModelProperty(value = "mchid", name = "直连商户号", required = true, notes = "直连商户的商户号，由微信支付生成并下发")
    @NotEmpty(message = "mchid不能为空")
    private String mchid;

    @ApiModelProperty(value = "description", required = true, name = "商品描述")
    @NotEmpty(message = "商品描述不能为空")
    private String description;

    @ApiModelProperty(value = "outTradeNo", name = "商户订单号", required = true, notes = "商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一")
    @NotEmpty(message = "商户订单号不能为空")
    private String outTradeNo;

    @ApiModelProperty(value = "timeExpire", name = "交易结束时间", notes = "订单失效时间，遵循rfc3339标准格式，" +
            "格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，" +
            "HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒",
            example = "2018-06-08T10:34:56+08:00")
    private String timeExpire;

    @ApiModelProperty(value = "attach", name = "附加数据", notes = "附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段")
    private String attach;

    @ApiModelProperty(value = "notifyUrl", name = "通知地址\t", required = true, notes = "异步接收微信支付结果通知的回调地址，" +
            "通知url必须为外网可访问的url，不能携带参数。 公网域名必须为https，如果是走专线接入，使用专线NAT IP或者私有回调域名可使用http")
    @NotEmpty(message = "通知地址不能为空")
    private String notifyUrl;

    @ApiModelProperty(value = "goodsTag", name = "订单优惠标记", example = "WXG")
    private String goodsTag;

    @ApiModelProperty(value = "supportFapiao", required = true, name = "电子发票入口开放标识", notes = "传入true时，支付成功消息和支付详情页将出现开票入口。" +
            "需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效")
    private String supportFapiao;

    @ApiModelProperty(value = "amoutDTO", name = "订单金额")
    @NotNull(message = "订单金额 can't be empty")
    @Valid
    private AmountDTO amount;

    @ApiModelProperty(value = "payer", name = "支付者")
    @NotNull(message = "支付者 can't be empty")
    @Valid
    private PayerDTO payer;

    @ApiModelProperty(value="dtail", name = "优惠功能")
    public DtailDTO dtail;

    @ApiModelProperty(value="sceneInfo", name = "场景信息")
    public SceneInfoDTO sceneInfo;

    @ApiModelProperty(value="settleInfoDTO", name = "结算信息")
    public SettleInfoDTO settleInfoDTO;

}
