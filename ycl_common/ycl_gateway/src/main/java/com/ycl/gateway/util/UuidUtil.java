package com.ycl.gateway.util;

import java.util.UUID;

/**
 * @version 1.0.0
 * @author: yuchenglin
 * @description: uuid生成器
 * @date: 2021/8/5 13:51
 */
public class UuidUtil {

    public static String get32UUid() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
