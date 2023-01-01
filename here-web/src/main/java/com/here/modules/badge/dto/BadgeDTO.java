package com.here.modules.badge.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 徽章信息数据传输对象
 * @author Lzk
 */
@Data
public class BadgeDTO implements Serializable {

    private static final long serialVersionUID = -5462757913552753682L;

    /**
     * 徽章名称
     */
    private String badgeName;

    /**
     * 徽章url
     */
    private String badgeUrl;

    /**
     * 用户手机号
     */
    private String userPhone;

}
