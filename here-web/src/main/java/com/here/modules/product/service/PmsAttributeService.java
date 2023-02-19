package com.here.modules.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.here.modules.product.dto.ProductAttrInfo;
import com.here.modules.product.entity.PmsAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * 商品属性管理Service
 *
 * @author syj
 *   @since 2023/1/30 21:53
 */
public interface PmsAttributeService extends IService<PmsAttribute> {

    /**
     * 根据分类分页获取商品属性
     * @param cid 分类id
     * @param type 0->规格；1->参数
     */
    Page<PmsAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    @Transactional
    int create(PmsAttribute pmsAttribute);

    /**
     * 批量删除商品属性
     */
    @Transactional
    int delete(List<Long> ids);

    /**
     * 获取商品分类对应属性列表
     */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
