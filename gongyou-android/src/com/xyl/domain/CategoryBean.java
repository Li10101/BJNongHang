package com.xyl.domain;

import java.io.Serializable;

/**
 * Created by 47500 on 2018/5/21.
 */

public class CategoryBean implements Serializable {

    /**
     * goodsCategoryId : 1
     * name : 电器
     * icon :
     * order :
     * description :
     * buildingId : null
     * buildingName : 中关村店
     */

    private int goodsCategoryId;
    private String name;
    private String icon;
    private String order;
    private String description;
    private Object buildingId;
    private String buildingName;
    private boolean takeStock;
    private String takeStockDisplay;
    private String processUrl;

    public String getProcessUrl() {
        return processUrl;
    }

    public void setProcessUrl(String processUrl) {
        this.processUrl = processUrl;
    }

    public boolean isTakeStock() {
        return takeStock;
    }

    public void setTakeStock(boolean takeStock) {
        this.takeStock = takeStock;
    }

    public String getTakeStockDisplay() {
        return takeStockDisplay;
    }

    public void setTakeStockDisplay(String takeStockDisplay) {
        this.takeStockDisplay = takeStockDisplay;
    }

    public int getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(int goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Object buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}
