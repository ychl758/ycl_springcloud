package com.ycl.gateway.routes.expand.handler;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 维护一个key, value的map
 * @date: 2021/8/05 10:06
 */
public class Factory {
    private static Map<String, AbstractHandler> strategyMap = Maps.newHashMap();

    public static AbstractHandler getInvokeStrategy(String name) {
        return strategyMap.get(name);
    }

    public static void register(String name, AbstractHandler handler) {
        if (StringUtils.isEmpty(name) || null == handler) {
            return;
        }
        strategyMap.put(name, handler);
    }


}
