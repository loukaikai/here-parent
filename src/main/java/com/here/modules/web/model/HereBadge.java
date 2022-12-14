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
 * 徽章
 * </p>
 *
 * @author macro
 * @since 2022-12-11
 */
@Getter
@Setter
@TableName("here_badge")
@ApiModel(value = "HereBadge对象", description = "徽章")
public class HereBadge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("徽章名称")
    private String name;

    @ApiModelProperty("获取方式/url")
    private String model;

    private Date createTime;

    private Date updateTime;


}
