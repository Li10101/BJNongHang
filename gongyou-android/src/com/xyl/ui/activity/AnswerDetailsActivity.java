package com.xyl.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.adapter.answer.QuestionPagerAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.click.FastClickUtil;
import com.xyl.domain.MessageBean;
import com.xyl.domain.answer.AnswerBean;
import com.xyl.domain.answer.AnswerQuestionBean;
import com.xyl.domain.answer.DaTiRenDataBean;
import com.xyl.domain.answer.HomeworkAnswerBean;
import com.xyl.domain.answer.HomeworkItemResultBean;
import com.xyl.domain.answer.HomeworkQuestionBean;
import com.xyl.domain.answer.HomeworkQuestionTypeBean;
import com.xyl.domain.answer.TiMuMobileVosBean;
import com.xyl.event.MessageEvent;
import com.xyl.net.AnswerManager;
import com.xyl.ui.widget.answer.HomeWorkViewPager;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.xyl.base.BaseApplication.getContext;

public class AnswerDetailsActivity extends BaseActivity {

    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;

    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;

    @BindView(R.id.view_pager)
    HomeWorkViewPager viewPager;
    @BindView(R.id.toolbar_left_tv)
    TextView toolbarLeftTv;
    @BindView(R.id.toolbar_right_btn)
    Button toolbarRightBtn;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    private QuestionPagerAdapter mAdapter;
    //分类答案
    public ArrayList<HomeworkAnswerBean> answerList = new ArrayList<>();
    //public ArrayList<HomeworkQuestionBean> mQuestionList = new ArrayList<>();


    //当前题目下标
    protected int mCurrentIndex = 0;
    private int scrollPosition;
    private ArrayList<TiMuMobileVosBean> tiMuMobileVos;
    private AnswerQuestionBean answerQuestionBean;
    private int rightBtnFlag;

