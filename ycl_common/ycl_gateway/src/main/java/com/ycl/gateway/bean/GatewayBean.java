package com.ycl.gateway.bean;

import com.alibaba.fastjson.JSON;
import com.ycl.gateway.common.BaseEntity;
import com.ycl.gateway.common.exception.ExampleException;
import com.ycl.gateway.common.exception.ExceptionCodeEnum;
import org.springframework.util.StringUtils;


import java.util.Collections;
import java.util.List;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 网关路由的实体 主要是实现了动态配置刷新路由
 * @date: 2021/7/29 13:36
 */
public class GatewayBean extends BaseEntity {

    /**
     * 路由id
     */
    private String routeId;

    /**
     * 路由的url
     */
    private String routeUrl;

    /**
     * 路由的优先级，数字越大优先级越低，支持负数
     */
    private Integer routeOrder;

    /**
     * 是否有效，有效的路由在刷新网关的时候才会被加载到路由表中
     */
    private Integer isEbl;

    /**
     * 是否删除，删除路由做的时候逻辑删除，没做物理删除
     */
    private Integer isDel;


    /***
     * 断言
     */
    private String predicates;

    /**
     * 过滤器
     */
    private String filters;

    /**
     * 作者
     */
    private String routeAuthor;

    /***
     * 路由名称
     */
    private String routeName;


    /**
     * 获取断言集合
     *
     * @return
     */
    public List<GatewayPredicateDefinition> getPredicateDefinition() {
        try {
            if (!StringUtils.isEmpty(this.predicates)) {
                return JSON.parseArray(this.predicates, GatewayPredicateDefinition.class);
            }
            return Collections.emptyList();
        } catch (Exception e) {
            throw new ExampleException(ExceptionCodeEnum.Route_Exception_Rredicates);
        }
    }

    /**
     * 获取过滤器集合
     *
     * @return
     */
    public List<GatewayFilterDefinition> getFilterDefinition() {
        try {
            if (!StringUtils.isEmpty(this.filters)) {
                return JSON.parseArray(this.filters, GatewayFilterDefinition.class);
            }
            return Collections.emptyList();
        } catch (Exception e) {
            throw new ExampleException(ExceptionCodeEnum.Route_Exception_Filters);
        }
    }


    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }

    public Integer getRouteOrder() {
        return routeOrder;
    }

    public void setRouteOrder(Integer routeOrder) {
        this.routeOrder = routeOrder;
    }

    public Integer getIsEbl() {
        return isEbl;
    }

    public void setIsEbl(Integer isEbl) {
        this.isEbl = isEbl;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getPredicates() {
        return predicates;
    }

    public void setPredicates(String predicates) {
        this.predicates = predicates;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getRouteAuthor() {
        return routeAuthor;
    }

    public void setRouteAuthor(String routeAuthor) {
        this.routeAuthor = routeAuthor;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
}
