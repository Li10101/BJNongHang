package com.xyl.adapter;
import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xyl.R;
import com.xyl.domain.OrderBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 47500 on 2018/5/8.
 */

public class TimeAxisAdapter extends RecyclerView.Adapter<TimeAxisAdapter.TimeAxisItemHolder> {

    private Context mContext;
    private OrderBean orderBean;
    public TimeAxisAdapter(Context mContext, OrderBean orderBean) {
        this.mContext = mContext;
        this.orderBean = orderBean;
    }

    @Override
    public TimeAxisItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_timeaxis, null);
        TimeAxisItemHolder timeAxisItemHolder = new TimeAxisItemHolder(inflate);
        return timeAxisItemHolder;
    }

    @Override
    public void onBindViewHolder(TimeAxisItemHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(TimeAxisItemHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()){
            OrderBean.GoodsCaseTraceVosBean goodsCaseTraceVosBean = orderBean.getGoodsCaseTraceVos().get(position);
            if (position == 0 && orderBean.getGoodsCaseTraceVos().size()-1==0){
                holder.tvLineTop.setVisibility(View.INVISIBLE);
                holder.tvLineBottom.setVisibility(View.INVISIBLE);
            }else if (position ==orderBean.getGoodsCaseTraceVos().size()-1){
                holder.tvLineBottom.setVisibility(View.INVISIBLE);
            }if (position == 0){
                holder.tvLineTop.setVisibility(View.INVISIBLE);
            }
            holder.tvOperatorStatus.setText(goodsCaseTraceVosBean.getAction());
            holder.tvOperatorPeople.setText(goodsCaseTraceVosBean.getStaffName());
            holder.tvOperatorTime.setText(goodsCaseTraceVosBean.getExecuteTime());
            holder.tvOperatorStatusAction.setText(goodsCaseTraceVosBean.getStatus());
            holder.tvOperatorDescription.setText(goodsCaseTraceVosBean.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return orderBean.getGoodsCaseTraceVos().size();
    }

    class TimeAxisItemHolder extends RecyclerView.ViewHolder{
        private RelativeLayout rlCenter;
        private View tvLineTop;
        private ImageView ivDot;
        private View tvLineBottom;
        private RelativeLayout rlLeft;
        private TextView tvOperatorStatus;
        private TextView tvOperatorPeople;
        private TextView tvOperatorTime;
        private TextView tvOperatorStatusAction;
        private TextView tvOperatorDescription;

        public TimeAxisItemHolder(View itemView) {
            super(itemView);
            rlCenter = itemView.findViewById( R.id.rl_center );
            tvLineTop = itemView.findViewById( R.id.tv_line_top );
            ivDot = itemView.findViewById( R.id.iv_dot );
            tvLineBottom = itemView.findViewById( R.id.tv_line_bottom );
            rlLeft = itemView.findViewById( R.id.rl_left );
            tvOperatorStatus = itemView.findViewById( R.id.tv_operator_status );
            tvOperatorPeople = itemView.findViewById( R.id.tv_operator_people );
            tvOperatorTime = itemView.findViewById( R.id.tv_operator_time );
            tvOperatorStatusAction = itemView.findViewById( R.id.tv_operator_status_action );
            tvOperatorDescription = itemView.findViewById( R.id.tv_operator_description );
        }
    }
}
