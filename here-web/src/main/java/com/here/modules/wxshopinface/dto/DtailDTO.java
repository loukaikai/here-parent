package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName DtailDTO.java
 * @Description TODO
 * @createTime 2023年03月05日 21:49:00
 */
@Data
@ApiModel(value="DtailDTO", description="优惠功能")
public class DtailDTO {

    @ApiModelProperty(value = "costPrice", name = "订单原价", notes = "1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。\n" +
            "2、当订单原价与支付金额不相等，则不享受优惠。\n" +
            "3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。\n" +
            "示例值：608800")
    private int costPrice;

    @ApiModelProperty(value = "invoiceId", name = "商品小票ID", notes = "商家小票ID")
    private String invoiceId;

    @ApiModelProperty(value="goodsDtail", name = "单品列表")
    public GoodsDtailDTO goodsDtail;
}
