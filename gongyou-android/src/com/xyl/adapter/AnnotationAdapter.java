package com.xyl.adapter;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xyl.R;
import com.xyl.domain.OrderBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 47500 on 2018/5/11.
 */

public class AnnotationAdapter extends RecyclerView.Adapter<AnnotationAdapter.AnnotationHolder> {
    private Context mContext;
    private List<OrderBean.GoodsCaseDescriptionVosBean> descriptionVosBeen;
    public AnnotationAdapter(List<OrderBean.GoodsCaseDescriptionVosBean> descriptionVosBeen, Context mContext) {
        this.mContext = mContext;
        this.descriptionVosBeen = descriptionVosBeen;

    }

    @Override
    public AnnotationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_annotation,null);
        AnnotationHolder annotationHolder = new AnnotationHolder(view);
        return annotationHolder;
    }

    @Override
    public void onBindViewHolder(AnnotationHolder holder, int position) {


    }

    @Override
    public void onBindViewHolder(AnnotationHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);OrderBean.GoodsCaseDescriptionVosBean goodsCaseDescriptionVosBean = descriptionVosBeen.get(position);
        holder.tvAnnotationPeople.setText("批注人:"+goodsCaseDescriptionVosBean.getStaffName());
        holder.tvAnnotationContent.setText("批注内容:"+goodsCaseDescriptionVosBean.getDescription());
        holder.tvAnnotationTime.setText("批注时间:"+goodsCaseDescriptionVosBean.getCreateTime());

    }

    @Override
    public int getItemCount() {
        return descriptionVosBeen.size();
    }

    class AnnotationHolder extends RecyclerView.ViewHolder{
        private RelativeLayout rlLeft;
        private TextView tvAnnotationPeople;
        private TextView tvAnnotationContent;
        private TextView tvAnnotationTime;


        public AnnotationHolder(View itemView) {
            super(itemView);
            rlLeft = itemView.findViewById( R.id.rl_left );
            tvAnnotationPeople = itemView.findViewById( R.id.tv_annotation_people );
            tvAnnotationContent = itemView.findViewById( R.id.tv_annotation_content );
            tvAnnotationTime = itemView.findViewById( R.id.tv_annotation_time );
        }
    }
}
