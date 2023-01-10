package com.here.modules.oauth.dto;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName PhoneInfo.java
 * @Description TODO
 * @createTime 2023年01月09日 20:12:00
 */
public class PhoneInfo {

    // 用户绑定的手机号（国外手机号会有区号
    private String phoneNumber;

    // 没有区号的手机号
    private String purePhoneNumber;

    // 区号
    private String countryCode;

    // 数据水印
    private Watermark watermark;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurePhoneNumber() {
        return purePhoneNumber;
    }

    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }
}
