package com.here.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.entity.PmsSkuStock;
import com.here.modules.product.mapper.PmsSkuStockMapper;
import com.here.modules.product.service.PmsSkuStockService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品SKU库存管理Service实现类
 *
 * @author syj
 * @since 2023-02-18
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements PmsSkuStockService {

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        return baseMapper.selectByPid(pid, keyword);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        baseMapper.deleteById(pid);
        return baseMapper.replaceList(skuStockList);
    }
}
