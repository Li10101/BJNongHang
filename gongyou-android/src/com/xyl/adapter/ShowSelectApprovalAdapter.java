package com.xyl.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.ui.activity.SelectApprovalActivity;

import androidx.recyclerview.widget.RecyclerView;

public class ShowSelectApprovalAdapter extends RecyclerView.Adapter<ShowSelectApprovalAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater inflater;

    public ShowSelectApprovalAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);


    }

    @Override
    public ShowSelectApprovalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = inflater.inflate(R.layout.item_showapproval, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ShowSelectApprovalAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_select_title;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_select_title = itemView.findViewById(R.id.tv_select_title);
        }
    }
}
