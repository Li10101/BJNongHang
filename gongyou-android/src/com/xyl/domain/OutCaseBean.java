package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

public class OutCaseBean implements Serializable {



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
         * repairCaseCode : CUS_0000050
         * fixStaffName : 张德
         * fixStaffPhone : 13521646298
         * priority : 0
         * expectedFixTime :
         * customerName : 乔蝶
         * address : B1办公室
         * description : 打印机出现故障
         * requestTime : 2018-01-31 14:52:38
         * building : 农银大厦南楼
         * status : fixed
         * type : 0
         * caseMoneyType : 1
         * caseMoneyTypeDisplay : 无偿
         * caseArea : 0
         * caseAreaDisplay : 客户
         * contentAndNote :
         * fixMoney : 0.0
         * caseProfession : 0
         * caseProfessionDisplay : 强电
         * manHour : null
         * equipmentPmType : null
         * lastMaintainDate : null
         * nextMaintainDate : null
         * period : null
         * maintenanceSop : null
         * typeDisplay : 客户工单
         * statusDisplay : 已完成
         * priorityDisplay : 低
         */

        private String repairCaseCode;
        private String fixStaffName;
        private String fixStaffPhone;
        private String priority;
        private String expectedFixTime;
        private String customerName;
        private String address;
        private String description;
        private String requestTime;
        private String building;
        private String status;
        private String type;
        private String caseMoneyType;
        private String caseMoneyTypeDisplay;
        private String caseArea;
        private String caseAreaDisplay;
        private String contentAndNote;
        private String fixMoney;
        private String caseProfession;
        private String caseProfessionDisplay;
        private String manHour;
        private Object equipmentPmType;
        private Object lastMaintainDate;
        private Object nextMaintainDate;
        private Object period;
        private Object maintenanceSop;
        private String typeDisplay;
        private String statusDisplay;
        private String priorityDisplay;

        public String getRepairCaseCode() {
            return repairCaseCode;
        }

        public void setRepairCaseCode(String repairCaseCode) {
            this.repairCaseCode = repairCaseCode;
        }

        public String getFixStaffName() {
            return fixStaffName;
        }

        public void setFixStaffName(String fixStaffName) {
            this.fixStaffName = fixStaffName;
        }

        public String getFixStaffPhone() {
            return fixStaffPhone;
        }

        public void setFixStaffPhone(String fixStaffPhone) {
            this.fixStaffPhone = fixStaffPhone;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getExpectedFixTime() {
            return expectedFixTime;
        }

        public void setExpectedFixTime(String expectedFixTime) {
            this.expectedFixTime = expectedFixTime;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRequestTime() {
            return requestTime;
        }

        public void setRequestTime(String requestTime) {
            this.requestTime = requestTime;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCaseMoneyType() {
            return caseMoneyType;
        }

        public void setCaseMoneyType(String caseMoneyType) {
            this.caseMoneyType = caseMoneyType;
        }

        public String getCaseMoneyTypeDisplay() {
            return caseMoneyTypeDisplay;
        }

        public void setCaseMoneyTypeDisplay(String caseMoneyTypeDisplay) {
            this.caseMoneyTypeDisplay = caseMoneyTypeDisplay;
        }

        public String getCaseArea() {
            return caseArea;
        }

        public void setCaseArea(String caseArea) {
            this.caseArea = caseArea;
        }

        public String getCaseAreaDisplay() {
            return caseAreaDisplay;
        }

        public void setCaseAreaDisplay(String caseAreaDisplay) {
            this.caseAreaDisplay = caseAreaDisplay;
        }

        public String getContentAndNote() {
            return contentAndNote;
        }

        public void setContentAndNote(String contentAndNote) {
            this.contentAndNote = contentAndNote;
        }

        public String getFixMoney() {
            return fixMoney;
        }

        public void setFixMoney(String fixMoney) {
            this.fixMoney = fixMoney;
        }

        public String getCaseProfession() {
            return caseProfession;
        }

        public void setCaseProfession(String caseProfession) {
            this.caseProfession = caseProfession;
        }

        public String getCaseProfessionDisplay() {
            return caseProfessionDisplay;
        }

        public void setCaseProfessionDisplay(String caseProfessionDisplay) {
            this.caseProfessionDisplay = caseProfessionDisplay;
        }

        public String getManHour() {
            return manHour;
        }

        public void setManHour(String manHour) {
            this.manHour = manHour;
        }

        public Object getEquipmentPmType() {
            return equipmentPmType;
        }

        public void setEquipmentPmType(Object equipmentPmType) {
            this.equipmentPmType = equipmentPmType;
        }

        public Object getLastMaintainDate() {
            return lastMaintainDate;
        }

        public void setLastMaintainDate(Object lastMaintainDate) {
            this.lastMaintainDate = lastMaintainDate;
        }

        public Object getNextMaintainDate() {
            return nextMaintainDate;
        }

        public void setNextMaintainDate(Object nextMaintainDate) {
            this.nextMaintainDate = nextMaintainDate;
        }

        public Object getPeriod() {
            return period;
        }

        public void setPeriod(Object period) {
            this.period = period;
        }

        public Object getMaintenanceSop() {
            return maintenanceSop;
        }

        public void setMaintenanceSop(Object maintenanceSop) {
            this.maintenanceSop = maintenanceSop;
        }

        public String getTypeDisplay() {
            return typeDisplay;
        }

        public void setTypeDisplay(String typeDisplay) {
            this.typeDisplay = typeDisplay;
        }

        public String getStatusDisplay() {
            return statusDisplay;
        }

        public void setStatusDisplay(String statusDisplay) {
            this.statusDisplay = statusDisplay;
        }

        public String getPriorityDisplay() {
            return priorityDisplay;
        }

        public void setPriorityDisplay(String priorityDisplay) {
            this.priorityDisplay = priorityDisplay;
        }
    }
}
