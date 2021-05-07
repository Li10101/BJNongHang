package com.xyl.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmergencyActivity extends BaseActivity {


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
    @BindView(R.id.rv_emergency)
    RecyclerView rvEmergency;
    List<ProcurementDetails> procurementDetails;
    @BindView(R.id.bt_submit)
    Button btSubmit;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.add_details)
    Button addDetails;
    @BindView(R.id.rv_showImags)
    RecyclerView rvShowImags;
    @BindView(R.id.work_flow)
    RecyclerView workFlow;
    private EmergencyCreateAdatper adatper;
    private ProcurementDetails details;
    private String renShiId;
    private String caiGouId;
    private String danWei;
    private LeaveShowImageAdapter leaveShowImageAdapter;
    private List<Uri> mUris;
    private List<String> mPaths;
    private List<File> imageFile;
    private SelectAllPersonalBean.RecordsBean recordsBean;

    @Override
    protected void initData() {
        recordsBean = (SelectAllPersonalBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        if (recordsBean != null) {
            new PersonnelManager().emergencyDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<EmergencyDetailBean>() {
                @Override
                public void messageSuccess(EmergencyDetailBean bean) {
                    renShiId = String.valueOf(bean.getRenShiId());
                    caiGouId = String.valueOf(bean.getCaiGouId());
                    procurementDetails = bean.getCaiGouMingXis();
                    rvEmergency.setLayoutManager(new LinearLayoutManager(EmergencyActivity.this));
                    adatper = new EmergencyCreateAdatper(EmergencyActivity.this, procurementDetails, 1);
                    rvEmergency.setAdapter(adatper);
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
            rvEmergency.setLayoutManager(new LinearLayoutManager(this));
            adatper = new EmergencyCreateAdatper(this, procurementDetails, 1);
            rvEmergency.setAdapter(adatper);
        }


        imageFile = new ArrayList<>();
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
        leaveShowImageAdapter = new LeaveShowImageAdapter(EmergencyActivity.this);
        rvShowImags.addItemDecoration(spaceItemDecoration);
        rvShowImags.setLayoutManager(new GridLayoutManager(EmergencyActivity.this, 4));
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
                    Matisse.from(EmergencyActivity.this)
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
        new PersonnelManager().workFlow("10", new BaseNet.BaseCallback<WorkFlowBean>() {
            @Override
            public void messageSuccess(WorkFlowBean bean) {

                if (bean.getRecords().size() == 0) {
                    return;
                }
                workFlow.setLayoutManager(new GridLayoutManager(EmergencyActivity.this, 4));
                WorkFlowAdapter workFlowAdapter = new WorkFlowAdapter(EmergencyActivity.this, bean.getRecords().get(0));
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
        toolbarTitleTv.setText("应急采购");
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_emergency;
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


    @OnClick({R.id.toolbar_left_btn,R.id.add_details, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.add_details:
                ProcurementDetails detai = new ProcurementDetails();
                adatper.addData(detai);
                adatper.notifyDataSetChanged();
                break;
            case R.id.bt_submit:
                List<ProcurementDetails> emergencyData = adatper.getEmergencyData();
                Gson gson = new Gson();
                String data = gson.toJson(emergencyData);
                File[] files = new File[imageFile.size()];
                for (int a = 0; a < imageFile.size(); a++) {
                    files[a] = imageFile.get(a);
                }
                try {
                    new PersonnelManager().meetpurchaseSubmit(renShiId, caiGouId, data, files, new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            finish();
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //new PersonnelManager().meetpurchaseSubmit(renShiId,baoXiaoId,);
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


}
