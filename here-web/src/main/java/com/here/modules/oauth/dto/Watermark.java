package com.here.modules.oauth.dto;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName Watermark.java
 * @Description TODO
 * @createTime 2023年01月09日 20:15:00
 */
public class Watermark {

    // 用户获取手机号操作的时间戳
    private String timestamp;

    // 小程序appid
    private String appid;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
