package com.here.modules.order.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName HereOrdersDTO.java
 * @Description TODO
 * @createTime 2022年12月16日 20:34:00
 */
@Data
public class HereOrdersDTO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户code")
    private String userCode;

    @ApiModelProperty("店铺ID")
    private String shopId;

    @ApiModelProperty("商品ID")
    private Integer goodsId;

    @ApiModelProperty("商品标题 商品名")
    private String goodsTitle;

    @ApiModelProperty("商品详情ID")
    private Integer goodsDetailId;

    @ApiModelProperty("规格")
    private String goodsSpecifications;

    @ApiModelProperty("地址ID")
    private Integer addressId;

    @ApiModelProperty("购买数量")
    private Integer buyCount;

    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("总价")
    private BigDecimal sumPrice;

    @ApiModelProperty("运费")
    private BigDecimal freightPrice;

    @ApiModelProperty("优惠金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("实付款")
    private BigDecimal actualPayment;

    @ApiModelProperty("订单状态 0待付款 1待发货 2运送中 3待收货 4已送达待确认 5交易成功 99退款中 100退款成功")
    private Integer status;

    @ApiModelProperty("订单生成时间")
    private Date createTime;

    @ApiModelProperty("付款时间")
    private Date paymentTime;

    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    private Date updateTime;
}
