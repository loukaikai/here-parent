package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.entity.PmsAttrAttrGroupRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性&属性组关系mapper
 *
 * @author syj
 * @since 2023/2/4
 */
@Mapper
public interface PmsAttrAttrGroupRelationMapper extends BaseMapper<PmsAttrAttrGroupRelation> {
    void deleteBatchRelation(@Param("entities") List<PmsAttrAttrGroupRelation> entities);
}
