package com.here.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 品牌分类关联
 *
 * @author syj
 * @since 2023-01-19
 */
@Data
@TableName("pms_category_brand_relation")
@ApiModel(value = "PmsCategoryBrandRelation", description = "品牌分类关联")
public class PmsCategoryBrandRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("分类id")
    private Long catelogId;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("分类名称")
    private String catelogName;

}
