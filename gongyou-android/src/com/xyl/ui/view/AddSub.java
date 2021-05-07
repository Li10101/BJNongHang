package com.xyl.ui.view;

import android.content.Context;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xyl.R;

import androidx.annotation.Nullable;


/**
 * Created by 47500 on 2017/8/24.
 */

public class AddSub extends LinearLayout implements View.OnClickListener {
    private ImageView iv_sub;
    private EditText tv_content;
    private ImageView iv_add;
    private Context mContext;
    private double value = 1;
    private int maxValue = 100;
    private int minValue = 1;
    public AddSub(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View view = View.inflate(context,R.layout.addsub,this);
        iv_sub =  view.findViewById(R.id.iv_sub);
        tv_content = (EditText) view.findViewById(R.id.tv_content);
        iv_add = (ImageView) view.findViewById(R.id.iv_add);
        double value = getValue();
        setValue(value);
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_sub:
                subNumber();
                break;
            case R.id.iv_add:
                 addNumber();
                break;
        }
    }

    private void addNumber() {
        /*if (value<maxValue){
            value++;
        }*/
        getValue();
        setValue(value);
        if (onNumberChangeListener!=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    private void subNumber() {
        if (value>minValue){
            value--;
        }
        setValue(value);
        if (onNumberChangeListener!=null){
            //onNumberChangeListener.onNumberChange(value);
        }
    }

    public double getValue() {
        String stvalue = tv_content.getText().toString().trim();
        if (!TextUtils.isEmpty(stvalue)){
            value = Double.parseDouble(stvalue);
        }
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        tv_content.setText(value+"");
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * 当数量发生变化的时候回调
     */
    public interface OnNumberChangeListener{
        void onNumberChange(double value);
    }

    private OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
