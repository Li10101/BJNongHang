package com.xyl.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.Utils;
import com.xyl.R;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.global.NetContacts;
import com.xyl.net.ModifyInfoNet;
import com.xyl.utils.SharedPreferencesUtil;

import cn.jpush.android.api.JPushInterface;

public class SettingInfoActivity extends Activity {

	private TextView tv_user_info;
	private EditText et_user_info;
	private ImageView iv_title_left;
	private TextView tv_title;
	private TextView tv_title_right;
	private String mTitle;
	private String mType;
	private String mInfo;
	private Handler mHandler = new Handler();
	private int flag = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		Intent intent = getIntent();
		mTitle = intent.getStringExtra("title");
		mType = intent.getStringExtra("type");
		mInfo = intent.getStringExtra("info");
		
		initView();
		initData();
		initListener();
		
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
	
	@Override
	protected void onStart() {
		super.onStart();
		toggleInputState(true);
	}
	
	private void initView() {
		tv_user_info = (TextView)this.findViewById(R.id.tv_user_info);
		et_user_info = (EditText)this.findViewById(R.id.et_user_info);
		iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
		iv_title_left.setVisibility(View.VISIBLE);
		tv_title = (TextView)this.findViewById(R.id.tv_title);
		tv_title_right = (TextView)this.findViewById(R.id.tv_title_right);
		tv_title_right.setTextSize(20);
		tv_title_right.setVisibility(View.VISIBLE);
	}

	private void initListener() {
		iv_title_left.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				toggleInputState(false);
				setResult(0, null);
				finish();
			}
		});
		
		tv_title_right.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//TODO ????????????????????????
				toggleInputState(false);
				String info = et_user_info.getText().toString();
				if (info.equals("")){
					Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
					return;
				}
				if(!TextUtils.isEmpty(info)){
					if("????????????".equals(mTitle.trim())){
						flag = 1;
						new ModifyInfoNet().setPhone(info, new EntityCallback() {
							@Override
							public void connectCallback(EntityrResult entityrResult) {
								if(EntityType.messagetrue==entityrResult.entityType){
									Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
								}
							}
						});
					}else if("????????????".equals(mTitle.trim())){
						flag = 2;
						new ModifyInfoNet().setAddress(info, new EntityCallback() {
							@Override
							public void connectCallback(EntityrResult entityrResult) {
								if(EntityType.messagetrue==entityrResult.entityType){
									Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
								}
							}
						});
					}else if("????????????".equals(mTitle.trim())){
						flag = 3;
						new ModifyInfoNet().setDescription(info, new EntityCallback() {
							@Override
							public void connectCallback(EntityrResult entityrResult) {
								if(EntityType.messagetrue==entityrResult.entityType){
									Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
								}
							}
						});
					}else if("????????????".equals(mTitle.trim())){
						flag = 4;
						new ModifyInfoNet().setPassword(info, new EntityCallback() {
							@Override
							public void connectCallback(EntityrResult entityrResult) {
								if(EntityType.messagetrue==entityrResult.entityType){
									Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();  
								}
							}
						});
					}else if("????????????".equals(mTitle.trim())){
						SharedPreferencesUtil.setString(getApplicationContext(), "BaseUrl", info);
						SharedPreferencesUtil.setParam(getApplicationContext(), "isFirst", false);
						NetContacts.getInstance().setBaseUrl(info);
						startActivity(new Intent(getApplicationContext(),LoginActivity.class));
						finish();
						return ;
					}
				}
				
				Intent data = new Intent();
				data.putExtra("info", info);
				data.putExtra("flag", flag);
				setResult(0, data);
				finish();
			}
		});
	}

    private void initData() {
        String infodata = (String) SharedPreferencesUtil.getParam(getApplicationContext(), "BaseUrl", "");
        et_user_info.setText(mInfo);
        if (!infodata.isEmpty()&mTitle.trim().equals("????????????")){
            et_user_info.setText(infodata);
        }else{
			et_user_info.setText(mInfo);
		}
        iv_title_left.setImageResource(R.drawable.rightmenu);
        tv_title_right.setText("??????");
        tv_title.setText(mTitle);
        tv_user_info.setText(mType);
    }

    public void toggleInputState(boolean isShow) {
        final InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		if(isShow){
			//???????????????
			mHandler.postDelayed(new Runnable(){
				@Override
				public void run() {
					imm.showSoftInput(et_user_info, 0);
				}
			}, 300);
		}else{
			//???????????????
			imm.hideSoftInputFromWindow(et_user_info.getWindowToken(), 0);
		}
	}
}
