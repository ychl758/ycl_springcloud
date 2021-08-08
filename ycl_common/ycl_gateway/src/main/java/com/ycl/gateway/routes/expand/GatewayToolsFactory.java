package com.ycl.gateway.routes.expand;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayBean;
import com.ycl.gateway.bean.GatewayFilterDefinition;
import com.ycl.gateway.bean.GatewayPredicateDefinition;
import com.ycl.gateway.common.Constant;
import com.ycl.gateway.routes.expand.handler.AbstractHandler;
import com.ycl.gateway.routes.expand.handler.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: GatewayToolsFactory ，组装参数
 * @date: 2021/8/5 11:36
 */
public class GatewayToolsFactory {

    private static final Logger log = LoggerFactory.getLogger(GatewayToolsFactory.class);

    /**
     * 服务器接收参数，适配成断言和过滤器规则
     *
     * @param jsb 参数
     * @return GatewayBean
     */
    public GatewayBean gatewayAssembly(JSONObject jsb) {
        List<GatewayFilterDefinition> list = new LinkedList<>();
        List<GatewayPredicateDefinition> list1 = new LinkedList<>();
        list.add(Factory.getInvokeStrategy(Constant.ROUTE_EXPAND_HYSTRIX).Hystrix(jsb));
        list.add(Factory.getInvokeStrategy(Constant.ROUTE_STRIP_PREFIX).StripPrefix(jsb));
        list.add(Factory.getInvokeStrategy(Constant.ROUTE_PREFIX_PATH).PrefixPath(jsb));
        list.add(Factory.getInvokeStrategy(Constant.ROUTE_RATE_LIMITER).Currentlimit(jsb));
        list1.add(Factory.getInvokeStrategy(Constant.ROUTE_PATH).Path(jsb));
        list1.add(Factory.getInvokeStrategy(Constant.ROUTE_WEIGHT).Weight(jsb));
        /**去空*/
        List<GatewayFilterDefinition> list2 = list.stream().filter(gatewayFilterDefinition -> gatewayFilterDefinition != null).collect(Collectors.toList());
        List<GatewayPredicateDefinition> list3 = list1.stream().filter(gatewayPredicateDefinition -> gatewayPredicateDefinition != null).collect(Collectors.toList());
        jsb.put(Constant.ROUTE_FILTERS, JSON.toJSONString(list2));
        jsb.put(Constant.ROUTE_PREDICATES, JSON.toJSONString(list3));
        jsb.remove(Constant.ROUTE_EXPAND_HYSTRIX);
        jsb.remove(Constant.ROUTE_STRIP_PREFIX);
        jsb.remove(Constant.ROUTE_CX);
        jsb.remove(Constant.ROUTE_CURRENT_LIMIT);
        jsb.remove(Constant.ROUTE_LIMIT_CSLP);
        jsb.remove(Constant.ROUTE_LIMIT_LPRL);
        jsb.remove(Constant.ROUTE_WEIGHT);
        jsb.remove(Constant.ROUTE_WEIGHT_NUMBER);
        jsb.remove(Constant.ROUTE_PATTEN_NAME);
        try {
            return JSONObject.toJavaObject(jsb, GatewayBean.class);
        } catch (Exception e) {
            log.debug("字符串参数组装路由失败！", e);
        }
        return null;
    }

    /**
     * 解析路由信息，返回前端展示
     *
     * @param list List<GatewayBean>
     * @return 解析后的数据
     */
    public List<JSONObject> parsingRoutes(List<GatewayBean> list) {
        try {
            return list.stream().map(k -> parsing(k)).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("路由解析出错！", e);
        }
        return null;
    }

    /**
     * 单条路由解析
     *
     * @param gatewayRoutes 数据库查出的路由信息
     * @return
     */
    public JSONObject OneGatewayRoutesToString(GatewayBean gatewayRoutes) {
        try {
            return parsing(gatewayRoutes);
        } catch (Exception e) {
            log.error("路由解析出错！", e);
        }
        return null;
    }

    /**
     * 解析的操作
     * @param gatewayRoutes
     * @return
     */
    public JSONObject parsing(GatewayBean gatewayRoutes) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(gatewayRoutes));
        jsonObject.put(Constant.ROUTE_CX, Constant.ROUTE_CX_0);
        jsonObject.put(Constant.ROUTE_EXPAND_HYSTRIX, Constant.ROUTE_CX_0);
        jsonObject.put(Constant.ROUTE_CURRENT_LIMIT, Constant.ROUTE_CX_0);
        jsonObject.put(Constant.ROUTE_WEIGHT, Constant.ROUTE_CX_0);
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.get(Constant.ROUTE_PREDICATES).toString());
        JSONArray jsonArray1 = JSONArray.parseArray(jsonObject.get(Constant.ROUTE_FILTERS).toString());
        AbstractHandler abstractHandlerHystrix = Factory.getInvokeStrategy(Constant.ROUTE_EXPAND_HYSTRIX);
        AbstractHandler abstractHandlerStripPrefix = Factory.getInvokeStrategy(Constant.ROUTE_STRIP_PREFIX);
        AbstractHandler abstractHandlerPrefixPath = Factory.getInvokeStrategy(Constant.ROUTE_PREFIX_PATH);
        AbstractHandler abstractHandlerCurrentlimit = Factory.getInvokeStrategy(Constant.ROUTE_RATE_LIMITER);
        AbstractHandler abstractHandlerPattenName = Factory.getInvokeStrategy(Constant.ROUTE_PATH);
        AbstractHandler abstractHandlerWeight = Factory.getInvokeStrategy(Constant.ROUTE_WEIGHT);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsb = JSON.parseObject(jsonArray.get(i).toString());
            JSONObject pathJson = abstractHandlerPattenName.PathJson(jsb, jsonObject);
            jsonObject = abstractHandlerWeight.WeightJson(jsb, pathJson);
        }
        JSONObject jsonObjectLast = new JSONObject();
        if (jsonArray1.size() == 0) {
            jsonObjectLast = jsonObject;
            jsonObjectLast.put("Currentlimit", Constant.ROUTE_CX_0);
            jsonObjectLast.put("Weight", Constant.ROUTE_CX_0);
            jsonObjectLast.put("routeCX", Constant.ROUTE_CX_0);
        } else {
            for (int i = 0; i < jsonArray1.size(); i++) {
                JSONObject jsb = JSON.parseObject(jsonArray1.get(i).toString());
                JSONObject hystrixJson = abstractHandlerHystrix.HystrixJson(jsb, jsonObject);
                JSONObject stripPrefixJson = abstractHandlerStripPrefix.StripPrefixJson(jsb, hystrixJson);
                JSONObject prefixPathJson = abstractHandlerPrefixPath.PrefixPathJson(jsb, stripPrefixJson);
                jsonObjectLast = abstractHandlerCurrentlimit.RequestRateLimiterJson(jsb, prefixPathJson);
            }
        }
        jsonObjectLast.remove("filterDefinition");
        jsonObjectLast.remove("predicateDefinition");
        jsonObjectLast.remove("filters");
        jsonObjectLast.remove("predicates");
        return jsonObjectLast;
    }


}