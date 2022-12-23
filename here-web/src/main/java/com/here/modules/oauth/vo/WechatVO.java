package com.here.modules.oauth.vo;

import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WechatVO.java
 * @Description TODO
 * @createTime 2022年12月20日 16:13:00
 */
@Data
public class WechatVO {

    private String code;

    /** 邀请码 **/
    private String invitationCode;


}
