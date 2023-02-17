package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.dto.ProductAttrInfo;
import com.here.modules.product.entity.PmsAttrCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品属性分类mapper
 *
 * @author syj
 * @since 2023/2/4
 */
@Mapper
public interface PmsAttrCategoryMapper extends BaseMapper<PmsAttrCategory> {
    /**
     * 获取商品属性信息
     */
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
