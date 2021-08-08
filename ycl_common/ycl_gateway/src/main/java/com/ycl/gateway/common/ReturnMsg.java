package com.ycl.gateway.common;

import lombok.Data;

/**
 * @author:yuchenglin
 * @description：构建返回参数
 * @date:2021/7/30
 * @time: 10:20
 */
@Data
public class ReturnMsg<T> {
    private int code;
    private String msg;
    private T data;
    /**
     * 该字段是为了满足平台前端架构
     **/
    private String extra;

    public ReturnMsg() {
    }

    public ReturnMsg(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ReturnMsg(int code, String msg, T data, String extra) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.extra = extra;
    }

    public ReturnMsg(int code, String msg, String extra) {
        this.code = code;
        this.msg = msg;
        this.extra = extra;
    }
    public ReturnMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
