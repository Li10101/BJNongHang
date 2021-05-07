package com.xyl.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.xyl.R;
import com.xyl.adapter.answer.AnswerListAdapter;
import com.xyl.adapter.answer.DaTiListAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.answer.AnswerBean;
import com.xyl.domain.answer.DaTiListBean;
import com.xyl.domain.answer.DaTiRenDataBean;
import com.xyl.global.NetContacts;
import com.xyl.net.AnswerManager;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaTiListActivity extends BaseActivity {


    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_left_tv)
    TextView toolbarLeftTv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.toolbar_right_btn)
    Button toolbarRightBtn;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.rv_answerList)
    RecyclerView rvAnswerList;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    private List<DaTiRenDataBean> records;
    private List<DaTiRenDataBean> recordsBeanList;
    private DaTiListAdapter answerListAdapter;

    @Override
    protected void initData() {
        getDatiMyData();

    }

    private void getDatiMyData() {
        new AnswerManager().getdatimydata(new BaseNet.BaseCallback<DaTiListBean>() {
            @Override
            public void messageSuccess(DaTiListBean bean) {
                rvAnswerList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                records = bean.getRecords();
                if (NetContacts.getInstance().pageIndex ==1){
                    recordsBeanList = records;
                    answerListAdapter = new DaTiListAdapter(getBaseContext(), recordsBeanList);
                    rvAnswerList.setAdapter(answerListAdapter);

                }
                else if (records.size()>1){
                    recordsBeanList.addAll(records);
                    answerListAdapter.notifyDataSetChanged();
                }


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
        return R.layout.activity_da_ti_list;
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
