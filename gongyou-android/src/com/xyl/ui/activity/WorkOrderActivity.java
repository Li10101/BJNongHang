package com.xyl.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.base.BaseResource;
import com.xyl.domain.AlarmsBean;
import com.xyl.domain.AssetBean;
import com.xyl.domain.CasesBean;
import com.xyl.domain.CategoriesBean;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.FindByCategoryNoBean;
import com.xyl.domain.FindByCategoryNoBean.Records;
import com.xyl.domain.FindNoticeBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.OutCaseBean;
import com.xyl.domain.ServiceBeanRecords;
import com.xyl.domain.ServicesBean;
import com.xyl.domain.StaffListBean;
import com.xyl.domain.TeamListBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.domain.personnel.ApplyPeopleBean;
import com.xyl.global.NetContacts;
import com.xyl.net.AssetManager;
import com.xyl.net.DeviceManager;
import com.xyl.net.MixNet;
import com.xyl.net.NewNet;
import com.xyl.net.NoticeNet;
import com.xyl.net.OrderManager;
import com.xyl.net.OrderStream;
import com.xyl.net.PersonnelManager;
import com.xyl.net.ServiceRequest;
import com.xyl.ui.view.FunctionView;
import com.xyl.ui.widget.HorizontialListView;
import com.xyl.ui.widget.PullToRefreshLayout;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import me.leolin.shortcutbadger.ShortcutBadger;

public class WorkOrderActivity extends Activity implements PullToRefreshLayout.OnRefreshListener {
    private List<FindByCategoryNoBean.Records> categoryList;
    private List<OutCaseBean.RecordsBean> outCaseList;
    private List<FindByAssetNoBean.RecordsBean> assetList;
    private CategoriesBean categoriesBean;
    private AssetBean assetBean;
    private ImageView iv_title_left;
    private TextView tv_title;
    private TextView tv_title_right;
    private ListView lv_work_order;
    private ExpandableListView expandableListView;
    private PullToRefreshLayout refreshLayout;
    private RotateAnimation loadingAnimation;
    private List<ServiceBeanRecords> serviceList;
    private List<CasesBean.Records> casesList;
    private List<StaffListBean> staffList;
    private List<AlarmsBean.Records> alarmList;
    private LoginBean loginBean;
    private String workInfoBean;
    private MyBaseAdapter mAdapter;
    private HashMap<String, Integer> resourceState;
    private CasesBean casesBean;
    private Button bt_order_all;
    private Button bt_order_assignGroup;
    private Button bt_order_worker;
    private Button bt_order_loot;
    private int flag;
    private HorizontialListView lv_horizon_state;
    private TeamListBean teamBean;
    private int assetId;
    private String assetName;
    private int purchase_type;
    private String carts;
    private OutCaseBean.RecordsBean recordsBean;
    private List<ApplyPeopleBean.RecordsBean> applyPeoplerecords;


