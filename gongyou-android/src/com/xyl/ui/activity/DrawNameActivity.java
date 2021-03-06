package com.xyl.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.net.OrderStream;
import com.xyl.ui.widget.DrawingBoardWidget;
import com.xyl.utils.CommonUtil;
import com.xyl.utils.PermissionUtil;

import java.io.File;
import java.util.ArrayList;

import androidx.core.content.res.ResourcesCompat;
import cn.jpush.android.api.JPushInterface;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

import static com.xyl.utils.ToastUtil.showToast;

public class DrawNameActivity extends Activity implements OnClickListener {
	
	private DrawingBoardWidget db_draw;
	private ImageView iv_title_left;
	private TextView tv_title;
	private Button bt_makeSure;
	private Button bt_clear;
	private ArrayList<PermissionItem> permissionItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw_name);
		initWidget();
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}

	private void initWidget() {
		db_draw = (DrawingBoardWidget)findViewById(R.id.db_draw);
		iv_title_left = (ImageView)this.findViewById(R.id.iv_title_left);
		tv_title = (TextView)this.findViewById(R.id.tv_title);
		bt_makeSure = (Button) this.findViewById(R.id.bt_makeSure);
		bt_clear = (Button) this.findViewById(R.id.bt_clear);
		iv_title_left.setVisibility(View.VISIBLE);
		iv_title_left.setImageResource(R.drawable.rightmenu);
		iv_title_left.setOnClickListener(this);
		bt_makeSure.setOnClickListener(this);
		bt_clear.setOnClickListener(this);
		tv_title.setText("??????");
		//db_draw.setMoney(this.getIntent().getStringExtra("money"));
		permissionItems = new ArrayList<PermissionItem>();
		permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "??????", R.drawable.permission_ic_storage));
		HiPermission.create(DrawNameActivity.this)
				.title("????????????")
				.permissions(permissionItems)
				.filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//???????????????
				.msg("??????????????????????????????????????????")
				.style(R.style.PermissionBlueStyle)
				.checkMutiPermission(new PermissionCallback() {
					@Override
					public void onClose() {
						showToast("????????????????????????");
					}

					@Override
					public void onFinish() {
					}

					@Override
					public void onDeny(String permission, int position) {
					}

					@Override
					public void onGuarantee(String permission, int position) {

					}
				});
	}


	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		setResult(10);
		finish();
		
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.iv_title_left:
				setResult(10);
				finish();
			break;
			case R.id.bt_makeSure:
				//???????????????????????????????????????
//				String[] permisions = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,};
//				PermissionUtil.checkPermission(DrawNameActivity.this,"android.permission.CAMERA");


				HiPermission.create(DrawNameActivity.this)
						.title("????????????")
						.permissions(permissionItems)
						.filterColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, getTheme()))//???????????????
						.msg("??????????????????????????????????????????")
						.style(R.style.PermissionBlueStyle)
						.checkMutiPermission(new PermissionCallback() {
							@Override
							public void onClose() {
								showToast("????????????????????????");
								Intent intent = new Intent();
								intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
								intent.setData(Uri.fromParts("package",getPackageName(), null));
								startActivity(intent);
							}

							@Override
							public void onFinish() {
								bt_makeSure.setEnabled(false);
								bt_clear.setEnabled(false);
								String code = getIntent().getStringExtra("casesCode");
								Bitmap bitmap = db_draw.getBitmap();
								File file = CommonUtil.saveBitmap2file(bitmap,
										String.valueOf(System.currentTimeMillis()).concat(".png"));
								new OrderStream().fix(new EntityCallback() {
									@Override
									public void connectCallback(EntityrResult entityrResult) {
										if(entityrResult.entityType == EntityType.messagetrue){
											Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
											setResult(6);
											finish();
										}
									}
								}, code, file,getIntent().getStringExtra("money"));
							}

							@Override
							public void onDeny(String permission, int position) {
							}

							@Override
							public void onGuarantee(String permission, int position) {

							}
						});

			break;
			case R.id.bt_clear:
				setResult(10);
				finish();
			break;


		}
	}
}
