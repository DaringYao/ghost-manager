package com.ghost.flowable.config;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2024/8/15 00:46
 */


@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {
    /**
     * 防止生成的流程图中文乱码
     */
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
    }
}

