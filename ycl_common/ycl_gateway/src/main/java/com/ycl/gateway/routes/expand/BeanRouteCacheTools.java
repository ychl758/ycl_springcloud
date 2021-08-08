package com.ycl.gateway.routes.expand;

import java.util.concurrent.ConcurrentHashMap;


public class BeanRouteCacheTools {
    /**
     * 首先ConcurrentHashMap是安全的， 单例的情况下加volatite以后防止指令重排
     */
    public volatile static ConcurrentHashMap<String, String> cacheMap;

    /**
     * DCL double check lock  双端检索
     * synchronized 之前和之后的检查
     *
     * 首先双端检索并不是线程安全的 因为会有指令重排的情况存在  所以要使用volatite来静止指令重排（排查class）
     * @return
     */
    public static ConcurrentHashMap getInstance() {
        if (null == cacheMap) {
            synchronized (ConcurrentHashMap.class) {
                if (null == cacheMap) {
                    cacheMap = new ConcurrentHashMap<>();
                }
            }
        }
        return cacheMap;
    }

    public static String getCache(String K) {
        if (cacheMap.containsKey(K)) {
            return cacheMap.get(K);
        }
        return null;
    }

    public static void initCache(String K, String V) {
        cacheMap.put(K, V);
    }


    public static void removeCache(String K) {
        if (cacheMap.containsKey(K)) {
            cacheMap.remove(K);
        }
    }

}
