package com.here.modules.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.dto.PmsAttrCategoryItem;
import com.here.modules.product.entity.PmsAttrCategory;
import com.here.modules.product.mapper.PmsAttrCategoryMapper;
import com.here.modules.product.service.PmsAttrCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性分类管理Service实现类
 *
 * @author syj
 * @since 2023/2/17
 */
@Service
public class PmsAttrCategoryServiceImpl extends ServiceImpl<PmsAttrCategoryMapper, PmsAttrCategory> implements PmsAttrCategoryService {

    @Autowired
    private PmsAttrCategoryMapper attrCategoryMapper;

    @Override
    public int create(String name) {
        PmsAttrCategory attrCategory = new PmsAttrCategory();
        attrCategory.setName(name);
        return baseMapper.insert(attrCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsAttrCategory attrCategory = new PmsAttrCategory();
        attrCategory.setName(name);
        attrCategory.setId(id);
        return baseMapper.updateById(attrCategory);
    }

    @Override
    public List<PmsAttrCategoryItem> getListWithAttr() {
        return attrCategoryMapper.getListWithAttr();
    }
}
