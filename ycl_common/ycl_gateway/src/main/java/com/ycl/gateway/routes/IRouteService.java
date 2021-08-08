package com.ycl.gateway.routes;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 操作路由的方法
 * @date: 2021/7/30 11:00
 */
public interface IRouteService {
    Collection<RouteDefinition> getRouteDefinitions();

    boolean save(RouteDefinition routeDefinition);

    boolean delete(String routeId);
}
