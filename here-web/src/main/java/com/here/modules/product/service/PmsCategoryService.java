package com.here.modules.product.service;

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
     * 查出所有的分类以及子分类，以树形结构组装起来
     * @return 树形展示分类
     */
    List<PmsCategory> listWithTree();

    /**
     * 根据id删除菜单
     * @param ids id集合
     */
    void removeMenuByIds(List<Long> ids);

    /**
     * 找到catelogId的完整路径；
     * @param catelogId 分类id
     * @return 完整路径
     */
    Long[] findCatelogPath(Long catelogId);
}
