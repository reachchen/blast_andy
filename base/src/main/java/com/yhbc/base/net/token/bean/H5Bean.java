package com.yhbc.base.net.token.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * H5使用的数据，不用去管
 *
 * @author xuhaijiang on 2018/9/25.
 */
public class H5Bean {

    @JSONField(name = "employee_id")
    private Long employeeId;

    @JSONField(name = "opt_code")
    private Integer optCode;

    @JSONField(name = "username")
    private String username;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getOptCode() {
        return optCode;
    }

    public void setOptCode(Integer optCode) {
        this.optCode = optCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
