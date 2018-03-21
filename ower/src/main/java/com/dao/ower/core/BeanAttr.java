package com.dao.ower.core;

import java.io.Serializable;
import java.util.Comparator;

/**
 * bean 属性
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.ower.core.BeanAttr.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-20 11:09:00
 */
public class BeanAttr implements Serializable,Comparable<BeanAttr> {

    private static final long serialVersionUID = 2068992453001676968L;

    /**
     * 属性的标签
     */
    private String tar;

    /**
     * 属性名称
     */
    private String name;
    /**
     * 属性顺序
     */
    private Integer index;
    /**
     * 属性值
     */
    private Object value;
    /**
     * 属性指向的 bean
     */
    private String ref;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTar() {
        return tar;
    }

    public void setTar(String tar) {
        this.tar = tar;
    }

    //按照 index 升序
    @Override
    public int compareTo(BeanAttr o) {
        return this.getIndex()-o.getIndex();
    }
}
