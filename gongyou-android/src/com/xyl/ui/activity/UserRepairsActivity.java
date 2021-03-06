package com.xyl.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.net.MixNet;
import com.xyl.net.ServiceRequest;
import com.xyl.ui.view.DateTimePickerView;
import com.xyl.ui.view.DateTimePickerView.OnDateTimeSetListener;
import com.xyl.ui.widget.PhotoView;
import com.xyl.utils.CommonUtil;
import com.xyl.utils.StringUtil;
import com.xyl.utils.UIUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.core.content.res.ResourcesCompat;
import cn.jpush.android.api.JPushInterface;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;


import static com.xyl.utils.ToastUtil.showToast;

@SuppressWarnings("deprecation")
public class UserRepairsActivity extends Activity {
	private ImageView iv_title_left;
	private TextView tv_title;
	private EditText et_repairs_des;
	private View sp_view;
	private Spinner sp_group;
	private LinearLayout ll_repairs_settime;
	private TextView tv_repairs_time;
	private ImageView iv_repairs_tp;
	private ImageView iv_repairs_ap;
	private LinearLayout ll_repairs_photos;
	private SlidingDrawer mDrawer;
	private TextView tv_chouti_finish;
	private DateTimePickerView pickerView;
	private LinearLayout content;
	private Uri photoUrl;
	private ArrayList<File> fileList = new ArrayList<File>();
	private int windowWidth;
	private int windowHeight;
	private Button bt_commit;
	private int pos;
	private List<LoginBean> ownerBeen;
	private EditText et_address;
	private String mFilePath;

