package com.xyl.ui.activity;

import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseFragment;
import com.xyl.fragment.noticeFragment.NoticePagerAdapter;
import com.xyl.fragment.noticeFragment.NoticesFragment1;
import com.xyl.fragment.noticeFragment.NoticesFragment2;
import com.xyl.fragment.noticeFragment.NoticesFragment3;
import com.xyl.fragment.noticeFragment.NoticesFragment4;

import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeSortActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    @BindView(R.id.tab_tongzhi)
    TextView tabTongzhi;
    @BindView(R.id.tab_tv_guizhang)
    TextView tabTvGuizhang;
    @BindView(R.id.tab_qiye)
    TextView tabQiye;
    @BindView(R.id.tab_dangjian)
    TextView tabDangjian;
    @BindView(R.id.third_vp1)
    ViewPager thirdVp1;
    @BindView(R.id.cursor1)
    View cursor1;
    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;

    private int offset = 0;
    // 左margin
    //private int leftMargin = 0;
    // 屏幕宽度
    private int screenWidth = 0;
    // 屏幕宽度的五分之一
    private int screen1_5;
    private LinearLayout.LayoutParams lp;


    // fragment对象集合
    private ArrayList<BaseFragment> fragmentsList;

    @Override
    protected void initData() {
        NoticesFragment1 noticesFragment1 = new NoticesFragment1();
        NoticesFragment2 noticesFragment2 = new NoticesFragment2();
        NoticesFragment3 noticesFragment3 = new NoticesFragment3();
        NoticesFragment4 noticesFragment4 = new NoticesFragment4();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("type", 0);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("type", 1);
        Bundle bundle3 = new Bundle();
        bundle3.putInt("type", 2);
        Bundle bundle4 = new Bundle();
        bundle4.putInt("type", 3);
        noticesFragment1.setArguments(bundle1);
        noticesFragment2.setArguments(bundle2);
        noticesFragment3.setArguments(bundle3);
        noticesFragment4.setArguments(bundle4);


        fragmentsList = new ArrayList<>();

        fragmentsList.add(noticesFragment1);
        fragmentsList.add(noticesFragment2);
        fragmentsList.add(noticesFragment3);
        fragmentsList.add(noticesFragment4);

        thirdVp1.setAdapter(new NoticePagerAdapter(getSupportFragmentManager(),
                fragmentsList));
        thirdVp1.setCurrentItem(0);
        thirdVp1.setOffscreenPageLimit(4);
        thirdVp1.addOnPageChangeListener(this);
    }

    @Override
    protected void initView() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screen1_5 = screenWidth / 4;
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setText("通知");
        lp = (LinearLayout.LayoutParams) cursor1.getLayoutParams();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_sort;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }


    @OnClick({R.id.tab_tongzhi, R.id.tab_tv_guizhang, R.id.tab_qiye, R.id.tab_dangjian, R.id.toolbar_left_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_tongzhi:
                thirdVp1.setCurrentItem(0);
                break;
            case R.id.tab_tv_guizhang:
                thirdVp1.setCurrentItem(1);
                break;
            case R.id.tab_qiye:
                thirdVp1.setCurrentItem(2);
                break;
            case R.id.tab_dangjian:
                thirdVp1.setCurrentItem(3);
                break;
            case R.id.toolbar_left_btn:
                finish();
                break;
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        offset = (screen1_5 - cursor1.getLayoutParams().width) / 2;
        final float scale = getResources().getDisplayMetrics().density;
        if (position == 0) {// 0<->1
            lp.leftMargin = (int) (positionOffsetPixels / 4) + offset;
        } else if (position == 1) {// 1<->2
            lp.leftMargin = (int) (positionOffsetPixels / 4) + screen1_5 + offset;
        } else if (position == 2) {// 1<->2
            lp.leftMargin = (int) (positionOffsetPixels / 4) + screen1_5 * 2 + offset;
        } else if (position == 3) {// 1<->2
            lp.leftMargin = (int) (positionOffsetPixels / 4) + screen1_5 * 3 + offset;
        }
        cursor1.setLayoutParams(lp);
//        currentIndex = position;
        upTextcolor(position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void upTextcolor(int position) {
        if (position == 0) {
            tabTongzhi.setTextColor(getBaseContext().getColor(R.color.title_bag));
            tabTvGuizhang.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabQiye.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabDangjian.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        } else if (position == 1) {
            tabTongzhi.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabTvGuizhang.setTextColor(getBaseContext().getColor(R.color.title_bag));
            tabQiye.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabDangjian.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        } else if (position == 2) {
            tabTongzhi.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabTvGuizhang.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabQiye.setTextColor(getBaseContext().getColor(R.color.title_bag));
            tabDangjian.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        } else if (position == 3) {
            tabTongzhi.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabTvGuizhang.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabQiye.setTextColor(getBaseContext().getColor(R.color.text_color_context));
            tabDangjian.setTextColor(getBaseContext().getColor(R.color.title_bag));
            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
        }
        //else if (position == 2) {
//            tab_tv_warehousing_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
//            tab_tv_treasury.setTextColor(getResources().getColor(R.color.text_color_context));
//            //tab_tv_treasury_receipt.setTextColor(getResources().getColor(R.color.text_color_context));
//            //tab_tv_library.setTextColor(getResources().getColor(R.color.text_color_context));
//            //tab_tv_library_receipt.setTextColor(getResources().getColor(R.color.title_bag));
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
