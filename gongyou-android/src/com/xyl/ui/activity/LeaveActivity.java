package com.xyl.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.xyl.R;
import com.xyl.adapter.LeaveShowImageAdapter;
import com.xyl.adapter.personnel.WorkFlowAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.RenShiPictures;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.domain.personnel.WorkFlowBean;
import com.xyl.domain.personnel.leaveDetailBean;
import com.xyl.net.PersonnelManager;
import com.xyl.ui.view.Glide4Engine;
import com.xyl.ui.view.MyLinearLayout;
import com.xyl.ui.view.SpaceItemDecoration;
import com.xyl.utils.ToastUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaveActivity extends BaseActivity {

    boolean is = false;

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
    @BindView(R.id.open_time_data)
    TextView openTimeData;
    @BindView(R.id.end_time_data)
    TextView endTimeData;

    @BindView(R.id.iv_fuzhu)
    ImageView ivFuzhu;
    @BindView(R.id.iv_end_fuzhu)
    ImageView ivEndFuzhu;
    //    @BindView(R.id.iv_add_image)
//    ImageView ivAddImage;
//    @BindView(R.id.tv_leave_people)
//    TextView tvLeavePeople;
    @BindView(R.id.tv_leave_type)
    TextView tvLeaveType;
    @BindView(R.id.et_leave_number)
    EditText etLeaveNumber;
    @BindView(R.id.bt_leave_submit)
    Button btLeaveSubmit;
    @BindView(R.id.et_leave_reason)
    EditText etLeaveReason;
    @BindView(R.id.rv_showImags)
    RecyclerView rvShowImags;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.ll_open_time)
    MyLinearLayout llOpenTime;
    @BindView(R.id.ll_end_time)
    MyLinearLayout llEndTime;
    //    @BindView(R.id.iv_approval)
