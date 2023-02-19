package com.here.modules.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 添加更新产品分类的参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsCategoryParam {
    @ApiModelProperty("父分类的编号")
    private Long parentId;

    @NotEmpty
    @ApiModelProperty(value = "商品分类名称", required = true)
    private String name;

    @ApiModelProperty("分类单位")
    private String productUnit;

    @Range(max = 1, min = 0)
    @ApiModelProperty("是否在导航栏显示")
    private Integer navStatus;

    @Range(max = 1, min = 0)
    @ApiModelProperty("是否进行显示")
    private Integer showStatus;

    @Min(value = 0)
    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("关键字")
    private String keywords;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("产品相关筛选属性集合")
    private List<Long> productAttributeIdList;
}
