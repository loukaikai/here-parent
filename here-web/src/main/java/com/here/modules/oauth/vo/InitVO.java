package com.here.modules.oauth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName InitVO.java
 * @Description 初始化用户数据，手机，宝箱数据
 * @createTime 2023年01月10日 10:27:00
 */

@Data
@ApiModel(value="初始化用户数据，手机，宝箱数据", description="初始化用户数据，手机，宝箱数据")
public class InitVO {

    @ApiModelProperty(value = "用户Id")
    @Size(min = 1, message = "用户id长度错误")
    @NotNull( message = "用户Id不能为空")
    private int userId;

    private String code;
}
