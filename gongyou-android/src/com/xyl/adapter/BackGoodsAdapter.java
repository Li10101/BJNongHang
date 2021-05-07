package com.xyl.adapter;

import android.content.Context;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.GoodsCaseDetailsBean;
import com.xyl.ui.activity.GetBackGoodsActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class BackGoodsAdapter extends RecyclerView.Adapter<BackGoodsAdapter.ViewHolder> {

    private Context mContext;
    private List<GoodsCaseDetailsBean> goodsCaseDetailsVos;
    public BackGoodsAdapter(Context mCntext, List<GoodsCaseDetailsBean> goodsCaseDetailsVos) {
        this.mContext = mCntext;
        this.goodsCaseDetailsVos = goodsCaseDetailsVos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_back_goods, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final GoodsCaseDetailsBean goodsCaseDetailsBean = goodsCaseDetailsVos.get(position);
        holder.goods_name.setText(goodsCaseDetailsBean.getName());
        holder.goods_aount.setText(goodsCaseDetailsBean.getAmount()+"");
        goodsCaseDetailsBean.setAmount(0);
        holder.et_count.setText("0");
        holder.et_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String num = holder.et_count.getText().toString();
                int number = 0;
                if (num.isEmpty()){
                    number = 0;
                }else {
                    number = Integer.parseInt(holder.et_count.getText().toString());
                }

                goodsCaseDetailsBean.setAmount(number);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goodsCaseDetailsVos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView goods_name;
        private TextView goods_aount;
        private EditText et_count;
        public ViewHolder(View itemView) {
            super(itemView);
            goods_name = itemView.findViewById(R.id.goods_name);
            et_count = itemView.findViewById(R.id.et_count);
            goods_aount = itemView.findViewById(R.id.goods_aount);
        }
    }

    public List<GoodsCaseDetailsBean> getGoodsCaseDetailsVos(){
        return goodsCaseDetailsVos;
    }

}
