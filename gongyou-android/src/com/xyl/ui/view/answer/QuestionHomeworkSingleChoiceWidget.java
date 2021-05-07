package com.xyl.ui.view.answer;

import android.content.Context;
import android.os.Bundle;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.xyl.R;
import com.xyl.domain.answer.HomeworkQuestionBean;
import com.xyl.domain.answer.TiMuMobileVosBean;
import com.xyl.event.MessageEvent;
import com.xyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * @Author yinzh
 * @Date 2019/8/14 11:12
 * @Description 单选题
 */
public class QuestionHomeworkSingleChoiceWidget extends BaseHomeworkQuestionWidget implements View.OnClickListener {

    protected RadioGroup radioGroup;
    private int selectId;

    public QuestionHomeworkSingleChoiceWidget(Context context) {
        super(context);
    }

    public QuestionHomeworkSingleChoiceWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void setData(TiMuMobileVosBean question, int index, int totalNum) {
        super.setData(question, index, totalNum);
        invalidateData();

    }

    @Override
    protected void initView(AttributeSet attrs) {

    }


    @Override
    protected void invalidateData() {
        radioGroup = this.findViewById(R.id.quetion_choice_group);

        ArrayList<TiMuMobileVosBean.MetasBean> metas = mQuestion.getMetas();
        if (metas != null) {
            int size = metas.size();
            for (int i = 0; i < size; i++) {
                View view = initRadioButton(metas.get(i).getName(), i);
//                LinearLayout.LayoutParams paramsRb = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                lp.topMargin = 10;
                lp.bottomMargin = 10;
                view.setOnClickListener(this);
                radioGroup.addView(view, lp);
            }
        }
        super.invalidateData();

    }

