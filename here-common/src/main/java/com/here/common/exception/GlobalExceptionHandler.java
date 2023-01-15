package com.here.common.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.here.common.api.CommonResult;
import com.here.common.api.ResultObject;
import io.netty.util.internal.ThrowableUtil;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 全局异常处理
 * Created by macro on 2020/2/27.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResultObject handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return ResultObject.failed(e.getErrorCode());
        }
        return ResultObject.failed(e.getMessage());
    }


    /**
     * 处理空指针，业务的异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ResultObject Bizxceptionhandle(BizException e) {
        logger.error("发生业务异常！原因是:",e);
        if (e.getErrorCode() != null) {
            return ResultObject.failed(e.getErrorCode(), e.getMessage());
        }
        return ResultObject.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultObject handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResultObject.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResultObject handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return ResultObject.validateFailed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultObject httpMessageNotReadableException(HttpMessageNotReadableException e) {
        // 打印堆栈信息
        logger.info("[{}],[{}]",e, e.getMessage());

       /* String erroMessage = null;
        Throwable cause = e.getCause();
        if (cause instanceof JsonMappingException) {
            List<JsonMappingException.Reference> list = ((JsonMappingException) cause).getPath();
            for (JsonMappingException.Reference r : list) {
                Object object = r.getFrom();
                Class<?> c = object.getClass();
                String fieldName = r.getFieldName();
                Field field;
                try {
                    field = c.getDeclaredField(fieldName);
                    JsonFormatVaild jsonFormatVaild = field.getDeclaredAnnotation(JsonFormatVaild.class);
                    erroMessage = jsonFormatVaild.message();
                } catch (NoSuchFieldException noSuchFieldException) {
                    noSuchFieldException.printStackTrace();
                }
                if (erroMessage != null) {
                    break;
                }
            }
        }*/
         return ResultObject.failed(e.getMessage());

    }
}
