package com.ycl.gateway.common.exception;

import lombok.AllArgsConstructor;

/**
 * 基础异常类
 *
 * @author yuchenglin
 * @date 2020/10/13
 */
@AllArgsConstructor
public enum ExceptionCodeEnum {
    Dta_Input(900, "输入数据不符合规范"),
    Dta_InputAready(650, "数据已存在"),
    Dta_Exception(600, "数据操作错误"),
    Data_NoFound(601, "数据不存在"),
    Data_Insert(602, "数据插入失败"),
    Data_Update(603, "数据更新失败"),
    Data_Delete(604, "删除数据失败"),
    Data_FindAll(605, "查询数据失败或者为空"),
    Data_Insert_NullList(606, "需要插入的List集合为空！"),
    Route_Release(700,"路由发布失败"),
    Route_Exception(701,"服务器内部错误，发布了不符合规范格式的路由"),
    Route_Exception_Rredicates(703,"刷新路由失败，存在错误的断言规则"),
    Route_Exception_Filters(704,"刷新路由失败，存在错误的滤器集合"),
    Success(0, "成功");
    public final  int code;
    public final  String message;


}

