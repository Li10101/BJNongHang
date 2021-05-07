package com.xyl.ui.widget;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.global.NetContacts;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

 

public class SlidingGroupView extends LinearLayout {

	private View view_sliding_group;
	private ImageView iv_work_info;
	private TextView tv_work_des;
	private LinearLayout ll_sliding_group;
	private NotifyTextView tv_notify_count;
	private Context mContext;

	public SlidingGroupView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public SlidingGroupView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public SlidingGroupView(Context context) {
		super(context);
		mContext = context;
		initView(context);
	}

	public void initView(Context context){
		view_sliding_group = View.inflate(context, R.layout.view_sliding_group, this);
		
		ll_sliding_group = (LinearLayout)view_sliding_group.findViewById(R.id.ll_sliding_group);
		
		iv_work_info = (ImageView)view_sliding_group.findViewById(R.id.iv_work_info);
		tv_work_des = (TextView)view_sliding_group.findViewById(R.id.tv_work_des);
		tv_notify_count = (NotifyTextView)view_sliding_group.findViewById(R.id.tv_notify_count);
	}
	
	public void setUserInfo(String des, String resId){
		if(!TextUtils.isEmpty(des)){
			tv_work_des.setText(des);
		}
		//iv_work_info.setImageResource(resId);
		String url = NetContacts.getInstance().SERVER_URL + "/" + resId;
		Glide.with(mContext).load(url).into(iv_work_info);
	}

	public void setUserInfo(String des, int resId){
		if(!TextUtils.isEmpty(des)){
			tv_work_des.setText(des);
		}
		iv_work_info.setImageResource(resId);
	}
	public void setMyGroupClickListener(OnClickListener  l){
		ll_sliding_group.setOnClickListener(l);
	}
	
	public View getView(){
		return view_sliding_group;
	}

	public NotifyTextView getNotifyTextView(){
		return tv_notify_count;
	}
}
