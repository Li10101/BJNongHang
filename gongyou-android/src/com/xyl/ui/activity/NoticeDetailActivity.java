package com.xyl.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.xyl.R;
import com.xyl.base.BaseApplication;
import com.xyl.domain.NoticeBean;
import com.xyl.domain.NoticeDateBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.ToastUtil;

import net.sf.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class NoticeDetailActivity extends Activity {

    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    //private TextView tv_title;
    private WebView wv_boss;
    private float x;
    private float x1;
    private String title;
    private int flag;
    private NoticeDateBean recordsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    private void initView() {

        recordsBean = (NoticeDateBean) getIntent().getSerializableExtra("recordsBean");
        //img_loding.setVisibility(View.VISIBLE);

        wv_boss = (WebView) this.findViewById(R.id.wv_boss);
        //开启js
        WebSettings settings = wv_boss.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        wv_boss.setVerticalScrollBarEnabled(false);
        wv_boss.setHorizontalScrollBarEnabled(false);
        wv_boss.addJavascriptInterface(new nongHangApp(), "nongHangApp");
    }


    private void initData() {
        //String url = "https://u.eqxiu.com/s/7soG6GZp?eqrcode=1&share_level=2&from_user=202006268f47b889&from_id=dedcf30b-e&share_time=1593157498476&from=groupmessage";
        String url = NetContacts.getInstance().getBaseUrl() + "/estate/mobile/cms/show?cmsId=" + recordsBean.getCmsId();
        syncCookie(url);
        wv_boss.loadUrl(url);

    }

    private void syncCookie(String url) {
        try {
            CookieSyncManager.createInstance(wv_boss.getContext());//创建一个cookie管理器
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除以前的cookie
            cookieManager.removeAllCookie();
			/*StringBuilder sbCookie = new StringBuilder();//创建一个拼接cookie的容器,为什么这么拼接，大家查阅一下http头Cookie的结构
			sbCookie.append(_mApplication.getUserInfo().getSessionID());//拼接sessionId
			sbCookie.append(String.format(";domain=%s", ""));
			sbCookie.append(String.format(";path=%s", ""));*/
            String cookieValue = SharedPreferencesUtil.getString(
                    BaseApplication.getBaseApplication(), "cookie");
            ;
            cookieManager.setCookie(url, cookieValue);//为url设置cookie
            CookieSyncManager.getInstance().sync();//同步cookie
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.toolbar_left_btn)
    public void onViewClicked() {
        finish();
    }



    public class nongHangApp {
        @JavascriptInterface
        public void passResult2App(String ss) {
                ToastUtil.showToast("已读");
                finish();

        }
    }

}
