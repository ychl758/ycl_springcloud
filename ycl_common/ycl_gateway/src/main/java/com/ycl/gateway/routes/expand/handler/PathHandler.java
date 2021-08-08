package com.ycl.gateway.routes.expand.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayPredicateDefinition;
import com.ycl.gateway.common.Constant;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 断言器适配
 */
@Component
public class PathHandler extends AbstractHandler {

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register(Constant.ROUTE_PATH, this);
    }

    @Override
    public GatewayPredicateDefinition Path(JSONObject jsb) {
        GatewayPredicateDefinition gatewayPredicateDefinition = new GatewayPredicateDefinition();
        if (jsb.containsKey(Constant.ROUTE_PATTEN_NAME)) {
            gatewayPredicateDefinition.setName(Constant.ROUTE_PATH);
            gatewayPredicateDefinition.setArgs(new HashMap<String, String>() {{
                put(Constant.ROUTE_PATTERN, jsb.getString(Constant.ROUTE_PATTEN_NAME));
            }});
            return gatewayPredicateDefinition;
        }
        return null;

    }


    @Override
    public JSONObject PathJson(JSONObject jsonObject, JSONObject jsb) {
        if (jsonObject.getString(Constant.PREDICATE_DEFINITION_NAME).equals(Constant.ROUTE_PATH)) {
            JSONObject jsbpattern = JSON.parseObject(jsonObject.getString(Constant.ROUTE_ARGS));
            jsb.put(Constant.ROUTE_PATTEN_NAME, jsbpattern.get(Constant.ROUTE_PATTERN));
        }
        return jsb;
    }
}
