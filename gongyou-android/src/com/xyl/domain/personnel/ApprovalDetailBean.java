package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class ApprovalDetailBean implements Serializable {

    /**
     * renShiId : 5
     * renShiType : 12
     * renShiTypeDisplay : 通用审批
     * createTime : 2019-08-31
     * tongYongShenPiId : 1
     * shenQingNR : 测试通用审批
     * shenPiXQ : 测试通用审批详情
     * doneTime :
     * shenQingRenId : 347
     * shenQingRenName : 测试库管
     * status : 0
     * statusDisplay : 申请中
     * buildingId : 3
     * buildingName : 农银大厦南楼
     */

    private int renShiId;
    private int renShiType;
    private String renShiTypeDisplay;
    private String createTime;
    private int tongYongShenPiId;
    private String shenQingNR;
    private String shenPiXQ;
    private String doneTime;
    private int shenQingRenId;
    private String shenQingRenName;
    private int status;
    private String statusDisplay;
    private int buildingId;
    private String buildingName;

    private List<RenShiPictures> renShiPictures;



    public List<RenShiPictures> getRenShiPictures() {
        return renShiPictures;
    }

    public void setRenShiPictures(List<RenShiPictures> renShiPictures) {
        this.renShiPictures = renShiPictures;
    }

    private List<RenShiTraces> renShiTraces;
    public List<RenShiTraces> getRenShiTraces() {
        return renShiTraces;
    }

    public void setRenShiTraces(List<RenShiTraces> renShiTraces) {
        this.renShiTraces = renShiTraces;
    }
    private String shenPiRen;
    public String getShenPiRen() {
        return shenPiRen;
    }

    public void setShenPiRen(String shenPiRen) {
        this.shenPiRen = shenPiRen;
    }

    public int getRenShiId() {
        return renShiId;
    }

    public void setRenShiId(int renShiId) {
        this.renShiId = renShiId;
    }

    public int getRenShiType() {
        return renShiType;
    }

    public void setRenShiType(int renShiType) {
        this.renShiType = renShiType;
    }

    public String getRenShiTypeDisplay() {
        return renShiTypeDisplay;
    }

    public void setRenShiTypeDisplay(String renShiTypeDisplay) {
        this.renShiTypeDisplay = renShiTypeDisplay;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getTongYongShenPiId() {
        return tongYongShenPiId;
    }

    public void setTongYongShenPiId(int tongYongShenPiId) {
        this.tongYongShenPiId = tongYongShenPiId;
    }

    public String getShenQingNR() {
        return shenQingNR;
    }

    public void setShenQingNR(String shenQingNR) {
        this.shenQingNR = shenQingNR;
    }

    public String getShenPiXQ() {
        return shenPiXQ;
    }

    public void setShenPiXQ(String shenPiXQ) {
        this.shenPiXQ = shenPiXQ;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public int getShenQingRenId() {
        return shenQingRenId;
    }

    public void setShenQingRenId(int shenQingRenId) {
        this.shenQingRenId = shenQingRenId;
    }

    public String getShenQingRenName() {
        return shenQingRenName;
    }

    public void setShenQingRenName(String shenQingRenName) {
        this.shenQingRenName = shenQingRenName;
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
}
