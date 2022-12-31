package com.here.modules.award.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 奖励子类型（具体的奖励）
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-30
 */
@Getter
@Setter
@TableName("pms_award_sub_type")
@ApiModel(value = "PmsAwardSubType对象", description = "奖励子类型（具体的奖励）")
public class PmsAwardSubType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("奖励类型id")
    private String awardTypeId;

    @ApiModelProperty("奖励子类名称")
    private String awardSubtypeTypeName;

    @ApiModelProperty("虚拟奖励id")
    private Integer awardVirtualId;

    @ApiModelProperty("状态 0-启用 2-停用 3-删除")
    private String status;

    @ApiModelProperty("有效时间（-1为永远不过期）")
    private Integer effctiveDays;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新人")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    private Date updatedTime;


}
