package com.here.modules.product.controller;

import com.here.common.api.CommonPage;
import com.here.common.api.CommonResult;
import com.here.modules.product.dto.PmsBrandParam;
import com.here.modules.product.entity.PmsBrand;
import com.here.modules.product.service.PmsBrandService;
import io.swagger.annotations.Api;
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
@RequestMapping("/brand")
@Api(value = "PmsBrandController", tags = "商品品牌管理")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getList() {
        return CommonResult.success(brandService.list());
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@Validated @RequestBody PmsBrandParam pmsBrand) {
        int count = brandService.createBrand(pmsBrand);
        return count == 1 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable("id") Long id,
                                        @Validated @RequestBody PmsBrandParam pmsBrandParam) {
        int count = brandService.updateBrand(id, pmsBrandParam);
        return count == 1 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Void> delete(@PathVariable("id") Long id) {
        return brandService.removeById(id) ? CommonResult.success(null) : CommonResult.failed();
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "showStatus", required = false) Integer showStatus,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return CommonResult.success(CommonPage.restPage(brandService.listBrand(keyword, showStatus, pageNum,
                pageSize)));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> getItem(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getById(id));
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Void> deleteBatch(@RequestParam("ids") List<Long> ids) {
        return brandService.removeBatchByIds(ids) ? CommonResult.success(null) : CommonResult.failed();
    }

    @ApiOperation(value = "批量更新显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateShowStatus(@RequestParam("ids") List<Long> ids,
                                                  @RequestParam("showStatus") Integer showStatus) {
        int count = brandService.updateShowStatus(ids, showStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                                     @RequestParam("factoryStatus") Integer factoryStatus) {
        int count = brandService.updateFactoryStatus(ids, factoryStatus);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}
