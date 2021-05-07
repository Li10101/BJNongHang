package com.xyl.adapter.personnel;

import android.content.Context;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
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
import butterknife.BindView;

public class EmergencyCreateAdatper extends RecyclerView.Adapter<EmergencyCreateAdatper.EmerrgencyAddItemViewHolder> {



    private Context mContext;
    private List<ProcurementDetails> procurementDetailsList;
    private int type;

    public EmergencyCreateAdatper(Context context, List<ProcurementDetails> procurementDetails,int type) {
        this.mContext = context;
        this.procurementDetailsList = procurementDetails;
        this.type = type;
    }

    @Override
    public EmerrgencyAddItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.emergency_create_item, null);
        EmerrgencyAddItemViewHolder viewHolder = new EmerrgencyAddItemViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmerrgencyAddItemViewHolder holder, final int position) {
        final ProcurementDetails details = procurementDetailsList.get(position);
        if (type==1){
            holder.ll_unit.setVisibility(View.GONE);
            holder.ll_purpose.setVisibility(View.VISIBLE);
            holder.etPrice.setText(details.getMoney());
            holder.etTotalPrice.setText(details.getZongJia());
        }else if (type == 2){
            holder.ll_purpose.setVisibility(View.GONE);
            holder.ll_unit.setVisibility(View.VISIBLE);
            holder.ll_danjia.setVisibility(View.GONE);
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

        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (procurementDetailsList.size()==1){
                    ToastUtil.showToast("不能删除");
                }else{
                    procurementDetailsList.remove(position);
                    notifyDataSetChanged();
                }

            }
        });

        holder.etGoodsName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setName(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.etType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setGuiGe(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setMoney(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        holder.etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setNumber(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.etUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setDanWei(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.etTotalPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (type ==1){
                    details.setZongJia(s+"");
                }else{
                    details.setMoney(s+"");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.etPurpose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setYongTu(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return procurementDetailsList.size();
    }

    public void addData(ProcurementDetails details) {
        procurementDetailsList.add(details);
    }

    public List<ProcurementDetails>  getEmergencyData() {
        return procurementDetailsList;

    }


    class EmerrgencyAddItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvMingxi;
        TextView tv_delete;
        TextView tv_goods_name;
        TextView tv_type;
        TextView tv_zongjia;
        EditText etGoodsName;
        EditText etType;
        EditText etPrice;
        EditText etNumber;
        EditText etTotalPrice;
        EditText etPurpose;
        EditText etUnit;
        LinearLayout ll_unit;
        LinearLayout ll_purpose;
        LinearLayout ll_danjia;
        public EmerrgencyAddItemViewHolder(View itemView) {
            super(itemView);
            tvMingxi = itemView.findViewById(R.id.tv_mingxi);
            tv_delete = itemView.findViewById(R.id.tv_delete);
            etGoodsName = itemView.findViewById(R.id.et_goods_name);
            etType = itemView.findViewById(R.id.et_type);
            etPrice = itemView.findViewById(R.id.et_price);
            etNumber = itemView.findViewById(R.id.et_number);
            etUnit = itemView.findViewById(R.id.et_unit);
            etTotalPrice = itemView.findViewById(R.id.et_total_price);
            etPurpose = itemView.findViewById(R.id.et_purpose);
            ll_unit = itemView.findViewById(R.id.ll_unit);
            ll_purpose = itemView.findViewById(R.id.ll_purpose);
            ll_danjia = itemView.findViewById(R.id.ll_danjia);
            tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_zongjia = itemView.findViewById(R.id.tv_zongjia);


        }
    }
}
