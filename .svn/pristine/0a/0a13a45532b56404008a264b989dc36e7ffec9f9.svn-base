package com.xyl.domain;

import com.xyl.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class ServicesBean extends BaseBean {

	public int prePage;
	public int startRecord;
	public int nextPage;
	public Boolean error;
	public int endRecord;
	public int count;
	public String voClass;
	public int pageSize;
	public int totalPage;
	public int pageIndex;
	public List<ServiceBeanRecords> records;

	public class Records implements Serializable {

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
		public List<Traces> traces;

		public class Traces implements Serializable {

			public String staffCode;
			public String actionDisplay;
			public String status;
			public String staffName;
			public String statusDisplay;
			public String action;
			public String caseTraceId;
			public String executeTime;
		}

		// 这里是手动更改的 图片名称集合
		// public List<Pictures> pictures;
		public List<String> pictures;

		@Override
		public String toString() {
			return "Records{" +
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
					", traces=" + traces +
					", pictures=" + pictures +
					'}';
		}
	}
}
