package com.here.modules.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.product.entity.PmsBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品品牌管理mapper
 *
 * @author syj
 * @since 2023/1/30 21:57
 */
@Mapper
public interface PmsBrandMapper extends BaseMapper<PmsBrand> {

    /**
     * 修改显示状态
     */
    int updateShowStatus(@Param("ids") List<Long> ids, @Param("status") Integer showStatus);

    /**
     * 修改厂家制造商状态
     */
    int updateFactoryStatus(@Param("ids") List<Long> ids, @Param("status") Integer factoryStatus);

}
