package com.here.modules.oauth.controller;


import com.here.common.aop.Log;
import com.here.common.api.ResultObject;
import com.here.modules.oauth.service.HereUserService;
import com.here.modules.oauth.vo.InitVO;
import com.here.modules.oauth.vo.ThirdOauthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private HereUserService userService;

    @PostMapping("/center")
    public ResultObject myCenter() {
        try {
            return ResultObject.success(userService.getUser(),"success");
        }catch (Exception e){
            return ResultObject.failed();
        }
    }

    /**
     * 用户初始化
     * **/
    @PostMapping("/initUser")
    @Log
    public ResultObject init(@Validated @RequestBody InitVO initVO) {
        try {
            return userService.initUser(initVO);
        }catch (Exception e){
            return ResultObject.failed();
        }
    }
}
