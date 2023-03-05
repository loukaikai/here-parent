package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName AmountDTO.java
 * @Description TODO
 * @createTime 2023年03月05日 21:31:00
 */
@Data
@ApiModel(value="AmountDTO", description="订单金额")
public class AmountDTO {

    @ApiModelProperty(value = "total", name = "总金额", notes = "订单总金额，单位为分")
    private int total;

    @ApiModelProperty(value = "currentcy", name = "货币类型", notes = "CNY：人民币，境内商户号仅支持人民币")
    private String currentcy;
}
