package com.here.modules.product.dto;

import com.here.modules.product.entity.PmsCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 包含子级分类的商品分类
 * Created by macro on 2018/5/25.
 */
public class PmsCategoryWithChildrenItem extends PmsCategory {

    private static final long serialVersionUID = 1502290505228675826L;
    @Getter
    @Setter
    @ApiModelProperty("子级分类")
    private List<PmsCategory> children;
}
