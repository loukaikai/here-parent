package com.here.modules.award.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName DeAwardCountVO.java
 * @Description TODO
 * @createTime 2023年01月02日 15:40:00
 */
@Data
@ApiModel(value="扣减奖励抽奖次数VO类", description="扣减奖励抽奖次数")
public class DeAwardCountVO {

    /**
     * 奖励来源：1-开宝箱
     * **/
    @ApiModelProperty(value = "奖励来源")
    @NotNull( message = "请输入奖励来源")
    private int sourceId;

    /**
     * 用户Id
     * **/
    @ApiModelProperty(value = "用户Id")
    @NotNull( message = "请输入用户Id")
    private int userId;

    /**
     * 消耗次数：默认是1
     * **/
    @ApiModelProperty(value = "消耗次数：默认是1")
    @NotNull( message = "请输入消耗次数")
    private int delCount;
}
