package com.xyl.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import com.xyl.base.BaseBean;

/**
 * 这个bean要直接封装成集合里的数据
 * @author peng
 *
 */
public class StaffListBean extends BaseBean implements Serializable{

	//public class JSONEntity implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public String staffCode;
	    public int staffId;
		public String sex;
		public String phone;
		public String sexDisplay;
		public String typeDisplay;
		public String type;
		public String contact;
		public String photo;
		public String email;
		public String address;
		public String description;
		public String name;
		public List<Teams> teams;


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
//	}


	
	
	//======================
	public class Teams implements Serializable {
		
		public String teamLeaderName;
		public String name;
		public String teamLeaderCode;
		public String teamId;

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
			return getClass().getSimpleName()
					+ "["
					+ (arr.length == 0 ? s : s.substring(0, s.length() - 1))
					+ "]";
		}
	}

	
	
	
	
	
}
