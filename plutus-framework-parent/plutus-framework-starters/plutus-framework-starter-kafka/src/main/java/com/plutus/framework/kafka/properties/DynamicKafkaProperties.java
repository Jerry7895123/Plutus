package com.plutus.framework.kafka.properties;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Jerry
 * @Date: 2023/8/16 16:24
 * @Description: kafka动态配置
 */
@ConfigurationProperties(prefix = DynamicKafkaProperties.PREFIX)
public class DynamicKafkaProperties {

    public static final String PREFIX = "spring.kafka.dynamic";

    private Map<String, KafkaProperties> kafka = new HashMap<>();

    private String primary = "default";

    public Map<String, KafkaProperties> getKafka() {
        return kafka;
    }

    public void setKafka(Map<String, KafkaProperties> kafka) {
        this.kafka = kafka;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
}
