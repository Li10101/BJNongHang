package com.xyl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.xyl.R;
import com.xyl.adapter.personnel.GoBackAdapter;
import com.xyl.adapter.personnel.HealthStatusAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.GoBackBean;
import com.xyl.domain.HealthStatusBean;
import com.xyl.domain.MessageBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthStatusListActivity extends BaseActivity {


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
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.rv_go_back)
    RecyclerView rvGoBack;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private List<HealthStatusBean.RecordsBean> records;
    private List<HealthStatusBean.RecordsBean> healthStatus;
    private HealthStatusAdapter healthStatusAdapter;
    private HealthStatusBean.RecordsBean recordsBean;

    @Override
    protected void initData() {
        NetContacts.getInstance().pageIndex =1;
        getGoBackListData();
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NetContacts.getInstance().pageIndex = 1;
                        getGoBackListData();
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

                        getGoBackListData();
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });

    }

    private void getGoBackListData() {
        new OrderManager().jianKangqkfindall(new BaseNet.BaseCallback<HealthStatusBean>() {
            @Override
            public void messageSuccess(HealthStatusBean bean) {

                records = bean.getRecords();
                if (NetContacts.getInstance().pageIndex ==1){
                    healthStatus = records;
                    rvGoBack.setLayoutManager(new LinearLayoutManager(HealthStatusListActivity.this));
                    healthStatusAdapter = new HealthStatusAdapter(HealthStatusListActivity.this, healthStatus);
                    rvGoBack.addItemDecoration(new DividerItemDecoration(HealthStatusListActivity.this,DividerItemDecoration.VERTICAL));;
                    rvGoBack.setAdapter(healthStatusAdapter);


                }else if (records.size()>1){
                    healthStatus.addAll(records);
                    healthStatusAdapter.notifyDataSetChanged();
                }

                healthStatusAdapter.setOnItemClickListener(new HealthStatusAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(int position) {
                        Intent intent = new Intent(HealthStatusListActivity.this,GoBackHealthActivity.class);
                        recordsBean = healthStatus.get(position);
                        intent.putExtra("recordsBean", recordsBean);
                        intent.putExtra("type",2);
                        startActivityForResult(intent,1);

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
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setAutoRefresh(false);
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_go_back_list;
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

    @OnClick({R.id.toolbar_left_btn, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(this,AddHealthDataActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            getGoBackListData();
        }

    }
}
