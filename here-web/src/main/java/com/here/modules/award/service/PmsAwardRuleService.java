package com.here.modules.award.service;

import com.here.common.api.ResultObject;
import com.here.modules.award.entity.PmsAwardRule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.award.vo.AddAwardCountVO;

/**
 * <p>
 * 奖励规则达标表 服务类
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
public interface PmsAwardRuleService extends IService<PmsAwardRule> {

    /**
     * 添加抽奖次数
     * **/
    public ResultObject<Object> addAwardCount(AddAwardCountVO addAwardCountVO);

    public ResultObject<Object> queryAwardCount(AddAwardCountVO addAwardCountVO);
}
