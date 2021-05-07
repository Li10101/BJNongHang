package com.xyl.adapter.personnel;

import android.content.Context;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.personnel.ProcurementDetails;
import com.xyl.utils.ToastUtil;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ApprolvalEmergencyAdatper extends RecyclerView.Adapter<ApprolvalEmergencyAdatper.EmerrgencyAddItemViewHolder> {

    private Context mContext;
    private List<ProcurementDetails> procurementDetailsList;
    private int type;

    public ApprolvalEmergencyAdatper(Context context, List<ProcurementDetails> procurementDetails, int type) {
        this.mContext = context;
        this.procurementDetailsList = procurementDetails;
        this.type = type;
    }


    @Override
    public EmerrgencyAddItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.approlval_emergen_item, null);
        EmerrgencyAddItemViewHolder viewHolder = new EmerrgencyAddItemViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmerrgencyAddItemViewHolder holder, final int position) {
        final ProcurementDetails details = procurementDetailsList.get(position);
        if (type==10){
            holder.ll_em5.setVisibility(View.GONE);
            holder.ll_em7.setVisibility(View.VISIBLE);
            holder.etPrice.setText(details.getMoney());
            holder.etTotalPrice.setText(details.getZongJia());
        }else if (type == 9){
            holder.ll_em7.setVisibility(View.GONE);
            holder.ll_em5.setVisibility(View.VISIBLE);
            holder.ll_em3.setVisibility(View.GONE);
            holder.tv_goods_name.setText("名称");
            holder.tv_type.setText("规格");
            holder.tv_zongjia.setText("价格");
            holder.etTotalPrice.setText(details.getMoney());
        }
        holder.tvMingxi.setText("明细("+(position+1)+")");
        holder.etGoodsName.setText(details.getName());
        holder.etType.setText(details.getGuiGe());
        holder.etNumber.setText(details.getNumber());
        holder.etUnit.setText(details.getDanWei());
        holder.etPurpose.setText(details.getYongTu());


    }

    @Override
    public int getItemCount() {
        return procurementDetailsList.size();
    }


    class EmerrgencyAddItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvMingxi;
        TextView tv_goods_name;
        TextView tv_type;
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