    @Override
    protected void initData() {

        AnswerBean.RecordsBean recordsBean = (AnswerBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        new AnswerManager().getDaTiFindbyid(recordsBean.getDaTiId(), new BaseNet.BaseCallback<AnswerQuestionBean>() {
            @Override
            public void messageSuccess(AnswerQuestionBean bean) {
                answerQuestionBean = bean;
                tiMuMobileVos = bean.getTiMuMobileVos();
                setStartExamData(tiMuMobileVos);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });

        initListener();

    }

    private void setStartExamData(ArrayList<TiMuMobileVosBean> results) {
//        for (TiMuMobileVosBean homeworkQuestionBean : results) {
//            HomeworkAnswerBean answerBean = new HomeworkAnswerBean();
//            answerBean.data = homeworkQuestionBean.getZhengQueDA();
//            answerList.add(answerBean);
//        }
        mAdapter = new QuestionPagerAdapter(AnswerDetailsActivity.this, results,toolbarRightBtn);
        viewPager.setAdapter(mAdapter);
        viewPager.setScrollble(false);
    }

    @Override
    protected void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_answer__details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }


    private void initListener() {

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean flag;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                scrollPosition = position;
                Log.e("positionmCurrentIndexll", scrollPosition + "");

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;

//                else if (Integer.parseInt(sharePosition))
//                if (Integer.parseInt(sharePosition))


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int old;
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING://1手指按下
                        viewPager.setScrollble(true);
                        flag = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING://2滑动
                        flag = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE://0（END)
//
                        if (!tiMuMobileVos.get(viewPager.getCurrentItem()).getMyAnser().isEmpty()){
                            viewPager.setScrollble(true);
                        }else {
                            viewPager.setScrollble(false);
                        }

                        Log.e("positionmCurrentIndexll", scrollPosition + "");
                        Log.e("SelectedmCurrentIndex22", mCurrentIndex + "");
                        int current = viewPager.getCurrentItem();
                        if (mCurrentIndex == current)
                            Log.e("mCurrentIndex33", mCurrentIndex + "");
//                        if (scrollPosition < mCurrentIndex){
//                            viewPager.setCanGoRight(true);
//                        }else {
//                            viewPager.setCanGoRight(false);
//                        }

                        //判断是不是最后一页，同是是不是拖的状态
//                        if (viewPager.getCurrentItem() == mAdapter.getCount() - 1 && !flag) {
//                            ToastUtil.showToast("已经是最后一题");
//                        }
                        flag = true;
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.setOffscreenPageLimit(3);
    }

    @Subscribe
    public void onEvent(MessageEvent messageEvent) {
        switch (messageEvent.getType()) {
            case MessageEvent.EXAM_CHANGE_ANSWER: // 保存答案

                Bundle bundle = (Bundle) messageEvent.getMessageBody();
                int index = bundle.getInt("index", 0);
                int questionType =  bundle.getInt("QuestionType");
                changeAnswer(index, questionType);
            //    Log.i("QQQQQQQQQQ", "index = " + index + "= data =" + data.get(0) + "= questionType = " + questionType);
                break;
            case MessageEvent.EXAM_NEXT_QUESTION:
                //自动下一题
                toolbarRightBtn.setVisibility(View.GONE);
                int currentItem = viewPager.getCurrentItem();
                int size = tiMuMobileVos.size();
                Log.e("currentItem",currentItem+"size:"+size);
                if (viewPager.getCurrentItem() == tiMuMobileVos.size() - 1) {
                    toolbarRightBtn.setVisibility(View.VISIBLE);
                    toolbarRightBtn.setText("完成");
                    rightBtnFlag = 1;


                    //ToastUtil.showToast("已经是最后一题");

                    return;
                }else {
                    rightBtnFlag = 0;
                }

                if (viewPager.getCurrentItem() < tiMuMobileVos.size() - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    EventBus.getDefault().cancelEventDelivery(messageEvent);
                }
                break;
            case MessageEvent.EXAM_CARD_JUMP:
                //题目跳转

                int num = (Integer) messageEvent.getMessageBody();
                if (num < 0 || num > tiMuMobileVos.size() - 1) {
                    return;
                }
                viewPager.setCurrentItem(num, true);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean isOpenEventBus() {
        return true;
    }


    /**
     * 通过Event 事件回传答案
     */
    private void changeAnswer(int index, int questionType) {
        if (answerList == null) {
            return;
        }

//        if(data.equals("")){
//            return;
//        }
        if (questionType == 0 ) {
            if (FastClickUtil.isFastClick()) {
                viewPager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.EXAM_NEXT_QUESTION));
                    }
                }, 500);
            }
        } else if ((tiMuMobileVos.get(index).getType() == 0)) {
            if (FastClickUtil.isFastClick()) {
                viewPager.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new MessageEvent(MessageEvent.EXAM_NEXT_QUESTION));
                    }
                }, 500);
            }
        }
    }

    @OnClick({R.id.toolbar_left_btn,R.id.toolbar_right_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                Intent intent = new Intent(AnswerDetailsActivity.this,LookForAnswerActivity.class);
                startActivity(intent);
                finish();
                break;
                case R.id.toolbar_right_btn:
                    toolbarRightBtn.setVisibility(View.GONE);
                viewPager.setScrollble(true);
                    if (rightBtnFlag == 0 ){
                        int currentItem = viewPager.getCurrentItem();
                        viewPager.setCurrentItem(currentItem+1);
                        break;
                    }else if(rightBtnFlag == 1){
                        Gson gson = new Gson();
                        String toJson = gson.toJson(answerQuestionBean);
                        //Intent intent = new Intent(AnswerDetailsActivity.this,);
                        new  AnswerManager().saveOrupdatedati(toJson, new BaseNet.BaseCallback<DaTiRenDataBean>() {
                            @Override
                            public void messageSuccess(DaTiRenDataBean bean) {
                                ToastUtil.showToast(bean.getFenShu()+"");
                                Intent intent = new Intent(AnswerDetailsActivity.this,LookForAnswerActivity.class);
                                intent.putExtra("DaTiRenDataBean",bean);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void messageFailure(MessageBean backError) {

                            }

                            @Override
                            public void connectFailure(MessageBean messageBean) {

                            }
                        });
                    }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AnswerDetailsActivity.this,LookForAnswerActivity.class);
        startActivity(intent);
        finish();
    }

}
