package com.xyl.adapter.answer;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.OrderAdapter;
import com.xyl.domain.OrderBean;
import com.xyl.domain.answer.AnswerBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.ViewHolder> {


    private Context mContext;
    private List<AnswerBean.RecordsBean> records;

    public AnswerListAdapter(Context baseContext, List<AnswerBean.RecordsBean> records) {
        this.mContext = baseContext;
        this.records = records;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_answerlist, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AnswerBean.RecordsBean recordsBean = records.get(position);
        holder.tvPriority.setText(recordsBean.getName());
        holder.tvPersion.setText(recordsBean.getDateTime());
//        holder.tvTime.setText();
//        holder.tvPurchaseType.setText();
       // holder.tvState.setText();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null){
                    onClickListener.OnClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_priority)
        TextView tvPriority;
        @BindView(R.id.tv_persion)
        TextView tvPersion;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.bt_confirm)
        Button btConfirm;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnClickListener {
        void OnClickListener(int position);
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
