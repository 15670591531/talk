package org.docryze.talk.baseprovider.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.container.Main;
import com.alibaba.fastjson.JSONObject;
import org.docryze.talk.talkutil.json.JsonUtil;
import org.docryze.talk.talkutil.zookeeper.ZookeeperUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dubbo配置
 */

@Configuration
@ConditionalOnClass({Main.class})//判断ClassPath中是否存在Dubbo的入口函数
public class DubboConfig {
    private String address = "127.0.0.1:2181";//"127.0.0.1:2181"
    private String registryProtocol = "zookeeper";//"zookeeper"
    private String registryFile = null;//registryFile
    private String applicationName = "BaseProvider";//"BaseProvider"
    private String applicationOwner = "DocRyze_Group";//"DocRyze Group"
    private String monitorProtocol = "registry";//"registry"
    private String protocolName = "BaseProvider_Dubbo";//"BaseProvider Dubbo"
    private Integer protocolPort = 20880;//20880
    private Integer protocolThreads = 100;//100
    private String annotationPackage = "org.docryze.talk";

    /**
     * 从zookeeper获取自定义配置
     */
    public DubboConfig() {
        System.out.println("###################进行dubbo配置#######################");
        String config = ZookeeperUtil.QueryFromZookeeper("dubboConfig_baseProvider");
        JSONObject object = JsonUtil.paser(config);
        if (object != null) {
            address = object.getString("address") == null ? address : object.getString("address");
            registryProtocol = object.getString("registryProtocol") == null ? registryProtocol : object.getString("registryProtocol");
            registryFile = object.getString("registryFile");
            applicationName = object.getString("applicationName") == null ? applicationName : object.getString("applicationName");
            applicationOwner = object.getString("applicationOwner") == null ? applicationOwner : object.getString("applicationOwner");
            monitorProtocol = object.getString("monitorProtocol") == null ? monitorProtocol : object.getString("monitorProtocol");
            protocolName = object.getString("protocolName") == null ? protocolName : object.getString("protocolName");
            protocolPort = object.getInteger("protocolPort") == null ? protocolPort : object.getInteger("protocolPort");
            protocolThreads = object.getInteger("protocolThreads") == null ? protocolThreads : object.getInteger("protocolThreads");
            annotationPackage = object.getString("annotationPackage") == null ? annotationPackage : object.getString("annotationPackage");
        }
    }

    /**
     * 连接注册中心配置
     *
     * @return
     */
    @Bean
    public RegistryConfig registry() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(address);
        registryConfig.setProtocol(registryProtocol);
        if (registryFile != null) {
            registryConfig.setFile(registryFile);
        }
        return registryConfig;
    }

    /**
     * 注解配置
     *
     * @return
     */
    public AnnotationBean annotation() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(annotationPackage);
        return annotationBean;
    }

    /**
     * 当前应用配置
     *
     * @return
     */
    @Bean
    public ApplicationConfig application() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        applicationConfig.setOwner(applicationOwner);
        return applicationConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig mc = new MonitorConfig();
        mc.setProtocol(monitorProtocol);
        return mc;
    }

    @Bean
    public ReferenceConfig referenceConfig() {
        ReferenceConfig rc = new ReferenceConfig();
        rc.setMonitor(monitorConfig());
        return rc;
    }

    /**
     * 服务提供者协议配置
     *
     * @return
     */
    @Bean
    public ProtocolConfig protocol() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        protocolConfig.setThreads(protocolThreads);
        return protocolConfig;
    }

    @Bean
    public ProviderConfig provider() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setMonitor(monitorConfig());
        return providerConfig;
    }
}
