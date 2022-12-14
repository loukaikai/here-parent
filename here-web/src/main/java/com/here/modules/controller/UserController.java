package com.here.modules.controller;


import com.here.common.api.ResultObject;
import com.here.modules.service.HereUserService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private HereUserService userService;

    @PostMapping("/center")
    public ResultObject myCenter() {
        try {
            return new ResultObject(userService.getUser(),"success",200);
        }catch (Exception e){
            return new ResultObject(null,"没有获取到当前登录用户信息！",500);
        }
    }
}
