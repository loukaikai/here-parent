package com.here.modules.oauth.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName ThirdOauthVO.java
 * @Description 第三方登录参数
 * @createTime 2022年12月20日 10:22:00
 */
@Data
public class ThirdOauthVO {

    /**
     * 小程序code
     */
    @NotNull
    private String code;

    /**
     * 回调函数带的参数state
     * **/
    private String state;

}
