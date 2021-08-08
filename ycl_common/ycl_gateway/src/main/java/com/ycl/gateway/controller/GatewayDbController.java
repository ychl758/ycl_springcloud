package com.ycl.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.ycl.gateway.bean.GatewayBean;
import com.ycl.gateway.common.Constant;
import com.ycl.gateway.common.MessageBulider;
import com.ycl.gateway.common.ReturnMsg;
import com.ycl.gateway.service.GatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 网关控制器，新增和修改删除路由信息
 * @date: 2021/8/3 11:45
 */
@RestController
@Api(tags = "路由库操作")
public class GatewayDbController {

    private final String mappingPath = "/api/dbroutes";

    private static final Logger log = LoggerFactory.getLogger(GatewayDbController.class);

    @Autowired
    GatewayService gatewayService;

    /**
     * 参数格式
     * {
     * "replenishRate":"500",
     * "routeOrder":0,
     * "routeCX":"2",
     * "Hystrix":"1",
     * "Currentlimit":"1",
     * "updateTime":1605341399250,
     * "Weight":"0",
     * "routeName":"测试",
     * "routeUrl":"lb://ycl-consumer-onemapbussiness-v1",
     * "treeId":"2",
     * "routeAuthor":"ycl",
     * "pattenName":"/api/mapbussiness/v1/v0/sxbh/**",
     * "routeId":"1171114407",
     * "createTime":1605341399250,
     * "isEbl":1,
     * "burstCapacity":"15000",
     * "isDel":0,
     * "StripPrefix":"1"
     * }
     *
     * @param str
     * @return
     */
    @ApiOperation("新增路由信息")
    @PostMapping(value = mappingPath)
    public ReturnMsg addRoute(@RequestBody String str) {
        try {
            gatewayService.addRoute(JSONObject.parseObject(str));
            return MessageBulider.success("新增路由信息成功", HttpStatus.OK);
        } catch (Exception e) {
            log.error("参数传递有误！", e);
            return MessageBulider.failed("参数传递有误！", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 查询数据，构造树结构
     *
     * @return 树结构数据
     */
    @ApiOperation("查询数据构建树结构")
    @GetMapping(value = mappingPath + "/routes")
    public ReturnMsg routes() {
        List<GatewayBean> listInfo = gatewayService.getListInfo();
        return MessageBulider.success("查询路由信息成功!", listInfo);
    }

    /**
     * 按照路由id来删除路由
     *
     * @param ids 路由id
     * @return 成功与否
     */
    @ApiOperation("按照路由id来删除路由信息")
    @GetMapping(value = mappingPath + "/{ids}")
    public ReturnMsg deleteRoutes(@PathVariable("ids") String ids) {
        GatewayBean gatewayBean = new GatewayBean();
        gatewayBean.setRouteId(ids);
        gatewayBean.setIsDel(Constant.COMMON_WX);
        gatewayBean.setUpdateTime(new Date());
        gatewayService.updateRoutes(gatewayBean);
        return MessageBulider.success("删除路由信息成功!");
    }

    /**
     * 设置路由的状态
     *
     * @param ids   路由的id
     * @param state 路由的启用的状态
     * @return 成功与否
     */
    @ApiOperation("启用不启用路由")
    @GetMapping(value = mappingPath + "/{ids}/{state}")
    public ReturnMsg enableRoutes(@PathVariable("ids") String ids, @PathVariable("state") Integer state) {
        GatewayBean gatewayBean = new GatewayBean();
        gatewayBean.setRouteId(ids);
        gatewayBean.setUpdateTime(new Date());
        gatewayBean.setIsEbl(state);
        gatewayService.updateRoutes(gatewayBean);
        return MessageBulider.success("路由状态更新成功！");

    }
}
