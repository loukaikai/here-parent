package com.here.modules.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.dto.PmsBrandParam;
import com.here.modules.product.entity.PmsBrand;
import com.here.modules.product.entity.PmsProduct;
import com.here.modules.product.mapper.PmsBrandMapper;
import com.here.modules.product.mapper.PmsProductMapper;
import com.here.modules.product.service.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品品牌管理Service实现类
 *
 * @author syj
 * @since 2023/1/30 21:56
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return baseMapper.insert(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        productMapper.update(product, Wrappers.<PmsProduct>update().lambda().eq(PmsProduct::getBrandId, id));
        return baseMapper.updateById(pmsBrand);
    }

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

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        return baseMapper.updateShowStatus(ids, showStatus);
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        return baseMapper.updateFactoryStatus(ids, factoryStatus);
    }

}
