package com.xyl.utils;

import android.widget.Toast;

import com.xyl.base.BaseApplication;


public class ToastUtil {
	private static Toast toast;
	/**
	 * 可以连续弹吐司，不用等上个吐司消失，直接显示
	 * @param text
	 */
	public static void showToast(String text){
		if(toast==null){
			toast = Toast.makeText(BaseApplication.getContext(), text, Toast.LENGTH_SHORT);
		}
		toast.setText(text);
		toast.show();
	}
}
