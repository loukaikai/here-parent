package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.entity.PmsSkuStock;

import java.util.List;

/**
 * 商品SKU库存管理Service
 *
 * @author syj
 * @since 2023-02-18
 */
public interface PmsSkuStockService extends IService<PmsSkuStock> {
    /**
     * 根据产品id和skuCode关键字模糊搜索
     */
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStock> skuStockList);
}
