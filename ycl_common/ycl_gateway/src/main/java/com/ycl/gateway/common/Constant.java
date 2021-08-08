package com.ycl.gateway.common;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 常量类
 * @date: 2021/7/30 11:21
 */
public class Constant {
    /***
     * 构造方法
     */
    private Constant() {
    }

    /**
     * 有效
     */
    public static final Integer COMMON_YX = 1;

    /**
     * 无效
     */
    public static final Integer COMMON_WX = 0;

    /**
     * 父节点
     */
    public static final String TREE_PARENT = "parent";
    /**
     * 所有孩子节点
     */
    public static final String TREE_ALL_CHILDEN = "allChildren";

    /**
     * 成功
     */
    public static final String COMMON_SUCCESS = "success";

    /**
     * 失败
     */
    public static final String COMMON_FAILED = "falied";


    /**
     * 过滤器
     */
    public static final String ROUTE_FILTERS = "filters";

    /**
     * 断言
     */
    public static final String ROUTE_PREDICATES = "predicates";

    /**
     * 过滤的name
     * PredicateDefinition_NAME
     */
    public static final String PREDICATE_DEFINITION_NAME = "name";

    /**
     * 判断有效，新增相应的过滤
     */
    public static final String ROUTE_EXPAND_EFFICIENT = "1";

    /**
     * 限流Hystrix的关键字
     */
    public static final String ROUTE_EXPAND_HYSTRIX = "Hystrix";

    /**
     * 熔断回调地址
     */
    public static final String HYSTRIX_FORWARD = "forward:/mapgateway/v1/v0/Hystrix/supermap";

    /**
     * hystrix的回调关键字
     */
    public static final String HYSTRIX_FALLBACKURI = "fallbackUri";

    /**
     * 只支持按照path做断言
     */
    public static final String ROUTE_PATH = "Path";

    /**
     * pattenName
     */
    public static final String ROUTE_PATTEN_NAME = "pattenName";

    /**
     * pattern
     */
    public static final String ROUTE_PATTERN = "pattern";

    /**
     * args
     */
    public static final String ROUTE_ARGS = "args";

    /**
     * 重写路由的过滤器的key 头部
     */
    public static final String ROUTE_PREFIX_PATH = "PrefixPath";

    /**
     * 路由重写  尾部加减
     */
    public static final String ROUTE_STRIP_PREFIX = "StripPrefix";

    /**
     * _genkey_0
     */
    public static final String ROUTE_GENKEY_0 = "_genkey_0";

    /**
     * _genkey_0
     */
    public static final String ROUTE_GENKEY_1 = "_genkey_1";

    /**
     * 路由重写 是否
     */
    public static final String ROUTE_CX = "routeCX";
    /**
     * 尾部重写
     */
    public static final String ROUTE_CX_1 = "1";
    /**
     * 头部重写
     */
    public static final String ROUTE_CX_2 = "2";

    /**
     * 0
     */
    public static final String ROUTE_CX_0 = "0";

    /**
     * 限流器
     */
    public static final String ROUTE_RATE_LIMITER = "RequestRateLimiter";

    /**
     * Currentlimit
     */
    public static final String ROUTE_CURRENT_LIMIT = "Currentlimit";

    /**
     * 路由的权重
     */
    public static final String ROUTE_WEIGHT = "Weight";

    /**
     * 权重数字配比
     */
    public static final String ROUTE_WEIGHT_NUMBER = "weightNumber";

    /**
     * 令牌桶算法每秒产生的令牌数
     */
    public static final String ROUTE_LIMIT_CSLP = "replenishRate";

    /**
     * 令牌容量
     */
    public static final String ROUTE_LIMIT_LPRL = "burstCapacity";

    /**
     * 限流器的关键字
     */
    public static final String ROUTE_LIMIT_REDIS_CSLP = "redis-rate-limiter.replenishRate";
    /**
     * 限流器的桶容量
     */
    public static final String ROUTE_LIMIT_REDIS_LPRL = "redis-rate-limiter.burstCapacity";

    /**
     * 限流过滤器的关键字
     */
    public static final String ROUTE_LIMIT_KEY = "key-resolver";

    /**
     * URL类型限流器，还可以配置ip限流器等
     */
    public static final String ROUTE_LIMIT_TYPE = "#{@uriKeyResolver}";
}
