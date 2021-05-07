package com.xyl.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.net.UserManager;
import com.xyl.ui.activity.LoginActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;

public class HeartService extends Service {
	private String heart;
	private final int intervalTime = 1000 * 60 * 5;// 10分钟
	private final int RELEASE =1;
	final Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case RELEASE:
				if (mWakeLock != null && mWakeLock.isHeld()) {
					mWakeLock.release();
					mWakeLock = null;
					System.out.println("走到关闭锁了！！！！");
				}
				break;
			}
		};
	};
	private PowerManager.WakeLock mWakeLock;

	private Runnable runnable;

	private UserManager userManager;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		userManager = new UserManager();
	}

	@Override
	public void onDestroy() {
 		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		heart = null;
		initWeakLock();
		startRunnable();
		return super.onStartCommand(intent, flags, startId);
	}

	private void initWeakLock() {
		PowerManager pm = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myTag");
		mWakeLock.acquire();
	}

	private void startRunnable() {
		if (runnable == null) {
			runnable = new Runnable() {
				@Override
				public void run() {
					//showTime();
					startLogin(runnable);
					handler.postDelayed(runnable, intervalTime);
				}
			};
		}
		handler.postDelayed(runnable, intervalTime);
	}

	private void startLogin(final Runnable runnable) {
		userManager.heartbeat(new EntityCallback() {

			@Override
			public void connectCallback(EntityrResult entityrResult) {
				if (entityrResult.error == true) {
					if (heart == null) {
						heart = "1";
					} else {
						//关闭weakLock
						handler.sendEmptyMessage(RELEASE);
						//跳转登陆页面
						startLoginActivity();
						//关闭定时服务
						handler.removeCallbacks(runnable);
					}
				}else if(EntityType.messagetrue!=entityrResult.entityType){
					if (heart == null) {
						heart = "1";
					} else {
						//关闭weakLock
						handler.sendEmptyMessage(RELEASE);
						//跳转登陆页面
						startLoginActivity();
						//关闭定时服务
						handler.removeCallbacks(runnable);
					}
				}
			}

		});
	}
	/**
	 * 跳转登陆页面
	 */
	private void startLoginActivity() {
		Intent loginIntent = new Intent(this, LoginActivity.class); 
		loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		startActivity(loginIntent); 
	}

	private void showTime() {
		String message = Thread.currentThread().getName() + " 正常打印";
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy年MM月dd日    HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		String totalMessage = message + str;
		System.out.println(totalMessage);
		// LogUtil.writeLog(totalMessage);
		System.out.println("打印到文件了！");
	}
}
