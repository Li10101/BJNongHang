package com.xyl.utils;
import android.content.SharedPreferences.Editor;

import com.xyl.base.BaseApplication;


public class SharedPreUtil {
	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "config";
	
	private static Editor getEdit(){
		return BaseApplication.getSp().edit();
	}
	
	public static void putInt(String key,int value){
		getEdit().putInt(key, value);
	}
	
	public static void putString(String key,String value){
		getEdit().putString(key, value);
	}
	
	public static void putBoolean(String key,Boolean value){
		getEdit().putBoolean(key, value);
	}
	
	public static int getInt(String key,int defValue){
		return BaseApplication.getSp().getInt(key, defValue);
	}
	
	public static String getString(String key,String defValue){
		return BaseApplication.getSp().getString(key, defValue);
	}
	
	public static boolean getBoolean(String key,boolean defValue){
		return BaseApplication.getSp().getBoolean(key, defValue);
	}
}

