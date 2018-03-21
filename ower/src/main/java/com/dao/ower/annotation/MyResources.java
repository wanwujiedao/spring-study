package com.dao.ower.annotation;

import java.lang.annotation.*;
/**
 * 测试类
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.ower.annotation.Resources.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-19 18:15:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
//为了书写简单 这里只作用于属性 也就是域 成员变量
@Target(ElementType.FIELD)
public @interface MyResources {
}
