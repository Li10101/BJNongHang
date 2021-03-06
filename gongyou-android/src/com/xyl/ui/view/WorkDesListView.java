package com.xyl.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 47500 on 2017/10/23.
 */

public class WorkDesListView extends ListView {
    public WorkDesListView(Context context) {
        super(context);
    }

    public WorkDesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WorkDesListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
