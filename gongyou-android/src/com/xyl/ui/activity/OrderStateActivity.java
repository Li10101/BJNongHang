package com.xyl.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.base.BaseNet.EntityCallback;
import com.xyl.base.BaseNet.EntityType;
import com.xyl.base.BaseNet.EntityrResult;
import com.xyl.domain.ActionsBean;
import com.xyl.domain.CasesBean;
import com.xyl.domain.DataBean;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.FindByCategoryNoBean;
import com.xyl.domain.FindByEquipmentNoBean;
import com.xyl.domain.FindNoticeBean;
import com.xyl.domain.JSBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.ServiceBeanRecords;
import com.xyl.domain.WorkInfoBean;
import com.xyl.net.AssetManager;
import com.xyl.net.DeviceManager;
import com.xyl.net.MixNet;
import com.xyl.net.NewNet;
import com.xyl.net.NoticeNet;
import com.xyl.net.OrderManager;
import com.xyl.net.OrderStream;
import com.xyl.net.ServiceRequest;
import com.xyl.ui.widget.AssetDesView;
import com.xyl.ui.widget.OrderManagerWidget;
import com.xyl.ui.widget.WorkerDesView;
import com.xyl.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.core.content.res.ResourcesCompat;
import cn.jpush.android.api.JPushInterface;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;


import static com.xyl.utils.ToastUtil.showToast;

