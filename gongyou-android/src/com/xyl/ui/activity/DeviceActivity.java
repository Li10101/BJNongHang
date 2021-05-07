package com.xyl.ui.activity;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.domain.AssetBean;
import com.xyl.domain.CategoriesBean;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.FindByEquipmentNoBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.global.NetContacts;
import com.xyl.net.AssetManager;
import com.xyl.net.DeviceManager;
import com.xyl.qr_codescan.MipcaActivityCapture;
import com.xyl.utils.SharedPreferencesUtil;

import org.apache.commons.lang.StringUtils;

import androidx.core.content.res.ResourcesCompat;
import cn.jpush.android.api.JPushInterface;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

import static com.xyl.utils.ToastUtil.showToast;


public class DeviceActivity extends Activity {

	private ImageView iv_title_left;
	private ImageView iv_edit;
	private TextView tv_title;
	private ListView lv_device;
	private String[] names;
	private int[] res;
	private List<CategoriesBean> categorList;
	private List<AssetBean> assetList;
	private final static int SCANNIN_GREQUEST_CODE = 1;
	private int tab;
	private MyBaseAdapter myBaseAdapter;
	private FindByAssetNoBean.RecordsBean assetrecordsBean;
	private ArrayList<PermissionItem> permissionItems;
	private LoginBean loginBean;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device);

		initView();
		initListener();
		initData();
		resetNotifyList();
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
	
	public void resetNotifyList(){
		int position = this.getIntent().getIntExtra("position", -1);
		if(position!=-1){
			/*if(SlidingLeftView.slidingList!=null && SlidingLeftView.slidingList.size()>0){
				SlidingLeftView.slidingList.get(position).setNotifyCount(0);
			}*/
			SharedPreferencesUtil.setParam(this, String.valueOf(position), 0);
		}
	}

	private void initView() {
		iv_title_left =  this.findViewById(R.id.iv_title_left);
		tv_title =  this.findViewById(R.id.tv_title);
		iv_edit  =  this.findViewById(R.id.iv_edit);
		lv_device =  this.findViewById(R.id.lv_device);
		lv_device.setDivider(null);

		permissionItems = new ArrayList<PermissionItem>();
		permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
		permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage));
		HiPermission.create(DeviceActivity.this)
				.title("开启权限")
				.permissions(permissionItems)
				.filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//图标的颜色
				.msg("为了软件的正常，请开启权限！")
				.style(R.style.PermissionBlueStyle)
				.checkMutiPermission(new PermissionCallback() {
					@Override
					public void onClose() {
						showToast("用户关闭权限申请");
					}

					@Override
					public void onFinish() {


					}

					@Override
					public void onDeny(String permission, int position) {
					}

					@Override
					public void onGuarantee(String permission, int position) {

					}
				});
	}

	private void initListener() {
		iv_title_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		lv_device.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent intent = new Intent(DeviceActivity.this, WorkOrderActivity.class);
				intent.putExtra("LoginBean", getIntent().getSerializableExtra("LoginBean"));
				switch (tab) {
					case 1:
						WorkInfoBean infoBean = new WorkInfoBean(res[position], categorList.get(position).name);
						intent.putExtra("WorkInfoBean", categorList.get(position).name);
						intent.putExtra("CategoriesBean", categorList.get(position));
						startActivity(intent);
						break;
					case 2:
						WorkInfoBean info = new WorkInfoBean(res[position], assetList.get(position).getPhysicalAssetName());
						intent.putExtra("LoginBean", getIntent().getSerializableExtra("LoginBean"));
						intent.putExtra("WorkInfoBean", "资产管理");
						intent.putExtra("assetId",assetList.get(position).getPhysicalAssetName());
						intent.putExtra("AssetBean", assetList.get(position));
						startActivity(intent);
						break;
				}

			}
		});
		iv_edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//startActivity(new Intent(DeviceActivity.this,SaoMiaoActivity.class));
			Intent intent = new Intent(DeviceActivity.this, MipcaActivityCapture.class);
			intent.putExtra("LoginBean", loginBean);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			/*Intent intent = new Intent();
				intent.setClass(DeviceActivity.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
			//startActivity(intent);
			}
		});
		
	}
	int flag;
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:

			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				String result = bundle.getString("result");
				boolean isUser = StringUtils.contains(result, "userEquipment");
				if (isUser){
					new AssetManager().findAssetEquipmentNo(result, new BaseCallback<FindByAssetNoBean.RecordsBean>() {
						@Override
						public void messageSuccess(FindByAssetNoBean.RecordsBean bean) {
							flag=10;
							Intent intent = new Intent(DeviceActivity.this,OrderStateActivity.class);
							intent.putExtra("LoginBean", loginBean);
							intent.putExtra("flag", flag);
							intent.putExtra("FindByAssetNoBean", bean);
							startActivity(intent);

						}

						@Override
						public void messageFailure(MessageBean backError) {

						}

						@Override
						public void connectFailure(MessageBean messageBean) {

						}
					});

				}else{
                 new DeviceManager().findEquipmentNo(result, new BaseCallback<FindByEquipmentNoBean>() {
					 @Override
					 public void messageSuccess(FindByEquipmentNoBean bean) {
						 flag=8;
						 Intent intent = new Intent(DeviceActivity.this,OrderStateActivity.class);
						 intent.putExtra("LoginBean", loginBean);
						 intent.putExtra("flag", flag);
						 intent.putExtra("FindByEquipmentNo", bean);
						 startActivity(intent);
					 }

					 @Override
					 public void messageFailure(MessageBean backError) {

					 }

					 @Override
					 public void connectFailure(MessageBean messageBean) {

					 }
				 });
				}


				//mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
			}
			break;
		}
    }	
	private void initData() {
		iv_title_left.setImageResource(R.drawable.rightmenu);
		iv_title_left.setVisibility(View.VISIBLE);
		iv_edit.setImageResource(R.drawable.sao);
		loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
		iv_edit.setVisibility(View.VISIBLE);
		int tabs = this.getIntent().getIntExtra("tab", 0);
		title = getIntent().getStringExtra("title");
		tv_title.setText(title);
		res = new int[] { R.drawable.dv_bx, R.drawable.dv_mj, R.drawable.dv_kt,
				R.drawable.dv_kq, R.drawable.dv_tc, R.drawable.dv_lj,
				R.drawable.dv_dt, R.drawable.dv_ny, R.drawable.dv_xf,
				R.drawable.dv_gn, R.drawable.dv_kqzl, R.drawable.dv_dg,
				R.drawable.dv_mk, R.drawable.dv_dl, R.drawable.dv_zs,
				R.drawable.dv_jk, R.drawable.fense, R.drawable.dv_tf,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_kq, R.drawable.dv_tc, R.drawable.dv_lj,
				R.drawable.dv_dt, R.drawable.dv_ny, R.drawable.dv_xf,
				R.drawable.dv_gn, R.drawable.dv_kqzl, R.drawable.dv_dg,
				R.drawable.dv_mk, R.drawable.dv_dl, R.drawable.dv_zs,
				R.drawable.dv_jk, R.drawable.fense, R.drawable.dv_tf,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb,
				R.drawable.dv_fk, R.drawable.dv_sxh, R.drawable.dv_sb};
		myBaseAdapter = new MyBaseAdapter();
		if (tabs == 0){
			getCategoies();

		}else if (tabs == 1){
            getAssetsClassify();
		}

	}

	private void getAssetsClassify() {
		new AssetManager().assets(new BaseCallback<List<AssetBean>>() {

			@Override
			public void messageSuccess(List<AssetBean> bean) {
				assetList = bean;
				myBaseAdapter.setTab(2);
				lv_device.setAdapter(myBaseAdapter);
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}

	private void getCategoies() {
		new DeviceManager().categories(new BaseCallback<List<CategoriesBean>>() {

            @Override
            public void messageSuccess(List<CategoriesBean> bean) {
                categorList = bean;
				myBaseAdapter.setTab(1);
                lv_device.setAdapter(myBaseAdapter);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
	}

	class MyBaseAdapter extends BaseAdapter{

		private AssetBean assetBean;

		public void setTab(int tab) {
			DeviceActivity.this.tab = tab;
		}

		@Override
		public int getCount() {
			int count = 0;
			switch (tab) {
				case 1:
					count = categorList.size();
					break;
				case 2:
					count = assetList.size();
					break;
			}
			return count;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if(convertView==null){
				viewHolder = new ViewHolder();
				convertView = View.inflate(DeviceActivity.this, R.layout.item_device, null);
				viewHolder.iv_dv = (ImageView)convertView.findViewById(R.id.iv_dv);
				viewHolder.tv_dv = (TextView)convertView.findViewById(R.id.tv_dv);
				viewHolder.tv_amount = (TextView)convertView.findViewById(R.id.tv_amount);
				viewHolder.tv_xg = (TextView)convertView.findViewById(R.id.tv_xg);
				viewHolder.tv_expiredAmount= (TextView)convertView.findViewById(R.id.tv_expiredAmount);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			

			if (tab == 1){
				String omage_url = NetContacts.getInstance()
						.SERVER_URL+"/"+categorList.get(position).icon;
				if (!categorList.get(position).icon.equals("")){
					Glide.with(getApplicationContext()).load(omage_url).into(viewHolder.iv_dv);
				}else{
					viewHolder.iv_dv.setImageResource(R.drawable.worker);
				}
				//iv_dv.setImageResource(res[position]);
				viewHolder.tv_dv.setText(categorList.get(position).name);
				viewHolder.tv_amount.setText(categorList.get(position).amount);
				viewHolder.tv_expiredAmount.setText(categorList.get(position).expiredAmount);
				//setCategorData(position, iv_dv, tv_dv, tv_amount, tv_expiredAmount);
			}else if (tab == 2){
				//setAssetData(position, iv_dv, tv_dv, tv_amount, tv_expiredAmount);
				assetBean = assetList.get(position);
				String omage_url = NetContacts.getInstance()
					.SERVER_URL+"/"+ assetBean.getPhysicalAssetImg();
			if (!assetList.get(position).getPhysicalAssetImg().equals("")){
				Glide.with(getApplicationContext()).load(omage_url).into(viewHolder.iv_dv);
			}else{
				viewHolder.iv_dv.setImageResource(R.drawable.worker);
			}
				//iv_dv.setImageResource(res[position]);
				viewHolder.tv_dv.setText(assetList.get(position).getPhysicalAssetName());
				viewHolder.tv_amount.setVisibility(View.GONE);
				viewHolder.tv_xg.setVisibility(View.GONE);
				viewHolder.tv_expiredAmount.setVisibility(View.GONE);
			}
			return convertView;
		}
		class ViewHolder{
			ImageView iv_dv ;
			TextView tv_dv ;
			TextView tv_amount ;
			TextView tv_xg  ;
			TextView tv_expiredAmount;
		}
	}
}