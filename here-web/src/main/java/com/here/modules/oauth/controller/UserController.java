package com.here.modules.oauth.controller;


import com.here.common.api.ResultObject;
import com.here.modules.oauth.service.HereUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
}
