package com.here.modules.badge.service;

import com.here.modules.badge.dto.BadgeDTO;

import java.util.List;

/**
 * @author Lzk
 */
public interface BadgeService {

    /**
     * 增加徽章
     * @param badgeDTO 徽章信息
     */
    Integer addBadge(BadgeDTO badgeDTO);

    /**
     * 获取用户已获得徽章名称
     * @param userId 用户ID
     * @return 徽章名称列表
     */
    List<String> getBadgeByUserId(Integer userId);

}
