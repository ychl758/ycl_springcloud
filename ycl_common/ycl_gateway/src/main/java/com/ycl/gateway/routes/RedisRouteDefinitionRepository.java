package com.ycl.gateway.routes;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: RedisRouteDefinitionRepository
 * @date: 2021/7/29 13:36
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRouteDefinitionRepository.class);

    @Autowired
    private RouteService routeService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeService.getRouteDefinitions());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            LOGGER.info("保存路由:{}", route);
            routeService.save(routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeService.delete(id);
            LOGGER.info("删除路由，路由id:{}", routeId);
            return Mono.empty();
        });
    }
}
