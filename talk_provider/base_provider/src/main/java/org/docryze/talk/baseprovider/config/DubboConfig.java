package org.docryze.talk.baseprovider.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.container.Main;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo配置
 *      以下两个配置需要随模块名来更改对应配置
 *          APPLICATION_NAME
 *          SCAN_PACKAGE
 *
 *      以下一个配置需要根据zookeeper服务地址更改
 *          ZOOKEEPER_ADDRESS
 */

@Configuration
@ConditionalOnClass(Main.class)
public class DubboConfig {
    private static final String APPLICATION_NAME = "base_provider";//应用名(需要更改)
    private static final String ZOOKEEPER_ADDRESS = "zookeeper://127.0.0.1:2181";//zookeeper地址
    private static final String REGISTRY_PROTOCOL = "zookeeper";//注册协议
    private static final String REGISTRY_USERNAME = "docryze";//注册用户名
    private static final String REGISTRY_PASSWORD = "talk";//注册密码
    private static final Integer PROTOCOL_PORT = 20880;//根据情况修改为zookeeper地址
    private static final Integer THREADS = 200;//线程数
    private static final String MONITOR_PROTOCOL = "registry";//监控方式
    private static final String SCAN_PACKAGE = "org.docryze.talk.baseprovider";//扫描包路径(需要更改)

    public DubboConfig(){
        System.out.println("\n===========加载配置文件件================");
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(APPLICATION_NAME);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registry() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(ZOOKEEPER_ADDRESS);//根据情况修改为zookeeper地址
        registryConfig.setProtocol(REGISTRY_PROTOCOL);
        registryConfig.setUsername(REGISTRY_USERNAME);
        registryConfig.setPassword(REGISTRY_PASSWORD);
        registryConfig.setRegister(true);//是否暴露服务
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocol() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(PROTOCOL_PORT);
        protocolConfig.setThreads(THREADS);
        return protocolConfig;
    }

    /**
     * 配置dubbo注解扫描路径
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
