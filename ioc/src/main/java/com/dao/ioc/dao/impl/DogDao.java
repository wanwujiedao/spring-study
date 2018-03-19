package com.dao.ioc.dao.impl;

import com.dao.ioc.dao.IAnimalsDao;
import org.springframework.stereotype.Repository;

/**
 * 狗
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.di.dao.impl.DogDao.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-12 14:03:00
 */
@Repository
public class DogDao implements IAnimalsDao{

    /**
     * 接口层实现：获取动物名称
     *
     * @author 阿导
     * @time 2018/3/12
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    public void name() {
        System.out.println("狗狗");
    }
}
