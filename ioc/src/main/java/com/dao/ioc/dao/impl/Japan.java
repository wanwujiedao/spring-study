package com.dao.ioc.dao.impl;

import com.dao.ioc.dao.ICountry;

/**
 * 日本
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.di.dao.impl.Japan.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-09 19:56:00
 */
public class Japan implements ICountry {

    /**
     * 名字
     */
    private String name;

    /**
     * 区域
     */
    private String area;
    /**
     * 构造方法
     *
     * @author 阿导
     * @time 2018/3/9
     * @CopyRight 万物皆导
     * @param name
     * @param area
     * @return
     */
    public Japan(String name, String area) {

        this.name = name;
        this.area = area;
    }

    public Japan() {
    }

    /**
     * 名称
     *
     * @author 阿导
     * @time 2018/3/9
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    public void name() {
        System.out.println(name);
    }
    /**
     * 所在区域
     *
     * @author 阿导
     * @time 2018/3/9
     * @CopyRight 杭州微财科技有限公司
     * @param
     * @return void
     */
    public void area(){
        System.out.println(area);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
