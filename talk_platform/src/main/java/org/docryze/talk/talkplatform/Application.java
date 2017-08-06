package org.docryze.talk.talkplatform;

import org.springframework.boot.Banner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller Demo
 */
@Configuration/**/
@EnableAutoConfiguration/*自动配置    exclude={ 不自动配置的类,...}   */
@ComponentScan/*扫描该路径下的所有组件*/
/*
 *  @SpringBootApplication   相当于上面三个注释
 *
 * */
public class Application {
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {/*自定义退出码*/
        return () -> 42;
    }/*自定义退出码*/

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
//        Map<String,Object> map = new HashMap<>();
//        map.put("user","dongkang");
//        app.setDefaultProperties(map);
//        app.setWebEnvironment(true);/*开启web应用环境*/
//        app.setBannerMode(Banner.Mode.OFF);/*关闭横幅打印*/

//        app.addListeners();/*添加监听器*/
//        app.run(args);
        new SpringApplication(Application.class).run(args);
    }
}
