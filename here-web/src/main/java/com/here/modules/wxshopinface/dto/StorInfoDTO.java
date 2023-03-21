package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName StorInfoDTO.java
 * @Description TODO
 * @createTime 2023年03月06日 20:09:00
 */
@Data
@ApiModel(value="StorInfoDTO", description="商品门店信息")
public class StorInfoDTO {

    @ApiModelProperty(value = "id", name = "门店id", notes = "商户侧门店编号")
    private String id;

    @ApiModelProperty(value = "name", name = "门店名称")
    private String name;

    @ApiModelProperty(value = "areaCode", name = "门店名称", notes = "地区编码，详细请见省市区编号对照表")
    private String area_code;

    @ApiModelProperty(value = "address", name = "详细地址")
    private String address;
}
