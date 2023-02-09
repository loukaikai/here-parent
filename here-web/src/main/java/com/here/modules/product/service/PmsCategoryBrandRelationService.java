package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.entity.PmsCategoryBrandRelation;

/**
 * 商品品牌分类关联管理Service
 *
 * @author syj
 * @since 2023/2/10
 */
public interface PmsCategoryBrandRelationService extends IService<PmsCategoryBrandRelation> {

    void saveDetail(PmsCategoryBrandRelation categoryBrandRelation);

}
