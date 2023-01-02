package com.here.modules.award.service;

import com.here.modules.award.dto.AwardDTO;
import com.here.modules.award.entity.HereAwardUsrDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 奖励使用详情 服务类
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
public interface HereAwardUsrDetailService extends IService<HereAwardUsrDetail> {

    /**
     * 增加优惠券
     * @param awardDTO 优惠券信息
     */
    void addCoupon(AwardDTO awardDTO);

}
