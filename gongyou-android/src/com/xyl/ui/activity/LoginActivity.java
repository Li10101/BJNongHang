package com.xyl.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import com.xyl.R;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.UpDataApkBean;
import com.xyl.net.OrderManager;
import com.xyl.net.UserManager;
import com.xyl.service.HeartService;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.UIUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText et_username;
	private EditText et_password;
	private Button btn_login;
	private Button btn_register;
	private String userName;
	private String passWord;
	UserManager loginNet;
	private LinearLayout ll_root;
	private int screenHeigth;
	private int screenWidth;

	private String message;
	private boolean error = false;
	private SharedPreferences sp;
	private ImageView iv_setting;
	private CheckBox cb_save_password;
	private boolean isSaveUser = false;
	private Boolean stFirst =true ;
	private UpDataApkBean upDataApkBean;
	private ProgressDialog dialog;
	private File apkFile;
	private static final int DOWNLOAD_APK_FAIL = 0;
	private static final int DOWNLOAD_APK_SUCCESS = 1;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
          switch (msg.what){
			  case DOWNLOAD_APK_FAIL:
				  UIUtils.toast("联网下载数据失败",false);
				  break;
			  case DOWNLOAD_APK_SUCCESS:
				  UIUtils.toast("下载应用数据成功",false);
				  dialog.dismiss();
				  //installApk();//安装下载好的应用
				  finish();//结束当前的welcomeActivity的显示
				  break;
		  }

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sp = getSharedPreferences("login", MODE_PRIVATE);
		
		iv_setting = (ImageView)this.findViewById(R.id.iv_setting);
		initWidget();
		//downLoadApk();
		initListener();
		initScreenSize();
		loginNet = new UserManager();
		initRootSize();

	}
	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}



	private void initRootSize() {
		LayoutParams params = ll_root.getLayoutParams();
		params.width = screenWidth;
		params.height = screenHeigth;
		ll_root.setLayoutParams(params);
	}

	private void initScreenSize() {
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		screenHeigth = wm.getDefaultDisplay().getHeight();
		screenWidth = wm.getDefaultDisplay().getWidth();
	}

	private void initListener() {
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		iv_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, SettingInfoActivity.class);
    			intent.putExtra("title", "设置域名");
    			intent.putExtra("type", "请设置域名：");
    			intent.putExtra("info", "http://");
    			startActivity(intent);
			}
		});
		
		cb_save_password.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				isSaveUser = isChecked;
				SharedPreferencesUtil.setParam(getApplicationContext(), SharedPreferencesUtil.KEY_SAVE_USERINFO, isChecked);
				if(!isChecked){
					removeUserInfo();
				}
			}
		});
	}

	private void initWidget() {
		ll_root = (LinearLayout) findViewById(R.id.ll_root);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (Button) findViewById(R.id.btn_register);
		cb_save_password = (CheckBox)this.findViewById(R.id.cb_save_password);
		boolean checked = (Boolean)SharedPreferencesUtil.getParam(getApplicationContext(), SharedPreferencesUtil.KEY_SAVE_USERINFO, false);
		cb_save_password.setChecked(checked);
		isSaveUser = checked;
		if(checked){
			et_username.setText(SharedPreferencesUtil.getString(getApplicationContext(), SharedPreferencesUtil.KEY_UERNAME));
			et_password.setText(SharedPreferencesUtil.getString(getApplicationContext(), SharedPreferencesUtil.KEY_PASSWORD));
		}
	}

	@Override
	public void onClick(View v) {
		if(error==true){
			Toast.makeText(this, message, Toast.LENGTH_LONG).show();
			return;
		}
		if (v == btn_login) {// 登陆
			if (getContent()) {
				login(userName, passWord);
				if(isSaveUser){
					saveUserInfo(userName,passWord);
				}
			}
			return;
		}
		if (v == btn_register) {// 注册
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			return;
		}
	}


	private void login(String userName, String passWord) {
		for(int a=0; a<6; a++){
			SharedPreferencesUtil.setParam(this, String.valueOf(a), 0);
		}
		btn_login.setClickable(false);
		JPushInterface.resumePush(getApplicationContext());
		loginNet.userLoginIn(userName, passWord, new BaseCallback<LoginBean>() {
			
			@Override
			public void messageSuccess(LoginBean bean){
				SharedPreferencesUtil.setString(getApplicationContext(), SharedPreferencesUtil.LOGIN_NAME, bean.name);
				btn_login.setClickable(true);
				Intent intent = new Intent(getApplicationContext(),
						HomeActivity.class);
				intent.putExtra("LoginBean", bean);
				startActivity(intent);
//				startService();
				Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT)
				.show();
				/*SharedPreferencesUtil.setParam(getApplicationContext(), "stFirst", false);*/
				finish();
			}

			@Override
			public void messageFailure(MessageBean backError) {
				btn_login.setClickable(true);
				Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT)
						.show();
			}

			@Override
			public void connectFailure(MessageBean messageBean) {
				btn_login.setClickable(true);
				Toast.makeText(LoginActivity.this, "请检查您的网络连接！",
						Toast.LENGTH_SHORT).show();
			}
		});
	
	}

	/**
	 * 获取账号和密码
	 */
	private boolean getContent() {
		userName = et_username.getText().toString().trim();
		passWord = et_password.getText().toString().trim();
		if (TextUtils.isEmpty(userName)) {
			Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
			et_username.requestFocus();
			return false;
		}
		if (TextUtils.isEmpty(passWord)) {
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			et_username.requestFocus();
			return false;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent setIntent = new Intent(Intent.ACTION_MAIN);
		setIntent.addCategory(Intent.CATEGORY_HOME);
		setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(setIntent);
	}

	private void saveUserInfo(String userName, String passWord) {
		SharedPreferencesUtil.setString(getApplicationContext(), SharedPreferencesUtil.KEY_UERNAME, userName);
		SharedPreferencesUtil.setString(getApplicationContext(), SharedPreferencesUtil.KEY_PASSWORD, passWord);
	}
	
	public void removeUserInfo(){
		SharedPreferences sp = this.getSharedPreferences(SharedPreferencesUtil.FILE_NAME, Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.remove(SharedPreferencesUtil.KEY_UERNAME);
		edit.remove(SharedPreferencesUtil.KEY_PASSWORD);
		edit.commit();
	}
}
