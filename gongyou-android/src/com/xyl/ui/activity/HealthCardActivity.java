package com.xyl.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.base.BaseActivity;
import com.xyl.ui.view.MyLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthCardActivity extends BaseActivity {


    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.et_danwei)
    EditText etDanwei;
    @BindView(R.id.tv_jiankashijian)
    TextView tvJiankashijian;
    @BindView(R.id.mll_layout2)
    MyLinearLayout mllLayout2;
    @BindView(R.id.et_xingming)
    EditText etXingming;
    @BindView(R.id.tv_xingbie)
    TextView tvXingbie;
    @BindView(R.id.mll_layout3)
    MyLinearLayout mllLayout3;
    @BindView(R.id.et_shenfenzheng)
    EditText etShenfenzheng;
    @BindView(R.id.et_nianling)
    EditText etNianling;
    @BindView(R.id.et_lianxifangshi)
    EditText etLianxifangshi;
    @BindView(R.id.et_jvzhudizhi)
    EditText etJvzhudizhi;
    @BindView(R.id.tv_zhufangqingkuang)
    TextView tvZhufangqingkuang;
    @BindView(R.id.mll_layout4)
    MyLinearLayout mllLayout4;
    @BindView(R.id.tv_quchengshijian)
    TextView tvQuchengshijian;
    @BindView(R.id.mll_layout5)
    MyLinearLayout mllLayout5;
    @BindView(R.id.tv_mudidi)
    TextView tvMudidi;
    @BindView(R.id.sfwuhanzhongzhuan)
    TextView sfwuhanzhongzhuan;
    @BindView(R.id.tv_fanchengshijian)
    TextView tvFanchengshijian;
    @BindView(R.id.tv_chufadi)
    TextView tvChufadi;
    @BindView(R.id.tv_fanchenggongjv)
    TextView tvFanchenggongjv;
    @BindView(R.id.et_banci)
    EditText etBanci;
    @BindView(R.id.tv_isStopWuhan)
    TextView tvIsStopWuhan;
    @BindView(R.id.et_stop_wuhan_where)
    EditText etStopWuhanWhere;
    @BindView(R.id.et_jingtingshijian)
    EditText etJingtingshijian;
    @BindView(R.id.tv_commit)
    Button tvCommit;


    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_left_tv)
    TextView toolbarLeftTv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.toolbar_right_btn)
    Button toolbarRightBtn;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.iv_add)
    ImageView ivAdd;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_health_card;

    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_left_btn,R.id.iv_add, R.id.et_danwei, R.id.tv_jiankashijian, R.id.et_xingming, R.id.tv_xingbie, R.id.et_shenfenzheng, R.id.et_nianling, R.id.et_lianxifangshi, R.id.et_jvzhudizhi, R.id.tv_zhufangqingkuang, R.id.tv_quchengshijian, R.id.tv_mudidi, R.id.sfwuhanzhongzhuan, R.id.tv_fanchengshijian, R.id.tv_chufadi, R.id.tv_fanchenggongjv, R.id.et_banci, R.id.tv_isStopWuhan, R.id.et_stop_wuhan_where, R.id.et_jingtingshijian, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.iv_add:

                break;
            case R.id.et_danwei:
                etViewFocusa(etDanwei, 2);

                break;
            case R.id.tv_jiankashijian:
                setinitLunarPicker(tvJiankashijian);
                pvCustomLunar.show();
                break;
            case R.id.et_xingming:
                etViewFocusa(etXingming, 2);
                break;
            case R.id.tv_xingbie:
                break;
            case R.id.et_shenfenzheng:
                etViewFocusa(etShenfenzheng, 2);
                break;
            case R.id.et_nianling:
                etViewFocusa(etNianling, 2);
                break;
            case R.id.et_lianxifangshi:
                etViewFocusa(etLianxifangshi, 2);
                break;
            case R.id.et_jvzhudizhi:
                etViewFocusa(etJvzhudizhi, 2);
                break;
            case R.id.tv_zhufangqingkuang:
                break;
            case R.id.tv_quchengshijian:
                break;
            case R.id.tv_mudidi:
                break;
            case R.id.sfwuhanzhongzhuan:
                break;
            case R.id.tv_fanchengshijian:
                break;
            case R.id.tv_chufadi:
                break;
            case R.id.tv_fanchenggongjv:
                break;
            case R.id.et_banci:
                etViewFocusa(etBanci, 2);
                break;
            case R.id.tv_isStopWuhan:
                break;
            case R.id.et_stop_wuhan_where:
                etViewFocusa(etStopWuhanWhere, 2);
                break;
            case R.id.et_jingtingshijian:
                etViewFocusa(etJingtingshijian, 2);
                break;
            case R.id.tv_commit:
                break;
        }
    }

}
