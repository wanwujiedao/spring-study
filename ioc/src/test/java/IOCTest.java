import com.dao.ioc.Config;
import com.dao.ioc.service.AnimalsService;
import com.dao.ioc.service.CountryService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 启动测试类
 *
 * @author 阿导
 * @version 1.0
 * @fileName Main.java
 * @CopyRright (c) 2018-万物皆导
 * @created 2018-03-09 19:42:00
 */
public class IOCTest {

    @Test
    public void testGetCountryName()
    {
        xmlDITest();
        codeDITest();
    }

    public void xmlDITest(){
        CountryService countryService=new CountryService();
        countryService.getCountryName();
    }

    /**
     * 服务层
     */
    private AnimalsService animalsService;

    public void codeDITest(){
        //容器
        ApplicationContext ctx=new AnnotationConfigApplicationContext(Config.class);
        //这里装配的bean 要和 @Bean 保持一致
        animalsService= (AnimalsService) ctx.getBean("animalsService");
        animalsService.getAnimalsName();
    }
}
