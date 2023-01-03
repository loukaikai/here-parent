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

import java.util.*;

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

    @Override
    public List<String> getBadgeByUserId(Integer userId) {
        LambdaQueryWrapper<BadgeDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BadgeDO::getUserId, userId);
        List<BadgeDO> badgeDOs = Optional.ofNullable(badgeMapper.selectList(wrapper)).orElse(Collections.emptyList());
        if (badgeDOs.isEmpty()) {
            throw new BizException("用户尚未获得任何徽章");
        } else {
            List<String> badgeNames = new ArrayList<>();
            badgeDOs.forEach(badgeDO -> badgeNames.add(badgeDO.getName()));
            return badgeNames;
        }
    }

}
