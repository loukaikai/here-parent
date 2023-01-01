package com.here.modules.award.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券信息数据传输对象
 * @author Lzk
 */
@Data
public class CouponDTO implements Serializable {

    private static final long serialVersionUID = -3625303118964766248L;

    /**
     * 发放优惠券目标用户ID
     */
    private Integer userId;

    /**
     * 优惠券类型ID
     */
    private Integer awardSubTypeId;
}
