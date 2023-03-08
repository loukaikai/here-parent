package com.here.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 品牌表
 *
 * @author syj
 * @since 2023-01-19
 */
@Data
@TableName("pms_brand")
@ApiModel(value = "PmsBrand", description = "品牌表")
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("品牌名")
    private String name;

    @ApiModelProperty("检索首字母")
    private String firstLetter;

    @ApiModelProperty("品牌logo地址")
    private String logo;

    @ApiModelProperty("品牌介绍")
    private String description;

    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;

    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    private Integer showStatus;

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

}
