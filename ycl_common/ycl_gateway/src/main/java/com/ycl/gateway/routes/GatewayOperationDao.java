package com.ycl.gateway.routes;


import com.ycl.gateway.bean.GatewayRouteDefinition;
import com.ycl.gateway.common.Constant;
import com.ycl.gateway.dao.GatewayRouteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: GatewayOperationDao
 * @date: 2021/7/29 13:36
 */
@Repository
public class GatewayOperationDao implements ApplicationEventPublisherAware {

    private static final Logger log = LoggerFactory.getLogger(GatewayRouteDao.class);

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;


    private ApplicationEventPublisher publisher;

    /**
     * GatewayOperationDao
     *
     * @param list 路由的所有信息
     * @return 是否成功
     * @description 刷新所有库里面的路由信息
     * @author yuchenglin
     * @date 2021年08月2日 19:30:33
     * @version 1.0.0
     */
    public boolean addroutes(List<GatewayRouteDefinition> list) {
        try {
            list.forEach(k -> {
                RouteDefinition definition = assembleRouteDefinition(k);
                routeDefinitionWriter.save(Mono.just(definition)).subscribe();
                this.publisher.publishEvent(new RefreshRoutesEvent(this));
            });
        } catch (Exception e) {
            log.error("db批量刷新路由失败，错误信息为：{}", e);
            return false;
        }
        return true;
    }


    /**
     * GatewayOperationDao
     *
     * @param gatewayRouteDefinition 路由信息
     * @return 操作的结果
     * @description 刷新路由表单条
     * @author yuchenglin
     * @date 2021年08月2日 19:30:33
     * @version 1.0.0
     */
    public boolean addoneroutes(GatewayRouteDefinition gatewayRouteDefinition) {
        try {
            RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
        } catch (Exception e) {
            log.error("db刷新单条路由失败，错误信息为：{}", e);
            return false;
        }
        return true;
    }

    /**
     * GatewayOperationDao
     *
     * @param id 路由id
     * @return 删除成功与否
     * @description 删除路由信息
     * @author yuchenglin
     * @date 2021年08月2日 19:30:33
     * @version 1.0.0
     */
    public Mono<ResponseEntity<Object>> delete(String id) {
        return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
            return Mono.just(ResponseEntity.ok().build());
        })).onErrorResume((t) -> {
            return t instanceof NotFoundException;
        }, (t) -> {
            return Mono.just(ResponseEntity.notFound().build());
        });
    }

    /**
     * GatewayOperationDao
     *
     * @param gatewayRouteDefinition 路由信息
     * @return String
     * @description 修改路由信息
     * @author yuchenglin
     * @date 2021年08月2日 19:30:33
     * @version 1.0.0
     */
    public String updateroutes(GatewayRouteDefinition gatewayRouteDefinition) {
        RouteDefinition definition = assembleRouteDefinition(gatewayRouteDefinition);
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            log.error("未找到当前路由, 路由id:{},异常信息为：{}", definition.getId(), e);
            return "未找到当前路由: " + definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return Constant.COMMON_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Constant.COMMON_FAILED;
        }
    }


    /**
     * GatewayOperationDao
     *
     * @param gwdefinition 路由信息
     * @return  RouteDefinition
     * @description 构建路由信息
     * @author yuchenglin
     * @date 2021年08月2日 19:30:33
     * @version 1.0.0
     */
    private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gwdefinition) {

        RouteDefinition definition = new RouteDefinition() {{
            setId(gwdefinition.getId());
        }};

        // Predicates
        /*断言*/
        List<PredicateDefinition> pdList = gwdefinition.getPredicates().stream().map(temp -> {
            return new PredicateDefinition() {{
                setArgs(temp.getArgs());
                setName(temp.getName());
            }};
        }).collect(Collectors.toList());
        definition.setPredicates(pdList);

        // Filters
        /*过滤器*/
        List<FilterDefinition> fdList = gwdefinition.getFilters().stream().map(temp -> {
            return new FilterDefinition() {{
                setArgs(temp.getArgs());
                setName(temp.getName());
            }};
        }).collect(Collectors.toList());
        definition.setFilters(fdList);

        // URI
        URI uri = UriComponentsBuilder.fromUriString(gwdefinition.getUri()).build().toUri();
        definition.setUri(uri);
        definition.setOrder(gwdefinition.getOrder());
        return definition;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
