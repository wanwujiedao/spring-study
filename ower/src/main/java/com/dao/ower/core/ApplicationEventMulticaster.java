package com.dao.ower.core;


public interface ApplicationEventMulticaster {
    /**
     * 广播事件
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 杭州微财科技有限公司
     * @param event
     * @return void
     */
    void publishEvent(ApplicationEvent event);
}
