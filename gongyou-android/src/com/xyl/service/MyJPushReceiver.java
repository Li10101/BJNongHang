package com.xyl.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.xyl.base.BaseNet;
import com.xyl.domain.AlarmsBean;
import com.xyl.domain.CasesBean;
import com.xyl.domain.FindNoticeBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.PurchaseBean;
import com.xyl.domain.ServiceBeanRecords;
import com.xyl.domain.WorkInfoBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.net.UserManager;
import com.xyl.ui.activity.ApprolvalAllActivity;
import com.xyl.ui.activity.HomeActivity;
import com.xyl.ui.activity.OrderActivity;
import com.xyl.ui.activity.OrderStateActivity;
import com.xyl.ui.activity.WorkOrderActivity;
import com.xyl.ui.view.FunctionView;
import com.xyl.ui.view.SlidingLeftView;
import com.xyl.ui.widget.NotifyTextView;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;
import me.leolin.shortcutbadger.ShortcutBadger;

public class MyJPushReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";
	private FunctionView.MyBaseAdapter functionAdapter;
	private Context context;
	UserManager loginNet;
	int count ;
	private String caseCode;
	private CasesBean.Records caseBean;
	private ServiceBeanRecords serviceBeanRecords;
	private FindNoticeBean.RecordsBean findNoticeRecordsBean;
	private int position;
	private AlarmsBean.Records alarmRecords;
	private SelectAllPersonalBean.RecordsBean renShirecordsBean;
	private PurchaseBean.RecordsBean chuRurecordsBean;
	HomeActivity homeActivity;

	@Override
	public void onReceive(Context context, Intent intent) {
		homeActivity = new HomeActivity();
		this.context = context;
		Bundle bundle = intent.getExtras();
		loginNet = new UserManager();
		//接受到推送下来的通知
		if(JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())){
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			try {
				JSONObject jsonObject = new JSONObject(extras);
				String value = jsonObject.getString("tag");
				position = Integer.parseInt(value)-100;
				switch (position){
					case 7:
					Integer count = (Integer)SharedPreferencesUtil.getParam(context, "PersonnelCount", 0);
					SharedPreferencesUtil.setParam(context, "PersonnelCount", count+1);
//						NotifyTextView notifyTextView = homeActivity.getgetNotifyView();
//						notifyTextView.setNotifyCount(count);
						break;
				}
				Integer count = (Integer)SharedPreferencesUtil.getParam(context, String.valueOf(position), 0);
				SharedPreferencesUtil.setParam(context, String.valueOf(position), count+1);
				Integer push1 = (Integer)SharedPreferencesUtil.getParam(context, String.valueOf(1), 0);
				Integer push0 = (Integer)SharedPreferencesUtil.getParam(context, String.valueOf(0), 0);
				int count1 = push1+push0;
				ShortcutBadger.applyCount(context, count1);
				functionAdapter = FunctionView.getFunctionAdapter();
				//FunctionView.functionList.get(position+3).setNotifyCount(count+1);
				if(functionAdapter!=null){
					functionAdapter.notifyDataSetChanged();
				}
				if(SlidingLeftView.slidingList.size()>0){
					SlidingLeftView.slidingList.get(position).setNotifyCount(count+1);
				}
			    } catch (JSONException e) {
				e.printStackTrace();
			    }
			   /*Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
	            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
	            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);*/
		}
		//用户点击打开了通知
		else if(JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())){
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			if(TextUtils.isEmpty(extras)){
                   return;
			}
			JSONObject jsonObject = null;
			int  value = 0;
			try {
				Gson gson = new Gson();
				jsonObject = new JSONObject(extras);
				value = Integer.parseInt(jsonObject.getString("status"));
				//String case1 = jsonObject.getString("case");
				if (value==1){
					String case1 = jsonObject.getString("service");
					serviceBeanRecords = gson.fromJson(case1, ServiceBeanRecords.class);
				}else if(value==0){
					String case1 = jsonObject.getString("case");
					caseBean = gson.fromJson(case1, CasesBean.Records.class);
				}else if(value ==2){
					String case1 = jsonObject.getString("entity");
					findNoticeRecordsBean = gson.fromJson(case1, FindNoticeBean.RecordsBean.class);
				}else if(value ==3){
					String case1 = jsonObject.getString("alarm");
					alarmRecords = gson.fromJson(case1, AlarmsBean.Records.class);
				}else if(value ==4){
					String case1 = jsonObject.getString("entity");
					chuRurecordsBean = gson.fromJson(case1, PurchaseBean.RecordsBean.class);
				}else if(value ==5){
					String case1 = jsonObject.getString("entity");
					renShirecordsBean = gson.fromJson(case1, SelectAllPersonalBean.RecordsBean.class);
				}else{
					return;
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			  String username = null;
			  String password = null;
			boolean checked = (Boolean)SharedPreferencesUtil.getParam(context, SharedPreferencesUtil.KEY_SAVE_USERINFO, false);
			if(checked){
		      username =  SharedPreferencesUtil.getString(context, SharedPreferencesUtil.KEY_UERNAME);
			  password = SharedPreferencesUtil.getString(context, SharedPreferencesUtil.KEY_PASSWORD);
			}
			if (value==1){
				login(username,password,serviceBeanRecords);
			}else if (value==0){
				login(username,password,caseBean);
			}else if(value==2){
				login(username,password,findNoticeRecordsBean);
			}else if (value==3){
				login(username,password,alarmRecords);
			}else if (value==4){
				login(username,password,chuRurecordsBean);
			}else if (value==5){
				login(username,password,renShirecordsBean);
			}else{
				ToastUtil.showToast("工单已经被处理");
			}


			JPushInterface.reportNotificationOpened(context, bundle.getString(JPushInterface.EXTRA_MSG_ID));
		    // Intent i = new Intent(context, HomeActivity.class);//自定义打开的界面
			//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // context.startActivity(i);
		}
		
	}

	private void login(String username, String password, final PurchaseBean.RecordsBean chuRurecordsBean) {
		for(int a=0; a<6; a++){
			SharedPreferencesUtil.setParam(context, String.valueOf(a), 0);
		}

		loginNet.userLoginIn(username, password, new BaseNet.BaseCallback<LoginBean>() {

			@Override
			public void messageSuccess(LoginBean bean){
//				Intent intent = new Intent(context,ApprolvalAllActivity.class);
//				intent.putExtra("recordsBean", renShirecordsBean);
//				intent.putExtra("type", renShirecordsBean.getRenShiType());
//				context.startActivity(intent);

				Intent intent = new Intent(context,OrderActivity.class);
				intent.putExtra("LoginBean", bean);
				intent.putExtra("PurchaseBean", chuRurecordsBean);
				intent.putExtra("type", chuRurecordsBean.getType());
				context.startActivity(intent);


			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}
	private void login(String username, String password, final SelectAllPersonalBean.RecordsBean renShirecordsBean) {
		for(int a=0; a<6; a++){
			SharedPreferencesUtil.setParam(context, String.valueOf(a), 0);
		}

		loginNet.userLoginIn(username, password, new BaseNet.BaseCallback<LoginBean>() {

			@Override
			public void messageSuccess(LoginBean bean){
				Intent intent = new Intent(context,ApprolvalAllActivity.class);
				intent.putExtra("LoginBean", bean);
				intent.putExtra("recordsBean", renShirecordsBean);
				intent.putExtra("type", renShirecordsBean.getRenShiType());
				context.startActivity(intent);
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}
	private void login(String username, String password, AlarmsBean.Records alarmRecords) {
		for(int a=0; a<6; a++){
			SharedPreferencesUtil.setParam(context, String.valueOf(a), 0);
		}

		loginNet.userLoginIn(username, password, new BaseNet.BaseCallback<LoginBean>() {

			@Override
			public void messageSuccess(LoginBean bean){
				Intent intent = new Intent(context,WorkOrderActivity.class);
				intent.putExtra("LoginBean", bean);
				WorkInfoBean infoBean = new WorkInfoBean();
				infoBean.des = "设备报警";
				intent.putExtra("WorkInfoBean", infoBean);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}

	private void login(String username, String password, final FindNoticeBean.RecordsBean findNoticeRecordsBean) {
		for(int a=0; a<6; a++){
			SharedPreferencesUtil.setParam(context, String.valueOf(a), 0);
		}

		loginNet.userLoginIn(username, password, new BaseNet.BaseCallback<LoginBean>() {

			@Override
			public void messageSuccess(LoginBean bean){
				Intent intent = new Intent(context,OrderStateActivity.class);
				intent.putExtra("LoginBean", bean);
				intent.putExtra("FindNoticeBean", findNoticeRecordsBean);
				intent.putExtra("flag", 5);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}

	private void login(String userName, String passWord, final CasesBean.Records caseBean) {
		for(int a=0; a<6; a++){
			SharedPreferencesUtil.setParam(context, String.valueOf(a), 0);
		}
		
		loginNet.userLoginIn(userName, passWord, new BaseNet.BaseCallback<LoginBean>() {
			
			@Override
			public void messageSuccess(LoginBean bean){
				Intent intent = new Intent(context,OrderStateActivity.class);
				intent.putExtra("LoginBean", bean);
				intent.putExtra("CasesBean", caseBean);
				intent.putExtra("flag", 2);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);

			}
            
			@Override
			public void messageFailure(MessageBean backError) {
				
			}
            
			@Override
			public void connectFailure(MessageBean messageBean) {
				
			}
		});
	}
	private void login(String userName, String passWord, final ServiceBeanRecords beanRecords) {
		for(int a=0; a<6; a++){
			SharedPreferencesUtil.setParam(context, String.valueOf(a), 0);
		}

		loginNet.userLoginIn(userName, passWord, new BaseNet.BaseCallback<LoginBean>() {

			@Override
			public void messageSuccess(LoginBean bean){
				Intent intent = new Intent(context,OrderStateActivity.class);
				intent.putExtra("LoginBean", bean);
				intent.putExtra("serviceBean", beanRecords);
				intent.putExtra("flag", 1);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}
}