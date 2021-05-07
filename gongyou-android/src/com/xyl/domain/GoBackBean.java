package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

public class GoBackBean implements Serializable {

    /**
     * count : 2
     * records : [{"quFanChengQKId":2,"quChengSJ":"2020-03-09","muDiD":"河南省商丘市","shiFouJHBZZ":"否","fanChengSJ":"2020-03-18","chuFaD":"北京","fanChengType":3,"fanChengTypeDisplay":"自驾","fanChengCPH":"","shiFouJTHB":"否","jingTingHBHD":"","jingTingSJ":"","dateTime":"2020-03-09","shenQingRId":381,"shenQingRName":"测试员","staffId":381,"staffName":"测试员","buildingId":2,"buildingName":"展览馆路办公楼"},{"quFanChengQKId":1,"quChengSJ":"2020-02-27","muDiD":"河北省邯郸市","shiFouJHBZZ":"否","fanChengSJ":"2020-02-28","chuFaD":"北京市","fanChengType":2,"fanChengTypeDisplay":"火车","fanChengCPH":"D1222","shiFouJTHB":"否","jingTingHBHD":"","jingTingSJ":"","dateTime":"2020-02-27","shenQingRId":381,"shenQingRName":"测试员","staffId":381,"staffName":"测试员","buildingId":2,"buildingName":"展览馆路办公楼"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 2
     * error : false
     * voClass : com.etiansoft.estate.yiQing.vo.QuFanChengQKVo
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
         * quFanChengQKId : 2
         * quChengSJ : 2020-03-09
         * muDiD : 河南省商丘市
         * shiFouJHBZZ : 否
         * fanChengSJ : 2020-03-18
         * chuFaD : 北京
         * fanChengType : 3
         * fanChengTypeDisplay : 自驾
         * fanChengCPH :
         * shiFouJTHB : 否
         * jingTingHBHD :
         * jingTingSJ :
         * dateTime : 2020-03-09
         * shenQingRId : 381
         * shenQingRName : 测试员
         * staffId : 381
         * staffName : 测试员
         * buildingId : 2
         * buildingName : 展览馆路办公楼
         */

        private int quFanChengQKId;
        private String quChengSJ;
        private String muDiD;
        private String shiFouJHBZZ;
        private String fanChengSJ;
        private String chuFaD;
        private int fanChengType;
        private String fanChengTypeDisplay;
        private String fanChengCPH;
        private String shiFouJTHB;
        private String jingTingHBHD;
        private String jingTingSJ;
        private String dateTime;
        private int shenQingRId;
        private String shenQingRName;
        private int staffId;
        private String staffName;
        private int buildingId;
        private String buildingName;

        public int getQuFanChengQKId() {
            return quFanChengQKId;
        }

        public void setQuFanChengQKId(int quFanChengQKId) {
            this.quFanChengQKId = quFanChengQKId;
        }

        public String getQuChengSJ() {
            return quChengSJ;
        }

        public void setQuChengSJ(String quChengSJ) {
            this.quChengSJ = quChengSJ;
        }

        public String getMuDiD() {
            return muDiD;
        }

        public void setMuDiD(String muDiD) {
            this.muDiD = muDiD;
        }

        public String getShiFouJHBZZ() {
            return shiFouJHBZZ;
        }

        public void setShiFouJHBZZ(String shiFouJHBZZ) {
            this.shiFouJHBZZ = shiFouJHBZZ;
        }

        public String getFanChengSJ() {
            return fanChengSJ;
        }

        public void setFanChengSJ(String fanChengSJ) {
            this.fanChengSJ = fanChengSJ;
        }

        public String getChuFaD() {
            return chuFaD;
        }

        public void setChuFaD(String chuFaD) {
            this.chuFaD = chuFaD;
        }

        public int getFanChengType() {
            return fanChengType;
        }

        public void setFanChengType(int fanChengType) {
            this.fanChengType = fanChengType;
        }

        public String getFanChengTypeDisplay() {
            return fanChengTypeDisplay;
        }

        public void setFanChengTypeDisplay(String fanChengTypeDisplay) {
            this.fanChengTypeDisplay = fanChengTypeDisplay;
        }

        public String getFanChengCPH() {
            return fanChengCPH;
        }

        public void setFanChengCPH(String fanChengCPH) {
            this.fanChengCPH = fanChengCPH;
        }

        public String getShiFouJTHB() {
            return shiFouJTHB;
        }

        public void setShiFouJTHB(String shiFouJTHB) {
            this.shiFouJTHB = shiFouJTHB;
        }

        public String getJingTingHBHD() {
            return jingTingHBHD;
        }

        public void setJingTingHBHD(String jingTingHBHD) {
            this.jingTingHBHD = jingTingHBHD;
        }

        public String getJingTingSJ() {
            return jingTingSJ;
        }

        public void setJingTingSJ(String jingTingSJ) {
            this.jingTingSJ = jingTingSJ;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public int getShenQingRId() {
            return shenQingRId;
        }

        public void setShenQingRId(int shenQingRId) {
            this.shenQingRId = shenQingRId;
        }

        public String getShenQingRName() {
            return shenQingRName;
        }

        public void setShenQingRName(String shenQingRName) {
            this.shenQingRName = shenQingRName;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
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
