package com.xyl.ui.widget;

import com.xyl.R;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

 

public class UserGroupView extends LinearLayout {

	private View view_user_group;
	private ImageView iv_info_type;
	private TextView tv_user_des;
	private LinearLayout ll_user_group;
	private String des;

	public UserGroupView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public UserGroupView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public UserGroupView(Context context) {
		super(context);
		initView(context);
	}

	public void initView(Context context){
		view_user_group = View.inflate(context, R.layout.view_user_group, this);
		
		ll_user_group = (LinearLayout)view_user_group.findViewById(R.id.ll_user_group);
		
		iv_info_type = (ImageView)view_user_group.findViewById(R.id.iv_info_type);
		tv_user_des = (TextView)view_user_group.findViewById(R.id.tv_user_des);
	}
	
	public void setUserInfo(String des, int resId){
		if(!TextUtils.isEmpty(des)){
			tv_user_des.setText(des);
			this.des = des;
		}
		iv_info_type.setImageResource(resId);
	}
	
	public void setMyGroupClickListener(OnClickListener  onClickListener){
		ll_user_group.setOnClickListener(onClickListener);
	}
	
	public String getInfoTitle(){
		System.out.println("des:"+this.des);
		return des.split(":")[0];
	}
	
	public String getInfoType(){
		System.out.println("des:"+this.des);
		return des.split(":")[0].concat(":");
	}
	
	public String getUserInfo(){
			return des.split(":")[1];
	}
}
