package com.dao.ioc.dao.impl;

import com.dao.ioc.dao.ICountry;

/**
 * 中国
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.di.interfaces.impl.China.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-09 19:39:00
 */
public class China implements ICountry{

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
        System.out.println("中国");
    }
    /**
     * 区域
     *
     * @author 阿导
     * @time 2018/3/9
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    public void area() {
        System.out.print("亚洲");
    }
}
