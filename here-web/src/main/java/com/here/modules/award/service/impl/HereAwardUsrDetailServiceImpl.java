package com.here.modules.award.service.impl;

import com.here.modules.award.dto.CouponDTO;
import com.here.modules.award.entity.HereAwardUsrDetail;
import com.here.modules.award.mapper.HereAwardUsrDetailMapper;
import com.here.modules.award.service.HereAwardUsrDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public void addCoupon(CouponDTO couponDTO) {
        HereAwardUsrDetail hereAwardUsrDetail = new HereAwardUsrDetail();
        hereAwardUsrDetail.setAwardSubTypeId(couponDTO.getAwardSubTypeId());
        hereAwardUsrDetail.setAwardStatus(0);
        hereAwardUsrDetail.setUserId(couponDTO.getUserId());
        hereAwardUsrDetail.setStatus(1);
        hereAwardUsrDetail.setReceiveTime(new Date());
        hereAwardUsrDetailMapper.insert(hereAwardUsrDetail);
    }
}
