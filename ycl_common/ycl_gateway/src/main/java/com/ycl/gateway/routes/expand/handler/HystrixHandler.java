package com.ycl.gateway.routes.expand.handler;

import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayFilterDefinition;
import com.ycl.gateway.common.Constant;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 熔断适配器
 */
@Component
public class HystrixHandler extends AbstractHandler {
    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register(Constant.ROUTE_EXPAND_HYSTRIX, this);
    }

    @Override
    public GatewayFilterDefinition Hystrix(JSONObject jsonObject) {
        GatewayFilterDefinition gatewayFilterDefinition = new GatewayFilterDefinition();
        if (jsonObject.get(Constant.ROUTE_EXPAND_HYSTRIX).equals(Constant.ROUTE_EXPAND_EFFICIENT)) {
            gatewayFilterDefinition.setName(Constant.ROUTE_EXPAND_HYSTRIX);
            gatewayFilterDefinition.setArgs(new HashMap<String, String>() {{
                put(Constant.PREDICATE_DEFINITION_NAME, Constant.ROUTE_EXPAND_HYSTRIX);
                put(Constant.HYSTRIX_FALLBACKURI, Constant.HYSTRIX_FORWARD);
            }});
            return gatewayFilterDefinition;
        }
        return null;

    }

    @Override
    public JSONObject HystrixJson(JSONObject jsonObject, JSONObject jsb) {
        if (jsonObject.get(Constant.PREDICATE_DEFINITION_NAME).equals(Constant.ROUTE_EXPAND_HYSTRIX)) {
            jsb.put(Constant.ROUTE_EXPAND_HYSTRIX, Constant.ROUTE_EXPAND_EFFICIENT);
        }
        return jsb;
    }
}
