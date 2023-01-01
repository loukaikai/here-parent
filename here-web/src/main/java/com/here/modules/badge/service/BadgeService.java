package com.here.modules.badge.service;

import com.here.modules.badge.dto.BadgeDTO;

/**
 * @author Lzk
 */
public interface BadgeService {

    /**
     * 增加徽章
     * @param badgeDTO 徽章信息
     */
    void addBadge(BadgeDTO badgeDTO);

}
