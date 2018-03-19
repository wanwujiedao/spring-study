package com.dao.aop.proxy;

import org.springframework.stereotype.Component;

/**
 * 动物
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.aop.proxy.Animals.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-19 09:41:00
 */
@Component("animals")
public class Animals {


    /**
     * 叫什么名字？
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 动物名称
     * @param name
     * @return void
     */
    public void name(String name){
        System.out.println("你好！我是".concat(name));
    }
    /**
     * 吃什么东西？
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 动物名称
     * @param food
     * @return void
     */
    public void eat(String food){
        System.out.println("我喜欢吃".concat(food));
    }

    /**
     * 传入的种类数
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     * @param objs
     * @return java.lang.Integer
     */
    public Integer count(Object... objs){
        for(Object obj:objs){
            System.out.println(obj);
        }
        return objs.length;
    }
}
