package com.here.modules.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.dto.PmsCategoryParam;
import com.here.modules.product.dto.PmsCategoryWithChildrenItem;
import com.here.modules.product.entity.PmsCategory;
import com.here.modules.product.entity.PmsCategoryAttributeRelation;
import com.here.modules.product.entity.PmsProduct;
import com.here.modules.product.mapper.PmsCateAttrRelationMapper;
import com.here.modules.product.mapper.PmsCategoryMapper;
import com.here.modules.product.mapper.PmsProductMapper;
import com.here.modules.product.service.PmsCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类管理Service实现类
 *
 * @author syj
 * @since 2023/1/30 21:56
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {

    @Autowired
    private PmsCateAttrRelationMapper cateAttrRelationMapper;

    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public int create(PmsCategoryParam pmsCategoryParam) {
        PmsCategory category = new PmsCategory();
        category.setProductCount(0);
        BeanUtils.copyProperties(pmsCategoryParam, category);
        //没有父分类时为一级分类
        setCategoryLevel(category);
        int count = baseMapper.insert(category);
        //创建筛选属性关联
        List<Long> productAttributeIdList = pmsCategoryParam.getProductAttributeIdList();
        if (!CollectionUtils.isEmpty(productAttributeIdList)) {
            insertRelationList(category.getId(), productAttributeIdList);
        }
        return count;
    }


    /**
     * 批量插入商品分类与筛选属性关系表
     *
     * @param productCategoryId      商品分类id
     * @param productAttributeIdList 相关商品筛选属性id集合
     */
    private void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        for (Long productAttrId : productAttributeIdList) {
            PmsCategoryAttributeRelation relation = new PmsCategoryAttributeRelation();
            relation.setAttributeId(productAttrId);
            relation.setCategoryId(productCategoryId);
            cateAttrRelationMapper.insert(relation);
        }
    }

    @Override
    public int update(Long id, PmsCategoryParam pmsCategoryParam) {
        PmsCategory productCategory = new PmsCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(pmsCategoryParam, productCategory);
        setCategoryLevel(productCategory);
        //更新商品分类时要更新商品中的名称
        PmsProduct product = new PmsProduct();
        product.setProductCategoryName(productCategory.getName());
        productMapper.update(product, Wrappers.<PmsProduct>update().lambda().eq(PmsProduct::getCategoryId, id));
        //同时更新筛选属性的信息
        cateAttrRelationMapper.delete(Wrappers.<PmsCategoryAttributeRelation>query().lambda().eq(PmsCategoryAttributeRelation::getCategoryId, id));
        if (!CollectionUtils.isEmpty(pmsCategoryParam.getProductAttributeIdList())) {
            insertRelationList(id, pmsCategoryParam.getProductAttributeIdList());
        }

        return baseMapper.updateById(productCategory);
    }

    @Override
    public Page<PmsCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        Page<PmsCategory> page = new Page<>(pageNum, pageSize);
        return page(page, Wrappers.<PmsCategory>lambdaQuery().eq(PmsCategory::getParentId, parentId));
    }

    @Override
    public int updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsCategory category = new PmsCategory();
        category.setNavStatus(navStatus);
        return baseMapper.update(category, Wrappers.<PmsCategory>update().lambda().in(PmsCategory::getId, ids));
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsCategory category = new PmsCategory();
        category.setShowStatus(showStatus);
        return baseMapper.update(category, Wrappers.<PmsCategory>update().lambda().in(PmsCategory::getId, ids));
    }

    @Override
    public List<PmsCategoryWithChildrenItem> listWithChildren() {
        return baseMapper.listWithChildren();
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(PmsCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsCategory parentCategory = baseMapper.selectById(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}
