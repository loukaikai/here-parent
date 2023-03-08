package com.here.modules.wxshopinface.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName Goods.java
 * @Description TODO
 * @createTime 2023年03月05日 21:52:00
 */
@Data
@ApiModel(value="GoodsDtailDTO", description="单品列表")
public class GoodsDtailDTO {

    @ApiModelProperty(value = "merchantGoodsId", name = "商户侧商品编码")
    private String merchantGoodsId;

    @ApiModelProperty(value = "wechatpayGoodsId", name = "微信支付商品编码", notes = "微信支付定义的统一商品编号（没有可不传")
    private String wechatpayGoodsId;

    @ApiModelProperty(value = "goodsName", name = "商户名称")
    private String goodsName;

    @ApiModelProperty(value = "quantity", name = "商品数量， 用户购买的数量")
    private int quantity;

    @ApiModelProperty(value = "unitPrice", name = "商品单价")
    private int unitPrice;

}
