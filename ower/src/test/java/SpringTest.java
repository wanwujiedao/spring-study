import com.dao.ower.core.ConfigResolver;
import com.dao.ower.vo.Blind;
import org.junit.Test;

/**
 * 测试类
 *
 * @author 阿导
 * @version 1.0
 * @fileName PACKAGE_NAME.SpringTest.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-19 18:15:00
 */
public class SpringTest {

    @Test
    public void test(){
       ConfigResolver cfg=new ConfigResolver("spring.xml");
       Blind b= (Blind) cfg.getBean("bind");
       System.out.println("tell me how is the world :"+b.getEge().see());
    }

}
