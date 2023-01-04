package com.here.modules.nameplate.controller;

import com.here.common.api.ResultObject;
import com.here.modules.nameplate.service.NameplateAssistService;
import com.here.modules.nameplate.service.NameplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 铭牌模块Controller
 * @author Lzk
 */
@RestController
@RequestMapping("/nameplate")
public class NameplateController {

    @Autowired
    private NameplateService nameplateService;

    @Autowired
    private NameplateAssistService nameplateAssistService;

    /**
     * 用户申请铭牌
     * @param phone 用户手机号
     * @return 响应信息
     */
    @PostMapping
    public ResultObject<Void> addNameplate(@RequestParam String phone) {
        nameplateService.addNameplate(phone);
        return ResultObject.success(null);
    }

    /**
     * 获取已取得铭牌用户名列表
     * @return 用户名列表
     */
    @GetMapping("/list")
    public ResultObject<List<String>> getNameplateList() {
        return ResultObject.success(nameplateService.getNameplateList());
    }

    /**
     * 获取用户铭牌编号
     * @param phone 用户手机号
     * @return 铭牌编号
     */
    @GetMapping("/check")
    public ResultObject<String> checkNameplate(@RequestParam String phone) {
        return ResultObject.success(nameplateService.getNameplateNumber(phone));
    }

    /**
     * 获取助力用户头像链接
     * @param phone 用户手机号
     * @return 所有助力用户头像链接
     */
    @GetMapping("/assist")
    public ResultObject<List<String>> getAssistUserUrls(@RequestParam String phone) {
        return ResultObject.success(nameplateAssistService.getAssistUserUrls(phone));
    }

    /**
     * 用户助力
     * @param userPhone 用户手机号
     * @param assistUserPhone 助力用户手机号
     * @return 响应消息
     */
    @PostMapping("/assist")
    public ResultObject<Void> assistUser(@RequestParam String userPhone, @RequestParam String assistUserPhone) {
        nameplateAssistService.assistUser(userPhone, assistUserPhone);
        return ResultObject.success(null);
    }

}
