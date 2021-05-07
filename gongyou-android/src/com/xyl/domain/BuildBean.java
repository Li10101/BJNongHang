package com.xyl.domain;

import java.io.Serializable;

public class BuildBean implements Serializable {

    /**
     * buildingId : 1
     * buildingName : 全部
     * buildingCode : null
     * pushEmail : null
     */

    private int buildingId;
    private String buildingName;
    private String buildingCode;
    private String pushEmail;

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Object getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Object getPushEmail() {
        return pushEmail;
    }

    public void setPushEmail(String pushEmail) {
        this.pushEmail = pushEmail;
    }
}
