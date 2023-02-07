package com.here.modules.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.api.CommonPage;
import com.here.common.api.ResultObject;
import com.here.modules.product.entity.PmsSpuAttrValue;
import com.here.modules.product.service.PmsSpuAttrValueService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品spu属性值管理Controller
 *
 * @author syj
 * @since 2023/1/30 21:53
 */
@RestController
@RequestMapping("product/spuattrvalue")
public class PmsSpuAttrValueController {
    @Autowired
    private PmsSpuAttrValueService spuAttrValueService;

    @ApiOperation("分页查询spu属性值列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject<CommonPage<PmsSpuAttrValue>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<PmsSpuAttrValue> brandList = spuAttrValueService.list(keyword, pageNum, pageSize);
        return ResultObject.success(CommonPage.restPage(brandList));
    }


    @ApiOperation("根据品牌id查询spu属性值详情")
    @RequestMapping("/info/{id}")
    public ResultObject<PmsSpuAttrValue> info(@PathVariable("id") Long id) {
        return ResultObject.success(spuAttrValueService.getById(id));
    }

    @ApiOperation("spu属性值保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultObject<Void> save(@Validated @RequestBody PmsSpuAttrValue value) {
        return spuAttrValueService.saveOrUpdate(value) ? ResultObject.success(null) : ResultObject.failed("spu属性值保存失败");
    }

    @ApiOperation("spu属性值删除")
    @RequestMapping("/delete")
    public ResultObject<Void> delete(@RequestBody List<Long> ids) {
        return spuAttrValueService.removeBatchByIds(ids) ? ResultObject.success(null) : ResultObject.failed("spu" +
                "属性值删除失败");
    }
}
