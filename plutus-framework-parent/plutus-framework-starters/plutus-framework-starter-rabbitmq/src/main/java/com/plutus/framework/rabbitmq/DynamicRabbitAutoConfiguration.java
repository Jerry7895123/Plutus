package com.plutus.framework.rabbitmq;

import com.plutus.framework.rabbitmq.properties.DynamicRabbitProperties;
import com.plutus.framework.rabbitmq.registrar.DynamicRabbitConfigurationRegistrar;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @Auther: Jerry
 * @Date: 2023/8/29 17:27
 */
@ConditionalOnClass(RabbitTemplate.class)
@ConditionalOnProperty(name = "spring.rabbitmq.dynamic.enabled", matchIfMissing = true)
@EnableConfigurationProperties(DynamicRabbitProperties.class)
@Import(DynamicRabbitConfigurationRegistrar.class)
public class DynamicRabbitAutoConfiguration {

}
