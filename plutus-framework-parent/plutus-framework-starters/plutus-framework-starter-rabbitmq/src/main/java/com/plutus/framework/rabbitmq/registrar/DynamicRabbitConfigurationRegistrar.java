package com.plutus.framework.rabbitmq.registrar;

import com.plutus.framework.rabbitmq.properties.DynamicRabbitProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.amqp.CachingConnectionFactoryConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionFactoryBeanConfigurer;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @Auther: Jerry
 * @Date: 2023/8/29 17:51
 */
public class DynamicRabbitConfigurationRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private DynamicRabbitProperties dynamicRabbitProperties;

    @Override
    public void setEnvironment(Environment environment) {
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        Binder binder = new Binder(sources);
        BindResult<DynamicRabbitProperties> bindResult = binder.bind(DynamicRabbitProperties.PREFIX, DynamicRabbitProperties.class);
        this.dynamicRabbitProperties = bindResult.get();
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String primary = dynamicRabbitProperties.getPrimary();
        for (Map.Entry<String, RabbitProperties> entry : dynamicRabbitProperties.getRabbitmq().entrySet()) {




        }
    }

    private ConnectionFactory connectionFactory(RabbitProperties rabbitProperties) throws Exception {
        RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
        factory.setHost(rabbitProperties.getHost());
        factory.setPort(rabbitProperties.getPort());
        factory.setUsername(rabbitProperties.getUsername());
        factory.setPassword(rabbitProperties.getPassword());
        factory.setVirtualHost(rabbitProperties.getVirtualHost());
        if (rabbitProperties.getConnectionTimeout() != null) {
            factory.setConnectionTimeout((int) rabbitProperties.getConnectionTimeout().toMillis());
        }
        if (rabbitProperties.getRequestedHeartbeat() != null) {
            factory.setRequestedHeartbeat((int) rabbitProperties.getRequestedHeartbeat().getSeconds());
        }
        factory.setRequestedChannelMax(rabbitProperties.getRequestedChannelMax());
        factory.afterPropertiesSet();
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(factory.getObject());
        if (rabbitProperties.getAddresses() != null) {
            connectionFactory.setAddresses(rabbitProperties.getAddresses());
        }
        connectionFactory.setPublisherReturns(rabbitProperties.isPublisherReturns());
        if (rabbitProperties.getPublisherConfirmType() != null) {
            connectionFactory.setPublisherConfirmType(rabbitProperties.getPublisherConfirmType());
        }
        if (rabbitProperties.getCache() != null) {
            if (rabbitProperties.getCache().getConnection() != null) {
                connectionFactory.setCacheMode(rabbitProperties.getCache().getConnection().getMode());
                connectionFactory.setConnectionCacheSize(rabbitProperties.getCache().getConnection().getSize());
            }
            if (rabbitProperties.getCache().getChannel() != null) {
                connectionFactory.setChannelCheckoutTimeout(rabbitProperties.getCache().getChannel().getCheckoutTimeout().toMillis());
                connectionFactory.setChannelCacheSize(rabbitProperties.getCache().getChannel().getSize());
            }
        }
        return connectionFactory;
    }

}
