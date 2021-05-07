package com.xyl.ui.widget;


import com.xyl.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

 
public class TitleWidget extends LinearLayout {

	private View view;
	private ImageView iv_title_left;
	private ImageView iv_title_right;
	private TextView tv_title;
	private final String namespace = "http://schemas.android.com/apk/res/com.gongyou";

	public TitleWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getView(context);
		initWidget();
		initAttrs(attrs);
	}

	public TitleWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		getView(context);
		initWidget();
		initAttrs(attrs);
	}

	public TitleWidget(Context context) {
		super(context);
		getView(context);
		initWidget();
	}

	private void initAttrs(AttributeSet attrs) {
		int leftValue = attrs.getAttributeResourceValue(namespace, "left",0);
		int rightValue = attrs.getAttributeResourceValue(namespace, "right",0);
		String attributeValue = attrs.getAttributeValue(namespace, "title");
		tv_title.setText(attributeValue);
		if(leftValue!=0){
			iv_title_left.setImageResource(leftValue);
		}
		if(rightValue!=0){
			iv_title_right.setImageResource(rightValue);
		}
		
	}
	private void getView(Context context) {
		view = View.inflate(context, R.layout.widget_title, this);
		
	}

	private void initWidget() {
		iv_title_left = (ImageView) view.findViewById(R.id.iv_title_left);
		iv_title_right = (ImageView) view.findViewById(R.id.iv_title_right);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
	}

	public void setLeftImage(int resource){
		iv_title_left.setImageResource(resource);
	}
	
	public void setTitleText(String text){
		tv_title.setText(text);
	}
	
	public void setRightImage(int resource){
		iv_title_right.setImageResource(resource);
	}
	
	public void setLeftOnclickListener(View.OnClickListener listener){
		iv_title_left.setOnClickListener(listener);
	}
	
	public void setRightOnclickListener(View.OnClickListener listener){
		iv_title_right.setOnClickListener(listener);
	}
	
	public void setLeftImageVisibility(int visibility){
		iv_title_left.setVisibility(visibility);
	}
	
}
