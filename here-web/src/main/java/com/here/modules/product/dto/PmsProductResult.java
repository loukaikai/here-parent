package com.here.modules.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询单个商品修改后返回的结果
 *
 * @author syj
 * @since 2023/2/19
 */
public class PmsProductResult extends PmsProductParam {

    private static final long serialVersionUID = 1640129698342023668L;

    @Getter
    @Setter
    @ApiModelProperty("商品所选分类的父id")
    private Long cateParentId;
}
