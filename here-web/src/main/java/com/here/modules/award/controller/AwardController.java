package com.here.modules.award.controller;

import com.here.common.api.ResultObject;
import com.here.modules.award.dto.CouponDTO;
import com.here.modules.award.service.HereAwardUsrDetailService;
import com.here.modules.award.service.PmsAwardRuleService;
import com.here.modules.award.vo.AddAwardCountVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName AwardController.java
 * @Description TODO
 * @createTime 2022年12月31日 10:37:00
 */

@RestController
@RequestMapping("/award")
@Tag(name = "AwardController",description = "奖励管理")
public class AwardController {

    private static final Logger logger = LoggerFactory.getLogger(AwardController.class);

    @Autowired
    private PmsAwardRuleService pmsAwardRuleService;

    @Autowired
    private HereAwardUsrDetailService hereAwardUsrDetailService;

    @ApiOperation(value = "添加抽奖次数")
    @RequestMapping(value = "/addawardcount", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject<Object> addawardcount(@Validated @RequestBody AddAwardCountVO addAwardCountVO) {
        logger.info("添加抽奖次数控制层========>start");
        ResultObject<Object> resultObject = pmsAwardRuleService.addAwardCount(addAwardCountVO);
        if (resultObject.isSuccess()) {
            return resultObject;
        }

        return ResultObject.failed("添加抽奖次数失败");
    }

    @ApiOperation("查询用户抽奖次数")
    @RequestMapping(value = "/queryawardcount", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject<Object> queryAwardCount(@Validated @RequestBody AddAwardCountVO addAwardCountVO) {

        logger.info("查询用户抽奖次数控制层========>start");
        ResultObject<Object> resultObject = pmsAwardRuleService.queryAwardCount(addAwardCountVO);
        if (resultObject.isSuccess()) {
            return resultObject;
        }

        return ResultObject.failed("查询用户抽奖次数失败");
    }

    @ApiOperation("发放优惠券")
    @PostMapping("/coupon")
    public ResultObject<Void> addCoupon(@RequestBody CouponDTO couponDTO) {
        hereAwardUsrDetailService.addCoupon(couponDTO);
        return ResultObject.success(null);
    }

}
