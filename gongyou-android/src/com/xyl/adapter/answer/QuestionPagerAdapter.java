package com.xyl.adapter.answer;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

import com.xyl.R;
import com.xyl.domain.answer.HomeworkQuestionBean;
import com.xyl.domain.answer.HomeworkQuestionTypeBean;
import com.xyl.domain.answer.TiMuMobileVosBean;
import com.xyl.ui.view.answer.BaseHomeworkQuestionWidget;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @Author yinzh
 * @Date 2019/8/13 14:16
 * @Description
 */
public class QuestionPagerAdapter extends PagerAdapter {


    protected LayoutInflater inflater;
    protected Context mContext;
    protected ArrayList<TiMuMobileVosBean> mList;
    private BaseHomeworkQuestionWidget mWidget;
    private  Button toolbarRightBtn;


    public QuestionPagerAdapter(Context context, ArrayList<TiMuMobileVosBean> list, Button toolbarRightBtn) {
        mList = list;
        mContext = context;
        this.toolbarRightBtn = toolbarRightBtn;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TiMuMobileVosBean questionBean = mList.get(position);

        View view = switchQuestionWidget(questionBean, position + 1, mList.size());
        ScrollView scrollView = new ScrollView(mContext);
        scrollView.setFillViewport(true);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        container.addView(scrollView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return scrollView;
    }


    private View switchQuestionWidget(TiMuMobileVosBean question, int index, int totalNum) {
        int typeBean = question.getType();
//        if (typeBean == HomeworkQuestionTypeBean.material) {
//            typeBean = question.getItems().get(0).getType();
//        }
        int layoutId = 0;
        switch (typeBean) {

            case 0://单选题
                layoutId = R.layout.item_pager_homework_question_singlechoice;
                break;
//            case essay://问答题
//                layoutId = R.layout.item_pager_homework_question_essay;
//                break;
//            case determine://判断题
//                layoutId = R.layout.item_pager_homework_question_determine;
//                break;
//            case material://材料题
//                layoutId = R.layout.item_pager_homework_question_material;
//                break;
//            case fill:
//                layoutId = R.layout.item_pager_homework_question_material;
//                break;
            default:
                break;
        }
        mWidget = (BaseHomeworkQuestionWidget) LayoutInflater.from(mContext).inflate(layoutId, null);
        mWidget.setData(question, index, totalNum,toolbarRightBtn);
        return mWidget;
    }





}
