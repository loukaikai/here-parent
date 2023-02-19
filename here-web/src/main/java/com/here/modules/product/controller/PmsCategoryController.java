package com.here.modules.product.controller;

import com.here.common.api.CommonPage;
import com.here.common.api.CommonResult;
import com.here.modules.product.dto.PmsCategoryParam;
import com.here.modules.product.dto.PmsCategoryWithChildrenItem;
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
@RequestMapping("/productCategory")
@Api(value = "PmsCategoryController", tags = "商品分类管理")
public class PmsCategoryController {
    @Autowired
    private PmsCategoryService categoryService;

    @ApiOperation(value = "添加商品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@Validated @RequestBody PmsCategoryParam productCategoryParam) {
        int count = categoryService.create(productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long id,
                                        @Validated
                                        @RequestBody PmsCategoryParam productCategoryParam) {
        int count = categoryService.update(id, productCategoryParam);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsCategory>> getList(@PathVariable Long parentId,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return CommonResult.success(CommonPage.restPage(categoryService.getList(parentId, pageSize, pageNum)));
    }

    @ApiOperation(value = "根据id获取商品分类")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsCategory> getItem(@PathVariable Long id) {
        return CommonResult.success(categoryService.getById(id));
    }

    @ApiOperation(value = "删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Void> delete(@PathVariable Long id) {
        return categoryService.removeById(id) ? CommonResult.success(null) : CommonResult.failed();
    }

    @ApiOperation(value = "修改导航栏显示状态")
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        int count = categoryService.updateNavStatus(ids, navStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        int count = categoryService.updateShowStatus(ids, showStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("查询所有一级分类及子分类")
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsCategoryWithChildrenItem>> listWithChildren() {
        return CommonResult.success(categoryService.listWithChildren());
    }
}
