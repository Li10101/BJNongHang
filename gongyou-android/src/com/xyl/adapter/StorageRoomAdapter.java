package com.xyl.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.WorkInfoBean;
import com.xyl.ui.activity.StorageRoomActivity;
import com.xyl.utils.SharedPreferencesUtil;
import com.xyl.utils.UIUtils;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class StorageRoomAdapter extends RecyclerView.Adapter<StorageRoomAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<WorkInfoBean> resourceGrid;
    private final LayoutInflater from;

    public StorageRoomAdapter(Context context, ArrayList<WorkInfoBean> resourceGrid) {
        this.mContext = context;
        this.resourceGrid = resourceGrid;
        from = LayoutInflater.from(mContext);
    }

    @Override
    public StorageRoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = from.inflate(R.layout.item_function, null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(UIUtils.dp2px(80),UIUtils.dp2px(80)));//重点行
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(StorageRoomAdapter.ViewHolder holder, int position) {
        WorkInfoBean workInfoBean = resourceGrid.get(position);
        holder.iv_gvitem.setImageResource(resourceGrid.get(position).resId);
        holder.tv_gvitem.setText(resourceGrid.get(position).des);
        //holder.tv_notify_count.setNotifyCount((Integer)SharedPreferencesUtil.getParam(mContext, String.valueOf(position), 0));


    }

    @Override
    public int getItemCount() {
        return resourceGrid.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout l_item;
        ImageView iv_gvitem;
        TextView tv_gvitem;
        //TextView tv_notify_count;

        public ViewHolder(View itemView) {
            super(itemView);
            l_item = itemView.findViewById(R.id.ll_item);
            iv_gvitem = itemView.findViewById(R.id.iv_gvitem);
            tv_gvitem = itemView.findViewById(R.id.tv_gvitem);
            //tv_notify_count = itemView.findViewById(R.id.tv_notify_count);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemGridClick !=null){
                        onItemGridClick.OnItemGridClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    private OnItemGridClick onItemGridClick;
    public interface  OnItemGridClick{
        void OnItemGridClick(int position);
    }

    public void setOnItemGridClick(OnItemGridClick onItemGridClick) {
        this.onItemGridClick = onItemGridClick;
    }
}
