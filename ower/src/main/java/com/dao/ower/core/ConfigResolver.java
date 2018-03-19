package com.dao.ower.core;

import com.dao.ower.annotation.Resources;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;

public class ConfigResolver extends ApplicationEvent implements ApplicationEventMulticaster {
    //spring 默认配置
    private String configXml = "spring.xml";
    //这里就是模仿beanFactory 将所有的bean用beanid与对应实例用map保存起来
    static HashMap<String, Object> BeanFactory;
    //这里保存那些是监听者的bean
    static HashMap<String, ApplicationListener> RegistryListener;

    //静态常量初始化
    static {
        BeanFactory = new HashMap<>();
        RegistryListener = new HashMap<>();
    }

    /**
     * 构造函数，含有配置文件
     *
     * @param config
     * @return
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    public ConfigResolver(String config) {
        //默认就是spring.xml
        this.configXml = config == null ? configXml : config;
        setConfigLocations(this.configXml);
        refresh();
    }

    /**
     * 寻找默认配置文件
     *
     * @param
     * @return
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    public ConfigResolver() {
        setConfigLocations(this.configXml);
        refresh();
    }

    /**
     * 获取 bean
     *
     * @param beanId
     * @return java.lang.Object
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    public Object getBean(String beanId) {
        return BeanFactory.get(beanId);
    }

    /**
     * 做一些前置处理，比如校验 xml 的合法性，这个一般配合 DTD 去做校验，这里偷个懒
     *
     * @param configXml
     * @return void
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    private void setConfigLocations(String configXml) {

    }

    /**
     * 刷新
     *
     * @param
     * @return void
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    private void refresh() {
        //注册bean
        invokeBeanFactoryPostProcessors(BeanFactory);
        //登记监听者
        registerListeners();
        //结束刷新，表面程序已经启动，可以广播这个刷新完毕事件了，广播事件
        finishRefresh();
    }

    /**
     * 结束刷新，广播事件
     *
     * @param
     * @return void
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    private void finishRefresh() {
        publishEvent(this);
    }

    /**
     * 从 beanfactory 找到那些是监听者类型的 bean
     *
     * @param
     * @return void
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    private void registerListeners() {
        //迭代器获取 bean
        Iterator<String> it = BeanFactory.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            //判断获取的 bean 是不是 ApplicationListener 的子类或者衍生类，若是则放入监听者类型里面，并从 bean 工厂移除
            if (BeanFactory.get(key) instanceof ApplicationListener) {
                RegistryListener.put(key, (ApplicationListener) BeanFactory.get(key));
                it.remove();
            }
        }
    }


    /**
     * 将配置文件中的 bean 全部实例化到工厂 map 里面
     *
     * @param beanFactory
     * @return void
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     */
    private void invokeBeanFactoryPostProcessors(HashMap beanFactory) {
        //声明输入流
        InputStream in = null;
        try {
            //兼容资源路径 与 绝对路径
            in = ConfigResolver.class.getResourceAsStream(this.configXml) == null ? new FileInputStream(this.configXml) : ConfigResolver.class.getResourceAsStream(this.configXml);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //开始解析 xml 文件
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //通过 sax 方式解析 xml，也可以使用 dom4j
            Document dc = db.parse(in);
            //读取节点 bean
            NodeList nl = dc.getElementsByTagName("bean");
            //遍历节点
            for (int i = 0; i < nl.getLength(); i++) {
                //获取节点属性
                NamedNodeMap attrs = nl.item(i).getAttributes();
                //对应一个 bean 标签
                HashMap<String, String> beanMap = new HashMap<>();
                for (int j = 0; j < attrs.getLength(); j++) {
                    String beanNodeName = attrs.item(j).getNodeName();
                    String beanNodeValue = null;
                    if (beanNodeName != null) {
                        beanNodeValue = attrs.item(j).getNodeValue();
                    }
                    if (beanNodeValue != null) {
                        beanMap.put(beanNodeName, beanNodeValue);
                    }
                }
                String beanId = beanMap.get("id");
                String beanClass = beanMap.get("class");
                if (beanClass == null || beanId == null) {
                    continue;
                }
                try {
                    Class cls = Class.forName(beanClass);
                    Object beanObject = cls.newInstance();
                    Field[] fds = beanObject.getClass().getDeclaredFields();
                    for (Field fd : fds) {
                        fd.setAccessible(true);//获取访问私有变量权限
                        Resources rs = fd.getAnnotation(Resources.class);
                        if (rs != null) {
                            fd.set(beanObject, fd.getType().newInstance());//实例化带有Resource注解的成员
                        }
                    }
                    beanFactory.put(beanId, beanObject);//将bean放到map
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播事件
     *
     * @param event
     * @return void
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 杭州微财科技有限公司
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
        Iterator<String> it = RegistryListener.keySet().iterator();
        while (it.hasNext()) {
            RegistryListener.get(it.next()).onApplicationEvent(event);
        }
    }
}
