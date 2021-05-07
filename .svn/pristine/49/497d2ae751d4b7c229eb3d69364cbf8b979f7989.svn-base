package com.xyl.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.domain.DataBean;
import com.xyl.net.OrderManager;

public class WorkerSituationActivity extends Activity {
    private ImageView iv_title_left;
    private TextView tv_title;
    private TextView tv_title_right;
    private EditText et_worker_des;
    private ImageView iv_worker_des;
    private DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workdes);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        dataBean = (DataBean) getIntent().getSerializableExtra("dataContent");
        iv_title_left.setImageResource(R.drawable.rightmenu);
        iv_title_left.setVisibility(View.VISIBLE);
        tv_title.setText("");
        tv_title_right.setText("完成");
        tv_title_right.setVisibility(View.VISIBLE);
    }

    private void initListener() {
        iv_title_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_worker_des.getText().toString())){
                    Toast.makeText(getApplicationContext(), "请输入描述内容", Toast.LENGTH_SHORT).show();
                    return ;
                }
            new OrderManager().uploadArriveCase(new BaseNet.EntityCallback() {
                @Override
                public void connectCallback(BaseNet.EntityrResult entityrResult) {
                    if(entityrResult.entityType == BaseNet.EntityType.messagetrue){
                        tv_title_right.setEnabled(false);
                        Intent intent = new Intent();
                        intent.putExtra("des", et_worker_des.getText().toString());
                        intent.putExtra("bitmap", "");
                        setResult(3, intent);
                        finish();
                    }else{
                        tv_title_right.setEnabled(true);
                        Toast.makeText(WorkerSituationActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }, dataBean.repairCaseCode,et_worker_des.getText().toString().trim());
            }

        });
    }

    private void initView() {
        iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
        tv_title = (TextView)this.findViewById(R.id.tv_title);
        tv_title_right = (TextView)this.findViewById(R.id.tv_title_right);
        et_worker_des = (EditText)this.findViewById(R.id.et_worker_des);
        iv_worker_des = (ImageView) this.findViewById(R.id.iv_worker_des);
        iv_worker_des.setVisibility(View.GONE);
    }
}
