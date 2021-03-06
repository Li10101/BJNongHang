package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 47500 on 2018/5/23.
 */

public class LibraryListBean implements Serializable {


    /**
     * count : 5
     * records : [{"stockTakingId":201806263,"personId":0,"personName":"","personPhone":"","dateTime":"2018-06-26 07:00:00","buildingId":3,"buildingName":"中关村店","processUrl":"static/images/inventory/green.png"},{"stockTakingId":201806253,"personId":0,"personName":"","personPhone":"","dateTime":"2018-06-25 07:00:00","buildingId":3,"buildingName":"中关村店","processUrl":"static/images/inventory/green.png"},{"stockTakingId":201806243,"personId":0,"personName":"","personPhone":"","dateTime":"2018-06-24 07:00:00","buildingId":3,"buildingName":"中关村店","processUrl":"static/images/inventory/green.png"},{"stockTakingId":201806233,"personId":0,"personName":"","personPhone":"","dateTime":"2018-06-23 07:00:00","buildingId":3,"buildingName":"中关村店","processUrl":"static/images/inventory/green.png"},{"stockTakingId":201806223,"personId":0,"personName":"","personPhone":"","dateTime":"2018-06-22 10:16:28","buildingId":3,"buildingName":"中关村店","processUrl":"static/images/inventory/green.png"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 5
     * error : false
     * voClass : com.etiansoft.estate.module.inventory.vo.StockTakingSimpleVo
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

    public static class RecordsBean implements Serializable {
        /**
         * stockTakingId : 201806263
         * personId : 0
         * personName :
         * personPhone :
         * dateTime : 2018-06-26 07:00:00
         * buildingId : 3
         * buildingName : 中关村店
         * processUrl : static/images/inventory/green.png
         */

        private int stockTakingId;
        private int personId;
        private String personName;
        private String personPhone;
        private String dateTime;
        private int buildingId;
        private String buildingName;
        private String processUrl;

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

        public String getProcessUrl() {
            return processUrl;
        }

        public void setProcessUrl(String processUrl) {
            this.processUrl = processUrl;
        }
    }
}
