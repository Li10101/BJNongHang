package com.xyl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.PurchaseBean;


import java.util.List;

/**
 * Created by 47500 on 2018/4/28.
 */

public class ReviewFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private List<PurchaseBean.RecordsBean> recordsBeanList;
    private String reviewtype;

    public ReviewFragmentAdapter(Context mContext, List<PurchaseBean.RecordsBean> bean, String reviewtype) {
        this.mContext = mContext;
        this.recordsBeanList = bean;
        this.reviewtype = reviewtype;

    }

    @Override
    public int getCount() {
        return recordsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return recordsBeanList.get(i);
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
            revuewHolder.tv_purchase_type = (TextView)view.findViewById( R.id.tv_purchase_type );
            revuewHolder.tvState = (TextView)view.findViewById( R.id.tv_state );
            revuewHolder.btConfirm = (Button)view.findViewById( R.id.bt_confirm );
            view.setTag(revuewHolder);
        }else{
            revuewHolder = (RevuewHolder) view.getTag();
        }
        PurchaseBean.RecordsBean recordsBean = recordsBeanList.get(position);
       // Glide.with(mContext).load(NetContacts.getInstance().SERVER_URL+"/"+recordsBean.getProcessUrl()).into(revuewHolder.ivSearchState);
        if (reviewtype.equals("0")){
            revuewHolder.tvPriority.setText("编号:"+recordsBean.getGoodsCaseId());
            revuewHolder.tvPersion.setText("入库人:"+recordsBean.getPersonName());
            revuewHolder.tvTime.setText("入库时间:"+recordsBean.getDateTime());
            revuewHolder.tv_purchase_type.setVisibility(View.GONE);
            revuewHolder.tvState.setText(recordsBean.getStatusDisplay());
        }else{
            revuewHolder.tvPriority.setText("编号:"+recordsBean.getGoodsCaseId());
            revuewHolder.tvPersion.setText("出库人:"+recordsBean.getPersonName());
            revuewHolder.tvTime.setText("出库时间:"+recordsBean.getDateTime());
            revuewHolder.tv_purchase_type.setVisibility(View.GONE);
            revuewHolder.tvState.setText(recordsBean.getStatusDisplay());
        }

        return view;
    }

   class RevuewHolder{
       private ImageView ivSearchState;
       private TextView tvPriority;
       private TextView tvPersion;
       private TextView tvTime;
       private TextView tvState;
       private TextView tv_purchase_type;
       private Button btConfirm;
   }
}
