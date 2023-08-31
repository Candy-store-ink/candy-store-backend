package com.github.candy.store.config.props;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class AppPropsConfig {

    @Bean
    @Validated
    public AppProps props() {
        return new AppProps();
    }
}
