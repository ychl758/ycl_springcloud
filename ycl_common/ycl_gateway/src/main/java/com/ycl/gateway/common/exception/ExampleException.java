package com.ycl.gateway.common.exception;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 自定义异常
 *
 * @author yuchenglin
 * @date 2020/10/13
 */
@Data
public class ExampleException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleException.class);
    private static final long serialVersionUID = -6979901566637669960L;

    private ExceptionCodeEnum exceptionCodeEnum;

    private int code;

    private String message;


    public ExampleException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ExampleException(ExceptionCodeEnum exceptionCodeEnum) {
        super(exceptionCodeEnum.message);
        this.exceptionCodeEnum = exceptionCodeEnum;
        this.code = exceptionCodeEnum.code;
        this.message = exceptionCodeEnum.message;
    }

    public ExampleException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

    public ExampleException(ExceptionCodeEnum exceptionCodeEnum, Throwable throwable) {
        super(exceptionCodeEnum.message, throwable);
        this.exceptionCodeEnum = exceptionCodeEnum;
    }


    /**
     * 重写打印异常
     */
    @Override
    public void printStackTrace() {
        if (exceptionCodeEnum != null) {
            LOGGER.info("异常代码: {}, 异常信息: {}", exceptionCodeEnum.code, exceptionCodeEnum.message);
            return;
        }
        LOGGER.info("异常代码: {}, 异常信息: {}", code, message);
    }

}

