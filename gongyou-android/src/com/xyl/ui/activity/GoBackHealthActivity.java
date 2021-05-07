package com.xyl.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.personnel.ApprolvalEmergencyAdatper;
import com.xyl.adapter.personnel.ApprovalAllShowImageAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.GoBackBean;
import com.xyl.domain.HealthStatusBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.JianKangQKBean;
import com.xyl.domain.personnel.ProcurementDetails;
import com.xyl.domain.personnel.QuFanChengQKBean;
import com.xyl.domain.personnel.ReimburseDetail;
import com.xyl.net.OrderManager;
import com.xyl.utils.ToastUtil;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoBackHealthActivity extends BaseActivity {
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
    @BindView(R.id.bt_approval_flag)
    TextView btApprovalFlag;
    @BindView(R.id.bt_approval_name)
    TextView btApprovalName;
    @BindView(R.id.status_description)
    TextView statusDescription;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.approval_text1)
    TextView approvalText1;
    @BindView(R.id.approval_content1)
    TextView approvalContent1;
    @BindView(R.id.ll_approval_line1)
    LinearLayout llApprovalLine1;
    @BindView(R.id.approval_text2)
    TextView approvalText2;
    @BindView(R.id.approval_content2)
    TextView approvalContent2;
    @BindView(R.id.ll_approval_line2)
    LinearLayout llApprovalLine2;
    @BindView(R.id.approval_text3)
    TextView approvalText3;
    @BindView(R.id.approval_content3)
    TextView approvalContent3;
    @BindView(R.id.ll_approval_line3)
    LinearLayout llApprovalLine3;
    @BindView(R.id.approval_text4)
    TextView approvalText4;
    @BindView(R.id.approval_content4)
    TextView approvalContent4;
    @BindView(R.id.ll_approval_line4)
    LinearLayout llApprovalLine4;
    @BindView(R.id.approval_text5)
    TextView approvalText5;
    @BindView(R.id.approval_content5)
    TextView approvalContent5;
    @BindView(R.id.ll_approval_line5)
    LinearLayout llApprovalLine5;
    @BindView(R.id.approval_text6)
    TextView approvalText6;
    @BindView(R.id.approval_content6)
    TextView approvalContent6;
    @BindView(R.id.ll_approval_line6)
    LinearLayout llApprovalLine6;
    @BindView(R.id.approval_text7)
    TextView approvalText7;
    @BindView(R.id.approval_content7)
    TextView approvalContent7;
    @BindView(R.id.ll_approval_line7)
    LinearLayout llApprovalLine7;
    @BindView(R.id.approval_text8)
    TextView approvalText8;
    @BindView(R.id.approval_content8)
    TextView approvalContent8;
    @BindView(R.id.ll_approval_line8)
    LinearLayout llApprovalLine8;
    @BindView(R.id.approval_text9)
    TextView approvalText9;
    @BindView(R.id.approval_content9)
    TextView approvalContent9;
    @BindView(R.id.ll_approval_line9)
    LinearLayout llApprovalLine9;
    @BindView(R.id.approval_text10)
    TextView approvalText10;
    @BindView(R.id.approval_content10)
    TextView approvalContent10;
    @BindView(R.id.ll_approval_line10)
    LinearLayout llApprovalLine10;
    @BindView(R.id.approval_text11)
    TextView approvalText11;
    @BindView(R.id.approval_content11)
    TextView approvalContent11;
    @BindView(R.id.ll_approval_line11)
    LinearLayout llApprovalLine11;
    @BindView(R.id.cl_layout)
    ConstraintLayout clLayout;
    @BindView(R.id.scll)
    ScrollView scll;

    @BindView(R.id.approval_text12)
    TextView approvalText12;
    @BindView(R.id.approval_content12)
    TextView approvalContent12;
    @BindView(R.id.ll_approval_line12)
    LinearLayout llApprovalLine12;
    @BindView(R.id.approval_text13)
    TextView approvalText13;
    @BindView(R.id.approval_content13)
    TextView approvalContent13;
    @BindView(R.id.ll_approval_line13)
    LinearLayout llApprovalLine13;
    @BindView(R.id.llay)
    LinearLayout llay;
    @BindView(R.id.rl_update_data)
    RelativeLayout rlUpdateData;
    @BindView(R.id.rl_delete_order)
    RelativeLayout rlDeleteOrder;
    @BindView(R.id.ll_bottom_approval)
    LinearLayout llBottomApproval;
    private GoBackBean.RecordsBean recordsBean;
    private int type;
    private ApprovalAllShowImageAdapter allShowImageAdapter;
    private List<ProcurementDetails> procurementDetails;
    private ApprolvalEmergencyAdatper adatper;
    private List<ReimburseDetail> baoXiaoMingXiVos;
    private LoginBean loginBean;
    private HealthStatusBean.RecordsBean healthrecordsBean;

    @Override
    protected void initData() {


        type = getIntent().getIntExtra("type", 0);
        loginBean = (LoginBean) this.getIntent().getSerializableExtra("LoginBean");
        switch (type) {
            case 1:
                //去反信息
                recordsBean = (GoBackBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
                getGoBackData();
                toolbarTitleTv.setText(recordsBean.getShenQingRName());
                break;
            case 2:
                //健康信息
                healthrecordsBean = (HealthStatusBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
                getHealthData();
                toolbarTitleTv.setText(healthrecordsBean.getShenQingRName());
                break;

        }


    }

    private void getGoBackData() {
        new OrderManager().quFanChengqkFindbyid(recordsBean.getQuFanChengQKId() + "", new BaseNet.BaseCallback<QuFanChengQKBean>() {
            @Override
            public void messageSuccess(QuFanChengQKBean bean) {

                btApprovalFlag.setText(bean.getShenQingRName());
                btApprovalName.setText(bean.getDateTime());
                statusDescription.setText("");
                llApprovalLine1.setVisibility(View.VISIBLE);
                llApprovalLine2.setVisibility(View.VISIBLE);
                llApprovalLine3.setVisibility(View.VISIBLE);
                llApprovalLine4.setVisibility(View.VISIBLE);
                llApprovalLine5.setVisibility(View.VISIBLE);
                llApprovalLine6.setVisibility(View.VISIBLE);
                llApprovalLine7.setVisibility(View.VISIBLE);
                llApprovalLine8.setVisibility(View.VISIBLE);
                llApprovalLine9.setVisibility(View.VISIBLE);
                llApprovalLine10.setVisibility(View.VISIBLE);
                approvalText1.setText("去程时间：");
                approvalText2.setText("目的地(省、市)：");
                approvalText3.setText("是否经湖北中转：");
                approvalText4.setText("返程时间：");
                approvalText5.setText("出发地(省、市)：");
                approvalText6.setText("返程工具：");
                approvalText7.setText("航班/车次/车牌号：");
                approvalText8.setText("是否经停湖北：");
                approvalText9.setText("经停湖北何地：");
                approvalText10.setText("经停时间：");


                approvalContent1.setText(bean.getQuChengSJ());
                approvalContent2.setText(bean.getMuDiD());
                approvalContent3.setText(bean.getShiFouJHBZZ());
                approvalContent4.setText(bean.getFanChengSJ());
                approvalContent5.setText(bean.getChuFaD());
                approvalContent6.setText(bean.getFanChengTypeDisplay());
                approvalContent7.setText(bean.getFanChengCPH());
                approvalContent8.setText(bean.getShiFouJTHB());
                approvalContent9.setText(bean.getJingTingHBHD());
                approvalContent10.setText(bean.getJingTingSJ());

            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private void getHealthData() {
        new OrderManager().jianKangqkFindbyid(healthrecordsBean.getJianKangQKId() + "", new BaseNet.BaseCallback<JianKangQKBean>() {
            @Override
            public void messageSuccess(JianKangQKBean bean) {

                btApprovalFlag.setText(bean.getShenQingRName());
                btApprovalName.setText(bean.getDateTime());
                statusDescription.setText(bean.getShangXiaWDisplay());
                llApprovalLine1.setVisibility(View.VISIBLE);
                llApprovalLine2.setVisibility(View.VISIBLE);
                llApprovalLine3.setVisibility(View.VISIBLE);
                llApprovalLine4.setVisibility(View.VISIBLE);
                llApprovalLine5.setVisibility(View.VISIBLE);
                llApprovalLine6.setVisibility(View.VISIBLE);
                llApprovalLine7.setVisibility(View.VISIBLE);
                llApprovalLine8.setVisibility(View.VISIBLE);
                llApprovalLine9.setVisibility(View.VISIBLE);
                llApprovalLine10.setVisibility(View.VISIBLE);
                llApprovalLine11.setVisibility(View.VISIBLE);

                approvalText1.setText("形式：");
                approvalText2.setText("体温是否正常：");
                approvalText3.setText("具体温度：");
                approvalText4.setText("目前状态：");
                approvalText5.setText("是否有发烧、咳嗽、感冒等症状：");
                approvalText6.setText("共同生活家人是否有发热、咳嗽、感冒等症状：");
                approvalText7.setText("备注：");
                approvalText8.setText("是否接触疫情高发区人员：");
                approvalText9.setText("是否密切接触者：");
                approvalText10.setText("是否疑似病例：");
                approvalText11.setText("是否确诊病例：");


                approvalContent1.setText(bean.getXingShiDisplay());
                approvalContent2.setText(bean.getTiWenSFZC());
                approvalContent3.setText(bean.getJuTiWD());
                approvalContent4.setText(bean.getMuQianZTDisplay());
                approvalContent5.setText(bean.getShiFouYZZ());
                approvalContent6.setText(bean.getJiaRenSFYZZ());
                approvalContent7.setText(bean.getBeiZhu());
                approvalContent8.setText(bean.getShiFouJCYQRY());
                approvalContent9.setText(bean.getShiFouMQJCZ());
                approvalContent10.setText(bean.getShiFouYSBL());
                approvalContent11.setText(bean.getShiFouQZBL());


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
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.black));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goback_health;
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

//    @OnClick({R.id.iv_comeback, R.id.reject_approval, R.id.adopt_approval, R.id.toolbar_left_btn})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.toolbar_left_btn:
//                finish();
//                break;
//            case R.id.iv_comeback:
////                new OrderManager().renshiApproval(recordsBean.getRenShiId() + "", "3", "撤销", new BaseNet.EntityCallback() {
////                    @Override
////                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
////                        finish();
////                    }
////                });
//                break;
//            case R.id.reject_approval:
////                new OrderManager().renshiApproval(recordsBean.getRenShiId() + "", "2", "拒绝", new BaseNet.EntityCallback() {
////                    @Override
////                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
////                        finish();
////                    }
////                });
//                break;
//            case R.id.adopt_approval:
////                new OrderManager().renshiApproval(recordsBean.getRenShiId() + "", "1", "", new BaseNet.EntityCallback() {
////                    @Override
////                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
////                        finish();
////                    }
////                });
//                break;
//        }
//    }

    @OnClick({R.id.toolbar_left_btn, R.id.rl_update_data, R.id.rl_delete_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.rl_update_data:
                ToastUtil.showToast("修改数据");
                if (type == 1) {
                    Intent intent = new Intent(GoBackHealthActivity.this, AddGoBackCardActivity.class);
                    intent.putExtra("quFanChengQKId",recordsBean.getQuFanChengQKId());
                    startActivityForResult(intent,1);
                } else if (type == 2) {
                    Intent intent = new Intent(GoBackHealthActivity.this, AddHealthDataActivity.class);
                    intent.putExtra("jianKangQKId",healthrecordsBean.getJianKangQKId());
                    startActivityForResult(intent,2);
                }
                break;
            case R.id.rl_delete_order:
                ToastUtil.showToast("删除数据");
                if (type == 1) {
                    new OrderManager().quFanChengqkRemove(String.valueOf(recordsBean.getQuFanChengQKId()), new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            setResult(1);
                            finish();
                        }
                    });
                } else if (type == 2) {
                    new OrderManager().quFanChengqkRemove(String.valueOf(recordsBean.getQuFanChengQKId()), new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            setResult(2);
                            finish();
                        }
                    });
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            getGoBackData();

        }else if (requestCode == 2){
            getHealthData();
        }

    }
}
