package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class WorkFlowBean implements Serializable {

    /**
     * count : 1
     * records : [{"gongZuoLiuId":4,"type":5,"typeDisplay":"请假工作流","buildingId":3,"buildingName":"农银大厦南楼","remove":false,"liuZhuans":[{"liuZhuanId":14,"paiXu":1,"shenPiR":"[156]","shenPiRName":"乔蝶,","tongGuo":2,"buTongGuo":0,"buTongGuoDisplay":"发起人"},{"liuZhuanId":15,"paiXu":2,"shenPiR":"[156]","shenPiRName":"乔蝶,","tongGuo":3,"buTongGuo":-2,"buTongGuoDisplay":"上一步"}]}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 1
     * error : false
     * voClass : com.etiansoft.estate.vo.GongZuoLiuVo
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

    public static class RecordsBean {
        /**
         * gongZuoLiuId : 4
         * type : 5
         * typeDisplay : 请假工作流
         * buildingId : 3
         * buildingName : 农银大厦南楼
         * remove : false
         * liuZhuans : [{"liuZhuanId":14,"paiXu":1,"shenPiR":"[156]","shenPiRName":"乔蝶,","tongGuo":2,"buTongGuo":0,"buTongGuoDisplay":"发起人"},{"liuZhuanId":15,"paiXu":2,"shenPiR":"[156]","shenPiRName":"乔蝶,","tongGuo":3,"buTongGuo":-2,"buTongGuoDisplay":"上一步"}]
         */

        private int gongZuoLiuId;
        private int type;
        private String typeDisplay;
        private int buildingId;
        private String buildingName;
        private boolean remove;
        private List<LiuZhuansBean> liuZhuans;

        public int getGongZuoLiuId() {
            return gongZuoLiuId;
        }

        public void setGongZuoLiuId(int gongZuoLiuId) {
            this.gongZuoLiuId = gongZuoLiuId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeDisplay() {
            return typeDisplay;
        }

        public void setTypeDisplay(String typeDisplay) {
            this.typeDisplay = typeDisplay;
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

        public List<LiuZhuansBean> getLiuZhuans() {
            return liuZhuans;
        }

        public void setLiuZhuans(List<LiuZhuansBean> liuZhuans) {
            this.liuZhuans = liuZhuans;
        }

        public static class LiuZhuansBean {
            /**
             * liuZhuanId : 14
             * paiXu : 1
             * shenPiR : [156]
             * shenPiRName : 乔蝶,
             * tongGuo : 2
             * buTongGuo : 0
             * buTongGuoDisplay : 发起人
             */

            private int liuZhuanId;
            private int paiXu;
            private String shenPiR;
            private String shenPiRName;
            private int tongGuo;
            private int buTongGuo;
            private String buTongGuoDisplay;

            public int getLiuZhuanId() {
                return liuZhuanId;
            }

            public void setLiuZhuanId(int liuZhuanId) {
                this.liuZhuanId = liuZhuanId;
            }

            public int getPaiXu() {
                return paiXu;
            }

            public void setPaiXu(int paiXu) {
                this.paiXu = paiXu;
            }

            public String getShenPiR() {
                return shenPiR;
            }

            public void setShenPiR(String shenPiR) {
                this.shenPiR = shenPiR;
            }

            public String getShenPiRName() {
                return shenPiRName;
            }

            public void setShenPiRName(String shenPiRName) {
                this.shenPiRName = shenPiRName;
            }

            public int getTongGuo() {
                return tongGuo;
            }

            public void setTongGuo(int tongGuo) {
                this.tongGuo = tongGuo;
            }

            public int getBuTongGuo() {
                return buTongGuo;
            }

            public void setBuTongGuo(int buTongGuo) {
                this.buTongGuo = buTongGuo;
            }

            public String getBuTongGuoDisplay() {
                return buTongGuoDisplay;
            }

            public void setBuTongGuoDisplay(String buTongGuoDisplay) {
                this.buTongGuoDisplay = buTongGuoDisplay;
            }
        }
    }
}
