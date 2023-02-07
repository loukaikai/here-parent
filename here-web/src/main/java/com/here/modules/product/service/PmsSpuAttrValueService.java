package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.entity.PmsSpuAttrValue;

/**
 * 商品品牌管理Service
 *
 * @author syj
 * @since 2023/1/30 21:53
 */
public interface PmsSpuAttrValueService extends IService<PmsSpuAttrValue> {
    /**
     * 分页查询spu属性值
     */
    Page<PmsSpuAttrValue> list(String keyword, int pageNum, int pageSize);
}
