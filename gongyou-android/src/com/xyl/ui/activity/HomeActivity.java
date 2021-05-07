package com.xyl.ui.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;

import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.tencent.bugly.Bugly;
import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.base.BaseResource;
import com.xyl.domain.CasesBean;
import com.xyl.domain.FindAllBulidingBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.domain.personnel.HomeBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.net.OrderStream;
import com.xyl.net.PersonnelManager;
import com.xyl.net.UserManager;
import com.xyl.ui.view.FunctionView;
import com.xyl.ui.view.SearchView;
import com.xyl.ui.view.SlidingLeftView;
import com.xyl.ui.view.UserView;
import com.xyl.ui.widget.NoTouchViewPager;
import com.xyl.ui.widget.NotifyTextView;
import com.xyl.utils.CommonUtil;
import com.xyl.utils.ImageTools;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.update.UmengUpdateAgent;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.viewpager.widget.PagerAdapter;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class HomeActivity extends Activity {

	private NoTouchViewPager vp_home;
	private ImageView iv_home_function;
	private ImageView iv_home_search;
	private ImageView iv_home_user;
	private ImageView iv_title_left;
	private TextView tv_title_right;
	private TextView tv_title;
	private ArrayList<View> viewList = new ArrayList<View>();
	private View view_function;
	private View view_search;
	private View view_user;
	private View slidingLeftView;
	private SlidingMenu mMenu;
	private String type;
	private SlidingLeftView leftView;
	private LoginBean loginBean;
	private ArrayList<WorkInfoBean> mWorkList;
	private UserView userView; 
	private SearchView searchView;

	private static final int TAKE_PICTURE = 0;
	private static final int CHOOSE_PICTURE = 1;
	private static final int CROP = 3;
	private static final int CROP_PICTURE = 4;

	private static final int SCALE = 5;// 照片缩小比例
	private FunctionView functionView;
	private NotifyTextView tv_notify_count;

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what){
				case 1:
					int count = msg.arg1;
					//tv_title.setText(count+"");
					//tv_notify_count.setNotifyCount(count);
					break;
			}
		}
	};
	private List<FindAllBulidingBean> findAllBulidingBeans;
	private List<String> findStirngBuliding = new ArrayList<>();
	private Integer buildingId;
	private List<HomeBean> homebeans;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_home);
		UmengUpdateAgent.setDefault();
		UmengUpdateAgent.update(this);
		initView();
		initListener();
		initData();
	}
	
	
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
		vp_home.setCurrentItem(0, false);
		HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(
				View.VISIBLE);
		if ("1".equals(type) || "2".equals(type) || "3".equals(type)
				|| "4".equals(type)) {
			iv_title_left.setVisibility(View.VISIBLE);
		}
		tv_title_right.setVisibility(View.GONE);
		//String title = "<font color='#43CD80'>BF</font><font color='#555555'>XYL</font>";
		String title = "工作软件";
		tv_title.setText(Html.fromHtml(title));
		tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.logo_top),null);

		setBackgroundSelecter(true, false, false);
    }
     
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
	
	
    @Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
		//FunctionView.getFunctionAdapter().notifyDataSetChanged();
		Integer count = (Integer)SharedPreferencesUtil.getParam(getApplicationContext(), "PersonnelCount", 0);
		//tv_notify_count.setNotifyCount(count);

	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
    //FunctionView.functionList.clear();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent home = new Intent(Intent.ACTION_MAIN);  
		    home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
		    home.addCategory(Intent.CATEGORY_HOME);  
		    startActivity(home);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public NotifyTextView getgetNotifyView(){
		return tv_notify_count;
	}

	private void initView() {

		Bugly.init(getApplicationContext(), "1c9dec0e2d", false);
		this.findViewById(R.id.view_shadow).setVisibility(View.VISIBLE);
		vp_home = (NoTouchViewPager) this.findViewById(R.id.vp_home);
		vp_home.setOffscreenPageLimit(0);
        
		iv_home_function = (ImageView) this.findViewById(R.id.iv_home_function);
		iv_home_search = (ImageView) this.findViewById(R.id.iv_home_search);
		tv_notify_count = (NotifyTextView) this.findViewById(R.id.tv_notify_count);
		iv_home_user = (ImageView) this.findViewById(R.id.iv_home_user);
	
		iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
		iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
		tv_title_right = (TextView) this.findViewById(R.id.tv_title_right);
		tv_title = (TextView) this.findViewById(R.id.tv_title);
		tv_title.setVisibility(View.VISIBLE);

		view_function = View.inflate(this, R.layout.view_function, null);
		view_search = View.inflate(this, R.layout.view_search, null);
		view_user = View.inflate(this, R.layout.view_user, null);
		LoginBean bean = (LoginBean)this.getIntent().getSerializableExtra("LoginBean");
		type = bean.type;
		if ("5".equals(type)) {
			tv_title_right.setVisibility(View.VISIBLE);
		}
		//String title = "<font color='#43CD80'>BF</font><font color='#555555'>XYL</font>";
		String title = "工作软件";
		tv_title.setText(Html.fromHtml(title));
		tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.logo_top),null);
		if (!"0".equals(type)){
			initSlidingMenu();
			iv_title_left.setVisibility(View.VISIBLE);
		}
	}

	private void initSlidingMenu() {
		leftView = new SlidingLeftView(this);
		slidingLeftView = leftView.getView();
		//leftView.setDate(homebeans);
		mMenu = new SlidingMenu(this);
		mMenu.setMode(SlidingMenu.LEFT);
		mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		mMenu.setShadowWidthRes(R.dimen.shadow_width);
		mMenu.setShadowDrawable(R.drawable.shadow);

		mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mMenu.setFadeDegree(0.35f);
		mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		mMenu.setMenu(slidingLeftView);
	}

	private void initListener() {
		iv_home_function.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vp_home.setCurrentItem(0, false);
				HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(
						View.VISIBLE);
//				if ("1".equals(type) || "2".equals(type) || "3".equals(type)
//						|| "4".equals(type)) {
//					iv_title_left.setVisibility(View.VISIBLE);
//				}
				iv_title_left.setVisibility(View.VISIBLE);
				if ("5".equals(type)) {
					tv_title_right.setVisibility(View.VISIBLE);
				}

				//String title = "<font color='#43CD80'>BF</font><font color='#555555'>XYL</font>";
				String title = "工作软件";
				tv_title.setText(Html.fromHtml(title));
				tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.logo_top),null);
				setBackgroundSelecter(true, false, false);
			}
		});

		iv_home_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NetContacts.getInstance().pageIndex = 1;
				vp_home.setCurrentItem(1, false);
				HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(
						View.GONE);
				iv_title_left.setVisibility(View.GONE);
				tv_title_right.setVisibility(View.GONE);
				tv_title.setText("搜索");
				tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
				setBackgroundSelecter(false, true, false);


				new PersonnelManager().selectAllPersonal(new BaseCallback<SelectAllPersonalBean>() {
					@Override
					public void messageSuccess(SelectAllPersonalBean bean) {
						((SearchView) (viewList.get(1).getTag())).setAllPersonalData(bean);
					}

					@Override
					public void messageFailure(MessageBean backError) {

					}

					@Override
					public void connectFailure(MessageBean messageBean) {

					}
				});
				//搜索工单模块
