package com.here.modules.award.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.common.exception.BizException;
import com.here.modules.award.dto.AwardDTO;
import com.here.modules.award.entity.HereAwardUsrDetail;
import com.here.modules.award.entity.PmsAwardSubType;
import com.here.modules.award.mapper.HereAwardUsrDetailMapper;
import com.here.modules.award.mapper.PmsAwardSubTypeMapper;
import com.here.modules.award.service.HereAwardUsrDetailService;
import com.here.modules.badge.mapper.BadgeDO;
import com.here.modules.badge.mapper.BadgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 奖励使用详情 服务实现类
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
@Service
public class HereAwardUsrDetailServiceImpl extends ServiceImpl<HereAwardUsrDetailMapper, HereAwardUsrDetail> implements HereAwardUsrDetailService {

    @Autowired
    private HereAwardUsrDetailMapper hereAwardUsrDetailMapper;

    @Autowired
    private PmsAwardSubTypeMapper pmsAwardSubTypeMapper;

    @Autowired
    private BadgeMapper badgeMapper;

    @Override
    public void addCoupon(AwardDTO awardDTO) {
        HereAwardUsrDetail hereAwardUsrDetail = new HereAwardUsrDetail();
        if (awardDTO.getAwardType() == 0) {
            BadgeDO badgeDO = badgeMapper.selectById(awardDTO.getContentId());
            if (Objects.isNull(badgeDO)) {
                throw new BizException("徽章类型不存在");
            }
            hereAwardUsrDetail.setAwardSubTypeId(-1);
            hereAwardUsrDetail.setBadgeId(awardDTO.getContentId());
        } else {
            PmsAwardSubType pmsAwardSubType = pmsAwardSubTypeMapper.selectById(awardDTO.getContentId());
            if (Objects.isNull(pmsAwardSubType)) {
                throw new BizException("奖励类型不存在");
            }
            hereAwardUsrDetail.setAwardSubTypeId(awardDTO.getContentId());
            hereAwardUsrDetail.setBadgeId(-1);
        }
        hereAwardUsrDetail.setAwardStatus(0);
        hereAwardUsrDetail.setUserId(awardDTO.getUserId());
        hereAwardUsrDetail.setStatus(1);
        hereAwardUsrDetail.setReceiveTime(new Date());
        hereAwardUsrDetailMapper.insert(hereAwardUsrDetail);
    }
}
