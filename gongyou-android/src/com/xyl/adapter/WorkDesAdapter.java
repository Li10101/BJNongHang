package com.xyl.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.domain.DataBean;
import com.xyl.domain.Pictures;
import com.xyl.global.NetContacts;

import java.util.List;

/**
 * Created by 47500 on 2017/10/16.
 */

public class WorkDesAdapter extends BaseAdapter {
    private Context mContext;
    private List<Pictures> pictures ;
    public WorkDesAdapter(DataBean dataBean, Context mContext) {
        this.pictures = dataBean.pictures;
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.view_worker_content,null);
            viewHolder.tv_content_worker = (TextView) convertView.findViewById(R.id.tv_content_worker);
            viewHolder.tv_content_time = (TextView) convertView.findViewById(R.id.tv_content_time);
            viewHolder.tv_content_des = (TextView) convertView.findViewById(R.id.tv_content_des);
            viewHolder.iv_content_photo = (ImageView) convertView.findViewById(R.id.iv_content_photo);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Pictures pictures = this.pictures.get(i);
        if (pictures!=null){
            viewHolder.tv_content_worker.setVisibility(View.VISIBLE);
            viewHolder.tv_content_time.setVisibility(View.VISIBLE);
            viewHolder.tv_content_des.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(pictures.pictureUrl)){
            viewHolder.iv_content_photo.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(NetContacts.getInstance()
                    .BASE_IMAGE_URL+"/"+pictures.pictureUrl).into(viewHolder.iv_content_photo);
        }else{
            viewHolder.iv_content_photo.setVisibility(View.GONE);
        }
        viewHolder.tv_content_worker.setText("姓名:"+pictures.uploader);
        viewHolder.tv_content_des.setText("描述:"+pictures.description);
        viewHolder.tv_content_time.setText("时间:"+pictures.uploadTime);
        return convertView;
    }

    class ViewHolder{
        TextView tv_content_worker;
        TextView tv_content_time;
        TextView tv_content_des;
        ImageView iv_content_photo;

    }
}
