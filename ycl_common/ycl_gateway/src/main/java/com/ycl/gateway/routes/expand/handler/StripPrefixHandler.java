package com.ycl.gateway.routes.expand.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayFilterDefinition;
import com.ycl.gateway.common.Constant;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 路由重写 头部重写
 */
@Component
public class StripPrefixHandler extends AbstractHandler {
    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register(Constant.ROUTE_STRIP_PREFIX, this);
    }

    @Override
    public JSONObject StripPrefixJson(JSONObject jsonObject, JSONObject jsb) {
        if (jsonObject.get(Constant.PREDICATE_DEFINITION_NAME).toString().equals(Constant.ROUTE_STRIP_PREFIX)) {
            JSONObject StripPrefix = JSON.parseObject(jsonObject.getString(Constant.ROUTE_ARGS));
            jsb.put(Constant.ROUTE_STRIP_PREFIX, StripPrefix.get(Constant.ROUTE_GENKEY_0));
            jsb.put(Constant.ROUTE_CX, Constant.ROUTE_CX_2);
        }
        return jsb;
    }

    @Override
    public GatewayFilterDefinition StripPrefix(JSONObject jsonObject) {
        GatewayFilterDefinition gatewayFilterDefinition = new GatewayFilterDefinition();
        if (jsonObject.get(Constant.ROUTE_CX).equals(Constant.ROUTE_CX_2)) {
            gatewayFilterDefinition.setName(Constant.ROUTE_STRIP_PREFIX);
            gatewayFilterDefinition.setArgs(new HashMap<String, String>() {{
                put(Constant.ROUTE_GENKEY_0, jsonObject.getString(Constant.ROUTE_STRIP_PREFIX));
            }});
            return gatewayFilterDefinition;
        }
        return null;

    }
}

