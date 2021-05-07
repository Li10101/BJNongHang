package com.xyl.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.base.BaseActivity;
import com.xyl.domain.answer.DaTiRenDataBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LookForAnswerActivity extends BaseActivity {


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
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.tv_daduiNumber)
    TextView tvDaduiNumber;
    @BindView(R.id.tv_dangqiandefen)
    TextView tvDangqiandefen;
    @BindView(R.id.tv_zuigaofen)
    TextView tvZuigaofen;
    @BindView(R.id.tv_cuotishu)
    TextView tvCuotishu;
    @BindView(R.id.tv_dataTime)
    TextView tvDataTime;
    @BindView(R.id.bt_back)
    Button btBack;
    private DaTiRenDataBean daTiRenDataBean;

    @Override
    protected void initData() {
        daTiRenDataBean = (DaTiRenDataBean) getIntent().getSerializableExtra("DaTiRenDataBean");
        tvDaduiNumber.setText(daTiRenDataBean.getZhengQueSL()+"");
        tvDangqiandefen.setText("当前得分："+daTiRenDataBean.getFenShu());
        tvZuigaofen.setText("最高分："+daTiRenDataBean.getMaxFenShu());
        tvCuotishu.setText("错题数："+daTiRenDataBean.getCuoWuSL());
        tvDataTime.setText("答题时间："+daTiRenDataBean.getDateTime());
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_look_for_answer;
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

    @OnClick({R.id.toolbar_left_btn, R.id.bt_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.bt_back:
                finish();
                break;
        }
    }
}
