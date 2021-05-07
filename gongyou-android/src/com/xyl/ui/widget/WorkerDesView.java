package com.xyl.ui.widget;

import android.Manifest;
import android.app.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.xyl.R;
import com.xyl.adapter.WorkDesAdapter;
import com.xyl.domain.DataBean;

import com.xyl.domain.LoginBean;
import com.xyl.domain.Pictures;
import com.xyl.ui.activity.OrderStateActivity;
import com.xyl.ui.activity.WorkerSituationActivity;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.UIUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.core.content.res.ResourcesCompat;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

import static com.xyl.utils.ToastUtil.showToast;

public class WorkerDesView extends View {

    private Context mContext;
    private View view_worker_des;
    private ListView lv_workdes;
    private RelativeLayout rl_worker_pic;
    private ImageView iv_workdes_tp;
    private ImageView iv_workdes_ap;
    private int SELECT_PICTURE = 1; // 从图库中选择图片
    private int SELECT_CAMER = 2; // 用相机拍摄照片
    private DataBean bean;
    private String mFilePath;
    private WorkDesAdapter workDesAdapter;
    private File tempFile;
    private ArrayList<PermissionItem> permissionItems;

    public WorkerDesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        initView();
        initListener();
    }

    public WorkerDesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initListener();
    }

    public WorkerDesView(Context context) {
        super(context);
        mContext = context;
        initView();
        initListener();
    }

    private void initView() {
        view_worker_des = View.inflate(mContext, R.layout.view_worker_des, null);
        lv_workdes = (ListView) view_worker_des
                .findViewById(R.id.lv_workdes);
        rl_worker_pic = (RelativeLayout) view_worker_des
                .findViewById(R.id.rl_worker_pic);
        iv_workdes_tp = (ImageView) view_worker_des
                .findViewById(R.id.iv_workdes_tp);
        iv_workdes_ap = (ImageView) view_worker_des
                .findViewById(R.id.iv_workdes_ap);
    }

    private void initListener() {
        LoginBean loginBean = (LoginBean) (((Activity) mContext).getIntent().getSerializableExtra("LoginBean"));

            iv_workdes_tp.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    permissionItems = new ArrayList<PermissionItem>();
                    permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.drawable.permission_ic_camera));
                    permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R.drawable.permission_ic_storage));
                    HiPermission.create(mContext)
                            .title("开启权限")
                            .permissions(permissionItems)
                            .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, mContext.getTheme()))//图标的颜色
                            .msg("为了软件的正常，请开启权限！")
                            .style(R.style.PermissionBlueStyle)
                            .checkMutiPermission(new PermissionCallback() {

                                @Override
                                public void onClose() {
                                    showToast("用户关闭权限申请");
                                    Intent intent = new Intent();
                                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                    intent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
                                    mContext.startActivity(intent);
                                }

                                @Override
                                public void onFinish() {
                                    showChoosePhotoDialog();
                                }

                                @Override
                                public void onDeny(String permission, int position) {

                                }

                                @Override
                                public void onGuarantee(String permission, int position) {

                                }
                            });

                }
            });

            iv_workdes_ap.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, WorkerSituationActivity.class);//调用android的图库
                    i.putExtra("dataContent", bean);
                    ((Activity) mContext).startActivityForResult(i, 2);
                }
            });


        lv_workdes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final List<Pictures> pictures = bean.getPictures();
                final int pictureId = pictures.get(i).pictureId;
                if (onListItemLongClickListener != null) {
                    onListItemLongClickListener.OnListItemLongClick(pictureId);
                }
                return false;
            }
        });
    }

    private void showChoosePhotoDialog() {
        CharSequence[] items = {"相机", "相册"};
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("选择图片来源")
                .setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentPic;
                        // TODO Auto-generated method stub
                        if (which == SELECT_PICTURE) {
//							Intent i = new Intent(
//									Intent.ACTION_PICK,
//									android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
//							startActivityForResult(i, 2);
                            if (Build.VERSION.SDK_INT < 19) {
                                intentPic = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            } else {
                                intentPic = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intentPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                            }
                            ((Activity) mContext).startActivityForResult(intentPic, 2);
                        } else {/*SELECT_PICTURE*/
							/*mFilePath = ImageBitmapUtil.getFilePath();
							SharedPreferencesUtil.setParam(mContext,"imagePath",mFilePath);
							File outFile = new File(mFilePath);
							Uri uri = Uri.fromFile(outFile);
							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);*/
                            //获取系统版本
                            int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                            // 激活相机
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            // 判断存储卡是否可以用，可用进行存储
                            if (hasSdcards()) {
                                SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                                        "yyyy_MM_dd_HH_mm_ss");
                                String filename = timeStampFormat.format(new Date());
                                tempFile = new File(Environment.getExternalStorageDirectory(),
                                        filename + ".jpg");
                                mFilePath = tempFile.getAbsolutePath();
                                SharedPreferencesUtil.setParam(mContext, "imagePath", mFilePath);
                                if (currentapiVersion < 24) {
                                    // 从文件中创建uri
                                    Uri uri = Uri.fromFile(tempFile);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                } else {
                                    //兼容android7.0 使用共享文件的形式
                                    ContentValues contentValues = new ContentValues(1);
                                    contentValues.put(MediaStore.Images.Media.DATA, mFilePath);
                                    Uri uri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                }
                            }
                            ((Activity) mContext).startActivityForResult(intent, 1);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                }).create();
                dialog.show();
    }

    public static boolean hasSdcards() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    private boolean isEmpty = false;

    public WorkerDesView setData(String type, DataBean dataBean, int flag) {
        if (dataBean != null) {
            bean = dataBean;
            workDesAdapter = new WorkDesAdapter(dataBean, mContext);
        }
        switch (flag) {
            case 1:
                rl_worker_pic.setVisibility(View.GONE);
                lv_workdes.setAdapter(workDesAdapter);
                UIUtils.setListViewHeightBasedOnChildren(lv_workdes);
                break;
            case 2:
                List<Pictures> pics = dataBean.pictures;
                if ("0".equals(type)) {
                    rl_worker_pic.setVisibility(View.GONE);
                    if (pics.size() == 0) {
                        isEmpty = true;
                        return this;
                    }
                }
                lv_workdes.setAdapter(workDesAdapter);
                UIUtils.setListViewHeightBasedOnChildren(lv_workdes);

                break;
            case 3:
                lv_workdes.setAdapter(workDesAdapter);
                UIUtils.setListViewHeightBasedOnChildren(lv_workdes);

                break;
        }
        isEmpty = false;
        return this;
    }

    public View getView() {
        if (isEmpty) {
            return null;
        }
        return view_worker_des;
    }

    public ListView getListView() {

        return lv_workdes;
    }

    public interface OnListItemLongClickListener {
        void OnListItemLongClick(int pictureId);
    }

    private OnListItemLongClickListener onListItemLongClickListener;

    public void setOnListItemLongClickListener(OnListItemLongClickListener onListItemLongClickListener) {
        this.onListItemLongClickListener = onListItemLongClickListener;
    }
}
