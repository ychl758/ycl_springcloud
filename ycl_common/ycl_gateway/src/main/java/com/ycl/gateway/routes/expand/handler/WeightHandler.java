package com.ycl.gateway.routes.expand.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayPredicateDefinition;
import com.ycl.gateway.common.Constant;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 路由权重，不在注册中心中，使用ip转发的单体服务使用
 */
@Component
public class WeightHandler extends AbstractHandler {

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register(Constant.ROUTE_WEIGHT, this);
    }

    @Override
    public GatewayPredicateDefinition Weight(JSONObject jsb) {
        GatewayPredicateDefinition gatewayPredicateDefinition = new GatewayPredicateDefinition();
        if (jsb.get(Constant.ROUTE_WEIGHT).equals(Constant.ROUTE_EXPAND_EFFICIENT)) {
            gatewayPredicateDefinition.setName(Constant.ROUTE_WEIGHT);
            gatewayPredicateDefinition.setArgs(new HashMap<String, String>() {{
                put(Constant.ROUTE_GENKEY_0, jsb.getString(Constant.ROUTE_PATTEN_NAME));
                put(Constant.ROUTE_GENKEY_1, jsb.getString(Constant.ROUTE_WEIGHT_NUMBER));
            }});
            return gatewayPredicateDefinition;
        }
        return null;

    }

    @Override
    public JSONObject WeightJson(JSONObject jsonObject, JSONObject jsb) {
        if (jsonObject.getString(Constant.PREDICATE_DEFINITION_NAME).equals(Constant.ROUTE_WEIGHT)) {
            JSONObject jsbpattern = JSON.parseObject(jsonObject.getString(Constant.ROUTE_ARGS));
            jsb.put(Constant.ROUTE_WEIGHT_NUMBER, jsbpattern.get(Constant.ROUTE_GENKEY_1));
            jsb.put(Constant.ROUTE_WEIGHT, Constant.ROUTE_EXPAND_EFFICIENT);
        }
        return jsb;
    }
}
