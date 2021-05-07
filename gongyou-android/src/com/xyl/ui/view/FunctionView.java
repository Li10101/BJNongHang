package com.xyl.ui.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.base.BaseResource;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.domain.personnel.HomeBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.ui.activity.AnswerListActivity;
import com.xyl.ui.activity.ApprovalActivity;
import com.xyl.ui.activity.BossInfoActivity;
import com.xyl.ui.activity.CultivateActivity;
import com.xyl.ui.activity.DeviceActivity;
import com.xyl.ui.activity.EmergencyActivity;
import com.xyl.ui.activity.HealthCardActivity;
import com.xyl.ui.activity.HealthListActivity;
import com.xyl.ui.activity.HomeActivity;
import com.xyl.ui.activity.InventoryActivity;
import com.xyl.ui.activity.LeaveActivity;
import com.xyl.ui.activity.MaterilActivity;
import com.xyl.ui.activity.NoticeListActivity;
import com.xyl.ui.activity.NoticeSortActivity;
import com.xyl.ui.activity.PurchaseActivity;
import com.xyl.ui.activity.ReimbursementActivity;
import com.xyl.ui.activity.SealActivity;
import com.xyl.ui.activity.StandbyActivity;
import com.xyl.ui.activity.StorageRoomActivity;
import com.xyl.ui.activity.UserRepairsActivity;
import com.xyl.ui.activity.WorkOrderActivity;
import com.xyl.ui.widget.NotifyTextView;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.UIUtils;

public class FunctionView extends View {

	private View functionView;
	private GridView gv_view_function;
	private Context mContext;
	private ArrayList<WorkInfoBean> resourceGrid;
	private LoginBean loginBean;
	public static MyBaseAdapter mAdapter;
	private int tab;
	private List<HomeBean> homebeans;

	public FunctionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FunctionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FunctionView(Context context) {
		super(context);
		mContext = context;
		initView();
		initListener();
		initData();
	}
	
	private void initView() {
		functionView = View.inflate(mContext, R.layout.view_function, null);
		gv_view_function =  functionView
				.findViewById(R.id.gv_view_function);
	}

