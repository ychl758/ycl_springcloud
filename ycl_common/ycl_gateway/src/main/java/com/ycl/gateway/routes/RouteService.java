package com.ycl.gateway.routes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 操作路由的方法
 * @date: 2021/7/30 11:00
 */
@Service
public class RouteService implements IRouteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);
    private Map<String, RouteDefinition> routeDefinitionMaps = new HashMap<>();


    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMaps.values();
    }

    @Override
    public boolean save(RouteDefinition routeDefinition) {
        routeDefinitionMaps.put(routeDefinition.getId(), routeDefinition);
        LOGGER.info("新增路由1条：{},目前路由共{}条", routeDefinition, routeDefinitionMaps.size());
        return true;
    }

    @Override
    public boolean delete(String routeId) {
        routeDefinitionMaps.remove(routeId);
        LOGGER.info("删除路由1条：{},目前路由共{}条", routeId, routeDefinitionMaps.size());
        return true;
    }
}
