package com.xyl.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

    private View childAt;

    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean b = super.onInterceptTouchEvent(ev);
        return b;
    }
}
