package com.xyl.base;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xyl.R;
import com.xyl.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;


/**
 * Created by 47500 on 2018/4/16.
 */

public abstract class BaseActivity extends FragmentActivity {

    private InputMethodManager imm;
    public TimePickerView pvCustomLunar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /*this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏 第一种方法
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        */
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(getLayoutId());
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.biaoti_back));
        }
       /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/

        initView();
        initData();
    }

    private ActionBar getSupportActionBar() {
        ActionBar actionBar = getActionBar();
        return actionBar;
    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayoutId();

    public void etViewFocusa(EditText etStoreView, int flag) {
        if (flag == 0){
            etStoreView.setFocusable(true);
            etStoreView.setFocusableInTouchMode(true);
            etStoreView.requestFocus();
            imm.showSoftInput(etStoreView, InputMethodManager.SHOW_FORCED);
        }else if (flag ==1){
            etStoreView.setFocusable(false);
            etStoreView.setFocusableInTouchMode(false);
            imm.hideSoftInputFromWindow(etStoreView.getWindowToken(), 0);
        }else if (flag == 2){
            etStoreView.setFocusable(true);
            etStoreView.setFocusableInTouchMode(true);
            etStoreView.requestFocus();
            imm.showSoftInput(etStoreView, InputMethodManager.SHOW_FORCED);
        }

        //imm.toggleSoftInput(2, InputMethodManager.HIDE_NOT_ALWAYS);
    }
    public void setinitLunarPicker(final TextView textView) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                ToastUtil.showToast(getTime(date));
                textView.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(date);
    }

    public void hideInputMethod(TextView editText) {
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public abstract boolean isOpenEventBus();
}
