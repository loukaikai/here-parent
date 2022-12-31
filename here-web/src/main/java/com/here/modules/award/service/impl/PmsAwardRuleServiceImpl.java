package com.here.modules.award.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.here.common.api.ResultObject;
import com.here.common.exception.BizException;
import com.here.modules.award.controller.AwardController;
import com.here.modules.award.entity.PmsAwardRule;
import com.here.modules.award.entity.PmsAwardSource;
import com.here.modules.award.mapper.PmsAwardRuleMapper;
import com.here.modules.award.mapper.PmsAwardSourceMapper;
import com.here.modules.award.service.PmsAwardRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.award.vo.AddAwardCountVO;
import com.here.modules.order.entity.HereOrders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 奖励规则达标表 服务实现类
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-31
 */
@Service
public class PmsAwardRuleServiceImpl extends ServiceImpl<PmsAwardRuleMapper, PmsAwardRule> implements PmsAwardRuleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsAwardRuleServiceImpl.class);

    @Autowired
    private PmsAwardSourceMapper pmsAwardSourceMapper;
    /**
     * 添加抽奖次数
     **/
    @Override
    public ResultObject<Object> addAwardCount(AddAwardCountVO addAwardCountVO) {
        LOGGER.info("添加抽奖次数服务======start");
        ResultObject<Object> resultObject = new ResultObject<>();
        int sourceId = addAwardCountVO.getSourceId();
        int userId = addAwardCountVO.getUserId();
        PmsAwardSource pmsAwardSource = pmsAwardSourceMapper.selectById(sourceId);
        if (Objects.isNull(pmsAwardSource)){
            throw new BizException("sourceId"+"对应数据为空");
        }

        QueryWrapper<PmsAwardRule> wrapper = new QueryWrapper<PmsAwardRule>();
        LambdaQueryWrapper<PmsAwardRule> lambda = wrapper.lambda();
        lambda.eq(PmsAwardRule::getUserId,userId).eq(PmsAwardRule::getSourceId, sourceId);
        PmsAwardRule pmsAwardRule = getOne(wrapper);
        if (Objects.nonNull(pmsAwardRule)){
            int times = pmsAwardRule.getTimes();
            pmsAwardRule.setTimes(times++);
        }else {
            pmsAwardRule = new PmsAwardRule();
            pmsAwardRule.setTimes(1);
            pmsAwardRule.setStatus("0");
            pmsAwardRule.setPlatformFlag("3");
        }
        save(pmsAwardRule);
      //  PmsAwardRule pmsAwardRule = new PmsAwardRule();
        resultObject.setMessage("添加抽奖次数完成");
        resultObject.setSuccess(true);
        resultObject.setData(pmsAwardRule);
        return resultObject;
    }

    /**
     * @param addAwardCountVO
     * @return
     */
    @Override
    public ResultObject<Object> queryAwardCount(AddAwardCountVO addAwardCountVO) {
        LOGGER.info("查询用户抽奖次数服务======start");
        ResultObject<Object> resultObject = new ResultObject<>();
        int sourceId = addAwardCountVO.getSourceId();
        int userId = addAwardCountVO.getUserId();

        QueryWrapper<PmsAwardRule> wrapper = new QueryWrapper<PmsAwardRule>();
        LambdaQueryWrapper<PmsAwardRule> lambda = wrapper.lambda();
        lambda.eq(PmsAwardRule::getUserId,userId).eq(PmsAwardRule::getSourceId, sourceId);
        PmsAwardRule pmsAwardRule = getOne(wrapper);
        //  PmsAwardRule pmsAwardRule = new PmsAwardRule();
        resultObject.setMessage("查询用户抽奖次数完成");
        resultObject.setSuccess(true);
        resultObject.setData(pmsAwardRule);
        return resultObject;
    }
}
