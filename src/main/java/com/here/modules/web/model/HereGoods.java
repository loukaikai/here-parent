package com.here.modules.web.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-12-11
 */
@Getter
@Setter
@TableName("here_goods")
@ApiModel(value = "HereGoods对象", description = "")
public class HereGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String goodsTitle;

    private String goodsName;

    private Integer shopId;

    private String shipName;

    private Integer userId;

    @ApiModelProperty("商品类型")
    private Integer type;

    @ApiModelProperty("成交数")
    private Integer tradVolume;

    private String goodsDescription;

    private Date createTime;


}
