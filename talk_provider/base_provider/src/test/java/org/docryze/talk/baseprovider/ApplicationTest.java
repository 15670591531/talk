package org.docryze.talk.baseprovider;

import org.junit.runner.RunWith;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Application
 * 访问地址 http://localhost:8080
 */
//@Configuration/**/
//@EnableAutoConfiguration/*自动配置    exclude={ 不自动配置的类,...}   */
//@ComponentScan/*扫描该路径下的所有组件*/
/*
 *  @SpringBootApplication   相当于上面三个注释
 *
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {/*自定义退出码*/
        return () -> 42;
    }/*自定义退出码*/

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApplicationTest.class);
        //添加监听器,应用环境构建完成时,启动dubbo
//        ApplicationListener<ApplicationEvent> listener = new ApplicationListener<ApplicationEvent>() {
//            @Override
//            public void onApplicationEvent(ApplicationEvent event) {
//                if (ApplicationReadyEvent.class.equals(event.getClass())) {
//                    com.alibaba.dubbo.container.Main.main(args);
//                }
//            }
//        };

        /*Lambda*/
        ApplicationListener<ApplicationEvent> listener = (ApplicationEvent event)->{
            if (ApplicationReadyEvent.class.equals(event.getClass())) {
                    com.alibaba.dubbo.container.Main.main(args);
                }
        };

        springApplication.addListeners(listener);
        springApplication.run(args);
    }
}
