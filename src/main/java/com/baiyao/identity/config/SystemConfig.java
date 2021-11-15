package com.baiyao.identity.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author baiyao
 * @date 2021/11/12 14:49
 * @description
 */
@Component
@Data
@NacosConfigurationProperties(prefix = "data", autoRefreshed = true, type = ConfigType.YAML, dataId = "system-config.yaml")
public class SystemConfig {

    private String userSession;

    private String userToken;
}
