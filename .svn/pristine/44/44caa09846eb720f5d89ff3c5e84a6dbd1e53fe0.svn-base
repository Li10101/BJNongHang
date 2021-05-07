package com.xyl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.FindByAssetNoBean;

/**
 * Created by 47500 on 2017/10/16.
 */

public class AssetDesAdapter extends BaseAdapter {
    private Context mContext;
    private FindByAssetNoBean.RecordsBean recordsBeen;
    public AssetDesAdapter(FindByAssetNoBean.RecordsBean recordBean, Context mContext) {
        this.recordsBeen = recordBean;
        this.mContext = mContext;

    }

    @Override
    public int getCount() {
        return recordsBeen.getUserEquiHistoryVos().size();
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
            convertView = View.inflate(mContext, R.layout.view_asset_content,null);
            viewHolder.tv_content_worker = (TextView) convertView.findViewById(R.id.tv_content_asset);
            viewHolder.tv_content_time = (TextView) convertView.findViewById(R.id.tv_content_time_asset);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FindByAssetNoBean.RecordsBean.UserEquiHistoryVosBean userEquiHistoryVosBean = this.recordsBeen.getUserEquiHistoryVos().get(i);
        if (userEquiHistoryVosBean!=null){
            viewHolder.tv_content_worker.setVisibility(View.VISIBLE);
            viewHolder.tv_content_time.setVisibility(View.VISIBLE);
        }
        viewHolder.tv_content_worker.setText("变更人:"+userEquiHistoryVosBean.getLoginUserName());
        viewHolder.tv_content_time.setText("变更时间:"+userEquiHistoryVosBean.getUpdateTime());
        return convertView;
    }

    class ViewHolder{
        TextView tv_content_worker;
        TextView tv_content_time;

    }
}
