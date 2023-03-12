package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName ScneInfoDTO.java
 * @Description TODO
 * @createTime 2023年03月06日 20:04:00
 */
@Data
@ApiModel(value="SceneInfoDTO", description="场景信息")
public class SceneInfoDTO {

    @ApiModelProperty(value = "payerClientIp", name = "用户终端IP", notes = "用户的客户端IP，支持IPv4和IPv6两种格式的IP地址")
    @NotEmpty(message = "用户终端IP不能为空")
    private String payerClientIp;

    @ApiModelProperty(value = "deviceId", name = "商户端设备号", notes = "商户端设备号（门店号或收银设备ID")
    private String deviceId;

    @ApiModelProperty(value="storInfo", name="商品门店信息")
    public StorInfoDTO storInfo;


}
