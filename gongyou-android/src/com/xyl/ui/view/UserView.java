package com.xyl.ui.view;

import java.io.File;

import org.xutils.x;
import org.xutils.image.ImageOptions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;

import com.xyl.R;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.domain.LoginBean;
import com.xyl.global.NetContacts;
import com.xyl.net.LoginNet;
import com.xyl.ui.activity.HomeActivity;
import com.xyl.ui.activity.LoginActivity;
import com.xyl.ui.activity.SettingInfoActivity;
import com.xyl.ui.widget.UserGroupView;
import com.xyl.utils.ImageTools;
import com.xyl.utils.ToastUtil;

public class UserView extends InstrumentedActivity {

    private View userView;
    private Context mContext;
    private UserGroupView ugv_user_call;
    private UserGroupView ugv_user_address;
    private UserGroupView ugv_user_function;
    private UserGroupView ugv_user_logout;
    private UserGroupView ugv_user_setpassword;
    private Intent intent;
    private TextView tv_user_name;
    private TextView tv_user_number;
    private TextView tv_user_type;
    private LinearLayout ll_user_info;
    private LoginBean loginBean;
    private static final int TAKE_PICTURE = 0;
    private static final int CHOOSE_PICTURE = 1;
    private static final int CROP = 3;
    private static final int CROP_PICTURE = 4;

    private static final int SCALE = 5;//照片缩小比例
    private ImageView iv_user_photo;
    private TextView tv_version;
    private UserGroupView ugv_user_baseurl;

    public UserView(Context context, AttributeSet attrs, int defStyle) {
        super();
        mContext = context;
        initView();
        initListener();
        initData();
    }

    public UserView(Context context, AttributeSet attrs) {
        super();
        mContext = context;
        initView();
        initListener();
        initData();
    }

    public UserView(Context context) {
        super();
        mContext = context;

        initView();
        initListener();
        initData();
    }

    private void initView() {
        userView = View.inflate(mContext, R.layout.view_user, null);
        ugv_user_call = (UserGroupView) userView.findViewById(R.id.ugv_user_call);
        ugv_user_address = (UserGroupView) userView.findViewById(R.id.ugv_user_address);
        ugv_user_function = (UserGroupView) userView.findViewById(R.id.ugv_user_function);
        ugv_user_logout = (UserGroupView) userView.findViewById(R.id.ugv_user_logout);
        ugv_user_baseurl = (UserGroupView) userView.findViewById(R.id.ugv_user_baseurl);
        ugv_user_setpassword = (UserGroupView) userView.findViewById(R.id.ugv_user_setpassword);
        tv_user_name = (TextView) userView.findViewById(R.id.tv_user_name);
        tv_user_number = (TextView) userView.findViewById(R.id.tv_user_number);
        tv_user_type = (TextView) userView.findViewById(R.id.tv_user_type);
        ll_user_info = (LinearLayout) userView.findViewById(R.id.ll_user_info);
        iv_user_photo = (ImageView) userView.findViewById(R.id.iv_user_photo);
        tv_version = (TextView) userView.findViewById(R.id.tv_version);
    }
    public void setUserInfo(String name, String number, String type) {
        tv_user_name.setText(name);
        tv_user_number.setText(number);
        tv_user_type.setText(type);
    }

