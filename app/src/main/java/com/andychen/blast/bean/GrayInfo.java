package com.andychen.blast.bean;

/**
 * Created by Administrator on 2017/11/2.
 * 灰度发布 bean
 */
public class GrayInfo {


    /**
     * code : 200
     * data : {"clientName":"简易付(专业版)","forceUpdate":"0","isUpdate":"1","url":"https://cloud.blibao.com/server/module/shop/","version":"1.0.2.26 "}
     * msg : 更新
     * success : true
     */

    private int code;
    /**
     * clientName : 简易付(专业版)
     * forceUpdate : 0
     * isUpdate : 1
     * url : https://cloud.blibao.com/server/module/shop/
     * version : 1.0.2.26
     */

    private Data data;
    private String msg;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class Data {
        private String clientName;
        private String forceUpdate;
        private String isUpdate;
        private String url;
        private String version;

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(String forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        public String getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(String isUpdate) {
            this.isUpdate = isUpdate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
