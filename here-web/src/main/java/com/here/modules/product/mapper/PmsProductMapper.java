package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.dto.PmsProductResult;
import com.here.modules.product.entity.PmsProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品管理mapper
 *
 * @author syj
 * @since 2023/2/19
 */
@Mapper
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    /**
     * 获取商品编辑信息
     */
    PmsProductResult getUpdateInfo(@Param("id") Long id);

    /**
     * 批量更新上下架状态
     *
     * @param ids           变更的商品ids
     * @param publishStatus 上架状态
     * @return 返回数量
     */
    int updatePublishStatus(@Param("ids") List<Long> ids, @Param("status") Integer publishStatus);

    /**
     * 批量更新推荐状态
     *
     * @param ids             变更的商品ids
     * @param recommendStatus 推荐状态
     * @return 返回数量
     */
    int updateRecommendStatus(@Param("ids") List<Long> ids, @Param("status") Integer recommendStatus);

    /**
     * 批量更新状态
     *
     * @param ids       变更的商品ids
     * @param newStatus 新状态
     * @return 返回数量
     */
    int updateNewStatus(@Param("ids") List<Long> ids, @Param("status") Integer newStatus);


    /**
     * 批量更新删除状态
     *
     * @param ids          变更的商品ids
     * @param deleteStatus 删除状态
     * @return 返回数量
     */
    int updateDeleteStatus(@Param("ids") List<Long> ids, @Param("status") Integer deleteStatus);
}
