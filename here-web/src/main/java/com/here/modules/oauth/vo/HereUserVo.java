package com.here.modules.oauth.vo;

import lombok.Data;

import java.util.List;

@Data
public class HereUserVo {
    /**
     * 订单数
     */
    private Integer count;
    /**
     * 头像
     */
    private String headUrl;
    /**
     * Here居民号
     */
    private String hereCode;
    /**
     * 我的邀请码
     */
    private String myCode;
    /**
     * 铭牌点亮1 未点亮0
     */
    private Integer nameplateFlag;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 购买记录
     */
    private List<HereUserShopping> shoppings;
}
