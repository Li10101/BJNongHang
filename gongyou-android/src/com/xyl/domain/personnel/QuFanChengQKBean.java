package com.xyl.domain.personnel;

import java.io.Serializable;

public class QuFanChengQKBean implements Serializable {
    private Integer quFanChengQKId;//去返程情况ID（修改时需要传的，新增必为空）
    private String quChengSJ;//去程时间 （时间控件）
    private String muDiD;//目的地（省、市）（填写项，不是选择项）
    private String shiFouJHBZZ;//是否经湖北中转（单选按钮，但是需要提交中文 是/否）
    private String fanChengSJ;//返程时间（时间控件）
    private String chuFaD;//出发地（省、市）（填写项，不是选择项）
    private Integer fanChengType;//返程交通工具 飞机(1), 火车(2), 自驾(3), 其他(4)（提交数值）
    private String fanChengTypeDisplay;//返程交通工具
    private String fanChengCPH;//返程车牌号
    private String shiFouJTHB;//是否经停湖北（单选按钮，但是需要提交中文 是/否）
    private String jingTingHBHD;//经停湖北何地
    private String jingTingSJ;//经停时间（时间控件）
    private String dateTime;//申请时间 （用户可选择的时间）
    private Integer shenQingRId;//申请人Id（保存时，传递这个即可）
    private String shenQingRName;
    private Integer staffId;
    private String staffName;//创建人（只用作展示，不用提交）
    private Integer buildingId;
    private String buildingName;//楼宇（只用作展示，不用提交）

    public Integer getQuFanChengQKId() {
        return quFanChengQKId;
    }

    public void setQuFanChengQKId(Integer quFanChengQKId) {
        this.quFanChengQKId = quFanChengQKId;
    }

    public String getQuChengSJ() {
        return quChengSJ;
    }

    public void setQuChengSJ(String quChengSJ) {
        this.quChengSJ = quChengSJ;
    }

    public String getMuDiD() {
        return muDiD;
    }

    public void setMuDiD(String muDiD) {
        this.muDiD = muDiD;
    }

    public String getShiFouJHBZZ() {
        return shiFouJHBZZ;
    }

    public void setShiFouJHBZZ(String shiFouJHBZZ) {
        this.shiFouJHBZZ = shiFouJHBZZ;
    }

    public String getFanChengSJ() {
        return fanChengSJ;
    }

    public void setFanChengSJ(String fanChengSJ) {
        this.fanChengSJ = fanChengSJ;
    }

    public String getChuFaD() {
        return chuFaD;
    }

    public void setChuFaD(String chuFaD) {
        this.chuFaD = chuFaD;
    }

    public Integer getFanChengType() {
        return fanChengType;
    }

    public void setFanChengType(Integer fanChengType) {
        this.fanChengType = fanChengType;
    }

    public String getFanChengTypeDisplay() {
        return fanChengTypeDisplay;
    }

    public void setFanChengTypeDisplay(String fanChengTypeDisplay) {
        this.fanChengTypeDisplay = fanChengTypeDisplay;
    }

    public String getFanChengCPH() {
        return fanChengCPH;
    }

    public void setFanChengCPH(String fanChengCPH) {
        this.fanChengCPH = fanChengCPH;
    }

    public String getShiFouJTHB() {
        return shiFouJTHB;
    }

    public void setShiFouJTHB(String shiFouJTHB) {
        this.shiFouJTHB = shiFouJTHB;
    }

    public String getJingTingHBHD() {
        return jingTingHBHD;
    }

    public void setJingTingHBHD(String jingTingHBHD) {
        this.jingTingHBHD = jingTingHBHD;
    }

    public String getJingTingSJ() {
        return jingTingSJ;
    }

    public void setJingTingSJ(String jingTingSJ) {
        this.jingTingSJ = jingTingSJ;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getShenQingRId() {
        return shenQingRId;
    }

    public void setShenQingRId(Integer shenQingRId) {
        this.shenQingRId = shenQingRId;
    }

    public String getShenQingRName() {
        return shenQingRName;
    }

    public void setShenQingRName(String shenQingRName) {
        this.shenQingRName = shenQingRName;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
