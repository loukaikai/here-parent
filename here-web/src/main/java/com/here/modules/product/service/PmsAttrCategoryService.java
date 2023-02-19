package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.dto.PmsAttrCategoryItem;
import com.here.modules.product.entity.PmsAttrCategory;

import java.util.List;

/**
 * 商品属性分类管理Service
 *
 * @author syj
 * @since 2023/2/17
 */
public interface PmsAttrCategoryService extends IService<PmsAttrCategory> {

    /**
     * 创建属性分类
     *
     * @param name 名称
     * @return 创建个数
     */
    int create(String name);

    /**
     * 修改属性分类
     *
     * @param id   id
     * @param name 名称
     * @return 修改个数
     */
    int update(Long id, String name);

    /**
     * 获取包含属性的属性分类
     */
    List<PmsAttrCategoryItem> getListWithAttr();
}
