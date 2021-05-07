package com.xyl.domain.answer;

import java.io.Serializable;

public class DaTiRenDataBean implements Serializable {
    private Integer daTiRenListId;//生成题Id
    private Integer daTiId;//考的试卷Id
    private String daTiName;//考的试卷名称
    private Double fenShu;//当前得分
    private Double maxFenShu;//当前最高分
    private Integer zhengQueSL;//正确数量
    private Integer cuoWuSL;//错误数量
    private String dateTime;//最后答题时间
    private Integer staffId;
    private String staffName;//答题人名称
    private String buildingName;//所属项目

    public Integer getDaTiRenListId() {
        return daTiRenListId;
    }

    public void setDaTiRenListId(Integer daTiRenListId) {
        this.daTiRenListId = daTiRenListId;
    }

    public Integer getDaTiId() {
        return daTiId;
    }

    public void setDaTiId(Integer daTiId) {
        this.daTiId = daTiId;
    }

    public String getDaTiName() {
        return daTiName;
    }

    public void setDaTiName(String daTiName) {
        this.daTiName = daTiName;
    }

    public Double getFenShu() {
        return fenShu;
    }

    public void setFenShu(Double fenShu) {
        this.fenShu = fenShu;
    }

    public Double getMaxFenShu() {
        return maxFenShu;
    }

    public void setMaxFenShu(Double maxFenShu) {
        this.maxFenShu = maxFenShu;
    }

    public Integer getZhengQueSL() {
        return zhengQueSL;
    }

    public void setZhengQueSL(Integer zhengQueSL) {
        this.zhengQueSL = zhengQueSL;
    }

    public Integer getCuoWuSL() {
        return cuoWuSL;
    }

    public void setCuoWuSL(Integer cuoWuSL) {
        this.cuoWuSL = cuoWuSL;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
