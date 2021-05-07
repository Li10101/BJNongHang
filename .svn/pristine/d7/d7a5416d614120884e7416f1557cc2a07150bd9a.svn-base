package com.xyl.ui.activity;

import java.util.ArrayList;
import java.util.List;

import com.xyl.R;
import com.xyl.adapter.ExpandableListAdapter;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.domain.CasesBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.TeamListBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.net.MixNet;
import com.xyl.net.NewNet;
import com.xyl.net.OrderManager;
import com.xyl.net.OrderStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
public class FindworkListViewActivity extends Activity {
	private LoginBean loginBean;
	private ExpandableListView expandableListView;
	private List<TeamListBean> fatherList;
	private List<TeamListBean> childList;
	private ImageView iv_title_left;
	private WorkInfoBean workInfoBean;
	private TextView tv_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.expandlistview_layout);
		expandableListView = (ExpandableListView) findViewById(R.id.ExpandableListView);
		iv_title_left = (ImageView) findViewById(R.id.iv_title_left);
		tv_title = (TextView) this.findViewById(R.id.tv_title);
		workInfoBean = (WorkInfoBean) this.getIntent().getSerializableExtra(
				"WorkInfoBean");
		initData();
		// 给expandableListView设置监听
		setListener();

	}
	// 设置监听接口
	private void setListener() {
		// TODO Auto-generated method stub
		expandableListView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String action = getIntent().getStringExtra("action");
				if("allot".equals(action)){
					showAllotDialog(position);
			    }else if("forward".equals(action)){
				    showForwardDialog(position);
			    }else if("addWorker".equals(action)){
					showAddWorker(position);
				}
				return false;
			}
		});
		
		/*expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return false;

			}
			
			
		});*/

		// 给一级菜单下面的二级菜单的选项设置监听接口
		expandableListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				String action = getIntent().getStringExtra("action");
				if("allot".equals(action)){
					showAllotDialog(groupPosition,childPosition);
			    }else if("forward".equals(action)){
				    showForwardDialog(groupPosition,childPosition);
			    }else if("addWorker".equals(action)){
					showAddWorker(groupPosition,childPosition);
				}
				return true;
			}
		});
		
		iv_title_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(10);
				finish();
			}
		});

	}

	// 初始化数据的填充
	private void initData() {
		tv_title.setText(workInfoBean.des);
		iv_title_left.setImageResource(R.drawable.rightmenu);
		iv_title_left.setVisibility(View.VISIBLE);
		fatherList = new ArrayList<TeamListBean>();
		childList = new ArrayList<TeamListBean>();
		AddData();
		
		
	}
	private void AddData() {
		new MixNet().teamList(new BaseCallback<List<TeamListBean>>() {
		@Override
		public void messageSuccess(List<TeamListBean> bean) {
				fatherList = bean;
				childList = bean;
				expandableListView.setAdapter(new ExpandableListAdapter(FindworkListViewActivity.this, fatherList,childList));
		}
		
		@Override
		public void messageFailure(MessageBean backError) {
		}
		@Override
		public void connectFailure(MessageBean messageBean) {
		}
		
	});
	}
	
	String code = "";
	public void showAllotDialog(final int position){
		final int flag = getIntent().getIntExtra("flag", 0);
		loginBean = (LoginBean)this.getIntent().getSerializableExtra("LoginBean");
		String teamLeaderName = fatherList.get(position).teamLeaderName;
		AlertDialog.Builder builder = new AlertDialog.Builder(FindworkListViewActivity.this);
		builder.setTitle("派发给:"+teamLeaderName);
		final View view = View.inflate(this, R.layout.dialog_allot_options, null);
		final RadioGroup rg_priority = (RadioGroup)view.findViewById(R.id.rg_priority);
		final RadioGroup rg_caseMoneyType = (RadioGroup)view.findViewById(R.id.rg_caseMoneyType);
		final RadioGroup rg_caseArea = (RadioGroup)view.findViewById(R.id.rg_caseArea);
		final RadioGroup rg_caseProfession = (RadioGroup)view.findViewById(R.id.rg_caseProfession);
		final EditText et_allot_content = (EditText)view.findViewById(R.id.et_allot_content);
		final EditText et_allot_money = (EditText)view.findViewById(R.id.et_allot_money);
		if(flag ==2){
			view.setVisibility(View.GONE);
		}
		////caseMoneyType, caseArea, contentAndNote, fixMoney, caseProfession
		builder.setView(view);
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(final DialogInterface arg0, int arg1) {
				
				switch (flag) {
				case 1:
					code = getIntent().getStringExtra("serviceBean");
					String priority = ((RadioButton)view.findViewById(rg_priority.getCheckedRadioButtonId())).getHint().toString();
					String caseMoneyType = ((RadioButton)view.findViewById(rg_caseMoneyType.getCheckedRadioButtonId())).getHint().toString();
					String caseArea = ((RadioButton)view.findViewById(rg_caseArea.getCheckedRadioButtonId())).getHint().toString();
					String caseProfession = ((RadioButton)view.findViewById(rg_caseProfession.getCheckedRadioButtonId())).getHint().toString();
					String contentAndNote = et_allot_content.getText().toString();
					String fixMoney = et_allot_money.getText().toString();
					
					new NewNet().createCustomerAndAssignStaff(new BaseCallback<MessageBean>() {
						
						@Override
						public void messageSuccess(MessageBean bean) {
							Toast.makeText(getApplicationContext(), "工单派发成功\n编号为:CUS_0000"+code, Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(getApplicationContext(),
									HomeActivity.class);
							intent.putExtra("LoginBean", loginBean);
							arg0.dismiss();
							startActivityForResult(intent,100);
							setResult(4);
							finish();
						}
						
						@Override
						public void messageFailure(MessageBean backError) {
						}
						@Override
						public void connectFailure(MessageBean messageBean) {
						}
					}, code, fatherList.get(position).teamLeaderCode, priority,caseMoneyType,caseArea,caseProfession,contentAndNote,fixMoney);
					break;
				case 2:
					view.setVisibility(View.GONE);
					code = getIntent().getStringExtra("casesBean");
					new OrderStream().assign(new EntityCallback() {
						
						@Override
						public void connectCallback(EntityrResult entityrResult) {
							if(entityrResult.entityType==EntityType.messagetrue){
								Toast.makeText(getApplicationContext(), "工单派发成功\n编号为:"+code, Toast.LENGTH_SHORT).show();
								arg0.dismiss();
//								startActivityForResult(new Intent(WorkOrderActivity.this, HomeActivity.class),100);
								setResult(4);
								finish();
							}
						}
					}, code, fatherList.get(position).teamLeaderCode);
					break;
				}
				
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		
		builder.create().show();
	}
	public void showAllotDialog(final int position,final int childposition){
		final int flag = getIntent().getIntExtra("flag", 0);
		loginBean = (LoginBean)this.getIntent().getSerializableExtra("LoginBean");
		String name = fatherList.get(position).staffs.get(childposition).name;
		AlertDialog.Builder builder = new AlertDialog.Builder(FindworkListViewActivity.this);
		builder.setTitle("派发员工:"+name);
		final View view = View.inflate(this, R.layout.dialog_allot_options, null);
		final RadioGroup rg_priority = (RadioGroup)view.findViewById(R.id.rg_priority);
		final RadioGroup rg_caseMoneyType = (RadioGroup)view.findViewById(R.id.rg_caseMoneyType);
		final RadioGroup rg_caseArea = (RadioGroup)view.findViewById(R.id.rg_caseArea);
		final RadioGroup rg_caseProfession = (RadioGroup)view.findViewById(R.id.rg_caseProfession);
		final EditText et_allot_content = (EditText)view.findViewById(R.id.et_allot_content);
		final EditText et_allot_money = (EditText)view.findViewById(R.id.et_allot_money);
        if(flag ==2){
			view.setVisibility(View.GONE);
		}
		////caseMoneyType, caseArea, contentAndNote, fixMoney, caseProfession
		builder.setView(view);
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(final DialogInterface arg0, int arg1) {
				
				
				switch (flag) {
				case 1:
					code = getIntent().getStringExtra("serviceBean");
					String priority = ((RadioButton)view.findViewById(rg_priority.getCheckedRadioButtonId())).getHint().toString();
					String caseMoneyType = ((RadioButton)view.findViewById(rg_caseMoneyType.getCheckedRadioButtonId())).getHint().toString();
					String caseArea = ((RadioButton)view.findViewById(rg_caseArea.getCheckedRadioButtonId())).getHint().toString();
					String caseProfession = ((RadioButton)view.findViewById(rg_caseProfession.getCheckedRadioButtonId())).getHint().toString();
					String contentAndNote = et_allot_content.getText().toString();
					String fixMoney = et_allot_money.getText().toString();
					
					new NewNet().createCustomerAndAssignStaff(new BaseCallback<MessageBean>() {
						
						@Override
						public void messageSuccess(MessageBean bean) {
							Toast.makeText(getApplicationContext(), "工单派发成功\n编号为:CUS_0000"+code, Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(getApplicationContext(),
									HomeActivity.class);
							intent.putExtra("LoginBean", loginBean);
							arg0.dismiss();
							startActivityForResult(intent,100);
							setResult(4);
							finish();
						}
						
						@Override
						public void messageFailure(MessageBean backError) {
						}
						@Override
						public void connectFailure(MessageBean messageBean) {
						}
					}, code, fatherList.get(position).staffs.get(childposition).staffCode, priority,caseMoneyType,caseArea,caseProfession,contentAndNote,fixMoney);
					break;
				case 2:
					code = getIntent().getStringExtra("casesBean");
					new OrderStream().assign(new EntityCallback() {
						@Override
						public void connectCallback(EntityrResult entityrResult) {
							if(entityrResult.entityType==EntityType.messagetrue){
								Toast.makeText(getApplicationContext(), "工单派发成功\n编号为:"+code, Toast.LENGTH_SHORT).show();
								arg0.dismiss();
//								startActivityForResult(new Intent(WorkOrderActivity.this, HomeActivity.class),100);
								setResult(4);
								finish();
							}
						}
					}, code, fatherList.get(position).staffs.get(childposition).staffCode);
					break;
				}
				
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		
		builder.create().show();
	}
	public void showForwardDialog(final int position){
		loginBean = (LoginBean)this.getIntent().getSerializableExtra("LoginBean");
		String teamLeaderName = fatherList.get(position).teamLeaderName;
		AlertDialog.Builder builder = new AlertDialog.Builder(FindworkListViewActivity.this);
		builder.setMessage("请输入转发备注");
		builder.setTitle("转发给:"+teamLeaderName);
		final EditText editText = (EditText)(View.inflate(getApplicationContext(), R.layout.dialog_edit, null));
		builder.setView(editText);
		
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(final DialogInterface arg0, int arg1) {
				int flag = getIntent().getIntExtra("flag", 0);
				String code = "";
				switch (flag) {
				case 1:
					code = getIntent().getStringExtra("serviceBean");
					break;
				case 2:
					code = getIntent().getStringExtra("casesBean");
					break;
				}
				new OrderStream().forward(new EntityCallback() {
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						Toast.makeText(getApplicationContext(), "转发成功", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(getApplicationContext(),
								HomeActivity.class);
						intent.putExtra("LoginBean", loginBean);
						arg0.dismiss();
						startActivityForResult(intent,100);
						setResult(5);
						finish();
					}
				}, code, fatherList.get(position).teamLeaderCode, editText.getText().toString());
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		
		builder.create().show();
	}
	public void showForwardDialog(final int position,final int childposition){
		loginBean = (LoginBean)this.getIntent().getSerializableExtra("LoginBean");
		String name = fatherList.get(position).staffs.get(childposition).name;
		AlertDialog.Builder builder = new AlertDialog.Builder(FindworkListViewActivity.this);
		builder.setMessage("请输入转发备注");
		builder.setTitle("转发给:"+name);
		final EditText editText = (EditText)(View.inflate(getApplicationContext(), R.layout.dialog_edit, null));
		builder.setView(editText);
		
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(final DialogInterface arg0, int arg1) {
				int flag = getIntent().getIntExtra("flag", 0);
				String code = "";
				switch (flag) {
				case 1:
					code = getIntent().getStringExtra("serviceBean");
					break;
				case 2:
					code = getIntent().getStringExtra("casesBean");
					break;
				}
				new OrderStream().forward(new EntityCallback() {
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						Toast.makeText(getApplicationContext(), "转发成功", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(getApplicationContext(),
								HomeActivity.class);
						intent.putExtra("LoginBean", loginBean);
						arg0.dismiss();
						startActivityForResult(intent,100);
						setResult(5);
						finish();
					}
				}, code, fatherList.get(position).staffs.get(childposition).staffCode, editText.getText().toString());
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		
		builder.create().show();
	}
	public void showAddWorker(final int position){
		String teamLeaderName = fatherList.get(position).teamLeaderName;
		AlertDialog.Builder builder = new AlertDialog.Builder(FindworkListViewActivity.this);
		builder.setMessage("确认添加为工友:"+teamLeaderName);
		builder.setTitle("提示");
		
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(final DialogInterface arg0, int arg1) {
				CasesBean.Records rec = (CasesBean.Records)(getIntent().getSerializableExtra("CasesBean"));
				new OrderManager().addPartner(new EntityCallback() {
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
						arg0.dismiss();
						setResult(9);
						finish();
					}
				}, rec.repairCaseCode, fatherList.get(position).teamLeaderCode);
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		
		builder.create().show();
	}
	public void showAddWorker(final int position,final int childPosition){
		String name = fatherList.get(position).staffs.get(childPosition).name;
		AlertDialog.Builder builder = new AlertDialog.Builder(FindworkListViewActivity.this);
		builder.setMessage("确认添加为工友:"+name);
		builder.setTitle("提示");
		
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(final DialogInterface arg0, int arg1) {
				CasesBean.Records rec = (CasesBean.Records)(getIntent().getSerializableExtra("CasesBean"));
				new OrderManager().addPartner(new EntityCallback() {
					@Override
					public void connectCallback(EntityrResult entityrResult) {
						Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
						arg0.dismiss();
						setResult(9);
						finish();
					}
				}, rec.repairCaseCode, fatherList.get(position).staffs.get(childPosition).staffCode);
			}
		});
		
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.dismiss();
			}
		});
		
		builder.create().show();
	}
	@Override
	public void onBackPressed() {
		//super.onBackPressed();
		setResult(10);
		finish();
		
	}
	
}
