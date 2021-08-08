package com.ycl.gateway.routes.expand.handler;

import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayFilterDefinition;
import com.ycl.gateway.bean.GatewayPredicateDefinition;
import org.springframework.beans.factory.InitializingBean;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: AbstractHandler,实现InitializingBean接口
 * @date: 2021/8/05 10:06
 */
public abstract class AbstractHandler implements InitializingBean {


    public GatewayFilterDefinition Hystrix(JSONObject jsonObject) {
        throw new UnsupportedOperationException();
    }

    public GatewayFilterDefinition StripPrefix(JSONObject jsonObject) {
        throw new UnsupportedOperationException();
    }

    public GatewayFilterDefinition PrefixPath(JSONObject jsonObject) {
        throw new UnsupportedOperationException();
    }

    public GatewayFilterDefinition Currentlimit(JSONObject jsonObject) {
        throw new UnsupportedOperationException();
    }

    public GatewayPredicateDefinition Path(JSONObject jsonObject) {
        throw new UnsupportedOperationException();
    }

    public GatewayPredicateDefinition Weight(JSONObject jsonObject) {
        throw new UnsupportedOperationException();
    }

    public JSONObject HystrixJson(JSONObject jsonObject, JSONObject jsonObject1) {
        throw new UnsupportedOperationException();
    }

    public JSONObject StripPrefixJson(JSONObject jsonObject, JSONObject jsonObject1) {
        throw new UnsupportedOperationException();
    }

    public JSONObject PrefixPathJson(JSONObject jsonObject, JSONObject jsonObject1) {
        throw new UnsupportedOperationException();
    }

    public JSONObject RequestRateLimiterJson(JSONObject jsonObject, JSONObject jsonObject1) {
        throw new UnsupportedOperationException();
    }

    public JSONObject PathJson(JSONObject jsonObject, JSONObject jsonObject1) {
        throw new UnsupportedOperationException();
    }

    public JSONObject WeightJson(JSONObject jsonObject, JSONObject jsonObject1) {
        throw new UnsupportedOperationException();
    }

}
