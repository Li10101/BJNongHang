package com.xyl.domain;

import java.lang.reflect.Field;

import com.xyl.base.BaseBean;

public class ActionsBean extends BaseBean {

	public String name;
	public String action;
	public String formUrl;

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
