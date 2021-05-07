package com.xyl.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.adapter.ShoppingCartAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.CasesBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.WorkInfoBean;
import com.xyl.greendao.Cart;
import com.xyl.greendao.TreasuryCartBean;
import com.xyl.net.OrderManager;
import com.xyl.utils.GreendaoHelper;
import com.xyl.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by li on 2017/1/4.
 */
public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ShoppingCartActivity.class.getSimpleName();
    private TextView tvEdit;
    private RecyclerView rvShoppCart;
    private LinearLayout llCheckAll;
    private LinearLayout ll_goShopping;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private TextView tv_goshopp;
    private static final int ACTION_EDIT = 1;
    private static final int ACTION_COMPLETE = 2;
    private ShoppingCartAdapter shoppingCartAdapter;
    private View view;
    private List<Cart> mCartList = new ArrayList<Cart>();
    private List<Cart> mCarts = new ArrayList<Cart>();
    private int businessId = 0;
    private RelativeLayout toolbarContentRlyt;
    private Button toolbarLeftBtn;
    private TextView toolbarLeftTv;
    private TextView toolbarTitleTv;
    private Button toolbarRightBtn;
    private TextView toolbarRightTv;
    private int flag;
    private int type;
    private String[] signFlag;
    private int purchase_type = 0;
    private CasesBean.Records caseRecord;
    private LoginBean loginBean;
    private String carts;
    private RadioGroup radioGroup;

   /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopp_cart);
        initView();
    }*/

    @Override
    protected void initData() {
        if (flag == 0) {
            mCartList = GreendaoHelper.getCartList(businessId);
        } else if (flag == 1 || flag == 3) {
            List<TreasuryCartBean> treasuryList = GreendaoHelper.getTreasuryList(businessId);
            for (int i = 0; i < treasuryList.size(); i++) {
                TreasuryCartBean treasuryCartBean = treasuryList.get(i);
                Cart cart = new Cart();
                cart.setName(treasuryCartBean.getName());
                cart.setProductId(treasuryCartBean.getProductId());
                cart.setSaleCount(treasuryCartBean.getSaleCount());
                cart.setPrice(treasuryCartBean.getPrice());
                cart.setProductType(treasuryCartBean.getProductType());
                cart.setBusinessId(treasuryCartBean.getBusinessId());
                cart.setIsSelected(treasuryCartBean.getIsSelected());
                cart.setImageUrl(treasuryCartBean.getImageUrl());
                mCartList.add(cart);
            }
        }

        if (mCartList != null && mCartList.size() > 0) {
            ll_goShopping.setVisibility(View.GONE);
            tvEdit.setVisibility(View.VISIBLE);
            llCheckAll.setVisibility(View.VISIBLE);
            rvShoppCart.setLayoutManager(new LinearLayoutManager(ShoppingCartActivity.this, LinearLayoutManager.VERTICAL, false));
            shoppingCartAdapter = new ShoppingCartAdapter(ShoppingCartActivity.this, mCartList, tvShopcartTotal, checkboxAll, cbAll, flag);
            rvShoppCart.setAdapter(shoppingCartAdapter);
            hideDelete();
        } else {
            empty_null();
        }
    }

    @Override
    protected void initView() {
        flag = getIntent().getIntExtra("flag", 0);
        type = getIntent().getIntExtra("type", 0);
        caseRecord = (CasesBean.Records) getIntent().getSerializableExtra("caseBean");
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        toolbarContentRlyt = findViewById(R.id.toolbar_content_rlyt);
        toolbarLeftBtn = findViewById(R.id.toolbar_left_btn);
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftTv = (TextView) findViewById(R.id.toolbar_left_tv);
        toolbarTitleTv = findViewById(R.id.toolbar_title_tv);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setText("购物车");

        toolbarRightBtn = findViewById(R.id.toolbar_right_btn);
        tvEdit = findViewById(R.id.toolbar_right_tv);
        tvEdit.setVisibility(View.VISIBLE);
        tvEdit.setText("编辑");
        tvEdit.setTextColor(getResources().getColor(R.color.bg_color));
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.bg_color));

        rvShoppCart = findViewById(R.id.rv_shoppCart);
        llCheckAll = findViewById(R.id.ll_check_all);
        checkboxAll = findViewById(R.id.checkbox_all);
        tvShopcartTotal = findViewById(R.id.tv_shopcart_total);
        btnCheckOut = findViewById(R.id.btn_check_out);
        llDelete = findViewById(R.id.ll_delete);
        cbAll = findViewById(R.id.cb_all);
        btnDelete = findViewById(R.id.btn_delete);
        ll_goShopping = findViewById(R.id.ll_goShopping);
        tv_goshopp = findViewById(R.id.tv_goshopp);

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        tv_goshopp.setOnClickListener(this);

        signFlag = new String[]{"外单", "内单", "其他"};

        if (flag == 0) {
            btnCheckOut.setText("生成入库单");
        } else if (flag == 1) {
            btnCheckOut.setText("生成出库单");
        } else if (flag == 3) {
            btnCheckOut.setText("备件单");
        }
        initListener();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopp_cart;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    private void initListener() {
        tvEdit.setTag(ACTION_EDIT);
        tvEdit.setText("编辑");
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) v.getTag();
                if (action == ACTION_EDIT) {
                    //切换完成状态
                    showDelete();
                } else {
                    //切换成编辑状态
                    hideDelete();
                }

            }
        });
        toolbarLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                if (flag == 0) {
                    finish();
                } else if (flag == 1) {
                    setResult(1);
                    finish();
                } else if (flag == 3) {
                    setResult(1);
                    finish();
                }

            }
        });
    }

    private void hideDelete() {
        //1.设置编辑
        tvEdit.setTag(ACTION_EDIT);
        //2.显示删除控件
        llDelete.setVisibility(View.GONE);
        //3.隐藏结算控件
        llCheckAll.setVisibility(View.VISIBLE);
        //4.设置文本为-完成
        tvEdit.setText("编辑");
        if (shoppingCartAdapter != null) {
            shoppingCartAdapter.checkAll_none(true);
            shoppingCartAdapter.checkAll();
            shoppingCartAdapter.showtotle();

        }

    }

    private void showDelete() {
        //1.设置完成
        tvEdit.setTag(ACTION_COMPLETE);
        //2.显示删除控件
        llDelete.setVisibility(View.VISIBLE);
        //3.隐藏结算控件
        llCheckAll.setVisibility(View.GONE);
        //4.设置文本为-完成
        tvEdit.setText("完成");
        //5.把所有的数据设置非选择状态
        if (shoppingCartAdapter != null) {
            shoppingCartAdapter.checkAll_none(false);
            shoppingCartAdapter.checkAll();
            shoppingCartAdapter.showtotle();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private void empty_null() {
        ll_goShopping.setVisibility(View.VISIBLE);
        llCheckAll.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);
        tvEdit.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            btnCheckOut.setEnabled(false);
            // Handle clicks for btnCheckOut
            for (int i = 0; i < mCartList.size(); i++) {
                Cart cart = mCartList.get(i);
                boolean select = cart.getIsSelected();
                if (select) {
                    mCarts.add(cart);
                }
            }
            if (mCartList.size() > 0 && mCarts.size() == 0) {
                ToastUtil.showToast("请选择需要购买的商品");
                return;
            } else if (mCartList.size() == 0) {
                ToastUtil.showToast("请添加商品到购物车");
                return;
            }
            Gson gson = new Gson();
            carts = gson.toJson(mCarts);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            if (flag == 0) {
                alertDialog.setTitle("确认生成入库单");
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
                        btnCheckOut.setEnabled(true);
                    }
                });
                alertDialog.show();
            } else if (flag == 1) {
                alertDialog.setTitle("确认生成出库单");
                View view = View.inflate(this, R.layout.item_domestic_foreign_layout, null);
                radioGroup = view.findViewById(R.id.rg_select_type);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                        switch (i) {
                            case R.id.rb_oneself_type:
                                type = 4;
                                break;
                            case R.id.rb_department_type:
                                type = 2;
                                break;
                            case R.id.rb_order_type:
                                type = 1;
                                break;
                        }
                    }
                });
                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                        switch (checkedRadioButtonId) {
                            case R.id.rb_oneself_type:
                                type = 4;
                                break;
                            case R.id.rb_department_type:
                                type = 2;
                                break;
                            case R.id.rb_order_type:
                                type = 1;
                                break;
                        }
                        if (type == 1){
                            Intent intent = new Intent(ShoppingCartActivity.this,WorkOrderActivity.class);
                            WorkInfoBean workInfoBean = new WorkInfoBean(0,"关联工单");
                            intent.putExtra("WorkInfoBean",workInfoBean);
                            intent.putExtra("LoginBean",loginBean);
                            intent.putExtra("Carts", carts);
                            intent.putExtra("purchase_type",type);
                            startActivityForResult(intent,10);
                        }else {
                            comit();
                        }
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        btnCheckOut.setEnabled(true);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            } else if (flag == 3) {
                alertDialog.setTitle("确认生成备件单");
                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        commitCar();
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        btnCheckOut.setEnabled(true);
                    }
                });
                alertDialog.show();
            }
        } else if (v == btnDelete) {
            //Handle clicks for btnDelete
            shoppingCartAdapter.deleteData();
            shoppingCartAdapter.checkAll();
            if (shoppingCartAdapter.getItemCount() == 0) {
                empty_null();
            }
        }
    }

    private void commitCar() {
        new OrderManager().caseapply(mCarts, caseRecord.repairCaseCode, new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    shoppingCartAdapter.deleteData();
                    shoppingCartAdapter.checkAll();
                    ToastUtil.showToast("订单已生成");
                        setResult(1);
                        finish();
//                    if (flag == 0) {
//                        setResult(0);
//                        finish();
//                    } else if (flag == 1) {
//                        setResult(1);
//                        finish();
//                    } else if (flag == 3) {
//                        setResult(3);
//                        finish();
//                    }
                    /*if (shoppingCartAdapter.getItemCount()==0){
                        empty_null();
                    }*/
                } else {
                    ToastUtil.showToast("生成订单失败");
                }
                btnCheckOut.setEnabled(true);
            }
        });
    }

    public void comit() {
        new OrderManager().commitCartData(carts, type, "",new BaseNet.EntityCallback() {
            @Override
            public void connectCallback(BaseNet.EntityrResult entityrResult) {
                if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                    shoppingCartAdapter.deleteData();
                    shoppingCartAdapter.checkAll();
                    ToastUtil.showToast("订单已生成");
                    setResult(1);
                    finish();
//                    if (flag==0){
//                        setResult(0);
//                        finish();
//                    }else if (flag ==1){
//                        setResult(1);
//                        finish();
//                    }
                    /*if (shoppingCartAdapter.getItemCount()==0){
                        empty_null();
                    }*/
                } else {
                    ToastUtil.showToast("生成订单失败");
                }
                btnCheckOut.setEnabled(true);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            btnCheckOut.setEnabled(true);
            return;
        }
        switch (resultCode){
            case 1:
                shoppingCartAdapter.deleteData();
                shoppingCartAdapter.checkAll();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //setResult(0);
    }
}
