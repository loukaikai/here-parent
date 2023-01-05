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
import com.here.modules.award.vo.DeAwardCountVO;
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

        // 分布式锁
        if (Objects.nonNull(pmsAwardRule)){
            int times = pmsAwardRule.getTimes();
            pmsAwardRule.setTimes(times++);
        }else {
            pmsAwardRule = new PmsAwardRule();
            pmsAwardRule.setTimes(4);
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
     * @param deAwardCountVO
     * @return
     */
    @Override
    public ResultObject<Object> deAwardCount(DeAwardCountVO deAwardCountVO) {
        LOGGER.info("减少抽奖次数服务======start");
        ResultObject<Object> resultObject = new ResultObject<>();
        int sourceId = deAwardCountVO.getSourceId();
        int userId = deAwardCountVO.getUserId();
        int count = deAwardCountVO.getDelCount();

        PmsAwardSource pmsAwardSource = pmsAwardSourceMapper.selectById(sourceId);
        if (Objects.isNull(pmsAwardSource)){
            throw new BizException("sourceId"+"对应数据为空");
        }

        QueryWrapper<PmsAwardRule> wrapper = new QueryWrapper<PmsAwardRule>();
        LambdaQueryWrapper<PmsAwardRule> lambda = wrapper.lambda();
        lambda.eq(PmsAwardRule::getUserId,userId).eq(PmsAwardRule::getSourceId, sourceId);
        PmsAwardRule pmsAwardRule = getOne(wrapper);


        if (Objects.isNull(pmsAwardRule)){
            throw new BizException("用户抽奖次数数据为空");
        }

        // 分布式锁，第二期上
        int times = pmsAwardRule.getTimes();
        if (times <= 0){
            resultObject.setMessage("抽奖次数已经为0，不再扣减");
            resultObject.setSuccess(true);
            resultObject.setData(pmsAwardRule);
        }
        if (times < count){
            throw new BizException("用户抽奖次数小于扣减次数");
        }
        times = times - count;
        pmsAwardRule.setTimes(times);

        saveOrUpdate(pmsAwardRule);
        //  PmsAwardRule pmsAwardRule = new PmsAwardRule();
        resultObject.setMessage("扣减抽奖次数完成");
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
        if (Objects.isNull(pmsAwardRule)){
            pmsAwardRule = new PmsAwardRule();
            pmsAwardRule.setTimes(4);
            pmsAwardRule.setStatus("0");
            pmsAwardRule.setPlatformFlag("3");
            saveOrUpdate(pmsAwardRule);
        }
        //  PmsAwardRule pmsAwardRule = new PmsAwardRule();
        resultObject.setMessage("查询用户抽奖次数完成");
        resultObject.setSuccess(true);
        resultObject.setData(pmsAwardRule);
        return resultObject;
    }
}
