package com.xyl.domain;

import java.io.Serializable;

public class NoticeDateBean implements Serializable {
    /**
     * cmsId : 1
     * biaoTi : 测试文章
     * type : 2
     * typeDisplay : 文章
     * status : 1
     * statusDisplay : 已发布
     * startTime : 2020-06-17
     * endTime : 2020-08-31
     * createTime : 2020-06-17
     * staffId : 43
     * staffName : 詹志勇
     * showBuildingId : 2
     * showBuildingName : 展览路物业项目
     * buildingId : 5
     * buildingName : 中心
     */

    private int cmsId;
    private String biaoTi;
    private int type;
    private String typeDisplay;
    private int status;
    private String statusDisplay;
    private String startTime;
    private String endTime;
    private String createTime;
    private int staffId;
    private String staffName;
    private int showBuildingId;
    private String showBuildingName;
    private int buildingId;
    private String buildingName;
    private String photo;
    private Integer chaKan;
    private boolean isSee;

    public boolean isSee() {
        return isSee;
    }

    public void setSee(boolean see) {
        isSee = see;
    }

    public Integer getChaKan() {
        return chaKan;
    }

    public void setChaKan(Integer chaKan) {
        this.chaKan = chaKan;
    }

    public int getCmsId() {
        return cmsId;
    }

    public void setCmsId(int cmsId) {
        this.cmsId = cmsId;
    }

    public String getBiaoTi() {
        return biaoTi;
    }

    public void setBiaoTi(String biaoTi) {
        this.biaoTi = biaoTi;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getShowBuildingId() {
        return showBuildingId;
    }

    public void setShowBuildingId(int showBuildingId) {
        this.showBuildingId = showBuildingId;
    }

    public String getShowBuildingName() {
        return showBuildingName;
    }

    public void setShowBuildingName(String showBuildingName) {
        this.showBuildingName = showBuildingName;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
