package com.xyl.ui.widget;

import com.xyl.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

 

public class PhotoView extends View {

	private Context mContext;
	private View item_photo;
	private ImageView iv_photo;

	public PhotoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView();
	}

	public PhotoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}

	public PhotoView(Context context) {
		super(context);
		mContext = context;
		initView();
	}

	private void initView() {
		item_photo = View.inflate(mContext, R.layout.item_photo, null);
		iv_photo = (ImageView) item_photo.findViewById(R.id.iv_photo);
	}

	public PhotoView setResource(Bitmap bitmap) {
//		int width = bitmap.getWidth();
//		int height = bitmap.getHeight();
//
//		Matrix matrix = new Matrix();
//		matrix.preScale(0.8f, 0.8f);
//		Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
//				matrix, true);

		iv_photo.setImageBitmap(bitmap);
		return this;
	}

	public View getView() {
		return item_photo;
	}
}
