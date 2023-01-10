package com.here.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.here.common.constant.ModuleConstant;
import com.here.common.domain.SysLog;
import com.here.common.exception.BizException;
import com.here.common.mapper.SysLogMapper;
import com.here.common.utils.CommonUtil;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName LogAspect.java
 * @Description TODO
 * @createTime 2022年12月12日 23:08:00
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Autowired
    SysLogMapper logMapper;
    @Value("${spring.application.name}")
    private String appNname;

    @Pointcut("@annotation(com.here.common.aop.Log)")
    public void logPoint() {
    }

    @Around("logPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("系统操作日志=========>start");
        Object result = null;
        SysLog logDO = new SysLog();
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = (HttpServletRequest) requestAttributes
                    .resolveReference(RequestAttributes.REFERENCE_REQUEST);
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            //浏览器对象
            Browser browser = userAgent.getBrowser();
            //操作系统对象
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            logDO.setBrowserName(browser.getName());
            ApiOperation aon = methodSignature.getMethod().getAnnotation(ApiOperation.class);
            if (aon != null) {
                logDO.setModuleName(aon.value());
            }
            logDO.setOsName(operatingSystem.getName());
            logDO.setIpAddr(CommonUtil.getIpAddr(request));
            logDO.setAppName(appNname);
            logDO.setClassName(joinPoint.getTarget().getClass().getName());
            logDO.setMethodName(methodSignature.getMethod().getName());
            logDO.setRequestUrl(request.getRequestURI());
            logDO.setRequestMethod(request.getMethod());
            //获取请求参数
            CommonUtil.getRequestParam(joinPoint, methodSignature, logDO);
            logDO.setResultText(JSON.toJSONString(result));
            logDO.setStatus((byte) 0);
            logDO.setCreateTime(CommonUtil.getCurrentDate());
            logDO.setModuleName(ModuleConstant.HERE_WEB.getModuleName());
           /* logDO.setCreateUserId(CommonUtil.getCurrentUserId());
            logDO.setCreatePhoneNumber(CommonUtil.getCurrentPhoneNumber());
            logDO.setCreateUserName(CommonUtil.getCurrentUserName());*/
            long startTime = System.currentTimeMillis();
            result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logDO.setTakeUpTime(String.format("耗时：%s 毫秒", endTime - startTime));
            logDO.setResultText(result.toString());
        } catch (Exception e) {
            logDO.setStatus((byte) 1);
            if (e instanceof BizException) {
                BizException bizException = (BizException) e;
               // result = JsonData.buildCodeAndMsg(bizException.getCode(), bizException.getMessage());
                logDO.setErrorText(bizException.getMessage());
            } /*else if (e instanceof RpvException) {
                RpvException ve = (RpvException) e;
                result = JsonData.buildCodeAndMsg(ve.getCode(), ve.getMessage());
                logDO.setErrorText(result.toString());
            }*/ else {
                logDO.setErrorText(e.getMessage());
                result = e.getMessage();
            }
        } finally {
            logger.info("操作日志参数=====>{}", JSONObject.toJSONString(logDO));
            logMapper.insert(logDO);
        }
        logger.info("系统操作日志=========>end");
        return result;
    }
}
