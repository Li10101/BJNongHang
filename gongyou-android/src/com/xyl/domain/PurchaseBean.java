package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 47500 on 2018/4/27.
 */

public class PurchaseBean implements Serializable {

    /**
     * count : 4
     * records : [{"goodsCaseId":1,"personName":"xl001","personPhone":"","status":"0","statusDisplay":"申请中","dateTime":"2018-04-28 10:03:49"},{"goodsCaseId":2,"personName":"xl001","personPhone":"","status":"0","statusDisplay":"申请中","dateTime":"2018-04-28 10:04:22"},{"goodsCaseId":3,"personName":"xl001","personPhone":"","status":"0","statusDisplay":"申请中","dateTime":"2018-04-28 10:04:45"},{"goodsCaseId":4,"personName":"xl001","personPhone":"","status":"0","statusDisplay":"申请中","dateTime":"2018-04-28 10:05:09"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 4
     * error : false
     * voClass : com.etiansoft.estate.module.inventory.vo.GoodsCaseSimpleVo
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
         * goodsCaseId : 1
         * personName : xl001
         * personPhone :
         * status : 0
         * statusDisplay : 申请中
         * dateTime : 2018-04-28 10:03:49
         */



        /** 单号ID (Not Null) */
        private Integer goodsCaseId;
        /** 单类型 入库(0), 工单出库(1), 调拨出库(2), 盘点(3) */
        private Integer type;
        private String typeDisplay;
        /** 报单人 */
        private String personName;
        /** 报单人电话 */
        private String personPhone;
        /** 状态 申领中(0),已通过(1),未通过(2), 已领取(3),退料(4),已完成(5), 未知(-1); */
        private String status;
        private String statusDisplay;
        /** 生成时间 */
        private String dateTime;


        public void setGoodsCaseId(Integer goodsCaseId) {
            this.goodsCaseId = goodsCaseId;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getTypeDisplay() {
            return typeDisplay;
        }

        public void setTypeDisplay(String typeDisplay) {
            this.typeDisplay = typeDisplay;
        }

        public int getGoodsCaseId() {
            return goodsCaseId;
        }

        public void setGoodsCaseId(int goodsCaseId) {
            this.goodsCaseId = goodsCaseId;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusDisplay() {
            return statusDisplay;
        }

        public void setStatusDisplay(String statusDisplay) {
            this.statusDisplay = statusDisplay;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }
    }
}
