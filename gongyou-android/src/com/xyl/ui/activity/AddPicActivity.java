package com.xyl.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xyl.R;
import com.xyl.adapter.LeaveShowImageAdapter;
import com.xyl.base.BaseNet;
import com.xyl.domain.OrderBean;
import com.xyl.domain.PhotoModel;
import com.xyl.domain.UploadGoodsBean;
import com.xyl.net.OrderManager;
import com.xyl.ui.view.Glide4Engine;
import com.xyl.ui.view.SpaceItemDecoration;
import com.xyl.utils.CommonUtil;
import com.xyl.utils.LogUtil;
import com.xyl.utils.ToastUtil;
import com.xyl.utils.UIUtils;
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

/**
 * @author fengyongge
 * @Description
 */
public class AddPicActivity extends Activity {

    private Bitmap bitmap;
    private List<File> fileList = new ArrayList<>();
    private EditText et_add_des;
    /* private ImageView ivTitleLeft;
     private TextView tvTitle;
     private TextView tvTitleRight;
     private ImageView ivEdit;*/
    private View viewShadow;
    private File[] files;
    private RelativeLayout toolbarContentRlyt;
    private Button toolbarLeftBtn;
    private TextView toolbarLeftTv;
    private TextView toolbarTitleTv;
    private Button toolbarRightBtn;
    private TextView toolbarRightTv;
    private OrderBean orderBean;
    private RecyclerView rvShowImags;


    private static final int REQUEST_CODE_CHOOSE = 23;
    private LeaveShowImageAdapter leaveShowImageAdapter;

    private List<Uri> mUris;
    private List<String> mPaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pic);
        toolbarContentRlyt = findViewById(R.id.toolbar_content_rlyt);
        toolbarLeftBtn = findViewById(R.id.toolbar_left_btn);
        toolbarLeftTv = findViewById(R.id.toolbar_left_tv);
        toolbarTitleTv = findViewById(R.id.toolbar_title_tv);
        toolbarRightBtn = findViewById(R.id.toolbar_right_btn);
        rvShowImags = findViewById(R.id.rv_addImage);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        /*toolbarRightBtn.setVisibility(View.VISIBLE);
        toolbarRightBtn.setText("提交");*/
        toolbarRightTv = findViewById(R.id.toolbar_right_tv);
        toolbarRightTv.setVisibility(View.VISIBLE);
        toolbarRightTv.setText("提交");
        toolbarRightTv.setTextColor(getResources().getColor(R.color.bg_color));
        orderBean = (OrderBean) getIntent().getSerializableExtra("OrderBean");

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        et_add_des = (EditText) findViewById(R.id.et_add_des);

        mUris = new ArrayList<>();
        mPaths = new ArrayList<>();
        initData();

        initListener();
    }

    private void initData() {
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(10);
         leaveShowImageAdapter = new LeaveShowImageAdapter(AddPicActivity.this);
        rvShowImags.addItemDecoration(spaceItemDecoration);
        rvShowImags.setLayoutManager(new GridLayoutManager(AddPicActivity.this, 4));
        rvShowImags.setAdapter(leaveShowImageAdapter);
        leaveShowImageAdapter.setOnDeleteItemClickListener(new LeaveShowImageAdapter.onDeleteItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                ToastUtil.showToast("" + position);
                mUris.remove(position);
                fileList.remove(position);
                mPaths.remove(position);
                leaveShowImageAdapter.notifyDataSetChanged();
            }
        });
        leaveShowImageAdapter.setOnItemClickListener(new LeaveShowImageAdapter.onItemClickListener() {

            @Override
            public void onItemClick(int position,int size) {
                if (position == size){
                    Matisse.from(AddPicActivity.this)
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

    private void initListener() {
        toolbarLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1);
                finish();
            }
        });
        toolbarRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbarRightTv.setEnabled(false);
                String edit = et_add_des.getText().toString();
                if (TextUtils.isEmpty(edit)) {
                    ToastUtil.showToast("请输入描述信息");
                    toolbarRightTv.setEnabled(true);
                    return;
                }
                if (fileList.size() <= 0) {
                    ToastUtil.showToast("请选择图片");
                    toolbarRightTv.setEnabled(true);
                    return;
                }
                File[] files = new File[fileList.size()];
		/*if(files.length==0){
			Toast.makeText(getApplicationContext(), "请提供图片描述", Toast.LENGTH_SHORT).show();
			return ;
		}*/
                for (int a = 0; a < fileList.size(); a++) {
                    files[a] = fileList.get(a);
                }
                try {
                    new OrderManager().uploadArrivePicture(orderBean.getGoodsCaseId() + "", et_add_des.getText().toString(), files, new BaseNet.EntityCallback() {

                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                                Intent intent = new Intent();
                                setResult(0, intent);
                                ToastUtil.showToast("提交成功");
                                finish();
                            } else {
                                ToastUtil.showToast("提交失败");
                            }

                            toolbarRightTv.setEnabled(true);
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            setResult(1);
            finish();
        }
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         List<Uri> uris;
         List<String> paths;
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            uris = Matisse.obtainResult(data);
            mUris.addAll(uris);
            paths = Matisse.obtainPathResult(data);
            mPaths.addAll(paths);
            leaveShowImageAdapter.setData(mUris, mPaths);

            for (int position = 0; position < mPaths.size(); position++) {
                Uri uri = mUris.get(position);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(mPaths.get(position), options);
                options.inJustDecodeBounds = false;
                options.inSampleSize = 1;
                bitmap = BitmapFactory.decodeFile(mPaths.get(position), options);
                File file = CommonUtil.saveBitmap2file(bitmap,
                        String.valueOf(System.currentTimeMillis()).concat(".png"));
                fileList.add(file);
            }

            Log.e("OnActivityResult ", String.valueOf(Matisse.obtainOriginalState(data)));
        }
    }
}