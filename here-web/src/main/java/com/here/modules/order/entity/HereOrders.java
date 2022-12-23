package com.here.modules.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-16
 */
@Getter
@Setter
@TableName("here_orders")
@ApiModel(value = "HereOrders对象", description = "订单表")
public class HereOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("订单号")
    private String orderNo;

    private Integer userId;

    @ApiModelProperty("用户code")
    private String userCode;

    private Integer goodsId;

    @ApiModelProperty("商品标题 商品名")
    private String goodsTitle;

    private Integer goodsDetailId;

    @ApiModelProperty("规格")
    private String goodsSpecifications;

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

    private Date createTime;

    @ApiModelProperty("付款时间")
    private Date paymentTime;

    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    private Date updateTime;


}
