package com.here.modules.product.controller;

import com.here.common.api.ResultObject;
import com.here.modules.product.entity.PmsCategory;
import com.here.modules.product.service.PmsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类管理Controller
 *
 * @author syj
 * @since 2023/1/30 21:53
 */
@RestController
@RequestMapping("product/category")
@Api(value = "PmsCategoryController", tags = "商品分类管理")
public class PmsCategoryController {
    @Autowired
    private PmsCategoryService pmsCategoryService;

    @ApiOperation(value = "查出所有的分类以及子分类，以树形结构组装起来")
    @RequestMapping("/list/tree")
    public ResultObject<List<PmsCategory>> list() {
        return ResultObject.success(pmsCategoryService.listWithTree());
    }

    @ApiOperation(value = "根据分类id查询分类详情")
    @RequestMapping("/info/{categoryId}")
    public ResultObject<PmsCategory> info(@PathVariable("categoryId") Long id) {
        return ResultObject.success(pmsCategoryService.getById(id));
    }

    @ApiOperation(value = "分类保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultObject<Void> save(@Validated @RequestBody PmsCategory category) {
        return pmsCategoryService.saveOrUpdate(category) ? ResultObject.success(null)
                : ResultObject.failed("分类保存失败");
    }

    @ApiOperation(value = "分类删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultObject<Void> delete(@RequestBody List<Long> ids) {
        pmsCategoryService.removeMenuByIds(ids);
        return ResultObject.success(null);
    }
}
