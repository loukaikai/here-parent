package com.here.modules.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.here.common.api.ResultObject;
import com.here.common.utils.WechatUtil;
import com.here.modules.entity.HereUser;
import com.here.modules.service.HereUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private HereUserService userService;

    /**
     * 微信登录
     * @param code
     * @param rawData
     * @param signature
     * @return
     */
    @PostMapping("/weChart")
    public ResultObject weChartLogin(@RequestParam(value = "code", required = false) String code, //小程序code
                                   @RequestParam(value = "rawData", required = false) String rawData, // 用户非敏感信息
                                   @RequestParam(value = "signature", required = false) String signature // 签名
    ) {
        JSONObject rawDataJson = JSON.parseObject(rawData);
        //开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        //接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        // 校验签名
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return new ResultObject("签名校验失败",null,500);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；
        LambdaQueryWrapper<HereUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(HereUser::getOpenId, openid);
        HereUser user = userService.getOne(lqw);
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            user = new HereUser();
            user.setOpenId(openid);
            user.setWecharHeadsUrl(avatarUrl);
            user.setWecharName(nickName);
            userService.save(user);
        }
        return new ResultObject(user,"success",200);
    }
}
