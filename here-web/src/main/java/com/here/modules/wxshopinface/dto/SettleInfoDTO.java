package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName SettleInfoDTO.java
 * @Description TODO
 * @createTime 2023年03月06日 20:17:00
 */
@Data
@ApiModel(value="SettleInfoDTO", description="结算信息")
public class SettleInfoDTO {

    @ApiModelProperty(value = "profitSharing", name = "是否指定分账")
    private boolean profitSharing;

}
