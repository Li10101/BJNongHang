package com.xyl.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.EqumentAdapter;
import com.xyl.domain.CasesBean;
import com.xyl.domain.FindByEquipmentNoBean;
import com.xyl.domain.LoginBean;

public class OverhaulActivity extends Activity {
    private ListView lv_equment;
    private CasesBean.Records casesBean;
    private FindByEquipmentNoBean equmentBean;
    private LoginBean loginBean;
    private ImageView iv_title_left;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equament_view);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        lv_equment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String repairCaseCode = equmentBean.maintainRecords.get(i).repairCase;
                Intent intent = new Intent(OverhaulActivity.this,OrderStateActivity.class);
                intent.putExtra("repairCaseCode",repairCaseCode);
                intent.putExtra("LoginBean",loginBean);
                intent.putExtra("flag",2);
                startActivity(intent);
            }
        });

        iv_title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        tv_title.setText("历史设备工单");
        equmentBean = (FindByEquipmentNoBean) getIntent().getSerializableExtra("maintainRecords");
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        EqumentAdapter equmentAdapter = new EqumentAdapter(this, equmentBean.maintainRecords);
        lv_equment.setAdapter(equmentAdapter);
    }

    private void initView() {
        lv_equment = (ListView) findViewById(R.id.lv_equment);
        iv_title_left = (ImageView) findViewById(R.id.iv_title_left);
        iv_title_left.setImageResource(R.drawable.rightmenu);
        iv_title_left.setVisibility(View.VISIBLE);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }
}
