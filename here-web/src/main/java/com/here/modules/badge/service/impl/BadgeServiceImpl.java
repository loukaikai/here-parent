package com.here.modules.badge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.here.common.exception.BizException;
import com.here.modules.badge.dto.BadgeDTO;
import com.here.modules.badge.mapper.BadgeDO;
import com.here.modules.badge.mapper.BadgeMapper;
import com.here.modules.badge.service.BadgeService;
import com.here.modules.oauth.entity.HereUser;
import com.here.modules.oauth.mapper.HereUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 徽章模块service
 * @author Lzk
 */
@Service
public class BadgeServiceImpl implements BadgeService {

    @Autowired
    private BadgeMapper badgeMapper;

    @Autowired
    private HereUserMapper hereUserMapper;

    @Override
    public void addBadge(BadgeDTO badgeDTO) {
        LambdaQueryWrapper<HereUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(HereUser::getPhone, badgeDTO.getUserPhone());
        HereUser hereUser = hereUserMapper.selectOne(userWrapper);
        if (Objects.isNull(hereUser)) {
            throw new BizException("徽章发放对象不存在");
        }

        BadgeDO badgeDO = new BadgeDO();
        badgeDO.setName(badgeDTO.getBadgeName());
        badgeDO.setBadgeUrl(badgeDTO.getBadgeUrl());
        badgeDO.setUserId(hereUser.getId());
        badgeMapper.insert(badgeDO);
    }

}
