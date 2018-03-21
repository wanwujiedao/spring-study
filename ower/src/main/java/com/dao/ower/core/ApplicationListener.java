package com.dao.ower.core;
/**
 * 监听者，进行事件订阅
 *
 * @author 阿导
 * @version 1.0
 * @fileName com.dao.ower.core.ApplicationListener.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-19 18:15:00
 */
public interface ApplicationListener<T extends ApplicationEvent> {

    /**
     * 事件
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 杭州微财科技有限公司
     * @param event
     * @return void
     */
    void onApplicationEvent(T event);
}
