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
 * @since 2022-12-30
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

    private Integer sourceId;

    private Integer userId;

    @ApiModelProperty("标识 0-生效；1-失效。统一时间一个平台只有一个生效")
    private String status;

    @ApiModelProperty("平台标识 1-微信小程序；2-抖音")
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
