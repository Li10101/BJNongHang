package com.xyl.ui.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

 



import com.xyl.R;
import com.xyl.domain.WorkInfoBean;
import com.xyl.ui.activity.FindworkListViewActivity;
import com.xyl.ui.activity.OrderStateActivity;
public class ChooseWorkerWidget extends LinearLayout implements OnClickListener {

	private View view;
	private ArrayList<ImageButton> arrImage;
	private Context mContext;
	private LinearLayout ll_root;
	private ImageButton ib_title;
	private static int i=-1;
	public ChooseWorkerWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
		initListener();
		mContext = context;
	}

	private void initWidget() {
		ll_root = (LinearLayout) view.findViewById(R.id.ll_root);
		ib_title = (ImageButton) view.findViewById(R.id.ib_title);
	}
	private void initListener() {
		ib_title.setOnClickListener(this);
	}
	private void initView(Context context) {
		view = View.inflate(context, R.layout.widget_choose_worker, this);
	}

	//改动：跳转工人选择页面
	@Override
	public void onClick(View v) {
		if(v == ib_title){
//			((Activity)mContext).startActivityForResult(new Intent(mContext,OrderStateActivity.class), 4);
			Intent intent = new Intent(mContext, FindworkListViewActivity.class);
			WorkInfoBean infoBean = new WorkInfoBean();
			infoBean.des = "工人选择";
			intent.putExtra("WorkInfoBean", infoBean);
			intent.putExtra("action", "addWorker");
			intent.putExtra("CasesBean", ((OrderStateActivity)mContext).getIntent().getSerializableExtra("CasesBean"));
			((Activity)mContext).startActivityForResult(intent, 0);
		}else{
			((LinearLayout)(v.getParent().getParent())).removeView((View)v.getParent());
			i--;
		}
	}

	/**
	 * 返回结果时调用这个方法 设置名字
	 * @param name
	 */
	public void setName(String name) {
		View view = View.inflate(mContext, R.layout.view_gongdan, null);
		view.findViewById(R.id.ib_s).setOnClickListener(this);
		TextView tv_s = (TextView)view.findViewById(R.id.tv_s);
		tv_s.setText(name);
		ll_root.addView(view);
		i++;
	}	
}
