package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.entity.PmsBrand;

/**
 * 商品品牌管理Service
 *
 * @author syj
 * @since 2023/1/30 21:53
 */
public interface PmsBrandService extends IService<PmsBrand> {
    /**
     * 分页查询品牌
     */
    Page<PmsBrand> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize);
}
