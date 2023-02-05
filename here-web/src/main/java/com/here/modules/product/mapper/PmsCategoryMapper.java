package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.entity.PmsCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类管理mapper
 *
 * @author syj
 * @since 2023/1/30 21:57
 */
@Mapper
public interface PmsCategoryMapper extends BaseMapper<PmsCategory> {

}
