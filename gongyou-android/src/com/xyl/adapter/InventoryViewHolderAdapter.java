package com.xyl.adapter;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.domain.LoginBean;
import com.xyl.domain.OrderBean;
import com.xyl.global.NetContacts;
import com.xyl.ui.view.AddSub;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


/**
 * Created by 47500 on 2018/5/3.
 */

public class InventoryViewHolderAdapter extends RecyclerView.Adapter<InventoryViewHolderAdapter.InventoryViewHolder> {

    private Context mContext;
    private OrderBean orderBean;
    private OrderBean.GoodsCaseDetailsVosBean goodsCaseDetailssBean;
    private List<OrderBean.GoodsCaseDetailsVosBean> goodsCaseDetailsVosBeens;
    private boolean isShow;
    private LoginBean loginBean;

    public InventoryViewHolderAdapter(Context iContext, OrderBean orderBean, LoginBean loginBean) {
        this.mContext = iContext;
        this.orderBean = orderBean;
        this.loginBean = loginBean;
    }

    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_order, null);
        InventoryViewHolder orderHolder = new InventoryViewHolder(view);
        return orderHolder;
    }

    @Override
    public void onBindViewHolder(InventoryViewHolder holder, final int position) {


    }

    @Override
    public void onBindViewHolder(InventoryViewHolder holder, final int position, List<Object> payloads) {

        if (payloads.isEmpty()) {
            goodsCaseDetailssBean = orderBean.getGoodsCaseDetailsVos().get(position);
            holder.tvKucun.setText("库存:");
            holder.tvPinpai.setText("品牌:" +goodsCaseDetailssBean.getKuFangTypeDisplay());
            holder.tvCategory.setText("规格:" + goodsCaseDetailssBean.getGuiGe());
            holder.tvXinghao.setText("型号:" + goodsCaseDetailssBean.getXingHao());
            holder.tvName.setText("名称:" + goodsCaseDetailssBean.getName());
            holder.tvPrice.setText("价格:" + goodsCaseDetailssBean.getUnitPrice());
            holder.tvCount.setText("数量:" + goodsCaseDetailssBean.getAmount() + goodsCaseDetailssBean.getUnit());
            holder.order_add_sub.setVisibility(View.GONE);
            /*holder.order_add_sub.setValue(goodsCaseDetailssBean.getAmount());
            holder.order_add_sub.setMinValue(0);*/
            String imagePath = NetContacts.getInstance().BASE_IMAGE_URL + "/" + goodsCaseDetailssBean.getPictureUrl();
            Glide.with(mContext).load(R.drawable.icon_stub).into(holder.ivOrderPicur);
            /*if (orderBean.getProcess()>=3){
                holder.order_add_sub.setVisibility(View.GONE);
            }*/
//            holder.order_add_sub.setOnNumberChangeListener(new AddSub.OnNumberChangeListener() {
//                @Override
//                public void onNumberChange(double value) {
//                    OrderBean.GoodsCaseDetailsVosBean detailsVosBean = orderBean.getGoodsCaseDetailsVos().get(position);
//                    detailsVosBean.setAmount(value);
//                    if (goodsCaseDetailsVosBeens == null){
//                        goodsCaseDetailsVosBeens = new ArrayList<>();
//                    }
//                    if (onValueChangeListener !=null){
//                        if (goodsCaseDetailsVosBeens.size() == 0){
//                            goodsCaseDetailsVosBeens.add(detailsVosBean);
//                        }
//                        else
//                        {
//                            int size = goodsCaseDetailsVosBeens.size();
//                            for (int i=0 ;i < size ;i++){
//                                boolean contains = goodsCaseDetailsVosBeens.contains(detailsVosBean);
//                                int goodsCaseDetailsId = goodsCaseDetailsVosBeens.get(i).getGoodsCaseDetailsId();
//                                int goodsCaseDetailsId1 = detailsVosBean.getGoodsCaseDetailsId();
//                                if (contains && goodsCaseDetailsId ==goodsCaseDetailsId1){
//                                    goodsCaseDetailsVosBeens.set(i,detailsVosBean);
//                                }else if (contains==false){
//                                    goodsCaseDetailsVosBeens.add(detailsVosBean);
//                                }
//
//                            }
//                        }
//                        onValueChangeListener.OnValueChange(goodsCaseDetailsVosBeens);
//
//                    }
//                }
//            });

        }


    }

    @Override
    public int getItemCount() {
        return orderBean.getGoodsCaseDetailsVos().size();
    }

    public void changitemAddSub(boolean isManager) {
        this.isShow = isManager;
        notifyDataSetChanged();
    }

    class InventoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKucun;
        private TextView tvPinpai;
        private TextView tvXinghao;

        private ImageView ivOrderPicur;
        private TextView tvCategory;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvCount;
        private AddSub order_add_sub;

        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2018-05-02 10:30:42 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */
        public InventoryViewHolder(View itemView) {
            super(itemView);
            tvKucun = itemView.findViewById(R.id.tv_kucun);
            tvPinpai = itemView.findViewById(R.id.tv_pinpai);
            tvXinghao = itemView.findViewById(R.id.tv_xinghao);

            ivOrderPicur = itemView.findViewById(R.id.iv_order_picur);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvCount = itemView.findViewById(R.id.tv_count);
            order_add_sub = itemView.findViewById(R.id.order_add_sub);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (onItemClickListener !=null){
//                        onItemClickListener.OnItemClickListener(getLayoutPosition());
//                    }
//                }
//            });


        }
    }

    public interface OnValueChangeListener {
        void OnValueChange(List<OrderBean.GoodsCaseDetailsVosBean> goodsCaseDetailsVosBean);
    }

    private OnValueChangeListener onValueChangeListener;

    public void setOnValueChangeListener(OnValueChangeListener onValueChangeListener) {
        this.onValueChangeListener = onValueChangeListener;
    }

    public interface OnItemClickListener {
        void OnItemClickListener(int position);
    }

    private ShoppingCartAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(ShoppingCartAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
   /* public interface OnChangeAddSubListener{
        void OnChangeAddSub(View view);
    }
    private OnChangeAddSubListener onChangeAddSubListener;

    public OnChangeAddSubListener setOnChangeAddSubListener(OnChangeAddSubListener onChangeAddSubListener){
        return this.onChangeAddSubListener = onChangeAddSubListener;
    }*/


}
