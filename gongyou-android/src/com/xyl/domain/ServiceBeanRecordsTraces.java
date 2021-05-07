package com.xyl.domain;

import java.io.Serializable;
import java.util.Objects;

public class ServiceBeanRecordsTraces implements Serializable{

	public String staffCode;
	public String actionDisplay;
	public String status;
	public String staffName;
	public String statusDisplay;
	public String action;
	public String caseTraceId;
	public String executeTime;
	
	@Override
	public String toString() {
		return "ServiceBeanRecordsTraces [staffCode=" + staffCode
				+ ", actionDisplay=" + actionDisplay + ", status=" + status
				+ ", staffName=" + staffName + ", statusDisplay="
				+ statusDisplay + ", action=" + action + ", caseTraceId="
				+ caseTraceId + ", executeTime=" + executeTime + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ServiceBeanRecordsTraces that = (ServiceBeanRecordsTraces) o;
		return Objects.equals(staffCode, that.staffCode) &&
				Objects.equals(actionDisplay, that.actionDisplay) &&
				Objects.equals(status, that.status) &&
				Objects.equals(staffName, that.staffName) &&
				Objects.equals(statusDisplay, that.statusDisplay) &&
				Objects.equals(action, that.action) &&
				Objects.equals(caseTraceId, that.caseTraceId) &&
				Objects.equals(executeTime, that.executeTime);
	}

	@Override
	public int hashCode() {

		return Objects.hash(staffCode, actionDisplay, status, staffName, statusDisplay, action, caseTraceId, executeTime);
	}
}
