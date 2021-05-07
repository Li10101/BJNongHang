package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

public class HealthStatusBean implements Serializable {

    /**
     * count : 2
     * records : [{"jianKangQKId":2,"shangXiaW":0,"shangXiaWDisplay":"上午","xingShi":-1,"xingShiDisplay":"其他","xingShiStr":"测试啊","tiWenSFZC":"是","juTiWD":"测试","muQianZT":2,"muQianZTDisplay":"居家观察","shiFouYZZ":"否","jiaRenSFYZZ":"否","beiZhu":"","shiFouJCYQRY":"否","shiFouMQJCZ":"否","shiFouYSBL":"否","shiFouQZBL":"否","dateTime":"2020-03-06","shenQingRId":381,"shenQingRName":"测试员","staffId":381,"staffName":"测试员","buildingId":2,"buildingName":"展览馆路办公楼"},{"jianKangQKId":1,"shangXiaW":0,"shangXiaWDisplay":"上午","xingShi":1,"xingShiDisplay":"现场检测","xingShiStr":"现场检测","tiWenSFZC":"是","juTiWD":"36.6","muQianZT":2,"muQianZTDisplay":"居家观察","shiFouYZZ":"否","jiaRenSFYZZ":"否","beiZhu":"","shiFouJCYQRY":"否","shiFouMQJCZ":"否","shiFouYSBL":"否","shiFouQZBL":"否","dateTime":"2020-03-06","shenQingRId":381,"shenQingRName":"测试员","staffId":381,"staffName":"测试员","buildingId":2,"buildingName":"展览馆路办公楼"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 2
     * error : false
     * voClass : com.etiansoft.estate.yiQing.vo.JianKangQKVo
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
         * jianKangQKId : 2
         * shangXiaW : 0
         * shangXiaWDisplay : 上午
         * xingShi : -1
         * xingShiDisplay : 其他
         * xingShiStr : 测试啊
         * tiWenSFZC : 是
         * juTiWD : 测试
         * muQianZT : 2
         * muQianZTDisplay : 居家观察
         * shiFouYZZ : 否
         * jiaRenSFYZZ : 否
         * beiZhu :
         * shiFouJCYQRY : 否
         * shiFouMQJCZ : 否
         * shiFouYSBL : 否
         * shiFouQZBL : 否
         * dateTime : 2020-03-06
         * shenQingRId : 381
         * shenQingRName : 测试员
         * staffId : 381
         * staffName : 测试员
         * buildingId : 2
         * buildingName : 展览馆路办公楼
         */

        private int jianKangQKId;
        private int shangXiaW;
        private String shangXiaWDisplay;
        private int xingShi;
        private String xingShiDisplay;
        private String xingShiStr;
        private String tiWenSFZC;
        private String juTiWD;
        private int muQianZT;
        private String muQianZTDisplay;
        private String shiFouYZZ;
        private String jiaRenSFYZZ;
        private String beiZhu;
        private String shiFouJCYQRY;
        private String shiFouMQJCZ;
        private String shiFouYSBL;
        private String shiFouQZBL;
        private String dateTime;
        private int shenQingRId;
        private String shenQingRName;
        private int staffId;
        private String staffName;
        private int buildingId;
        private String buildingName;

        public int getJianKangQKId() {
            return jianKangQKId;
        }

        public void setJianKangQKId(int jianKangQKId) {
            this.jianKangQKId = jianKangQKId;
        }

        public int getShangXiaW() {
            return shangXiaW;
        }

        public void setShangXiaW(int shangXiaW) {
            this.shangXiaW = shangXiaW;
        }

        public String getShangXiaWDisplay() {
            return shangXiaWDisplay;
        }

        public void setShangXiaWDisplay(String shangXiaWDisplay) {
            this.shangXiaWDisplay = shangXiaWDisplay;
        }

        public int getXingShi() {
            return xingShi;
        }

        public void setXingShi(int xingShi) {
            this.xingShi = xingShi;
        }

        public String getXingShiDisplay() {
            return xingShiDisplay;
        }

        public void setXingShiDisplay(String xingShiDisplay) {
            this.xingShiDisplay = xingShiDisplay;
        }

        public String getXingShiStr() {
            return xingShiStr;
        }

        public void setXingShiStr(String xingShiStr) {
            this.xingShiStr = xingShiStr;
        }

        public String getTiWenSFZC() {
            return tiWenSFZC;
        }

        public void setTiWenSFZC(String tiWenSFZC) {
            this.tiWenSFZC = tiWenSFZC;
        }

        public String getJuTiWD() {
            return juTiWD;
        }

        public void setJuTiWD(String juTiWD) {
            this.juTiWD = juTiWD;
        }

        public int getMuQianZT() {
            return muQianZT;
        }

        public void setMuQianZT(int muQianZT) {
            this.muQianZT = muQianZT;
        }

        public String getMuQianZTDisplay() {
            return muQianZTDisplay;
        }

        public void setMuQianZTDisplay(String muQianZTDisplay) {
            this.muQianZTDisplay = muQianZTDisplay;
        }

        public String getShiFouYZZ() {
            return shiFouYZZ;
        }

        public void setShiFouYZZ(String shiFouYZZ) {
            this.shiFouYZZ = shiFouYZZ;
        }

        public String getJiaRenSFYZZ() {
            return jiaRenSFYZZ;
        }

        public void setJiaRenSFYZZ(String jiaRenSFYZZ) {
            this.jiaRenSFYZZ = jiaRenSFYZZ;
        }

        public String getBeiZhu() {
            return beiZhu;
        }

        public void setBeiZhu(String beiZhu) {
            this.beiZhu = beiZhu;
        }

        public String getShiFouJCYQRY() {
            return shiFouJCYQRY;
        }

        public void setShiFouJCYQRY(String shiFouJCYQRY) {
            this.shiFouJCYQRY = shiFouJCYQRY;
        }

        public String getShiFouMQJCZ() {
            return shiFouMQJCZ;
        }

        public void setShiFouMQJCZ(String shiFouMQJCZ) {
            this.shiFouMQJCZ = shiFouMQJCZ;
        }

        public String getShiFouYSBL() {
            return shiFouYSBL;
        }

        public void setShiFouYSBL(String shiFouYSBL) {
            this.shiFouYSBL = shiFouYSBL;
        }

        public String getShiFouQZBL() {
            return shiFouQZBL;
        }

        public void setShiFouQZBL(String shiFouQZBL) {
            this.shiFouQZBL = shiFouQZBL;
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
