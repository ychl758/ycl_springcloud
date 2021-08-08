package com.ycl.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 跨域配置
 * @date: 2021/7/30 14:30
 */
@Configuration
public class CorsConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorsConfig.class);


    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        LOGGER.info("跨域配置加载完毕，当前允许任意方法，任意访问源，任意请求头。");
        return new CorsWebFilter(source);
    }
}
