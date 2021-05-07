package com.xyl.base;

import java.lang.Thread.UncaughtExceptionHandler;

import org.xutils.x;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import cn.jpush.android.api.JPushInterface;

import com.xyl.greendao.DaoMaster;
import com.xyl.greendao.DaoSession;
import com.xyl.utils.SharedPreUtil;

public class BaseApplication extends Application implements UncaughtExceptionHandler {

	public static BaseApplication application;
	
	public static  BaseApplication getBaseApplication(){
		return application;
	}
	
	@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {

	}
	
	private static Context mContext;
	private static Handler mainHandler;
	private static SharedPreferences sp;
	private DaoMaster mDaoMaster;
	private DaoSession mDaoSession;
	private DaoMaster.DevOpenHelper mHelper;
	private SQLiteDatabase db;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		x.Ext.init(this);

		
		application = this;
		mContext = this;
		mainHandler = new Handler();
//		sp = mContext.getSharedPreferences(SharedPreUtil.FILE_NAME, Context.MODE_PRIVATE);
//		setDatabase();
		new Thread(new Runnable() {
			@Override
			public void run() {
				JPushInterface.setDebugMode(true);
				JPushInterface.init(mContext);
				sp = mContext.getSharedPreferences(SharedPreUtil.FILE_NAME, Context.MODE_PRIVATE);
				setDatabase();
			}
		}).start();

	}
	private void setDatabase() {
		// 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
		// 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
		// 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
		// 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
		mHelper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
		db = mHelper.getWritableDatabase();
		// 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
		mDaoMaster = new DaoMaster(db);
		mDaoSession = mDaoMaster.newSession();
	}
	public DaoSession getDaoSession() {
		return mDaoSession;
	}
	/**
	 * 获取全局上下文对象
	 * @return
	 */
	public static Context getContext(){
		return mContext;
	}
	
	/**
	 * 获取主线程的Handler
	 * @return
	 */
	public static Handler getMainHandler(){
		return mainHandler;
	}
	
	public static SharedPreferences getSp(){
		return sp;
	}
}
