package com.xyl.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.domain.OrderBean;
import com.xyl.global.NetContacts;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by 47500 on 2018/5/3.
 */

public class DescribeAdapter extends RecyclerView.Adapter<DescribeAdapter.DescriHolder> {

    private Context mContext;
    private OrderBean orderBean;
    private final LayoutInflater layoutInflater;

    public DescribeAdapter(Context dContext, OrderBean orderBean) {
        this.mContext = dContext;
        this.orderBean = orderBean;
        layoutInflater = LayoutInflater.from(dContext);
    }

    @Override
    public DescriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.holder_item_describe,null);
        return new DescriHolder(view);
    }

    @Override
    public void onBindViewHolder(DescriHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()){
            OrderBean.GoodsCasePictureVosBeanX goodsCasePictureVosBeanX = orderBean.getGoodsCasePictureVos().get(position);
            if(position>=0){
                holder.tvDescriPerson.setVisibility(View.VISIBLE);
                holder.tvDescriTime.setVisibility(View.VISIBLE);
                holder.tvDescriContent.setVisibility(View.VISIBLE);
            }
            holder.tvDescriPerson.setText("描述人:"+goodsCasePictureVosBeanX.getUploaderName());
            holder.tvDescriTime.setText("描述时间:"+goodsCasePictureVosBeanX.getUploadTime());
            holder.tvDescriContent.setText("描述内容:"+goodsCasePictureVosBeanX.getDescription());
            int size = goodsCasePictureVosBeanX.getGoodsCasePictureVos().size();
            for (int i=0 ;i <size;i++){
                if (i==0){
                    holder.ivDescriImage1.setVisibility(View.VISIBLE);
                    String url = NetContacts.getInstance().BASE_IMAGE_URL + "/" + goodsCasePictureVosBeanX.getGoodsCasePictureVos().get(0).getPictureUrl();
                    Glide.with(mContext).load(url).into(holder.ivDescriImage1);
                }else if (i == 1){
                    holder.ivDescriImage2.setVisibility(View.VISIBLE);
                    String url1 = NetContacts.getInstance().BASE_IMAGE_URL+"/"+goodsCasePictureVosBeanX.getGoodsCasePictureVos().get(1).getPictureUrl();
                    Glide.with(mContext).load(url1).into(holder.ivDescriImage2);
                }else if (i == 2){
                    holder.ivDescriImage3.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(NetContacts.getInstance().BASE_IMAGE_URL+"/"+goodsCasePictureVosBeanX.getGoodsCasePictureVos().get(2).getPictureUrl()).into(holder.ivDescriImage3);
                }else if (i == 3){
                    holder.ivDescriImage4.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(NetContacts.getInstance().BASE_IMAGE_URL+"/"+goodsCasePictureVosBeanX.getGoodsCasePictureVos().get(3).getPictureUrl()).into(holder.ivDescriImage4);

                }else if (i == 4){
                    holder.ivDescriImage5.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(NetContacts.getInstance().BASE_IMAGE_URL+"/"+goodsCasePictureVosBeanX.getGoodsCasePictureVos().get(4).getPictureUrl()).into(holder.ivDescriImage5);

                }else if (i == 5){
                    holder.ivDescriImage6.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(NetContacts.getInstance().BASE_IMAGE_URL+"/"+goodsCasePictureVosBeanX.getGoodsCasePictureVos().get(5).getPictureUrl()).into(holder.ivDescriImage6);

                }
            }
        }
    }

    @Override
    public void onBindViewHolder(DescriHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return orderBean.getGoodsCasePictureVos().size();
    }

    class DescriHolder extends RecyclerView.ViewHolder{
        private TextView tvDescriPerson;
        private TextView tvDescriTime;
        private TextView tvDescriContent;
        private ImageView ivDescriImage1;
        private ImageView ivDescriImage2;
        private ImageView ivDescriImage3;
        private ImageView ivDescriImage4;
        private ImageView ivDescriImage5;
        private ImageView ivDescriImage6;




        public DescriHolder(View itemView) {
            super(itemView);
            tvDescriPerson = itemView.findViewById( R.id.tv_descri_person );
            tvDescriTime = itemView.findViewById( R.id.tv_descri_time );
            tvDescriContent = itemView.findViewById( R.id.tv_descri_content );
            ivDescriImage1 = itemView.findViewById( R.id.iv_descri_image1 );
            ivDescriImage2 = itemView.findViewById( R.id.iv_descri_image2 );
            ivDescriImage3 = itemView.findViewById( R.id.iv_descri_image3 );
            ivDescriImage4 = itemView.findViewById( R.id.iv_descri_image4 );
            ivDescriImage5 = itemView.findViewById( R.id.iv_descri_image5 );
            ivDescriImage6 = itemView.findViewById( R.id.iv_descri_image6 );
        }
    }
}
