package com.xyl.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.FirstBean;
import com.xyl.fragment.approvalFragment.FristFragment;
import com.xyl.fragment.approvalFragment.SecondFragment;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.ViewHolder> {

    private FragmentActivity context;
    private List<FirstBean> stringList;
    private SecondFragment secondFragment;

    public FirstAdapter(FragmentActivity mContext, List<FirstBean> stringList) {
        this.context = mContext;
        this.stringList = stringList;
    }


    @Override
    public FirstAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_showapproval, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FirstAdapter.ViewHolder holder, final int position) {
        FirstBean firstBean = stringList.get(position);
        holder.textView.setText(firstBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null){
                    onItemClick.OnItemClick(position);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tv_select_title);
        }
    }

    private OnItemClickListener onItemClick;
    public interface  OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClick(OnItemClickListener onItemClick) {
        this.onItemClick = onItemClick;
    }
}
