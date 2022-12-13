package com.here.common.aop;

import java.lang.annotation.*;

/** 被该注解修饰的方法，会被记录到日志中 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
}
