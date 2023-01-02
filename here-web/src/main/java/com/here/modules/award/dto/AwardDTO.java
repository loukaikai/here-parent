package com.here.modules.award.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 优惠券信息数据传输对象
 * @author Lzk
 */
@Data
public class AwardDTO implements Serializable {

    private static final long serialVersionUID = -3625303118964766248L;

    /**
     * 发放优惠券目标用户ID
     */
    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    /**
     * 奖励类型
     */
    @NotNull(message = "奖励类型不能为空")
    private Integer awardType;

    /**
     * 优惠券类型ID
     */
    @NotNull(message = "奖励内容ID不能为空")
    private Integer contentId;
}
