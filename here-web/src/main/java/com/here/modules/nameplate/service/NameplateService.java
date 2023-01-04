package com.here.modules.nameplate.service;

import java.util.List;

/**
 * @author Lzk
 */
public interface NameplateService {

    /**
     * 添加铭牌
     *
     * @param phone 用户手机号
     */
    void addNameplate(String phone);

    /**
     * 获取已获得铭牌用户列表
     * @return 用户名列表
     */
    List<String> getNameplateList();

    /**
     * 获取用户铭牌编号
     * @param phone 用户手机号
     * @return 铭牌编号
     */
    String getNameplateNumber(String phone);

}
