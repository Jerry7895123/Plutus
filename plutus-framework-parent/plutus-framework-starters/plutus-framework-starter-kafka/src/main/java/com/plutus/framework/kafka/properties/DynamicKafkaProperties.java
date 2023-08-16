package com.plutus.framework.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Jerry
 * @Date: 2023/8/16 16:24
 * @Description: kafka动态配置
 */
@Configuration
@ConfigurationProperties(prefix = DynamicKafkaProperties.PREFIX)
public class DynamicKafkaProperties {

    public static final String PREFIX = "spring.kafka.dynamic";

    @Getter
    @Setter
    private Map<String, KafkaProperties> kafka = new HashMap<>();

    @Getter
    @Setter
    private String primary = "default";

}
