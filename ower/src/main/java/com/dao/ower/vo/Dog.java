package com.dao.ower.vo;


import com.dao.ower.annotation.MyResources;

/**
 * 狗
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.ower.vo.Dog.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-21 09:31:00
 */
public class Dog {

    //试试自动注入
    @MyResources
    private Bone bone;
    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void like(){
        System.out.println("狗狗喜欢"+bone.name());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
