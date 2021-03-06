package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

public class ServiceBeanRecords implements Serializable {

	public String customerName;
	public String serviceRequestId;
	public String caseCode;
	public String requestTime;
	public String status;
	public String address;
	public String expectedFixTime;
	public String description;
	public String customerCode;
	public String statusDisplay;
	public String customerPhone;
	public String customerType;
	public String customerTypeDisplay;
	public List<ServiceBeanRecordsTraces> traces;

	@Override
	public String toString() {
		return "ServiceBeanRecords{" +
				"customerName='" + customerName + '\'' +
				", serviceRequestId='" + serviceRequestId + '\'' +
				", caseCode='" + caseCode + '\'' +
				", requestTime='" + requestTime + '\'' +
				", status='" + status + '\'' +
				", address='" + address + '\'' +
				", expectedFixTime='" + expectedFixTime + '\'' +
				", description='" + description + '\'' +
				", customerCode='" + customerCode + '\'' +
				", statusDisplay='" + statusDisplay + '\'' +
				", customerPhone='" + customerPhone + '\'' +
				", customerType='" + customerType + '\'' +
				", customerTypeDisplay='" + customerTypeDisplay + '\'' +
				", traces=" + traces +
				'}';
	}


}