	private void initListener() {
		gv_view_function.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				gridSelecter(position);
			}
		});
	}
	
	public void gridSelecter(int position){
		String des = resourceGrid.get(position).des;
//		String modularName = homebeans.get(position).getName();
//		String modularCode = homebeans.get(position).getCode();
//		if (modularCode.equalsIgnoreCase("Zichanguanli")){
//			tab = 1;
//			Intent intent = new Intent(mContext,DeviceActivity.class);
//			intent.putExtra("LoginBean", loginBean);
//			intent.putExtra("tab",tab);
//			intent.putExtra("title", modularName);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Yufangxingweibao")){
//			tab = 0;
//			Intent intent = new Intent(mContext,DeviceActivity.class);
//			intent.putExtra("LoginBean", loginBean);
//			intent.putExtra("tab",tab);
//			intent.putExtra("title", modularName);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Shebeiweihu")){
//			return;
//
//		}else if (modularCode.equalsIgnoreCase("Gongdan")){
//			Intent intent = new Intent(mContext,BossInfoActivity.class);
//			intent.putExtra("title", modularName);
//			intent.putExtra("LoginBean", loginBean);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Shebeibaojing")){
//			Intent intent = new Intent(mContext,BossInfoActivity.class);
//			intent.putExtra("title", modularName);
//			intent.putExtra("LoginBean", loginBean);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Nengyuanguanli")){
//			Intent intent = new Intent(mContext,BossInfoActivity.class);
//			intent.putExtra("title", modularName);
//			intent.putExtra("LoginBean", loginBean);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Churuku")){
//			Intent intent = new Intent(mContext,StorageRoomActivity.class);
//			intent.putExtra("title", modularName);
//			intent.putExtra("LoginBean", loginBean);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Qingjia")){
//			Intent intent = new Intent(mContext,LeaveActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Tongyongguanli")){
//			Intent intent = new Intent(mContext,ApprovalActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Yingjicaigou")){
//			Intent intent = new Intent(mContext,EmergencyActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Caigou")){
//			Intent intent = new Intent(mContext,PurchaseActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Yongyinshenqing")){
//			Intent intent = new Intent(mContext,SealActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Beiyongjin")){
//			Intent intent = new Intent(mContext,StandbyActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Baoxiao")){
//			Intent intent = new Intent(mContext,ReimbursementActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Jiankangbiao")){
//			Intent intent = new Intent(mContext,HealthListActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Peixun")){
//			Intent intent = new Intent(mContext,CultivateActivity.class);
//			mContext.startActivity(intent);
//		}else if (modularCode.equalsIgnoreCase("Tongzhi")){
//			Intent intent = new Intent(mContext, NoticeSortActivity.class);
//			mContext.startActivity(intent);
//		}else {
//			//TODO 跳到ListView展示条目的页面
//			Intent intent = new Intent(mContext,WorkOrderActivity.class);
//			intent.putExtra("position", position);
//			intent.putExtra("LoginBean", loginBean);
//			intent.putExtra("WorkInfoBean", homebeans.get(position).getName());
//			mContext.startActivity(intent);
//		}
		if(position==0 && loginBean.type.equals("12")||loginBean.type.equals("3")){
			Intent intent = new Intent(mContext,UserRepairsActivity.class);
			intent.putExtra("LoginBean", loginBean);
			mContext.startActivity(intent);
		}else if(!loginBean.type.equals("5")&&"资产管理".equals(des)||("预防维保".equals(des)|| "设备维护".equals(des))){
			if ("预防维保".equals(des)|| "设备维护".equals(des)){
				tab = 0;
			}else if ("资产管理".equals(des)){
				tab = 1;
			}
			Intent intent = new Intent(mContext,DeviceActivity.class);
			intent.putExtra("LoginBean", loginBean);
			intent.putExtra("position", position);
			intent.putExtra("tab",tab);
			intent.putExtra("title", des);
			mContext.startActivity(intent);
		}else if("5".equals(loginBean.type) && ("工单".equals(des) || "设备报警".equals(des)|| "能源管理".equals(des)|| "资产管理".equals(des))){
			Intent intent = new Intent(mContext,BossInfoActivity.class);
			intent.putExtra("title", des);
			intent.putExtra("LoginBean", loginBean);
			mContext.startActivity(intent);
		}else if("空气质量".equals(des)){
			Toast.makeText(mContext, "暂未开通", Toast.LENGTH_SHORT).show();
			return ;
		}else if("出入库".equals(des)){
			Intent intent = new Intent(mContext,StorageRoomActivity.class);
			intent.putExtra("title", des);
			intent.putExtra("LoginBean", loginBean);
			mContext.startActivity(intent);
		}else if("请假".equals(des)){
			Intent intent = new Intent(mContext,LeaveActivity.class);
			mContext.startActivity(intent);

		}else if("通用审批".equals(des)){
			Intent intent = new Intent(mContext,ApprovalActivity.class);
			mContext.startActivity(intent);
		}else if("应急采购".equals(des)){
			Intent intent = new Intent(mContext,EmergencyActivity.class);
			mContext.startActivity(intent);

		}else if("采购".equals(des)){
			Intent intent = new Intent(mContext,PurchaseActivity.class);
			mContext.startActivity(intent);

		}else if("用印申请".equals(des)){
			Intent intent = new Intent(mContext,SealActivity.class);
			mContext.startActivity(intent);
		}else if("备用金".equals(des)){
			Intent intent = new Intent(mContext,StandbyActivity.class);
			mContext.startActivity(intent);
		}else if("报销".equals(des)){
			Intent intent = new Intent(mContext,ReimbursementActivity.class);
			mContext.startActivity(intent);

		}else if("健康表".equals(des)){
			Intent intent = new Intent(mContext,HealthListActivity.class);
			mContext.startActivity(intent);

		}else if("培训".equals(des)){
			Intent intent = new Intent(mContext,CultivateActivity.class);
			mContext.startActivity(intent);

		}else if("通知".equals(des)){
			Intent intent = new Intent(mContext, NoticeSortActivity.class);
			mContext.startActivity(intent);


		}else{
			//TODO 跳到ListView展示条目的页面
			Intent intent = new Intent(mContext,WorkOrderActivity.class);
			intent.putExtra("position", position);
			intent.putExtra("LoginBean", loginBean);
			intent.putExtra("WorkInfoBean", resourceGrid.get(position).des);
			mContext.startActivity(intent);
		}
	}

	private void initData() {
		loginBean = (LoginBean)(((HomeActivity)mContext).getIntent().getSerializableExtra("LoginBean"));
		//getFindPermissions();
		BaseResource resource = new BaseResource();
		resourceGrid = resource.getResourceGrid(loginBean.type);
		mAdapter = new MyBaseAdapter();
		gv_view_function.setAdapter(mAdapter);
	}

