package com.here.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.here.common.aop.LogPlus;
import com.here.common.domain.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.*;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName CommonUtil.java
 * @Description TODO
 * @createTime 2022年12月12日 23:15:00
 */
@Slf4j
public class CommonUtil {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }


    /**
     * 获取请求参数
     *
     * @param joinPoint 切入点
     * @param signature 方法签名
     * @param logDO     日志对象
     */
    public static void getRequestParam(ProceedingJoinPoint joinPoint, MethodSignature signature, SysLog logDO) {
        // 参数值
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<>(32);
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
            if (args[i] != null) {
                //反射获取具体的值
                LogPlus logPlus = args[i].getClass().getAnnotation(LogPlus.class);
                if (logPlus != null) {
                    Field f = null;
                    try {
                        f = args[i].getClass().getDeclaredField(logPlus.editTableId());
                        f.setAccessible(true);
                        Object obj = f.get(args[i]);
                        logDO.setEditTableId(Long.valueOf(obj + ""));
                        logDO.setEditTableName(logPlus.editTableName());
                    } catch (Exception e) {
                        log.error("反射获取编辑的表主键异常：{}", e.getMessage());
                    } finally {
                        if (f != null) {
                            f.setAccessible(false);
                        }
                    }
                }
            }
        }
        logDO.setRequestParam(paramMap.toString());
    }
}
