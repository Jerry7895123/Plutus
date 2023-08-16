package com.plutus.framework.kafka;

import com.plutus.framework.kafka.registrar.KafkaConfigurationRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListenerConfigurationSelector;

/**
 * @Auther: Jerry
 * @Date: 2023/8/16 16:07
 */
@Configuration
@ConditionalOnBean(KafkaListenerConfigurationSelector.class)
@Import({KafkaConfigurationRegistrar.class})
public class KafkaAutoConfiguration {



}
