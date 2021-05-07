package com.xyl.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends WebView {

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initSet();
	}

	public MyWebView(Context context) {
		super(context);
		initSet();
	}
	
	private void initSet() {
		// 对webview进行初始化设置
		WebSettings settings = this.getSettings();
		// 设置支持JavaScript
		settings.setJavaScriptEnabled(true);
		// 设置webview的缩放级别
		settings.setDefaultZoom(ZoomDensity.MEDIUM);

		// 设置webview的页面导航
		this.setWebViewClient(new WebViewClient());
		
	}
	
	/**
	 * 设置
	 */
	private void initParam() {
		android.view.ViewGroup.LayoutParams params = this.getLayoutParams();
		params.height = LayoutParams.FILL_PARENT;
		params.width =  LayoutParams.FILL_PARENT;
		this.setLayoutParams(params);
	}


	public MyWebView loadkeOrder(){
		 initParam() ;
		this.loadUrl("http://www.etiansoft.com/estate/ichart");
		return this;
	}

	public MyWebView loadkeAlarm(){
		 initParam() ;
		this.loadUrl("http://www.etiansoft.com/estate/equipment/alarm");
		return this;
	}

}
