package com.xyl.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import com.xyl.base.BaseBean;

public class FindByEquipmentNoBean extends BaseBean {

	public Boolean warn;
	public String location;
	public String fixStaffCode;
	public String vendorAddress; 
	public String overhaulCycle; 
	public String modelNo;
	public String maintenanceSop; 
	public String releaseDate; 
	public String description; 
	public String technicalParameters; 
	public String name; 
	public String repairCaseCode; 
	public String icon; 
	public String forwardMaintenanceDate; 
	public String vendorName; 
	public String image; 
	public String useDate; 
	public String fixStaffName; 
	public String vendorPhone; 
	public String vendorContact; 
	public String nextMaintenanceDate; 
	public String serialNo; 
	public String category; 
	public String iomFile; 
	public String equipmentNo; 
	public String manufacturer; 
	public String specification; 
	public String serviceArea; 
	public List<MaintainRecords> maintainRecords;
	public List<EquipmentPm>  equipmentPmVos;
	public List<AlarmsBean.Records> alarmVos;
	public class EquipmentPm implements Serializable{
		/**
		 * announcement :
		 * description :
		 * fixStaffCode : 002
		 * fixStaffName : 002
		 * lastMaintainDate : 2017-05-12
		 * nextMaintainDate : 2025-07-30
		 * period : 30
		 * pmName : A保养
		 * type : A
		 */

		public String announcement;
		public String description;
		public String fixStaffCode;
		public String fixStaffName;
		public String lastMaintainDate;
		public String nextMaintainDate;
		public String maintenanceSop;
		public int period;
		public String pmName;
		public String type;

		public String getAnnouncement() {
			return announcement;
		}

		public void setAnnouncement(String announcement) {
			this.announcement = announcement;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public String getLastMaintainDate() {
			return lastMaintainDate;
		}

		public void setLastMaintainDate(String lastMaintainDate) {
			this.lastMaintainDate = lastMaintainDate;
		}

		public String getNextMaintainDate() {
			return nextMaintainDate;
		}

		public void setNextMaintainDate(String nextMaintainDate) {
			this.nextMaintainDate = nextMaintainDate;
		}

		public int getPeriod() {
			return period;
		}

		public void setPeriod(int period) {
			this.period = period;
		}

		public String getPmName() {
			return pmName;
		}

		public void setPmName(String pmName) {
			this.pmName = pmName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
	}
	public class MaintainRecords implements Serializable {
		public String description;
		public String fixStaffCode;
		public String fixStaffName;
		public int maintainRecordId;
		public String maintainDate;
		public String pmName;
		public String repairCase;

		@Override
		public String toString() {
			return "MaintainRecords{" +
					"description='" + description + '\'' +
					", fixStaffCode='" + fixStaffCode + '\'' +
					", fixStaffName='" + fixStaffName + '\'' +
					", maintainRecordId=" + maintainRecordId +
					", maintainDate='" + maintainDate + '\'' +
					", pmName='" + pmName + '\'' +
					", repairCase='" + repairCase + '\'' +
					'}';
		}
	}

	@Override
	public String toString() {
		String s = "";
		Field[] arr = this.getClass().getFields();
		for (Field f : getClass().getFields()) {
			try {
				s += f.getName() + "=" + f.get(this) + "\n,";
			} catch (Exception e) {
			}
		}
		return getClass().getSimpleName() + "["
				+ (arr.length == 0 ? s : s.substring(0, s.length() - 1)) + "]";
	}
}