//	private void getFindPermissions() {
//		new OrderManager().findPermissions(7,0,new BaseNet.BaseCallback<List<HomeBean>>() {
//			@Override
//			public void messageSuccess(List<HomeBean> bean) {
//				if (bean == null){
//					return;
//				}
//				homebeans = bean;
//				mAdapter = new MyBaseAdapter();
//				gv_view_function.setAdapter(mAdapter);
//			}
//
//			@Override
//			public void messageFailure(MessageBean backError) {
//
//			}
//
//			@Override
//			public void connectFailure(MessageBean messageBean) {
//
//			}
//		});
//
//	}
	public int getFunctionCount(){
		return resourceGrid.size();
	}

	public static MyBaseAdapter getFunctionAdapter(){
		return mAdapter;
	}

	public View getView() {
		return functionView;
	}
	
//	public class MyBaseAdapter extends BaseAdapter{
//
//		private ViewHolder viewHolder;
//
//		@Override
//		public int getCount() {
//			return homebeans.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//			return null;
//		}
//
//		@Override
//		public long getItemId(int position) {
//			return position;
//		}
//
//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			if (convertView == null){
//				convertView = View.inflate(mContext, R.layout.item_function, null);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dp2px(80),UIUtils.dp2px(80));
//                layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
//                convertView.setLayoutParams(layoutParams);
//				viewHolder = new ViewHolder();
//				viewHolder.ll_item = convertView.findViewById(R.id.ll_item);
////				AbsListView.LayoutParams params = new AbsListView.LayoutParams(size, size);
////				viewHolder.ll_item.setLayoutParams(params);
//				viewHolder.iv_gvitem = convertView.findViewById(R.id.iv_gvitem);
//				viewHolder.tv_gvitem = convertView.findViewById(R.id.tv_gvitem);
//				//viewHolder.tv_notify_count = convertView.findViewById(R.id.tv_notify_count);
//				String url = NetContacts.getInstance().SERVER_URL + "/" + homebeans.get(position).getImgUrl();
//				Glide.with(mContext).load(url).into(viewHolder.iv_gvitem);
//				viewHolder.tv_gvitem.setText(homebeans.get(position).getName());
//				//viewHolder.tv_notify_count.setNotifyCount((Integer)SharedPreferencesUtil.getParam(mContext, String.valueOf(position), 0));
//				convertView.setTag(viewHolder);
//			     }else {
//				viewHolder = (ViewHolder) convertView.getTag();
//			}
//
////            if(convertView == null){
////              cv = View.inflate(R.layout.item_function, null);
////              vh = new ViewHolder();
////              vh.cv = (ImageView) cv.findViewById(R.id.imageview);
////
////              vh.cv.setLayoutParams(params);
////              cv.setTag(vh);
////             }else{
////             vh = (ViewHolder) cv.getTag();
////             }
//
////
//			return convertView;
//		}
//		class ViewHolder{
//			RelativeLayout ll_item;
//			ImageView iv_gvitem;
//			TextView tv_gvitem;
//			//NotifyTextView tv_notify_count;
//		}
//	}

	public class MyBaseAdapter extends BaseAdapter{

		private ViewHolder viewHolder;

		@Override
		public int getCount() {
			return resourceGrid.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {


			if (convertView == null){
				convertView = View.inflate(mContext, R.layout.item_function, null);
				Log.e("height",convertView.getHeight()+":"+convertView.getWidth());
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dp2px(80),UIUtils.dp2px(80));
				layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
				convertView.setLayoutParams(layoutParams);
				viewHolder = new ViewHolder();
				viewHolder.ll_item = convertView.findViewById(R.id.ll_item);
//				AbsListView.LayoutParams params = new AbsListView.LayoutParams(size, size);
//				viewHolder.ll_item.setLayoutParams(params);
				viewHolder.iv_gvitem = convertView.findViewById(R.id.iv_gvitem);
				viewHolder.tv_gvitem = convertView.findViewById(R.id.tv_gvitem);
				//viewHolder.tv_notify_count = convertView.findViewById(R.id.tv_notify_count);
				viewHolder.iv_gvitem.setImageResource(resourceGrid.get(position).resId);
				viewHolder.tv_gvitem.setText(resourceGrid.get(position).des);
				//viewHolder.tv_notify_count.setNotifyCount((Integer)SharedPreferencesUtil.getParam(mContext, String.valueOf(position), 0));
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

//            if(convertView == null){
//              cv = View.inflate(R.layout.item_function, null);
//              vh = new ViewHolder();
//              vh.cv = (ImageView) cv.findViewById(R.id.imageview);
//
//              vh.cv.setLayoutParams(params);
//              cv.setTag(vh);
//             }else{
//             vh = (ViewHolder) cv.getTag();
//             }

//
			return convertView;
		}
		class ViewHolder{
			RelativeLayout ll_item;
			ImageView iv_gvitem;
			TextView tv_gvitem;
			//NotifyTextView tv_notify_count;
		}
	}
}