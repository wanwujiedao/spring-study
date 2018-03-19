package com.dao.ioc.service;

import com.dao.ioc.dao.IAnimalsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 动物服务层
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.di.service.AnimalsService.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-12 14:05:00
 */
@Service
public class AnimalsService {

    /**
     * 数据访问层自动装配
     */
    @Autowired
    private IAnimalsDao animalsDao;

    /**
     * 获取动物名称
     *
     * @author 阿导
     * @time 2018/3/12
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    public void getAnimalsName() {
        animalsDao.name();
    }
}
