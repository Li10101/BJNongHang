package com.xyl.adapter;

import android.content.Context;
import android.graphics.Color;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.domain.NoticeBean;
import com.xyl.domain.NoticeDateBean;
import com.xyl.global.NetContacts;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.ViewHolder> {



    private Context mContext;
    private List<NoticeDateBean> records;

    public NoticeListAdapter(Context baseContext, List<NoticeDateBean> records) {
        this.mContext = baseContext;
        this.records = records;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.item_noticelist, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoticeDateBean recordsBean = records.get(position);
        Glide.with(mContext).load(NetContacts.getInstance()
                .BASE_IMAGE_URL+"/"+recordsBean.getPhoto()).into(holder.ivNoticeImage);
        if (recordsBean.isSee()){
            holder.tvNoticeTitle.setTextColor(mContext.getColor(R.color.cart_choice_color));
        }
        holder.tvNoticeTitle.setText(recordsBean.getBiaoTi());
        holder.tvName.setText(recordsBean.getBuildingName());
        holder.tvTime.setText(recordsBean.getStartTime());
        holder.tvLook.setText(recordsBean.getChaKan()+"");
//        holder.tvTime.setText();
//        holder.tvPurchaseType.setText();
//        holder.tvState.setText();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.OnClickListener(position);
                    notifyItemChanged(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_notice_image)
        ImageView ivNoticeImage;
        @BindView(R.id.tv_notice_title)
        TextView tvNoticeTitle;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_look)
        TextView tvLook;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
