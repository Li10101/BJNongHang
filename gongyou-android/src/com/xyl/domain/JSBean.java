package com.xyl.domain;

import java.io.Serializable;
import java.util.List;



public class JSBean implements Serializable{

	public String address;
    public String caseArea;
    public String caseAreaDisplay;
    public String caseMoneyType;
    public String caseMoneyTypeDisplay;
    public String caseProfession;
    public String caseProfessionDisplay;
    public String contentAndNote;
    public String customerCode;
    public String customerName;
    public String customerPhone;
    public String description;
    public String doneTime;
    public String evaluateContent;
    public String evaluateRate;
    public String evaluateRateDisplay;
    public String expectedFixTime;
    public int fixMoney;
    public String fixStaffCode;
    public String fixStaffName;
    public String fixStaffPhone;
    public String fixTeamName;
    public String priority;
    public String priorityDisplay;
    public int pushTag;
    public String repairCaseCode;
    public String requestTime;
    public String signatureUrl;
    public String status;
    public String statusDisplay;
    public String type;
    public String typeDisplay;
    public List<PartnersBean> partners;
    public List<PartnersBean> pictures;
    public List<ServicePictures> servicePictures;
    /**
     * action : service
     * actionDisplay : 客户报修
     * caseTraceId : 13455
     * executeTime : 2016-10-18 22:42:04
     * staffCode : zx006
     * staffName : zx006
     * status : service
     * statusDisplay : 客户报修
     */

    public List<TracesBean> traces;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContentAndNote() {
        return contentAndNote;
    }

    public void setContentAndNote(String contentAndNote) {
        this.contentAndNote = contentAndNote;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateRate() {
        return evaluateRate;
    }

    public void setEvaluateRate(String evaluateRate) {
        this.evaluateRate = evaluateRate;
    }

    public String getEvaluateRateDisplay() {
        return evaluateRateDisplay;
    }

    public void setEvaluateRateDisplay(String evaluateRateDisplay) {
        this.evaluateRateDisplay = evaluateRateDisplay;
    }

    public String getExpectedFixTime() {
        return expectedFixTime;
    }

    public void setExpectedFixTime(String expectedFixTime) {
        this.expectedFixTime = expectedFixTime;
    }

    public int getFixMoney() {
        return fixMoney;
    }

    public void setFixMoney(int fixMoney) {
        this.fixMoney = fixMoney;
    }

    public String getFixStaffCode() {
        return fixStaffCode;
    }

    public void setFixStaffCode(String fixStaffCode) {
        this.fixStaffCode = fixStaffCode;
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

    public String getFixTeamName() {
        return fixTeamName;
    }

    public void setFixTeamName(String fixTeamName) {
        this.fixTeamName = fixTeamName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriorityDisplay() {
        return priorityDisplay;
    }

    public void setPriorityDisplay(String priorityDisplay) {
        this.priorityDisplay = priorityDisplay;
    }

    public int getPushTag() {
        return pushTag;
    }

    public void setPushTag(int pushTag) {
        this.pushTag = pushTag;
    }

    public String getRepairCaseCode() {
        return repairCaseCode;
    }

    public void setRepairCaseCode(String repairCaseCode) {
        this.repairCaseCode = repairCaseCode;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getSignatureUrl() {
        return signatureUrl;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
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

    public List<?> getPartners() {
        return partners;
    }

    public void setPartners(List<PartnersBean> partners) {
        this.partners = partners;
    }

    public List<?> getPictures() {
        return pictures;
    }

    public void setPictures(List<PartnersBean> pictures) {
        this.pictures = pictures;
    }

    public List<?> getServicePictures() {
        return servicePictures;
    }

    public void setServicePictures(List<ServicePictures> servicePictures) {
        this.servicePictures = servicePictures;
    }

    public List<TracesBean> getTraces() {
        return traces;
    }

    public void setTraces(List<TracesBean> traces) {
        this.traces = traces;
    }

    public static class TracesBean {
        public String action;
        public String actionDisplay;
        public String caseTraceId;
        public String executeTime;
        public String staffCode;
        public String staffName;
        public String status;
        public String statusDisplay;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getActionDisplay() {
            return actionDisplay;
        }

        public void setActionDisplay(String actionDisplay) {
            this.actionDisplay = actionDisplay;
        }

        public String getCaseTraceId() {
            return caseTraceId;
        }

        public void setCaseTraceId(String caseTraceId) {
            this.caseTraceId = caseTraceId;
        }

        public String getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(String executeTime) {
            this.executeTime = executeTime;
        }

        public String getStaffCode() {
            return staffCode;
        }

        public void setStaffCode(String staffCode) {
            this.staffCode = staffCode;
        }

        public String getStaffName() {
            return staffName;
        }

        public void setStaffName(String staffName) {
            this.staffName = staffName;
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
    }
}
