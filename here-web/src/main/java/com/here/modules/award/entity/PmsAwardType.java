package com.here.modules.award.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 奖励类型
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
@Getter
@Setter
@TableName("pms_award_type")
@ApiModel(value = "PmsAwardType对象", description = "奖励类型")
public class PmsAwardType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("奖励类型 1-现金；2-优惠券；3-虚拟物品")
    private Integer awardType;

    @ApiModelProperty("奖励类型名称")
    private String awardTypeName;

    @ApiModelProperty("奖励预算")
    private Integer awardBudget;

    @ApiModelProperty("奖励预算使用")
    private Integer awradBudgetUse;

    @ApiModelProperty("状态 0-启用 2-停用 3-删除")
    private String status;

    @ApiModelProperty("创建人")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新人")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    private Date updatedTime;


}
