package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName ParamDTO.java
 * @Description TODO
 * @createTime 2023年03月07日 20:46:00
 */
@Data
@ApiModel(value="ParamDTO", description="小程序调起支付API")
public class ParamDTO {

    @ApiModelProperty(value = "appid", name = "小程序ID")
    private String appId;

    @ApiModelProperty(value = "timeStamp", name = "时间戳", example = "1414561699")
    private String timeStamp;

    @ApiModelProperty(value = "nonceStr", name = "随机字符串", example = "K8264ILTKCH16CQ2502SI8ZNMTM67VS")
    private String nonceStr;

    @ApiModelProperty(value = "prepayId", name = "订单详情扩展字符串,该字段名为package需要改为package", example = "prepay_id=wx201410272009395522657a690389285100")
    private String prepayId;

    @ApiModelProperty(value = "signType", name = "签名类型，默认为RSA，仅支持RSA", example = "RSA")
    private String signType;

    @ApiModelProperty(value = "paySign", name = "签名，使用字段appId、timeStamp、nonceStr、package计算得出的签名值")
    private String paySign;

    @ApiModelProperty(value = "privateKey", name = "私钥")
    private String privateKey;

    @ApiModelProperty(value = "publicKey", name = "公钥")
    private String publicKey;

}
