package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.entity.PmsSkuStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存管理mapper
 * @author syj
 * @since 2023/2/18
 */
@Mapper
public interface PmsSkuStockMapper extends BaseMapper<PmsSkuStock> {

    /**
     * 根据商品id或者编码模糊查询
     *
     * @param pid     商品编码
     * @param keyword 关键字
     * @return 库存列表
     */
    List<PmsSkuStock> selectByPid(@Param("pid") Long pid, @Param("keyword") String keyword);

    /**
     * 批量插入或替换操作
     */
    int replaceList(@Param("list") List<PmsSkuStock> skuStockList);
}