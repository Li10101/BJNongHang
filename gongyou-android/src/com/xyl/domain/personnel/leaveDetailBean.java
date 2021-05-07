package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class leaveDetailBean implements Serializable {

    /**
     * renShiId : 22
     * renShiType : 5
     * renShiTypeDisplay : 请假
     * createTime : 2019-09-17
     * qingJiaId : 3
     * jinJiCD : 0
     * qingJiaLX : 0
     * qingJiaLXDisplay : 事假
     * startTime : 2019-09-17
     * endTime : 2019-09-17
     * doneTime :
     * tianShu : 222.0
     * shiYou : hhj
     * shenQingRenId : 196
     * shenQingRenName : 李振平
     * status : 0
     * statusDisplay : 申请中
     * buildingId : 3
     * buildingName : 农银大厦南楼
     */

    private int renShiId;
    private int renShiType;
    private String renShiTypeDisplay;
    private String createTime;
    private int qingJiaId;
    private int jinJiCD;
    private int qingJiaLX;
    private String qingJiaLXDisplay;
    private String startTime;
    private String endTime;
    private String doneTime;
    private String tianShu;
    private String shiYou;
    private int shenQingRenId;
    private String shenQingRenName;
    private int status;
    private String statusDisplay;
    private int buildingId;
    private String buildingName;
    private String shenPiRen;

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

    public int getQingJiaId() {
        return qingJiaId;
    }

    public void setQingJiaId(int qingJiaId) {
        this.qingJiaId = qingJiaId;
    }

    public int getJinJiCD() {
        return jinJiCD;
    }

    public void setJinJiCD(int jinJiCD) {
        this.jinJiCD = jinJiCD;
    }

    public int getQingJiaLX() {
        return qingJiaLX;
    }

    public void setQingJiaLX(int qingJiaLX) {
        this.qingJiaLX = qingJiaLX;
    }

    public String getQingJiaLXDisplay() {
        return qingJiaLXDisplay;
    }

    public void setQingJiaLXDisplay(String qingJiaLXDisplay) {
        this.qingJiaLXDisplay = qingJiaLXDisplay;
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

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getTianShu() {
        return tianShu;
    }

    public void setTianShu(String tianShu) {
        this.tianShu = tianShu;
    }

    public String getShiYou() {
        return shiYou;
    }

    public void setShiYou(String shiYou) {
        this.shiYou = shiYou;
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
