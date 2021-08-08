package com.ycl.gateway.dao;


import com.ycl.gateway.bean.GatewayBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: GatewayRouteDao
 * @date: 2021/7/29 13:36
 */
@Mapper
public interface GatewayRouteDao {
    /**
     * 获取所有的有效的路由信息
     *
     * @return List<GatewayBean>
     */
    public List<GatewayBean> queryAllEblRoutes();

    /**
     * 新增路由信息
     *
     * @param gatewayBean 参数实体
     * @return 受影响行数
     */
    public int insertGateway(GatewayBean gatewayBean);

    /**
     * 修改路由信息
     * @param gatewayBean 路由信息
     * @return 受到影响的行数
     */
    public int updateRoute(GatewayBean gatewayBean);

}