    private static int RESULTAPPLY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initListener();
        initData();
        resetNotifyList();
    }


    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(2);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void resetNotifyList() {
        int position = this.getIntent().getIntExtra("position", -1);
        if (position != -1) {
            /*if(SlidingLeftView.slidingList!=null && SlidingLeftView.slidingList.size()>0){
                NotifyTextView pod = SlidingLeftView.slidingList.get(position);
				pod.setNotifyCount(0);
			}*/
//            SharedPreferencesUtil.setParam(this, String.valueOf(position), 0);
//            FunctionView.getFunctionAdapter().notifyDataSetChanged();

        }
    }

    private void initView() {
        iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        tv_title_right = (TextView) this.findViewById(R.id.tv_title_right);
        lv_work_order = (ListView) this.findViewById(R.id.content_view_order);
        lv_work_order.setDivider(null);
        refreshLayout = (PullToRefreshLayout) this
                .findViewById(R.id.refresh_view_order);
        refreshLayout.setOnRefreshListener(this);
        loadingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this,
                R.anim.rotating);
        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        loadingAnimation.setInterpolator(lir);

        lv_horizon_state = (HorizontialListView) this.findViewById(R.id.lv_horizon_state);
    }

    private void initListener() {


        lv_work_order.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                if (flag == 6) {
                    showGroupDialog(position);
                    return;
                }
                if (flag == 4) {
                    return;
                }
                if (flag == 11) {
                    carts = getIntent().getStringExtra("Carts");
                    purchase_type = getIntent().getIntExtra("purchase_type", 0);
                    recordsBean = outCaseList.get(position);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(WorkOrderActivity.this);
                    alertDialog.setTitle("确认关联此工单");
                    alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            comit();
                        }
                    });
                    alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                    return;
                }
                if (flag == 12){
                    ApplyPeopleBean.RecordsBean recordsBean = applyPeoplerecords.get(position);
                    Intent intent = new Intent();
                    intent.putExtra("Applyrecords", recordsBean);
                    setResult(RESULTAPPLY,intent);
                    finish();
                    return;
                }
                Intent intent = new Intent(WorkOrderActivity.this, OrderStateActivity.class);
                intent.putExtra("LoginBean", getIntent().getSerializableExtra("LoginBean"));
                switch (flag) {
                    case 1:
                        intent.putExtra("serviceBean", serviceList.get(position));
                        break;
                    case 2:
                        intent.putExtra("CasesBean", casesList.get(position));
                        break;
				/*case 4:
					intent.putExtra("AlarmsBean", alarmList.get(position));
					break;*/
                    case 5:
                        intent.putExtra("FindNoticeBean", noticeList.get(position));
                        break;
                    case 7:
                        intent.putExtra("FindByCategoryNoBean", categoryList.get(position));
                        break;
                    case 10:
                        intent.putExtra("FindByAssetNoBean", assetList.get(position));
                        break;

//                    case 11:
//                        intent.putExtra("OutCaseBean", outCaseList.get(position));
//                        break;
                }
                intent.putExtra("flag", flag);
                startActivity(intent);
            }
        });

        iv_title_left.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                setResult(10);
                finish();
            }
        });

        lv_work_order.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当不滚动时
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    //判断滚动到底部
                    if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                        NetContacts.getInstance().pageIndex++;
                        //修改了6.22
                        requestData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
            }
        });

        lv_horizon_state.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lv, View btn, int position,
                                    long arg3) {
//				clearData();
                casesList = new ArrayList<CasesBean.Records>();
                mAdapter.notifyDataSetChanged();
                setButtonStyle();
                ((Button) btn).setTextColor(Color.WHITE);
                ((Button) btn).setBackgroundColor(0xFF54A9DF);

                NetContacts.getInstance().pageIndex = 1;
                mPosition = position;
                if (position == 0) {
                    new OrderManager().cases(baseCallback);
                    return;
                }
                new OrderManager().statusCases(states[position - 1], baseCallback);
            }
        });
        tv_title_right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                final String[] srt = new String[]{"全部", "过期", "未过期"};
                final String category = categoriesBean.categoryNo;
                new AlertDialog.Builder(WorkOrderActivity.this)
                        .setTitle("请选择")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setSingleChoiceItems(srt, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                if (arg1 == 1) {
                                    srt[arg1] = "Expired";
                                } else if (arg1 == 2) {
                                    srt[arg1] = "NotExpired";
                                }
                                new DeviceManager().findSelectCategoryNo(category, srt[arg1], new BaseCallback<FindByCategoryNoBean>() {
                                    @Override
                                    public void messageSuccess(FindByCategoryNoBean bean) {
                                        mAdapter.setFlag(7);
                                        if (NetContacts.getInstance().pageIndex == 1) {
                                            categoryList = bean.records;
                                            lv_work_order.setAdapter(mAdapter);

                                        } else {
                                            if (bean.records.size() > 1) {
                                                categoryList.addAll(bean.records);
                                            }
                                        }

                                        if (bean.records.size() <= 1) {
                                            Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        mAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void messageFailure(MessageBean backError) {
                                    }

                                    @Override
                                    public void connectFailure(MessageBean messageBean) {
                                    }
                                });
                                arg0.dismiss();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    private int mPosition = 0;
    private boolean isFirst = true;

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
        if ("全部工单".equals(workInfoBean)) {
            return;
        }
        /*if (!isFirst) {
            NetContacts.getInstance().pageIndex = 1;
            //clearData();//修改过6、22
            requestData();
        }
        isFirst = false;*/
    }


    private void initData() {
        iv_title_left.setImageResource(R.drawable.rightmenu);
        iv_title_left.setVisibility(View.VISIBLE);

        resourceState = BaseResource.getResourceState();
        mAdapter = new MyBaseAdapter();

        workInfoBean =  this.getIntent().getStringExtra(
                "WorkInfoBean");

        tv_title.setText(workInfoBean);

        mHorizonAdapter = new MyHorizontialAdapter(WorkOrderActivity.this);
        lv_horizon_state.setAdapter(mHorizonAdapter);
        //TODO
        baseCallback = new BaseCallback<CasesBean>() {
            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
            }
        };

        NetContacts.getInstance().pageIndex = 1;
        requestData();
    }

    String code = "";


    public void comit() {
        new OrderManager().commitCartData(carts, purchase_type, recordsBean.getRepairCaseCode(), new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    ToastUtil.showToast("订单已生成");
                    setResult(1);
                    finish();
                } else {
                    ToastUtil.showToast("生成订单失败");
                }
            }
        });
    }

    public void showGroupDialog(int position) {
        final int flag = getIntent().getIntExtra("flag", 0);
        teamBean = teamList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(WorkOrderActivity.this);
        builder.setTitle("派发给:" + teamBean.name + "组");
        final View view = View.inflate(this, R.layout.dialog_allot_options, null);
        final RadioGroup rg_priority = (RadioGroup) view.findViewById(R.id.rg_priority);
        final RadioGroup rg_caseMoneyType = (RadioGroup) view.findViewById(R.id.rg_caseMoneyType);
        final RadioGroup rg_caseArea = (RadioGroup) view.findViewById(R.id.rg_caseArea);
        final RadioGroup rg_caseProfession = (RadioGroup) view.findViewById(R.id.rg_caseProfession);
        final EditText et_allot_content = (EditText) view.findViewById(R.id.et_allot_content);
        final EditText et_allot_money = (EditText) view.findViewById(R.id.et_allot_money);
        loginBean = (LoginBean) this.getIntent().getSerializableExtra("LoginBean");
        ////caseMoneyType, caseArea, contentAndNote, fixMoney, caseProfession
        if (flag == 2) {
            view.setVisibility(View.GONE);
        }
        builder.setView(view);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface arg0, int arg1) {
                switch (flag) {
                    case 1:
                        code = getIntent().getStringExtra("serviceBean");
                        String priority = ((RadioButton) view.findViewById(rg_priority.getCheckedRadioButtonId())).getHint().toString();
                        String caseMoneyType = ((RadioButton) view.findViewById(rg_caseMoneyType.getCheckedRadioButtonId())).getHint().toString();
                        String caseArea = ((RadioButton) view.findViewById(rg_caseArea.getCheckedRadioButtonId())).getHint().toString();
                        String caseProfession = ((RadioButton) view.findViewById(rg_caseProfession.getCheckedRadioButtonId())).getHint().toString();
                        String contentAndNote = et_allot_content.getText().toString();
                        String fixMoney = et_allot_money.getText().toString();

                        new NewNet().createCustomerAndAssignGroup(new BaseCallback<MessageBean>() {
                            @Override
                            public void messageSuccess(MessageBean bean) {
                                Toast.makeText(getApplicationContext(), "工单派发成功\n编号为:CUS_0000" + code, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),
                                        HomeActivity.class);
                                intent.putExtra("LoginBean", loginBean);
                                arg0.dismiss();
                                startActivityForResult(intent, 100);
                                setResult(7);
                                finish();
                            }

                            @Override
                            public void messageFailure(MessageBean backError) {
                            }

                            @Override
                            public void connectFailure(MessageBean messageBean) {
                            }
                        }, code, teamBean.teamId, priority, caseMoneyType, caseArea, caseProfession, contentAndNote, fixMoney);
                        break;
                    case 2:
                        view.setVisibility(View.GONE);
                        code = getIntent().getStringExtra("casesBean");
                        new OrderStream().assignTeam(new EntityCallback() {

                            @Override
                            public void connectCallback(EntityrResult entityrResult) {
                                if (entityrResult.entityType == EntityType.messagetrue) {
                                    Toast.makeText(getApplicationContext(), "工单派发成功\n编号为:" + code, Toast.LENGTH_SHORT).show();
                                    arg0.dismiss();
//								startActivityForResult(new Intent(WorkOrderActivity.this, HomeActivity.class),100);
                                    setResult(7);
                                    finish();
                                }
                            }
                        }, code, teamBean.teamId);
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

    public void requestData() {
        if ("维修状态".equals(workInfoBean) || "客户报修".equals(workInfoBean)) {
            getServiceData();
        } else if ("维修评价".equals(workInfoBean)
                || "维修工单".equals(workInfoBean)|| "已维修".equals(workInfoBean)) {
            getReviewData();
        } else if ("我的单".equals(workInfoBean)) {
            getMyData();
        } else if ("组工单".equals(workInfoBean)) {
            getGroupData();
        } else if ("接受工单".equals(workInfoBean)) {
            getAcceptData();
        } else if ("拒绝工单".equals(workInfoBean)) {
            getRejectData();
        } else if ("派发工单".equals(workInfoBean)) {
            getAllotData();
        } else if ("派给组".equals(workInfoBean)) {
            getAssginGroupData();
        } else if ("派给工人工单".equals(workInfoBean)) {
            getAssginStaffData();
            ///getAllotData();
        } else if ("已评价".equals(workInfoBean)) {
            getCommentData();
        } else if ("全部工单".equals(workInfoBean)) {
            initAllOrder();
        } else if ("设备报警".equals(workInfoBean)) {
            getDeviceAlarmData();
        } else if ("工人选择".equals(workInfoBean)) {
            getWorkerData();
        } else if ("楼宇通知".equals(workInfoBean)) {
            getNotifyData();
        } else if ("工人分组".equals(workInfoBean)) {
            getWorkGroup();
        } else if ("报修工单".equals(workInfoBean)) {
            //getServiceWorker();
            getServiceData();
        } else if ("遗留工单".equals(workInfoBean)) {
            getRemnantData();
        } else if ("资产管理".equals(workInfoBean)) {
            getAssetData();
        } else if ("关联工单".equals(workInfoBean)) {
            getOutCaseData();
        } else if ("申请人".equals(workInfoBean)) {
            getApplayPeople();
        } else {
            getDeviceData();
        }
    }

    private void getApplayPeople() {
        new PersonnelManager().getApplayPeople(new BaseCallback<ApplyPeopleBean>() {
            @Override
            public void messageSuccess(ApplyPeopleBean bean) {
                mAdapter.setFlag(12);
                if (NetContacts.getInstance().pageIndex == 1) {
                    applyPeoplerecords = bean.getRecords();
                    lv_work_order.setAdapter(mAdapter);
                } else {
                    if (applyPeoplerecords.size() > 1) {
                        applyPeoplerecords.addAll(bean.getRecords());
                    }
                }

                if (bean.getRecords().size() <= 1) {
                    Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private void getOutCaseData() {
        new AssetManager().getCaseSimpleVos(new BaseCallback<OutCaseBean>() {
            @Override
            public void messageSuccess(OutCaseBean bean) {
                mAdapter.setFlag(11);
                if (NetContacts.getInstance().pageIndex == 1) {
                    outCaseList = bean.getRecords();
                    lv_work_order.setAdapter(mAdapter);
                } else {
                    if (outCaseList.size() > 1) {
                        outCaseList.addAll(bean.getRecords());
                    }
                }

                if (bean.getRecords().size() <= 1) {
                    Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private void getAssetData() {
        //tv_title_right.setVisibility(View.VISIBLE);
        //tv_title_right.setText("筛选");

        assetBean = (AssetBean) this.getIntent().getSerializableExtra("AssetBean");
        assetName = (String) this.getIntent().getSerializableExtra("assetId");

        new AssetManager().findByAssetNo(assetBean.getPhysicalAssetId() + "", new BaseCallback<FindByAssetNoBean>() {
            @Override
            public void messageSuccess(FindByAssetNoBean bean) {
                mAdapter.setFlag(10);
                if ("资产管理".equals(workInfoBean)) {
                    tv_title.setText(assetName);
                }
                if (NetContacts.getInstance().pageIndex == 1) {
                    assetList = bean.getRecords();
                    lv_work_order.setAdapter(mAdapter);
                } else {
                    if (bean.getRecords().size() > 1) {
                        assetList.addAll(bean.getRecords());
                    }
                }

                if (bean.getRecords().size() <= 1) {
                    Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private void getRemnantData() {
        new OrderManager().RemnantCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }


    public void getDeviceData() {

        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("筛选");

        categoriesBean = (CategoriesBean) this.getIntent().getSerializableExtra("CategoriesBean");
        new DeviceManager().findByCategoryNo(categoriesBean.categoryNo, new BaseCallback<FindByCategoryNoBean>() {
            @Override
            public void messageSuccess(FindByCategoryNoBean bean) {
                mAdapter.setFlag(7);
                if (NetContacts.getInstance().pageIndex == 1) {
                    categoryList = bean.records;
                    lv_work_order.setAdapter(mAdapter);
                } else {
                    if (bean.records.size() > 1) {
                        categoryList.addAll(bean.records);
                    }
                }

                if (bean.records.size() <= 1) {
                    Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private List<TeamListBean> teamList;

    private void getWorkGroup() {
        new MixNet().teamList(new BaseCallback<List<TeamListBean>>() {
            @Override
            public void messageSuccess(List<TeamListBean> bean) {
                mAdapter.setFlag(6);
                if (NetContacts.getInstance().pageIndex == 1) {
                    teamList = bean;
                    lv_work_order.setAdapter(mAdapter);
                } else {
                    if (bean.size() > 1) {
                        clearData();
                        teamList.addAll(bean);

                    }
                }

                if (bean.size() <= 1) {
                    Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
            }
        });
    }

    public void getDeviceAlarmData() {
        new DeviceManager().alarms(new BaseCallback<AlarmsBean>() {
            @Override
            public void messageSuccess(AlarmsBean bean) {
                mAdapter.setFlag(4);
                if (NetContacts.getInstance().pageIndex == 1) {
                    alarmList = bean.records;
                    lv_work_order.setAdapter(mAdapter);
                } else {
                    if (bean.records.size() > 1) {
                        alarmList.addAll(bean.records);
                    }
                }

                if (bean.records.size() <= 1) {
                    Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_SUCCEED);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }


    private List<FindNoticeBean.RecordsBean> noticeList;
    private Button bt_order_accept;

    public void getNotifyData() {
        new NoticeNet().findNotice(new BaseCallback<FindNoticeBean>() {

            @Override
            public void messageSuccess(FindNoticeBean bean) {
                mAdapter.setFlag(5);
                if (NetContacts.getInstance().pageIndex == 1) {
                    noticeList = bean.getRecords();
                    lv_work_order.setAdapter(mAdapter);
                } else {
                    if (bean.getRecords().size() > 1) {
                        noticeList.addAll(bean.getRecords());
                    }
                }
                if (bean.getRecords().size() <= 1) {
                    Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
            }
        });
    }

    public void setStaffData(List<StaffListBean> bean) {
        mAdapter.setFlag(3);
        if (NetContacts.getInstance().pageIndex == 1) {
            staffList = bean;
            lv_work_order.setAdapter(mAdapter);
        } else {
            if (bean.size() > 1) {
                staffList.addAll(bean);
            }
        }

        if (bean.size() <= 1) {
            Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            return;
        }
        mAdapter.notifyDataSetChanged();
    }

    public void setCasesData(CasesBean bean) {
        casesBean = bean;
        mAdapter.setFlag(2);
        if (NetContacts.getInstance().pageIndex == 1) {
            casesList = bean.records;
            lv_work_order.setAdapter(mAdapter);
        } else if (bean.records.size() > 1) {
            casesList.addAll(casesBean.records);
        }
        if (bean.records.size() <= 1) {
            Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            return;
        }
        refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_SUCCEED);
        mAdapter.notifyDataSetChanged();
    }

    public void setServiceData(ServicesBean bean) {
        mAdapter.setFlag(1);
        if (NetContacts.getInstance().pageIndex == 1) {
            serviceList = bean.records;
            lv_work_order.setAdapter(mAdapter);
        } else if (bean.records.size() > 1) {
            serviceList.addAll(bean.records);
        }
        if (bean.records.size() <= 1) {
            Toast.makeText(WorkOrderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            return;
        }
        refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_SUCCEED);
        mAdapter.notifyDataSetChanged();
    }

    public void getWorkerData() {
        new MixNet().staffList(new BaseCallback<List<StaffListBean>>() {

            @Override
            public void messageSuccess(List<StaffListBean> bean) {
                setStaffData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    public void getAllotData() {
        LoginBean loginBean = (LoginBean) this.getIntent().getSerializableExtra("LoginBean");
        switch (Integer.parseInt(loginBean.type)) {
            case 2:

                new OrderManager().AssignCasesServiceGourp(new BaseCallback<CasesBean>() {
                    @Override
                    public void messageSuccess(CasesBean bean) {
                        setCasesData(bean);
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {
                        refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
                    }

                    @Override
                    public void connectFailure(MessageBean messageBean) {
                        refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
                    }
                });

                break;
            case 3:
                new OrderManager().serviceAssessedCases(new BaseCallback<CasesBean>() {

                    @Override
                    public void messageSuccess(CasesBean bean) {
                        setCasesData(bean);
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {
                    }

                    @Override
                    public void connectFailure(MessageBean messageBean) {
                    }
                });
                break;
            case 4:
                new OrderManager().assignCases(new BaseCallback<CasesBean>() {
                    @Override
                    public void messageSuccess(CasesBean bean) {
                        setCasesData(bean);
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {
                    }

                    @Override
                    public void connectFailure(MessageBean messageBean) {
                    }
                });
                break;
        }
    }

    public void getCommentData() {
        new OrderManager().assessedCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    public String[] btnText = {"全部", "派给组", "派给工人", "已接受", "已抢单", "已拒绝", "已到达", "已维修", "已完成", "已关闭"};
    public String[] states = {"assign-group", "assign-staff", "accepted", "vied", "rejected", "arrived", "fixed", "done", "closed"};
	/*public String[] btnText =   {"全部","派给组","派给工人","已接受","已抢单","已拒绝","已到达","已维修","已关闭","报修工单"};
	public String[] states =    {"assign-group","assign-staff","accepted","vied","rejected","arrived","fixed","closed"};*/
	/*public String[] btnText =   {"全部","派给组","派给工人","已接受","已抢单","已拒绝","已到达","已维修","已关闭"};
	public String[] states =    {"assign-group","assign-staff","accepted","vied","rejected","arrived","fixed","done","closed"};*/

    private BaseCallback<CasesBean> baseCallback;
    private MyHorizontialAdapter mHorizonAdapter;

    public void initAllOrder() {
        //bt_order_all = (Button)this.findViewById(R.id.bt_order_all);
        lv_horizon_state.setVisibility(View.VISIBLE);
        if (mPosition == 0) {
            new OrderManager().cases(baseCallback);
            return;
        }
        new OrderManager().statusCases(states[mPosition - 1], baseCallback);


//		ll_order_control = (LinearLayout) this
//				.findViewById(R.id.ll_order_control);
//		bt_order_assignGroup = (Button) this
//				.findViewById(R.id.bt_order_assignGroup);
//		bt_order_worker = (Button) this.findViewById(R.id.bt_order_worker);
//		bt_order_loot = (Button) this.findViewById(R.id.bt_order_loot);
//		bt_order_accept = (Button)this.findViewById(R.id.bt_order_accept);
//		requestAllOrder();
    }

    public void setButtonStyle() {
        ArrayList<Button> btnList = mHorizonAdapter.getBtnList();
        for (int a = 0; a < btnList.size(); a++) {
            Button btn = btnList.get(a);
            btn.setTextColor(Color.BLACK);
            btn.setBackgroundColor(0xFFF0F0F0);
        }
    }

    public void allOrder(View v) {
        setBackgroundSelect(true, false, false, false, false);
        requestAllOrder();
    }

    public void acceptOrder(View v) {
        setBackgroundSelect(false, false, false, false, true);
        getAcceptData();
    }

    public void requestAcceptOrder() {
        new OrderManager().statusCases("vied", new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                Toast.makeText(WorkOrderActivity.this, "请检查您的网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
            }
        });
    }

    public void requestAllOrder() {
        new OrderManager().cases(new BaseCallback<CasesBean>() {
            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                Toast.makeText(WorkOrderActivity.this, "请检查您的网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }
        });
    }

    public void assignGroup(View v) {
        setBackgroundSelect(false, true, false, false, false);
        getAssginGroupData();
    }

    public void assignWorker(View v) {
        setBackgroundSelect(false, false, true, false, false);
        getAssginStaffData();
    }

    public void lootOrder(View v) {
        setBackgroundSelect(false, false, false, true, false);
        requestAcceptOrder();
    }

    private void getAssginStaffData() {
        new OrderManager().assginStaffCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    public void getAssginGroupData() {
        new OrderManager().assginGroupCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    public void getRejectData() {
        new OrderManager().rejectCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    public void getAcceptData() {
        ShortcutBadger.applyCount(getApplicationContext(), 0);
        new OrderManager().acceptCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    private void getGroupData() {
        Integer push1 = (Integer) SharedPreferencesUtil.getParam(this, String.valueOf(1), 0);
        Integer push0 = (Integer) SharedPreferencesUtil.getParam(this, String.valueOf(0), 0);
        int count1 = push1 + push0;
        ShortcutBadger.applyCount(getApplicationContext(), count1 - push1);
        new OrderManager().teamCases(new BaseCallback<CasesBean>() {
            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    private void getMyData() {
        Integer push1 = (Integer) SharedPreferencesUtil.getParam(this, String.valueOf(1), 0);
        Integer push0 = (Integer) SharedPreferencesUtil.getParam(this, String.valueOf(0), 0);
        int count1 = push1 + push0;
        ShortcutBadger.applyCount(getApplicationContext(), count1 - push0);
        new OrderManager().myCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {

                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    public void getReviewData() {
        new OrderManager().fixedCases(new BaseCallback<CasesBean>() {

            @Override
            public void messageSuccess(CasesBean bean) {
                setCasesData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }

    public void getServiceData() {
        ShortcutBadger.applyCount(getApplicationContext(), 0);
        LoginBean loginBean = (LoginBean) this.getIntent().getSerializableExtra("LoginBean");
        int type = Integer.parseInt(loginBean.type);
        if ("报修工单".equals(workInfoBean)) {
            new ServiceRequest().myServices(new BaseCallback<ServicesBean>() {
                @Override
                public void messageSuccess(ServicesBean bean) {
                    setServiceData(bean);
                }

                @Override
                public void messageFailure(MessageBean backError) {
                    refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
                }

                @Override
                public void connectFailure(MessageBean messageBean) {
                    refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);

                }
            });
        } else {
            new ServiceRequest().services(new BaseCallback<ServicesBean>() {

                @Override
                public void messageSuccess(ServicesBean bean) {

                    setServiceData(bean);
                }

                @Override
                public void messageFailure(MessageBean backError) {
                }

                @Override
                public void connectFailure(MessageBean messageBean) {
                }
            });
        }

    }


    public void getServiceWorker() {
        new ServiceRequest().servicesWorker(new BaseCallback<ServicesBean>() {

            @Override
            public void messageSuccess(ServicesBean bean) {
                setServiceData(bean);
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
    public void onRefresh() {
        requestData();
    }


    class MyHorizontialAdapter extends BaseAdapter {

        private ArrayList<Button> btnList;

        public MyHorizontialAdapter(Context context) {
            if (btnList == null) {
                btnList = new ArrayList<Button>();
                for (int a = 0; a < btnText.length; a++) {
                    Button btn = (Button) (View.inflate(context, R.layout.item_horizontial, null));
                    btn.setText(btnText[a]);
                    if (a == 0) {
                        btn.setTextColor(Color.WHITE);
                        btn.setBackgroundColor(0xFF54A9DF);
                    }
                    btnList.add(btn);
                }
            }
        }

        @Override
        public int getCount() {
            return btnText.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return btnList.get(position);
        }

        public ArrayList<Button> getBtnList() {
            return btnList;
        }
    }

    class MyBaseAdapter extends BaseAdapter {
        public void setFlag(int flag) {
            WorkOrderActivity.this.flag = flag;
        }

        @Override
        public int getCount() {
            int count = 0;
            switch (flag) {
                case 1:
                    count = serviceList.size();
                    break;
                case 2:
                    count = casesList.size();
                    break;
                case 3:
                    count = staffList.size();
                    break;
                case 4:
                    count = alarmList.size();
                    break;
                case 5:
                    count = noticeList.size();
                    break;
                case 6:
                    count = teamList.size();
                    break;
                case 7:
                    count = categoryList.size();
                    break;
                case 10:
                    count = assetList.size();
                    break;
                case 11:
                    count = outCaseList.size();
                    break;
                case 12:
                    count = applyPeoplerecords.size();
                    break;
            }
            return count;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(WorkOrderActivity.this,
                        R.layout.item_search_result, null);
                holder.ivState = (ImageView) convertView
                        .findViewById(R.id.iv_search_state);
                holder.tvNumber = (TextView) convertView
                        .findViewById(R.id.tv_search_priority);
                holder.tvAddress = (TextView) convertView
                        .findViewById(R.id.tv_search_address);
                holder.tvTime = (TextView) convertView
                        .findViewById(R.id.tv_search_time);
                holder.tv_search_caseArea = (TextView) convertView
                        .findViewById(R.id.tv_search_caseArea);
                holder.tvDes = (TextView) convertView
                        .findViewById(R.id.tv_search_des);
                holder.tvState = (TextView) convertView
                        .findViewById(R.id.tv_search_state);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Integer resId;
            switch (flag) {
                case 1:
                    ServiceBeanRecords serviceRec = serviceList.get(position);
                    holder.tvNumber.setText(serviceRec.serviceRequestId);
                    holder.tvAddress.setText("地址：" + serviceRec.address);
                    holder.tvTime.setText("时间：" + serviceRec.requestTime);
                    holder.tvDes.setText("描述：" + serviceRec.description);
                    holder.tv_search_caseArea.setText("类型：" + serviceRec.customerTypeDisplay);
                    holder.tvState.setText(serviceRec.statusDisplay);
                    holder.ivState.setImageResource(R.drawable.state_group);
                    resId = resourceState.get(serviceRec.status);
                    if (resId != null) {
                        holder.ivState.setImageResource(resId);
                    }
                    break;
                case 2:
                    CasesBean.Records casesRec = casesList.get(position);
                    holder.tvNumber.setText(casesRec.repairCaseCode);
                    holder.tvAddress.setText("地址：" + casesRec.address);
                    holder.tvTime.setText("时间：" + casesRec.requestTime);
                    holder.tv_search_caseArea.setText("工单区域：" + casesRec.caseAreaDisplay);
                    holder.tvDes.setText("描述：" + casesRec.description);
                    holder.tvState.setText(casesRec.statusDisplay);
                    resId = resourceState.get(casesRec.status);
                    if (resId != null) {
                        holder.ivState.setImageResource(resId);
                    }
                    break;
                case 3:
                    if (staffList != null) {
                        StaffListBean staffBean = staffList.get(position);
                        TextView tvNumber = holder.tvNumber;
                        tvNumber.setTextSize(18);
                        tvNumber.setText("工程师姓名：" + staffBean.name);
                        holder.tvAddress.setVisibility(View.GONE);
                        holder.tvTime.setText("联系方式：" + staffBean.phone);
                        holder.tvDes.setText("职责描述：" + staffBean.description);
                        holder.tvState.setText("");
                        holder.ivState.setImageResource(R.drawable.worker);
                    }
                    break;
                case 4:
                    if (alarmList != null) {
                        AlarmsBean.Records records = alarmList.get(position);
                        holder.tvNumber.setText("设备：" + records.alarmEquipment);
                        //holder.tvNumber.setText("名称："+records.alarmPointName);
                        holder.tvAddress.setText("报警日期：" + records.alarmTime);
                        holder.tvTime.setText("设备位置：" + records.equipmentLocation);
                        holder.tvDes.setText("描述：" + records.alarmDescription);
                        holder.tvState.setText(records.statusDisplay);
                        if ("报警中".equals(records.statusDisplay)) {
                            holder.ivState.setImageResource(R.drawable.high);
                        } else {
                            holder.ivState.setImageResource(R.drawable.medium);
                        }
					
					/*else if("中".equals(records.priorityDisplay)){
						holder.ivState.setImageResource(R.drawable.low);
					}else if("高".equals(records.priorityDisplay)){
					//	holder.ivState.setImageResource(R.drawable.high);
					}*/
                    }
                    break;
                case 5:
                    if (noticeList != null) {
                        FindNoticeBean.RecordsBean noticeBean = noticeList.get(position);
                        holder.tvNumber.setText(noticeBean.getTitle());
                        holder.tvNumber.setTextSize(16);
                        holder.tvNumber.setTextColor(Color.BLACK);
                        holder.tvNumber.getPaint().setFakeBoldText(true);
                        holder.tvAddress.setText(noticeBean.getContent());
                        holder.tvTime.setText(noticeBean.getCreateTime());
                        holder.tvState.setText("");
                        holder.tv_search_caseArea.setVisibility(View.GONE);
                        holder.tvDes.setVisibility(View.GONE);
                        String omage_url = NetContacts.getInstance()
                                .BASE_IMAGE_URL + "/" + noticeList.get(position).getPictureUrl();
                        if (!noticeList.get(position).getPictureUrl().equals("")) {
                            Glide.with(getApplicationContext()).load(omage_url).into(holder.ivState);
                        } else {
                            holder.ivState.setImageResource(R.drawable.worker);
                        }

                    }

                    break;
                case 6:
                    if (teamList != null) {
                        TeamListBean teamBean = teamList.get(position);
                        TextView tvNumber = holder.tvNumber;
                        tvNumber.setTextSize(18);
                        tvNumber.setText("组名：" + teamBean.name);
                        holder.tvAddress.setVisibility(View.GONE);
                        holder.tvTime.setText("组长：" + teamBean.teamLeaderName);
                        holder.tv_search_caseArea.setVisibility(View.GONE);
                        holder.tvDes.setText("编号：" + teamBean.teamLeaderCode);
                        holder.tvState.setText("");
                        holder.ivState.setImageResource(R.drawable.worker);
                    }
                    break;
                case 7:
                    if (categoryList != null) {
                        Records records = categoryList.get(position);
                        holder.tvNumber.setText(records.equipmentNo);
                        holder.tvNumber.getPaint().setFakeBoldText(true);
                        holder.tvAddress.setText("名称:" + records.name);
                        //holder.tvTime.setText("下次检修日期：" + records.nextMaintenanceDate);
                        holder.tvTime.setVisibility(View.GONE);
                        holder.tv_search_caseArea.setVisibility(View.GONE);
                        holder.tvDes.setText("类别:" + records.categoryNo);
                        holder.tvState.setText("查看");
                        String omage_url = NetContacts.getInstance()
                                .SERVER_URL + "/" + records.icon;
                        if (!records.icon.equals("")) {
                            Glide.with(getApplicationContext()).load(omage_url).into(holder.ivState);
                        } else {
                            holder.ivState.setImageResource(R.drawable.worker);
                        }
                    }
                    break;
                case 10:
                    if (assetList != null) {
                        FindByAssetNoBean.RecordsBean records = assetList.get(position);
                        holder.tvNumber.setText(records.getUserEquipmentId() + "");
                        holder.tvNumber.getPaint().setFakeBoldText(true);
                        holder.tvAddress.setText("名称:" + records.getPhysicalAssetDes());
                        //holder.tvTime.setText("下次检修日期：" + records.nextMaintenanceDate);
                        holder.tvTime.setVisibility(View.GONE);
                        holder.tv_search_caseArea.setVisibility(View.GONE);
                        holder.tvDes.setText("类别:" + records.getUserPhysicalAssetName());
                        holder.tvState.setText("查看");
                        String omage_url = NetContacts.getInstance()
                                .SERVER_URL + "/" + records.getUserPhysicalAsset().getPhysicalAssetImg();
                        if (!records.getUserPhysicalAsset().getPhysicalAssetImg().equals("")) {
                            Glide.with(getApplicationContext()).load(omage_url).into(holder.ivState);
                        } else {
                            holder.ivState.setImageResource(R.drawable.worker);
                        }
                        holder.ivState.setImageResource(R.drawable.worker);
                    }
                    break;
                case 11:
                    if (outCaseList != null) {
                        OutCaseBean.RecordsBean records = outCaseList.get(position);
                        holder.tvNumber.setText(records.getRepairCaseCode());
                        holder.tvAddress.setText("地址：" + records.getAddress());
                        holder.tvTime.setText("时间：" + records.getRequestTime());
                        holder.tv_search_caseArea.setText("工单区域：" + records.getCaseAreaDisplay());
                        holder.tvDes.setText("描述：" + records.getDescription());
                        holder.tvState.setText(records.getStatusDisplay());
                        resId = resourceState.get(records.getStatus());
                        if (resId != null) {
                            holder.ivState.setImageResource(resId);
                        }
                    }
                    break;
                    case 12:
                        if (applyPeoplerecords != null) {
                            ApplyPeopleBean.RecordsBean recordsBean = applyPeoplerecords.get(position);
                            TextView tvNumber = holder.tvNumber;
                            tvNumber.setTextSize(18);
                            tvNumber.setText("工程师姓名：" + recordsBean.getName());
                            holder.tvAddress.setVisibility(View.GONE);
                            holder.tvTime.setText("联系方式：" + recordsBean.getPhone());
                            holder.tvDes.setText("职责描述：" + recordsBean.getLevelDisplay());
                            holder.tvState.setText("");
                            holder.ivState.setImageResource(R.drawable.worker);
                        }
                    break;

            }

            return convertView;
        }
    }

    public void clearData() {
        if (serviceList != null) {
            serviceList.clear();
        }
        if (casesList != null) {
            casesList.clear();
        }
        if (staffList != null) {
            staffList.clear();
        }
        if (alarmList != null) {
            alarmList.clear();
        }
        if (categoryList != null) {
            categoryList.clear();
        }
        if (teamList != null) {
            teamList.clear();
        }
        if (noticeList != null) {
            noticeList.clear();
        }
    }

    class ViewHolder {
        public ImageView ivState;
        public TextView tvNumber;
        public TextView tvAddress;
        public TextView tvTime;
        public TextView tv_search_caseArea;
        public TextView tvDes;
        public TextView tvState;

    }

    public void setBackgroundSelect(boolean isAll, boolean isGroup,
                                    boolean isWorker, boolean isLoot, boolean isAccept) {
        if (isAccept) {
            bt_order_accept.setBackgroundColor(0xFF54A9DF);
            bt_order_accept.setTextColor(Color.WHITE);
        } else {
            bt_order_accept.setBackgroundColor(0xFFF0F0F0);
            bt_order_accept.setTextColor(Color.BLACK);
        }

        if (isAll) {
            bt_order_all.setBackgroundColor(0xFF54A9DF);
            bt_order_all.setTextColor(Color.WHITE);
        } else {
            bt_order_all.setBackgroundColor(0xFFF0F0F0);
            bt_order_all.setTextColor(Color.BLACK);
        }

        if (isGroup) {
            bt_order_assignGroup.setBackgroundColor(0xFF54A9DF);
            bt_order_assignGroup.setTextColor(Color.WHITE);
        } else {
            bt_order_assignGroup.setBackgroundColor(0xFFF0F0F0);
            bt_order_assignGroup.setTextColor(Color.BLACK);
        }

        if (isWorker) {
            bt_order_worker.setBackgroundColor(0xFF54A9DF);
            bt_order_worker.setTextColor(Color.WHITE);
        } else {
            bt_order_worker.setBackgroundColor(0xFFF0F0F0);
            bt_order_worker.setTextColor(Color.BLACK);
        }

        if (isLoot) {
            bt_order_loot.setBackgroundColor(0xFF54A9DF);
            bt_order_loot.setTextColor(Color.WHITE);
        } else {
            bt_order_loot.setBackgroundColor(0xFFF0F0F0);
            bt_order_loot.setTextColor(Color.BLACK);
        }
    }


}
