package com.xyl.ui.activity;

import android.app.ActionBar;
import android.content.Intent;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.adapter.FragmentAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.domain.LoginBean;
import com.xyl.fragment.inventoryfragment.ProductFragment;
import com.xyl.fragment.inventoryfragment.ReviewFragment;
import com.xyl.utils.SharedPreferencesUtil;


import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class InventoryActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    TextView tab1Tv;
    TextView tab2Tv;
    View cursor;
    ViewPager thirdVp;
    Button toolbarLeftBtn;
    private RelativeLayout toolbarContentRlyt;
    //private Button toolbarLeftBtn;
    private TextView toolbarTitleTv;
    private TextView toolbarRightTv;

    // fragment对象集合
    private ArrayList<Fragment> fragmentsList;
    // 记录当前选中的tab的index
//    private int currentIndex = 0;
    // 指示器的偏移量
    private int offset = 0;
    // 左margin
//    private int leftMargin = 0;
    // 屏幕宽度
    private int screenWidth = 0;
    // 屏幕宽度的三分之一
    private int screen1_3;
    //
    private LinearLayout.LayoutParams lp;
    private LoginBean loginBean;
    private int type = 1;

    @Override
    public int getLayoutId() {
       // isSideBack=false;
        return R.layout.activity_inventory;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    protected void initView() {
        String param = SharedPreferencesUtil.getString(getApplicationContext(), SharedPreferencesUtil.LOGIN_BEAN);
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        if (loginBean ==null){
            loginBean =  new Gson().fromJson(param,LoginBean.class);
        }
        tab1Tv = findViewById( R.id.tab1_tv );
        tab2Tv = findViewById( R.id.tab2_tv );
        thirdVp = findViewById( R.id.third_vp );
        toolbarLeftBtn = findViewById( R.id.toolbar_left_btn );
        toolbarTitleTv = findViewById( R.id.toolbar_title_tv );
        toolbarRightTv =  findViewById( R.id.toolbar_right_tv );
        cursor = findViewById(R.id.cursor);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setText("搜索");
        toolbarTitleTv.setTextSize(15);
        toolbarTitleTv.setTextColor(getBaseContext().getColor(R.color.bg_color));
        toolbarRightTv.setText("公司库");
        toolbarRightTv.setTextColor(getBaseContext().getColor(R.color.bg_color));
        toolbarLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (type ==1){
            toolbarRightTv.setText("公司库");
        }else if (type ==2){
            toolbarRightTv.setText("部门临置");
        }

        toolbarRightTv.setOnClickListener(this);
        toolbarTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryActivity.this,SearchActivity.class);
                intent.putExtra("flag",0);
                startActivity(intent);
            }
        });
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screen1_3 = screenWidth / 2;
        lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
    }

    @Override
    protected void initData() {
        fragmentsList = new ArrayList<>();
        ProductFragment productFragment = new ProductFragment();
        productFragment.setData(loginBean,type,0);
        fragmentsList.add(productFragment);
        ReviewFragment reviewFragment = new ReviewFragment();
        reviewFragment.setLoginData(loginBean,0);
        fragmentsList.add(reviewFragment);
        thirdVp.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                fragmentsList));
        thirdVp.setCurrentItem(0);
        thirdVp.setOffscreenPageLimit(2);
        thirdVp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        offset = (screen1_3 - cursor.getLayoutParams().width) / 2;
        Log.d("111", position + "--" + positionOffset + "--"
                + positionOffsetPixels);
        final float scale = getResources().getDisplayMetrics().density;
        if (position == 0) {// 0<->1
            lp.leftMargin = (int) (positionOffsetPixels / 2) + offset;
        } else if (position == 1) {// 1<->2
            lp.leftMargin = (int) (positionOffsetPixels / 2) + screen1_3 +offset;
        }
        cursor.setLayoutParams(lp);
//        currentIndex = position;
        upTextcolor(position);
    }
    private void upTextcolor(int position){
        if (position==0){
            tab1Tv.setTextColor(getResources().getColor(R.color.title_bag));
            tab2Tv.setTextColor(getResources().getColor(R.color.text_color_context));
        }else if(position==1){
            tab1Tv.setTextColor(getResources().getColor(R.color.text_color_context));
            tab2Tv.setTextColor(getResources().getColor(R.color.title_bag));
        }else if(position==2){
            tab1Tv.setTextColor(getResources().getColor(R.color.text_color_context));
            tab2Tv.setTextColor(getResources().getColor(R.color.text_color_context));
        }
    }
    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View v) {
        View parentView = LayoutInflater.from(this).inflate(R.layout.activity_materil, null);
        final LinearLayout inflate = (LinearLayout) View.inflate(this, R.layout.dialog_text, null);
        final PopupWindow popupWindow = new PopupWindow(inflate,ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(parentView, Gravity.CENTER, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        TextView tv_alert_company = inflate.findViewById(R.id.tv_alert_company);
        TextView tv_alert_group = inflate.findViewById(R.id.tv_alert_group);
        tv_alert_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type =1;
                toolbarRightTv.setText("公司库");
                initData();
                popupWindow.dismiss();

            }
        });

        tv_alert_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type =2;
                toolbarRightTv.setText("部门临置");
                initData();
                popupWindow.dismiss();
            }
        });
    }
}
