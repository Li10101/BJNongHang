package com.xyl.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class DrawView extends View {
	
	public Point downPoint, movePoint, upPoint;
	public Paint paint; 
	public Canvas canvas;
	public Bitmap bitmap;
	public int downState;
	public int moveState;
	private Path mPath;
	private float mX, mY;
	private WindowManager wm;

	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		initWidget();
		mPath = new Path();
	}

	public DrawView(Context context, AttributeSet attrs) {
		super(context, attrs);
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		initWidget();
		mPath = new Path();
	}

	public DrawView(Context context) {
		super(context);
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		initWidget();
		mPath = new Path();
	}
	int width;
	int height;
	private void initWidget() {
		width = wm.getDefaultDisplay().getWidth(); 
	     height = wm.getDefaultDisplay().getHeight()/2; 

		paint = new Paint(Paint.DITHER_FLAG);
		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 
		canvas = new Canvas(bitmap);

		// 设置画笔
		paint.setStyle(Style.STROKE);// 
		paint.setStrokeWidth(5);// 
		paint.setColor(Color.BLACK);// 
		paint.setAntiAlias(true);// 

		downPoint = new Point();
		movePoint = new Point();
		upPoint = new Point();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap, 0, 0, null);
		canvas.drawPath(mPath, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			onTouchDown(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			onTouchMove(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			onTouchUp(x, y);
			invalidate();
			break;
		default:
		}
		return true;
	}

	private void onTouchDown(float x, float y) {
		Log.e("paint----", "ontouch");
		mPath.reset();
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private void onTouchMove(float x, float y) {
		Log.e("paint---", "onmove");
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx > 0 || dy > 0) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		} else if (dx == 0 || dy == 0) {
			mPath.quadTo(mX, mY, (x + 1 + mX) / 2, (y + 1 + mY) / 2);
			mX = x + 1;
			mY = y + 1;
		}
	}

	private void onTouchUp(float x, float y) {
		Log.e("paint----.", "onmove");
		// mPath.lineTo(mX, mY);
		canvas.drawPath(mPath, paint);
		mPath.reset();
	}
	
	
	public void clear(){
		Paint paint = new Paint();  
		paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));  
		canvas.drawPaint(paint);
	}
	
/*	public void setBitmapSize(Context context,int dpWidth,int dpHeight){
		bitmap.setWidth(DensityUtil.px2dip(context, dpWidth));
		bitmap.setHeight(DensityUtil.px2dip(context, dpHeight));
	}
	
	public void setBitmapHeight(Context context,int dpHeight){
		bitmap.setHeight(DensityUtil.px2dip(context, dpHeight));
	}
	
	public void setBitmapWidth(Context context,int dpWidth){
		bitmap.setWidth(DensityUtil.px2dip(context, dpWidth));
	}*/
	
	public Bitmap getBitmap(){
		return bitmap;
	}
	
	public void setPaintColor(int color) {
		paint.setColor(color); 
	}
}
