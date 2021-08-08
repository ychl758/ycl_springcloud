package com.ycl.gateway.controller;


import com.ycl.gateway.common.MessageBulider;
import com.ycl.gateway.common.ReturnMsg;
import com.ycl.gateway.service.GatewayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: 网关控制器，原则上不允许直接操作路由表中的路由，只能先操作数据库中的数据，然后刷新路由表
 * @date: 2021/7/30 16:32
 */
@RestController
@Api(tags = "路由表操作")
public class GatewayController {

    private final String mappinfPath = "/api/routes";

    @Autowired
    GatewayService gatewayService;

    /**
     * 刷新路由信息，从数据库中刷新到路由信息中
     *
     * @return 成功与否
     */
    @ApiOperation("刷新路由，从数据库刷新到路由表，原则是不支持直接操作路由表")
    @GetMapping(value = mappinfPath + "/refresh")
    public ReturnMsg refreshRoutes() {
        boolean b = gatewayService.refreshRoutes();
        if (b == true) {
            return MessageBulider.success("刷新路由成功", HttpStatus.OK);
        } else {
            return MessageBulider.failed("刷新路由失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
