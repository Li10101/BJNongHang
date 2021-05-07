package com.xyl.ui.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;

 

import com.xyl.R;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.net.UserManager;
import com.xyl.ui.widget.TitleWidget;

public class RegisterActivity extends Activity {

	private TitleWidget title_widget;
	private EditText et_email;
	private EditText et_name;
	private EditText et_password;
	private EditText et_phone;
	private EditText et_validate;
	private RadioButton rb_male;

	private Button btn_register;
	private UserManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initWidget();
		initTitle();
		initListener();
		manager = new UserManager();
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

	/**
	 * 获取输入的信息
	 */
	private Map getDate() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mail", getDate(et_email));
		map.put("name", getDate(et_name));
		map.put("passWord", getDate(et_password));
		map.put("phone", getDate(et_phone));
		map.put("validate", getDate(et_validate));
		map.put("sex", rb_male.isChecked() ? "0" : "1");// 0男1女
		return map;
	}

	private String getDate(EditText et) {
		String data = String.valueOf(et.getText()).trim();
		return data;
	}

	private void initWidget() {
		title_widget = (TitleWidget) findViewById(R.id.title_widget);
		et_email = (EditText) findViewById(R.id.et_email);
		et_name = (EditText) findViewById(R.id.et_name);
		et_password = (EditText) findViewById(R.id.et_password);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_validate = (EditText) findViewById(R.id.et_validate);
		rb_male = (RadioButton) findViewById(R.id.tb_male);
		btn_register = (Button) findViewById(R.id.btn_register);
	}

	private void initTitle() {
		title_widget.setTitleText("注册");
		title_widget.setLeftOnclickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RegisterActivity.this.finish();
			}
		});
	}

	private void initListener() {
		btn_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Map<String, String> map = getDate();
				if (map.containsValue("")) {
					Toast.makeText(RegisterActivity.this, "数据不能为空",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (!map.get("validate").equals(map.get("passWord"))) {
					Toast.makeText(RegisterActivity.this, "两次密码必须一致",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					btn_register.setClickable(false);
					// NetContacts.getInstance().COOKIE="";
					manager.register(map.get("mail"), map.get("passWord"),
							map.get("name"), Integer.parseInt(map.get("sex")),
							map.get("phone"), new EntityCallback() {

								@Override
								public void connectCallback(
										EntityrResult entityrResult) {
									switch (entityrResult.entityType) {
									case messagefalse:// 失败

										Toast.makeText(RegisterActivity.this,
												"注册失败", Toast.LENGTH_SHORT)
												.show();
										break;
									case messagetrue:// 成功
										showDialog();
										break;
									case connectFailure:// 连接失败
										Toast.makeText(RegisterActivity.this,
												"网络异常", Toast.LENGTH_SHORT)
												.show();
										break;
									}
									btn_register.setClickable(true);
									btn_register.setClickable(true);
								}
							});
				}

			}
		});
	}

	public void showDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("注册成功");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				startActivity(new Intent(
						RegisterActivity.this,
						LoginActivity.class));
				RegisterActivity.this.finish();
			}
		});
		builder.create().show();
	}

}
