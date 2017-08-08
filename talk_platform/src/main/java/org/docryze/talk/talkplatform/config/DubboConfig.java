package org.docryze.talk.talkplatform.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.container.Main;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo配置
 *          #可通过注解配置,应用中使用注解来进行服务注册和引用
 *            service           @Service
 *            reference         @Reference
 *
 *
 *         #引用必要配置
 *            application
 *            registry
 *            protocol
 *            annotation
 *
 *        #个性化配置
 *            methed            控制到方法级
 *            argument          控制到参数级别
 *            monitor           监控配置
 *            module
 *            provider
 *            consumer
 *            parameter
 *
 *
 *
 */

@Configuration
@ConditionalOnClass(Main.class)
public class DubboConfig {
    public DubboConfig(){
        System.out.println("\n===========加载配置文件件================");
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("base_provider");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registry() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocol() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(20881);
        return protocolConfig;
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
