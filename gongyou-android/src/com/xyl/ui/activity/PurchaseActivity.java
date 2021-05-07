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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.adapter.LeaveShowImageAdapter;
import com.xyl.adapter.personnel.EmergencyCreateAdatper;
import com.xyl.adapter.personnel.WorkFlowAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.EmergencyDetailBean;
import com.xyl.domain.personnel.ProcurementDetails;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.domain.personnel.WorkFlowBean;
import com.xyl.net.PersonnelManager;
import com.xyl.ui.view.Glide4Engine;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurchaseActivity extends BaseActivity {


    private static final int REQUEST_CODE_CHOOSE = 23;
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
    @BindView(R.id.et_purchase_shiyou)
    EditText etPurchaseShiyou;
    @BindView(R.id.tv_purchase_type)
    TextView tvPurchaseType;
    @BindView(R.id.tv_purchase_datetime)
    TextView tvPurchaseDatetime;
    @BindView(R.id.rv_purchase)
    RecyclerView rvPurchase;
    @BindView(R.id.bt_purchase_add_detail)
    Button btPurchaseAddDetail;
    @BindView(R.id.tv_play_type)
    TextView tvPlayType;
    @BindView(R.id.multiAutoCompleteTextView)
    EditText multiAutoCompleteTextView;
    @BindView(R.id.bt_purchase_submit)
    Button btPurchaseSubmit;
    @BindView(R.id.iv_end_fuzhu)
    ImageView ivEndFuzhu;
    @BindView(R.id.rv_showImags)
    RecyclerView rvShowImags;
    @BindView(R.id.work_flow)
    RecyclerView workFlow;

    private List<String> caiGouLXs;
    private List<String> zhiFuFSs;
    private String renShiId;
    private String caiGouId;
    private String caiGouLX;
    private String zhiFuFS;
    private TimePickerView pvCustomLunar;

    List<ProcurementDetails> procurementDetails;
    private ProcurementDetails details;
    private EmergencyCreateAdatper adatper;

    private List<Uri> mUris;
    private List<String> mPaths;
    private List<File> imageFile;
    private LeaveShowImageAdapter leaveShowImageAdapter;
    private SelectAllPersonalBean.RecordsBean recordsBean;

    @Override
    protected void initData() {
        caiGouLXs = new ArrayList<>();
        caiGouLXs.add("办公用品");
        caiGouLXs.add("生产原料");
        caiGouLXs.add("其他");
        zhiFuFSs = new ArrayList<>();
        zhiFuFSs.add("现金");
        zhiFuFSs.add("汇款");
        zhiFuFSs.add("支票");
        zhiFuFSs.add("微信");
        zhiFuFSs.add("支付宝");
        zhiFuFSs.add("其他");
        recordsBean = (SelectAllPersonalBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        if (recordsBean != null) {
            new PersonnelManager().purchaseDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<EmergencyDetailBean>() {
                @Override
                public void messageSuccess(EmergencyDetailBean bean) {
                    caiGouLX = String.valueOf(bean.getCaiGouLX());
                    renShiId = String.valueOf(bean.getRenShiId());
                    caiGouId = String.valueOf(bean.getCaiGouId());
                    etPurchaseShiyou.setText(bean.getShiYou());
                    tvPurchaseType.setText(bean.getCaiGouLXDisplay());
                    tvPurchaseDatetime.setText(bean.getQiWangJFRQ());

                    procurementDetails = bean.getCaiGouMingXis();
                    rvPurchase.setLayoutManager(new LinearLayoutManager(PurchaseActivity.this));
                    adatper = new EmergencyCreateAdatper(PurchaseActivity.this, procurementDetails, 2);
                    rvPurchase.setAdapter(adatper);
                }

                @Override
                public void messageFailure(MessageBean backError) {

                }

                @Override
                public void connectFailure(MessageBean messageBean) {

                }
            });
        } else {
            procurementDetails = new ArrayList<>();
            details = new ProcurementDetails();
            procurementDetails.add(details);
            rvPurchase.setLayoutManager(new LinearLayoutManager(this));
            adatper = new EmergencyCreateAdatper(this, procurementDetails, 2);
            rvPurchase.setAdapter(adatper);
        }

        new PersonnelManager().workFlow("9", new BaseNet.BaseCallback<WorkFlowBean>() {
            @Override
            public void messageSuccess(WorkFlowBean bean) {
                if (bean.getRecords().size() == 0){
                    return;
                }
                workFlow.setLayoutManager(new GridLayoutManager(PurchaseActivity.this, 4));
                WorkFlowAdapter workFlowAdapter = new WorkFlowAdapter(PurchaseActivity.this, bean.getRecords().get(0));
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
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
        leaveShowImageAdapter = new LeaveShowImageAdapter(PurchaseActivity.this);
        rvShowImags.addItemDecoration(spaceItemDecoration);
        rvShowImags.setLayoutManager(new GridLayoutManager(PurchaseActivity.this, 4));
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
                    Matisse.from(PurchaseActivity.this)
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
        toolbarTitleTv.setText("采购");
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_purchase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.toolbar_left_btn, R.id.tv_purchase_type, R.id.iv_end_fuzhu, R.id.tv_purchase_datetime, R.id.bt_purchase_add_detail, R.id.tv_play_type, R.id.bt_purchase_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.tv_purchase_type:
                showCaigouLXView();
                break;
            case R.id.tv_purchase_datetime:
                setinitLunarPicker(tvPurchaseDatetime, ivEndFuzhu);
                pvCustomLunar.show();
                break;
            case R.id.bt_purchase_add_detail:
                ProcurementDetails detai = new ProcurementDetails();
                adatper.addData(detai);
                adatper.notifyDataSetChanged();
                break;
            case R.id.tv_play_type:
                showZhiFuFSView();
                break;
            case R.id.bt_purchase_submit:
                String shiYou = etPurchaseShiyou.getText().toString();
                String qiWangJFRQ = tvPurchaseDatetime.getText().toString();
                String description = multiAutoCompleteTextView.getText().toString();
                List<ProcurementDetails> emergencyData = adatper.getEmergencyData();
                Log.e("emergencyData", emergencyData.toString());
                Gson gson = new Gson();
                String data = gson.toJson(emergencyData);
                File[] files = new File[imageFile.size()];
                for (int a = 0; a < imageFile.size(); a++) {
                    files[a] = imageFile.get(a);
                }

                try {
                    new PersonnelManager().purchaseSubmit(renShiId, caiGouId, shiYou, caiGouLX, qiWangJFRQ, zhiFuFS, description, data, files, new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            finish();
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_end_fuzhu:
                break;
        }
    }


    private void showCaigouLXView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String leaveName = caiGouLXs.get(options1);
                tvPurchaseType.setText(leaveName);
                if (leaveName.equals("其它")) {
                    caiGouLX = String.valueOf(-1);
                } else {
                    caiGouLX = String.valueOf(options1);
                }
            }
        })
                .setTitleText("采购类型")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(caiGouLXs);
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(type);//三级选择器
        pvOptions.show();
    }

    private void showZhiFuFSView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String leaveName = zhiFuFSs.get(options1);
                tvPlayType.setText(leaveName);
                if (leaveName.equals("其它")) {
                    zhiFuFS = String.valueOf(-1);
                } else {
                    zhiFuFS = String.valueOf(options1);
                }
            }
        })
                .setTitleText("支付方式")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(zhiFuFSs);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mUris = Matisse.obtainResult(data);
            mPaths = Matisse.obtainPathResult(data);
            leaveShowImageAdapter.setData(mUris, mPaths);
            for (int i = 0; i < mUris.size(); i++) {
                File file = new File(mPaths.get(i));
                imageFile.add(file);
            }
        }
    }
}
