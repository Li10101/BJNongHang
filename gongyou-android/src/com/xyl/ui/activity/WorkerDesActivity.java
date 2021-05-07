package com.xyl.ui.activity;

import java.io.File;
import java.io.FileDescriptor;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;

 

import com.xyl.R;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.domain.CasesBean;
import com.xyl.net.OrderManager;
import com.xyl.utils.CommonUtil;
import com.xyl.utils.UIUtils;

public class WorkerDesActivity extends Activity {

	private ImageView iv_title_left;
	private TextView tv_title;
	private TextView tv_title_right;
	private EditText et_worker_des;
	private ImageView iv_worker_des;
	private Bitmap bitmap;
	private CasesBean.Records casesCode;
	private String mFilePath;
	private String label;
	private File picture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workdes);
		
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
		iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
		tv_title = (TextView)this.findViewById(R.id.tv_title);
		tv_title_right = (TextView)this.findViewById(R.id.tv_title_right);
		
		et_worker_des = (EditText)this.findViewById(R.id.et_worker_des);
		iv_worker_des = (ImageView)this.findViewById(R.id.iv_worker_des);
	}
	
	public void initListener(){
		iv_title_left.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				setResult(3,intent);
				finish();
			}
		});
		
		tv_title_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(TextUtils.isEmpty(et_worker_des.getText().toString())){
					Toast.makeText(getApplicationContext(), "请输入描述内容", Toast.LENGTH_SHORT).show();
					return ;
				}
				new OrderManager().uploadArrivePicture(new EntityCallback() {
					
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						if(entityrResult.entityType == EntityType.messagetrue){
							Intent intent = new Intent();
							setResult(3, intent);
							finish();
						}else{
							tv_title_right.setEnabled(true);
							Toast.makeText(WorkerDesActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
						}
					}
				}, casesCode.repairCaseCode, et_worker_des.getText().toString(), picture);
				tv_title_right.setEnabled(false);
				}
			
			});
		
	}

	private void initData() {
		iv_title_left.setImageResource(R.drawable.rightmenu);
		iv_title_left.setVisibility(View.VISIBLE);
		tv_title.setText("");
		tv_title_right.setText("完成");
		tv_title_right.setVisibility(View.VISIBLE);
		
		/*bitmap = (Bitmap)(this.getIntent().getParcelableExtra("bitmap"));
		iv_worker_des.setImageBitmap(bitmap);*/
		casesCode = (CasesBean.Records) getIntent().getSerializableExtra("casesCode");
		mFilePath = (String) this.getIntent().getSerializableExtra("bitmap");
		label = (String) this.getIntent().getSerializableExtra("label");
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		if (label.equals("1")){
			options.inSampleSize = 1;
		}else if (label.equals("2")){
			options.inSampleSize = 1;
		}


		Uri imageContentUri = getImageContentUri(this, mFilePath);
		bitmap = getBitmapFromUri(this, imageContentUri);
		//bitmap = BitmapFactory.decodeFile(mFilePath, options);

		//bitmap = CommonUtil.zoomImg(bitmap, windowWidth * 3 / 4, windowHeight * 3 / 4);
		bitmap = CommonUtil.zoomImg(bitmap, UIUtils.dp2px(220), UIUtils.dp2px(230));
		picture = CommonUtil.saveBitmap2file(bitmap,
				String.valueOf(System.currentTimeMillis()).concat(".png"));
		if(bitmap==null){
			iv_worker_des.setVisibility(View.GONE);
		}else{
			iv_worker_des.setImageBitmap(bitmap);
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK){
			Intent intent = new Intent();
			setResult(3,intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}



	public static Uri getImageContentUri(Context context, String path) {
		Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=? ",
				new String[] { path }, null);
		if (cursor != null && cursor.moveToFirst()) {
			int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
			Uri baseUri = Uri.parse("content://media/external/images/media");
			return Uri.withAppendedPath(baseUri, "" + id);
		} else {
			// 如果图片不在手机的共享图片数据库，就先把它插入。
			if (new File(path).exists()) {
				ContentValues values = new ContentValues();
				values.put(MediaStore.Images.Media.DATA, path);
				return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			} else {
				return null;
			}
		}
	}

	//Bitmap bitmap = getBitmapFromUri(context, uri);
	// 通过uri加载图片
	public static Bitmap getBitmapFromUri(Context context, Uri uri) {
		try {
			ParcelFileDescriptor parcelFileDescriptor =
					context.getContentResolver().openFileDescriptor(uri, "r");
			FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
			Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
			parcelFileDescriptor.close();
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
