package com.here.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.entity.PmsBrand;
import com.here.modules.product.entity.PmsCategory;
import com.here.modules.product.entity.PmsCategoryBrandRelation;
import com.here.modules.product.mapper.PmsBrandMapper;
import com.here.modules.product.mapper.PmsCategoryBrandRelationMapper;
import com.here.modules.product.mapper.PmsCategoryMapper;
import com.here.modules.product.service.PmsCategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品品牌分类关联管理Service实现类
 *
 * @author syj
 * @since 2023/2/4
 */
@Service
public class PmsCategoryBrandRelationServiceImpl extends ServiceImpl<PmsCategoryBrandRelationMapper,
        PmsCategoryBrandRelation> implements PmsCategoryBrandRelationService {

    @Autowired
    private PmsBrandMapper brandMapper;

    @Autowired
    private PmsCategoryMapper categoryMapper;

    @Override
    public void saveDetail(PmsCategoryBrandRelation categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        //查询详细名字
        PmsBrand brandEntity = brandMapper.selectById(brandId);
        PmsCategory categoryEntity = categoryMapper.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.saveOrUpdate(categoryBrandRelation);
    }
}
