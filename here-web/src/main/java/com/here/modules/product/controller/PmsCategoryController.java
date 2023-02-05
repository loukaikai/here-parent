package com.here.modules.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.api.CommonPage;
import com.here.common.api.ResultObject;
import com.here.modules.product.entity.PmsCategory;
import com.here.modules.product.service.PmsCategoryService;
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
public class PmsCategoryController {
    @Autowired
    private PmsCategoryService pmsCategoryService;

    @ApiOperation("分页查询分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject<CommonPage<PmsCategory>> list(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "showStatus", required = false) Integer showStatus, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<PmsCategory> brandList = pmsCategoryService.list(keyword, showStatus, pageNum, pageSize);
        return ResultObject.success(CommonPage.restPage(brandList));
    }

    @ApiOperation("查出所有的分类以及子分类，以树形结构组装起来")
    @RequestMapping("/list/tree")
    public ResultObject<List<PmsCategory>> list() {
        return ResultObject.success(pmsCategoryService.listWithTree());
    }

    @ApiOperation("根据分类id查询分类详情")
    @RequestMapping("/info/{categoryId}")
    public ResultObject<PmsCategory> info(@PathVariable("categoryId") Long id) {
        return ResultObject.success(pmsCategoryService.getById(id));
    }

    @ApiOperation("分类保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultObject<Void> save(@Validated @RequestBody PmsCategory category) {
        boolean success = pmsCategoryService.saveOrUpdate(category);
        if (success) {
            return ResultObject.success(null);
        }

        return ResultObject.failed("分类保存失败");
    }

    @ApiOperation("分类状态修改")
    @RequestMapping("/update/status")
    public ResultObject<Integer> updateStatus(@Validated @RequestBody PmsCategory category) {
        boolean success = pmsCategoryService.updateById(category);
        if (success) {
            return ResultObject.success(null);
        }

        return ResultObject.failed("状态修改失败");
    }

    @ApiOperation("分类删除")
    @RequestMapping("/delete")
    public ResultObject<Void> delete(@RequestBody List<Long> ids) {
        boolean success = pmsCategoryService.removeBatchByIds(ids);
        if (success) {
            return ResultObject.success(null);
        }
        return ResultObject.failed("分类删除失败");
    }
}
