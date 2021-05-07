package com.xyl.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.xyl.R;
import com.xyl.adapter.LeaveShowImageAdapter;
import com.xyl.adapter.personnel.WorkFlowAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.SealDetailBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.domain.personnel.WorkFlowBean;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SealActivity extends BaseActivity {


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
    @BindView(R.id.tv_datTime)
    TextView tvDatTime;
    @BindView(R.id.seal_name)
    TextView sealName;
    @BindView(R.id.et_seal_file_name)
    EditText etSealFileName;
    @BindView(R.id.et_file_number)
    EditText etFileNumber;
    @BindView(R.id.tv_seal_type)
    TextView tvSealType;
    @BindView(R.id.et_remarks)
    EditText etRemarks;
    @BindView(R.id.rv_showImags)
    RecyclerView rvShowImags;
    @BindView(R.id.iv_datTime_delete)
    ImageView ivDatTimeDelete;
    @BindView(R.id.iv_seal_name_delete)
    ImageView ivSealNameDelete;
    @BindView(R.id.iv_seal_type_delete)
    ImageView ivSealTypeDelete;
    @BindView(R.id.mll_dataTime)
    MyLinearLayout mllDataTime;
    @BindView(R.id.mll_seal_name)
    MyLinearLayout mllSealName;
    @BindView(R.id.mll_file_type)
    MyLinearLayout mllFileType;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.et_userSeal_caompany)
    EditText etUserSealCaompany;
    @BindView(R.id.bt_sealSubmit)
    Button btSealSubmit;
    @BindView(R.id.work_flow)
    RecyclerView workFlow;

    private TimePickerView pvCustomLunar;
    private LeaveShowImageAdapter leaveShowImageAdapter;
    private List<Uri> mUris;
    private List<String> mPaths;
    private List<File> imageFile;
    private static final int REQUEST_CODE_CHOOSE = 23;

    private List<String> lsSealName;
    private List<String> lsFiletype;
    private int wenjianLB;
    private SelectAllPersonalBean.RecordsBean recordsBean;
    private String renShiId;
    private String yongYinShenQingId;

    @Override
    protected void initData() {
        recordsBean = (SelectAllPersonalBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        if (recordsBean != null) {
            new PersonnelManager().sealDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<SealDetailBean>() {
                @Override
                public void messageSuccess(SealDetailBean bean) {
                    renShiId = String.valueOf(bean.getRenShiId());
                    yongYinShenQingId = String.valueOf(bean.getYongYinShenQingId());
                    etUserSealCaompany.setText(bean.getYongYinDW());
                    tvDatTime.setText(bean.getDateTime());
                    sealName.setText(bean.getYinZhangMC());
                    etSealFileName.setText(bean.getYongYinWJMC());
                    etFileNumber.setText(bean.getWenJianFS() + "");
                    tvSealType.setText(bean.getWenjianLBDisplay());
                    etRemarks.setText(bean.getDescription());
                }

                @Override
                public void messageFailure(MessageBean backError) {

                }

                @Override
                public void connectFailure(MessageBean messageBean) {

                }
            });
        }

        imageFile = new ArrayList<>();
        lsSealName = new ArrayList<>();
        lsFiletype = new ArrayList<>();
        lsSealName.add("北京兴银龙企业管理中心(公章)");
        lsSealName.add("物业管理中心章");
        lsSealName.add("法人章");
        lsSealName.add("其它");

        lsFiletype.add("公告类");
        lsFiletype.add("规章制度类");
        lsFiletype.add("合同类");
        lsFiletype.add("其它");
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
        leaveShowImageAdapter = new LeaveShowImageAdapter(SealActivity.this);
        rvShowImags.addItemDecoration(spaceItemDecoration);
        rvShowImags.setLayoutManager(new GridLayoutManager(SealActivity.this, 4));
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
                    Matisse.from(SealActivity.this)
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
        new PersonnelManager().workFlow("14", new BaseNet.BaseCallback<WorkFlowBean>() {
            @Override
            public void messageSuccess(WorkFlowBean bean) {
                if (bean.getRecords().size() == 0){
                    return;
                }
                workFlow.setLayoutManager(new GridLayoutManager(SealActivity.this, 4));
                WorkFlowAdapter workFlowAdapter = new WorkFlowAdapter(SealActivity.this, bean.getRecords().get(0));
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
        toolbarTitleTv.setText("用印申请");
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_seal;
    }


    @OnClick({R.id.toolbar_left_btn,R.id.tv_datTime, R.id.seal_name, R.id.tv_seal_type, R.id.iv_datTime_delete, R.id.iv_seal_name_delete, R.id.iv_seal_type_delete, R.id.bt_sealSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.tv_datTime:
                setinitLunarPicker(tvDatTime, ivDatTimeDelete);
                pvCustomLunar.show();
                break;
            case R.id.seal_name:
                showSealNameView();
                break;
            case R.id.tv_seal_type:
                showFieTypeView();
                break;
            case R.id.iv_datTime_delete:
                tvDatTime.setText("");
                tvDatTime.setHint("请选择");
                ivDatTimeDelete.setImageResource(R.drawable.jiaobiao);
                break;
            case R.id.iv_seal_name_delete:
                break;
            case R.id.iv_seal_type_delete:
                break;
            case R.id.bt_sealSubmit:
                String yongYinDW = etUserSealCaompany.getText().toString();
                String datTime = tvDatTime.getText().toString();
                String yinZhangMC = sealName.getText().toString();
                String yongYinWJMC = etSealFileName.getText().toString();
                String wenJianFS = etFileNumber.getText().toString();
                String description = etRemarks.getText().toString();
                File[] files = new File[imageFile.size()];
                for (int a = 0; a < imageFile.size(); a++) {
                    files[a] = imageFile.get(a);
                }
                try {
                    new PersonnelManager().SealSubmit(renShiId, yongYinShenQingId, yongYinDW, datTime, yinZhangMC, yongYinWJMC, wenJianFS, wenjianLB + "", description, files, new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            Log.e("", entityrResult.message);
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
            for (int i = 0; i < mUris.size(); i++) {
                File file = new File(mPaths.get(i));
                imageFile.add(file);
            }
        }
    }

    private void showSealNameView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String seal_Canmpany = lsSealName.get(options1);
                sealName.setText(seal_Canmpany);

            }
        })
                .setTitleText("印章名称")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(lsSealName);
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(type);//三级选择器
        pvOptions.show();
    }

    private void showFieTypeView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String seal_Filetype = lsFiletype.get(options1);
                tvSealType.setText(seal_Filetype);
                if (seal_Filetype.equals("其它")) {
                    wenjianLB = -1;
                } else {
                    wenjianLB = options1;
                }


            }
        })
                .setTitleText("文件类别")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(lsFiletype);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
