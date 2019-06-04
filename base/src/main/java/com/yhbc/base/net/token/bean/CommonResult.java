package com.yhbc.base.net.token.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * token 的返回结果
 * @author xuhaijiang on 2018/9/18.
 */
public class CommonResult<T> {

    /**
     * {@link #code}的成功标志
     */
    public static final int CODE_SUCCESS = 10000;

    @JSONField(name = "code")
    private String code;

    @JSONField(name = "msg")
    private String msg;

    @JSONField(name = "sub_msg")
    private String subMsg;

    @JSONField(name = "data")
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
