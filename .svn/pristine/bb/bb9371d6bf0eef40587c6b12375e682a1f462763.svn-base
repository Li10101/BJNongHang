package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

import com.xyl.base.BaseBean;

/**
 * 这就是共同获取  的bean对象
 * @author Administrator
 *
 */
public class CasesBean extends BaseBean{

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
	public List<Records> records;

	public class Records implements Serializable{
		public String customerName;
		public String requestTime;
		public String status;
		public String typeDisplay;
		public String priorityDisplay;
		public String statusDisplay;
		public String fixStaffName;
		public String fixMoney;
		public String type;
		public String fixStaffPhone;
		public String address;
		public String priority;
		public String expectedFixTime;
		public String description;
		public String repairCaseCode;
		public String caseArea;
		public String caseAreaDisplay;
		public String caseMoneyType;
		public String caseMoneyTypeDisplay;
		public String caseProfession;
		public String caseProfessionDisplay;
		public String contentAndNote;
		
		@Override
		public String toString() {
			return "Records [customerName=" + customerName + ", requestTime="
					+ requestTime + ", status=" + status + ", typeDisplay="
					+ typeDisplay + ", priorityDisplay=" + priorityDisplay
					+ ", statusDisplay=" + statusDisplay + ", fixStaffName="
					+ fixStaffName + ", type=" + type + ", fixStaffPhone="
					+ fixStaffPhone + ", address=" + address + ", priority="
					+ priority + ", expectedFixTime=" + expectedFixTime
					+ ", description=" + description + ", repairCaseCode="
					+ repairCaseCode + "]";
		}
	}

}