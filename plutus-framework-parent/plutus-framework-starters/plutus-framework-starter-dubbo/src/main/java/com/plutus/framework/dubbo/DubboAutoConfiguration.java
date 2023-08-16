package com.plutus.framework.dubbo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScanRegistrar;
import org.apache.dubbo.config.spring.context.annotation.DubboConfigConfigurationRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Jerry
 * @Date: 2023/8/16 15:23
 */
@Configuration
@ConditionalOnBean({DubboComponentScanRegistrar.class, DubboConfigConfigurationRegistrar.class})
public class DubboAutoConfiguration {




}
