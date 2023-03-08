package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.dto.PmsCategoryParam;
import com.here.modules.product.dto.PmsCategoryWithChildrenItem;
import com.here.modules.product.entity.PmsCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类管理Service
 *
 * @author syj
 * @since 2023/1/30 21:53
 */
public interface PmsCategoryService extends IService<PmsCategory> {

    /**
     * 创建商品分类
     */
    @Transactional
    int create(PmsCategoryParam pmsCategoryParam);

    /**
     * 修改商品分类
     */
    @Transactional
    int update(Long id, PmsCategoryParam pmsCategoryParam);

    /**
     * 分页获取商品分类
     */
    Page<PmsCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 批量修改导航状态
     */
    int updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 批量修改显示状态
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 以层级形式获取商品分类
     */
    List<PmsCategoryWithChildrenItem> listWithChildren();
}
