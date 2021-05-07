package com.xyl.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 47500 on 2018/4/28.
 */

public class OrderBean implements Serializable {

    private int buildingId;
    private String buildingName;
    private String dateTime;
    private int goodsCaseId;
    private int personId;
    private String personName;
    private String personPhone;
    private int process;
    private String status;
    private String statusDisplay;
    private String storage;
    private String storageDisplay;
    private String type;
    private String typeDisplay;
    private String verify;
    private String rejectReason;
    private String shenPiRen;
    private List<GoodsCaseDescriptionVosBean> goodsCaseDescriptionVos;
    private List<GoodsCaseDetailsVosBean> goodsCaseDetailsVos;
    private List<GoodsCasePictureVosBeanX> goodsCasePictureVos;
    private List<GoodsCaseTraceVosBean> goodsCaseTraceVos;

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getGoodsCaseId() {
        return goodsCaseId;
    }

    public void setGoodsCaseId(int goodsCaseId) {
        this.goodsCaseId = goodsCaseId;
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

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getStorageDisplay() {
        return storageDisplay;
    }

    public void setStorageDisplay(String storageDisplay) {
        this.storageDisplay = storageDisplay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getShenPiRen() {
        return shenPiRen;
    }

    public void setShenPiRen(String shenPiRen) {
        this.shenPiRen = shenPiRen;
    }

    public List<GoodsCaseDescriptionVosBean> getGoodsCaseDescriptionVos() {
        return goodsCaseDescriptionVos;
    }

    public void setGoodsCaseDescriptionVos(List<GoodsCaseDescriptionVosBean> goodsCaseDescriptionVos) {
        this.goodsCaseDescriptionVos = goodsCaseDescriptionVos;
    }

    public List<GoodsCaseDetailsVosBean> getGoodsCaseDetailsVos() {
        return goodsCaseDetailsVos;
    }

    public void setGoodsCaseDetailsVos(List<GoodsCaseDetailsVosBean> goodsCaseDetailsVos) {
        this.goodsCaseDetailsVos = goodsCaseDetailsVos;
    }

    public List<GoodsCasePictureVosBeanX> getGoodsCasePictureVos() {
        return goodsCasePictureVos;
    }

    public void setGoodsCasePictureVos(List<GoodsCasePictureVosBeanX> goodsCasePictureVos) {
        this.goodsCasePictureVos = goodsCasePictureVos;
    }

    public List<GoodsCaseTraceVosBean> getGoodsCaseTraceVos() {
        return goodsCaseTraceVos;
    }

    public void setGoodsCaseTraceVos(List<GoodsCaseTraceVosBean> goodsCaseTraceVos) {
        this.goodsCaseTraceVos = goodsCaseTraceVos;
    }



    public static class GoodsCaseDescriptionVosBean implements Serializable{
        /**
         * createTime : 2018-05-11 19:32:57
         * description : fsdfsdfsdfdsfdsfsdfsdfsd
         * goodsCaseDescId : 2
         * staffId : 10
         * staffName : 测试西楼006
         */

        private String createTime;
        private String description;
        private int goodsCaseDescId;
        private int staffId;
        private String staffName;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getGoodsCaseDescId() {
            return goodsCaseDescId;
        }

        public void setGoodsCaseDescId(int goodsCaseDescId) {
            this.goodsCaseDescId = goodsCaseDescId;
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
    }

    public static class GoodsCaseDetailsVosBean implements Serializable{
        /**
         * amount : 2
         * buildingId : 3
         * category : 咖啡豆
         * goodsCaseDetailsId : 83
         * name : 冰箱
         * pictureUrl :
         * unit : 台
         * unitPrice : 2999.22
         */

        private double amount;
        private int buildingId;
        private String category;
        private int goodsCaseDetailsId;
        private String name;
        private String pictureUrl;
        private String unit;
        private BigDecimal unitPrice;


        /** 编号 */
        private String bianHao;
        /** 规格 */
        private String guiGe;
        /** 型号 */
        private String xingHao;
        /** 生产日期 */
        /** 所属库房 公司库 部门库 */
        private Integer kuFangType;
        /** 厂商 */
        private String changShang;
        /** 厂商地址 */
        private String changShangDZ;
        /** 厂商电话 */
        private String changShangDH;

        private String kuFangTypeDisplay;


        public String getBianHao() {
            return bianHao;
        }

        public void setBianHao(String bianHao) {
            this.bianHao = bianHao;
        }

        public String getGuiGe() {
            return guiGe;
        }

        public void setGuiGe(String guiGe) {
            this.guiGe = guiGe;
        }

        public String getXingHao() {
            return xingHao;
        }

        public void setXingHao(String xingHao) {
            this.xingHao = xingHao;
        }

        public Integer getKuFangType() {
            return kuFangType;
        }

        public void setKuFangType(Integer kuFangType) {
            this.kuFangType = kuFangType;
        }

        public String getChangShang() {
            return changShang;
        }

        public void setChangShang(String changShang) {
            this.changShang = changShang;
        }

        public String getChangShangDZ() {
            return changShangDZ;
        }

        public void setChangShangDZ(String changShangDZ) {
            this.changShangDZ = changShangDZ;
        }

        public String getChangShangDH() {
            return changShangDH;
        }

        public void setChangShangDH(String changShangDH) {
            this.changShangDH = changShangDH;
        }

        public String getKuFangTypeDisplay() {
            return kuFangTypeDisplay;
        }

        public void setKuFangTypeDisplay(String kuFangTypeDisplay) {
            this.kuFangTypeDisplay = kuFangTypeDisplay;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public int getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(int buildingId) {
            this.buildingId = buildingId;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getGoodsCaseDetailsId() {
            return goodsCaseDetailsId;
        }

        public void setGoodsCaseDetailsId(int goodsCaseDetailsId) {
            this.goodsCaseDetailsId = goodsCaseDetailsId;
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

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }
    }

    public static class GoodsCasePictureVosBeanX implements Serializable{
        /**
         * description : gfdg
         * goodsCasePictureListId : 7
         * goodsCasePictureVos : [{"goodsCasePictureId":9,"pictureUrl":"2018-05-11/0b0f458261a44abea084b72d0d886b6f.png"}]
         * uploadTime : 2018-05-11 19:39:14
         * uploaderId : 10
         * uploaderName : 测试西楼006
         */

        private String description;
        private int goodsCasePictureListId;
        private String uploadTime;
        private int uploaderId;
        private String uploaderName;
        private List<GoodsCasePictureVosBean> goodsCasePictureVos;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getGoodsCasePictureListId() {
            return goodsCasePictureListId;
        }

        public void setGoodsCasePictureListId(int goodsCasePictureListId) {
            this.goodsCasePictureListId = goodsCasePictureListId;
        }

        public String getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(String uploadTime) {
            this.uploadTime = uploadTime;
        }

        public int getUploaderId() {
            return uploaderId;
        }

        public void setUploaderId(int uploaderId) {
            this.uploaderId = uploaderId;
        }

        public String getUploaderName() {
            return uploaderName;
        }

        public void setUploaderName(String uploaderName) {
            this.uploaderName = uploaderName;
        }

        public List<GoodsCasePictureVosBean> getGoodsCasePictureVos() {
            return goodsCasePictureVos;
        }

        public void setGoodsCasePictureVos(List<GoodsCasePictureVosBean> goodsCasePictureVos) {
            this.goodsCasePictureVos = goodsCasePictureVos;
        }

        public static class GoodsCasePictureVosBean implements Serializable {
            /**
             * goodsCasePictureId : 9
             * pictureUrl : 2018-05-11/0b0f458261a44abea084b72d0d886b6f.png
             */

            private int goodsCasePictureId;
            private String pictureUrl;

            public int getGoodsCasePictureId() {
                return goodsCasePictureId;
            }

            public void setGoodsCasePictureId(int goodsCasePictureId) {
                this.goodsCasePictureId = goodsCasePictureId;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }
        }
    }

    public static class GoodsCaseTraceVosBean implements Serializable{
        /**
         * action : 生成入库单
         * executeTime : 2018-05-11 14:32:50
         * goodsCaseTraceId : 30
         * staffId : 13
         * staffName : xl001
         */

        private String action;
        private String executeTime;
        private int goodsCaseTraceId;
        private int staffId;
        private String staffName;
        private String status;
        private String description;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(String executeTime) {
            this.executeTime = executeTime;
        }

        public int getGoodsCaseTraceId() {
            return goodsCaseTraceId;
        }

        public void setGoodsCaseTraceId(int goodsCaseTraceId) {
            this.goodsCaseTraceId = goodsCaseTraceId;
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
    }
}
