package com.xyl.ui.activity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.personnel.ApprolvalEmergencyAdatper;
import com.xyl.adapter.personnel.ApprovalAllShowImageAdapter;
import com.xyl.adapter.personnel.ReimburseDetialAdatper;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.ApprovalDetailBean;
import com.xyl.domain.personnel.EmergencyDetailBean;
import com.xyl.domain.personnel.ProcurementDetails;
import com.xyl.domain.personnel.ReimburseBean;
import com.xyl.domain.personnel.ReimburseDetail;
import com.xyl.domain.personnel.SealDetailBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.domain.personnel.StandbyDetailBean;
import com.xyl.domain.personnel.leaveDetailBean;
import com.xyl.net.OrderManager;
import com.xyl.net.PersonnelManager;
import com.xyl.ui.view.SpaceItemDecoration;
import com.xyl.ui.widget.MessageProcessWidget;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApprolvalAllActivity extends BaseActivity {

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
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.approval_number)
    TextView approvalNumber;
    @BindView(R.id.approval_department)
    TextView approvalDepartment;
    @BindView(R.id.approval_text1)
    TextView approvalText1;
    @BindView(R.id.approval_content1)
    TextView approvalContent1;
    @BindView(R.id.approval_text2)
    TextView approvalText2;
    @BindView(R.id.approval_content2)
    TextView approvalContent2;
    @BindView(R.id.approval_text3)
    TextView approvalText3;
    @BindView(R.id.approval_content3)
    TextView approvalContent3;
    @BindView(R.id.approval_text4)
    TextView approvalText4;
    @BindView(R.id.approval_content4)
    TextView approvalContent4;
    @BindView(R.id.approval_text5)
    TextView approvalText5;
    @BindView(R.id.approval_content5)
    TextView approvalContent5;
    @BindView(R.id.approval_text6)
    TextView approvalText6;
    @BindView(R.id.approval_content6)
    TextView approvalContent6;
    @BindView(R.id.approval_text7)
    TextView approvalText7;
    @BindView(R.id.approval_content7)
    TextView approvalContent7;
    @BindView(R.id.approval_text8)
    TextView approvalText8;
    @BindView(R.id.approval_content8)
    TextView approvalContent8;
    @BindView(R.id.approval_text9)
    TextView approvalText9;
    @BindView(R.id.approval_content9)
    TextView approvalContent9;

    @BindView(R.id.rv_picture)
    RecyclerView rvPicture;
    @BindView(R.id.cl_layout)
    ConstraintLayout clLayout;
    @BindView(R.id.status_description)
    TextView statusDescription;
    @BindView(R.id.ll_approval_line1)
    LinearLayout llApprovalLine1;
    @BindView(R.id.ll_approval_line2)
    LinearLayout llApprovalLine2;
    @BindView(R.id.ll_approval_line3)
    LinearLayout llApprovalLine3;
    @BindView(R.id.ll_approval_line4)
    LinearLayout llApprovalLine4;
    @BindView(R.id.ll_approval_line5)
    LinearLayout llApprovalLine5;
    @BindView(R.id.ll_approval_line6)
    LinearLayout llApprovalLine6;
    @BindView(R.id.ll_approval_line7)
    LinearLayout llApprovalLine7;
    @BindView(R.id.ll_approval_line8)
    LinearLayout llApprovalLine8;
    @BindView(R.id.ll_approval_line9)
    LinearLayout llApprovalLine9;
    @BindView(R.id.approval_text10)
    TextView approvalText10;
    @BindView(R.id.ll_approval_line10)
    LinearLayout llApprovalLine10;
    @BindView(R.id.mpw)
    MessageProcessWidget mpw;

    @BindView(R.id.iv_comeback)
    ImageView ivComeback;
    @BindView(R.id.reject_approval)
    TextView rejectApproval;
    @BindView(R.id.adopt_approval)
    TextView adoptApproval;
    @BindView(R.id.llay)
    LinearLayout llay;
    @BindView(R.id.ll_bottom_approval)
    LinearLayout llBottomApproval;
    private SelectAllPersonalBean.RecordsBean recordsBean;
    private int type;
    private ApprovalAllShowImageAdapter allShowImageAdapter;
    private List<ProcurementDetails> procurementDetails;
    private ApprolvalEmergencyAdatper adatper;
    private List<ReimburseDetail> baoXiaoMingXiVos;
    private LoginBean loginBean;

    @Override
    protected void initData() {

        recordsBean = (SelectAllPersonalBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        toolbarTitleTv.setText(recordsBean.getRenShiTypeDisplay());
        type = getIntent().getIntExtra("type", 0);
        loginBean = (LoginBean) this.getIntent().getSerializableExtra("LoginBean");
        switch (type) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                //请假
                getLeaveDetails();
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                //采购  PurchaseActivity
                getPruchasDetails();
                break;
            case 10:
                //应急采购 EmergencyActivity
                getEmergencyDetails();
                break;
            case 11:
                //报销 ReimbursementActivity
                getReimburseDetails();
                break;
            case 12:
                //通用审批 ApprovalActivity
                getApprovalDetails();
                break;
            case 14:
                //用印申请 SealActivity
                getSealDetails();
                break;
            case 16:
                //备用金 StandbyActivity
                getStandbyDetails();
                break;

        }


    }

    private void getReimburseDetails() {
        if (recordsBean != null) {
            new PersonnelManager().reimburseDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<ReimburseBean>() {
                @Override
                public void messageSuccess(ReimburseBean bean) {

                    boolean contains = StringUtils.contains(bean.getShenPiRen(), loginBean.staffId);
                    if (contains) {
                        if (bean.getStatus() == 0) {
                            setButtonContent();
                        }//0待审批1彻底审批通过2彻底没通过
                    }



                    approvalNumber.setText(bean.getRenShiId() + "");
                    approvalDepartment.setText(bean.getBuildingName());
                    btApprovalFlag.setText(bean.getShenQingRenName());
                    btApprovalName.setText(bean.getShenQingRenName());
                    statusDescription.setText(bean.getStatusDisplay());


                    SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
                    allShowImageAdapter = new ApprovalAllShowImageAdapter(ApprolvalAllActivity.this, bean.getRenShiPictures());
                    rvPicture.addItemDecoration(spaceItemDecoration);
                    rvPicture.setLayoutManager(new GridLayoutManager(ApprolvalAllActivity.this, 4));
                    rvPicture.setAdapter(allShowImageAdapter);
                    StringBuilder sb = new StringBuilder();
                    bean.getRenShiTraces();
                    for (int i = 0; i < bean.getRenShiTraces().size(); i++) {
                        sb.append(bean.getRenShiTraces().get(i).getAction());
                        sb.append(": ");
                        sb.append(bean.getRenShiTraces().get(i).getStaffName());
                        sb.append(" ");
                        sb.append(bean.getRenShiTraces().get(i).getExecuteTime());
                        mpw.setTitle("状态");
                        mpw.setText(i, sb.toString());
                        sb.delete(0, sb.length());
                    }
                    baoXiaoMingXiVos = bean.getBaoXiaoMingXiVos();
//                    rvMingxi.setLayoutManager(new LinearLayoutManager(ApprolvalAllActivity.this));
//                    ReimburseDetialAdatper reimburseDetialAdatper = new ReimburseDetialAdatper(ApprolvalAllActivity.this, baoXiaoMingXiVos);
//                    rvMingxi.setAdapter(reimburseDetialAdatper);
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

    private void getStandbyDetails() {
        if (recordsBean != null) {
            new PersonnelManager().standbyDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<StandbyDetailBean>() {
                @Override
                public void messageSuccess(StandbyDetailBean bean) {

                    boolean contains = StringUtils.contains(bean.getShenPiRen(), loginBean.staffId);
                    if (contains) {
                        if (bean.getStatus() == 0) {
                            setButtonContent();
                        }//0待审批1彻底审批通过2彻底没通过
                    }


                    approvalNumber.setText(bean.getRenShiId() + "");
                    approvalDepartment.setText(bean.getBuildingName());
                    btApprovalFlag.setText(bean.getShenQingRenName());
                    btApprovalName.setText(bean.getShenQingRenName());
                    statusDescription.setText(bean.getStatusDisplay());
                    llApprovalLine1.setVisibility(View.VISIBLE);
                    llApprovalLine2.setVisibility(View.VISIBLE);
                    llApprovalLine3.setVisibility(View.VISIBLE);
                    llApprovalLine4.setVisibility(View.VISIBLE);
                    llApprovalLine5.setVisibility(View.VISIBLE);
                    llApprovalLine6.setVisibility(View.VISIBLE);
                    llApprovalLine7.setVisibility(View.VISIBLE);
                    llApprovalLine8.setVisibility(View.VISIBLE);
                    approvalText1.setText("申请人");
                    approvalText2.setText("部门");
                    approvalText3.setText("事由");
                    approvalText4.setText("申请金额");
                    approvalText5.setText("使用日期");
                    approvalText6.setText("归还日期");
                    approvalText7.setText("出纳");
                    approvalText8.setText("备注");


                    approvalContent1.setText(bean.getShenQingRenName());
                    approvalContent2.setText(bean.getBuildingName());
                    approvalContent3.setText(bean.getShiYou());
                    approvalContent4.setText(bean.getShenQingJE());
                    approvalContent5.setText(bean.getShiYongSJ());
                    approvalContent6.setText(bean.getGuiHuanSJ());
                    approvalContent7.setText(bean.getChuNa());
                    approvalContent8.setText(bean.getDescription());

                    SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
                    allShowImageAdapter = new ApprovalAllShowImageAdapter(ApprolvalAllActivity.this, bean.getRenShiPictures());
                    rvPicture.addItemDecoration(spaceItemDecoration);
                    rvPicture.setLayoutManager(new GridLayoutManager(ApprolvalAllActivity.this, 4));
                    rvPicture.setAdapter(allShowImageAdapter);
                    StringBuilder sb = new StringBuilder();
                    bean.getRenShiTraces();
                    for (int i = 0; i < bean.getRenShiTraces().size(); i++) {
                        sb.append(bean.getRenShiTraces().get(i).getAction());
                        sb.append(": ");
                        sb.append(bean.getRenShiTraces().get(i).getStaffName());
                        sb.append(" ");
                        sb.append(bean.getRenShiTraces().get(i).getExecuteTime());
                        mpw.setTitle("状态");
                        mpw.setText(i, sb.toString());
                        sb.delete(0, sb.length());
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
    }

    private void getSealDetails() {
        if (recordsBean != null) {
            new PersonnelManager().sealDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<SealDetailBean>() {
                @Override
                public void messageSuccess(SealDetailBean bean) {

                    boolean contains = StringUtils.contains(bean.getShenPiRen(), loginBean.staffId);
                    if (contains) {
                        if (bean.getStatus() == 0) {
                            setButtonContent();
                        }//0待审批1彻底审批通过2彻底没通过
                    }


                    approvalNumber.setText(bean.getRenShiId() + "");
                    approvalDepartment.setText(bean.getBuildingName());
                    btApprovalFlag.setText(bean.getShenQingRenName());
                    btApprovalName.setText(bean.getShenQingRenName());
                    statusDescription.setText(bean.getStatusDisplay());
                    llApprovalLine1.setVisibility(View.VISIBLE);
                    llApprovalLine2.setVisibility(View.VISIBLE);
                    llApprovalLine3.setVisibility(View.VISIBLE);
                    llApprovalLine4.setVisibility(View.VISIBLE);
                    llApprovalLine5.setVisibility(View.VISIBLE);
                    llApprovalLine6.setVisibility(View.VISIBLE);

                    approvalText1.setText("用印单位");
                    approvalText2.setText("日期");
                    approvalText3.setText("印章名称");
                    approvalText4.setText("用印文件名称");
                    approvalText5.setText("文件份数");
                    approvalText6.setText("备注");


                    approvalContent1.setText(bean.getYongYinDW());
                    approvalContent2.setText(bean.getCreateTime());
                    approvalContent3.setText(bean.getYinZhangMC());
                    approvalContent4.setText(bean.getYongYinWJMC());
                    approvalContent5.setText(bean.getWenJianFS() + "");
                    approvalContent6.setText(bean.getDescription());

                    SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
                    allShowImageAdapter = new ApprovalAllShowImageAdapter(ApprolvalAllActivity.this, bean.getRenShiPictures());
                    rvPicture.addItemDecoration(spaceItemDecoration);
                    rvPicture.setLayoutManager(new GridLayoutManager(ApprolvalAllActivity.this, 4));
                    rvPicture.setAdapter(allShowImageAdapter);
                    StringBuilder sb = new StringBuilder();
                    bean.getRenShiTraces();
                    for (int i = 0; i < bean.getRenShiTraces().size(); i++) {
                        sb.append(bean.getRenShiTraces().get(i).getAction());
                        sb.append(": ");
                        sb.append(bean.getRenShiTraces().get(i).getStaffName());
                        sb.append(" ");
                        sb.append(bean.getRenShiTraces().get(i).getExecuteTime());
                        mpw.setTitle("状态");
                        mpw.setText(i, sb.toString());
                        sb.delete(0, sb.length());
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
    }

    private void getEmergencyDetails() {
        if (recordsBean != null) {
            new PersonnelManager().emergencyDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<EmergencyDetailBean>() {
                @Override
                public void messageSuccess(EmergencyDetailBean bean) {
                    boolean contains = StringUtils.contains(bean.getShenPiRen(), loginBean.staffId);
                    if (contains) {
                        if (bean.getStatus() == 0) {
                            setButtonContent();
                        }//0待审批1彻底审批通过2彻底没通过
                    }

                    approvalNumber.setText(bean.getRenShiId() + "");
                    approvalDepartment.setText(bean.getBuildingName());
                    btApprovalFlag.setText(bean.getShenQingRenName());
                    btApprovalName.setText(bean.getShenQingRenName());
                    statusDescription.setText(bean.getStatusDisplay());


                    SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
                    allShowImageAdapter = new ApprovalAllShowImageAdapter(ApprolvalAllActivity.this, bean.getRenShiPictures());
                    rvPicture.addItemDecoration(spaceItemDecoration);
                    rvPicture.setLayoutManager(new GridLayoutManager(ApprolvalAllActivity.this, 4));
                    rvPicture.setAdapter(allShowImageAdapter);
                    StringBuilder sb = new StringBuilder();
                    bean.getRenShiTraces();
                    for (int i = 0; i < bean.getRenShiTraces().size(); i++) {
                        sb.append(bean.getRenShiTraces().get(i).getAction());
                        sb.append(": ");
                        sb.append(bean.getRenShiTraces().get(i).getStaffName());
                        sb.append(" ");
                        sb.append(bean.getRenShiTraces().get(i).getExecuteTime());
                        mpw.setTitle("状态");
                        mpw.setText(i, sb.toString());
                        sb.delete(0, sb.length());
                    }


                    procurementDetails = bean.getCaiGouMingXis();
//                    rvMingxi.setLayoutManager(new LinearLayoutManager(ApprolvalAllActivity.this));
//                    adatper = new ApprolvalEmergencyAdatper(ApprolvalAllActivity.this, procurementDetails, type);
//                    rvMingxi.setAdapter(adatper);
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

    private void getApprovalDetails() {
        if (recordsBean != null) {
            new PersonnelManager().approvalDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<ApprovalDetailBean>() {
                @Override
                public void messageSuccess(ApprovalDetailBean bean) {

                    boolean contains = StringUtils.contains(bean.getShenPiRen(), loginBean.staffId);
                    if (contains) {
                        if (bean.getStatus() == 0) {
                            setButtonContent();
                        }//0待审批1彻底审批通过2彻底没通过
                    }

                    approvalNumber.setText(bean.getRenShiId() + "");
                    approvalDepartment.setText(bean.getBuildingName());
                    btApprovalFlag.setText(bean.getShenQingRenName());
                    btApprovalName.setText(bean.getShenQingRenName());
                    statusDescription.setText(bean.getStatusDisplay());
                    llApprovalLine1.setVisibility(View.VISIBLE);
                    llApprovalLine2.setVisibility(View.VISIBLE);


                    approvalText1.setText("申请内容");
                    approvalText2.setText("审批详情");


                    approvalContent1.setText(bean.getShenQingNR());
                    approvalContent2.setText(bean.getShenPiXQ());


                    SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
                    allShowImageAdapter = new ApprovalAllShowImageAdapter(ApprolvalAllActivity.this, bean.getRenShiPictures());
                    rvPicture.addItemDecoration(spaceItemDecoration);
                    rvPicture.setLayoutManager(new GridLayoutManager(ApprolvalAllActivity.this, 4));
                    rvPicture.setAdapter(allShowImageAdapter);
                    StringBuilder sb = new StringBuilder();
                    bean.getRenShiTraces();
                    for (int i = 0; i < bean.getRenShiTraces().size(); i++) {
                        sb.append(bean.getRenShiTraces().get(i).getAction());
                        sb.append(": ");
                        sb.append(bean.getRenShiTraces().get(i).getStaffName());
                        sb.append(" ");
                        sb.append(bean.getRenShiTraces().get(i).getExecuteTime());
                        mpw.setTitle("状态");
                        mpw.setText(i, sb.toString());
                        sb.delete(0, sb.length());
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
    }

    private void getPruchasDetails() {
        if (recordsBean != null) {
            new PersonnelManager().purchaseDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<EmergencyDetailBean>() {
                @Override
                public void messageSuccess(EmergencyDetailBean bean) {

                    boolean contains = StringUtils.contains(bean.getShenPiRen(), loginBean.staffId);
                    if (contains) {
                        if (bean.getStatus() == 0) {
                            setButtonContent();
                        }//0待审批1彻底审批通过2彻底没通过
                    }
                    approvalNumber.setText(bean.getRenShiId() + "");
                    approvalDepartment.setText(bean.getBuildingName());
                    btApprovalFlag.setText(bean.getShenQingRenName());
                    btApprovalName.setText(bean.getShenQingRenName());
                    statusDescription.setText(bean.getStatusDisplay());

                    llApprovalLine1.setVisibility(View.VISIBLE);
                    llApprovalLine2.setVisibility(View.VISIBLE);
                    llApprovalLine3.setVisibility(View.VISIBLE);
                    llApprovalLine4.setVisibility(View.VISIBLE);
                    llApprovalLine5.setVisibility(View.VISIBLE);

                    approvalText1.setText("申请事由");
                    approvalText2.setText("采购类型");
                    approvalText3.setText("期望交付时间");
                    approvalText4.setText("支付方式");
                    approvalText5.setText("备注");


                    approvalContent1.setText(bean.getShiYou() + "");
                    approvalContent2.setText(bean.getCaiGouLXDisplay() + "");
                    approvalContent3.setText(bean.getQiWangJFRQ());
                    approvalContent4.setText(bean.getZhiFuFSDisplay());
                    approvalContent5.setText(bean.getDescription());
//
//
                    SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
                    allShowImageAdapter = new ApprovalAllShowImageAdapter(ApprolvalAllActivity.this, bean.getRenShiPictures());
                    rvPicture.addItemDecoration(spaceItemDecoration);
                    rvPicture.setLayoutManager(new GridLayoutManager(ApprolvalAllActivity.this, 4));
                    rvPicture.setAdapter(allShowImageAdapter);
                    StringBuilder sb = new StringBuilder();
                    bean.getRenShiTraces();
                    for (int i = 0; i < bean.getRenShiTraces().size(); i++) {
                        sb.append(bean.getRenShiTraces().get(i).getAction());
                        sb.append(": ");
                        sb.append(bean.getRenShiTraces().get(i).getStaffName());
                        sb.append(" ");
                        sb.append(bean.getRenShiTraces().get(i).getExecuteTime());
                        mpw.setTitle("状态");
                        mpw.setText(i, sb.toString());
                        sb.delete(0, sb.length());
                    }

                    procurementDetails = bean.getCaiGouMingXis();
//                    rvMingxi.setLayoutManager(new LinearLayoutManager(ApprolvalAllActivity.this));
//                    adatper = new ApprolvalEmergencyAdatper(ApprolvalAllActivity.this, procurementDetails, type);
//                    rvMingxi.setAdapter(adatper);
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


    private void getLeaveDetails() {
        if (recordsBean != null) {
            new PersonnelManager().leaveDetails(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<leaveDetailBean>() {
                @Override
                public void messageSuccess(leaveDetailBean bean) {
                    boolean contains = StringUtils.contains(bean.getShenPiRen(), loginBean.staffId);
                    if (contains) {
                        if (bean.getStatus() == 0) {
                            setButtonContent();
                        }//0待审批1彻底审批通过2彻底没通过
                    }
                    approvalNumber.setText(bean.getRenShiId() + "");
                    approvalDepartment.setText(bean.getBuildingName());
                    btApprovalFlag.setText(bean.getShenQingRenName());
                    btApprovalName.setText(bean.getShenQingRenName());
                    statusDescription.setText(bean.getStatusDisplay());
                    llApprovalLine1.setVisibility(View.VISIBLE);
                    llApprovalLine2.setVisibility(View.VISIBLE);
                    llApprovalLine3.setVisibility(View.VISIBLE);
                    llApprovalLine4.setVisibility(View.VISIBLE);
                    llApprovalLine5.setVisibility(View.VISIBLE);
                    approvalText1.setText("请假类型");
                    approvalText2.setText("开始时间");
                    approvalText3.setText("结束时间");
                    approvalText4.setText("请假天数");
                    approvalText5.setText("请假理由");

                    approvalContent1.setText(bean.getQingJiaLXDisplay());
                    approvalContent2.setText(bean.getStartTime());
                    approvalContent3.setText(bean.getEndTime());
                    approvalContent4.setText(bean.getTianShu());
                    approvalContent5.setText(bean.getShiYou());
                    approvalContent5.setText(bean.getQingJiaLXDisplay());

                    SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
                    allShowImageAdapter = new ApprovalAllShowImageAdapter(ApprolvalAllActivity.this, bean.getRenShiPictures());
                    rvPicture.addItemDecoration(spaceItemDecoration);
                    rvPicture.setLayoutManager(new GridLayoutManager(ApprolvalAllActivity.this, 4));
                    rvPicture.setAdapter(allShowImageAdapter);
                    StringBuilder sb = new StringBuilder();
                    bean.getRenShiTraces();
                    for (int i = 0; i < bean.getRenShiTraces().size(); i++) {
                        sb.append(bean.getRenShiTraces().get(i).getAction());
                        sb.append(": ");
                        sb.append(bean.getRenShiTraces().get(i).getStaffName());
                        sb.append(" ");
                        sb.append(bean.getRenShiTraces().get(i).getExecuteTime());
                        mpw.setTitle("状态");
                        mpw.setText(i, sb.toString());
                        sb.delete(0, sb.length());
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
    }

    private void setButtonContent() {
        llBottomApproval.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.black));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_approlval_all;
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

    @OnClick({R.id.iv_comeback, R.id.reject_approval, R.id.adopt_approval, R.id.toolbar_left_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.iv_comeback:
                new OrderManager().renshiApproval(recordsBean.getRenShiId() + "", "3", "撤销", new BaseNet.EntityCallback() {
                    @Override
                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
                        finish();
                    }
                });
                break;
            case R.id.reject_approval:
                new OrderManager().renshiApproval(recordsBean.getRenShiId() + "", "2", "拒绝", new BaseNet.EntityCallback() {
                    @Override
                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
                        finish();
                    }
                });
                break;
            case R.id.adopt_approval:
                new OrderManager().renshiApproval(recordsBean.getRenShiId() + "", "1", "", new BaseNet.EntityCallback() {
                    @Override
                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
                        finish();
                    }
                });
                break;
        }
    }
}
