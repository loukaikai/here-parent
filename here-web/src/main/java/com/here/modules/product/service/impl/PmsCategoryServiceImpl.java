package com.here.modules.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.product.entity.PmsCategory;
import com.here.modules.product.mapper.PmsCategoryMapper;
import com.here.modules.product.service.PmsCategoryService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品分类管理Service实现类
 *
 * @author syj
 * @since 2023/1/30 21:56
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {
    @Override
    public Page<PmsCategory> list(String keyword, Integer showStatus, int pageNum, int pageSize) {
        Page<PmsCategory> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsCategory> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsCategory> lambda = wrapper.lambda();
        if (StrUtil.isNotEmpty(String.valueOf(keyword))) {
            lambda.eq(PmsCategory::getName, keyword);
        }
        if (StrUtil.isNotEmpty(String.valueOf(showStatus))) {
            lambda.and(queryWrapper -> queryWrapper.eq(PmsCategory::getShowStatus, showStatus));
        }
        return page(page, wrapper);
    }

    @Override
    public List<PmsCategory> listWithTree() {
        //查出所有的分类
        List<PmsCategory> entities = baseMapper.selectList(null);
        //组装成父子的树形结构
        return entities.stream().filter(categoryEntity -> categoryEntity.getParentId() == 0)
                .peek((menu) -> menu.setChildren(getChildrens(menu, entities)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    /**
     * 递归查询所有菜单的子菜单
     *
     * @param node 节点对象
     * @param list ；列表对象
     * @return 树对象
     */
    private List<PmsCategory> getChildrens(PmsCategory node, List<PmsCategory> list) {
        return list.stream().filter(categoryEntity -> Objects.equals(categoryEntity.getParentId(), node.getId()))
                .peek(categoryEntity -> categoryEntity.setChildren(getChildrens(categoryEntity, list)))
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() == null ? 0 : menu.getSort())))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[0]);
    }

    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        //收集当前节点id
        paths.add(catelogId);
        PmsCategory byId = this.getById(catelogId);
        if (byId.getParentId() != 0) {
            findParentPath(byId.getParentId(), paths);
        }
        return paths;
    }
}
