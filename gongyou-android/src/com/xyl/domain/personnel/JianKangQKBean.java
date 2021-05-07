package com.xyl.domain.personnel;

import java.io.Serializable;

public class JianKangQKBean implements Serializable {

    private Integer jianKangQKId;//健康情况（修改时需要传的，新增必为空）
    private Integer shangXiaW;// 0上午 1下午（单选按钮，提交数值型 0/1）
    private String shangXiaWDisplay;// 0上午 1下午
    private Integer xingShi;// 0现场检测  1电话沟通   -1其他 （提交数值，当选择其他的时候，弹出一个新的可填写的录入控件，把填写的内容，放在xingShiStr字段里）
    private String xingShiDisplay;// 现场检测  电话沟通   其他
    private String xingShiStr;// 现场检测  电话沟通   其他 （提交中文）
    private String tiWenSFZC;//体温是否正常 （单选按钮，但是需要提交中文 是/否）
    private String juTiWD;//具体温度
    private Integer muQianZT;//1.应急值守2.居家观察3.医院就诊4.其他 (写死的可选项)
    private String muQianZTDisplay;//1.应急值守2.居家观察3.医院就诊4.其他
    private String shiFouYZZ;//是否有发烧、咳嗽、感冒等症状 （单选按钮，但是需要提交中文 是/否）
    private String jiaRenSFYZZ;//共同生活家人是否有发热、咳嗽、感冒等症状 （提交中文 是/否）
    private String beiZhu;//备注（是否有需要报告的其他情况）
    private String shiFouJCYQRY;//是否接触疫情高发区人员 （单选按钮，但是需要提交中文 是/否）
    private String shiFouMQJCZ;//是否密切接触者 （单选按钮，但是需要提交中文 是/否）
    private String shiFouYSBL;//是否疑似病例 （单选按钮，但是需要提交中文 是/否）
    private String shiFouQZBL;//是否确诊病例 （单选按钮，但是需要提交中文 是/否）
    private String dateTime;//申请时间 （用户可选择的时间）
    private Integer shenQingRId;//申请人Id（保存时，传递这个即可）
    private String shenQingRName;//申请人姓名
    private Integer staffId;
    private String staffName;//创建人（只用作展示，不用提交）
    private Integer buildingId;
    private String buildingName;//楼宇（只用作展示，不用提交）

    public Integer getJianKangQKId() {
        return jianKangQKId;
    }

    public void setJianKangQKId(Integer jianKangQKId) {
        this.jianKangQKId = jianKangQKId;
    }

    public Integer getShangXiaW() {
        return shangXiaW;
    }

    public void setShangXiaW(Integer shangXiaW) {
        this.shangXiaW = shangXiaW;
    }

    public String getShangXiaWDisplay() {
        return shangXiaWDisplay;
    }

    public void setShangXiaWDisplay(String shangXiaWDisplay) {
        this.shangXiaWDisplay = shangXiaWDisplay;
    }

    public Integer getXingShi() {
        return xingShi;
    }

    public void setXingShi(Integer xingShi) {
        this.xingShi = xingShi;
    }

    public String getXingShiDisplay() {
        return xingShiDisplay;
    }

    public void setXingShiDisplay(String xingShiDisplay) {
        this.xingShiDisplay = xingShiDisplay;
    }

    public String getXingShiStr() {
        return xingShiStr;
    }

    public void setXingShiStr(String xingShiStr) {
        this.xingShiStr = xingShiStr;
    }

    public String getTiWenSFZC() {
        return tiWenSFZC;
    }

    public void setTiWenSFZC(String tiWenSFZC) {
        this.tiWenSFZC = tiWenSFZC;
    }

    public String getJuTiWD() {
        return juTiWD;
    }

    public void setJuTiWD(String juTiWD) {
        this.juTiWD = juTiWD;
    }

    public Integer getMuQianZT() {
        return muQianZT;
    }

    public void setMuQianZT(Integer muQianZT) {
        this.muQianZT = muQianZT;
    }

    public String getMuQianZTDisplay() {
        return muQianZTDisplay;
    }

    public void setMuQianZTDisplay(String muQianZTDisplay) {
        this.muQianZTDisplay = muQianZTDisplay;
    }

    public String getShiFouYZZ() {
        return shiFouYZZ;
    }

    public void setShiFouYZZ(String shiFouYZZ) {
        this.shiFouYZZ = shiFouYZZ;
    }

    public String getJiaRenSFYZZ() {
        return jiaRenSFYZZ;
    }

    public void setJiaRenSFYZZ(String jiaRenSFYZZ) {
        this.jiaRenSFYZZ = jiaRenSFYZZ;
    }

    public String getBeiZhu() {
        return beiZhu;
    }

    public void setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
    }

    public String getShiFouJCYQRY() {
        return shiFouJCYQRY;
    }

    public void setShiFouJCYQRY(String shiFouJCYQRY) {
        this.shiFouJCYQRY = shiFouJCYQRY;
    }

    public String getShiFouMQJCZ() {
        return shiFouMQJCZ;
    }

    public void setShiFouMQJCZ(String shiFouMQJCZ) {
        this.shiFouMQJCZ = shiFouMQJCZ;
    }

    public String getShiFouYSBL() {
        return shiFouYSBL;
    }

    public void setShiFouYSBL(String shiFouYSBL) {
        this.shiFouYSBL = shiFouYSBL;
    }

    public String getShiFouQZBL() {
        return shiFouQZBL;
    }

    public void setShiFouQZBL(String shiFouQZBL) {
        this.shiFouQZBL = shiFouQZBL;
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
