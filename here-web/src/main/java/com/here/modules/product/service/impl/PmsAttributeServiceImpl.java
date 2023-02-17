package com.here.modules.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.dto.ProductAttrInfo;
import com.here.modules.product.entity.PmsAttrCategory;
import com.here.modules.product.entity.PmsAttribute;
import com.here.modules.product.mapper.PmsAttrCategoryMapper;
import com.here.modules.product.mapper.PmsAttributeMapper;
import com.here.modules.product.service.PmsAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性管理Service实现类
 *
 * @author syj
 * @since 2023/2/10
 */
@Service
public class PmsAttributeServiceImpl extends ServiceImpl<PmsAttributeMapper, PmsAttribute> implements PmsAttributeService {

    @Autowired
    private PmsAttrCategoryMapper attrCategoryMapper;

    @Override
    public Page<PmsAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        Page<PmsAttribute> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsAttribute> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsAttribute> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(String.valueOf(cid))) {
            lambda.eq(PmsAttribute::getAttrCatelogId, cid);
        }
        if (StrUtil.isNotEmpty(String.valueOf(type))) {
            lambda.eq(PmsAttribute::getType, type);
        }
        return page(page, wrapper);
    }

    @Override
    public int create(PmsAttribute pmsAttribute) {
        int count = baseMapper.insert(pmsAttribute);
        //新增商品属性以后需要更新商品属性分类数量
        PmsAttrCategory attrCategory = attrCategoryMapper.selectById(pmsAttribute.getAttrCatelogId());
        if (pmsAttribute.getType() == 0) {
            attrCategory.setAttributeCount(attrCategory.getAttributeCount() + 1);
        } else if (pmsAttribute.getType() == 1) {
            attrCategory.setParamCount(attrCategory.getParamCount() + 1);
        }
        attrCategoryMapper.updateById(attrCategory);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        PmsAttribute pmsAttribute = baseMapper.selectById(ids.get(0));
        Integer type = pmsAttribute.getType();
        PmsAttrCategory pmsProductAttributeCategory = attrCategoryMapper.selectById(pmsAttribute.getAttrCatelogId());
        int count = baseMapper.deleteBatchIds(ids);
        //删除完成后修改数量
        if (type == 0) {
            if (pmsProductAttributeCategory.getAttributeCount() >= count) {
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount() - count);
            } else {
                pmsProductAttributeCategory.setAttributeCount(0);
            }
        } else if (type == 1) {
            if (pmsProductAttributeCategory.getParamCount() >= count) {
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount() - count);
            } else {
                pmsProductAttributeCategory.setParamCount(0);
            }
        }
        attrCategoryMapper.updateById(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return attrCategoryMapper.getProductAttrInfo(productCategoryId);
    }
}
