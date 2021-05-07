package com.xyl.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.adapter.BackGoodsAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.DataBean;
import com.xyl.domain.GoodsCaseDetailsBean;
import com.xyl.net.OrderManager;
import com.xyl.net.OrderStream;
import com.xyl.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetBackGoodsActivity extends BaseActivity {

    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.toolbar_left_tv)
    TextView toolbarLeftTv;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.toolbar_right_btn)
    Button toolbarRightBtn;
    @BindView(R.id.toolbar_right_tv)
    TextView toolbarRightTv;
    @BindView(R.id.toolbar_content_rlyt)
    RelativeLayout toolbarContentRlyt;
    @BindView(R.id.rv_back_goods)
    RecyclerView rvBackGoods;
    @BindView(R.id.bt_qveren)
    Button btQveren;
    private DataBean dataBean;
    private BackGoodsAdapter backGoodsAdapter;

    @Override
    protected void initData() {
        rvBackGoods.setLayoutManager(new LinearLayoutManager(this));
        backGoodsAdapter = new BackGoodsAdapter(this, dataBean.goodsCaseDetailsVos);
        rvBackGoods.setAdapter(backGoodsAdapter);
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarTitleTv.setVisibility(View.VISIBLE);
        toolbarTitleTv.setTextColor(getResources().getColor(R.color.cart_choice_color));
        toolbarTitleTv.setText("退料");
        dataBean = (DataBean) getIntent().getSerializableExtra("dataBean");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_get_back_goods;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @OnClick()
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_left_btn,R.id.bt_qveren})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.toolbar_left_btn:
                setResult(3);
                finish();
                break;
            case R.id.bt_qveren:
                List<GoodsCaseDetailsBean> goodsCaseDetailsVos = backGoodsAdapter.getGoodsCaseDetailsVos();
                List<GoodsCaseDetailsBean> goodsCaseDetails = new ArrayList<>();
                for (int i = 0; i < goodsCaseDetailsVos.size(); i++) {
                    GoodsCaseDetailsBean goodsCaseDetailsBean = goodsCaseDetailsVos.get(i);
                    if (goodsCaseDetailsBean.getAmount()!=0 ||goodsCaseDetailsBean.getAmount()>0){
                        goodsCaseDetails.add(goodsCaseDetailsBean);
                    }
                }

                new OrderStream().TUILIAO(2, dataBean.repairCaseCode, goodsCaseDetails, new BaseNet.EntityCallback() {
                    @Override
                    public void connectCallback(BaseNet.EntityrResult entityrResult) {
                        if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                            ToastUtil.showToast("成功");
                            setResult(3);
                            finish();
                        }
                    }
                });
                break;
        }
    }
}
