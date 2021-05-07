package com.xyl.domain;

import com.xyl.base.BaseBean;

/**
 * Created by 47500 on 2018/1/16.
 */

public class AssetBean extends BaseBean {

    /**
     * building : {"buildingId":3,"buildingName":"农行南楼"}
     * physicalAssetCode : 47
     * physicalAssetId : 106
     * physicalAssetImg : static/images/icon/1@user.png
     * physicalAssetName : 配电设备
     * physicalAssetOrder : 1
     */

    private BuildingBean building;
    private String physicalAssetCode;
    private int physicalAssetId;
    private String physicalAssetImg;
    private String physicalAssetName;
    private String physicalAssetOrder;

    public BuildingBean getBuilding() {
        return building;
    }

    public void setBuilding(BuildingBean building) {
        this.building = building;
    }

    public String getPhysicalAssetCode() {
        return physicalAssetCode;
    }

    public void setPhysicalAssetCode(String physicalAssetCode) {
        this.physicalAssetCode = physicalAssetCode;
    }

    public int getPhysicalAssetId() {
        return physicalAssetId;
    }

    public void setPhysicalAssetId(int physicalAssetId) {
        this.physicalAssetId = physicalAssetId;
    }

    public String getPhysicalAssetImg() {
        return physicalAssetImg;
    }

    public void setPhysicalAssetImg(String physicalAssetImg) {
        this.physicalAssetImg = physicalAssetImg;
    }

    public String getPhysicalAssetName() {
        return physicalAssetName;
    }

    public void setPhysicalAssetName(String physicalAssetName) {
        this.physicalAssetName = physicalAssetName;
    }

    public String getPhysicalAssetOrder() {
        return physicalAssetOrder;
    }

    public void setPhysicalAssetOrder(String physicalAssetOrder) {
        this.physicalAssetOrder = physicalAssetOrder;
    }

    public static class BuildingBean extends BaseBean{
        /**
         * buildingId : 3
         * buildingName : 农行南楼
         */

        private int buildingId;
        private String buildingName;

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
}
