package com.here.modules.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.api.CommonPage;
import com.here.common.api.CommonResult;
import com.here.common.api.ResultObject;
import com.here.modules.order.dto.HereOrdersDTO;
import com.here.modules.order.entity.HereOrders;
import com.here.modules.order.service.HereOrdersService;
import com.here.security.component.JwtAuthenticationTokenFilter;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *      订单表 前端控制器
 * </p>
 *
 * @author loukaikai
 * @since 2022-12-16
 */
@RestController
@RequestMapping("wx/order")
public class HereOrdersController {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private HereOrdersService hereOrdersService;

    @ApiOperation(value = "订单添加")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject<HereOrdersDTO> crate(@Validated @RequestBody HereOrders dto) {
        logger.info("订单添加控制层========>start");
        boolean success = hereOrdersService.addHereOrder(dto);
        if (success) {
            return ResultObject.success(null,"订单添加成功");
        }

        return ResultObject.failed("订单添加失败");
    }

    @ApiOperation("根据用户查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject<CommonPage<HereOrders>> list(@RequestParam(value = "userId", required = false) Integer userId,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page<HereOrders> hereOrdersList = hereOrdersService.list(userId, pageSize, pageNum);
        return ResultObject.success(CommonPage.restPage(hereOrdersList));
    }

}

