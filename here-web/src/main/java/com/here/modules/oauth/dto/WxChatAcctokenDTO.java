package com.here.modules.oauth.dto;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WxChatAcctokenDTO.java
 * @Description TODO
 * @createTime 2022年12月23日 11:43:00
 */
public class WxChatAcctokenDTO {

    private String accessToken;

    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
