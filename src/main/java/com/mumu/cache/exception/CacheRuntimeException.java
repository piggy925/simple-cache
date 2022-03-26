package com.mumu.cache.exception;

/**
 * 自定义缓存异常
 *
 * @author mumu
 * @date 2022/3/26
 */
public class CacheRuntimeException extends RuntimeException {
    public CacheRuntimeException() {
        super();
    }

    public CacheRuntimeException(String message) {
        super(message);
    }

    public CacheRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheRuntimeException(Throwable cause) {
        super(cause);
    }

    protected CacheRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}