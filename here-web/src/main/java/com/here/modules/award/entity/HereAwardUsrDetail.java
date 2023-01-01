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
 * 奖励使用详情
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
@Getter
@Setter
@TableName("here_award_usr_detail")
@ApiModel(value = "HereAwardUsrDetail对象", description = "奖励使用详情")
public class HereAwardUsrDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("奖励类型 1-现金；2-优惠券；3-虚拟物品")
    private Integer awardSubTypeId;

    @ApiModelProperty("0-领取；1-使用；2-过期")
    private Integer awardStatus;

    @ApiModelProperty("用户")
    private Integer userId;

    @ApiModelProperty("状态 0-启用 2-停用 3-删除")
    private Integer status;

    @ApiModelProperty("领取时间")
    private Date receiveTime;

    @ApiModelProperty("使用时间")
    private Date useTime;

    @ApiModelProperty("过期时间")
    private Date expiredTime;

    @ApiModelProperty("创建人")
    private Integer createdBy;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新人")
    private Integer updatedBy;

    @ApiModelProperty("更新时间")
    private Date updatedTime;


}
