package com.xyl.ui.widget;

import com.xyl.R;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

 

public class PingJiaWidget extends LinearLayout {

	private View rootView;
	private RatingBar rb;
	private TextView tv_suggest;
	private LinearLayout ll_content1;
	private TextView tv_quality;

	public PingJiaWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
	}

	private void initWidget() {
		rb = (RatingBar)rootView.findViewById(R.id.rb);
		tv_suggest = (TextView)rootView.findViewById(R.id.tv_suggest);
		tv_quality = (TextView)rootView.findViewById(R.id.tv_quality);
		ll_content1 = (LinearLayout)findViewById(R.id.ll_content1);
	}

	private void initView(Context context) {
		rootView = View.inflate(context, R.layout.widget_ping_jia, this);
	}

	public void setStart(float rating, String evaluateRateDisplay){
		rb.setRating(rating+1);
		tv_quality.setText(evaluateRateDisplay);
		ll_content1.setVisibility(VISIBLE);
	}
	
	public void setSuggest(String suggest){
		tv_suggest.setText(tv_suggest.getText().toString()+suggest);
		tv_suggest.setVisibility(VISIBLE);
	}
	
}
