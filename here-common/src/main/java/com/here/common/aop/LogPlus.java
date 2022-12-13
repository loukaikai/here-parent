package com.here.common.aop;

import java.lang.annotation.*;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName LogPlus.java
 * @Description 被该注解修饰的类，会记录从表的id值和从表的表名(用于记录某张表的一行记录，历史修改信息，不需要可忽略)
 * @createTime 2022年12月12日 22:53:00
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogPlus {
    /**
     * 编辑的表主键
     * @return
     */
    String editTableId() default "id";

    /**
     * 编辑的表名称
     * @return
     */
    String editTableName() default "未知";
}
