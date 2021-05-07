package com.xyl.utils;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;


public class ListUtil {

	public static final String DEFAULT_JOIN_SEPARATOR = ",";

	private ListUtil() {
		throw new AssertionError();
	}

	/**
	 * 获取list长度
	 */
	public static <V> int getSize(List<V> sourceList) {
		return sourceList == null ? 0 : sourceList.size();
	}

	/**
	 * list是否为空
	 * 
	 */
	public static <V> boolean isEmpty(List<V> sourceList) {
		return (sourceList == null || sourceList.size() == 0);
	}

	/**
	 * 比较两个list是否一样
	 */
	public static <V> boolean isEquals(ArrayList<V> actual,
			ArrayList<V> expected) {
		if (actual == null) {
			return expected == null;
		}
		if (expected == null) {
			return false;
		}
		if (actual.size() != expected.size()) {
			return false;
		}

		for (int i = 0; i < actual.size(); i++) {
			if (!ObjectUtils.isEquals(actual.get(i), expected.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * join list to string, separator is ","
	 * 
	 * <pre>
	 * join(null)      =   ""; 
	 * join({})        =   ""; 
	 * join({a,b})     =   "a,b";
	 * </pre>
	 * 
	 * @param list
	 * @return join list to string, separator is ",". if list is empty, return
	 *         ""
	 */
	public static String join(List<String> list) {
		return join(list, DEFAULT_JOIN_SEPARATOR);
	}

	/**
	 * list转换成String
	 * 
	 */
	public static String join(List<String> list, char separator) {
		return join(list, new String(new char[] { separator }));
	}

	/**
	 *list转换成String 自定义分隔符
	 */
	public static String join(List<String> list, String separator) {
		return list == null ? "" : TextUtils.join(separator, list);
	}

	/**
	 * add distinct entry to list
	 * sourceList是否包含entry.不包含就把entry加入sourceList；包含返回false；
	 */
	public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
		return (sourceList != null && !sourceList.contains(entry)) ? sourceList
				.add(entry) : false;
	}

	/**
	 * 添加entryList在sourceList中没有的项，返回不包含的项数量。
	 */
	public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
		if (sourceList == null || isEmpty(entryList)) {
			return 0;
		}

		int sourceCount = sourceList.size();
		for (V entry : entryList) {
			if (!sourceList.contains(entry)) {
				sourceList.add(entry);
			}
		}
		return sourceList.size() - sourceCount;
	}

	/**
	 * 去重并返回size
	 */
	public static <V> int distinctList(List<V> sourceList) {
		if (isEmpty(sourceList)) {
			return 0;
		}

		int sourceCount = sourceList.size();
		int sourceListSize = sourceList.size();
		for (int i = 0; i < sourceListSize; i++) {
			for (int j = (i + 1); j < sourceListSize; j++) {
				if (sourceList.get(i).equals(sourceList.get(j))) {
					sourceList.remove(j);
					sourceListSize = sourceList.size();
					j--;
				}
			}
		}
		return sourceCount - sourceList.size();
	}

	/**
	 * 添加非空value到List
	 */
	public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
		return (sourceList != null && value != null) ? sourceList.add(value)
				: false;
	}


}
