package com.here.modules.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.here.common.api.ResultObject;
import com.here.modules.product.entity.PmsCategoryBrandRelation;
import com.here.modules.product.service.PmsCategoryBrandRelationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌分类关联Controller
 *
 * @author syj
 * @since 2023/2/10
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class PmsCategoryBrandRelationController {
    @Autowired
    private PmsCategoryBrandRelationService categoryBrandRelationService;

    @ApiOperation("获取当前品牌关联的所有分类列表")
    @RequestMapping(value = "/catelog/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject<List<PmsCategoryBrandRelation>> cateloglist(@RequestParam("brandId") Long brandId) {
        return ResultObject.success(categoryBrandRelationService.list(new QueryWrapper<PmsCategoryBrandRelation>()
                .eq("brand_id", brandId)));
    }

    @ApiOperation("获取当前分类关联的所有品牌列表")
    @RequestMapping(value = "/brands/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject<List<PmsCategoryBrandRelation>> relationBrandsList(@RequestParam(value = "catId")Long catId) {
        return ResultObject.success(categoryBrandRelationService.list(new QueryWrapper<PmsCategoryBrandRelation>()
                .eq("catelog_id", catId)));
    }


    @ApiOperation("品牌分类关联保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultObject<Void> save(@Validated @RequestBody PmsCategoryBrandRelation relation) {
        categoryBrandRelationService.saveDetail(relation);
        return ResultObject.success(null);
    }

    @ApiOperation("品牌分类关联删除")
    @RequestMapping("/delete")
    public ResultObject<Void> delete(@RequestBody List<Long> ids) {
        return categoryBrandRelationService.removeByIds(ids) ? ResultObject.success(null)
                : ResultObject.failed("品牌删除失败");
    }
}
