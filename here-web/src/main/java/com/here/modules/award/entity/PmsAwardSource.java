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
 * 奖励来源配置
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
@Getter
@Setter
@TableName("pms_award_source")
@ApiModel(value = "PmsAwardSource对象", description = "奖励来源配置")
public class PmsAwardSource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("奖励类型 1-宝箱；2-邀请奖励")
    private String awardSourceType;

    @ApiModelProperty("奖励规则")
    private String awardRule;

    @ApiModelProperty("奖励类型名称")
    private String awardSourceName;

    @ApiModelProperty("奖励规则id")
    private String awardRuleId;

    @ApiModelProperty("状态 0-启用 2-停用 3-删除")
    private String status;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("创建人")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新人")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    private Date updatedTime;


}
