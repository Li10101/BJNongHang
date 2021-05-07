package com.xyl.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.GoodsCaseDetailsBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class OrderShopAdapter extends RecyclerView.Adapter<OrderShopAdapter.ViewHolder> {

    private Context mContext;
    private List<GoodsCaseDetailsBean> goodsCaseDetailsVos;
    private final LayoutInflater from;

    public OrderShopAdapter(Context mContext, List<GoodsCaseDetailsBean> goodsCaseDetailsVos) {
        this.mContext = mContext;
        this.goodsCaseDetailsVos = goodsCaseDetailsVos;
        from = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = from.inflate(R.layout.item_order_shop_adaptr, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsCaseDetailsBean goodsCaseDetailsBean = goodsCaseDetailsVos.get(position);
        holder.tv_shop.setText("商品名称:"+goodsCaseDetailsBean.getName()+"商品数量:"+goodsCaseDetailsBean.getAmount());
    }

    @Override
    public int getItemCount() {
        return goodsCaseDetailsVos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_shop;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_shop = itemView.findViewById(R.id.tv_shop);
        }
    }
}
