package com.xyl.ui.widget;

import java.util.ArrayList;

import com.xyl.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

 

public class MessageProcessWidget extends LinearLayout {

	private View view;
	private ArrayList<TextView> arrTextViews;
	private ArrayList<ImageView> arrayImageViews;
	private LinearLayout ll_content;
	private TextView tv_head;
	public MessageProcessWidget(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		initWidget();
	}

	public MessageProcessWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		initWidget();
	}

	public MessageProcessWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
	}
	
	private void initWidget(){
				arrTextViews = new ArrayList<TextView>();
				arrTextViews.add(0, (TextView)view.findViewById(R.id.tv_process0));
				arrTextViews.add(1, (TextView)view.findViewById(R.id.tv_process1));
				arrTextViews.add(2, (TextView)view.findViewById(R.id.tv_process2));
				arrTextViews.add(3, (TextView)view.findViewById(R.id.tv_process3));
				arrTextViews.add(4, (TextView)view.findViewById(R.id.tv_process4));
				arrTextViews.add(5, (TextView)view.findViewById(R.id.tv_process5));
				arrTextViews.add(6, (TextView)view.findViewById(R.id.tv_process6));
				arrTextViews.add(7, (TextView)view.findViewById(R.id.tv_process7));
				arrTextViews.add(8, (TextView)view.findViewById(R.id.tv_process8));
				arrTextViews.add(9, (TextView)view.findViewById(R.id.tv_process9));
				arrTextViews.add(10, (TextView)view.findViewById(R.id.tv_process10));
				arrTextViews.add(11, (TextView)view.findViewById(R.id.tv_process11));
				arrTextViews.add(12, (TextView)view.findViewById(R.id.tv_process12));
				arrTextViews.add(13, (TextView)view.findViewById(R.id.tv_process13));
				arrTextViews.add(14, (TextView)view.findViewById(R.id.tv_process14));
				arrTextViews.add(15, (TextView)view.findViewById(R.id.tv_process15));
				arrTextViews.add(16, (TextView)view.findViewById(R.id.tv_process16));
				arrTextViews.add(17, (TextView)view.findViewById(R.id.tv_process17));
				arrTextViews.add(18, (TextView)view.findViewById(R.id.tv_process18));
				arrTextViews.add(19, (TextView)view.findViewById(R.id.tv_process19));
				arrTextViews.add(20, (TextView)view.findViewById(R.id.tv_process20));
				arrTextViews.add(21, (TextView)view.findViewById(R.id.tv_process21));
				arrTextViews.add(22, (TextView)view.findViewById(R.id.tv_process22));
				arrTextViews.add(23, (TextView)view.findViewById(R.id.tv_process23));
				arrTextViews.add(24, (TextView)view.findViewById(R.id.tv_process24));
     			arrTextViews.add(25, (TextView)view.findViewById(R.id.tv_process25));
				arrTextViews.add(26, (TextView)view.findViewById(R.id.tv_process26));
				arrTextViews.add(27, (TextView)view.findViewById(R.id.tv_process27));
				arrTextViews.add(28, (TextView)view.findViewById(R.id.tv_process28));
				arrTextViews.add(29, (TextView)view.findViewById(R.id.tv_process29));
				arrTextViews.add(30, (TextView)view.findViewById(R.id.tv_process30));
				arrTextViews.add(31, (TextView)view.findViewById(R.id.tv_process31));
				arrTextViews.add(32, (TextView)view.findViewById(R.id.tv_process32));
				arrTextViews.add(33, (TextView)view.findViewById(R.id.tv_process33));
				arrTextViews.add(34, (TextView)view.findViewById(R.id.tv_process34));
				arrTextViews.add(35, (TextView)view.findViewById(R.id.tv_process35));
				
				arrayImageViews = new ArrayList<ImageView>();
				arrayImageViews.add(0,(ImageView)view.findViewById(R.id.iv_process1));
				arrayImageViews.add(1,(ImageView)view.findViewById(R.id.iv_process2));
				arrayImageViews.add(2,(ImageView)view.findViewById(R.id.iv_process3));
				arrayImageViews.add(3,(ImageView)view.findViewById(R.id.iv_process4));
				arrayImageViews.add(4,(ImageView)view.findViewById(R.id.iv_process5));
				arrayImageViews.add(5,(ImageView)view.findViewById(R.id.iv_process6));
				arrayImageViews.add(6,(ImageView)view.findViewById(R.id.iv_process7));
				arrayImageViews.add(7,(ImageView)view.findViewById(R.id.iv_process8));
				arrayImageViews.add(8,(ImageView)view.findViewById(R.id.iv_process9));
				arrayImageViews.add(9,(ImageView)view.findViewById(R.id.iv_process10));
				arrayImageViews.add(10,(ImageView)view.findViewById(R.id.iv_process11));
				arrayImageViews.add(11,(ImageView)view.findViewById(R.id.iv_process12));
				arrayImageViews.add(12,(ImageView)view.findViewById(R.id.iv_process13));
				arrayImageViews.add(13,(ImageView)view.findViewById(R.id.iv_process14));
				arrayImageViews.add(14,(ImageView)view.findViewById(R.id.iv_process15));
				arrayImageViews.add(15,(ImageView)view.findViewById(R.id.iv_process16));
				arrayImageViews.add(16,(ImageView)view.findViewById(R.id.iv_process17));
				arrayImageViews.add(17,(ImageView)view.findViewById(R.id.iv_process18));
				arrayImageViews.add(18,(ImageView)view.findViewById(R.id.iv_process19));
				arrayImageViews.add(19,(ImageView)view.findViewById(R.id.iv_process20));
				arrayImageViews.add(20,(ImageView)view.findViewById(R.id.iv_process21));
				arrayImageViews.add(21,(ImageView)view.findViewById(R.id.iv_process22));
				arrayImageViews.add(22,(ImageView)view.findViewById(R.id.iv_process23));
				arrayImageViews.add(23,(ImageView)view.findViewById(R.id.iv_process24));
				arrayImageViews.add(24,(ImageView)view.findViewById(R.id.iv_process25));
				arrayImageViews.add(25,(ImageView)view.findViewById(R.id.iv_process26));
				arrayImageViews.add(26,(ImageView)view.findViewById(R.id.iv_process27));
				arrayImageViews.add(27,(ImageView)view.findViewById(R.id.iv_process28));
				arrayImageViews.add(28,(ImageView)view.findViewById(R.id.iv_process29));
      			arrayImageViews.add(29,(ImageView)view.findViewById(R.id.iv_process30));
      			arrayImageViews.add(30,(ImageView)view.findViewById(R.id.iv_process31));
      			arrayImageViews.add(31,(ImageView)view.findViewById(R.id.iv_process32));
      			arrayImageViews.add(32,(ImageView)view.findViewById(R.id.iv_process33));
      			arrayImageViews.add(33,(ImageView)view.findViewById(R.id.iv_process34));
      			arrayImageViews.add(34,(ImageView)view.findViewById(R.id.iv_process35));
				tv_head = (TextView)view.findViewById(R.id.tv_head);
	}
	
	private void initView(Context context){
		view = View.inflate(context, R.layout.widget_message_process, this);
	}
	
	public void setTitle(String title){
		tv_head.setText(title);
	}
	
	public void setText(int position,String text){
		if(position==0){
			arrTextViews.get(position).setText(text);
			arrTextViews.get(position).setVisibility(VISIBLE);
		}else{
			/*ProcessView process = new ProcessView(context);
			process.setText(text);
			ll_content.addView(process.getView());*/
			arrTextViews.get(position).setText(text);
			arrTextViews.get(position).setVisibility(VISIBLE);
			arrayImageViews.get(position-1).setVisibility(VISIBLE);
		}
	}
}
