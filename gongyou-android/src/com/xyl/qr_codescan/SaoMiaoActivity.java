package com.xyl.qr_codescan;


import com.xyl.R;
import com.xyl.domain.LoginBean;
import com.xyl.ui.activity.OrderStateActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SaoMiaoActivity extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	
	private TextView mTextView ;
	
	private ImageView mImageView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mTextView = (TextView) findViewById(R.id.result); 
		mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
		Button mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SaoMiaoActivity.this, MipcaActivityCapture.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
			}
		});
	}
	
	int flag;
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
		case SCANNIN_GREQUEST_CODE:
			 LoginBean loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
			if(resultCode == RESULT_OK){
				Bundle bundle = data.getExtras();
				String string = bundle.getString("result");
				int lastIndexOf = string.lastIndexOf("/");
				String equipmentNo = string.substring(lastIndexOf+1);
				Log.e("equipmentNo",""+equipmentNo);
				mTextView.setText("lastIndexOf"+lastIndexOf+equipmentNo);
				flag=8;
				Intent intent = new Intent(this,OrderStateActivity.class);
				intent.putExtra("LoginBean", loginBean);
				intent.putExtra("flag", flag);
				intent.putExtra("equipmentNo", equipmentNo);
				startActivity(intent);
				//mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
			}
			break;
		}
    }	

}
