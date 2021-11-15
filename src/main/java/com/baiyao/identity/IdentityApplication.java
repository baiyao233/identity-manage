package com.baiyao.identity;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySources(value = {@NacosPropertySource(dataId = "system-config.yaml", autoRefreshed = true),
        @NacosPropertySource(dataId = "example", autoRefreshed = true)
})
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "baiyao:8848", namespace = "53f890e7-3291-46c4-93c9-df2c100c1c9c"))
@MapperScan("com.baiyao.identity.mapper")
public class IdentityApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

}
