package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName PrepayIdDTO.java
 * @Description TODO
 * @createTime 2023年03月07日 15:15:00
 */
@Data
@ApiModel(value="PrepayIdDTO", description="jsApi返回结构类")
public class PrepayIdDTO {

    @ApiModelProperty(value = "prepayId", name = "预支付交易会话标识")
    @NotEmpty(message = "用户标识不能为空")
    private String prepayId;
}
