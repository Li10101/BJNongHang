package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class SelectAllPersonalBean implements Serializable {

    /**
     * count : 13
     * records : [{"renShiId":1,"renShiType":5,"renShiTypeDisplay":"请假","createTime":"2019-08-25","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":2,"renShiType":6,"renShiTypeDisplay":"外出","createTime":"2019-08-31","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":3,"renShiType":7,"renShiTypeDisplay":"转正","createTime":"2019-08-31","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":4,"renShiType":8,"renShiTypeDisplay":"加班","createTime":"2019-08-31","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":5,"renShiType":12,"renShiTypeDisplay":"通用审批","createTime":"2019-08-31","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":6,"renShiType":13,"renShiTypeDisplay":"工作请示","createTime":"2019-09-01","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":8,"renShiType":15,"renShiTypeDisplay":"资质使用","createTime":"2019-09-04","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":9,"renShiType":14,"renShiTypeDisplay":"用印申请","createTime":"2019-09-04","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":10,"renShiType":16,"renShiTypeDisplay":"备用金申请","createTime":"2019-09-04","status":0,"statusDisplay":"申请中","shenQingRen":"测试库管","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":18,"renShiType":12,"renShiTypeDisplay":"通用审批","createTime":"2019-09-16","status":0,"statusDisplay":"申请中","shenQingRen":"李振平","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":19,"renShiType":5,"renShiTypeDisplay":"请假","createTime":"2019-09-16","status":0,"statusDisplay":"申请中","shenQingRen":"李振平","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":20,"renShiType":12,"renShiTypeDisplay":"通用审批","createTime":"2019-09-16","status":0,"statusDisplay":"申请中","shenQingRen":"李振平","buildingId":3,"buildingName":"农银大厦南楼"},{"renShiId":21,"renShiType":14,"renShiTypeDisplay":"用印申请","createTime":"2019-09-16","status":0,"statusDisplay":"申请中","shenQingRen":"李振平","buildingId":3,"buildingName":"农银大厦南楼"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 13
     * error : false
     * voClass : com.etiansoft.estate.renshi.vo.RenShiSimpleVo
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
         * renShiId : 1
         * renShiType : 5
         * renShiTypeDisplay : 请假
         * createTime : 2019-08-25
         * status : 0
         * statusDisplay : 申请中
         * shenQingRen : 测试库管
         * buildingId : 3
         * buildingName : 农银大厦南楼
         */

        private int renShiId;
        private int renShiType;
        private String renShiTypeDisplay;
        private String createTime;
        private int status;
        private String statusDisplay;
        private String shenQingRen;
        private int buildingId;
        private String buildingName;

        public int getRenShiId() {
            return renShiId;
        }

        public void setRenShiId(int renShiId) {
            this.renShiId = renShiId;
        }

        public int getRenShiType() {
            return renShiType;
        }

        public void setRenShiType(int renShiType) {
            this.renShiType = renShiType;
        }

        public String getRenShiTypeDisplay() {
            return renShiTypeDisplay;
        }

        public void setRenShiTypeDisplay(String renShiTypeDisplay) {
            this.renShiTypeDisplay = renShiTypeDisplay;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusDisplay() {
            return statusDisplay;
        }

        public void setStatusDisplay(String statusDisplay) {
            this.statusDisplay = statusDisplay;
        }

        public String getShenQingRen() {
            return shenQingRen;
        }

        public void setShenQingRen(String shenQingRen) {
            this.shenQingRen = shenQingRen;
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
    }
}
