package my.sample;

import my.sample.domain.service.MultiThreadService;
import my.sample.domain.service.MultiThreadServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductSampleRun {

    public static void main(String[] args) {
        ProductSampleRun productSampleRun = new ProductSampleRun();
        productSampleRun.execute();
    }

    public void execute() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        MultiThreadService multiThreadService = ctx.getBean(MultiThreadService.class);

        multiThreadService.execute();

    }
}