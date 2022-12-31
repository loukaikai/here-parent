package com.here.modules.oauth.vo;

import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WriteInviCodeVO.java
 * @Description TODO
 * @createTime 2022年12月31日 11:43:00
 */
@Data
public class WriteInviCodeVO {
    /**用户Id**/
    private int userId;

    /**邀请人Id**/
    private int inviId;

    /** 邀请人邀请码 **/
    private String inviCode;
}
