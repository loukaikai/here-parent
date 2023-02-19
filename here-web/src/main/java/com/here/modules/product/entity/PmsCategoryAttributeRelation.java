package com.here.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品的分类和属性的关系表
 *
 * @author syj
 * @since 2023-01-19
 */
@Data
@TableName("pms_category_attribute_relation")
@ApiModel(value = "PmsCategoryAttributeRelation", description = "产品的分类和属性的关系表")
public class PmsCategoryAttributeRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("属性id")
    private Long attributeId;

}