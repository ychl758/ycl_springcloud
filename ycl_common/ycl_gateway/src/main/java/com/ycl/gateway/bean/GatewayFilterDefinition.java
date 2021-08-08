package com.ycl.gateway.bean;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 过滤器模型
 * @date: 2021/7/29 13:36
 */
public class GatewayFilterDefinition {
    /**
     * Filter Name
     */
    private String name;

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}
