package com.yhbc.base.net;

/**
 * url枚举
 * Created by xuhaijiang on 2018/4/17.
 */
public enum UrlEnum {

    /**
     * 登录
     */
    SCM_SYSTEM("http://172.16.8.15:8080/v1/scm-system/","http://test.blibao.com:10080/api/v1/scm-system/","","https://gnway.blibao.com/v1/scm-system/"),

    /**
     * Token
     */
    TOKEN("http://172.16.8.15:8080/v1/scm-system/", "http://test.blibao.com:10080/api/v1/scm-system/", "", "https://gnway.blibao.com/v1/scm-system/");

    private String _15;
    private String test;
    private String pre;
    private String cloud;

    /**
     * @param _15   13环境
     * @param test  测试
     * @param pre   预发布
     * @param cloud 云端
     */

    UrlEnum(String _15, String test, String pre, String cloud) {
        this._15 = _15;
        this.test = test;
        this.pre = pre;
        this.cloud = cloud;
    }

    public void setUrl(String _15, String test, String pre, String cloud) {
        this._15 = _15;
        this.test = test;
        this.pre = pre;
        this.cloud = cloud;
    }

    public final static int TYPE_15 = 0;
    public final static int TYPE_TEST = 1;
    public final static int TYPE_PRE = 2;
    public final static int TYPE_CLOUD = 3;
    /**
     * 当前环境 0:15;1:test;2:pre;3:cloud
     */
    public static int environment = TYPE_CLOUD;

    /**
     * @return 相应环境的url
     */
    public String getUrl() {
        return environment == TYPE_15 ? _15 : (environment == TYPE_TEST ? test : (environment == TYPE_PRE ? pre : cloud));
//        return environment == TYPE_TEST ? test : (environment == TYPE_PRE ? pre : cloud);
    }
}
