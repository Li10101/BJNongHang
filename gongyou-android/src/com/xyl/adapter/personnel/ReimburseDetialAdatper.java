package com.xyl.adapter.personnel;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.personnel.ProcurementDetails;
import com.xyl.domain.personnel.ReimburseDetail;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ReimburseDetialAdatper extends RecyclerView.Adapter<ReimburseDetialAdatper.EmerrgencyAddItemViewHolder> {



    private Context mContext;
    private List<ReimburseDetail> baoXiaoMingXiVos;


    public ReimburseDetialAdatper(Context context, List<ReimburseDetail> baoXiaoMingXiVos) {
        this.mContext = context;
        this.baoXiaoMingXiVos = baoXiaoMingXiVos;
    }

    @Override
    public EmerrgencyAddItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.approlval_emergen_item, null);
        EmerrgencyAddItemViewHolder viewHolder = new EmerrgencyAddItemViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmerrgencyAddItemViewHolder holder, final int position) {
        final ReimburseDetail details = baoXiaoMingXiVos.get(position);
        holder.ll_em4.setVisibility(View.GONE);
        holder.ll_em5.setVisibility(View.GONE);
        holder.ll_em6.setVisibility(View.GONE);
        holder.ll_em7.setVisibility(View.GONE);
        holder.tvMingxi.setText("明细("+(position+1)+")");
        holder.tv_goods_name.setText("报销类型");
        holder.tv_type.setText("报销金额");
        holder.tv_price.setText("费用明细");
        holder.etGoodsName.setText(details.getLeiBie());
        holder.etType.setText(details.getMoney());
        holder.etPrice.setText(details.getFeiYongMX());



    }

    @Override
    public int getItemCount() {
        return baoXiaoMingXiVos.size();
    }


    class EmerrgencyAddItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvMingxi;
        TextView tv_goods_name;
        TextView tv_type;
        TextView tv_price;
        TextView tv_zongjia;
        TextView etGoodsName;
        TextView etType;
        TextView etPrice;
        TextView etNumber;
        TextView etTotalPrice;
        TextView etPurpose;
        TextView etUnit;
        LinearLayout ll_em1;
        LinearLayout ll_em2;
        LinearLayout ll_em3;
        LinearLayout ll_em4;
        LinearLayout ll_em5;
        LinearLayout ll_em6;
        LinearLayout ll_em7;


        public EmerrgencyAddItemViewHolder(View itemView) {
            super(itemView);
            tvMingxi = itemView.findViewById(R.id.tv_mingxi);
            tv_price = itemView.findViewById(R.id.tv_price);
            etGoodsName = itemView.findViewById(R.id.et_goods_name);
            etType = itemView.findViewById(R.id.et_type);
            etPrice = itemView.findViewById(R.id.et_price);
            etNumber = itemView.findViewById(R.id.et_number);
            etUnit = itemView.findViewById(R.id.et_unit);
            etTotalPrice = itemView.findViewById(R.id.et_total_price);
            etPurpose = itemView.findViewById(R.id.et_purpose);
            ll_em1 = itemView.findViewById(R.id.ll_em1);
            ll_em2 = itemView.findViewById(R.id.ll_em2);
            ll_em3 = itemView.findViewById(R.id.ll_em3);
            ll_em4 = itemView.findViewById(R.id.ll_em4);
            ll_em5 = itemView.findViewById(R.id.ll_em5);
            ll_em6 = itemView.findViewById(R.id.ll_em6);
            ll_em7 = itemView.findViewById(R.id.ll_em7);


            tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_zongjia = itemView.findViewById(R.id.tv_zongjia);


        }
    }
}