	private ArrayList<PermissionItem> permissionItems;
	private File tempFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repairs);
		initView();
		initListener();
		initData();
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

	private void initView() {
		iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
		tv_title = (TextView) this.findViewById(R.id.tv_title);

		bt_commit = (Button)this.findViewById(R.id.bt_commit);
		et_repairs_des = (EditText) this.findViewById(R.id.et_repairs_des);
		et_address = (EditText) this.findViewById(R.id.et_address);;
		sp_view =  this.findViewById(R.id.sp_view);
		sp_group = (Spinner) this.findViewById(R.id.sp_group);;
		ll_repairs_settime = (LinearLayout) this
				.findViewById(R.id.ll_repairs_settime);
		tv_repairs_time = (TextView) this.findViewById(R.id.tv_repairs_time);

		iv_repairs_tp = (ImageView) this.findViewById(R.id.iv_repairs_tp);
		iv_repairs_ap = (ImageView) this.findViewById(R.id.iv_repairs_ap);

		ll_repairs_photos = (LinearLayout) this
				.findViewById(R.id.ll_repairs_photos);

		mDrawer = (SlidingDrawer) findViewById(R.id.slidingdrawer);
		tv_chouti_finish = (TextView) this.findViewById(R.id.tv_chouti_finish);

		pickerView = new DateTimePickerView(this, System.currentTimeMillis());
		content = (LinearLayout) this.findViewById(R.id.content);
		content.addView(pickerView.getView());
		permissionItems = new ArrayList<PermissionItem>();
		permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "?????????", R.drawable.permission_ic_camera));
		permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "??????", R.drawable.permission_ic_storage));
		HiPermission.create(UserRepairsActivity.this)
				.title("????????????")
				.permissions(permissionItems)
				.filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//???????????????
				.msg("??????????????????????????????????????????")
				.style(R.style.PermissionBlueStyle)
				.checkMutiPermission(new PermissionCallback() {
					@Override
					public void onClose() {
						showToast("????????????????????????");
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
				iv_title_left.setVisibility(View.GONE);
				finish();
			}
		});

		ll_repairs_settime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDrawer.animateOpen();
			}
		});

		iv_repairs_tp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//??????????????????
				int currentapiVersion = android.os.Build.VERSION.SDK_INT;
				// ????????????
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// ???????????????????????????????????????????????????
				if (hasSdcard()) {
					SimpleDateFormat timeStampFormat = new SimpleDateFormat(
							"yyyy_MM_dd_HH_mm_ss");
					String filename = timeStampFormat.format(new Date());
					tempFile = new File(Environment.getExternalStorageDirectory(),
							filename + ".jpg");
					mFilePath = tempFile.getAbsolutePath();
					if (currentapiVersion < 24) {
						// ??????????????????uri
						Uri uri = Uri.fromFile(tempFile);
						intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
					} else {
						//??????android7.0 ???????????????????????????
						ContentValues contentValues = new ContentValues(1);
						contentValues.put(MediaStore.Images.Media.DATA, mFilePath);
						Uri uri = getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
						intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
					}
				}
				startActivityForResult(intent, 1);
				/*// TODO ????????????
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// ??????android??????????????????
				startActivityForResult(intent, 1);*/
				/*mFilePath = ImageBitmapUtil.getFilePath();
				File outFile = new File(mFilePath);
				Uri uri = Uri.fromFile(outFile);
				// TODO ????????????
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				*/
			}
		});

		iv_repairs_ap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO ???????????????
				Intent intent1 = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// ??????android?????????
				startActivityForResult(intent1, 2);
			}
		});


		tv_chouti_finish.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				pickerView.onClick();
				mDrawer.animateClose();
			}
		});

		pickerView.setOnDateTimeSetListener(new OnDateTimeSetListener() {

			@Override
			public void OnDateTimeSet(View dialog, long date) {
				Toast.makeText(UserRepairsActivity.this,
						"????????????????????????" + StringUtil.getStringDate(date),
						Toast.LENGTH_SHORT).show();
				tv_repairs_time.setText(StringUtil.getStringDate(date));
			}
		});

		sp_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				pos = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}
	/*
         * ??????sdcard???????????????
         */
	public static boolean hasSdcard() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}
	private void initData() {
		LoginBean loginbean = (LoginBean)this.getIntent().getSerializableExtra("LoginBean");
		if (loginbean.type.equals("0")){
			et_address.setText(loginbean.address);
			sp_view.setVisibility(View.GONE);
			sp_group.setVisibility(View.GONE);
		}
		ownerBeen = new ArrayList<LoginBean>();
		loadOwner();
		WindowManager windowManager = getWindowManager();
		windowWidth = windowManager.getDefaultDisplay().getWidth();
		windowHeight = windowManager.getDefaultDisplay().getHeight();
		iv_title_left.setVisibility(View.VISIBLE);
		iv_title_left.setImageResource(R.drawable.rightmenu);
		if("create".equals(this.getIntent().getStringExtra("create"))){
			tv_title.setText("????????????");
		}else{
			tv_title.setText("????????????");
		}

		/*tv_repairs_time.setText(StringUtil.getStringDate(System
				.currentTimeMillis()));*/
	}

	private void loadOwner() {
		new MixNet().ownerList(new BaseNet.BaseCallback<List<LoginBean>>() {
			@Override
			public void messageSuccess(List<LoginBean> bean) {
				ownerBeen = bean;
				MySpinnerAdater mySpinnerAdater = new MySpinnerAdater();
				sp_group.setAdapter(mySpinnerAdater);
			}

			@Override
			public void messageFailure(MessageBean backError) {

			}

			@Override
			public void connectFailure(MessageBean messageBean) {

			}
		});
	}

	class MySpinnerAdater implements SpinnerAdapter {
		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(UserRepairsActivity.this);
			textView.setPadding(20,0,0,0);
			textView.setTextSize(20);
			//textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
			textView.setText(ownerBeen.get(position).name);
			return textView;
		}

		@Override
		public void registerDataSetObserver(DataSetObserver observer) {

		}

		@Override
		public void unregisterDataSetObserver(DataSetObserver observer) {

		}

		@Override
		public int getCount() {
			return ownerBeen.size();
		}

		@Override
		public Object getItem(int position) {
			return ownerBeen.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = new TextView(UserRepairsActivity.this);
			textView.setPadding(20,0,10,0);
			Drawable nav_up=getResources().getDrawable(R.drawable.user_into);
			nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
			textView.setCompoundDrawables(null, null, nav_up, null);
			textView.setTextSize(20);
			textView.setText(ownerBeen.get(position).name);
			return textView;
		}

		@Override
		public int getItemViewType(int position) {
			return 1;
		}

		@Override
		public int getViewTypeCount() {
			return 1;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}
	}

	public void commitOrder(View v) {




		String des = et_repairs_des.getText().toString().trim();
		if (TextUtils.isEmpty(des)) {
			Toast.makeText(getApplicationContext(), "?????????????????????",
					Toast.LENGTH_SHORT).show();
			return;
		}
		String time = tv_repairs_time.getText().toString();
		if(TextUtils.isEmpty(time)){
			Toast.makeText(getApplicationContext(), "?????????????????????",
					Toast.LENGTH_SHORT).show();
			return;
		}
		bt_commit.setEnabled(false);
		String address = et_address.getText().toString().trim();
		if (TextUtils.isEmpty(address)) {
			Toast.makeText(getApplicationContext(), "???????????????",
					Toast.LENGTH_SHORT).show();
			return;
		}

		File[] files = new File[fileList.size()];
		/*if(files.length==0){
			Toast.makeText(getApplicationContext(), "?????????????????????", Toast.LENGTH_SHORT).show();
			return ;
		}*/
		for (int a = 0; a < fileList.size(); a++) {
			files[a] = fileList.get(a);
		}
		this.findViewById(R.id.loading).setVisibility(View.VISIBLE);
		try {
			new ServiceRequest().createService(des,time, files, address,ownerBeen.get(pos).staffCode,new EntityCallback() {
				@Override
				public void connectCallback(EntityrResult entityrResult) {
					 
					switch (entityrResult.entityType) {
					case messagetrue:
						Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
						bt_commit.setEnabled(true);
						finish();
						break;
					case messagefalse:
						Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
						break;
					case connectFailure:
						//Toast.makeText(getApplicationContext(), "???????????????????????????????????????", Toast.LENGTH_SHORT).show();
						Toast.makeText(getApplicationContext(), "???????????????", Toast.LENGTH_SHORT).show();
						bt_commit.setEnabled(true);
						finish();
						break;
					}
					
					findViewById(R.id.loading).setVisibility(View.GONE);
					bt_commit.setEnabled(true);
				}
			});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bitmap bitmap = null;
		switch (requestCode) {
		case 1:
			switch (resultCode) {
			case Activity.RESULT_OK:
				/*Bundle bundle = data.getExtras();
				bitmap = (Bitmap) bundle.get("data");
				bitmap = CommonUtil.zoomImg(bitmap, windowWidth*3/4, windowHeight*2/3);
				File file = CommonUtil.saveBitmap2file(bitmap,
						String.valueOf(System.currentTimeMillis()).concat(".png"));
				fileList.add(file);*/
				Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = false;
				options.inSampleSize = 1;
				bitmap = BitmapFactory.decodeFile(mFilePath, options);
				//bitmap = CommonUtil.zoomImg(bitmap, windowWidth * 3 / 4, windowHeight * 3 / 4);
				bitmap = CommonUtil.zoomImg(bitmap, UIUtils.dp2px(200), UIUtils.dp2px(210));
				File file = CommonUtil.saveBitmap2file(bitmap,
						String.valueOf(System.currentTimeMillis()).concat(".png"));
				fileList.add(file);
				break;
			}
			break;
		case 2:
			switch (resultCode) {
			case Activity.RESULT_OK:
				/*Uri uri = data.getData();
				Cursor cursor = this.getContentResolver().query(uri, null,null, null, null);
				cursor.moveToFirst();
				String imgPath = cursor.getString(1); // ??????????????????
				Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = false;
				options.inSampleSize = 2;
				bitmap = BitmapFactory.decodeFile(imgPath, options);
				bitmap = CommonUtil.zoomImg(bitmap, windowWidth*3/4, windowHeight*3/4);
				File file = CommonUtil.saveBitmap2file(bitmap,
						String.valueOf(System.currentTimeMillis()).concat(".png"));
				fileList.add(file);*/
				Uri uri = data.getData();
				Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
				cursor.moveToFirst();
				String imgPath = cursor.getString(1); // ??????????????????
				Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = false;
				options.inSampleSize = 2;
				bitmap = BitmapFactory.decodeFile(imgPath, options);
				//bitmap = CommonUtil.zoomImg(bitmap, windowWidth * 3 / 4, windowHeight * 3 / 4);
				bitmap = CommonUtil.zoomImg(bitmap, UIUtils.dp2px(220), UIUtils.dp2px(230));
				File file = CommonUtil.saveBitmap2file(bitmap,
						String.valueOf(System.currentTimeMillis()).concat(".png"));
				fileList.add(file);
				cursor.close();
				break;
			}
			break;
		}

		if (bitmap != null) {
			ll_repairs_photos.addView(new PhotoView(this).setResource(bitmap)
					.getView());
		}
	}
}
