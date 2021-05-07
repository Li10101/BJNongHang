package com.xyl.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xyl.R;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;

import cn.jpush.android.api.JPushInterface;


public class SplashActivity extends Activity {

    private Boolean isFirst;
	private Context mContext;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
	        finish();
	        return;
	    }
        setContentView(R.layout.activity_splash);
        isFirst = (Boolean)SharedPreferencesUtil.getParam(SplashActivity.this, "isFirst", true);
		/*Integer push1 = (Integer)SharedPreferencesUtil.getParam(SplashActivity.this, String.valueOf(1), 0);
		Integer push0 = (Integer)SharedPreferencesUtil.getParam(SplashActivity.this, String.valueOf(0), 0);
		int count = push1+push0;*/

		//ShortcutBadger.with(getApplicationContext()).count(badgeCount);
        new Handler().postDelayed(new Runnable(){
        	@Override
        	public void run() {
        		if(isFirst){
        			Intent intent = new Intent(SplashActivity.this, SettingInfoActivity.class);
        			intent.putExtra("title", "设置域名");
        			intent.putExtra("type", "请设置域名：");
        			intent.putExtra("info", "http://101.200.78.162");
        			startActivity(intent);
        		}else {
        			NetContacts.getInstance().setBaseUrl(SharedPreferencesUtil.getString(getApplicationContext(), "BaseUrl"));
        			startActivity(new Intent(SplashActivity.this,LoginActivity.class));
        		}
        		finish();
        	}
        }, 2000);
    }

	@Override
	protected void onResume(){
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}
}
