package com.here.modules.badge.controller;

import com.here.common.api.ResultObject;
import com.here.modules.badge.dto.BadgeDTO;
import com.here.modules.badge.service.BadgeService;
import com.here.modules.badge.vo.BadgeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 徽章模块controller
 * @author Lzk
 */
@RequestMapping("/badge")
@RestController
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    /**
     * 增加徽章类型
     * @param badgeDTO 徽章信息
     * @return 徽章ID
     */
    @PostMapping
    public ResultObject<Integer> addBadge(@RequestBody BadgeDTO badgeDTO) {
        return ResultObject.success(badgeService.addBadge(badgeDTO));
    }

    /**
     * 获取用户已获得徽章名称
     * @param userId 用户ID
     * @return 用户徽章名称
     */
    @GetMapping("/user")
    public ResultObject<List<BadgeVO>> getBadgeByUserId(@RequestParam Integer userId) {
        return ResultObject.success(badgeService.getBadgeByUserId(userId));
    }

}
