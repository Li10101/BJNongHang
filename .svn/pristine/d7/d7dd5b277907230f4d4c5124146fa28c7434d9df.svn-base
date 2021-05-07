package com.xyl.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.base.BaseBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

public class MessagePicWidget extends LinearLayout {
	private ArrayList<TextView> arrTextView;
	private ArrayList<ImageView> arrImageViews;
	private View view;
	private TextView tv_title;
	public MessagePicWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		initWidget();
	}

	public MessagePicWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		initWidget();
	}

	public MessagePicWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
	}

	private void initView(Context context){
		view = View.inflate(context, R.layout.widget_message_pic, this);
	}
	
	private void initWidget(){
				tv_title = (TextView)view.findViewById(R.id.tv_title);
				arrTextView = new ArrayList<TextView>();
				arrTextView.add(0, (TextView)view.findViewById(R.id.tv_message0)); 
				arrTextView.add(1, (TextView)view.findViewById(R.id.tv_message1));
				arrTextView.add(2, (TextView)view.findViewById(R.id.tv_message2));
				arrTextView.add(3, (TextView)view.findViewById(R.id.tv_message3));
				arrImageViews = new ArrayList<ImageView>();
				arrImageViews.add(0,(ImageView)view.findViewById(R.id.iv_pic0));
				arrImageViews.add(1,(ImageView)view.findViewById(R.id.iv_pic1));
				arrImageViews.add(2,(ImageView)view.findViewById(R.id.iv_pic2));
				arrImageViews.add(3,(ImageView)view.findViewById(R.id.iv_pic3));
				arrImageViews.add(4,(ImageView)view.findViewById(R.id.iv_pic4));
				arrImageViews.add(5,(ImageView)view.findViewById(R.id.iv_pic5));
				arrImageViews.add(6,(ImageView)view.findViewById(R.id.iv_pic6));
				arrImageViews.add(7,(ImageView)view.findViewById(R.id.iv_pic7));
				arrImageViews.add(8,(ImageView)view.findViewById(R.id.iv_pic8));
				arrImageViews.add(9,(ImageView)view.findViewById(R.id.iv_pic9));
				arrImageViews.add(10,(ImageView)view.findViewById(R.id.iv_pic10));
	}
	
	/**
	 * @param position position<=2
	 * @param
	 */
	
	public void setImage(int position,String url){
		Log.e("URL", url);
		arrImageViews.get(position).setVisibility(VISIBLE);
		arrImageViews.get(position).setScaleType(ScaleType.CENTER_CROP);
		x.image().bind(arrImageViews.get(position), url, new ImageOptions.Builder() 
	      // 如果ImageView的大小不是定义为wrap_content, 不要crop.
          // .setCrop(true)
	      // 加载中或错误图片的ScaleType
          // .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
	      .setImageScaleType(ImageView.ScaleType.FIT_XY)
	      //设置支持gif
	      .setIgnoreGif(true)
	     
	      //设置加载过程中的图片
	      .setLoadingDrawableId(R.drawable.bg_gif)
	      //设置加载失败后的图片
	      .setFailureDrawableId(R.drawable.bg_gif)
	      //设置使用缓存
	      .setUseMemCache(true)
	      .build());
	}
	
	public void setImage(int position,Bitmap bitmap){
		arrImageViews.get(position).setVisibility(VISIBLE);
		arrImageViews.get(position).setImageBitmap(bitmap);
	}
	public void setImageLoadGone(int position){
		arrImageViews.get(position).setVisibility(GONE);
	}
	
	public void setImageVisible(int position){
		arrImageViews.get(position).setVisibility(VISIBLE);
	}

	public void setImageVisibleGone(int position){
		arrImageViews.get(position).setVisibility(GONE);
	}


	/**
	 * 
	 * @param position position<=3
	 * @param text
	 */
	public void setText(int position,String text){
		arrTextView.get(position).setText(text);
		arrTextView.get(position).setVisibility(VISIBLE);
	}
	
	public View getView(){
		return view;
	}
	
	public void setTitle(String text){
		tv_title.setText(text);
	}

	public void setTitleSize(float size){
		tv_title.setTextSize(size);
	}
	
	public <T extends BaseBean>MessagePicWidget setData(T bean){
		return null;
	}
	
	//改动： 由原来的设置背景改为设置src
	public void setBackGround(int resid){
		arrImageViews.get(0).setBackgroundResource(resid);
		arrImageViews.get(0).setVisibility(VISIBLE);
	}
	
	public void setScaleType(int position,ScaleType scaleType){
		arrImageViews.get(position).setScaleType(scaleType);
	}
	
	public void setSrc(int position,int resid){
		arrImageViews.get(0).setImageResource(resid);
	}
}
