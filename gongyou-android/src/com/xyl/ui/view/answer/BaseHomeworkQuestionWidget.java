package com.xyl.ui.view.answer;

import android.content.Context;

import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


//import com.supe.supertest.R;
//import com.supe.supertest.homework.QuestionActivity;
//import com.supe.supertest.homework.html.EduHtml;
//import com.supe.supertest.homework.html.EduImageGetterHandler;
//import com.supe.supertest.homework.html.EduTagHandler;
//import com.supe.supertest.homework.module.HomeworkAnswerBean;
//import com.supe.supertest.homework.module.HomeworkQuestionBean;
//import com.supe.supertest.homework.module.HomeworkQuestionTypeBean;


import com.xyl.R;
import com.xyl.domain.answer.AnswerQuestionBean;
import com.xyl.domain.answer.HomeworkAnswerBean;
import com.xyl.domain.answer.HomeworkQuestionBean;
import com.xyl.domain.answer.HomeworkQuestionTypeBean;
import com.xyl.domain.answer.TiMuMobileVosBean;
import com.xyl.html.EduHtml;
import com.xyl.html.EduImageGetterHandler;
import com.xyl.html.EduTagHandler;
import com.xyl.ui.activity.AnswerDetailsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Pattern;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;

import static java.lang.Integer.parseInt;

//import androidx.appcompat.widget.AppCompatCheckBox;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import static com.supe.supertest.homework.html.EduTagHandler.parseInt;

/**
 * @Author yinzh
 * @Date 2019/8/13 14:56
 * @Description
 */
public abstract class BaseHomeworkQuestionWidget extends RelativeLayout {

    protected TextView tvType;
    protected TextView tvNumber;

    protected ConstraintLayout clChild;
    protected TextView tvMaterialStem;
    protected TextView tvChildType;

    protected TextView tvStem;
    protected AppCompatCheckBox checkShowAnalysis;
    protected TextView tvQuestionAnalysis;
    protected LinearLayout llQuestionAnalysis;
    protected ViewStub mAnalysisVS;

    protected Context mContext;
    protected TiMuMobileVosBean mQuestion;
    //protected TiMuMobileVosBean mChildQuestion;

    //当前题目编号
    protected int mIndex;
    //总题数
    public int mTotalNum;

    public Button mNextButtom;

    protected Pattern stemPattern = Pattern.compile("(\\[\\[[^\\[\\]]+]])", Pattern.DOTALL);

    public static final String[] CHOICE_ANSWER = {
            "A", "B", "C",
            "D", "E", "F",
            "G", "H", "I",
            "J", "K", "L"
    };
    private Button toolbarRightBtn;

    public void setData(TiMuMobileVosBean question, int index, int totalNum) {
        mIndex = index;
        mQuestion = question;

        //mChildQuestion = question;
        mTotalNum = totalNum;
    }

    public BaseHomeworkQuestionWidget(Context context) {
        super(context);
        mContext = context;
        initView(null);
    }

    public BaseHomeworkQuestionWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(attrs);
    }

    protected abstract void initView(AttributeSet attrs);

    /**
     * 初始化
     */
    protected void invalidateData() {
        tvType = this.findViewById(R.id.tv_type);
        tvNumber = this.findViewById(R.id.tv_number);

        clChild = this.findViewById(R.id.cl_child);
        tvMaterialStem = this.findViewById(R.id.tv_material_stem);
        tvChildType = this.findViewById(R.id.tv_child_type);

        tvStem = this.findViewById(R.id.question_stem);

        //答案解析
        llQuestionAnalysis = this.findViewById(R.id.ll_question_analysis);
        checkShowAnalysis = this.findViewById(R.id.check_show_analysis);
        tvQuestionAnalysis = this.findViewById(R.id.tv_question_analysis);

        showQuestionTopTitle();

        SpannableStringBuilder spanned = (SpannableStringBuilder) getQuestionStem(mQuestion.getStem());
        spanned = EduHtml.addImageClickListener(spanned, tvStem, mContext);
        tvStem.setText(setHtmlContent(spanned));

        tvQuestionAnalysis.setText(TextUtils.isEmpty(mQuestion.getAnalysis()) ? "暂无解析" : Html.fromHtml(mQuestion.getAnalysis()));
        checkShowAnalysis.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llQuestionAnalysis.isShown()) {
                    llQuestionAnalysis.setVisibility(GONE);

                    llQuestionAnalysis.setFocusable(false);
                    llQuestionAnalysis.setFocusableInTouchMode(false);
                } else {
                    llQuestionAnalysis.setVisibility(VISIBLE);

                    llQuestionAnalysis.setFocusable(true);
                    llQuestionAnalysis.setFocusableInTouchMode(true);
                    llQuestionAnalysis.requestFocus();
                }
            }
        });


        if (mContext instanceof AnswerDetailsActivity) {
            AnswerDetailsActivity testpaperActivity = (AnswerDetailsActivity) getContext();
            ArrayList<HomeworkAnswerBean> answerList = testpaperActivity.answerList;
            //页面切换 回填
            restoreResult(mQuestion.getMyAnser());
            //setAnswer(mIndex - 1, answerList);
        }
    }

    /**
     * 显示题型、分数、说明、题数
     */
    private void showQuestionTopTitle() {
        String text = String.format("%d/%d", mIndex, mTotalNum);
        SpannableString spannableString = new SpannableString(text);
        int color = getResources().getColor(R.color.es_font_1);
        int length = getNumberLength(mIndex);
        spannableString.setSpan(new ForegroundColorSpan(color), 0, length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvType.setText(mQuestion.getTypeDisplay());
        tvNumber.setText(spannableString);

        SpannableStringBuilder spanned = (SpannableStringBuilder) getMaterialQuestionStem(mQuestion.getStem());
        spanned = EduHtml.addImageClickListener(spanned, tvStem, mContext);
        tvMaterialStem.setText(spanned);
        tvChildType.setText(mQuestion.getType()+"");
        clChild.setVisibility(GONE);
    }

    private int getNumberLength(int number) {
        int length = 1;
        while (number >= 10) {
            length++;
            number = number / 10;
        }
        return length;
    }

    /**
     * 复用时 重新回填
     */
    private void setAnswer(int index, ArrayList<HomeworkAnswerBean> answerList) {
        if (answerList == null) {
            return;
        }
        if (mQuestion.getMyAnser()!= null){
            restoreResult(mQuestion.getMyAnser());
        }

//        HomeworkAnswerBean answer = answerList.get(index);
//        if (answer != null && answer.data != null) {
//            restoreResult(mQuestion.getMyAnser());
//        }
        return;
    }

    /**
     * 恢复数据
     *
     * @param resultData
     */
    protected abstract void restoreResult(String resultData);

    /**
     * 材料题题干
     */

    protected Spanned getMaterialQuestionStem(String strStem) {
        return Html.fromHtml(removeHtml_P(removeHtml(strStem)), new EduImageGetterHandler(mContext, tvStem), new EduTagHandler());
    }

    /**
     * 获取题干
     */
    protected Spanned getQuestionStem(String strStem) {
        String stem = String.format("%d、%s",
                mIndex, removeHtml_P(removeHtml(strStem))
        );
        return Html.fromHtml(stem, new EduImageGetterHandler(mContext, tvStem), new EduTagHandler());
    }

    protected void enable(ViewGroup viewGroup, boolean isEnable) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            view.setEnabled(isEnable);
        }
    }

