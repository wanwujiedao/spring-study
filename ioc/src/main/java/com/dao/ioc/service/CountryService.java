package com.dao.ioc.service;

import com.dao.ioc.dao.ICountry;
import com.dao.ioc.dao.impl.America;
import com.dao.ioc.dao.impl.Japan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务层模拟
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.di.service.CountryService.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-09 19:46:00
 */
public class CountryService {

    private ICountry china;

    private ICountry japanName;

    private ICountry japanIndex;

    private ICountry japanProperty;

    private ICountry america;

    /**
     * 从配置文件读取
     *
     * @author 阿导
     * @time 2018/3/9
     * @CopyRight 杭州微财科技有限公司
     * @return
     */
    public CountryService() {
        //容器
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ioc.xml");
        //从容器中获得id为bookdao的bean
        china=(ICountry)ctx.getBean("china");
        japanName=ctx.getBean("japan_name", Japan.class);
        japanIndex=ctx.getBean("japan_index", Japan.class);
        japanProperty=ctx.getBean("japan_property", Japan.class);

        //容器
        ApplicationContext ctxComment=new ClassPathXmlApplicationContext("ioc-component.xml");
        //注解注入
        america=ctxComment.getBean("america", America.class);
    }

    /**
     * 获取国家名称
     *
     * @author 阿导
     * @time 2018/3/9
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    public void getCountryName(){
        japanIndex.name();
        china.name();
        japanName.name();
        japanProperty.name();
        america.name();
    }

}
