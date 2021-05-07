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
import com.xyl.global.NetContacts;

import java.util.List;

/**
 * Created by 47500 on 2018/4/28.
 */

public class CheckListFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<LibraryListBean.RecordsBean> libraryList;


    public CheckListFragmentAdapter(Context mContext, List<LibraryListBean.RecordsBean> bean) {
        this.mContext = mContext;
        this.libraryList = bean;

    }

    @Override
    public int getCount() {
        return libraryList.size();
    }

    @Override
    public Object getItem(int i) {
        return libraryList.get(i);
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
            view = View.inflate(mContext, R.layout.item_review,null);
            revuewHolder.ivSearchState = (ImageView)view.findViewById( R.id.iv_state );
            revuewHolder.tvPriority = (TextView)view.findViewById( R.id.tv_priority );
            revuewHolder.tvPersion = (TextView)view.findViewById( R.id.tv_persion );
            revuewHolder.tvTime = (TextView)view.findViewById( R.id.tv_time );
            revuewHolder.tvState = (TextView)view.findViewById( R.id.tv_state );
            revuewHolder.btConfirm = (Button)view.findViewById( R.id.bt_confirm );
            view.setTag(revuewHolder);
        }else{
            revuewHolder = (RevuewHolder) view.getTag();
        }
        LibraryListBean.RecordsBean recordsBean = libraryList.get(position);
        String url = NetContacts.getInstance().SERVER_URL + "/" + recordsBean.getProcessUrl();
        Glide.with(mContext).load(url).into(revuewHolder.ivSearchState);
        revuewHolder.tvPriority.setText("编号:"+recordsBean.getStockTakingId());
        revuewHolder.tvPersion.setText("盘库人:"+recordsBean.getPersonName());
        revuewHolder.tvTime.setText("盘库时间:"+recordsBean.getDateTime());
        revuewHolder.tvState.setVisibility(View.GONE);
        return view;
    }

   class RevuewHolder{
       private ImageView ivSearchState;
       private TextView tvPriority;
       private TextView tvPersion;
       private TextView tvTime;
       private TextView tvState;
       private Button btConfirm;

   }
}
