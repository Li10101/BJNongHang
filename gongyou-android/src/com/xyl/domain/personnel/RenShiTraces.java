package com.xyl.domain.personnel;

import java.io.Serializable;

public class RenShiTraces implements Serializable {

    /**
     * traceId : 44
     * staffCode : nl_lizhenp
     * staffName : 李振平
     * action : 新增请假单
     * status : 新增
     * executeTime : 2019-10-08 18:40:50
     * actionDisplay : 未知
     * statusDisplay : 未知
     */

    private String traceId;
    private String staffCode;
    private String staffName;
    private String action;
    private String status;
    private String executeTime;
    private String actionDisplay;
    private String statusDisplay;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getActionDisplay() {
        return actionDisplay;
    }

    public void setActionDisplay(String actionDisplay) {
        this.actionDisplay = actionDisplay;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }
}
