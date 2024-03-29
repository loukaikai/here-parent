package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.dto.PmsCategoryWithChildrenItem;
import com.here.modules.product.entity.PmsCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类管理mapper
 *
 * @author syj
 * @since 2023/1/30 21:57
 */
@Mapper
public interface PmsCategoryMapper extends BaseMapper<PmsCategory> {

    /**
     * 获取商品分类及其子分类
     */
    List<PmsCategoryWithChildrenItem> listWithChildren();


    /**
     * 批量修改导航状态
     */
    int updateNavStatus(@Param("ids") List<Long> ids, @Param("status") Integer navStatus);

    /**
     * 批量修改显示状态
     */
    int updateShowStatus(@Param("ids") List<Long> ids, @Param("status") Integer showStatus);
}
