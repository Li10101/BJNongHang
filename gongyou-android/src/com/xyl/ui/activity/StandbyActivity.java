package com.xyl.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xyl.R;
import com.xyl.adapter.personnel.WorkFlowAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.domain.personnel.ApplyPeopleBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.domain.personnel.StandbyDetailBean;
import com.xyl.domain.personnel.WorkFlowBean;
import com.xyl.net.PersonnelManager;
import com.xyl.ui.view.MyLinearLayout;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StandbyActivity extends BaseActivity {


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
    @BindView(R.id.tv_apply_people)
    TextView tvApplyPeople;
    @BindView(R.id.iv_applyPeople_delete)
    ImageView ivApplyPeopleDelete;
    @BindView(R.id.mll_dataTime)
    MyLinearLayout mllDataTime;
    @BindView(R.id.et_standby_shiyou)
    EditText etStandbyShiyou;
    @BindView(R.id.et_apply_amount)
    EditText etApplyAmount;
    @BindView(R.id.tv_use_date)
    TextView tvUseDate;
    @BindView(R.id.iv_usedate_delete)
    ImageView ivUsedateDelete;
    @BindView(R.id.tv_return_date)
    TextView tvReturnDate;
    @BindView(R.id.iv_returnDate_delete)
    ImageView ivReturnDateDelete;
    @BindView(R.id.et_cashier)
    EditText etCashier;
    @BindView(R.id.et_standby_remark)
    EditText etStandbyRemark;
    @BindView(R.id.bt_standby_submit)
    Button btStandbySubmit;
    @BindView(R.id.et_department)
    EditText etDepartment;
    @BindView(R.id.work_flow)
    RecyclerView workFlow;
    private TimePickerView pvCustomLunar;

    private static int APPLYNAMEID = 200;
    private String staffId;
    private SelectAllPersonalBean.RecordsBean recordsBean;
    private String renShiId;
    private String beiYongJinShenQingId;

    @Override
    protected void initData() {
        recordsBean = (SelectAllPersonalBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        if (recordsBean != null) {
            new PersonnelManager().standbyDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<StandbyDetailBean>() {
                @Override
                public void messageSuccess(StandbyDetailBean bean) {
                    renShiId = String.valueOf(bean.getRenShiId());
                    beiYongJinShenQingId = String.valueOf(bean.getBeiYongJinShenQingId());
                    tvApplyPeople.setText(bean.getShenQingRenName());
                    // etDepartment.setText(bean.getshen);
                    etStandbyShiyou.setText(bean.getShiYou());
                    etApplyAmount.setText(bean.getShenQingJE());
                    tvUseDate.setText(bean.getShiYongSJ());
                    tvReturnDate.setText(bean.getGuiHuanSJ());
                    etCashier.setText(bean.getChuNa());
                    etStandbyRemark.setText(bean.getDescription());
                }

                @Override
                public void messageFailure(MessageBean backError) {

                }

                @Override
                public void connectFailure(MessageBean messageBean) {

                }
            });
        }
        new PersonnelManager().workFlow("16", new BaseNet.BaseCallback<WorkFlowBean>() {
            @Override
            public void messageSuccess(WorkFlowBean bean) {
                if (bean.getRecords().size() == 0){
                    return;
                }
                workFlow.setLayoutManager(new GridLayoutManager(StandbyActivity.this, 4));
                WorkFlowAdapter workFlowAdapter = new WorkFlowAdapter(StandbyActivity.this, bean.getRecords().get(0));
                workFlow.setAdapter(workFlowAdapter);

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
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setText("备用金");
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_standby;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_left_btn,R.id.tv_apply_people, R.id.iv_applyPeople_delete, R.id.tv_use_date, R.id.iv_usedate_delete, R.id.tv_return_date, R.id.iv_returnDate_delete, R.id.bt_standby_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.tv_apply_people:
                Intent intent = new Intent(StandbyActivity.this, WorkOrderActivity.class);
                WorkInfoBean workInfoBean = new WorkInfoBean(0, "申请人");
                intent.putExtra("WorkInfoBean", workInfoBean);
                startActivityForResult(intent, APPLYNAMEID);

                break;
            case R.id.iv_applyPeople_delete:
                break;
            case R.id.tv_use_date:
                setinitLunarPicker(tvUseDate, ivUsedateDelete);
                pvCustomLunar.show();
                break;
            case R.id.iv_usedate_delete:
                tvUseDate.setText("");
                tvUseDate.setHint("请选择");
                ivUsedateDelete.setImageResource(R.drawable.jiaobiao);
                break;
            case R.id.tv_return_date:
                setinitLunarPicker(tvReturnDate, ivReturnDateDelete);
                pvCustomLunar.show();
                break;
            case R.id.iv_returnDate_delete:
                tvReturnDate.setText("");
                tvReturnDate.setHint("请选择");
                ivReturnDateDelete.setImageResource(R.drawable.jiaobiao);
                break;
            case R.id.bt_standby_submit:
                String buMen = etDepartment.getText().toString();
                String shiYou = etStandbyShiyou.getText().toString();
                String shenQingJE = etApplyAmount.getText().toString();
                String shiYongSJ = tvUseDate.getText().toString();
                String guiHuanSJ = tvReturnDate.getText().toString();
                String chuNa = etCashier.getText().toString();
                String description = etStandbyRemark.getText().toString();
                try {
                    new PersonnelManager().standbySubmit(renShiId, beiYongJinShenQingId, staffId, buMen, shiYou, shenQingJE, shiYongSJ, guiHuanSJ, chuNa, description, new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            finish();
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APPLYNAMEID && resultCode == APPLYNAMEID) {
            ApplyPeopleBean.RecordsBean recordsBean = (ApplyPeopleBean.RecordsBean) data.getSerializableExtra("Applyrecords");
            tvApplyPeople.setText(recordsBean.getName());
            staffId = String.valueOf(recordsBean.getStaffId());
        }
    }

    public void setinitLunarPicker(final TextView editText, final ImageView imageView) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                editText.setText(getTime(date));
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                                imageView.setImageResource(R.drawable.x);
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }
}
