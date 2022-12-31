package com.here.common.exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName JsonFormatVaild.java
 * @Description TODO
 * @createTime 2022年12月27日 21:22:00
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormatVaild {
    String message() default "";
}
