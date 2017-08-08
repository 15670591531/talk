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
    private static final String APPLICATION_NAME = "talk_platform";//应用名(需要更改)
    private static final String ZOOKEEPER_ADDRESS = "zookeeper://127.0.0.1:2181";//zookeeper地址
    private static final String REGISTRY_USERNAME = "docryze";//注册用户名
    private static final String REGISTRY_PASSWORD = "talk";//注册密码
    private static final String MONITOR_PROTOCOL = "registry";//监控方式
    private static final String SCAN_PACKAGE = "org.docryze.talk.talkplatform";//扫描包路径(需要更改)

    public DubboConfig(){
        System.out.println("\n===========加载配置文件件================");
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(APPLICATION_NAME); //设置应用名 , 统一取该项目名称, 不能有重复
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registry() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(ZOOKEEPER_ADDRESS);
        registryConfig.setUsername(REGISTRY_USERNAME);
        registryConfig.setPassword(REGISTRY_PASSWORD);
        return registryConfig;
    }

    /**
     * 配置dubbo注解扫描包路径
     * @return
     */
    @Bean
    public AnnotationBean annotationBean(){
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(SCAN_PACKAGE);
        return annotationBean;
    }

    /*############################以上为必要配置######################*/

    /*配置监控,从注册中心获取信息*/
    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig mc = new MonitorConfig();
        mc.setProtocol(MONITOR_PROTOCOL);
        return mc;
    }

}
