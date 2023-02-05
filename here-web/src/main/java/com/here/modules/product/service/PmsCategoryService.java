package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.entity.PmsCategory;

import java.util.List;

/**
 * 商品分类管理Service
 *
 * @author syj
 * @since 2023/1/30 21:53
 */
public interface PmsCategoryService extends IService<PmsCategory> {

    /**
     * 分页查询分类
     * @param keyword 查询关键字
     * @param showStatus 状态
     * @param pageNum 页数
     * @param pageSize 每页记录数
     * @return 分类列表
     */
    Page<PmsCategory> list(String keyword, Integer showStatus, int pageNum, int pageSize);

    /**
     * 查出所有的分类以及子分类，以树形结构组装起来
     * @return 树形展示分类
     */
    List<PmsCategory> listWithTree();
}
