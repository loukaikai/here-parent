package com.here.modules.wxshopinface.service;

import com.here.common.api.ResultObject;
import com.here.modules.oauth.dto.PhoneInfo;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WxInfaceService.java
 * @Description TODO
 * @createTime 2023年01月05日 16:49:00
 */
public interface WxInfaceService {

    public ResultObject<String> getAccToken();

    public ResultObject<PhoneInfo> getPhoneNum(String code);

    public ResultObject<Object> getJsApi();
}
