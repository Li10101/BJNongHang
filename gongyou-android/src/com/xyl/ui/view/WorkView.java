package com.xyl.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by 47500 on 2017/10/16.
 */

public class WorkView extends LinearLayout {
    public WorkView(Context context) {
        this(context,null);
    }

    public WorkView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WorkView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        super.setOnFocusChangeListener(l);
    }
}
