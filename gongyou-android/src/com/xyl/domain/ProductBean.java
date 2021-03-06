package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 47500 on 2018/4/25.
 */

public class ProductBean implements Serializable {

    private List<AddGoodsCategoryVosBean> addGoodsCategoryVos;

    public List<AddGoodsCategoryVosBean> getAddGoodsCategoryVos() {
        return addGoodsCategoryVos;
    }

    public void setAddGoodsCategoryVos(List<AddGoodsCategoryVosBean> addGoodsCategoryVos) {
        this.addGoodsCategoryVos = addGoodsCategoryVos;
    }

    public static class AddGoodsCategoryVosBean {
        /**
         * goodsCategoryId : 1
         * name : 电器
         * count : 2
         * goodsList : [{"goodsId":1,"name":"空调","pictureUrl":"","category":null,"categoryId":"1","categoryName":"电器","unit":"台","unitPrice":2553.12,"amount":2,"description":"","goodsHistories":[],"buildingId":"3","buildingName":"中关村店"}]
         */

        private int goodsCategoryId;
        private String name;
        private int count;
        private List<GoodsListBean> goodsList;

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

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * goodsId : 1
             * name : 空调
             * pictureUrl :
             * category : null
             * categoryId : 1
             * categoryName : 电器
             * unit : 台
             * unitPrice : 2553.12
             * amount : 2.0
             * description :
             * goodsHistories : []
             * buildingId : 3
             * buildingName : 中关村店
             */

            private int goodsId;
            private String name;
            private String pictureUrl;
            private Object category;
            private String categoryId;
            private String categoryName;
            private String unit;
            private double unitPrice;
            private double amount;
            private String description;
            private String buildingId;
            private String buildingName;
            private double thisAmount;
            private boolean stockTaking;
            private List<?> goodsHistories;




            public boolean isStockTaking() {
                return stockTaking;
            }

            public void setStockTaking(boolean stockTaking) {
                this.stockTaking = stockTaking;
            }

            public double getThisAmount() {
                return thisAmount;
            }

            public void setThisAmount(double thisAmount) {
                this.thisAmount = thisAmount;
            }

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

            public Object getCategory() {
                return category;
            }

            public void setCategory(Object category) {
                this.category = category;
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

            public List<?> getGoodsHistories() {
                return goodsHistories;
            }

            public void setGoodsHistories(List<?> goodsHistories) {
                this.goodsHistories = goodsHistories;
            }
        }
    }
}