public class OrderStateActivity extends Activity {
    private ImageView iv_title_left;
    private TextView tv_title;
    private Button btn_order_left;
    private Button btn_order_right;
    private ScrollView sv_order;
    private Button btn_order_center;
    private View view_worker_des;
    private ListView lv_workdes;
    private LoginBean loginBean;
    private int flag;
    private ServiceBeanRecords serviceBean;
    private CasesBean.Records casesBean;
    private FindNoticeBean.RecordsBean findNoticeBean;
    //private AlarmsBean.Records alermsBean;
    private JSBean jsBean;
    private WorkerDesView desView;
    private LinearLayout ll_contentsay;
    private Button bn_maintain;
    private FindByCategoryNoBean.Records categoryBean;
    private String fixMoney = "";
    private String priority1 = "";
    private String caseMoneyType = "";
    private String caseArea = "";
    private String caseProfession = "";
    private String contentAndNote = "";
    private String equipmentNo;
    private List<ActionsBean> actionsBeen;
    private FindByEquipmentNoBean findByEquipmentNoBean;
    private List<FindByEquipmentNoBean.EquipmentPm> equipmentPmVos;
    private String selectedItem;
    private String mFilePath;
    private String label;
    private DataBean dataBean;
    private WorkerDesView workerDesView;
    private AssetDesView assetDesView;
    private String repairCaseCode;
    private FindByAssetNoBean.RecordsBean recordsBean;
    private AssetDesView assetDes;
    private ArrayList<PermissionItem> permissionItems;
    private FindByEquipmentNoBean findByEquipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        initView();
        initListener();
        initData();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sv_order != null) {
            sv_order.removeAllViews();
            sv_order.removeAllViewsInLayout();
        }
    }

    private void initView() {
        workerDesView = new WorkerDesView(OrderStateActivity.this);
        assetDesView = new AssetDesView(OrderStateActivity.this);
        iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        tv_title_right = (TextView) this.findViewById(R.id.tv_title_right);
        iv_edit = (ImageView) this.findViewById(R.id.iv_edit);
        btn_order_left = (Button) this.findViewById(R.id.btn_order_left);
        btn_order_right = (Button) this.findViewById(R.id.btn_order_right);
        btn_order_center = (Button) this.findViewById(R.id.btn_order_center);
        bn_maintain = (Button) findViewById(R.id.bn_maintain);
        ll_contentsay = (LinearLayout) findViewById(R.id.ll_contentsay);
        sv_order = (ScrollView) this.findViewById(R.id.sv_order);
        ll = (LinearLayout) this.findViewById(R.id.ll);
        /*view_worker_des = sv_order.findViewById(R.id.ll_work_dess);
        lv_workdes = (ListView) view_worker_des.findViewById(R.id.lv_workdes);*/

        permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "?????????", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "??????", R.drawable.permission_ic_storage));
        HiPermission.create(OrderStateActivity.this)
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

    private void initListener() {
        iv_title_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bn_maintain.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                equipmentPmVos = findByEquipmentNoBean.equipmentPmVos;
                List<String> strings = new ArrayList<String>();
                if (equipmentPmVos == null) {
                    equipmentPmVos = new ArrayList<FindByEquipmentNoBean.EquipmentPm>();
                }
                for (int i = 0; i < equipmentPmVos.size(); i++) {
                    strings.add(equipmentPmVos.get(i).type);
                }

                final View view = View.inflate(OrderStateActivity.this, R.layout.dialog_allot_maintain, null);
                final Spinner sp_baoyang = (Spinner) view.findViewById(R.id.sp_baoyang);
                //String[] mItems = getResources().getStringArray(R.array.maintainrname);
                ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(OrderStateActivity.this, android.R.layout.simple_spinner_item, strings);
                sp_baoyang.setAdapter(_Adapter);
                sp_baoyang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItem = sp_baoyang.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                final AlertDialog.Builder builder = new AlertDialog.Builder(OrderStateActivity.this);
                builder.setCancelable(false);
                builder.setTitle("??????????????????:");
                builder.setView(view);
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface arg0, int arg1) {
                        new OrderStream().createEquipmentCase(new EntityCallback() {
                            public void connectCallback(EntityrResult entityrResult) {
                                if (entityrResult.error == true) {
                                    showToast("????????????????????????");
                                } else if (entityrResult.error == false) {
                                    arg0.dismiss();
                                    showToast("?????????????????????");
                                }

                            }
                        }, equipmentNo, selectedItem);
                    }
                });
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        tv_title_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderStateActivity.this, EditActivity.class);
                intent.putExtra("AssetRecords", recordsBean);
                startActivityForResult(intent,0);
            }
        });

    }

    public void updateOrder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderStateActivity.this);
        builder.setTitle("??????????????????");
        final View view = View.inflate(this, R.layout.dialog_allot_options, null);
        final RadioGroup rg_priority = (RadioGroup) view.findViewById(R.id.rg_priority);
        RadioButton rb_low = (RadioButton) view.findViewById(R.id.rb_low);
        RadioButton rb_medium = (RadioButton) view.findViewById(R.id.rb_medium);
        RadioButton rb_high = (RadioButton) view.findViewById(R.id.rb_high);

        if (0 == Integer.parseInt(priority1)) {
            rb_low.setChecked(true);
        } else if (1 == Integer.parseInt(priority1)) {
            rb_medium.setChecked(true);
        } else if (2 == Integer.parseInt(priority1)) {
            rb_high.setChecked(true);
        }

        final RadioGroup rg_caseMoneyType = (RadioGroup) view.findViewById(R.id.rg_caseMoneyType);
        RadioButton rb_private = (RadioButton) view.findViewById(R.id.rb_private);
        RadioButton rb_public = (RadioButton) view.findViewById(R.id.rb_public);
        if (0 == Integer.parseInt(caseMoneyType)) {
            rb_private.setChecked(true);
        } else if (1 == Integer.parseInt(caseMoneyType)) {
            rb_public.setChecked(true);
        }

        final RadioGroup rg_caseArea = (RadioGroup) view.findViewById(R.id.rg_caseArea);
        RadioButton rb_share = (RadioButton) view.findViewById(R.id.rb_share);
        RadioButton rb_user = (RadioButton) view.findViewById(R.id.rb_user);
        if (1 == Integer.parseInt(caseArea)) {
            rb_share.setChecked(true);
        } else if (0 == Integer.parseInt(caseArea)) {
            rb_user.setChecked(true);
        }
        TextView set_gongdanzu = (TextView) view.findViewById(R.id.set_gongdanzu);
        set_gongdanzu.setVisibility(View.VISIBLE);
        final RadioGroup rg_caseProfession = (RadioGroup) view.findViewById(R.id.rg_caseProfession);
        rg_caseProfession.setVisibility(View.VISIBLE);
        RadioButton rb_qiang = (RadioButton) view.findViewById(R.id.rb_qiang);
        RadioButton rb_ruo = (RadioButton) view.findViewById(R.id.rb_ruo);
        RadioButton rb_nuan = (RadioButton) view.findViewById(R.id.rb_nuan);
        RadioButton rb_zong = (RadioButton) view.findViewById(R.id.rb_zong);


        if (0 == Integer.parseInt(caseProfession)) {
            rb_qiang.setChecked(true);
        } else if (1 == Integer.parseInt(caseProfession)) {
            rb_ruo.setChecked(true);
        } else if (2 == Integer.parseInt(caseProfession)) {
            rb_nuan.setChecked(true);
        } else if (3 == Integer.parseInt(caseProfession)) {
            rb_zong.setChecked(true);
        }
        final EditText et_allot_content = (EditText) view.findViewById(R.id.et_allot_content);
        final EditText et_allot_money = (EditText) view.findViewById(R.id.et_allot_money);

        et_allot_money.setText(fixMoney);
        et_allot_content.setText(contentAndNote);
        ////caseMoneyType, caseArea, contentAndNote, fixMoney, caseProfession
        builder.setView(view);

        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface arg0, int arg1) {
                String repairCaseCode = "";
                int flag = getIntent().getIntExtra("flag", 0);
                switch (flag) {

                    case 1:
                        repairCaseCode = serviceBean.caseCode;
                        break;
                    case 2:
                        repairCaseCode = repairCaseCode;
                        break;
                }
                String priority = ((RadioButton) view.findViewById(rg_priority.getCheckedRadioButtonId())).getHint().toString();
                String caseMoneyType = ((RadioButton) view.findViewById(rg_caseMoneyType.getCheckedRadioButtonId())).getHint().toString();
                String caseArea = ((RadioButton) view.findViewById(rg_caseArea.getCheckedRadioButtonId())).getHint().toString();
                String caseProfession = ((RadioButton) view.findViewById(rg_caseProfession.getCheckedRadioButtonId())).getHint().toString();
                String fixMoney = et_allot_money.getText().toString();
                String contentAndNote = et_allot_content.getText().toString();

                new NewNet().updateWorkOrder(new BaseCallback<MessageBean>() {

                    @Override
                    public void messageSuccess(MessageBean bean) {
                        arg0.dismiss();
                        Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                        initData();
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {
                        Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void connectFailure(MessageBean messageBean) {
                    }
                }, repairCaseCode, priority, caseMoneyType, caseArea, caseProfession, fixMoney, contentAndNote);

            }

        });


        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                arg0.dismiss();
            }
        });

        builder.create().show();
    }

    private void initData() {
        workerDesView = new WorkerDesView(OrderStateActivity.this);
        assetDesView = new AssetDesView(OrderStateActivity.this);
        label = "";
        Intent intent = this.getIntent();
        loginBean = (LoginBean) intent.getSerializableExtra("LoginBean");
        flag = intent.getIntExtra("flag", 0);
        String title = "";
        switch (flag) {
            case 1:
                serviceBean = (ServiceBeanRecords) intent
                        .getSerializableExtra("serviceBean");
                new ServiceRequest().findById(serviceBean.serviceRequestId,
                        new BaseCallback<DataBean>() {
                            @Override
                            public void messageSuccess(DataBean bean) {
                                fixMoney = String.valueOf(bean.fixMoney);
                                priority1 = bean.priority;
                                caseMoneyType = bean.caseMoneyType;
                                caseArea = bean.caseArea;
                                caseProfession = bean.caseProfession;
                                contentAndNote = bean.contentAndNote;
                                // sv_order.addView(new
                                // bean, 1).getView());
                                sv_order.removeAllViews();
                                sv_order.addView(new OrderManagerWidget(
                                        OrderStateActivity.this).setWorkDesView(
                                        workerDesView.setData(loginBean.type, bean, 2)
                                                .getView()).setData(bean, loginBean.type, 1));
                            }

                            @Override
                            public void messageFailure(MessageBean backError) {
                            }

                            @Override
                            public void connectFailure(MessageBean messageBean) {

                            }
                        });
                title = "????????????";
                initServiceButton();
                break;
            case 2:
                casesBean = (CasesBean.Records) intent.getSerializableExtra("CasesBean");

                if (casesBean == null) {
                    repairCaseCode = getIntent().getStringExtra("repairCaseCode");
                } else {
                    repairCaseCode = casesBean.repairCaseCode;
                }

                new OrderManager().data(repairCaseCode,
                        new BaseCallback<DataBean>() {
                            @Override
                            public void messageSuccess(DataBean bean) {
                                dataBean = bean;
                                fixMoney = String.valueOf(bean.fixMoney);
                                priority1 = bean.priority;
                                caseMoneyType = bean.caseMoneyType;
                                caseArea = bean.caseArea;
                                caseProfession = bean.caseProfession;
                                contentAndNote = bean.contentAndNote;

                                // sv_order.addView(desView.setData(
                                // loginBean.type, bean, 2).getView());
                                sv_order.removeAllViews();
                                sv_order.addView(new OrderManagerWidget(
                                        OrderStateActivity.this).setWorkDesView(
                                        workerDesView.setData(loginBean.type, bean, 2).getView()).setData(bean, loginBean.type, 2));
                            }

                            @Override
                            public void messageFailure(MessageBean backError) {

                            }

                            @Override
                            public void connectFailure(MessageBean messageBean) {

                            }
                        });
                setIvedit(loginBean);
                title = repairCaseCode;
                initCasesButton();
                workDesListener();
                break;
            case 5:
                findNoticeBean = (FindNoticeBean.RecordsBean) intent.getSerializableExtra("FindNoticeBean");
                new NoticeNet().findNoticeInfo(findNoticeBean.getBuildingNoticeId(), new BaseCallback<FindNoticeBean.RecordsBean>() {
                    @Override
                    public void messageSuccess(FindNoticeBean.RecordsBean bean) {

                        sv_order.removeAllViews();
                        sv_order.addView(new OrderManagerWidget(
                                OrderStateActivity.this).setFindNoticeData(bean, loginBean.type));
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {

                    }

                    @Override
                    public void connectFailure(MessageBean messageBean) {

                    }
                });
                ll.setVisibility(View.GONE);
                break;
            case 7:
                categoryBean = (FindByCategoryNoBean.Records) intent.getSerializableExtra("FindByCategoryNoBean");
                equipmentNo = categoryBean.equipmentNo;
                new DeviceManager().findByEquipmentNo(categoryBean.equipmentNo, new BaseCallback<FindByEquipmentNoBean>() {
                    @Override
                    public void messageSuccess(FindByEquipmentNoBean bean) {
                        findByEquipmentNoBean = bean;
                        sv_order.removeAllViews();
                        desView = new WorkerDesView(OrderStateActivity.this);
                        sv_order.addView(new OrderManagerWidget(OrderStateActivity.this)
                                .setEqumentData(bean, OrderStateActivity.this, loginBean));

                        ll_contentsay.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {

                    }

                    @Override

                    public void connectFailure(MessageBean messageBean) {
                    }
                });
                title = "????????????";
                ll.setVisibility(View.GONE);

                break;
            case 8:
                findByEquipment = (FindByEquipmentNoBean) intent.getSerializableExtra("FindByEquipmentNo");
                equipmentNo = findByEquipment.equipmentNo;
                new DeviceManager().findByEquipmentNo(findByEquipment.equipmentNo, new BaseCallback<FindByEquipmentNoBean>() {
                    @Override
                    public void messageSuccess(FindByEquipmentNoBean bean) {
                        findByEquipmentNoBean = bean;
                        sv_order.removeAllViews();
                        desView = new WorkerDesView(OrderStateActivity.this);
                        sv_order.addView(new OrderManagerWidget(OrderStateActivity.this)
                                .setEqumentData(bean, OrderStateActivity.this, loginBean));
                        ll_contentsay.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {
                    }

                    @Override

                    public void connectFailure(MessageBean messageBean) {
                    }
                });
                setIvedit(loginBean);
                title = "????????????";
                ll.setVisibility(View.GONE);

                break;
            case 9:
                jsBean = (JSBean) intent.getSerializableExtra("JSBean");
                new OrderManager().data(jsBean.repairCaseCode,
                        new BaseCallback<DataBean>() {
                            @Override
                            public void messageSuccess(DataBean bean) {
                                fixMoney = String.valueOf(bean.fixMoney);
                                priority1 = bean.priority;
                                caseMoneyType = bean.caseMoneyType;
                                caseArea = bean.caseArea;
                                caseProfession = bean.caseProfession;
                                contentAndNote = bean.contentAndNote;
                                desView = new WorkerDesView(OrderStateActivity.this);
                                // sv_order.addView(desView.setData(
                                // loginBean.type, bean, 2).getView());
                                sv_order.removeAllViews();
                                sv_order.addView(new OrderManagerWidget(
                                        OrderStateActivity.this).setWorkDesView(
                                        desView.setData(loginBean.type, bean, 2).getView()).setData(bean, loginBean.type, 2));
                            }

                            @Override
                            public void messageFailure(MessageBean backError) {
                            }

                            @Override
                            public void connectFailure(MessageBean messageBean) {
                            }
                        });
                title = jsBean.repairCaseCode;
                initCasesButton();
                break;
            case 10:
                recordsBean = (FindByAssetNoBean.RecordsBean) getIntent().getSerializableExtra("FindByAssetNoBean");

                new AssetManager().findByAssetEquipmentNo(recordsBean.getUserEquipmentId() + "", new BaseCallback<FindByAssetNoBean.RecordsBean>() {
                    @Override
                    public void messageSuccess(FindByAssetNoBean.RecordsBean bean) {
                        sv_order.removeAllViews();

                        sv_order.addView(new OrderManagerWidget(OrderStateActivity.this)
                                .setAssetUpdate(
                                        assetDesView.setData(loginBean.type, bean, 2).getView())
                                .setAssetData(bean, OrderStateActivity.this, loginBean));
                        ll_contentsay.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {
                    }

                    @Override

                    public void connectFailure(MessageBean messageBean) {
                    }
                });
                setIvedit(loginBean);
                title = "????????????";
                bn_maintain.setVisibility(View.GONE);
                iv_edit.setVisibility(View.GONE);
                ll.setVisibility(View.GONE);
                    assetDesListener();
                tv_title_right.setVisibility(View.VISIBLE);
                tv_title_right.setText("??????");

                break;
        }
        iv_title_left.setImageResource(R.drawable.rightmenu);
        iv_title_left.setVisibility(View.VISIBLE);
        tv_title.setText(title);
        if (("????????????").equals(title)) {
            iv_edit.setVisibility(View.GONE);
        }

    }

    private void workDesListener() {
        workerDesView.setOnListItemLongClickListener(new WorkerDesView.OnListItemLongClickListener() {
            @Override
            public void OnListItemLongClick(final int pictureId) {
                new AlertDialog.Builder(OrderStateActivity.this)
                        .setMessage("????????????????????????")
                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialogInterface, int i) {
                                new OrderStream().DeletePicture(new EntityCallback() {
                                    @Override
                                    public void connectCallback(EntityrResult entityrResult) {
                                        initData();
                                        dialogInterface.dismiss();
                                    }
                                }, pictureId + "");
                            }
                        })
                        .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();

            }
        });
    }

    private void assetDesListener() {
        assetDesView.setOnItemClickListener(new AssetDesView.OnItemClickListener() {
            @Override
            public void OnItemClick(FindByAssetNoBean.RecordsBean recordsBean,int position) {
                Intent intent = new Intent(OrderStateActivity.this, SeeAssetContentActivity.class);
                intent.putExtra("AssetrecordsBean",recordsBean.getUserEquiHistoryVos().get(position));
                startActivity(intent);
            }
        });

    }

    private void setIvedit(LoginBean loginBean) {
        if ("??????".equals(this.loginBean.typeDisplay) || "??????".equals(this.loginBean.typeDisplay)) {
            iv_edit.setVisibility(View.VISIBLE);
            iv_edit.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    updateOrder();
                }
            });
        }
    }

    public void initServiceButton() {
        ll.setVisibility(View.VISIBLE);
        int type = Integer.parseInt(loginBean.type);

        String state = serviceBean.statusDisplay;
        switch (type) {
            case 0:
                if ("?????????".equals(state)) {
                    btn_order_left.setVisibility(View.GONE);
                    btn_order_right.setVisibility(View.GONE);
                    btn_order_center.setVisibility(View.VISIBLE);
                    btn_order_center.setText("??????");
                } else {
                    btn_order_left.setVisibility(View.GONE);
                    btn_order_right.setVisibility(View.GONE);
                    btn_order_center.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                }
                break;
            case 1:
            case 2:
                btn_order_left.setVisibility(View.GONE);
                btn_order_right.setVisibility(View.GONE);
                btn_order_center.setVisibility(View.GONE);
                ll.setVisibility(View.GONE);
                break;
            case 3:
            case 12:
            case 4:
                if ("?????????".equals(state)) {
                    btn_order_left.setVisibility(View.VISIBLE);
                    btn_order_left.setText("????????????");
                    btn_order_right.setVisibility(View.VISIBLE);
                    btn_order_right.setText("?????????");
                } else if ("????????????".equals(state)) {
                    btn_order_left.setVisibility(View.VISIBLE);
                    btn_order_left.setText("??????");
                } else if ("?????????".equals(state)) {
                    btn_order_left.setVisibility(View.VISIBLE);
                    btn_order_left.setText("????????????");
                    btn_order_right.setVisibility(View.VISIBLE);
                    btn_order_right.setText("??????");
                } else {
                    btn_order_left.setVisibility(View.GONE);
                    btn_order_right.setVisibility(View.GONE);
                    btn_order_center.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                }
                break;
            case 5:
                if ("?????????".equals(state)) {
                    btn_order_left.setVisibility(View.VISIBLE);
                    btn_order_left.setText("????????????");
                    btn_order_right.setVisibility(View.VISIBLE);
                    btn_order_right.setText("?????????");
                }
                break;
        }
        setButtonEnable(true);
    }

    private OrderStream orderStream = new OrderStream();

    private void setButtonState(String btnText) {
        if ("?????????".equals(btnText)) {
            new OrderStream().arrive(new EntityCallback() {
                @Override
                public void connectCallback(EntityrResult entityrResult) {
                    resetButtonState("?????????");
                    if (entityrResult.entityType == EntityType.messagetrue) {
                        ll.invalidate();
                        initData();
                        //initCasesButton();
                        Toast.makeText(getApplicationContext(), "?????????",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }, repairCaseCode);

        } else if ("??????".equals(btnText)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    OrderStateActivity.this);
            builder.setMessage("?????????????????????");
            builder.setTitle("??????");
            final EditText editText = (EditText) (View.inflate(getApplicationContext(), R.layout.dialog_edit, null));
            builder.setView(editText);

            builder.setPositiveButton("??????",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface arg0, int arg1) {
                            new OrderStream().reject(new EntityCallback() {
                                @Override
                                public void connectCallback(
                                        EntityrResult entityrResult) {
                                    if (entityrResult.entityType == EntityType.messagetrue) {
                                        resetButtonState("?????????");
                                        initData();
                                        Toast.makeText(getApplicationContext(),
                                                "?????????", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                            }, repairCaseCode, editText.getText().toString());
                        }
                    });

            builder.setNegativeButton("??????",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                            //resetButtonState("?????????");
                            initData();

                        }
                    });

            builder.create().show();
        } else if ("??????".equals(btnText)) {
            orderStream.vie(new EntityCallback() {
                @Override
                public void connectCallback(EntityrResult entityrResult) {
                    if (entityrResult.entityType == EntityType.messagetrue) {
                        resetButtonState("?????????");
                        ll.invalidate();
                        initData();

                        Toast.makeText(getApplicationContext(), "????????????",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }, repairCaseCode);
        } else if ("????????????".equals(btnText)) {
            allotWorker("allot");
        } else if ("??????".equals(btnText)) {
            showCloseDialog();
        } else if ("??????".equals(btnText)) {
            Intent intent = new Intent(this, CommentActivity.class);
            intent.putExtra("CasesBean", casesBean);
            startActivityForResult(intent, 0);
        } else if ("??????".equals(btnText)) {
            new OrderStream().accept(new EntityCallback() {
                @Override
                public void connectCallback(EntityrResult entityrResult) {
                    if (entityrResult.entityType == EntityType.messagetrue) {
                        resetButtonState("?????????");
                        ll.invalidate();
                        initData();

                        Toast.makeText(getApplicationContext(), "?????????",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }, repairCaseCode);
        } else if ("?????????".equals(btnText)) {
            new AlertDialog.Builder(OrderStateActivity.this)
                    .setMessage("??????????????????????????????")
                    .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new OrderStream().Auditor(new EntityCallback() {
                                @Override
                                public void connectCallback(EntityrResult entityrResult) {
                                    if (entityrResult.entityType == EntityType.messagetrue) {
                                        initData();
                                    }
                                }
                            }, repairCaseCode);
                        }
                    })
                    .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            initData();

                        }
                    })
                    .create()
                    .show();

        } else if ("??????".equals(btnText)) {
            //????????????
            //allotGroup();
            //???????????????
            allotWorker("forward");
        } else if ("????????????".equals(btnText)) {
            //????????????
            //allotGroup();
            //???????????????
            Intent intent = new Intent(this,MaterilActivity.class);
            intent.putExtra("LoginBean", loginBean);
            intent.putExtra("CaseBean",casesBean);
            startActivityForResult(intent,10);
        }else if ("????????????".equals(btnText)) {
            new AlertDialog.Builder(OrderStateActivity.this)
                    .setMessage("??????????????????")
                    .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new OrderStream().caseApprove(new EntityCallback() {
                                @Override
                                public void connectCallback(EntityrResult entityrResult) {
                                    if (entityrResult.entityType == EntityType.messagetrue) {
                                        initData();
                                    }
                                }
                            }, repairCaseCode);
                        }
                    })
                    .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            initData();

                        }
                    })
                    .create()
                    .show();

        }else if ("???????????????".equals(btnText)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    OrderStateActivity.this);
            builder.setMessage("?????????????????????");
            builder.setTitle("??????");
            final EditText editText = (EditText) (View.inflate(getApplicationContext(), R.layout.dialog_edit, null));
            builder.setView(editText);

            builder.setPositiveButton("??????",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface arg0, int arg1) {
                            new OrderStream().caseDisapprove(new EntityCallback() {
                                @Override
                                public void connectCallback(
                                        EntityrResult entityrResult) {
                                    if (entityrResult.entityType == EntityType.messagetrue) {
                                        resetButtonState("?????????");
                                        initData();
                                        Toast.makeText(getApplicationContext(),
                                                "?????????", Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                            }, repairCaseCode, editText.getText().toString());
                        }
                    });

            builder.setNegativeButton("??????",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                            //resetButtonState("?????????");
                            initData();

                        }
                    });

            builder.create().show();

        } else if ("?????????".equals(btnText)) {
            showMoneyDialog();
        }else if ("??????".equals(btnText)) {
            Intent intent = new Intent(this,GetBackGoodsActivity.class);
            intent.putExtra("dataBean",dataBean);
            startActivityForResult(intent,3);
        } else if ("??????".equals(btnText.trim())) {
            showCloseDialog();
        } else if ("?????????".equals(btnText)) {
            allotGroup();
        } else if ("??????".equals(btnText)) {
            removeService();
        }
    }

    private void removeService() {
        new OrderStream().removeService(new EntityCallback() {
            @Override
            public void connectCallback(EntityrResult entityrResult) {
                finish();
            }
        }, serviceBean.serviceRequestId);
    }

    public void initCasesButton() {
        actionsBeen = new ArrayList<ActionsBean>();
        ll.setVisibility(View.VISIBLE);
        new MixNet().actions(repairCaseCode, new BaseCallback<List<ActionsBean>>() {
            @Override
            public void messageSuccess(List<ActionsBean> bean) {
                setCaseButton(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }

        });
        setButtonEnable(true);
    }

    private void setCaseButton(List<ActionsBean> bean) {
        int size = bean.size();
        actionsBeen = bean;
        switch (size) {
            case 0:
                ll.setVisibility(View.GONE);
                btn_order_left.setVisibility(View.GONE);
                btn_order_right.setVisibility(View.GONE);
                btn_order_center.setVisibility(View.GONE);
                break;
            case 1:
                btn_order_left.setVisibility(View.VISIBLE);
                btn_order_left.setText(actionsBeen.get(0).name);
                btn_order_right.setVisibility(View.GONE);
                btn_order_center.setVisibility(View.GONE);
                break;
            case 2:
                btn_order_left.setVisibility(View.VISIBLE);
                btn_order_left.setText(actionsBeen.get(0).name);
                btn_order_right.setVisibility(View.VISIBLE);
                btn_order_right.setText(actionsBeen.get(1).name);
//                btn_order_center.setVisibility(View.VISIBLE);
//                btn_order_center.setText(actionsBeen.get(2).name);
                /*btn_order_center.setVisibility(View.VISIBLE);
                btn_order_center.setText("????????????");*/
                break;
            case 3:
                btn_order_left.setVisibility(View.VISIBLE);
                btn_order_left.setText(actionsBeen.get(0).name);
                btn_order_right.setVisibility(View.VISIBLE);
                btn_order_right.setText(actionsBeen.get(1).name);
                btn_order_center.setVisibility(View.VISIBLE);
                btn_order_center.setText(actionsBeen.get(2).name);
                break;
            case 4:
                btn_order_left.setVisibility(View.VISIBLE);
                btn_order_left.setText(actionsBeen.get(0).name);
                btn_order_right.setVisibility(View.VISIBLE);
                btn_order_right.setText(actionsBeen.get(2).name);
                btn_order_center.setVisibility(View.VISIBLE);
                btn_order_center.setText(actionsBeen.get(3).name);
                break;

        }
    }

    public void resetButtonState(String state) {
        switch (flag) {
            case 1:
                serviceBean.statusDisplay = state;
                break;
            case 2:
                casesBean.statusDisplay = state;
                break;
        }
    }

    public void setButtonEnable(boolean enable) {
        btn_order_left.setEnabled(enable);
        btn_order_center.setEnabled(enable);
        btn_order_right.setEnabled(enable);
    }


    public void leftClick(View v) {
        setButtonEnable(false);
        String btnText = ((Button) v).getText().toString();
        setButtonState(btnText);
    }

    public void rightClick(View v) {
        setButtonEnable(false);
        String btnText = ((Button) v).getText().toString();
        setButtonState(btnText);
    }

    public void centerClick(View v) {
        setButtonEnable(false);
        String btnText = ((Button) v).getText().toString();
        setButtonState(btnText);
    }

    public void allotGroup() {
        Intent intent = new Intent(this, WorkOrderActivity.class);
        WorkInfoBean infoBean = new WorkInfoBean();
        infoBean.des = "????????????";
        intent.putExtra("LoginBean", loginBean);
        intent.putExtra("WorkInfoBean", infoBean);
        intent.putExtra("flag", flag);
        switch (flag) {
            case 1:
                intent.putExtra("serviceBean", serviceBean.serviceRequestId);
                break;
            case 2:
                intent.putExtra("casesBean", repairCaseCode);
                break;
        }
        startActivityForResult(intent, 0);
    }

    public void showMoneyDialog() {
        Intent intent = new Intent(OrderStateActivity.this, DrawNameActivity.class);
        intent.putExtra("money", fixMoney);
        intent.putExtra("casesCode", repairCaseCode);
        startActivityForResult(intent, 0);
    }

    public void allotWorker(String action) {
        Intent intent = new Intent(this, FindworkListViewActivity.class);
        WorkInfoBean infoBean = new WorkInfoBean();
        infoBean.des = "????????????";
        intent.putExtra("LoginBean", loginBean);
        intent.putExtra("WorkInfoBean", infoBean);
        intent.putExtra("flag", flag);
        intent.putExtra("action", action);
        switch (flag) {
            case 1:
                intent.putExtra("serviceBean", serviceBean.serviceRequestId);
                break;
            case 2:
                intent.putExtra("casesBean", repairCaseCode);
                break;
        }
        startActivityForResult(intent, 0);
        // startActivity(intent);
    }

    private String mCasesCode = "";
    private LinearLayout ll;
    private TextView tv_title_right;
    private ImageView iv_edit;

    public void showCloseDialog() {
        final EditText editText = new EditText(this);
        editText.setBackgroundResource(R.drawable.bg_search);
        editText.setPadding(10, 10, 10, 10);
        switch (flag) {
            case 1:
                mCasesCode = serviceBean.caseCode;
                break;
            case 2:
                mCasesCode = repairCaseCode;
                break;
        }
        new AlertDialog.Builder(this)
                .setTitle("????????????????????????")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(editText)
                .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        new OrderStream().close(new EntityCallback() {

                            @Override
                            public void connectCallback(
                                    EntityrResult entityrResult) {
                                if (entityrResult.entityType == EntityType.messagetrue) {
                                    resetButtonState("?????????");
                                    //initData();
                                    finish();
                                    Toast.makeText(getApplicationContext(),
                                            "????????????", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, mCasesCode, editText.getText().toString().trim());
                    }
                })
                .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        initData();
                        arg0.dismiss();
                    }
                })
                .setCancelable(true)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        switch (requestCode) {
            case 1:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mFilePath = SharedPreferencesUtil.getString(OrderStateActivity.this, "imagePath");
                        label = "1";
                        break;
                }
                break;
            case 2:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Uri uri = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = this.getContentResolver().query(uri, filePathColumn,
                                null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        mFilePath = cursor.getString(columnIndex);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = false;
                        options.inSampleSize = 16;
                        bitmap = BitmapFactory.decodeFile(mFilePath, options);
                        cursor.close();

//                        Uri uri = data.getData();
//                        Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
//                        cursor.moveToFirst();
//                        mFilePath = cursor.getString(1); // ??????????????????
//                        cursor.close();
                        label = "2";
                        break;

                }
                break;
        }

        switch (resultCode) {
            case 3:
                initData();
                break;
            case 4:
                resetButtonState("????????????");
                initData();
                break;
            case 5:
                resetButtonState("?????????");
                initData();
                break;
            case 6:
                resetButtonState("?????????");
                initData();

                break;
            case 7:
                resetButtonState("?????????");
                initData();
                break;
            case 8:
                resetButtonState("?????????");
                initData();
                break;
            case 9:
            case 10:
                initData();
                break;


        }

        /*if (bitmap != null) {
            Intent intent = new Intent(OrderStateActivity.this, WorkerDesActivity.class);
            intent.putExtra("bitmap", bitmap);
            intent.putExtra("casesCode", casesBean.repairCaseCode);
            //    ?????????????????????;
            //    uploadPhote(bitmap);
            startActivityForResult(intent, 0);
        }*/
        if (!label.equals("")) {
            Intent intent = new Intent(OrderStateActivity.this, WorkerDesActivity.class);
            intent.putExtra("bitmap", mFilePath);
            intent.putExtra("label", label);
            if (casesBean != null) {
                intent.putExtra("casesCode", casesBean);
            }
            //    ?????????????????????;
            //   uploadPhote(bitmap);
            startActivityForResult(intent, 0);
        }
    }

}