//				new OrderManager().cases(new BaseCallback<CasesBean>() {
//
//					@Override
//					public void messageSuccess(CasesBean bean) {
//						((SearchView) (viewList.get(1).getTag())).setCasesData(bean);
//					}
//
//					@Override
//					public void connectFailure(MessageBean messageBean) {
//						Toast.makeText(HomeActivity.this, "请检查您的网络连接",
//								Toast.LENGTH_SHORT).show();
//					}
//
//					@Override
//					public void messageFailure(MessageBean backError) {
//
//					}
//				});
//
//				FunctionView.functionList.clear();
			}
		});

		iv_home_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				vp_home.setCurrentItem(2, false);
				HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(
						View.GONE);
				iv_title_left.setVisibility(View.GONE);
				tv_title_right.setVisibility(View.GONE);
				tv_title.setText("我的信息");
				tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
				setBackgroundSelecter(false, false, true);

//				FunctionView.functionList.clear();
			}
		});



		iv_title_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mMenu.toggle();
			}
		});


		tv_title_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCaigouLXView();
			}
		});

		if (leftView != null) {
			leftView.setMenuItemListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					WorkInfoBean infoBean = mWorkList.get(position);
					if ("创建工单".equals(infoBean.des)) {
						Intent intent = new Intent(HomeActivity.this,
								UserRepairsActivity.class);
						intent.putExtra("create", "create");
						intent.putExtra("LoginBean", loginBean);
						HomeActivity.this.startActivity(intent);
					} else if ("预防维保".equals(infoBean.des)) {
						Intent intent = new Intent(HomeActivity.this,
								DeviceActivity.class);
						intent.putExtra("LoginBean", loginBean);
						intent.putExtra("title", infoBean.des);
						intent.putExtra("position", position);
						HomeActivity.this.startActivity(intent);
					} else {

						Intent intent = new Intent(HomeActivity.this,
								WorkOrderActivity.class);
						intent.putExtra("LoginBean", loginBean);
						intent.putExtra("WorkInfoBean", infoBean.des);
						intent.putExtra("position", position);
						startActivity(intent);
					}
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							mMenu.toggle();
						}
					}, 200);
				}
			});
		}

