package com.yhbc.base.net.token.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * {@link TokenBean}外面包了一层
 *
 * @author xuhaijiang on 2018/9/25.
 */
public class TokenData {

    @JSONField(name = "login_resp_to")
    private TokenBean tokenBean;

    @JSONField(name = "migrating_account_res_to")
    private H5Bean h5Bean;

    @JSONField(name = "status")
    private Integer status;


    public TokenBean getTokenBean() {
        return tokenBean;
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public H5Bean getH5Bean() {
        return h5Bean;
    }

    public void setH5Bean(H5Bean h5Bean) {
        this.h5Bean = h5Bean;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
