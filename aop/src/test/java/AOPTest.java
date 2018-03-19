import com.dao.aop.proxy.Animals;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 切面编程测试类
 *
 * @author 阿导
 * @version 1.0
 * @fileName PACKAGE_NAME.AOPTest.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-19 09:58:00
 */
public class AOPTest {

    @Test
    public void test(){
        testAop();
        testAopComponent();
    }

    /**
     * 配置 切面测试
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    public void testAop(){
        //读取配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop.xml");
        // 转换 bean
        Animals animals=ctx.getBean("animals",Animals.class);

        //执行方法
        animals.name("小狗");
        animals.eat("骨头");
        animals.count("草狗","牙狗","狼狗","藏獒");
    }
    /**
     * 自动注入切面测试
     *
     * @author 阿导
     * @time 2018/3/19
     * @CopyRight 万物皆导
     * @param
     * @return void
     */
    public void testAopComponent(){
        //读取配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("aop-component.xml");
        // 转换 bean
        Animals animals=ctx.getBean("animals",Animals.class);

        //执行方法
        animals.name("小狗");
        animals.eat("骨头");
        animals.count("草狗","牙狗","狼狗","藏獒");
    }
}
