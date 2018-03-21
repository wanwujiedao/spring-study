import com.alibaba.fastjson.JSON;
import com.dao.ower.core.ConfigResolver;
import com.dao.ower.vo.Dog;
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
    public void test() {

        ConfigResolver cfg = new ConfigResolver("E:\\springstudy\\ower\\src\\main\\resources\\spring.xml");
        Dog dog = cfg.getBean("dog", Dog.class);
        Dog dogOne = cfg.getBean("dog_one", Dog.class);
        Dog dogTwo = cfg.getBean("dog_two", Dog.class);
        dog.like();
        System.out.println(JSON.toJSONString(dogOne));
        System.out.println(JSON.toJSONString(dogTwo));
    }

}
