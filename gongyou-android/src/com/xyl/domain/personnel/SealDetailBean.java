package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class SealDetailBean implements Serializable {

    /**
     * renShiId : 9
     * renShiType : 14
     * renShiTypeDisplay : 用印申请
     * createTime : 2019-09-04
     * yongYinShenQingId : 1
     * yongYinDW : 测试单位
     * dateTime : 2019-09-04
     * doneTime :
     * yinZhangMC : 测试
     * yongYinWJMC : 测试赛
     * wenJianFS : 2
     * wenjianLB : null
     * wenjianLBDisplay : 其他
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
    private int yongYinShenQingId;
    private String yongYinDW;
    private String dateTime;
    private String doneTime;
    private String yinZhangMC;
    private String yongYinWJMC;
    private int wenJianFS;
    private Object wenjianLB;
    private String wenjianLBDisplay;
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

    public int getYongYinShenQingId() {
        return yongYinShenQingId;
    }

    public void setYongYinShenQingId(int yongYinShenQingId) {
        this.yongYinShenQingId = yongYinShenQingId;
    }

    public String getYongYinDW() {
        return yongYinDW;
    }

    public void setYongYinDW(String yongYinDW) {
        this.yongYinDW = yongYinDW;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getYinZhangMC() {
        return yinZhangMC;
    }

    public void setYinZhangMC(String yinZhangMC) {
        this.yinZhangMC = yinZhangMC;
    }

    public String getYongYinWJMC() {
        return yongYinWJMC;
    }

    public void setYongYinWJMC(String yongYinWJMC) {
        this.yongYinWJMC = yongYinWJMC;
    }

    public int getWenJianFS() {
        return wenJianFS;
    }

    public void setWenJianFS(int wenJianFS) {
        this.wenJianFS = wenJianFS;
    }

    public Object getWenjianLB() {
        return wenjianLB;
    }

    public void setWenjianLB(Object wenjianLB) {
        this.wenjianLB = wenjianLB;
    }

    public String getWenjianLBDisplay() {
        return wenjianLBDisplay;
    }

    public void setWenjianLBDisplay(String wenjianLBDisplay) {
        this.wenjianLBDisplay = wenjianLBDisplay;
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
