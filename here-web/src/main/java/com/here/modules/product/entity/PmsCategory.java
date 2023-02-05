package com.here.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品属性表
 *
 * @author syj
 * @since 2023-01-19
 */
@Data
@TableName("pms_category")
@ApiModel(value = "PmsCategory", description = "商品分类")
public class PmsCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父分类id")
    private Long parentId;

    @ApiModelProperty("层级")
    private Integer level;

    @ApiModelProperty("是否显示[0-不显示，1显示]")
    private Integer showStatus;

    @ApiModelProperty("图标地址")
    private String icon;

    @ApiModelProperty("计量单位")
    private String productUnit;

    @ApiModelProperty("商品数量")
    private Integer productCount;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("创建人")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新人")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    private Date updatedTime;

    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PmsCategory> children;

}
