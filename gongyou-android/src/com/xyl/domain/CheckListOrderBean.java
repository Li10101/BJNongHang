package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 47500 on 2018/5/25.
 */

public class CheckListOrderBean implements Serializable {


    private int stockTakingId;
    private int personId;
    private String personName;
    private String personPhone;
    private String dateTime;
    private int buildingId;
    private String buildingName;
    private boolean remove;
    private List<?> stockTakingPictureListVos;
    private List<StockTakingDetailsVosBean> stockTakingDetailsVos;
    private List<?> stockTakingDescriptionVos;

    public int getStockTakingId() {
        return stockTakingId;
    }

    public void setStockTakingId(int stockTakingId) {
        this.stockTakingId = stockTakingId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public List<?> getStockTakingPictureListVos() {
        return stockTakingPictureListVos;
    }

    public void setStockTakingPictureListVos(List<?> stockTakingPictureListVos) {
        this.stockTakingPictureListVos = stockTakingPictureListVos;
    }

    public List<StockTakingDetailsVosBean> getStockTakingDetailsVos() {
        return stockTakingDetailsVos;
    }

    public void setStockTakingDetailsVos(List<StockTakingDetailsVosBean> stockTakingDetailsVos) {
        this.stockTakingDetailsVos = stockTakingDetailsVos;
    }

    public List<?> getStockTakingDescriptionVos() {
        return stockTakingDescriptionVos;
    }

    public void setStockTakingDescriptionVos(List<?> stockTakingDescriptionVos) {
        this.stockTakingDescriptionVos = stockTakingDescriptionVos;
    }

    public static class StockTakingDetailsVosBean implements Serializable{
        /**
         * stockTakingDetailsId : 30
         * name : ??????
         * pictureUrl : 2018-05-14/54a97a916d1c4514923d6249b4a36784.png
         * category : ?????????
         * unit :
         * unitPrice : 2999.2
         * amount : 97
         * thisAmount : 97
         * buildingId : 3
         */

        private int stockTakingDetailsId;
        private String name;
        private String pictureUrl;
        private String category;
        private String unit;
        private double unitPrice;
        private int amount;
        private int thisAmount;
        private int buildingId;

        public int getStockTakingDetailsId() {
            return stockTakingDetailsId;
        }

        public void setStockTakingDetailsId(int stockTakingDetailsId) {
            this.stockTakingDetailsId = stockTakingDetailsId;
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

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getThisAmount() {
            return thisAmount;
        }

        public void setThisAmount(int thisAmount) {
            this.thisAmount = thisAmount;
        }

        public int getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(int buildingId) {
            this.buildingId = buildingId;
        }
    }
}
