package com.here.modules.nameplate.service;

import java.util.List;

/**
 * @author Lzk
 */
public interface NameplateAssistService {

    /**
     * 获取助力用户头像链接
     * @param phone 用户手机号
     * @return 所有助力用户头像链接
     */
    List<String> getAssistUserUrls(String phone);

    /**
     * 用户助力
     * @param userPhone 用户手机号
     * @param assistUserPhone 助力用户手机号
     */
    void assistUser(String userPhone, String assistUserPhone);

}
