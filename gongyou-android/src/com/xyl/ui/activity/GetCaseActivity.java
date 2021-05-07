package com.xyl.ui.activity;

import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.ProductBean;
import com.xyl.net.OrderManager;
import com.xyl.utils.LogUtil;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GetCaseActivity extends BaseActivity {


    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.rv_case)
    RecyclerView rvCase;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_get_case;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }
}
