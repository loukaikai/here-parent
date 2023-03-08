package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.entity.PmsCategoryAttributeRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 产品的分类和属性的关系管理mapper
 *
 * @author syj
 * @since 2023/1/30 21:57
 */
@Mapper
public interface PmsCateAttrRelationMapper extends BaseMapper<PmsCategoryAttributeRelation> {

}