    /**
     * 恢复数据
     */
    @Override
    protected void restoreResult(String resultData) {
        int count = radioGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            ArrayList<TiMuMobileVosBean.MetasBean> metas = mQuestion.getMetas();
            TiMuMobileVosBean.MetasBean metasBean = metas.get(i);
            View view = radioGroup.getChildAt(i);
            if (!resultData.equals("")) {
                if (resultData.equals(String.valueOf(metasBean.getDaAnId()))) {
                    if (!mQuestion.getZhengQueDA().equals(String.valueOf(mQuestion.getMetas().get(i).getDaAnId()))) {
                        ConstraintLayout clLayout = view.findViewById(R.id.cl_layout);
                        TextView tvMetaNum = view.findViewById(R.id.tv_meta_num);
                        TextView tvContent = view.findViewById(R.id.tv_content);
                        clLayout.setBackgroundResource(R.drawable.rect_4_solid_red);
                        tvMetaNum.setBackgroundResource(R.drawable.cuowu);
                        tvMetaNum.setText("");
                        //tvMetaNum.setTextColor(mContext.getResources().getColor(R.color.red));
                        tvContent.setTextColor(mContext.getResources().getColor(R.color.red));

                    }

                }

                //选中 正确答案

                if (mQuestion.getZhengQueDA().equals(String.valueOf(mQuestion.getMetas().get(i).getDaAnId()))) {
                    ConstraintLayout clLayout = view.findViewById(R.id.cl_layout);
                    TextView tvMetaNum = view.findViewById(R.id.tv_meta_num);
                    TextView tvContent = view.findViewById(R.id.tv_content);
                    clLayout.setBackgroundResource(R.drawable.rect_4_solid_green);
                    tvMetaNum.setBackgroundResource(R.drawable.dui);
                    tvMetaNum.setText("");
                    //tvMetaNum.setTextColor(mContext.getResources().getColorStateList(R.color.es_green));
                    tvContent.setTextColor(mContext.getResources().getColorStateList(R.color.es_green));
                    tvMetaNum.setSelected(true);
                    tvContent.setSelected(true);

                }
//                if (mQuestion.getZhengQueDA().equals(String.valueOf(metasBean.getDaAnId()))) {
//                    ConstraintLayout clLayout = view.findViewById(R.id.cl_layout);
//                    TextView tvMetaNum = view.findViewById(R.id.tv_meta_num);
//                    TextView tvContent = view.findViewById(R.id.tv_content);
//                    tvMetaNum.setBackgroundResource(R.drawable.dui);
//                    tvMetaNum.setText("");
//                    //tvMetaNum.setTextColor(mContext.getResources().getColorStateList(R.color.es_green));
//                    tvContent.setTextColor(mContext.getResources().getColorStateList(R.color.es_green));
//                    tvMetaNum.setSelected(true);
//                    tvContent.setSelected(true);
//                }
//
//
//                if (resultData.equals(String.valueOf(metasBean.getDaAnId()))) {
//                    if (resultData != mQuestion.getZhengQueDA()) {
//                        ConstraintLayout clLayout = view.findViewById(R.id.cl_layout);
//                        TextView tvMetaNum = view.findViewById(R.id.tv_meta_num);
//                        TextView tvContent = view.findViewById(R.id.tv_content);
//                        clLayout.setBackgroundResource(R.drawable.rect_4_solid_red);
//                        tvMetaNum.setBackgroundResource(R.drawable.cuo);
//                        tvMetaNum.setText("");
//                        //tvMetaNum.setTextColor(mContext.getResources().getColor(R.color.red));
//                        tvContent.setTextColor(mContext.getResources().getColor(R.color.red));
//                    }
//                }
            }
        }
    }

    @Override
    public void setData(TiMuMobileVosBean question, int index, int totalNum, Button toolbarRightBtn) {
        super.setData(question, index, totalNum, toolbarRightBtn);
        invalidateData();

    }


    /**
     * 初始化选项
     */
    private View initRadioButton(String text, int index) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_homework_question_checkbox, null);
        ConstraintLayout clLayout = view.findViewById(R.id.cl_layout);
        TextView tvMetaNum = view.findViewById(R.id.tv_meta_num);
        TextView tvContent = view.findViewById(R.id.tv_content);

        tvContent.setText(text);

        //tvMetaNum.setBackgroundResource(R.drawable.sel_homework_radiobutton_bg);
        tvMetaNum.setText(CHOICE_ANSWER[index]);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (!mQuestion.getMyAnser().equals("")) {
            return;
        } else {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                View child = radioGroup.getChildAt(i);
                ConstraintLayout clLayout = view.findViewById(R.id.cl_layout);
                TextView tvMetaNum = child.findViewById(R.id.tv_meta_num);
                TextView tvContent = child.findViewById(R.id.tv_content);
                child.setSelected(false);
                clLayout.setSelected(false);
                tvMetaNum.setSelected(false);
                tvContent.setSelected(false);

            }
            ConstraintLayout clLayout = view.findViewById(R.id.cl_layout);
            TextView tvMetaNum = view.findViewById(R.id.tv_meta_num);
            TextView tvContent = view.findViewById(R.id.tv_content);
            if (view.isSelected()) {
                view.setSelected(false);
                clLayout.setSelected(false);
                tvMetaNum.setSelected(false);
                tvContent.setSelected(false);
            } else {
                view.setSelected(true);
                clLayout.setSelected(true);
                tvMetaNum.setSelected(true);
                tvContent.setSelected(true);
            }

            sendMsgToTestpaper();
        }


    }

    /**
     * 选择选项事件触发
     */
    protected void sendMsgToTestpaper() {
        Bundle bundle = new Bundle();
        bundle.putInt("index", mIndex - 1);
        bundle.putSerializable("QuestionType", mQuestion.getType());

        int count = radioGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = radioGroup.getChildAt(i);
            if (view.isSelected()) {
                String answer = String.valueOf(mQuestion.getMetas().get(i).getDaAnId());
                mQuestion.setMyAnser(answer);
                if (answer.equals(mQuestion.getZhengQueDA())) {
                    EventBus.getDefault().post(new MessageEvent<>(bundle, MessageEvent.EXAM_CHANGE_ANSWER));
                } else {
                    mNextButtom.setVisibility(VISIBLE);
                    if (mIndex == mTotalNum) {
                        EventBus.getDefault().post(new MessageEvent<>(bundle, MessageEvent.EXAM_CHANGE_ANSWER));
                    }
                    initQuestionResult(radioGroup);
                }


            }
        }

    }


}
