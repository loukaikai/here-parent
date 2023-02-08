package com.here.modules.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.entity.PmsSpuAttrValue;
import com.here.modules.product.mapper.PmsSpuAttrValueMapper;
import com.here.modules.product.service.PmsSpuAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品品牌管理Service实现类
 *
 * @author syj
 * @since 2023/1/30 21:56
 */
@Service
public class PmsSpuAttrValueServiceImpl extends ServiceImpl<PmsSpuAttrValueMapper, PmsSpuAttrValue> implements PmsSpuAttrValueService {
    @Override
    public Page<PmsSpuAttrValue> list(String keyword, int pageNum, int pageSize) {
        Page<PmsSpuAttrValue> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsSpuAttrValue> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsSpuAttrValue> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(String.valueOf(keyword))) {
            lambda.eq(PmsSpuAttrValue::getAttrName, keyword);
        }
        return page(page, wrapper);
    }

    @Override
    public void saveProductAttr(List<PmsSpuAttrValue> collect) {
        this.saveBatch(collect);
    }

    @Override
    public List<PmsSpuAttrValue> baseAttrlistforspu(Long spuId) {
        return this.baseMapper.selectList(new QueryWrapper<PmsSpuAttrValue>().eq("spu_id", spuId));
    }

    @Override
    public void updateSpuAttr(Long spuId, List<PmsSpuAttrValue> entities) {
        //1、删除这个spuId之前对应的所有属性
        this.baseMapper.delete(new QueryWrapper<PmsSpuAttrValue>().eq("spu_id",spuId));
        List<PmsSpuAttrValue> collect = entities.stream().peek(item -> item.setSpuId(spuId)).collect(Collectors.toList());
        this.saveBatch(collect);
    }

}
