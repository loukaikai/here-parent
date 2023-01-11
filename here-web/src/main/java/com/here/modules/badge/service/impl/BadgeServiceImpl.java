package com.here.modules.badge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.here.common.exception.BizException;
import com.here.modules.award.entity.HereAwardUsrDetail;
import com.here.modules.award.mapper.HereAwardUsrDetailMapper;
import com.here.modules.badge.dto.BadgeDTO;
import com.here.modules.badge.mapper.BadgeDO;
import com.here.modules.badge.mapper.BadgeMapper;
import com.here.modules.badge.service.BadgeService;
import com.here.modules.badge.vo.BadgeVO;
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
    private HereAwardUsrDetailMapper hereAwardUsrDetailMapper;

    @Override
    public Integer addBadge(BadgeDTO badgeDTO) {
        BadgeDO badgeDO = new BadgeDO();
        badgeDO.setName(badgeDTO.getBadgeName());
        badgeDO.setBadgeUrl(badgeDTO.getBadgeUrl());
        badgeMapper.insert(badgeDO);
        return badgeDO.getId();
    }

    @Override
    public List<BadgeVO> getBadgeByUserId(Integer userId) {
        LambdaQueryWrapper<HereAwardUsrDetail> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HereAwardUsrDetail::getUserId, userId).ne(HereAwardUsrDetail::getBadgeId, -1);
        List<HereAwardUsrDetail> hereAwardUsrDetails = Optional.ofNullable(hereAwardUsrDetailMapper.selectList(wrapper)).orElse(Collections.emptyList());
        if (hereAwardUsrDetails.isEmpty()) {
            throw new BizException("用户尚未获得任何徽章");
        } else {
            List<BadgeVO> badgeVOs = new ArrayList<>();
            Map<Integer, Integer> badgeAmount = new HashMap<>();
            for (HereAwardUsrDetail hereAwardUsrDetail : hereAwardUsrDetails) {
                Integer amount = badgeAmount.getOrDefault(hereAwardUsrDetail.getBadgeId(), 0);
                amount++;
                badgeAmount.put(hereAwardUsrDetail.getBadgeId(), amount);
            }
            badgeAmount.forEach((id, amount) -> {
                BadgeVO badgeVO = new BadgeVO();
                badgeVO.setBadgeId(id);
                BadgeDO badgeDO = badgeMapper.selectById(id);
                if (Objects.nonNull(badgeDO)) {
                    badgeVO.setBadgeName(badgeDO.getName());
                }
                badgeVO.setBadgeAmount(amount);
                badgeVOs.add(badgeVO);
            });
            return badgeVOs;
        }
    }

}
