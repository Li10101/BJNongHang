package com.xyl.ui.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyl.R;

import java.util.ArrayList;
import java.util.List;
public class MessageWidget extends LinearLayout {

	private View view;
	private ArrayList<TextView> arrTextView;
	private TextView tv_title;

	public MessageWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		initWidget();
		
	}

	public MessageWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		initWidget();
	}

	public MessageWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
	}

	private void initView(Context context){
		view = View.inflate(context, R.layout.widget_message, this);
	}
	
	private void initWidget(){
		tv_title = (TextView)view.findViewById(R.id.tv_title);
		arrTextView = new ArrayList<TextView>();
		arrTextView.add(0, (TextView)view.findViewById(R.id.tv_message0)); 
		arrTextView.add(1, (TextView)view.findViewById(R.id.tv_message1));
		arrTextView.add(2, (TextView)view.findViewById(R.id.tv_message2));
		arrTextView.add(3, (TextView)view.findViewById(R.id.tv_message3));
		arrTextView.add(4, (TextView)view.findViewById(R.id.tv_message4));
		arrTextView.add(5, (TextView)view.findViewById(R.id.tv_message5));
		arrTextView.add(6, (TextView)view.findViewById(R.id.tv_message6));
		arrTextView.add(7, (TextView)view.findViewById(R.id.tv_message7));
		arrTextView.add(8, (TextView)view.findViewById(R.id.tv_message8));
		arrTextView.add(9, (TextView)view.findViewById(R.id.tv_message9));
		arrTextView.add(10, (TextView)view.findViewById(R.id.tv_message10));
		arrTextView.add(11, (TextView)view.findViewById(R.id.tv_message11));
		arrTextView.add(12, (TextView)view.findViewById(R.id.tv_message12));
		arrTextView.add(13, (TextView)view.findViewById(R.id.tv_message13));
		arrTextView.add(14, (TextView)view.findViewById(R.id.tv_message14));
		arrTextView.add(15, (TextView)view.findViewById(R.id.tv_message15));

		
	}
	
	public List<TextView> getTextViews(){
		return arrTextView;
	}
	
	public void setText(int position,String text){
		arrTextView.get(position).setText(text);
		arrTextView.get(position).setVisibility(VISIBLE);
	}
	
	/*public String getText(int position){
		String text = (String) arrTextView.get(position).getText();
		System.out.println(text+"");
		return text;
		 
	}*/
	
	public View getView(){
		return view;
	}
	
	public void setTitle(String text){
		tv_title.setText(text);
	}

	public void setTitleAndDrawable(String text){
		tv_title.setText(text);


	}
}
