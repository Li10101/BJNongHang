package com.xyl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.xyl.base.BaseApplication;

/**
 * 常用方法封装
 * 
 * @author Administrator
 * 
 */
public class CommonUtil {
	/**
	 * 在主线程中执行runnable
	 * 
	 * @param runnable
	 */
//	public static void runOnUIThread(Runnable runnable) {
//		BaseApplication.getMainHandler().post(runnable);
//	}

	/**
	 * 获取字符串资源
	 * 
	 * @param id
	 * @return
	 */
	public static String getString(int id) {
		return BaseApplication.getContext().getResources().getString(id);
	}

	/**
	 * 获取图片资源
	 * 
	 * @param id
	 * @return
	 */
	public static Drawable getDrawable(int id) {
		return BaseApplication.getContext().getResources().getDrawable(id);
	}

	/**
	 * 获取字符串数组资源
	 * 
	 * @param id
	 * @return
	 */
	public static String[] getStringArray(int id) {
		return BaseApplication.getContext().getResources().getStringArray(id);
	}

	/**
	 * 将view从父view中移除
	 * 
	 * @param view
	 */
	public static void removeSelfFromParent(View view) {
		if (view != null) {
			ViewParent parent = view.getParent();
			if (parent instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) parent;
				group.removeView(view);
			}
		}
	}

	public static int getScreenWidth(Activity activity) {
		return activity.getWindowManager().getDefaultDisplay().getWidth();
	}

	public static int getScrreenHeight(Activity activity) {
		return activity.getWindowManager().getDefaultDisplay().getHeight();
	}

	@SuppressLint("SdCardPath") public static File saveBitmap2file(Bitmap bmp, String filename) {
		CompressFormat format = Bitmap.CompressFormat.PNG;
		int quality = 100;
		OutputStream stream = null;
		try {
			stream = new FileOutputStream("/sdcard/uploadpic" + filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		boolean compress = bmp.compress(format, quality, stream);
		if(compress){
			return new File("/sdcard/uploadpic" + filename);
		}else{
			return null;
		}
	}
	
	// 缩放图片
	public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
	   // 获得图片的宽高
	   int width = bm.getWidth();
	   int height = bm.getHeight();
	   // 计算缩放比例
	   float scaleWidth = ((float) newWidth) / width;
	   float scaleHeight = ((float) newHeight) / height;
	   // 取得想要缩放的matrix参数
	   Matrix matrix = new Matrix();
	   matrix.postScale(scaleWidth, scaleHeight);
	   // 得到新的图片
	   Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
	   return newbm;
	}
}
