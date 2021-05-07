package com.xyl.ui.activity;

import android.app.ActionBar;
import android.content.Intent;

import android.util.DisplayMetrics;
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

public class StockActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    TextView tab_tv_warehousing_receipt;
    TextView tab_tv_treasury;
    //TextView tab_tv_treasury_receipt;
    //TextView tab_tv_library;
    //TextView tab_tv_library_receipt;
    View cursor;
    ViewPager thirdVp;

    private Button toolbarLeftBtn;
    private TextView toolbarTitleTv;
    private TextView toolbarRightTv;

    // fragment对象集合
    private ArrayList<Fragment> fragmentsList;
    // 记录当前选中的tab的index
    //private int currentIndex = 0;
    // 指示器的偏移量
    private int offset = 0;
    // 左margin
    //private int leftMargin = 0;
    // 屏幕宽度
    private int screenWidth = 0;
    // 屏幕宽度的五分之一
    private int screen1_5;
    private LinearLayout.LayoutParams lp;
    private LoginBean loginBean;
    private int type = 1;

    @Override
    protected void initData() {
        fragmentsList = new ArrayList<>();
        ProductFragment productFragment = new ProductFragment();
        productFragment.setData(loginBean,type,1);
        fragmentsList.add(productFragment);
        ReviewFragment reviewFragment = new ReviewFragment();
        reviewFragment.setLoginData(loginBean, type);
        fragmentsList.add(reviewFragment);

        thirdVp.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                fragmentsList));
        thirdVp.setCurrentItem(0);
        thirdVp.setOffscreenPageLimit(2);
        thirdVp.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        offset = (screen1_5 - cursor.getLayoutParams().width) / 2;
        final float scale = getResources().getDisplayMetrics().density;
        if (position == 0) {// 0<->1
            lp.leftMargin = (int) (positionOffsetPixels / 2) + offset;
        } else if (position == 1) {// 1<->2
            lp.leftMargin = (int) (positionOffsetPixels / 2) + screen1_5 + offset;
        }/*else if (position == 2) {// 1<->2
            lp.leftMargin = (int) (positionOffsetPixels / 3) + screen1_5*2+ offset;
        }else if (position == 3) {// 1<->2
            lp.leftMargin = (int) (positionOffsetPixels / 4) + screen1_5*3+ offset;
        }*/
        cursor.setLayoutParams(lp);
//        currentIndex = position;
        upTextcolor(position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    @Override
    protected void initView() {
        toolbarLeftBtn = (Button) findViewById(R.id.toolbar_left_btn);
        toolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        toolbarRightTv = (TextView) findViewById(R.id.toolbar_right_tv);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));

        tab_tv_warehousing_receipt = findViewById(R.id.tab_tv_warehousing_receipt);
        tab_tv_treasury = findViewById(R.id.tab_tv_treasury);
        //tab_tv_treasury_receipt = findViewById(R.id.tab_tv_treasury_receipt);
        //tab_tv_library = findViewById(R.id.tab_tv_library);
        //tab_tv_library_receipt = findViewById(R.id.tab_tv_library_receipt);
        cursor = findViewById(R.id.cursor1);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setVisibility(View.VISIBLE);
        thirdVp = findViewById(R.id.third_vp1);
        toolbarTitleTv.setText("搜索");
        toolbarTitleTv.setTextSize(15);
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));
        toolbarRightTv.setText("公司库");
        toolbarRightTv.setTextColor(getResources().getColor(R.color.bg_color));
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

        tab_tv_warehousing_receipt.setOnClickListener(this);
        tab_tv_treasury.setOnClickListener(this);
        //tab_tv_treasury_receipt.setOnClickListener(this);
        //tab_tv_library.setOnClickListener(this);
        //tab_tv_library_receipt.setOnClickListener(this);


        toolbarTitleTv.setVisibility(View.VISIBLE);
        String name = (String) getIntent().getCharSequenceExtra("name");

        toolbarRightTv.setOnClickListener(this);
        toolbarTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StockActivity.this,SearchActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screen1_5 = screenWidth / 2;

        lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();

        String param = SharedPreferencesUtil.getString(getApplicationContext(), SharedPreferencesUtil.LOGIN_BEAN);
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        if (loginBean ==null){
            loginBean =  new Gson().fromJson(param,LoginBean.class);
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_stock;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_tv_warehousing_receipt:
                thirdVp.setCurrentItem(0);
                break;
            case R.id.tab_tv_treasury:
                thirdVp.setCurrentItem(1);
                break;
            /*case R.id.tab_tv_treasury_receipt:
                thirdVp.setCurrentItem(2);
                break;
            case R.id.tab_tv_library:
                thirdVp.setCurrentItem(2);
                break;*/
//            case R.id.tab_tv_library_receipt:
//                thirdVp.setCurrentItem(2);
//                break;
            case R.id.toolbar_left_btn:
                finish();
                break;
                case R.id.toolbar_right_tv:
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

                    break;
        }
    }

    private void upTextcolor(int position) {
        if (position == 0) {
            tab_tv_warehousing_receipt.setTextColor(getBaseContext().getColor(R.color.title_bag));
            tab_tv_treasury.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            // tab_tv_treasury_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_library.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        } else if (position == 1) {
            tab_tv_warehousing_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            tab_tv_treasury.setTextColor(getResources().getColor(R.color.title_bag));
            //tab_tv_treasury_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_library.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        } /*else if (position == 2) {
            tab_tv_warehousing_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            tab_tv_treasury.setTextColor(getResources().getColor(R.color.text_color_context));
            tab_tv_treasury_receipt.setTextColor(getResources().getColor(R.color.title_bag));
            tab_tv_library.setTextColor(getResources().getColor(R.color.text_color_context));
            tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        }else if (position == 2) {
            tab_tv_warehousing_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            tab_tv_treasury.setTextColor(getResources().getColor(R.color.text_color_context));
           // tab_tv_treasury_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_library.setTextColor(getResources().getColor(R.color.title_bag));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        }*/else if (position == 2) {
            tab_tv_warehousing_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            tab_tv_treasury.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_treasury_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_library.setTextColor(getResources().getColor(R.color.text_color_context));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.title_bag));
        }
    }
}


