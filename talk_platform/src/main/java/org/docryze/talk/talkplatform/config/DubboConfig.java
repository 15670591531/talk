package org.docryze.talk.talkplatform.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.container.Main;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Main.class)
public class DubboConfig {
    public DubboConfig(){
        System.out.println("\n===========加载配置文件件================");
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("talk_platform"); //设置应用名 , 统一取该项目名称, 不能有重复
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registry() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setUsername("docryze");
        registryConfig.setPassword("talk");
        return registryConfig;
    }

    /**
     * 配置dubbo注解扫描包路径
     * @return
     */
    @Bean
    public AnnotationBean annotationBean(){
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage("org.docryze.talk.talkplatform");
        return annotationBean;
    }

    /*############################以上为必要配置######################*/

    /*配置监控,从注册中心获取信息*/
    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig mc = new MonitorConfig();
        mc.setProtocol("registry");
        return mc;
    }

}
