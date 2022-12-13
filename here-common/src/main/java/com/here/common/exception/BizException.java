package com.here.common.exception;

import com.here.common.api.IErrorCode;

/**
 * 自定义运行时异常，用于处理我们发生的业务异常
 * Created by loukk on 2022/12/12.
 */
public class BizException extends RuntimeException{

    private IErrorCode errorCode;

    public BizException(IErrorCode errorCode, String message) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BizException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

}
