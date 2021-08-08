package com.ycl.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 限流工厂
 * @date: 2021/7/29 13:36
 */
@Configuration
public class ResolverConfig {

    private static  final Logger LOGGER= LoggerFactory.getLogger(ResolverConfig.class);

    @Bean
    public KeyResolver uriKeyResolver() {
        LOGGER.info("限流工厂配置加载完毕，当前是url限流工厂模式");
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getPath()
                        .toString()
        );
    }

//    @Bean
//    public KeyResolver ipKeyResolver() {
//        log.info("限流工厂配置加载完毕，当前是ip限流工厂模式");
//        return exchange -> Mono.just(
//                exchange.getRequest()
//                        .getHeaders()
//                        .getFirst("X-Forwarded-For")
//        );
//    }
//
//    @Bean
//    public KeyResolver userKeyResolver() {
//        log.info("限流工厂配置加载完毕，当前是用户限流工厂");
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
//    }

}
