package com.here.modules.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.entity.PmsBrand;
import com.here.modules.product.mapper.PmsBrandMapper;
import com.here.modules.product.service.PmsBrandService;
import org.springframework.stereotype.Service;

/**
 * 商品品牌管理Service实现类
 *
 * @author syj
 * @since 2023/1/30 21:56
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {
    @Override
    public Page<PmsBrand> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize) {
        Page<PmsBrand> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsBrand> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(String.valueOf(keyword))) {
            lambda.eq(PmsBrand::getName, keyword);
        }
        if (StrUtil.isNotEmpty(String.valueOf(showStatus))) {
            lambda.and(queryWrapper -> queryWrapper.eq(PmsBrand::getShowStatus, showStatus));
        }
        return page(page, wrapper);
    }

}
