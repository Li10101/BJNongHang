package com.xyl.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.io.Serializable;
import java.util.List;
public class AlarmsBean implements Serializable {

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

	public class Records implements Serializable {

		public String alarmDescription;
		public String alarmPointValue;
		public String alarmPointPriority;
		public String alarmPointDescription;
		public String priorityDisplay;
		public String statusDisplay;
		public String alarmId;
		public String alarmConfirm;
		public String equipmentLocation;
		public String alarmTime;
		public String confirmAlarm;
		public String alarmEquipment;
		public String alarmStatus;
		public String alarmPointName;

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
					+ (arr.length == 0 ? s : s.substring(0, s.length() - 1))
					+ "]";
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