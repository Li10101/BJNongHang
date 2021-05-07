package com.xyl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.base.BaseActivity;
import com.xyl.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CultivateActivity extends BaseActivity {

    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.detail_everyDay)
    LinearLayout detailEveryDay;
    @BindView(R.id.tv_datijilu)
    TextView tvDatijilu;
    @BindView(R.id.tv_name)
    TextView tvName;
    private String loginName;

    @Override
    protected void initData() {
        loginName = SharedPreferencesUtil.getString(getApplicationContext(), SharedPreferencesUtil.LOGIN_NAME);
        tvName.setText(loginName);
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cultivate;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @OnClick({R.id.toolbar_left_btn, R.id.detail_everyDay, R.id.tv_datijilu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.tv_datijilu:
                Intent intent1 = new Intent(CultivateActivity.this, DaTiListActivity.class);
                startActivity(intent1);
                break;
            case R.id.detail_everyDay:
                Intent intent = new Intent(CultivateActivity.this, AnswerListActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
