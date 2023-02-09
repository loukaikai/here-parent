package com.here.modules.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.api.CommonPage;
import com.here.common.api.ResultObject;
import com.here.modules.product.entity.PmsBrand;
import com.here.modules.product.service.PmsBrandService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌管理Controller
 *
 * @author syj
 * @since 2023/1/30 21:53
 */
@RestController
@RequestMapping("product/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject<CommonPage<PmsBrand>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "showStatus", required = false) Integer showStatus,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<PmsBrand> brandList = pmsBrandService.listBrand(keyword, showStatus, pageNum, pageSize);
        return ResultObject.success(CommonPage.restPage(brandList));
    }


    @ApiOperation("根据品牌id查询品牌详情")
    @RequestMapping("/info/{brandId}")
    public ResultObject<PmsBrand> info(@PathVariable("brandId") Long brandId) {
        return ResultObject.success(pmsBrandService.getById(brandId));
    }

    @ApiOperation("品牌保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultObject<Void> save(@Validated @RequestBody PmsBrand brand) {
        return pmsBrandService.saveOrUpdate(brand) ? ResultObject.success(null) : ResultObject.failed("品牌保存失败");
    }

    @ApiOperation("品牌删除")
    @RequestMapping("/delete")
    public ResultObject<Void> delete(@RequestBody List<Long> brandIds) {
        return pmsBrandService.removeBatchByIds(brandIds) ? ResultObject.success(null)
                : ResultObject.failed("品牌删除失败");
    }
}
