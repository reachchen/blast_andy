package com.yhbc.base.net.token.bean;

import okhttp3.Response;

/**
 * Response的包装类
 *
 * @author xuhaijiang on 2018/9/26.
 */
public class ResponseBean {

    private boolean success;
    private Response response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
