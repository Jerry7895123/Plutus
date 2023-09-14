package com.plutus.framework.kafka;

import com.plutus.framework.kafka.properties.DynamicKafkaProperties;
import com.plutus.framework.kafka.registrar.DynamicKafkaConfigurationRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @Auther: Jerry
 * @Date: 2023/8/16 16:07
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(KafkaTemplate.class)
@ConditionalOnProperty(name = "spring.kafka.dynamic.enabled", matchIfMissing = true)
@EnableConfigurationProperties(DynamicKafkaProperties.class)
@Import(DynamicKafkaConfigurationRegistrar.class)
public class DynamicKafkaAutoConfiguration {

}
