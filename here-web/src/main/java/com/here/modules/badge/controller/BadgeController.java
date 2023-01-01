package com.here.modules.badge.controller;

import com.here.common.api.ResultObject;
import com.here.modules.badge.dto.BadgeDTO;
import com.here.modules.badge.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 增加徽章
     * @param badgeDTO 徽章信息
     * @return 响应消息
     */
    @PostMapping
    public ResultObject<Void> addBadge(@RequestBody BadgeDTO badgeDTO) {
        badgeService.addBadge(badgeDTO);
        return ResultObject.success(null);
    }

}
