package com.xyl.ui.activity;

import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.StorageRoomAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseResource;
import com.xyl.domain.LoginBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.ui.view.SpaceItemDecoration;
import com.xyl.utils.ToastUtil;

import java.util.ArrayList;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HealthListActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private ArrayList<WorkInfoBean> resourceGrid;
    private Button toolbarLeftBtn;
    private TextView toolbarLeftTv;
    private TextView toolbarTitleTv;
    private Button toolbarRightBtn;
    private TextView toolbarRightTv;
    private LoginBean loginBean;

    @Override
    protected void initData() {
        BaseResource resource = new BaseResource();
        resourceGrid = resource.getResourceGrid("7");
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(50);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(spaceItemDecoration);
        StorageRoomAdapter storageRoomAdapter = new StorageRoomAdapter(this,resourceGrid);
        storageRoomAdapter.setOnItemGridClick(new StorageRoomAdapter.OnItemGridClick() {
            @Override
            public void OnItemGridClick(int position) {
                WorkInfoBean workInfoBean = resourceGrid.get(position);
                ToastUtil.showToast(workInfoBean.des);
                if ("去反情况".equals(workInfoBean.des)){
                    Intent intent = new Intent(HealthListActivity.this,GoBackListActivity.class);
                    intent.putExtra("LoginBean", loginBean);
                    startActivity(intent);
                }else if ("健康情况".equals(workInfoBean.des)){
                    Intent intent = new Intent(HealthListActivity.this,HealthStatusListActivity.class);
                    intent.putExtra("name",workInfoBean.des);
                    intent.putExtra("LoginBean", loginBean);
                    startActivity(intent);
                }

            }
        });
        recyclerView.setAdapter(storageRoomAdapter);


    }

    @Override
    protected void initView() {
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        toolbarLeftBtn = (Button) findViewById(R.id.toolbar_left_btn);
        toolbarLeftTv = (TextView) findViewById(R.id.toolbar_left_tv);
        toolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        toolbarRightBtn = (Button) findViewById(R.id.toolbar_right_btn);
        toolbarRightTv = (TextView) findViewById(R.id.toolbar_right_tv);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.rv_storage);
        toolbarTitleTv.setText("健康卡");
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));
        toolbarLeftBtn.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_storage_room;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