//    ImageView ivApproval;
    @BindView(R.id.work_flow)
    RecyclerView workFlow;
    @BindView(R.id.bt_cancel)
    Button btCancel;
    @BindView(R.id.bt_approval)
    Button btApproval;
    @BindView(R.id.ll_workflow)
    LinearLayout llWorkflow;
    private TimePickerView pvCustomLunar;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private LeaveShowImageAdapter leaveShowImageAdapter;

    private List<Uri> mUris;
    private List<String> mPaths;
    private List<File> imageFile;
    private String[] leaveType = {"事假", "病假", "年假", "调休", "婚假", "产假"};
    private List<String> leaveTypes;


    private Integer qingJiaLX;
    private SelectAllPersonalBean.RecordsBean recordsBean;
    private String renShiId;
    private String qingJiaId;
    private List<RenShiPictures> renShiPictures;
    private com.xyl.domain.personnel.leaveDetailBean leaveDetailBean;

    @Override
    protected void initData() {
        recordsBean = (SelectAllPersonalBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        if (recordsBean != null) {
            new PersonnelManager().leaveDetails(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<leaveDetailBean>() {
                @Override
                public void messageSuccess(leaveDetailBean bean) {
                    leaveDetailBean = bean;
                    qingJiaLX = bean.getQingJiaLX();
                    renShiId = String.valueOf(bean.getRenShiId());
                    qingJiaId = String.valueOf(bean.getQingJiaId());
                    tvLeaveType.setText(bean.getQingJiaLXDisplay());
                    openTimeData.setText(bean.getStartTime());
                    endTimeData.setText(bean.getEndTime());
                    etLeaveNumber.setText(bean.getTianShu());
                    etLeaveReason.setText(bean.getShiYou());
                    renShiPictures = bean.getRenShiPictures();
                    leaveShowImageAdapter.setIntentData(renShiPictures, 1);
                    if (bean.getStatus()==0){
                        llWorkflow.setVisibility(View.VISIBLE);
                        btLeaveSubmit.setVisibility(View.GONE);
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

        new PersonnelManager().workFlow("5", new BaseNet.BaseCallback<WorkFlowBean>() {
            @Override
            public void messageSuccess(WorkFlowBean bean) {
                if (bean.getRecords().size() == 0){
                    return;
                }
                workFlow.setLayoutManager(new GridLayoutManager(LeaveActivity.this, 4));
                WorkFlowAdapter workFlowAdapter = new WorkFlowAdapter(LeaveActivity.this, bean.getRecords().get(0));
                workFlow.setAdapter(workFlowAdapter);

            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });

        imageFile = new ArrayList<>();
        leaveTypes = new ArrayList<>(Arrays.asList(leaveType));
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
        leaveShowImageAdapter = new LeaveShowImageAdapter(LeaveActivity.this);
        rvShowImags.addItemDecoration(spaceItemDecoration);
        rvShowImags.setLayoutManager(new GridLayoutManager(LeaveActivity.this, 4));
        rvShowImags.setAdapter(leaveShowImageAdapter);

        leaveShowImageAdapter.setOnDeleteItemClickListener(new LeaveShowImageAdapter.onDeleteItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                ToastUtil.showToast("" + position);
                mUris.remove(position);
                mPaths.remove(position);
                leaveShowImageAdapter.notifyDataSetChanged();
            }
        });
        leaveShowImageAdapter.setOnItemClickListener(new LeaveShowImageAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, int size) {
                if (position == size) {
                    Matisse.from(LeaveActivity.this)
                            .choose(MimeType.ofAll(), false)
                            .countable(true)
                            .capture(true)
                            .captureStrategy(
                                    new CaptureStrategy(true, "com.xyl.fileprovider", "test"))
                            .maxSelectable(9)
                            .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                            .gridExpectedSize(
                                    getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.85f)
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                            .imageEngine(new Glide4Engine())    // for glide-V4
                            .setOnSelectedListener(new OnSelectedListener() {
                                @Override
                                public void onSelected(
                                        @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                    // DO SOMETHING IMMEDIATELY HERE
                                    Log.e("onSelected", "onSelected: pathList=" + pathList);

                                }
                            })
                            .originalEnable(true)
                            .maxOriginalSize(10)
                            .autoHideToolbarOnSingleTap(true)
                            .setOnCheckedListener(new OnCheckedListener() {
                                @Override
                                public void onCheck(boolean isChecked) {
                                    // DO SOMETHING IMMEDIATELY HERE
                                    Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                                }
                            })
                            .forResult(REQUEST_CODE_CHOOSE);
                }
            }
        });
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setText("请假");
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leave;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showLeaveTypeView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String leaveName = leaveTypes.get(options1);
                tvLeaveType.setText(leaveName);
                qingJiaLX = options1;
            }
        })
                .setTitleText("类型")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(leaveTypes);
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(type);//三级选择器
        pvOptions.show();
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


    @OnClick({R.id.toolbar_left_btn, R.id.ll_open_time, R.id.ll_end_time, R.id.iv_fuzhu, R.id.iv_end_fuzhu,
            R.id.tv_leave_type, R.id.bt_leave_submit,R.id.bt_cancel, R.id.bt_approval})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.tv_leave_people:
//                break;
            case R.id.tv_leave_type:
                showLeaveTypeView();
                break;
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.ll_open_time:
                setinitLunarPicker(openTimeData, ivFuzhu);
                pvCustomLunar.show();
                break;
            case R.id.ll_end_time:
                setinitLunarPicker(endTimeData, ivEndFuzhu);
                pvCustomLunar.show();
                break;
            case R.id.iv_fuzhu:
                openTimeData.setText("");
                openTimeData.setHint("请选择");
                ivFuzhu.setImageResource(R.drawable.jiaobiao);
                break;
            case R.id.iv_end_fuzhu:
                endTimeData.setText("");
                endTimeData.setHint("请选择");
                ivEndFuzhu.setImageResource(R.drawable.jiaobiao);
                break;

            case R.id.bt_cancel:

                break;
            case R.id.bt_approval:

                break;
//            case R.id.iv_approval:
//                Intent intent = new Intent(LeaveActivity.this, SelectApprovalActivity.class);
//                startActivity(intent);
//                break;
            case R.id.bt_leave_submit:
                String startTime = openTimeData.getText().toString();
                String endTime = endTimeData.getText().toString();
                String leaveNumber = etLeaveNumber.getText().toString();
                String reason = etLeaveReason.getText().toString();
                File[] files = new File[mPaths.size()];
                for (int i = 0; i < mPaths.size(); i++) {
                    File file = new File(mPaths.get(i));
                    files[i] = file;
                }

                try {
                    new PersonnelManager().LeaveSubmit(renShiId, qingJiaId, qingJiaLX, startTime, endTime, leaveNumber, reason, files, new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            Log.e("entityrResult", entityrResult.message);
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
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mUris = Matisse.obtainResult(data);
            mPaths = Matisse.obtainPathResult(data);
            leaveShowImageAdapter.setData(mUris, mPaths);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
