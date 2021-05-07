package com.xyl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.domain.LibraryListBean;
import com.xyl.domain.NoticeDateBean;
import com.xyl.global.NetContacts;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 47500 on 2018/4/28.
 */

public class NoticeFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<NoticeDateBean> noticeDateBeans;


    public NoticeFragmentAdapter(Context mContext, List<NoticeDateBean> bean) {
        this.mContext = mContext;
        this.noticeDateBeans = bean;

    }

    @Override
    public int getCount() {
        return noticeDateBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return noticeDateBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        RevuewHolder revuewHolder = null;
        if (view ==null){
            revuewHolder = new RevuewHolder();
            view = View.inflate(mContext, R.layout.item_noticelist,null);
            revuewHolder.ivNoticeImage = (ImageView)view.findViewById( R.id.iv_notice_image );
            revuewHolder.tvNoticeTitle = (TextView)view.findViewById( R.id.tv_notice_title );
            revuewHolder.tvName = (TextView)view.findViewById( R.id.tv_name );
            revuewHolder.tvTime = (TextView)view.findViewById( R.id.tv_time );
            revuewHolder.tvLook = (TextView)view.findViewById( R.id.tv_look );
            view.setTag(revuewHolder);
        }else{
            revuewHolder = (RevuewHolder) view.getTag();
        }
        NoticeDateBean recordsBean = noticeDateBeans.get(position);
        Glide.with(mContext).load(NetContacts.getInstance()
                .BASE_IMAGE_URL+"/"+recordsBean.getPhoto()).into(revuewHolder.ivNoticeImage);
        if (recordsBean.isSee()){
            revuewHolder.tvNoticeTitle.setTextColor(mContext.getColor(R.color.cart_choice_color));
        }
        revuewHolder.tvNoticeTitle.setText(recordsBean.getBiaoTi());
        revuewHolder.tvName.setText(recordsBean.getBuildingName());
        revuewHolder.tvTime.setText(recordsBean.getStartTime());
        revuewHolder.tvLook.setText(recordsBean.getChaKan()+"");
        return view;
    }

   class RevuewHolder{
       private ImageView ivNoticeImage;
       private TextView tvNoticeTitle;
       private TextView tvName;
       private TextView tvTime;
       private TextView tvLook;

   }
}
