package com.xyl.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.xyl.R;
import com.xyl.ui.widget.OrderManagerWidget;

public class TestActivity extends Activity {

	private ScrollView sv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

		sv = (ScrollView)findViewById(R.id.sv);

		sv.addView(new OrderManagerWidget(this), params);
		TestThread testThread = new TestThread();
		new Thread(testThread).start();
	}

}

class TestThread implements Runnable{
	@Override
	public void run() {

	}
}


