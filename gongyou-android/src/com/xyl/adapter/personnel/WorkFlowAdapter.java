package com.xyl.adapter.personnel;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.personnel.WorkFlowBean;
import com.xyl.ui.activity.LeaveActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class WorkFlowAdapter extends RecyclerView.Adapter<WorkFlowAdapter.ViewHolder> {


    private Context context;
    private WorkFlowBean.RecordsBean records;
    public WorkFlowAdapter(Context context, WorkFlowBean.RecordsBean records) {
        this.context = context;
        this.records = records;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_work_flow, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WorkFlowBean.RecordsBean.LiuZhuansBean liuZhuansBean = records.getLiuZhuans().get(position);
        holder.work_flow_name.setText(liuZhuansBean.getShenPiRName());

    }

    @Override
    public int getItemCount() {
        return records.getLiuZhuans().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView work_flow_name;
        private ImageView work_flow_flag;
        public ViewHolder(View itemView) {
            super(itemView);
            work_flow_name = itemView.findViewById(R.id.work_flow_name);
            work_flow_flag = itemView.findViewById(R.id.work_flow_flag);
        }
    }
}
