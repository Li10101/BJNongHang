package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class StandbyDetailBean implements Serializable {

    /**
     * renShiId : 10
     * renShiType : 16
     * renShiTypeDisplay : 备用金申请
     * createTime : 2019-09-04
     * beiYongJinShenQingId : 1
     * shiYou : 测试事由
     * shenQingJE : 100.00
     * shiYongSJ : 2019-09-04
     * guiHuanSJ : 2019-09-06
     * doneTime :
     * chuNa : 测试出纳
     * description : 测试
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
    private int beiYongJinShenQingId;
    private String shiYou;
    private String shenQingJE;
    private String shiYongSJ;
    private String guiHuanSJ;
    private String doneTime;
    private String chuNa;
    private String description;
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

    private String shenPiRen;
    public String getShenPiRen() {
        return shenPiRen;
    }

    public void setShenPiRen(String shenPiRen) {
        this.shenPiRen = shenPiRen;
    }

    public void setRenShiTraces(List<RenShiTraces> renShiTraces) {
        this.renShiTraces = renShiTraces;
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

    public int getBeiYongJinShenQingId() {
        return beiYongJinShenQingId;
    }

    public void setBeiYongJinShenQingId(int beiYongJinShenQingId) {
        this.beiYongJinShenQingId = beiYongJinShenQingId;
    }

    public String getShiYou() {
        return shiYou;
    }

    public void setShiYou(String shiYou) {
        this.shiYou = shiYou;
    }

    public String getShenQingJE() {
        return shenQingJE;
    }

    public void setShenQingJE(String shenQingJE) {
        this.shenQingJE = shenQingJE;
    }

    public String getShiYongSJ() {
        return shiYongSJ;
    }

    public void setShiYongSJ(String shiYongSJ) {
        this.shiYongSJ = shiYongSJ;
    }

    public String getGuiHuanSJ() {
        return guiHuanSJ;
    }

    public void setGuiHuanSJ(String guiHuanSJ) {
        this.guiHuanSJ = guiHuanSJ;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getChuNa() {
        return chuNa;
    }

    public void setChuNa(String chuNa) {
        this.chuNa = chuNa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
