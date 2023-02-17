package com.here.modules.product.controller;

import com.here.common.api.CommonPage;
import com.here.common.api.CommonResult;
import com.here.modules.product.dto.ProductAttrInfo;
import com.here.modules.product.entity.PmsAttribute;
import com.here.modules.product.service.PmsAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 商品属性管理Controller
 *
 * @author syj
 * @since 2023/1/30
 */


@RestController
@RequestMapping("/productAttribute")
@Api(value = "PmsAttributeController", tags = "商品属性管理")
public class PmsAttributeController {
    @Autowired
    private PmsAttributeService attributeService;

    @ApiOperation(value = "根据分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query",
            dataType = "integer")})
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsAttribute>> getList(@PathVariable Long cid,
                                                          @RequestParam(value = "type") Integer type,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return CommonResult.success(CommonPage.restPage(attributeService.getList(cid, type, pageSize, pageNum)));
    }

    @ApiOperation(value = "添加商品属性信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@RequestBody PmsAttribute pmsAttribute) {
        int count = attributeService.create(pmsAttribute);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "修改商品属性信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Void> update(@RequestBody PmsAttribute pmsAttribute) {
        return attributeService.updateById(pmsAttribute) ? CommonResult.success(null) : CommonResult.failed();
    }

    @ApiOperation(value = "查询单个商品属性")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsAttribute> getItem(@PathVariable Long id) {
        return CommonResult.success(attributeService.getById(id));
    }

    @ApiOperation(value = "批量删除商品属性")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@RequestParam("ids") List<Long> ids) {
        int count = attributeService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation(value = "根据商品分类的id获取商品属性及属性分类")
    @RequestMapping(value = "/attrInfo/{productCategoryId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        return CommonResult.success(attributeService.getProductAttrInfo(productCategoryId));
    }
}
