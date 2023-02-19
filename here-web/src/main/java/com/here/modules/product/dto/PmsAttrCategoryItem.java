package com.here.modules.product.dto;

import com.here.modules.product.entity.PmsAttrCategory;
import com.here.modules.product.entity.PmsAttribute;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 带有属性的商品属性分类
 *
 * @author syj
 * @since 2023/2/17
 */
public class PmsAttrCategoryItem extends PmsAttrCategory {

    private static final long serialVersionUID = -3618160202096349101L;
    @Getter
    @Setter
    @ApiModelProperty(value = "商品属性列表")
    private List<PmsAttribute> productAttributeList;
}
