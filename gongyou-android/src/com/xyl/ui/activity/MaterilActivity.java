package com.xyl.ui.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.CasesBean;
import com.xyl.domain.LoginBean;
import com.xyl.fragment.inventoryfragment.ProductFragment;
import com.xyl.net.OrderStream;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MaterilActivity extends BaseActivity implements View.OnClickListener {

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
    int type = 1;

    @Override
    public int getLayoutId() {

        // isSideBack=false;
        return R.layout.activity_materil;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    protected void initView() {
        LoginBean loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));
        toolbarRightTv.setTextColor(getResources().getColor(R.color.bg_color));
        toolbarTitleTv.setText("出库");
        toolbarRightTv.setText("公司库");
        toolbarLeftBtn.setOnClickListener(this);
        if (type ==1){
            toolbarRightTv.setText("公司库");
        }else if (type ==2){
            toolbarRightTv.setText("部门临置");
        }
        CasesBean.Records caseRecord = (CasesBean.Records) getIntent().getSerializableExtra("CaseBean");
        ProductFragment productFragment = new ProductFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        productFragment.setData(loginBean, type, 3, caseRecord);
        fragmentTransaction.add(R.id.fl_materil, productFragment).commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    @OnClick({R.id.toolbar_left_btn, R.id.toolbar_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.toolbar_right_tv:
                View parentView = LayoutInflater.from(this).inflate(R.layout.activity_materil, null);
                final LinearLayout inflate = (LinearLayout) View.inflate(this, R.layout.dialog_text, null);
                final PopupWindow popupWindow = new PopupWindow(inflate,ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(parentView, Gravity.CENTER, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                TextView tv_alert_company = inflate.findViewById(R.id.tv_alert_company);
                TextView tv_alert_group = inflate.findViewById(R.id.tv_alert_group);
                tv_alert_company.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        type =1;
                        initView();
                        popupWindow.dismiss();

                    }
                });
                tv_alert_group.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        type =2;
                        initView();
                        popupWindow.dismiss();
                    }
                });

                break;
        }
    }

    @Override
    public void onClick(View v) {
        setResult(10);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(10);
        finish();
    }
}
