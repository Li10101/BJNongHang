package com.xyl.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xyl.R;

 

public class DrawingBoardWidget extends RelativeLayout {

	private View view;
	private ImageView iv_drawing_board;
	//private TextView tv;
	private Bitmap createBitmap;
	private Canvas canvas;
	private Paint paint;
	private DrawView dv_drawing_board;

	public DrawingBoardWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		initWidget();
	}

	public DrawingBoardWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		initWidget();
	}

	public DrawingBoardWidget(Context context) {
		super(context);
		initView(context);
		initWidget();
		
	}

	private void initView(Context context) {
		view = View.inflate(context, R.layout.widget_drawing_board, this);
	}

	private void initWidget() {
		dv_drawing_board = (DrawView) view.findViewById(R.id.dv_drawing_board);
		//tv = (TextView) view.findViewById(R.id.tv_pay);
	}


	/*public void setMoney(String text) {
		tv.setText(tv.getText().toString()+text);
	}*/
	
	public void setPaintColor(int color){
		dv_drawing_board.setPaintColor(color);
	}
	
	public Bitmap getBitmap(){
		return dv_drawing_board.getBitmap();
	}
	
	public void clear(){
		dv_drawing_board.clear();
	}
	
	public View getView(){
		return view;
	}
}
