package com.yhbc.base.framework.exception;

/**
 * Created by chengwen on 2016/7/28.
 */
public final class ApiException extends RuntimeException {

    private final String msg;

    public ApiException(String msg){
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
