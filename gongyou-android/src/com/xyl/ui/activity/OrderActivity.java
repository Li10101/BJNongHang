package com.xyl.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.adapter.OrderAdapter;
import com.xyl.adapter.UpdataDialogFragment;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.OrderBean;
import com.xyl.domain.PurchaseBean;
import com.xyl.net.OrderManager;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.StringUtil;
import com.xyl.utils.ToastUtil;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderActivity extends BaseActivity implements View.OnClickListener,UpdataDialogFragment.UpdataDialogListener{
    private RecyclerView rvOrder;
    private LinearLayout llorderbutton;
    private Button btApproval;
    private Button btReject;
    private PurchaseBean.RecordsBean purRecordsBean;
    private LoginBean loginBean;
    private int status;
    private OrderBean orderBean;
    private int type;
    private OrderAdapter orderAdapter;
    private RelativeLayout toolbarContentRlyt;
    private Button toolbarLeftBtn;
    private TextView toolbarLeftTv;
    private TextView toolbarTitleTv;
    private Button toolbarRightBtn;
    private TextView toolbarRightTv;
    private int currentPosition;
    private TextView tv_chuku_guanlian;
    private TextView tv_chuku_guanlianren;

    @Override
    protected void initData() {
        new OrderManager().findByGoodsCaseId(purRecordsBean.getGoodsCaseId(), new BaseNet.BaseCallback<OrderBean>() {
            @Override
            public void messageSuccess(OrderBean bean) {
                orderBean = bean;
                orderAdapter = new OrderAdapter(OrderActivity.this, orderBean,loginBean,getSupportFragmentManager());
                rvOrder.setAdapter(orderAdapter);
                boolean contains = StringUtils.contains(orderBean.getShenPiRen(), loginBean.staffId);
                if (contains){
                    if (orderBean.getStatus().equals("0")){
                        setOrderButtonContent();
                    }//0?????????1??????????????????2???????????????
                }
                orderAdapter.setOnClickCameraListener(new OrderAdapter.OnClickCameraListener() {
                    @Override
                    public void OnClickCamera() {
                        showChoosePhotoDialog();
                    }
                });

                    orderAdapter.setOnClickChangeListener(new OrderAdapter.OnClickChangeListener() {
                        @Override
                        public void OnClickChange(List<OrderBean.GoodsCaseDetailsVosBean> goodsCaseDetailsVosBeen) {
                            String json = new Gson().toJson(goodsCaseDetailsVosBeen);
                            new OrderManager().editGoodsCase(json, orderBean.getGoodsCaseId(), new BaseNet.EntityCallback() {
                                @Override
                                public void connectCallback(BaseNet.EntityrResult entityrResult) {
                                    if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                                        ToastUtil.showToast("????????????");
                                        //initData();
                                    }
                                }
                            });
                        }
                    });

                tv_chuku_guanlian.setText("????????????:"+orderBean.getTypeDisplay());
                tv_chuku_guanlianren.setText("?????????:"+orderBean.getPersonName());

            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private void showChoosePhotoDialog() {
        Intent caseIntent = new Intent(OrderActivity.this, AddPicActivity.class);
        caseIntent.putExtra("OrderBean", orderBean);
        startActivityForResult(caseIntent, 0);

    }

    @Override
    protected void initView() {
        purRecordsBean = (PurchaseBean.RecordsBean) getIntent().getSerializableExtra("PurchaseBean");
        String param = SharedPreferencesUtil.getString(getApplicationContext(), SharedPreferencesUtil.LOGIN_BEAN);
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        if (loginBean ==null){
            loginBean =  new Gson().fromJson(param,LoginBean.class);
        }
        type = getIntent().getIntExtra("type", 0);
        rvOrder =  this.findViewById(R.id.rv_order);
        btApproval =  this.findViewById(R.id.bt_approval);
        btReject =  this.findViewById(R.id.bt_reject);
        tv_chuku_guanlian = this.findViewById(R.id.tv_chuku_guanlian);
        tv_chuku_guanlianren = this.findViewById(R.id.tv_chuku_guanlianren);
        llorderbutton =  this.findViewById(R.id.ll_order_buttom);
        rvOrder.setLayoutManager(new LinearLayoutManager(OrderActivity.this));


        btApproval.setOnClickListener(this);
        btReject.setOnClickListener(this);

        toolbarContentRlyt = this.findViewById(R.id.toolbar_content_rlyt);
        toolbarLeftBtn =  this.findViewById(R.id.toolbar_left_btn);
        toolbarLeftTv =  this.findViewById(R.id.toolbar_left_tv);
        toolbarTitleTv =  this.findViewById(R.id.toolbar_title_tv);
        toolbarRightBtn =  this.findViewById(R.id.toolbar_right_btn);
        toolbarRightTv =  this.findViewById(R.id.toolbar_right_tv);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);

        toolbarTitleTv.setTextColor(getResources().getColor(R.color.cart_choice_color));
        toolbarTitleTv.setText(purRecordsBean.getGoodsCaseId()+"");
        toolbarLeftBtn.setOnClickListener(this);
        toolbarRightBtn.setOnClickListener(this);
    }

    private void setOrderButtonContent() {
        llorderbutton.setVisibility(View.VISIBLE);
                    btApproval.setText("??????");
                    btReject.setText("??????");
        /*orderAdapter.setOnRefressTimeerListener(new OrderAdapter.OnRefressTimeerListener() {
            @Override
            public void OnRefressTimeeer(TimeAxisAdapter timeAxisViewHolder) {
                initData();
                timeAxisViewHolder.notifyDataSetChanged();
            }
        });*/
        /*?????????(0),?????????(1),????????????(2),?????????(3),?????????(4),?????????(5),?????????(6),?????????(7),??????(8)*/
//        switch (loginBean.type) {
//            case "1":
//                if (orderBean.getProcess() == 0) {
//                    llorderbutton.setVisibility(View.GONE);
//                } else if (orderBean.getProcess() == 1 && loginBean.name.equals(orderBean.getPersonName())) {
//                    llorderbutton.setVisibility(View.VISIBLE);
//                    btApproval.setText("??????");
//                    btReject.setText("??????");
//                } else if (orderBean.getProcess() == 2) {
//                    llorderbutton.setVisibility(View.VISIBLE);
//                    btApproval.setText("????????????");
//                    btReject.setText("??????");
//                } else {
//                    llorderbutton.setVisibility(View.GONE);
//                }
//                break;
//            case "2":
//                break;
//            case "3":
//                if (orderBean.getProcess() == 0) {
//                    llorderbutton.setVisibility(View.VISIBLE);
//                    btApproval.setText("??????");
//                    btReject.setText("??????");
//                } else if (orderBean.getProcess() == 1 && loginBean.name.equals(orderBean.getPersonName())) {
//                    llorderbutton.setVisibility(View.VISIBLE);
//                    btApproval.setText("??????");
//                    btReject.setText("??????");
//                } else if (orderBean.getProcess() == 2 && loginBean.name.equals(orderBean.getPersonName())) {
//                    llorderbutton.setVisibility(View.VISIBLE);
//                    btApproval.setText("????????????");
//                    btReject.setText("??????");
//                } else if (orderBean.getProcess() == 3) {
//                    llorderbutton.setVisibility(View.VISIBLE);
//                    btApproval.setText("??????");
//                    btReject.setText("??????");
//                } else {
//                    llorderbutton.setVisibility(View.GONE);
//                }
//                break;
//        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ord;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == btApproval) {
            String btn_content = btApproval.getText().toString();
            btApproval.setEnabled(false);
            btReject.setEnabled(false);
            switchButtonStaus(btn_content);

        } else if (v == btReject) {
            String btn_content = btReject.getText().toString();
            btReject.setEnabled(false);
            btApproval.setEnabled(false);
            switchButtonStaus(btn_content);
        }else if (v ==toolbarLeftBtn){
            setResult(2);
            finish();
        }

    }

    private void switchButtonStaus(String btn_content) {
        switch (btn_content) {
            case "??????":
                status = 1;
                setApprove(status);
                break;
            case "??????":
                status = 2;
                setApprove(status);
                break;
            case "??????":
                setWarehousing();
                break;
            case "??????":
                setVerify();
                break;
            case "??????":
                setAnnotation();
                break;
            case "????????????":
                setResubmit();
                break;
            case "??????":
                setClosed();
                break;
            case "??????":
                btReject.setText("????????????");
                break;
            case "????????????":
                btReject.setText("????????????");
                break;
        }
    }

    private void setAnnotation() {

        AlertDialog.Builder builder = new AlertDialog.Builder(
                OrderActivity.this);
        builder.setCancelable(false);
        builder.setMessage("???????????????");
        builder.setTitle("??????");
        final EditText editText = (EditText) (View.inflate(getApplicationContext(), R.layout.dialog_edit, null));
        builder.setView(editText);
        builder.setPositiveButton("??????",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface arg0, int arg1) {
                        new OrderManager().uploadDescription(orderBean.getGoodsCaseId() + "", editText.getText().toString(), new BaseNet.EntityCallback() {
                            @Override
                            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                                   //finish();
                                    /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                                    String time=format.format(new Date());
                                    OrderBean.GoodsCaseDescriptionVosBean descriptionVosBean = new OrderBean.GoodsCaseDescriptionVosBean();
                                    descriptionVosBean.setStaffName(loginBean.name);
                                    descriptionVosBean.setStaffId(orderBean.getGoodsCaseDescriptionVos().size()+1);
                                    descriptionVosBean.setCreateTime(time);
                                    descriptionVosBean.setDescription(editText.getText().toString());
                                    descriptionVosBean.setGoodsCaseDescId(orderBean.getGoodsCaseId());
                                    orderBean.getGoodsCaseDescriptionVos().add(descriptionVosBean);
                                    orderAdapter.notifyDataSetChanged();*/
                                    setResult(2);
                                    finish();
                                    /* initData();

                                    btApproval.setEnabled(true);
                                    btReject.setEnabled(true);*/
                                }
                            }
                        });
                    }
                });
        builder.setNegativeButton("??????",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                        btApproval.setEnabled(true);
                        btReject.setEnabled(true);

                    }
                });

        builder.create().show();
    }

    private void setClosed() {
        new OrderManager().closed(orderBean.getGoodsCaseId() + "", new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    ToastUtil.showToast("????????????");
                    initData();
                    orderAdapter.notifyDataSetChanged();
                    btApproval.setEnabled(true);
                    btReject.setEnabled(true);
                } else {
                    ToastUtil.showToast("????????????");
                }
            }
        });
    }

    private void setResubmit() {
        new OrderManager().resubmit(orderBean.getGoodsCaseId() + "", new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    ToastUtil.showToast("????????????");
                    initData();
                    orderAdapter.notifyDataSetChanged();
                    btApproval.setEnabled(true);
                    btReject.setEnabled(true);
                } else {
                    ToastUtil.showToast("????????????");
                }
            }
        });
    }

    private void setVerify() {
        new OrderManager().verify(orderBean.getGoodsCaseId() + "", new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    ToastUtil.showToast("????????????");
                    Intent intent = new Intent();
                    intent.putExtra("status","?????????");
                    setResult(1,intent);
                    finish();
                   /* initData();
                    orderAdapter.notifyDataSetChanged();
                    btApproval.setEnabled(true);
                    btReject.setEnabled(true);*/
                } else {
                    ToastUtil.showToast("????????????");
                }
            }
        });
    }

    private void setWarehousing() {

        new OrderManager().storage(orderBean.getGoodsCaseId() + "", new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    ToastUtil.showToast("????????????");
                    Intent intent = new Intent();
                    intent.putExtra("status","?????????");
                    setResult(1,intent);
                    finish();
                    /*initData();
                    orderAdapter.notifyDataSetChanged();
                    btApproval.setEnabled(true);
                    btReject.setEnabled(true);*/
                } else {
                    ToastUtil.showToast("????????????");
                }
            }
        });
    }

    private void setApprove(final int status) {
        if (status == 1){
            selectApprove(status, "");
        }else if (status == 2){
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    OrderActivity.this);
            builder.setCancelable(false);
            builder.setMessage("????????????");
            builder.setTitle("??????");
            final EditText editText = (EditText) (View.inflate(getApplicationContext(), R.layout.dialog_edit, null));
            builder.setView(editText);
            builder.setPositiveButton("??????",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface arg0, int arg1) {
                            selectApprove(status,editText.getText().toString());
                            btReject.setEnabled(true);
                            btApproval.setEnabled(true);
                        }
                    });
            builder.setNegativeButton("??????",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                            btReject.setEnabled(true);
                            btApproval.setEnabled(true);
                        }
                    });

            builder.create().show();
        }

    }

    private void selectApprove(final int status, String rejectReason) {
        new OrderManager().approve(orderBean.getGoodsCaseId() + "", status + "",rejectReason, new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    ToastUtil.showToast("????????????");
                    Intent intent = new Intent();
                    if (status ==1){
                        intent.putExtra("status","?????????");
                    }else if (status ==2){
                        intent.putExtra("status","????????????");
                    }
                    setResult(1,intent);
                    finish();

                   /* llorderbutton.setVisibility(View.GONE);
                    initData();
                    orderAdapter.notifyDataSetChanged();
                    btApproval.setEnabled(true);
                    btReject.setEnabled(true);*/
                } else {
                    ToastUtil.showToast("????????????");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0:
                initData();
                break;
            case 1:

                break;
        }
    }

    @Override
    public void onUpdataPositiveListener(DialogFragment dialog, OrderBean.GoodsCaseDetailsVosBean caseDetailsVosBean, int goodsCaseId, int position) {
        currentPosition = position;
        new OrderManager().editGoodsCaseid(goodsCaseId,caseDetailsVosBean, new BaseNet.EntityCallback() {
                    @Override
                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
                        if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                            //initData();
                            orderAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToast("????????????");
                        }
                    }
                });
    }


    @Override
    public void onUpdataNegativeListener(DialogFragment dialog) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK&& event.getRepeatCount() == 0){
            setResult(2);
            finish();
        }
        return false;
    }
}
