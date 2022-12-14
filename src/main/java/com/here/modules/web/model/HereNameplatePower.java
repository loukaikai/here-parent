package com.here.modules.web.model;

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
 * 铭牌助力
 * </p>
 *
 * @author macro
 * @since 2022-12-11
 */
@Getter
@Setter
@TableName("here_nameplate_power")
@ApiModel(value = "HereNameplatePower对象", description = "铭牌助力")
public class HereNameplatePower implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("所属用户id")
    private Integer userId;

    @ApiModelProperty("助力用户id")
    private Integer powerUserId;

    @ApiModelProperty("助力用户头像url")
    private String powerUserHeads;

    @ApiModelProperty("助力时间")
    private Date createTime;


}
