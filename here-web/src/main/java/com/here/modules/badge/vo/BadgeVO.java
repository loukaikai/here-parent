package com.here.modules.badge.vo;

import lombok.Data;

/**
 * 徽章VO
 * @author Lzk
 */
@Data
public class BadgeVO {

    /**
     * 徽章ID
     */
    private Integer badgeId;

    /**
     * 徽章名称
     */
    private String badgeName;

    /**
     * 徽章数量
     */
    private Integer badgeAmount;

}
