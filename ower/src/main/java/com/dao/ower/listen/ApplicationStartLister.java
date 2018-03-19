package com.dao.ower.listen;

import com.dao.ower.core.ApplicationEvent;
import com.dao.ower.core.ApplicationListener;

public class ApplicationStartLister implements ApplicationListener<ApplicationEvent> {
    /**
     * 启动监听者
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 杭州微财科技有限公司
     * @param event
     * @return void
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("Spring  has been started！");
    }
}
