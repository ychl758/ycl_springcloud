package com.ycl.gateway.routes.expand.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayFilterDefinition;
import com.ycl.gateway.common.Constant;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 限流器
 */
@Component
public class RequestRateLimiterHandler extends AbstractHandler {

    @Override
    public void afterPropertiesSet() throws Exception {
        Factory.register(Constant.ROUTE_RATE_LIMITER, this);
    }

    /**
     * TODO,此处不做常量处理
     * @param jsb
     * @return
     */
    @Override
    public GatewayFilterDefinition Currentlimit(JSONObject jsb) {
        GatewayFilterDefinition gatewayFilterDefinition = new GatewayFilterDefinition();
        if (jsb.get(Constant.ROUTE_CURRENT_LIMIT).equals(Constant.ROUTE_EXPAND_EFFICIENT)) {
            gatewayFilterDefinition.setName(Constant.ROUTE_RATE_LIMITER);
            gatewayFilterDefinition.setArgs(new HashMap<String, String>() {{
                put(Constant.ROUTE_LIMIT_KEY, Constant.ROUTE_LIMIT_TYPE);
                put(Constant.ROUTE_LIMIT_REDIS_CSLP, jsb.getString(Constant.ROUTE_LIMIT_CSLP));
                put(Constant.ROUTE_LIMIT_REDIS_LPRL, jsb.getString(Constant.ROUTE_LIMIT_LPRL));
            }});
            return gatewayFilterDefinition;
        }
        return null;

    }

    @Override
    public JSONObject RequestRateLimiterJson(JSONObject jsonObject, JSONObject jsb) {
        if (jsonObject.get(Constant.PREDICATE_DEFINITION_NAME).toString().equals(Constant.ROUTE_EXPAND_EFFICIENT)) {
            JSONObject Currentlimit = JSON.parseObject(jsonObject.get(Constant.ROUTE_ARGS).toString());
            jsb.put(Constant.ROUTE_CURRENT_LIMIT, Constant.ROUTE_EXPAND_EFFICIENT);
            jsb.put(Constant.ROUTE_LIMIT_CSLP, Currentlimit.get(Constant.ROUTE_LIMIT_REDIS_CSLP));
            jsb.put(Constant.ROUTE_LIMIT_LPRL, Currentlimit.get(Constant.ROUTE_LIMIT_REDIS_LPRL));
        }
        return jsb;
    }

}
