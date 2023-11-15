package com.plutus.framework.rabbitmq.properties;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Jerry
 * @Date: 2023/8/29 17:47
 */
@ConfigurationProperties(prefix = DynamicRabbitProperties.PREFIX)
public class DynamicRabbitProperties {

    public static final String PREFIX = "spring.rabbitmq.dynamic";

    private Map<String, RabbitProperties> rabbitmq = new HashMap<>();

    private String primary = "default";

    public Map<String, RabbitProperties> getRabbitmq() {
        return rabbitmq;
    }

    public void setRabbitmq(Map<String, RabbitProperties> rabbitmq) {
        this.rabbitmq = rabbitmq;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
}