    private void initListener() {
        ugv_user_call.setMyGroupClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startInfoActivty(ugv_user_call.getInfoTitle(), ugv_user_call.getInfoType(), ugv_user_call.getUserInfo());
            }
        });

        ugv_user_address.setMyGroupClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startInfoActivty(ugv_user_address.getInfoTitle(), ugv_user_address.getInfoType(), ugv_user_address.getUserInfo());
            }
        });

        ugv_user_function.setMyGroupClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startInfoActivty(ugv_user_function.getInfoTitle(), ugv_user_function.getInfoType(), ugv_user_function.getUserInfo());
            }
        });

        ugv_user_logout.setMyGroupClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JPushInterface.stopPush(BaseApplication.getBaseApplication());
                ugv_user_logout.setEnabled(false);

                    new LoginNet().userLogout(new EntityCallback() {
                        @Override
                        public void connectCallback(EntityrResult entityrResult) {
                            mContext.startActivity(new Intent(mContext, LoginActivity.class));
                            ((Activity) mContext).finish();
                            ToastUtil.showToast("已退出登录");
                            SlidingLeftView.slidingList.clear();
                        }
                    });


            }
        });

        ugv_user_setpassword.setMyGroupClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                loginBean = (LoginBean) intent.getSerializableExtra("LoginBean");
                startInfoActivty(ugv_user_setpassword.getInfoTitle(), ugv_user_setpassword.getInfoType(), "");

            }
        });

        ugv_user_baseurl.setMyGroupClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SettingInfoActivity.class);
                intent.putExtra("title", "设置域名");
                intent.putExtra("type", "请设置域名：");
                intent.putExtra("info", "");
                mContext.startActivity(intent);
            }
        });

        ll_user_info.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                showPicturePicker(mContext, false);
            }
        });
    }

    private void initData() {
        intent = ((Activity) mContext).getIntent();
        loginBean = (LoginBean) intent.getSerializableExtra("LoginBean");
        if (TextUtils.isEmpty(loginBean.phone)){
            loginBean.phone="无";
        }
        if (TextUtils.isEmpty(loginBean.address)){
            loginBean.address="无";
        }
        if (TextUtils.isEmpty(loginBean.description)){
            loginBean.description="无";
        }
        setUserInfo("姓名：" + loginBean.name, "工号：" + loginBean.staffCode, "级别：" + loginBean.typeDisplay);
        ugv_user_call.setUserInfo("联系方式 :" + loginBean.phone, R.drawable.iv_user_call);
        ugv_user_address.setUserInfo("当前位置 :" + loginBean.address, R.drawable.iv_user_address);
        ugv_user_function.setUserInfo("业务介绍 :" + loginBean.description, R.drawable.iv_user_function);
        ugv_user_baseurl.setUserInfo("设置域名：" + NetContacts.getInstance().getBaseUrl(), R.drawable.iv_user_call);
        ugv_user_setpassword.setUserInfo("修改密码", R.drawable.state_finish);
        ugv_user_logout.setUserInfo("退出登录", R.drawable.iv_user_logout);
        try {
            tv_version.setText("版本号：V" + mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        if ("0".equals(loginBean.type)) {
            ugv_user_function.setVisibility(View.GONE);
        }

        String photoUrl = NetContacts.getInstance().BASE_IMAGE_URL + "/" + loginBean.photo;
        x.image().bind(iv_user_photo, photoUrl, new ImageOptions.Builder()
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true)
                // 加载中或错误图片的ScaleType
//	      .setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                //设置支持gif
                .setIgnoreGif(true)
                //设置加载过程中的图片
                .setLoadingDrawableId(R.drawable.touxiang)
                //设置加载失败后的图片
                .setFailureDrawableId(R.drawable.touxiang)
                //设置使用缓存
                .setUseMemCache(true)
                .build());
    }

    public void resetUserInfo(String info, int flag) {
        switch (flag) {
            case 1:
                ugv_user_call.setUserInfo("联系方式 :" + info, R.drawable.iv_user_call);
                break;
            case 2:
                ugv_user_address.setUserInfo("当前位置 :" + info, R.drawable.iv_user_address);
                break;
            case 3:
                ugv_user_function.setUserInfo("业务介绍 :" + info, R.drawable.iv_user_function);
                break;
        }
    }

    public void showPicturePicker(Context context, boolean isCrop) {
        final boolean crop = isCrop;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("图片来源");
        builder.setNegativeButton("取消", null);
        builder.setItems(new String[]{"拍照", "相册"}, new DialogInterface.OnClickListener() {
            //类型码
            int REQUEST_CODE;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case TAKE_PICTURE:
                        Uri imageUri = null;
                        String fileName = null;
                        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (crop) {
                            REQUEST_CODE = CROP;
                            //删除上一次截图的临时文件
                            SharedPreferences sharedPreferences = mContext.getSharedPreferences("temp", Context.MODE_WORLD_WRITEABLE);
                            ImageTools.deletePhotoAtPathAndName(Environment.getExternalStorageDirectory().getAbsolutePath(), sharedPreferences.getString("tempName", ""));

                            //保存本次截图临时文件名字
                            fileName = String.valueOf(System.currentTimeMillis()) + ".jpg";
                            Editor editor = sharedPreferences.edit();
                            editor.putString("tempName", fileName);
                            editor.commit();
                        } else {
                            REQUEST_CODE = TAKE_PICTURE;
                            fileName = "image.jpg";
                        }
                        imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), fileName));
                        //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        ((Activity) mContext).startActivityForResult(openCameraIntent, REQUEST_CODE);
                        break;

                    case CHOOSE_PICTURE:
                        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                        if (crop) {
                            REQUEST_CODE = CROP;
                        } else {
                            REQUEST_CODE = CHOOSE_PICTURE;
                        }
                        openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        ((Activity) mContext).startActivityForResult(openAlbumIntent, REQUEST_CODE);
                        break;

                    default:
                        break;
                }
            }
        });
        builder.create().show();
    }

    public View getView() {
        return userView;
    }

    public ImageView getTXView() {
        return iv_user_photo;
    }

    public void startInfoActivty(String title, String type, String info) {
        Intent intent = new Intent(mContext, SettingInfoActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.putExtra("info", info);
        ((HomeActivity) mContext).startActivityForResult(intent, 0);
    }
}
