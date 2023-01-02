package com.here.modules.award.vo;

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
public class DeAwardCountVO {

    /**
     * 奖励来源：1-开宝箱
     * **/
    @NotNull( message = "请输入奖励来源")
    private int sourceId;

    /**
     * 用户Id
     * **/
    @NotNull( message = "请输入用户Id")
    private int userId;

    /**
     * 消耗次数：默认是1
     * **/
    private int delCount;
}
