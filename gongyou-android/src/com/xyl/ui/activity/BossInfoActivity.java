package com.xyl.ui.activity;
import net.sf.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import cn.jpush.android.api.JPushInterface;

import com.xyl.R;
import com.xyl.base.BaseApplication;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.ToastUtil;

public class BossInfoActivity extends Activity {
    
	//private TextView tv_title;
	private WebView wv_boss;
	private ImageView iv_boss_exit1;
	private ImageView iv_boss_exit2;
	private ImageView img_loding;
	private float x;
	private float x1;
	private String title;
	private int flag;
	private View inflate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflate = getLayoutInflater().inflate(R.layout.activity_boss, null);
		setContentView(inflate);

		initView();
		initListener();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
		if ("能源管理".equals(title)){
			if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			}
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();

		wv_boss.destroy();
		wv_boss = null;
	}

	private void initView() {
		iv_boss_exit1 = (ImageView) this.findViewById(R.id.iv_boss_exit1);
		iv_boss_exit2 = (ImageView) this.findViewById(R.id.iv_boss_exit2);
		img_loding = (ImageView) this.findViewById(R.id.img_loding);
		img_loding.setVisibility(View.VISIBLE);
		
		wv_boss = (WebView) this.findViewById(R.id.wv_boss);
		//开启js
		WebSettings settings = wv_boss.getSettings();
		settings.setJavaScriptEnabled(true);
		//设置自适应屏幕，两者合用
		settings.setLoadWithOverviewMode(true);
		settings.setUseWideViewPort(true);//将图片调整到适合webview的大小
		settings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小


		wv_boss.setVerticalScrollBarEnabled(false);
		wv_boss.setHorizontalScrollBarEnabled(false);
		wv_boss.addJavascriptInterface(new  gongyouApp(),"gongyouApp");
		wv_boss.addJavascriptInterface(new  gongyouAppUser(),"gongyouAppUser");
		wv_boss.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				img_loding.setVisibility(View.GONE);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
				view.loadUrl(request.getUrl().toString());
				return true;
			}
		});

	}

	private void initListener() {
		iv_boss_exit1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		iv_boss_exit2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void initData() {
		title = this.getIntent().getStringExtra("title");
		if ("工单".equals(title)) {
			String url = NetContacts.getInstance().getBaseUrl() + "/estate/ichart";
			syncCookie(url);
			wv_boss.loadUrl(url);
			iv_boss_exit1.setVisibility(View.VISIBLE);
			iv_boss_exit2.setVisibility(View.GONE);
		} else if ("设备报警".equals(title)) {
			String alarmUrl = NetContacts.getInstance().getBaseUrl() + "/estate/equipment/alarm";
			syncCookie(alarmUrl);
			wv_boss.loadUrl(alarmUrl);
			iv_boss_exit1.setVisibility(View.GONE);
			iv_boss_exit2.setVisibility(View.VISIBLE);
		}else if ("资产管理".equals(title)) {
			String userUrl = NetContacts.getInstance().getBaseUrl() + "/estate/userIchart";
			syncCookie(userUrl);
			wv_boss.loadUrl(userUrl);
			iv_boss_exit1.setVisibility(View.GONE);
			iv_boss_exit2.setVisibility(View.VISIBLE);
		}
		else if ("能源管理".equals(title)) {
			String dashUrl = NetContacts.getInstance().getBaseUrl() + "/estate/pad/dashboard";
			syncCookie(dashUrl);
			wv_boss.loadUrl(dashUrl);
			iv_boss_exit1.setVisibility(View.VISIBLE);
			iv_boss_exit2.setVisibility(View.GONE);
			wv_boss.setOnTouchListener(new View.OnTouchListener() {


				@Override
				public boolean onTouch(View v, MotionEvent event) {
					boolean isScroll = false;
					switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							x = event.getX();
							break;
						case MotionEvent.ACTION_MOVE:
							x1 = event.getX();
							if(x-x1<30){
								isScroll =  false;
							}else{
								isScroll =  true;
							}
							break;
						case MotionEvent.ACTION_UP:
							break;
					}
					return isScroll;
				}
			});
		}
	}
	private void syncCookie(String url) {
		try {
			CookieSyncManager.createInstance(wv_boss.getContext());//创建一个cookie管理器
			CookieManager cookieManager = CookieManager.getInstance();
			cookieManager.setAcceptCookie(true);
			cookieManager.removeSessionCookie();// 移除以前的cookie
			cookieManager.removeAllCookie();
			/*StringBuilder sbCookie = new StringBuilder();//创建一个拼接cookie的容器,为什么这么拼接，大家查阅一下http头Cookie的结构
			sbCookie.append(_mApplication.getUserInfo().getSessionID());//拼接sessionId
			sbCookie.append(String.format(";domain=%s", ""));
			sbCookie.append(String.format(";path=%s", ""));*/
			String cookieValue = SharedPreferencesUtil.getString(
					BaseApplication.getBaseApplication(), "cookie");;
			cookieManager.setCookie(url, cookieValue);//为url设置cookie
			CookieSyncManager.getInstance().sync();//同步cookie
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


public class gongyouApp {
	@JavascriptInterface
	public void passResult2App(String ss) {
		 flag = 1;
		 JSONObject jsonobject = JSONObject.fromObject(ss);
		 Integer count = (Integer) jsonobject.get("count");
		if(count==0){
			ToastUtil.showToast("没有数据");
			return;
		}else{
		    Intent intent = new Intent(getApplicationContext(),GongdanStateActivity.class);
		    intent.putExtra("LoginBean", getIntent().getSerializableExtra("LoginBean"));
		    intent.putExtra("DataString", ss);
			intent.putExtra("flag",flag);
	        startActivity(intent);
	        }
	}
	}

	public class gongyouAppUser {
		@JavascriptInterface
		public void passResult2AppUser(String ss) {
			flag = 2;
			JSONObject jsonobject = JSONObject.fromObject(ss);
			String status = (String) jsonobject.get("value");
			if(status.length()==0){
				ToastUtil.showToast("没有数据");
				return;
			}else{
				Intent intent = new Intent(getApplicationContext(),GongdanStateActivity.class);
				intent.putExtra("LoginBean", getIntent().getSerializableExtra("LoginBean"));
				intent.putExtra("status", status);
				intent.putExtra("flag",flag);
				startActivity(intent);
			}
		}
	}


}
