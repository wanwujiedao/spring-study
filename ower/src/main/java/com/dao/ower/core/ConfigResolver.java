package com.dao.ower.core;

import com.dao.ower.annotation.MyResources;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.*;
import java.util.*;

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
     * 强转
     *
     * @author 阿导
     * @time 2018/3/21
     * @CopyRight 杭州微财科技有限公司
     * @param beanId
     * @param clazz
     * @return T
     */
    public <T> T getBean(String beanId,Class<? extends T> clazz) {
        return (T)BeanFactory.get(beanId);
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
                //是否有子节点，判断其构造函数
                NodeList childNodes = nl.item(i).getChildNodes();
                //对应一个 bean 标签
                HashMap<String, String> beanMap = new HashMap<>();
                //属性处理
                List<BeanAttr> beanAttrs=new ArrayList<>();
                //处理属性
                dealAttr(attrs,beanMap);

                //获取 bean 的 id
                String beanId = beanMap.getOrDefault("id",null);
                //获取 bean 的 类
                String beanClass = beanMap.getOrDefault("class",null);

                //处理子节点属性
                if(childNodes.getLength()!=0){
                    for(int pox=0;pox<childNodes.getLength();pox++){
                        Node item = childNodes.item(pox);
                        if(item!=null&&!"#text".equals(item.getNodeName())) {
                            //处理属性
                            beanAttrs.add(dealAttr(item));
                        }
                    }
                }



                if (beanClass == null || beanId == null) {
                    continue;
                }
                try {
                    //获取类
                    Class cls = Class.forName(beanClass);
                    Object beanObject =null;

                    if(beanAttrs.isEmpty()){
                        //若不包含参数
                        beanObject=cls.newInstance();
                    }else{
                        //对 index 进行升序
                        Collections.sort(beanAttrs);
                        //若包含参数(暂时不写，回头补上)
                        Constructor<?>[] constructors = cls.getConstructors();
                        for(Constructor constructor:constructors) {
                            //通过遍历匹配构造方法
                            if(beanAttrs.size()==constructor.getParameterCount()){
                                //这边处理比较麻烦，暂时留着，要匹配类型，名称或者参数位置等，这里目前先处理 index
                                Class[] parameterTypes = constructor.getParameterTypes();
                                Object[] args=new Object[beanAttrs.size()];
                                int index=0;
                                for(BeanAttr beanAttr:beanAttrs) {

                                    //获取当前的参数类型
                                    Class parameterType = parameterTypes[index];

                                    //若是下标处理参数，在 BeanAttr 已经按照 index 升序了
                                    if(beanAttr.getIndex()!=null) {
                                        //若是字符型，可能还有其他的这边先不处理
                                        if ("java.lang.String".equals(parameterType.getName())) {
                                            args[index++] = parameterType.getDeclaredMethod("valueOf", Object.class).invoke(null, beanAttr.getValue());
                                        } else {
                                            //其他情况，转型
                                            args[index++] = parameterType.getDeclaredMethod("valueOf", String.class).invoke(null, beanAttr.getValue());
                                        }
                                    }else{
                                        //spring是直接读取class文件来读取参数名,这边就先不做了，听说jdk1.8以上可以获取参数名称，但是我试了，还是无异议的arg0,arg1,这边就不写了，有兴趣的伙伴可以挑战下，嘿嘿
                                        Parameter[] parameters = constructor.getParameters();
                                        for(Parameter parameter:parameters){
                                            parameter.getName();
                                        }
                                        //若是字符型，可能还有其他的这边先不处理
                                        if ("java.lang.String".equals(parameterType.getName())) {
                                            args[index++] = parameterType.getDeclaredMethod("valueOf", Object.class).invoke(null, beanAttr.getValue());
                                        } else {
                                            //其他情况，转型
                                            args[index++] = parameterType.getDeclaredMethod("valueOf", String.class).invoke(null, beanAttr.getValue());
                                        }
                                    }
                                }
                                beanObject = constructor.newInstance(args);
                            }
                        }

                    }


                    //属性
                    Field[] fds = beanObject.getClass().getDeclaredFields();
                    for (Field fd : fds) {
                        fd.setAccessible(true);//获取访问私有变量权限
                        MyResources rs = fd.getAnnotation(MyResources.class);
                        if (rs != null) {
                            //实例化带有Resource注解的成员
                            fd.set(beanObject, fd.getType().newInstance());
                        }
                    }
                    //然后放到 bean 工厂里面
                    beanFactory.put(beanId, beanObject);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理 bean 属性
     * @author 阿导
     * @time 2018/3/20
     * @CopyRight 杭州微财科技有限公司
     * @param item
     * @return java.util.List<com.dao.ower.core.BeanAttr>
     */
    private BeanAttr dealAttr(Node item) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        //声明结果
        Class<BeanAttr> c=BeanAttr.class;
        //实例化对象
        Object obj=c.newInstance();
            //标签
            //获取字段
            Field field= c.getDeclaredField("tar");
            //获取访问私有变量权限
            field.setAccessible(true);
            //为字段赋值
            field.set(obj, item.getNodeName());
            //获取字标签的属性
            NamedNodeMap childAttrs = item.getAttributes();
            //若属性不为空
            if (childAttrs.getLength() != 0) {
                for (int pox = 0; pox < childAttrs.getLength(); pox++) {
                    //节点属性名称
                    String nodeName = childAttrs.item(pox).getNodeName().trim();
                    //节点属性值
                    String nodeValue = childAttrs.item(pox).getNodeValue().trim();
                    if(nodeName!=null&&!"".equals(nodeName)&&nodeValue!=null&&!"".equals(nodeValue)) {
                        //获取字段
                        Field fieldOther = c.getDeclaredField(nodeName);
                        //获取访问私有变量权限
                        fieldOther.setAccessible(true);
                        //为字段赋值
                        if("index".equals(nodeName)){
                            fieldOther.set(obj, Integer.valueOf(nodeValue));
                        }else {
                            fieldOther.set(obj, nodeValue);
                        }
                    }
                }
            }


        return (BeanAttr) obj;

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

    /**
     * 获取属性节点
     *
     * @author 阿导
     * @time 2018/3/20
     * @CopyRight 杭州微财科技有限公司
     * @param attrs
     * @param map
     * @return void
     */
    private void dealAttr(NamedNodeMap attrs,Map<String, String> map){
        if(attrs.getLength()==0){
            return;
        }
        for (int j = 0; j < attrs.getLength(); j++) {
            String nodeName = attrs.item(j).getNodeName();
            String nodeValue = null;
            if (nodeName != null) {
                nodeValue = attrs.item(j).getNodeValue();
            }
            if (nodeValue != null) {
                map.put(nodeName, nodeValue);
            }
        }
    }
}
