package com.xyl.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtil {

	public static final String DEFAULT_KEY_AND_VALUE_SEPARATOR = ":";
	public static final String DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR = ",";

	private MapUtil() {
		throw new AssertionError();
	}

	/**
	 * Map是否为空
	 */
	public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
		return (sourceMap == null || sourceMap.size() == 0);
	}

	/**
	 * 为非空map添加非空key的key，value（String）
	 */
	public static boolean putMapNotEmptyKey(Map<String, String> map,
			String key, String value) {
		if (map == null || StringUtil.isEmpty(key)) {
			return false;
		}

		map.put(key, value);
		return true;
	}

	/**
	 * 为非空map添加非空key，value
	 */
	public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map,
			String key, String value) {
		if (map == null || StringUtil.isEmpty(key) || StringUtil.isEmpty(value)) {
			return false;
		}

		map.put(key, value);
		return true;
	}

	/**
	 * 为非空map添加键值对，如果值为空，则添加defaultValue
	 */
	public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map,
			String key, String value, String defaultValue) {
		if (map == null || StringUtil.isEmpty(key)) {
			return false;
		}

		map.put(key, StringUtil.isEmpty(value) ? defaultValue : value);
		return true;
	}

	/**
	 * 为非空map添加非空key的key，value（泛型）
	 */
	public static <K, V> boolean putMapNotNullKey(Map<K, V> map, K key, V value) {
		if (map == null || key == null) {
			return false;
		}

		map.put(key, value);
		return true;
	}

	/**
	 * 为非空map添加非空key，value（泛型）
	 */
	public static <K, V> boolean putMapNotNullKeyAndValue(Map<K, V> map, K key,
			V value) {
		if (map == null || key == null || value == null) {
			return false;
		}

		map.put(key, value);
		return true;
	}

	/**
	 * 通过value返回key
	 */
	public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
		if (isEmpty(map)) {
			return null;
		}

		for (Entry<K, V> entry : map.entrySet()) {
			if (ObjectUtils.isEquals(entry.getValue(), value)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * parse key-value pairs to map, ignore empty key
	 * 
	 * <pre>
	 * parseKeyAndValueToMap("","","",true)=null 
	 * parseKeyAndValueToMap(null,"","",true)=null 
	 * parseKeyAndValueToMap("a:b,:","","",true)={(a,b)} 
	 * parseKeyAndValueToMap("a:b,:d","","",true)={(a,b)} 
	 * parseKeyAndValueToMap("a:b,c:d","","",true)={(a,b),(c,d)} 
	 * parseKeyAndValueToMap("a=b, c = d","=",",",true)={(a,b),(c,d)} 
	 * parseKeyAndValueToMap("a=b, c = d","=",",",false)={(a, b),( c , d)} 
	 * parseKeyAndValueToMap("a=b, c=d","=", ",", false)={(a,b),( c,d)} 
	 * parseKeyAndValueToMap("a=b; c=d","=", ";", false)={(a,b),( c,d)} 
	 * parseKeyAndValueToMap("a=b, c=d", ",", ";", false)={(a=b, c=d)} 
	 * </pre>
	 * 
	 * @param source
	 *            key-value pairs
	 * @param keyAndValueSeparator
	 *            separator between key and value
	 * @param keyAndValuePairSeparator
	 *            separator between key-value pairs
	 * @param ignoreSpace
	 *            whether ignore space at the begging or end of key and value
	 */
	public static Map<String, String> parseKeyAndValueToMap(String source,
			String keyAndValueSeparator, String keyAndValuePairSeparator,
			boolean ignoreSpace) {
		if (StringUtil.isEmpty(source)) {
			return null;
		}

		if (StringUtil.isEmpty(keyAndValueSeparator)) {
			keyAndValueSeparator = DEFAULT_KEY_AND_VALUE_SEPARATOR;
		}
		if (StringUtil.isEmpty(keyAndValuePairSeparator)) {
			keyAndValuePairSeparator = DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR;
		}
		Map<String, String> keyAndValueMap = new HashMap<String, String>();
		String[] keyAndValueArray = source.split(keyAndValuePairSeparator);
		if (keyAndValueArray == null) {
			return null;
		}

		int seperator;
		for (String valueEntity : keyAndValueArray) {
			if (!StringUtil.isEmpty(valueEntity)) {
				seperator = valueEntity.indexOf(keyAndValueSeparator);
				if (seperator != -1) {
					if (ignoreSpace) {
						MapUtil.putMapNotEmptyKey(keyAndValueMap, valueEntity
								.substring(0, seperator).trim(), valueEntity
								.substring(seperator + 1).trim());
					} else {
						MapUtil.putMapNotEmptyKey(keyAndValueMap,
								valueEntity.substring(0, seperator),
								valueEntity.substring(seperator + 1));
					}
				}
			}
		}
		return keyAndValueMap;
	}

	/**
	 * parse key-value pairs to map, ignore empty key 
	 * @param source key-value pairs 
	 * @param ignoreSpace whether ignore space at the begging or end of key and value 
	 */
	public static Map<String, String> parseKeyAndValueToMap(String source,
			boolean ignoreSpace) {
		return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR,
				DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR, ignoreSpace);
	}

	/**
	 * parse key-value pairs to map, ignore empty key, ignore space at the
	 * begging or end of key and value
	 * @param source key-value pairs
	 * {@link MapUtils#parseKeyAndValueToMap(String, String, String, boolean)},
	 * keyAndValueSeparator is 
	 * {@link #DEFAULT_KEY_AND_VALUE_SEPARATOR},
	 * keyAndValuePairSeparator is 
	 * {@link #DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR}, ignoreSpace is true 
	 */
	public static Map<String, String> parseKeyAndValueToMap(String source) {
		return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR,
				DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR, true);
	}

	/**
	 * join map
	 * @param map
	 * @return
	 */
	public static String toJson(Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return null;
		}

		StringBuilder paras = new StringBuilder();
		paras.append("{");
		Iterator<Map.Entry<String, String>> ite = map.entrySet().iterator();
		while (ite.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) ite
					.next();
			paras.append("\"").append(entry.getKey()).append("\":\"")
					.append(entry.getValue()).append("\"");
			if (ite.hasNext()) {
				paras.append(",");
			}
		}
		paras.append("}");
		return paras.toString();
	}
}
