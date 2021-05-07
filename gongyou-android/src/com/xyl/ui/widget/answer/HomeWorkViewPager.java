package com.xyl.ui.widget.answer;

import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.lang.reflect.Field;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.viewpager.widget.ViewPager;


/**
 * @Author yinzh
 * @Date 2020/3/16 14:11
 * @Description 作业容器
 */
public class HomeWorkViewPager extends ViewPager {
    //是否可以滑出左侧页面
    private boolean isCanGoLeft = true;
    //是否可以画出右侧页面
    private boolean isCanGoRight= true;

    /**
     * 上一次x坐标
     */
    private float beforeX;

    private boolean isCanScroll = true;

    public HomeWorkViewPager(@NonNull Context context) {
        super(context);
        initView();
    }

    public HomeWorkViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        try {
            Class clazz = Class.forName("android.support.v4.view.ViewPager");
            Field f = clazz.getDeclaredField("mScroller");
            FixedViewPagerSpeedScroller fixedSpeedScroller =
                    new FixedViewPagerSpeedScroller(getContext(), new LinearOutSlowInInterpolator());
            fixedSpeedScroller.setmDuration(300);
            f.setAccessible(true);
            f.set(this, fixedSpeedScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setCanGoLeft(boolean canGoLeft) {
        isCanGoLeft = canGoLeft;
    }

    public void setCanGoRight(boolean canGoRight) {
        isCanGoRight = canGoRight;
    }
    //-----禁止左滑-------左滑：上一次坐标 > 当前坐标
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(isCanScroll){
            return super.dispatchTouchEvent(ev);
        }else  {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN://按下如果‘仅’作为‘上次坐标’，不妥，因为可能存在左滑，motionValue大于0的情况（来回滑，只要停止坐标在按下坐标的右边，左滑仍然能滑过去）
                    beforeX = ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float motionValue = ev.getX() - beforeX;
                    if (motionValue < 0) {//禁止左滑
                        return true;
                    }
                    beforeX = ev.getX();//手指移动时，再把当前的坐标作为下一次的‘上次坐标’，解决上述问题

                    break;
                default:
                    break;
            }
            return super.dispatchTouchEvent(ev);
        }

    }



    public boolean isScrollble() {
        return isCanScroll;
    }
    /**
     * 设置 是否可以滑动
     * @param isCanScroll
     */
    public void setScrollble(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

}
