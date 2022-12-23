package com.here.modules.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户徽章关联表
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Getter
@Setter
@TableName("here_user_badge")
@ApiModel(value = "HereUserBadge对象", description = "用户徽章关联表")
public class HereUserBadge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    @ApiModelProperty("徽章id")
    private Integer badgeId;

    @ApiModelProperty("获取时间")
    private Date createTime;


}
