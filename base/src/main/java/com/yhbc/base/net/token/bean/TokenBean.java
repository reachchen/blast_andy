package com.yhbc.base.net.token.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * token 数据
 * @author xuhaijiang on 2018/9/21.
 */
public class TokenBean {

    @JSONField(name = "dependable")
    private Boolean dependable;

    @JSONField(name = "expire_time")
    private Long expireTime;

    @JSONField(name = "first_login")
    private Long firstLogin;

    @JSONField(name = "last_login_time")
    private String lastLoginTime;

    @JSONField(name = "refresh_token")
    private String refreshoken;

    @JSONField(name = "token")
    private String token;

    @JSONField(name = "source")
    private String source;

    @JSONField(name = "username")
    private String userName;

    public Boolean getDependable() {
        return dependable;
    }

    public void setDependable(Boolean dependable) {
        this.dependable = dependable;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Long firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRefreshoken() {
        return refreshoken;
    }

    public void setRefreshoken(String refreshoken) {
        this.refreshoken = refreshoken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TokenBean{" +
                "dependable=" + dependable +
                ", expireTime=" + expireTime +
                ", firstLogin=" + firstLogin +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", refreshoken='" + refreshoken + '\'' +
                ", token='" + token + '\'' +
                ", source=" + source +
                ", userName='" + userName + '\'' +
                '}';
    }
}
