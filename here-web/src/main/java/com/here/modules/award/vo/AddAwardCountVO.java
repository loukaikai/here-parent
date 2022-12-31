package com.here.modules.award.vo;

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
public class AddAwardCountVO {

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
}
