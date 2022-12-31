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
 * 奖励规则达标表
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
@Getter
@Setter
@TableName("pms_award_rule")
@ApiModel(value = "PmsAwardRule对象", description = "奖励规则达标表")
public class PmsAwardRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("奖励来源id")
    private Integer sourceId;

    @ApiModelProperty("奖励来源名称")
    private String sourceName;

    @ApiModelProperty("奖励大类id")
    private String awardTypeId;

    @ApiModelProperty("奖励类型名称")
    private String awardTypeName;

    @ApiModelProperty("奖励子类id")
    private Integer awardSubtypeTypeId;

    @ApiModelProperty("奖励子类名称")
    private String awardSubtypeTypeName;

    private Integer userId;

    @ApiModelProperty("抽奖类的抽奖次数")
    private Integer times;

    @ApiModelProperty("1-奖励来源；2-奖励大类；3-奖励子类")
    private String status;

    @ApiModelProperty("平台标识 1-微信小程序；2-抖音；3-通用")
    private String platformFlag;

    @ApiModelProperty("创建人")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新人")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    private Date updatedTime;


}
