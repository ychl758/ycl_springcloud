package com.ycl.gateway.routes.expand.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayFilterDefinition;
import com.ycl.gateway.common.Constant;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 路由重写适配器,尾部重写
 */
@Component
public class PrefixPathHandler extends AbstractHandler {

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register(Constant.ROUTE_PREFIX_PATH, this);
    }

    @Override
    public JSONObject PrefixPathJson(JSONObject jsonObject, JSONObject jsb) {
        if (jsonObject.get(Constant.PREDICATE_DEFINITION_NAME).toString().equals(Constant.ROUTE_PREFIX_PATH)) {
            JSONObject StripPrefix = JSON.parseObject(jsonObject.get(Constant.ROUTE_ARGS).toString());
            jsb.put(Constant.ROUTE_PREFIX_PATH, StripPrefix.get(Constant.ROUTE_GENKEY_0));
            jsb.put(Constant.ROUTE_CX, Constant.ROUTE_CX_1);
        }
        return jsb;
    }

    @Override
    public GatewayFilterDefinition PrefixPath(JSONObject jsb) {
        GatewayFilterDefinition gatewayFilterDefinition = new GatewayFilterDefinition();
        if (jsb.get(Constant.ROUTE_CX).equals(Constant.ROUTE_CX_1)) {
            gatewayFilterDefinition.setName(Constant.ROUTE_PREFIX_PATH);
            gatewayFilterDefinition.setArgs(new HashMap<String, String>() {{
                put(Constant.ROUTE_GENKEY_0, jsb.getString(Constant.ROUTE_PREFIX_PATH));
            }});
            return gatewayFilterDefinition;
        }
        return null;
    }
}
