package com.ycl.gateway.service;

import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayBean;
import com.ycl.gateway.bean.GatewayRouteDefinition;
import com.ycl.gateway.dao.GatewayRouteDao;
import com.ycl.gateway.routes.GatewayOperationDao;
import com.ycl.gateway.routes.expand.GatewayToolsFactory;
import com.ycl.gateway.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 路由操作的service
 * @date: 2021/7/30 16:47
 */
@Service
public class GatewayService {

    private static final Logger log = LoggerFactory.getLogger(GatewayService.class);

    /***
     * mapper，操作数据库
     */
    @Resource
    GatewayRouteDao gatewayRouteDao;
    /**
     * 刷新路由表中的路由
     */
    @Autowired
    GatewayOperationDao gatewayOperationDao;


    /**
     * 刷新路由信息
     *
     * @return 是否刷新成功
     */
    public boolean refreshRoutes() {
        List<GatewayBean> gatewayBeans = gatewayRouteDao.queryAllEblRoutes();
        List<GatewayRouteDefinition> definitionList = gatewayBeans.stream().map(route -> {
            return new GatewayRouteDefinition() {{
                setId(route.getRouteId());
                setFilters(route.getFilterDefinition());
                setOrder(route.getRouteOrder());
                setUri((route.getRouteUrl()));
                setPredicates((route.getPredicateDefinition()));
            }};
        }).collect(Collectors.toList());
        return gatewayOperationDao.addroutes(definitionList);
    }


    /***
     * 删除或者修改路由
     * @param gatewayRouteDefinition
     * @return
     */
    public boolean updateroutes(GatewayRouteDefinition gatewayRouteDefinition) {
        try {
            gatewayOperationDao.delete(gatewayRouteDefinition.getId());
        } catch (Exception e) {
            log.error("删除路由失败,路由id:{},异常信息：", gatewayRouteDefinition.getId(), e);
            return false;
        }
        try {
            gatewayOperationDao.addoneroutes(gatewayRouteDefinition);
            return true;
        } catch (Exception e) {
            log.error("新增路由失败,路由id:{},异常信息：", gatewayRouteDefinition.getId(), e);
            return true;
        }
    }

    /**
     * 新增路由新增到库中
     *
     * @param jsonObject 参数信息
     */
    public void addRoute(JSONObject jsonObject) {
        GatewayToolsFactory gatewayToolsFactory = new GatewayToolsFactory();
        GatewayBean gatewayBean = gatewayToolsFactory.gatewayAssembly(jsonObject);
        gatewayBean.setRouteId(UuidUtil.get32UUid());
        if (StringUtils.isEmpty(gatewayBean.getNodeId())) {
            gatewayBean.setNodeId(UuidUtil.get32UUid());
        }
        gatewayBean.setCreateTime(new Date());
        gatewayRouteDao.insertGateway(gatewayBean);
    }

    /**
     * 获取所有库中的路由信息
     *
     * @return
     */
    public List<GatewayBean> getListInfo() {
        List<GatewayBean> gatewayBeans = gatewayRouteDao.queryAllEblRoutes();
        return null;
    }

    /**
     * 按照路由id修改库中的路由信息
     *
     * @param gatewayBean 需要修改的路信息
     * @return 受到影响的行数
     */
    public int updateRoutes(GatewayBean gatewayBean) {
        return gatewayRouteDao.updateRoute(gatewayBean);
    }
}
