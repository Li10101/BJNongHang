package com.xyl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.Button;

import com.andview.refreshview.XRefreshView;
import com.xyl.R;
import com.xyl.adapter.NoticeListAdapter;
import com.xyl.adapter.answer.AnswerListAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.answer.AnswerBean;
import com.xyl.global.NetContacts;
import com.xyl.net.AnswerManager;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnswerListActivity extends BaseActivity {


    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.rv_answerList)
    RecyclerView rvAnswerList;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    private List<AnswerBean.RecordsBean> records;
    private List<AnswerBean.RecordsBean> recordsBeanList;
    private AnswerListAdapter answerListAdapter;

    @Override
    protected void initData() {
        NetContacts.getInstance().pageIndex =1;
        getAnswerListData();
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NetContacts.getInstance().pageIndex = 1;
                        getAnswerListData();
                        xRefreshView.stopRefresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        NetContacts.getInstance().pageIndex++;
                        //piPosition = NetContacts.getInstance().pageIndex;
                        getAnswerListData();
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });
    }

    private void getAnswerListData() {
        new AnswerManager().getAnswerList(new BaseNet.BaseCallback<AnswerBean>() {
            @Override
            public void messageSuccess(AnswerBean bean) {
                rvAnswerList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                records = bean.getRecords();
                if (NetContacts.getInstance().pageIndex ==1){
                    recordsBeanList = records;
                    answerListAdapter = new AnswerListAdapter(getBaseContext(), recordsBeanList);
                    rvAnswerList.setAdapter(answerListAdapter);
                }
                else if (records.size()>1){
                    recordsBeanList.addAll(records);
                    answerListAdapter.notifyDataSetChanged();
                }

                answerListAdapter.setOnClickListener(new AnswerListAdapter.OnClickListener() {
                    @Override
                    public void OnClickListener(int position) {
                        AnswerBean.RecordsBean recordsBean = bean.getRecords().get(position);
                        Intent intent = new Intent(AnswerListActivity.this, AnswerDetailsActivity.class);
                        intent.putExtra("recordsBean", recordsBean);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    @Override
    protected void initView() {
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        records = new ArrayList<>();
        recordsBeanList = new ArrayList<>();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_answer_list;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.toolbar_left_btn)
    public void onViewClicked() {
        finish();

    }
}
