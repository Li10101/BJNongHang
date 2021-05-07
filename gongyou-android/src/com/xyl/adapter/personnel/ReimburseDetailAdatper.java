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
import com.xyl.domain.personnel.ReimburseDetail;

import java.math.BigDecimal;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ReimburseDetailAdatper extends RecyclerView.Adapter<ReimburseDetailAdatper.ReimburseAddItemViewHolder> {

    private Context mContext;
    private List<ReimburseDetail> reimburseDetailList;
    private TextView reimburseAllView;
    BigDecimal money ;
    public ReimburseDetailAdatper(Context context, List<ReimburseDetail> reimburseDetailList,TextView reimburseAll) {
        this.mContext = context;
        this.reimburseDetailList = reimburseDetailList;
        this.reimburseAllView = reimburseAll;
    }

    @Override
    public ReimburseAddItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_add_reimbursementdetail, null);
        ReimburseAddItemViewHolder viewHolder = new ReimburseAddItemViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReimburseAddItemViewHolder holder, int position) {

        final ReimburseDetail details = reimburseDetailList.get(position);
        holder.et_reimbur_type.setText(details.getLeiBie());
        holder.et_reimbur_price.setText(details.getMoney());
        holder.et_cost_detail.setText(details.getFeiYongMX());
        holder.et_reimbur_type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setLeiBie(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.et_reimbur_price.addTextChangedListener(new TextWatcher() {
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
        holder.et_cost_detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                details.setFeiYongMX(s+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return reimburseDetailList.size();
    }

    public void addData(ReimburseDetail details) {
        reimburseDetailList.add(details);
    }

    public List<ReimburseDetail>  getReimburseData() {
        return reimburseDetailList;

    }


    class ReimburseAddItemViewHolder extends RecyclerView.ViewHolder {

        private EditText et_reimbur_type;
        private EditText et_reimbur_price;
        private EditText et_cost_detail;

        public ReimburseAddItemViewHolder(View itemView) {
            super(itemView);
            et_reimbur_type = itemView.findViewById(R.id.et_reimbur_type);
            et_reimbur_price = itemView.findViewById(R.id.et_reimbur_price);
            et_cost_detail = itemView.findViewById(R.id.et_cost_detail);



        }
    }
}
