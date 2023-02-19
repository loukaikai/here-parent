package com.here.modules.product.controller;

import com.here.common.api.CommonResult;
import com.here.modules.product.entity.PmsSkuStock;
import com.here.modules.product.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品SKU库存管理Controller
 *
 * @author syj
 * @since 2023-02-18
 */
@RestController
@RequestMapping("/sku")
@Api(value = "PmsSkuStockController", tags = "sku商品库存管理")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;

    @ApiOperation(value = "根据商品ID及sku编码模糊搜索sku库存")
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsSkuStock>> getList(@PathVariable Long pid, @RequestParam(value = "keyword", required
            = false) String keyword) {
        return CommonResult.success(skuStockService.getList(pid, keyword));
    }

    @ApiOperation(value = "批量更新sku库存信息")
    @RequestMapping(value = "/update/{pid}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long pid, @RequestBody List<PmsSkuStock> skuStockList) {
        int count = skuStockService.update(pid, skuStockList);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}