//		if (leftView != null) {
//			leftView.setMenuItemListener(new OnItemClickListener() {
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1,
//						int position, long arg3) {
//					String infoBean = homebeans.get(position).getCode();
//					String infoBeanName = homebeans.get(position).getName();
//					if ("Chuangjiangongdan".equalsIgnoreCase(infoBean)) {
//						Intent intent = new Intent(HomeActivity.this, UserRepairsActivity.class);
//						intent.putExtra("create", "create");
//						intent.putExtra("LoginBean", loginBean);
//						HomeActivity.this.startActivity(intent);
//					} else if ("Yufangxingweibao".equalsIgnoreCase(infoBean)) {
//						Intent intent = new Intent(HomeActivity.this, DeviceActivity.class);
//						intent.putExtra("LoginBean", loginBean);
//						intent.putExtra("title", infoBeanName);
//						intent.putExtra("position", position);
//						HomeActivity.this.startActivity(intent);
//					} else {
//						Intent intent = new Intent(HomeActivity.this, WorkOrderActivity.class);
//						intent.putExtra("LoginBean", loginBean);
//						intent.putExtra("WorkInfoBean", infoBeanName);
//						intent.putExtra("position", position);
//						startActivity(intent);
//					}
//					new Handler().postDelayed(new Runnable() {
//						@Override
//						public void run() {
//							mMenu.toggle();
//						}
//					}, 200);
//				}
//			});
//		}
	}

	private void initData() {


		getAllBuilding();
		getFindPermissions();
		functionView = new FunctionView(this);
		viewList.add(functionView.getView());
		searchView = new SearchView(this);
		viewList.add(searchView.getView());
		userView = new UserView(this);
		viewList.add(userView.getView());
		loginBean = (LoginBean) this.getIntent().getSerializableExtra(
				"LoginBean");
		//JPushInterface.setAlias(this, 0,loginBean.staffCode);
		JPushInterface.setAlias(this, loginBean.staffCode,
				new TagAliasCallback() {
					@Override
					public void gotResult(int responseCode, String alias,
							Set<String> tags) {
					}
				});
		mWorkList = new BaseResource().getResourceMenu(loginBean.type);
		vp_home.setAdapter(new MyPagerAdapter());
	}

	private void getFindPermissions() {
		new OrderManager().findPermissions(7,1,new BaseCallback<List<HomeBean>>() {
			@Override
			public void messageSuccess(List<HomeBean> bean) {
				homebeans = bean;
				leftView.setDate(homebeans);
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});

	}

	private void getAllBuilding() {
		new OrderManager().findallbuildingvos(new BaseCallback<List<FindAllBulidingBean>>() {
			@Override
			public void messageSuccess(List<FindAllBulidingBean> bean) {
				findAllBulidingBeans = bean;

				for (int i = 0; i < findAllBulidingBeans.size(); i++) {
					findStirngBuliding.add(findAllBulidingBeans.get(i).getBuildingName());
				}
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		vp_home.setCurrentItem(0, false);
		HomeActivity.this.findViewById(R.id.view_shadow).setVisibility(
				View.VISIBLE);
		if ("1".equals(type) || "2".equals(type) || "3".equals(type)
				|| "4".equals(type)) {
			iv_title_left.setVisibility(View.VISIBLE);
		}
		tv_title_right.setVisibility(View.GONE);
		//String title = "<font color='#43CD80'>BF</font><font color='#555555'>XYL</font>>";
		String title = "工作软件";

		tv_title.setText(Html.fromHtml(title));
		tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.logo_top),null);

		setBackgroundSelecter(true, false, false);
	}






	// @Override
	// protected void onResume() {
	// super.onResume();
	// NetContacts.getInstance().pageIndex = 1;
	// if(vp_home.getCurrentItem()==1 && searchView!=null){
	// searchView.refreshData();
	// }
	// }

	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public View instantiateItem(ViewGroup container, int position) {
			View view = viewList.get(position);
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeAllViews();
			}
			container.addView(view);

//			if (position == 0) {
//				for (int a = 0; a < functionView.getFunctionCount(); a++) {
//					FunctionView.functionList.get(a).setNotifyCount(
//							(Integer) SharedPreferencesUtil.getParam(
//									getApplicationContext(), String.valueOf(a),
//									0));
//				}
//			}
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewList.get(position));
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (resultCode) {
		case 0:
			if (data != null) {
				String info = data.getStringExtra("info");
				int flag = data.getIntExtra("flag", 0);
				userView.resetUserInfo(info, flag);
			}
			break;
		}

		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case TAKE_PICTURE:
				// 将保存在本地的图片取出并缩小后显示在界面上
				Bitmap bitmap = BitmapFactory.decodeFile(Environment
						.getExternalStorageDirectory() + "/image.jpg");
				Bitmap newBitmap = ImageTools.zoomBitmap(bitmap,
						bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE);
				// 由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
				bitmap.recycle();

				// 将处理过的图片显示在界面上，并保存到本地
				userView.getTXView().setImageBitmap(newBitmap);
				ImageTools.savePhotoToSDCard(newBitmap, Environment
						.getExternalStorageDirectory().getAbsolutePath(),
						String.valueOf(System.currentTimeMillis()));
				File file = CommonUtil.saveBitmap2file(newBitmap, String
						.valueOf(System.currentTimeMillis()).concat(".png"));
				new UserManager().uploadPhoto(file, new EntityCallback() {
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						if (entityrResult.entityType == EntityType.messagetrue) {
							Toast.makeText(getApplicationContext(), "头像上传成功",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
				break;

			case CHOOSE_PICTURE:
				ContentResolver resolver = getContentResolver();
				// 照片的原始资源地址
				Uri originalUri = data.getData();
				try {
					// 使用ContentProvider通过URI获取原始图片
					Bitmap photo = MediaStore.Images.Media.getBitmap(resolver,
							originalUri);
					if (photo != null) {
						// 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
						Bitmap smallBitmap = ImageTools.zoomBitmap(photo,
								photo.getWidth() / SCALE, photo.getHeight()
										/ SCALE);
						// 释放原始图片占用的内存，防止out of memory异常发生
						photo.recycle();

						userView.getTXView().setImageBitmap(smallBitmap);

						File imgFile = CommonUtil.saveBitmap2file(smallBitmap,
								String.valueOf(System.currentTimeMillis())
										.concat(".png"));
						new UserManager().uploadPhoto(imgFile,
								new EntityCallback() {
									@Override
									public void connectCallback(
											EntityrResult entityrResult) {
										if (entityrResult.entityType == EntityType.messagetrue) {
											Toast.makeText(
													getApplicationContext(),
													"头像上传成功",
													Toast.LENGTH_SHORT).show();
										}
									}
								});
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public void setBackgroundSelecter(boolean isFunction, boolean isSearch,
			boolean isUsered) {
		if (isFunction) {
			iv_home_function
					.setImageResource(R.drawable.home_menu_select);
		} else {
			iv_home_function.setImageResource(R.drawable.home_menu);
		}

		if (isSearch) {
			iv_home_search.setImageResource(R.drawable.home_search_select);
		} else {
			iv_home_search.setImageResource(R.drawable.home_search);
		}

		if (isUsered) {
			iv_home_user.setImageResource(R.drawable.home_user_select);
		} else {
			iv_home_user.setImageResource(R.drawable.home_user);
		}
	}
	private void showCaigouLXView() {// 弹出选择器

		OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int options2, int options3, View v) {
				String leaveName = findAllBulidingBeans.get(options1).getBuildingName();
				tv_title_right.setText(leaveName);
				buildingId = findAllBulidingBeans.get(options1).getBuildingId();
				new OrderStream().updatebuilding(buildingId, new EntityCallback() {
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						ToastUtil.showToast("更换项目成功");
					}
				});

			}
		})
				.setTitleText("项目")
				.setDividerColor(Color.BLACK)
				.setTextColorCenter(Color.BLACK) //设置选中项文字颜色
				.setContentTextSize(20)
				.build();

		pvOptions.setPicker(findStirngBuliding);
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(type);//三级选择器
		pvOptions.show();
	}


}
