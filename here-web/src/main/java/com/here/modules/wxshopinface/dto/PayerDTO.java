package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName Payer.java
 * @Description TODO
 * @createTime 2023年03月05日 21:44:00
 */
@Data
@ApiModel(value="PayerDTO", description="支付者DTO")
public class PayerDTO {

    @ApiModelProperty(value = "openid", name = "用户标识", notes = "用户在直连商户appid下的唯一标识。 下单前需获取到用户的Openid")
    private String openid;
}
