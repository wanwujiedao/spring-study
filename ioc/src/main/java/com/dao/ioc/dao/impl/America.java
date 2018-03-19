package com.dao.ioc.dao.impl;

import com.dao.ioc.dao.ICountry;
import org.springframework.stereotype.Component;

/**
 * 美国[使用注解注入]
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.di.dao.impl.America.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-09 20:41:00
 */
@Component("america")
public class America implements ICountry{

    /**
     * 名字
     */
    private String name;

    /**
     * 区域
     */
    private String area;
    public void name() {
        System.out.println("美国");
    }

    public void area() {
        System.out.println("美洲");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
