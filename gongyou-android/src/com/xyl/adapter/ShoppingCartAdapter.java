package com.xyl.adapter;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.global.NetContacts;
import com.xyl.greendao.Cart;
import com.xyl.ui.view.AddSub;
import com.xyl.utils.GreendaoHelper;
import com.xyl.utils.ToastUtil;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by 47500 on 2017/8/24.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder>{
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox cbAll;
    private Context context;
    //private List<GoodsBean> goodsBeen;
    List<Cart> goodsBeen;
    private String check;
    private String selecter;

    private int flag;


    public ShoppingCartAdapter(Context mContext, List<Cart> goodsBeen, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll, int flag) {
        this.context = mContext;
        this.goodsBeen = goodsBeen;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;
        this.flag = flag;
        //showtotle();
        selectItem();
        checkAll();
    }

    private void selectItem() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                //1.根据位置找到修改的对象值
                Cart bean = goodsBeen.get(position);
                //2.取反
                boolean select = bean.getIsSelected();
                bean.setIsSelected(!select);
                //3.刷新勾选的头条木
                notifyItemChanged(position);
                //4.校验是否全选
                checkAll();
                //显示总价
                //showtotle();
            }
        });
    }

    public void checkAll() {
     if (goodsBeen!=null&&goodsBeen.size()>0){
         int number = 0;
         for (int i = 0; i < goodsBeen.size(); i++) {
             Cart bean = goodsBeen.get(i);
             boolean select = bean.getIsSelected();
             if (!select){
                 checkboxAll.setChecked(false);
                 cbAll.setChecked(false);
             }else{
                 number++;
             }
         }
         if (number==goodsBeen.size()){
             checkboxAll.setChecked(true);
             cbAll.setChecked(true);
         }else{
             checkboxAll.setChecked(false);
             cbAll.setChecked(false);
         }
     }
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = checkboxAll.isChecked();

                checkAll_none(isCheck);
                //showtotle();
            }
        });

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCheck = cbAll.isChecked();
                checkAll_none(isCheck);
            }
        });
    }

    public void checkAll_none(boolean isCheck) {
        if (goodsBeen!=null&&goodsBeen.size()>0){
            for (int i = 0; i < goodsBeen.size(); i++) {
                Cart bean = goodsBeen.get(i);
                bean.setIsSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shop_cart,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Cart bean = goodsBeen.get(position);
        boolean select = bean.getIsSelected();
        holder.cbGov.setChecked(select);
        String imageUrl = NetContacts.getInstance().BASE_IMAGE_URL + "/" + bean.getImageUrl();
        Glide.with(context).load(imageUrl).into(holder.ivGov);
        holder.tvDescGov.setText("商品:"+bean.getName());
        holder.tvPriceGov.setText("参考价:"+bean.getPrice());
        holder.numberAddSubView.setValue(bean.getSaleCount());

        holder.numberAddSubView.setOnNumberChangeListener(new AddSub.OnNumberChangeListener() {
            @Override
            public void onNumberChange(double value) {
                bean.setSaleCount(value);
                if (flag==0){
                    GreendaoHelper.updateCart(bean,bean.getProductId());
                }else if (flag ==1){
                    GreendaoHelper.updateTreasuryCart(bean,bean.getProductId());
                }
                notifyItemChanged(position);
                ToastUtil.showToast("修改成功");
                //showtotle();
            }
        });
    }

    public void showtotle() {
        double totle = 0;
        for (int i = 0; i < goodsBeen.size(); i++) {
            Cart  bean1 = goodsBeen.get(i);
            boolean isSelect = bean1.getIsSelected();
            if (isSelect){
                totle +=  Double.valueOf(bean1.getSaleCount())* Double.valueOf(bean1.getPrice());
            }
            totle +=  Double.valueOf(bean1.getSaleCount())* Double.valueOf(bean1.getPrice());
        }
        tvShopcartTotal.setText(totle+"");
    }
    @Override
    public int getItemCount() {
        return goodsBeen.size();
    }

    public void deleteData() {
        if (goodsBeen!=null && goodsBeen.size()>0){
            for (int i = 0; i < goodsBeen.size(); i++) {
                Cart bean = goodsBeen.get(i);
                boolean isSelect = bean.getIsSelected();
                if (isSelect){
                    goodsBeen.remove(bean);
                    if (flag ==0){
                        GreendaoHelper.deleteCart(bean.getProductId());
                    }else if (flag ==1){
                        GreendaoHelper.deleteTreasury(bean.getProductId());
                    } if (flag ==3){
                        GreendaoHelper.deleteTreasury(bean.getProductId());
                    }

                    notifyItemRemoved(i);
                    i--;
                }


            }
        }

    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbGov;
        private ImageView ivGov;
        private TextView tvDescGov;
        private TextView tvPriceGov;
        private AddSub numberAddSubView;
        public MyViewHolder(View itemView) {
            super(itemView);
            cbGov = (CheckBox)itemView.findViewById( R.id.cb_gov );
            ivGov = (ImageView)itemView.findViewById( R.id.iv_gov );
            tvDescGov = (TextView)itemView.findViewById( R.id.tv_desc_gov );
            tvPriceGov = (TextView)itemView.findViewById( R.id.tv_price_gov );
            numberAddSubView = (AddSub) itemView.findViewById( R.id.numberAddSubView );
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.OnItemClickListener(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void OnItemClickListener(int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
