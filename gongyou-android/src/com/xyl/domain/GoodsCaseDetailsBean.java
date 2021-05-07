package com.xyl.domain;

import java.io.Serializable;

public class GoodsCaseDetailsBean implements Serializable {

    /**
     * goodsCaseDetailsId : 1
     * name : 测试商品
     * pictureUrl :
     * category : 测试
     * unit :
     * unitPrice : 100
     * amount : 1
     * buildingId : 3
     */

    private int goodsCaseDetailsId;
    private Integer goodsId;
    private String name;
    private String pictureUrl;
    private String category;
    private String unit;
    private String typeDisplay;
    private String status;
    private String unitPrice;
    private int type;
    private int shiLing;
    private int amount;
    private int buildingId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsCaseDetailsId() {
        return goodsCaseDetailsId;
    }

    public void setGoodsCaseDetailsId(int goodsCaseDetailsId) {
        this.goodsCaseDetailsId = goodsCaseDetailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
}
