package com.xyl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.Button;

import com.andview.refreshview.XRefreshView;
import com.xyl.R;
import com.xyl.adapter.NoticeListAdapter;
import com.xyl.adapter.ReviewFragmentAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.NoticeBean;
import com.xyl.domain.NoticeDateBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.utils.ToastUtil;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeListActivity extends BaseActivity {


    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.rv_answerList)
    RecyclerView rvAnswerList;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    private int piPosition;
    private List<NoticeDateBean> records;
    private List<NoticeDateBean> recordsBeanList;
    private NoticeListAdapter answerListAdapter;

    @Override
    protected void initData() {
        NetContacts.getInstance().pageIndex =1;
        getNoticeData();
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NetContacts.getInstance().pageIndex = 1;
                        getNoticeData();
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
                        piPosition = NetContacts.getInstance().pageIndex;
                        getNoticeData();
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });
    }


    private void getNoticeData() {

        new OrderManager().cmsdata(0,new BaseNet.BaseCallback<NoticeBean>() {
            @Override
            public void messageSuccess(NoticeBean bean) {

                rvAnswerList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                records = bean.getRecords();
                if (NetContacts.getInstance().pageIndex ==1){
                    recordsBeanList = records;
                    answerListAdapter = new NoticeListAdapter(getBaseContext(), recordsBeanList);
                    rvAnswerList.setAdapter(answerListAdapter);
                }
                else if (records.size()>1){
                    recordsBeanList.addAll(records);
                    answerListAdapter.notifyDataSetChanged();
                }

                answerListAdapter.setOnClickListener(new NoticeListAdapter.OnClickListener() {
                    @Override
                    public void OnClickListener(int position) {
                        NoticeDateBean recordsBean = recordsBeanList.get(position);
                        recordsBeanList.get(position).setSee(true);
                        Intent intent = new Intent(NoticeListActivity.this, NoticeDetailActivity.class);
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
//设置刷新完成以后，headerview固定的时间
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
        return R.layout.activity_notice_list;
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
