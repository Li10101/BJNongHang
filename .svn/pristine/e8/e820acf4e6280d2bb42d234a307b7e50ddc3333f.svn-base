package com.xyl.ui.widget;

import org.xutils.x;
import org.xutils.image.ImageOptions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.xyl.R;
import com.xyl.global.NetContacts;
import com.xyl.utils.CommonUtil;

public class WorkerContentView extends View{

	private Context mContext;
	private View view_worker_content;
	private TextView tv_content_worker;
	private TextView tv_content_time;
	private TextView tv_content_des;
	private ImageView iv_content_photo;
	private View view_fg;
	private int mWidth;
	private int mHeight;
	private int pictureID;
	public WorkerContentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public WorkerContentView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WorkerContentView(Context context) {
		super(context);
		mContext = context;
		initView();
		WindowManager manager = ((Activity)mContext).getWindowManager();
		mWidth = manager.getDefaultDisplay().getWidth();
		mHeight = manager.getDefaultDisplay().getHeight();


		initListener();
	}

	private void initListener() {
		view_worker_content.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(mContext, ""+pictureID, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initView() {
		view_worker_content = View.inflate(mContext, R.layout.view_worker_content, null);
		tv_content_worker = (TextView)view_worker_content.findViewById(R.id.tv_content_worker);
		tv_content_time = (TextView)view_worker_content.findViewById(R.id.tv_content_time);
		tv_content_des = (TextView)view_worker_content.findViewById(R.id.tv_content_des);
		iv_content_photo = (ImageView)view_worker_content.findViewById(R.id.iv_content_photo);
	}

	public WorkerContentView setData(String worker, String time, String des, String url,int pictureId){
		pictureID = pictureId;
		if(!TextUtils.isEmpty(des)){
			tv_content_des.setVisibility(View.VISIBLE);
			tv_content_des.setText("描述："+des);
		}
		if(!TextUtils.isEmpty(time)){
			tv_content_time.setVisibility(View.VISIBLE);
			tv_content_time.setText("日期："+time);
		}
		if(!TextUtils.isEmpty(worker)){
			tv_content_worker.setVisibility(View.VISIBLE);
			tv_content_worker.setText("姓名："+worker);
		}
		if(!TextUtils.isEmpty(url)){
			url = NetContacts.getInstance().BASE_IMAGE_URL + "/" + url;
			iv_content_photo.setVisibility(View.VISIBLE);
			x.image().bind(iv_content_photo, url, new ImageOptions.Builder()
		      // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//		      .setCrop(true)
		      .setImageScaleType(ImageView.ScaleType.FIT_XY)
		      //设置支持gif
		      .setIgnoreGif(false)
		      //设置加载过程中的图片
		      .setLoadingDrawableId(R.drawable.bg_gif)
		      //设置加载失败后的图片
		      .setFailureDrawableId(R.drawable.bg_gif)
		      //设置使用缓存
		      .setUseMemCache(true)
		      .build());
		}else{
			iv_content_photo.setVisibility(View.GONE);
		}
		return this;
	}
	@SuppressLint("NewApi") 
	public WorkerContentView setData(String worker, String time, String des, Bitmap bitmap,int pictureId){
		pictureID = pictureId;
		if(!TextUtils.isEmpty(des)){
			tv_content_des.setVisibility(View.VISIBLE);
			tv_content_des.setText("描述："+des);
		}
		if(!TextUtils.isEmpty(time)){
			tv_content_time.setVisibility(View.VISIBLE);
			tv_content_time.setText("日期："+time);
		}
		if(!TextUtils.isEmpty(worker)){
			tv_content_worker.setVisibility(View.VISIBLE);
			tv_content_worker.setText("姓名："+worker);
		}
		if(bitmap!=null){
			bitmap = CommonUtil.zoomImg(bitmap, mWidth*3/4, mHeight*3/4);
			iv_content_photo.setVisibility(View.VISIBLE);
			iv_content_photo.setImageBitmap(bitmap);
		}
		return this;
	}
	
	public View getView(){
		return view_worker_content;
	}



}
