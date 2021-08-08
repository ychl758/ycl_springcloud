package com.ycl.gateway.common;

/**
 * @author:yuchenglin
 * @description：构建返回参数
 * @date:2021/7/30
 * @time: 10:20
 */
public class MessageBulider {

    public static <T> ReturnMsg<T> success(String msg, T data) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(0);
            setMsg(msg);
            setData(data);
            setExtra(null);
        }};
        return meaasge;
    }

    public static <T> ReturnMsg<T> success(String msg, T data, String extra) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(0);
            setMsg(msg);
            setData(data);
            setExtra(extra);
        }};
        return meaasge;
    }

    public static <T> ReturnMsg<T> success(String msg, int code, T data) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(code);
            setMsg(msg);
            setData(data);
            setExtra(null);
        }};
        return meaasge;
    }

    public static <T> ReturnMsg<T> success(String msg, int code) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(code);
            setMsg(msg);
            setExtra(null);
        }};
        return meaasge;
    }


    public static <T> ReturnMsg<T> success(String msg) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(0);
            setMsg(msg);
            setExtra(null);
        }};
        return meaasge;
    }


    public static <T> ReturnMsg<T> failed(String msg) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(500);
            setMsg(msg);
            setExtra(null);
        }};
        return meaasge;
    }

    public static <T> ReturnMsg<T> failed(String msg, int code) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(code);
            setMsg(msg);
            setExtra(null);
        }};
        return meaasge;
    }

    public static <T> ReturnMsg<T> failed(String msg, T t) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(500);
            setMsg(msg);
            setData(t);
            setExtra(null);
        }};
        return meaasge;
    }


    public static <T> ReturnMsg<T> failed(String msg, int code, T t) {
        ReturnMsg<T> meaasge = new ReturnMsg() {{
            setCode(code);
            setMsg(msg);
            setExtra(null);
            setData(t);
        }};
        return meaasge;
    }


}
