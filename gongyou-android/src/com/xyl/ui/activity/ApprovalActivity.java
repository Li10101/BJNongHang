package com.xyl.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.LeaveShowImageAdapter;
import com.xyl.adapter.personnel.WorkFlowAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.ApprovalDetailBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.domain.personnel.WorkFlowBean;
import com.xyl.filepicker.FilePickerActivity;
import com.xyl.filepicker.PickerManager;
import com.xyl.filepicker.adapter.FilePickerShowAdapter;
import com.xyl.filepicker.adapter.OnFileItemClickListener;
import com.xyl.filepicker.model.FileEntity;
import com.xyl.filepicker.util.OpenFile;
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
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ApprovalActivity extends BaseActivity {

    private static int REQ_CODE = 0X01;
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
//    @BindView(R.id.iv_add_enclosure)
//    ImageView ivAddEnclosure;
//    @BindView(R.id.rv_show_enclosure)
//    RecyclerView rvShowEnclosure;
    @BindView(R.id.et_apply_content)
    EditText etApplyContent;
    @BindView(R.id.et_apply_details)
    EditText etApplyDetails;
    @BindView(R.id.rv_showImages)
    RecyclerView rvShowImages;
    @BindView(R.id.bt_apply_submit)
    Button btApplySubmit;
    @BindView(R.id.work_flow)
    RecyclerView workFlow;
    private LeaveShowImageAdapter leaveShowImageAdapter;

    private List<Uri> mUris;
    private List<String> mPaths;
    private List<File> imageFile;
    private static final int REQUEST_CODE_CHOOSE = 23;
    private List<FileEntity> entityFiles;
    private SelectAllPersonalBean.RecordsBean recordsBean;
    private String renShiId;
    private String tongYongShenPiId;

    @Override
    protected void initData() {
        recordsBean = (SelectAllPersonalBean.RecordsBean) getIntent().getSerializableExtra("recordsBean");
        if (recordsBean != null) {
            new PersonnelManager().approvalDetail(recordsBean.getRenShiId() + "", new BaseNet.BaseCallback<ApprovalDetailBean>() {
                @Override
                public void messageSuccess(ApprovalDetailBean bean) {
                    renShiId = String.valueOf(bean.getRenShiId());
                    tongYongShenPiId = String.valueOf(bean.getTongYongShenPiId());
                    etApplyContent.setText(bean.getShenQingNR());
                    etApplyDetails.setText(bean.getShenPiXQ());
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
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
        leaveShowImageAdapter = new LeaveShowImageAdapter(ApprovalActivity.this);
        rvShowImages.addItemDecoration(spaceItemDecoration);
        rvShowImages.setLayoutManager(new GridLayoutManager(ApprovalActivity.this, 4));
        rvShowImages.setAdapter(leaveShowImageAdapter);

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
                    Matisse.from(ApprovalActivity.this)
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

        new PersonnelManager().workFlow("12", new BaseNet.BaseCallback<WorkFlowBean>() {
            @Override
            public void messageSuccess(WorkFlowBean bean) {
                if (bean.getRecords().size() == 0){
                    return;
                }

                workFlow.setLayoutManager(new GridLayoutManager(ApprovalActivity.this, 4));
                WorkFlowAdapter workFlowAdapter = new WorkFlowAdapter(ApprovalActivity.this, bean.getRecords().get(0));
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
        toolbarTitleTv.setText("通用审批");
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        rvShowEnclosure.setLayoutManager(manager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_approval;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.toolbar_left_btn, R.id.bt_apply_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
//            case R.id.iv_add_enclosure:
//                Intent intent = new Intent(this, FilePickerActivity.class);
//                startActivityForResult(intent, REQ_CODE);
//                break;
            case R.id.bt_apply_submit:
                String shenQingNR = etApplyContent.getText().toString();
                String shenPiXQ = etApplyDetails.getText().toString();
                File[] files = new File[imageFile.size()];
                for (int a = 0; a < imageFile.size(); a++) {
                    files[a] = imageFile.get(a);
                }
                try {
                    new PersonnelManager().ApprovalSubmit(renShiId, tongYongShenPiId, shenQingNR, shenPiXQ, files, new BaseNet.EntityCallback() {
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
        if (requestCode == REQ_CODE) {
//            FilePickerShowAdapter adapter = new FilePickerShowAdapter(this, PickerManager.getInstance().files);
//            for (int i = 0; i < PickerManager.getInstance().files.size(); i++) {
//                File file = new File(PickerManager.getInstance().files.get(i).getPath());
//                imageFile.add(file);
//            }
//            rvShowEnclosure.setAdapter(adapter);
//            adapter.setOnItemClickListener(new OnFileItemClickListener() {
//                @Override
//                public void click(int position) {
//                    startActivity(Intent.createChooser(OpenFile.openFile(PickerManager.getInstance().files.get(position).getPath(), getApplicationContext()), "选择程序"));
//                }
//            });
        } else if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
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
