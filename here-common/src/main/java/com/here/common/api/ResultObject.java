package com.here.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="统一返回值", description="统一返回值")
public class ResultObject<T> implements Serializable {

    @ApiModelProperty(value = "返回数据")
    private T data;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "响应值")
    private long code;

    @ApiModelProperty(value = "是否处理成功")
    private boolean isSuccess = true;

    protected ResultObject(long code, String message, T data, boolean isSuccess) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResultObject<T> success(T data) {
        return new ResultObject<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, true);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResultObject<T> success(T data, String message) {
        return new ResultObject<T>(ResultCode.SUCCESS.getCode(), message, data, true);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> ResultObject<T> failed(IErrorCode errorCode) {
        return new ResultObject<T>(errorCode.getCode(), errorCode.getMessage(), null, false);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> ResultObject<T> failed(IErrorCode errorCode,String message) {
        return new ResultObject<T>(errorCode.getCode(), message, null, false);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResultObject<T> failed(String message) {
        return new ResultObject<T>(ResultCode.FAILED.getCode(), message, null, false);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResultObject<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ResultObject<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> ResultObject<T> validateFailed(String message) {
        return new ResultObject<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null, false);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResultObject<T> unauthorized(T data) {
        return new ResultObject<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data, false);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResultObject<T> forbidden(T data) {
        return new ResultObject<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data, false);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}

