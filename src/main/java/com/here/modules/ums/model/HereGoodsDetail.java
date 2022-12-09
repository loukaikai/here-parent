package com.here.modules.ums.model;

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
 * 
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Getter
@Setter
@TableName("here_goods_detail")
@ApiModel(value = "HereGoodsDetail对象", description = "商品对象")
public class HereGoodsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer goodsId;

    @ApiModelProperty("规格")
    private String specifications;

    private String description;

    @ApiModelProperty("商品价格")
    private BigDecimal price;

    @ApiModelProperty("商品状态 下架 缺货 正常等")
    private Integer status;

    @ApiModelProperty("库存")
    private Integer inventory;

    @ApiModelProperty("发货地")
    private String shippingAddress;

    @ApiModelProperty("上架时间")
    private Date createTime;

    private Date updateTime;


}
