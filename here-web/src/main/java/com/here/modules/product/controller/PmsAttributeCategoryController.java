package com.here.modules.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.api.CommonPage;
import com.here.common.api.CommonResult;
import com.here.modules.product.dto.PmsAttrCategoryItem;
import com.here.modules.product.entity.PmsAttrCategory;
import com.here.modules.product.service.PmsAttrCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性分类管理Controller
 *
 * @author syj
 * @since 2023/2/17
 */
@Controller
@Api(tags = "PmsAttributeCategoryController")
@Tag(name = "PmsAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsAttributeCategoryController {
    @Autowired
    private PmsAttrCategoryService attributeCategoryService;

    @ApiOperation(value = "添加商品属性分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@RequestParam String name) {
        int count = attributeCategoryService.create(name);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "修改商品属性分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long id, @RequestParam String name) {
        int count = attributeCategoryService.update(id, name);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Void> delete(@PathVariable Long id) {
        return attributeCategoryService.removeById(id) ? CommonResult.success(null) : CommonResult.failed();
    }

    @ApiOperation(value = "获取单个商品属性分类信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsAttrCategory> getItem(@PathVariable Long id) {
        return CommonResult.success(attributeCategoryService.getById(id));
    }

    @ApiOperation(value = "分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsAttrCategory>> getList(@RequestParam(defaultValue = "5") Integer pageSize,
                                                             @RequestParam(defaultValue = "1") Integer pageNum) {
        return CommonResult.success(CommonPage.restPage(attributeCategoryService.page(new Page<>(pageNum, pageSize))));
    }

    @ApiOperation(value = "获取所有商品属性分类及其下属性")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsAttrCategoryItem>> getListWithAttr() {
        return CommonResult.success(attributeCategoryService.getListWithAttr());
    }
}
