package com.ycl.gateway.bean;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 断言模型
 * @date: 2021/7/29 13:36
 */
public class GatewayPredicateDefinition {

    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
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
