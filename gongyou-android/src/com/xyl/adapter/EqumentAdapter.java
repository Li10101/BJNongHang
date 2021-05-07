package com.xyl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.FindByEquipmentNoBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 47500 on 2017/8/28.
 */

public class EqumentAdapter extends BaseAdapter {

    private final Context context;
    private final List<FindByEquipmentNoBean.MaintainRecords> maintains;
    HashMap<Integer, View> lmap = new HashMap<Integer, View>();
    public EqumentAdapter(Context context, List<FindByEquipmentNoBean.MaintainRecords> maintainRecords) {
        super();
        this.context = context;
        this.maintains = maintainRecords;
    }

    @Override
    public int getCount() {
        return maintains.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (lmap.get(position)==null){
            convertView =  View.inflate(context, R.layout.equment_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_equmentCode = (TextView) convertView.findViewById(R.id.tv_equmentCode);
            viewHolder.tv_equmenttime = (TextView) convertView.findViewById(R.id.tv_equmenttime);
            lmap.put(position, convertView);
            convertView.setTag(viewHolder);

        }else{
            convertView = lmap.get(position);
            viewHolder  = (ViewHolder) convertView.getTag();
        }
        FindByEquipmentNoBean.MaintainRecords maintainss = maintains.get(position);
        viewHolder.tv_equmentCode.setText("工单编号:"+maintainss.repairCase);
        viewHolder.tv_equmenttime.setText("工单日期:"+maintainss.maintainDate);
        return convertView;
    }
    class ViewHolder{
        private TextView tv_equmentCode;
        private TextView tv_equmenttime;
    }
}
