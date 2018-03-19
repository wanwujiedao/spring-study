package com.dao.ioc;

import com.dao.ioc.service.AnimalsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 容器配置
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.di.config.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-12 14:11:00
 */
@Configuration
@ComponentScan(basePackages="com.dao")
public class Config {

    @Bean
    public AnimalsService animalsService(){
        return new AnimalsService();
    }
}
