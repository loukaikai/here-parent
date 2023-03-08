package com.here.modules.product.dto;

import com.here.modules.product.entity.PmsAttributeValue;
import com.here.modules.product.entity.PmsProduct;
import com.here.modules.product.entity.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 创建和修改商品的请求参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PmsProductParam extends PmsProduct {

    private static final long serialVersionUID = -5877907219335249080L;

    @ApiModelProperty("商品的sku库存信息")
    private List<PmsSkuStock> skuStockList;

    @ApiModelProperty("商品参数及自定义规格属性")
    private List<PmsAttributeValue> productAttributeValueList;

}
