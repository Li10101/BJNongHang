package com.xyl.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class NotifyTextView extends TextView {

	public NotifyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	public NotifyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NotifyTextView(Context context) {
		super(context);
	}

	public void setNotifyCount(int count){
		if(count<=0){
			this.setVisibility(View.GONE);
		}else if(count>99){
			this.setVisibility(View.VISIBLE);
			this.setText("99+");
			return;
		}else{
			this.setVisibility(View.VISIBLE);
			this.setText(String.valueOf(count));
		}
	}
}



