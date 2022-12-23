package com.here.modules.order.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.api.ResultObject;
import com.here.common.exception.BizException;
import com.here.modules.order.dto.HereOrdersDTO;
import com.here.modules.order.entity.HereOrders;
import com.here.modules.order.mapper.HereOrdersMapper;
import com.here.modules.order.service.HereOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.security.component.JwtAuthenticationTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-16
 */
@Service
public class HereOrdersServiceImpl extends ServiceImpl<HereOrdersMapper, HereOrders> implements HereOrdersService {

    private static final Logger logger = LoggerFactory.getLogger(HereOrdersServiceImpl.class);

    @Override
    public boolean addHereOrder(HereOrders dto) {
        logger.info("订单添加服务========>start:参数[{}]", JSONObject.toJSONString(dto));

        String orderNo = dto.getOrderNo();
        QueryWrapper<HereOrders> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(HereOrders::getOrderNo, orderNo);
        HereOrders hereOrders = getOne(wrapper);
        if (Objects.nonNull(hereOrders)){
            logger.info("根据订单号[{}]查询数据不为空");
            throw new BizException("订单为空");
        }
        dto.setCreateTime(new Date());

        return save(dto);
    }

    @Override
    public Page<HereOrders> list(Integer userId, Integer pageSize, Integer pageNum) {
        Page<HereOrders> page = new Page<>(pageNum,pageSize);
        QueryWrapper<HereOrders> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<HereOrders> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(String.valueOf(userId))){
            lambda.eq(HereOrders::getUserId,userId);
        }
        return page(page,wrapper);
    }
}
