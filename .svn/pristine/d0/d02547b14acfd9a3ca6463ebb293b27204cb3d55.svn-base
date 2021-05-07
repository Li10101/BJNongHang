package com.xyl.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import com.xyl.base.BaseBean;

public class FindByCategoryNoBean extends BaseBean {

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

		public String icon;
		public String categoryName;
		public String equipmentNo;
		public String name;
		public String categoryNo;
		public String nextMaintenanceDate;

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
