package com.here.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.api.ResultObject;
import com.here.modules.order.dto.HereOrdersDTO;
import com.here.modules.order.entity.HereOrders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-16
 */
public interface HereOrdersService extends IService<HereOrders> {

    boolean addHereOrder(HereOrders dto);

    Page<HereOrders> list(Integer userId, Integer pageSize, Integer pageNum);

}
