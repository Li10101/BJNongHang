package com.xyl.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import com.xyl.base.BaseBean;

public class TeamListBean extends BaseBean {

	public String teamLeaderName;
	public String name;
	public String teamLeaderCode;
	public String teamId;
	public List<Staffs> staffs;

	public class Staffs implements Serializable {

		public String staffCode;
		public String phone;
		public String sex;
		public String sexDisplay;
		public String email;
		public String typeDisplay;
		public String name;
		public String type;

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
