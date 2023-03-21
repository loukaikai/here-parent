package com.here.modules.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.order.entity.HereOrders;

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

    /**
     * 完成订单
     * @param orderId 订单ID
     */
    void completeOrder(Long orderId);

}
