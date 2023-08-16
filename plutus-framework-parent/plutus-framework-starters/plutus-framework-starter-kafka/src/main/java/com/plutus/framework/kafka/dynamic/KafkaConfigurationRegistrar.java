package com.plutus.framework.kafka.dynamic;

import com.plutus.framework.kafka.properties.DynamicKafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

/**
 * @Auther: lijian
 * @Date: 2023/8/16 17:32
 * @Description: kafka配置注册
 */
public class KafkaConfigurationRegistrar implements ImportBeanDefinitionRegistrar {

    @Autowired
    private DynamicKafkaProperties dynamicKafkaProperties;

    /**
     * 注册kafka配置和实例
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String primary = dynamicKafkaProperties.getPrimary();
        for (Map.Entry<String, KafkaProperties> entry : dynamicKafkaProperties.getKafka().entrySet()) {
            String key = entry.getKey();
            KafkaProperties kafkaProperties = entry.getValue();

            KafkaTemplate kafkaTemplate = new KafkaTemplate<>(producerFactory(kafkaProperties));

            ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory(kafkaProperties));

            RootBeanDefinition propertiesBeanDefinition = new RootBeanDefinition(KafkaProperties.class, () -> kafkaProperties);
            RootBeanDefinition templateBeanDefinition = new RootBeanDefinition(KafkaTemplate.class, () -> kafkaTemplate);
            RootBeanDefinition factoryBeanDefinition = new RootBeanDefinition(ConcurrentKafkaListenerContainerFactory.class, () -> factory);

            if (key.equals(primary)) {
                propertiesBeanDefinition.setPrimary(true);
                templateBeanDefinition.setPrimary(true);
                factoryBeanDefinition.setPrimary(true);
            }
            registry.registerBeanDefinition(key + "KafkaProperties", propertiesBeanDefinition);
            registry.registerBeanDefinition(key + "KafkaTemplate", templateBeanDefinition);
            registry.registerBeanDefinition(key + "KafkaListenerContainerFactory", factoryBeanDefinition);
        }
    }

    /**
     * kafka消费者工厂
     */
    private ConsumerFactory consumerFactory(KafkaProperties firstKafkaProperties) {
        return new DefaultKafkaConsumerFactory<>(firstKafkaProperties.buildConsumerProperties());
    }

    /**
     * kafka生产者工厂
     */
    private DefaultKafkaProducerFactory producerFactory(KafkaProperties firstKafkaProperties) {
        return new DefaultKafkaProducerFactory<>(firstKafkaProperties.buildProducerProperties());
    }

}
