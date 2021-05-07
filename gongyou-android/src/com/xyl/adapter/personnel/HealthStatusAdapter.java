package com.xyl.adapter.personnel;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.GoBackBean;
import com.xyl.domain.HealthStatusBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthStatusAdapter extends RecyclerView.Adapter<HealthStatusAdapter.GoBackViewHolder> {

    private Context mContext;
    private List<HealthStatusBean.RecordsBean> recordsBeanList;

    public HealthStatusAdapter(Context context, List<HealthStatusBean.RecordsBean> recordsBeanList) {
        this.mContext = context;
        this.recordsBeanList = recordsBeanList;
    }

    @Override
    public GoBackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_go_back, null);
        GoBackViewHolder purchaseViewHolder = new GoBackViewHolder(inflate);
        return purchaseViewHolder;
    }

    @Override
    public void onBindViewHolder(GoBackViewHolder holder, final int position) {

        HealthStatusBean.RecordsBean recordsBean = recordsBeanList.get(position);
        holder.tvPriority.setText("楼宇:"+recordsBean.getBuildingName());
        holder.tvPersion.setText("填报人:"+recordsBean.getShenQingRName());
        holder.tvTime.setText("填报时间:"+recordsBean.getDateTime());
        holder.tvPurchaseType.setVisibility(View.GONE);
        holder.tvState.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClickListener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return recordsBeanList.size();
    }

    class GoBackViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_state)
        ImageView ivState;
        @BindView(R.id.tv_priority)
        TextView tvPriority;
        @BindView(R.id.tv_persion)
        TextView tvPersion;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_purchase_type)
        TextView tvPurchaseType;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.bt_confirm)
        Button btConfirm;
        public GoBackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
       void onItemClickListener(int position);
    }
}
