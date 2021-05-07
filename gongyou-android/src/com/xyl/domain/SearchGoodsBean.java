package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

public class SearchGoodsBean implements Serializable {

    /**
     * count : 1
     * records : [{"goodsId":5,"name":"牙刷","pictureUrl":"","categoryId":"2","categoryName":"洗漱","unit":"","unitPrice":5.5,"amount":100,"thisAmount":100,"baseLine":null,"pushCount":null,"description":"","buildingId":"3","buildingName":"农银大厦南楼","stockTaking":false}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 1
     * error : false
     * voClass : com.etiansoft.estate.inventory.vo.GoodsSimpleVo
     */

    private int count;
    private int pageSize;
    private int pageIndex;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int startRecord;
    private int endRecord;
    private boolean error;
    private String voClass;
    private List<RecordsBean> records;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getEndRecord() {
        return endRecord;
    }

    public void setEndRecord(int endRecord) {
        this.endRecord = endRecord;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getVoClass() {
        return voClass;
    }

    public void setVoClass(String voClass) {
        this.voClass = voClass;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable{
        /**
         * goodsId : 5
         * name : 牙刷
         * pictureUrl :
         * categoryId : 2
         * categoryName : 洗漱
         * unit :
         * unitPrice : 5.5
         * amount : 100.0
         * thisAmount : 100.0
         * baseLine : null
         * pushCount : null
         * description :
         * buildingId : 3
         * buildingName : 农银大厦南楼
         * stockTaking : false
         */

        private int goodsId;
        private String name;
        private String pictureUrl;
        private String categoryId;
        private String categoryName;
        private String unit;
        private double unitPrice;
        private double amount;
        private double thisAmount;
        private Object baseLine;
        private Object pushCount;
        private String description;
        private String buildingId;
        private String buildingName;
        private boolean stockTaking;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
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

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
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

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getThisAmount() {
            return thisAmount;
        }

        public void setThisAmount(double thisAmount) {
            this.thisAmount = thisAmount;
        }

        public Object getBaseLine() {
            return baseLine;
        }

        public void setBaseLine(Object baseLine) {
            this.baseLine = baseLine;
        }

        public Object getPushCount() {
            return pushCount;
        }

        public void setPushCount(Object pushCount) {
            this.pushCount = pushCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(String buildingId) {
            this.buildingId = buildingId;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public boolean isStockTaking() {
            return stockTaking;
        }

        public void setStockTaking(boolean stockTaking) {
            this.stockTaking = stockTaking;
        }
    }
}

