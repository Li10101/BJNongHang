package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class EmergencyDetailBean implements Serializable {

    /**
     * renShiId : 26
     * renShiType : 10
     * renShiTypeDisplay : 应急采购
     * createTime : 2019-09-19
     * caiGouId : 3
     * shiYou :
     * caiGouLX : -1
     * caiGouLXDisplay : 其他
     * qiWangJFRQ :
     * zhiFuFS : -1
     * zhiFuFSDisplay : 其他
     * description :
     * doneTime : null
     * caiGouMingXis : [{"caiGouMingXiId":5,"name":"庆的","guiGe":"搜狗","number":"646.0","danWei":"","money":"664.00","zongJia":"428944.0","yongTu":"敏敏"}]
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
    private int caiGouId;
    private String shiYou;
    private int caiGouLX;
    private String caiGouLXDisplay;
    private String qiWangJFRQ;
    private int zhiFuFS;
    private String zhiFuFSDisplay;
    private String description;
    private Object doneTime;
    private int shenQingRenId;
    private String shenQingRenName;
    private int status;
    private String statusDisplay;
    private int buildingId;
    private String buildingName;
    private List<ProcurementDetails> caiGouMingXis;

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

    public int getCaiGouId() {
        return caiGouId;
    }

    public void setCaiGouId(int caiGouId) {
        this.caiGouId = caiGouId;
    }

    public String getShiYou() {
        return shiYou;
    }

    public void setShiYou(String shiYou) {
        this.shiYou = shiYou;
    }

    public int getCaiGouLX() {
        return caiGouLX;
    }

    public void setCaiGouLX(int caiGouLX) {
        this.caiGouLX = caiGouLX;
    }

    public String getCaiGouLXDisplay() {
        return caiGouLXDisplay;
    }

    public void setCaiGouLXDisplay(String caiGouLXDisplay) {
        this.caiGouLXDisplay = caiGouLXDisplay;
    }

    public String getQiWangJFRQ() {
        return qiWangJFRQ;
    }

    public void setQiWangJFRQ(String qiWangJFRQ) {
        this.qiWangJFRQ = qiWangJFRQ;
    }

    public int getZhiFuFS() {
        return zhiFuFS;
    }

    public void setZhiFuFS(int zhiFuFS) {
        this.zhiFuFS = zhiFuFS;
    }

    public String getZhiFuFSDisplay() {
        return zhiFuFSDisplay;
    }

    public void setZhiFuFSDisplay(String zhiFuFSDisplay) {
        this.zhiFuFSDisplay = zhiFuFSDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Object doneTime) {
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

    public List<ProcurementDetails> getCaiGouMingXis() {
        return caiGouMingXis;
    }

    public void setCaiGouMingXis(List<ProcurementDetails> caiGouMingXis) {
        this.caiGouMingXis = caiGouMingXis;
    }
}