//    protected String listToStr(String arrayList) {
//        StringBuilder stringBuilder = new StringBuilder();
//        Collections.sort(arrayList, new Comparator<String>() {
//            @Override
//            public int compare(String lhs, String rhs) {
//                int l = parseInt(lhs);
//                int r = parseInt(rhs);
//                return l - r;
//            }
//        });
//        for (String answer : arrayList) {
//            if (TextUtils.isEmpty(answer)) {
//                continue;
//            }
//            stringBuilder.append(getAnswerByType(mChildQuestion.getType(), answer));
//            stringBuilder.append("、");
//        }
//        int length = stringBuilder.length();
//        if (length > 0) {
//            stringBuilder.delete(length - 1, length);
//        }
//        return stringBuilder.toString();
//    }

    protected String getAnswerByType(HomeworkQuestionTypeBean qt, String answer) {
        switch (qt) {
            case choice:
            case single_choice:
            case uncertain_choice:
                return parseAnswer(answer);
//            case determine:
//                return parseDetermineAnswer(answer);
//            case essay:
//            case fill:
//                return answer;
            default:
                break;
        }

        return "";
    }

    private String parseDetermineAnswer(String answer) {
        int index;
        index = parseInt(answer);

        if (index == 0) {
            return "B";
        } else {
            return "A";
        }
    }

    private String parseAnswer(String answer) {
        int index;
        try {
            index = parseInt(answer);
        } catch (Exception e) {
            index = 0;
        }
        return CHOICE_ANSWER[index];
    }


    /**
     * 初始化 答案解析
     *
     * @param view
     */
    private String arrayList = new String();

    private String createAnsew() {
        arrayList = "";
        arrayList=mQuestion.getMyAnser();

        return arrayList;
    }

    protected void initResultAnalysis(View view) {
        LinearLayout llChoice = view.findViewById(R.id.ll_choice);
        LinearLayout llFill = view.findViewById(R.id.ll_fill);
        LinearLayout llEssay = view.findViewById(R.id.ll_essay);

        View divider = this.findViewById(R.id.divider);
        divider.setVisibility(GONE);

        checkShowAnalysis.setVisibility(GONE);
        llQuestionAnalysis.setVisibility(VISIBLE);


        llChoice.setVisibility(VISIBLE);
        llFill.setVisibility(GONE);
        llEssay.setVisibility(GONE);

        TextView tvRightAnswer = view.findViewById(R.id.tv_right_anwer);
        TextView tvMyAnswer = view.findViewById(R.id.tv_my_anwer);
        tvRightAnswer.setText(mQuestion.getMyAnser());
        String myAnswer;
//            if (null == mChildQuestion.getResult().status || "noAnswer".equals(mChildQuestion.getResult().status)) {
//                myAnswer = mContext.getResources().getString(R.string.unanswered);
//                tvMyAnswer.setTextColor(mContext.getResources().getColor(R.color.yellow));
//            } else if ("right".equals(mChildQuestion.getResult().status)) {
//                createAnsew();
//                myAnswer = listToStr(arrayList);
//                tvMyAnswer.setTextColor(mContext.getResources().getColor(R.color.es_green));
//            } else {
//                createAnsew();
//                myAnswer = listToStr(arrayList);
//                tvMyAnswer.setTextColor(mContext.getResources().getColor(R.color.red));
//            }
//
//            Log.i("AAAAAAAA", myAnswer + "-------000000--------");
//            tvMyAnswer.setText(myAnswer);

    }

    /**
     * 查看解析 选项赋值
     */
    public void initQuestionResult(RadioGroup radioGroup) {
       String arrayList = createAnsew();
        switch (mQuestion.getType()) {
//            case choice:
//            case uncertain_choice:
//                for (int i = 0; i < radioGroup.getChildCount(); i++) {
//                    View child = radioGroup.getChildAt(i);
//                    //选中 我的答案
//                    if (null != mChildQuestion.getResult().status && !"noAnswer".equals(mChildQuestion.getResult().status)) {
//                        for (String myAnswer : arrayList) {
//                            if (myAnswer.equals(String.valueOf(i))) {
//                                TextView tvMetaNum = child.findViewById(R.id.tv_meta_num);
//                                TextView tvContent = child.findViewById(R.id.tv_content);
//                                tvMetaNum.setBackgroundResource(R.drawable.rect_4_solid_orange);
//                                tvMetaNum.setTextColor(mContext.getResources().getColor(R.color.es_font_white));
//                                tvContent.setTextColor(mContext.getResources().getColor(R.color.red));
//                                break;
//                            }
//                        }
//                    }
//                    //选中 正确答案
//                    for (String answer : mChildQuestion.getAnswer()) {
//                        if (answer.equals(String.valueOf(i))) {
//                            TextView tvMetaNum = child.findViewById(R.id.tv_meta_num);
//                            TextView tvContent = child.findViewById(R.id.tv_content);
//                            tvMetaNum.setBackgroundResource(R.drawable.sel_homework_checkbox_bg);
//                            tvMetaNum.setTextColor(mContext.getResources().getColorStateList(R.color.sel_homework_checkbox_text_color));
//                            tvContent.setTextColor(mContext.getResources().getColorStateList(R.color.sel_homework_checkbox_content_text_color));
//                            tvMetaNum.setSelected(true);
//                            tvContent.setSelected(true);
//                            break;
//                        }
//                    }
//                }
//                break;
            case 0:
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    View child = radioGroup.getChildAt(i);
                    //选中 我的答案
                        if (child.isSelected()) {
                            if (!mQuestion.getZhengQueDA().equals(String.valueOf(mQuestion.getMetas().get(i).getDaAnId()))) {
                                ConstraintLayout clLayout = child.findViewById(R.id.cl_layout);
                                TextView tvMetaNum = child.findViewById(R.id.tv_meta_num);
                                TextView tvContent = child.findViewById(R.id.tv_content);
                                clLayout.setBackgroundResource(R.drawable.rect_4_solid_red);
                                tvMetaNum.setBackgroundResource(R.drawable.cuowu);
                                tvMetaNum.setText("");
                                //tvMetaNum.setTextColor(mContext.getResources().getColor(R.color.red));
                                tvContent.setTextColor(mContext.getResources().getColor(R.color.red));

                            }

                    }

                    //选中 正确答案

                        if (mQuestion.getZhengQueDA().equals(String.valueOf(mQuestion.getMetas().get(i).getDaAnId()))) {
                            ConstraintLayout clLayout = child.findViewById(R.id.cl_layout);
                            TextView tvMetaNum = child.findViewById(R.id.tv_meta_num);
                            TextView tvContent = child.findViewById(R.id.tv_content);
                            clLayout.setBackgroundResource(R.drawable.rect_4_solid_green);
                            tvMetaNum.setBackgroundResource(R.drawable.dui);
                            tvMetaNum.setText("");
                            //tvMetaNum.setTextColor(mContext.getResources().getColorStateList(R.color.es_green));
                            tvContent.setTextColor(mContext.getResources().getColorStateList(R.color.es_green));
                            tvMetaNum.setSelected(true);
                            tvContent.setSelected(true);

                        }

                }
                break;
//            case determine:
//                //选中 我的答案
//                if (null != mChildQuestion.getResult().status
//                        && !"noAnswer".equals(mChildQuestion.getResult().status)) {
//                    View child;
//                    if ("1".equals(arrayList.get(0))) {
//                        child = radioGroup.getChildAt(0);
//                    } else {
//                        child = radioGroup.getChildAt(1);
//                    }
//                    TextView tvMetaNum = child.findViewById(R.id.tv_meta_num);
//                    TextView tvContent = child.findViewById(R.id.tv_content);
//                    tvMetaNum.setBackgroundResource(R.drawable.oval_34_solid_orange);
//                    tvMetaNum.setTextColor(mContext.getResources().getColor(R.color.es_font_white));
//                    tvContent.setTextColor(mContext.getResources().getColor(R.color.red));
//
//                }
//                //选中 正确答案
//                View child;
//                if ("1".equals(mChildQuestion.getAnswer().get(0))) {
//                    child = radioGroup.getChildAt(0);
//                } else {
//                    child = radioGroup.getChildAt(1);
//                }
//                TextView tvMetaNum = child.findViewById(R.id.tv_meta_num);
//                TextView tvContent = child.findViewById(R.id.tv_content);
//                tvMetaNum.setBackgroundResource(R.drawable.sel_homework_radiobutton_bg);
//                tvMetaNum.setTextColor(mContext.getResources().getColorStateList(R.color.sel_homework_checkbox_text_color));
//                tvContent.setTextColor(mContext.getResources().getColorStateList(R.color.sel_homework_checkbox_content_text_color));
//                tvMetaNum.setSelected(true);
//                tvContent.setSelected(true);
//                break;
            default:
                break;

        }

    }


    /**
     * 去掉由于Html.fromHtml产生的'\n'
     *
     * @param spanned
     * @return
     */
    public static CharSequence setHtmlContent(Spanned spanned) {
        if (spanned == null) {
            return "";
        }
        if (spanned.length() > 2 && spanned.subSequence(spanned.length() - 2, spanned.length()).toString().equals("\n\n")) {
            return spanned.subSequence(0, spanned.length() - 2);
        }
        return spanned;
    }


    /**
     * 去掉头部、末尾产生的"\n"
     */
    public static String removeHtml(String strHtml) {
        if (strHtml == null)
            return "";
        if (strHtml.length() > 0 && strHtml.contains("\n")) {
            if (strHtml.substring(0, 1).equals("\n")) {
                strHtml = strHtml.substring(1, strHtml.length() - 1);
                return removeHtml(strHtml);
            }
            if (strHtml.substring(strHtml.length() - 1, strHtml.length()).equals("\n")) {
                strHtml = strHtml.substring(0, strHtml.length() - 1);
                return removeHtml(strHtml);
            }
        }
        return strHtml;
    }

    /**
     * 去掉全部"<p></p>"
     */
    public static String removeHtml_P(String strHtml) {
        strHtml = strHtml.replace("<p>", "");
        strHtml = strHtml.replace("</p>", "");
        return strHtml;
    }


    public void setData(TiMuMobileVosBean question, int index, int totalNum, Button toolbarRightBtn) {
        mIndex = index;
        mQuestion = question;

        //mChildQuestion = question;
        mTotalNum = totalNum;
        mNextButtom = toolbarRightBtn;
    }
}
