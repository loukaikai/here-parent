package com.here.modules.award.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName AddAwardCountVO.java
 * @Description 添加奖励抽奖次数VO类
 * @createTime 2022年12月31日 13:52:00
 */
@Data
@ApiModel(value="添加奖励抽奖次数VO类", description="奖励抽奖次数")
public class AddAwardCountVO {

    /**
     * 奖励来源：1-开宝箱
     * **/
    @ApiModelProperty(value = "奖励来源")
    @NotNull( message = "请输入奖励来源")
    private int sourceId;

    /**
     * 用户Id
     * **/
    @NotNull( message = "请输入用户Id")
    @ApiModelProperty(value = "用户Id")
    private int userId;
}
