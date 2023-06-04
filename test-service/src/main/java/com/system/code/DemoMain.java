package com.system.code;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

public class DemoMain {
    public static void main(String[] args) throws NacosException {
        // nacos address
        String serverAddr = "localhost:8848";
        // nacos id
        String dataId = "test.yaml";
        // nacos group
        String group = "DEFAULT_GROUP";

        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println("content = " + content);
    }
}
