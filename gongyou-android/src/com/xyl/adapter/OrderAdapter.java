package com.xyl.adapter;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.LoginBean;
import com.xyl.domain.OrderBean;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by 47500 on 2018/4/28.
 */

public class OrderAdapter extends RecyclerView.Adapter {
    //设置适配器的3中类型

    /**
     * 商品
     */
    public static final int COMMODITY = 0;
    /**
     * 添加修改商品以及添加照片
     */
    public static final int UPDATA = 1;
    /**
     * 操作记录
     */
    public static final int TIMEAXIS = 2;
    /**
     * 显示图片以及描述
     */
    public static final int DESCRIBE = 3;


    public static final int ANNOTATION = 4;

    public static final int REJECT = 5;
    private Context mContext;
    private OrderBean orderBean;

    private int currentType = COMMODITY;
    private final LayoutInflater layoutInflater;
    private List<OrderBean.GoodsCaseDetailsVosBean> caseDetailsVosBeans;
    private View viewLayout;
    private TimeAxisAdapter timeAxisViewHolder;
    private InventoryViewHolderAdapter inventoryViewHolderAdapter;
    private LoginBean loginBean;
    private FragmentManager manager;

    public OrderAdapter(Context context, OrderBean bean, LoginBean loginBean, FragmentManager supportFragmentManager) {
        this.mContext = context;
        this.orderBean = bean;
        this.loginBean = loginBean;
        this.manager = supportFragmentManager;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == COMMODITY) {
            return new InventoryViewHolder(layoutInflater.inflate(R.layout.holder_inventory, null), mContext);
        } else if (viewType == UPDATA) {
            return new ChangeViewHolder(layoutInflater.inflate(R.layout.holder_change, null), mContext);
        }  else if (viewType == TIMEAXIS) {
            return new TimeAxisViewHolder(layoutInflater.inflate(R.layout.holder_timeaxis, null), mContext);
        } else if (viewType == DESCRIBE) {
            return new DescribeViewholder(layoutInflater.inflate(R.layout.holder_describe, null), mContext);
        }else if (viewType == ANNOTATION) {
            return new AnnotationViewHolder(layoutInflater.inflate(R.layout.holder_annotation, null), mContext);
        } else if (viewType == REJECT) {
            return new RejectViewHolder(layoutInflater.inflate(R.layout.holder_reject, null), mContext);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads.isEmpty()){
            setOrderdata(holder, position);
        }
    }

    private void setOrderdata(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == COMMODITY) {
            InventoryViewHolder inventoryViewHolder = (InventoryViewHolder) holder;
            inventoryViewHolder.setData(orderBean);
        } else if (getItemViewType(position) == DESCRIBE) {
            DescribeViewholder describeViewholder = (DescribeViewholder) holder;
            describeViewholder.setData(orderBean);
        } else if (getItemViewType(position) == TIMEAXIS) {
            TimeAxisViewHolder timeAxisViewHolder = (TimeAxisViewHolder) holder;
            timeAxisViewHolder.setData(orderBean);
        }else if (getItemViewType(position) == ANNOTATION) {
            AnnotationViewHolder annotationViewHolder = (AnnotationViewHolder) holder;
            annotationViewHolder.setData(orderBean);
        }else if (getItemViewType(position) == REJECT) {
            RejectViewHolder rejectViewHolder = (RejectViewHolder) holder;
            rejectViewHolder.setData(orderBean);
        }
    }


    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case COMMODITY:
                currentType = COMMODITY;
                break;
            case UPDATA:
                currentType = UPDATA;
                break;
            case TIMEAXIS:
                currentType = TIMEAXIS;
                break;
            case DESCRIBE:
                currentType = DESCRIBE;
                break;
            case ANNOTATION:
                currentType = ANNOTATION;
                break;
            case REJECT:
                currentType = REJECT;
                break;
        }

        return currentType;
    }

    class RejectViewHolder extends RecyclerView.ViewHolder{

        private TextView tvRejectDes;
        private View itemView;

        public RejectViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.itemView = itemView;
            tvRejectDes = itemView.findViewById( R.id.tv_reject_des );
        }

        public void setData(OrderBean orderBean) {
            if (orderBean.getRejectReason().equals("")){
                itemView.setVisibility(View.GONE);
            }else{
                itemView.setVisibility(View.VISIBLE);
                tvRejectDes.setText("描述:"+orderBean.getRejectReason());
            }
        }
    }
    private class AnnotationViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private RecyclerView rv_annotation;
        public AnnotationViewHolder(View inflate, Context mContext) {
            super(inflate);
            this.mContext = mContext;
            rv_annotation = inflate.findViewById(R.id.rv_annotation);
            rv_annotation.setLayoutManager(new LinearLayoutManager(mContext));
            rv_annotation.setItemAnimator(new DefaultItemAnimator());
        }

        public void setData(OrderBean orderBean) {
            if (orderBean.getGoodsCaseDescriptionVos().size() == 0) {
                itemView.setVisibility(View.GONE);
            }
            AnnotationAdapter annotationAdapter = new AnnotationAdapter(orderBean.getGoodsCaseDescriptionVos(),mContext);
            rv_annotation.setAdapter(annotationAdapter);
        }
    }

    class TimeAxisViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView rv_time_axis;
        private Context mContext;

        public TimeAxisViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            rv_time_axis = itemView.findViewById(R.id.rv_time_axis);
            rv_time_axis.setLayoutManager(new LinearLayoutManager(mContext));
            rv_time_axis.setItemAnimator(new DefaultItemAnimator());
        }

        public void setData(OrderBean orderBean) {
            if (orderBean.getGoodsCaseTraceVos().size() == 0) {
                itemView.setVisibility(View.GONE);
            }
            timeAxisViewHolder = new TimeAxisAdapter(mContext, orderBean);
            rv_time_axis.setAdapter(timeAxisViewHolder);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (onRefressTimeerListener != null){
//                        onRefressTimeerListener.OnRefressTimeeer(timeAxisViewHolder);
//
//                    }
//                }
//            });


        }
    }

    class InventoryViewHolder extends RecyclerView.ViewHolder  {

        private final RecyclerView rv_inventry;
        private Context iContext;
        private OrderBean.GoodsCaseDetailsVosBean caseDetailsVosBean;
        private int currentPosition;

        public InventoryViewHolder(View inflate, Context mContext) {
            super(inflate);
            this.iContext = mContext;
            rv_inventry = inflate.findViewById(R.id.rv_inventry);
            rv_inventry.setLayoutManager(new LinearLayoutManager(mContext));
        }

        public void setData(final OrderBean orderBean) {
            inventoryViewHolderAdapter = new InventoryViewHolderAdapter(iContext, orderBean,loginBean);
            rv_inventry.setAdapter(inventoryViewHolderAdapter);
            inventoryViewHolderAdapter.notifyItemChanged(currentPosition);
           /* inventoryViewHolderAdapter.setOnValueChangeListener(new InventoryViewHolderAdapter.OnValueChangeListener() {
                @Override
                public void OnValueChange(List<OrderBean.GoodsCaseDetailsVosBean> goodsCaseDetailsVosBean) {
                    caseDetailsVosBeans = goodsCaseDetailsVosBean;
                }
            });*/
            if (orderBean.getProcess()<3) {
                inventoryViewHolderAdapter.setOnItemClickListener(new ShoppingCartAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(int position) {
                        currentPosition = position;
                        caseDetailsVosBean = orderBean.getGoodsCaseDetailsVos().get(position);
                        UpdataDialogFragment updataDialogFragment = new UpdataDialogFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("DetailsVosBean", caseDetailsVosBean);
                        bundle.putSerializable("orderBean", orderBean);
                        bundle.putSerializable("LoginBean", loginBean);

                        bundle.putInt("GoodsCaseId", orderBean.getGoodsCaseId());
                        bundle.putInt("position", position);
                        updataDialogFragment.setArguments(bundle);
                        updataDialogFragment.show(manager, "ff");
                        //ToastUtil.showToast(caseDetailsVosBean.getName()+"");
                    }
                });
            }
        }



    }

    private class ChangeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView iv_repairs_tp;
        private ImageView iv_repairs_ap;
       /* private boolean isManager;*/

        public ChangeViewHolder(View inflate, Context mContext) {
            super(inflate);
            iv_repairs_tp = inflate.findViewById(R.id.iv_repairs_tp);
            iv_repairs_ap = inflate.findViewById(R.id.iv_repairs_ap);

            iv_repairs_tp.setOnClickListener(this);
            iv_repairs_ap.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {

            if (view == iv_repairs_tp) {
                if (onClickCameraListener != null) {
                    onClickCameraListener.OnClickCamera();
                }
            } else if (view == iv_repairs_ap) {
                if (onClickChangeListener != null) {
                    onClickChangeListener.OnClickChange(caseDetailsVosBeans);
                    timeAxisViewHolder.notifyDataSetChanged();
                   /* isManager = !isManager;*/
                   // inventoryViewHolderAdapter.changitemAddSub(isManager);
                    //onClickChangeListener.OnClickChange(viewLayout);
                }
            }
        }
    }

    private class DescribeViewholder extends RecyclerView.ViewHolder {
        private RecyclerView rv_desctibe_data;
        private TextView tv_mscontent;
        private Context dContext;

        public DescribeViewholder(View inflate, Context mContext) {
            super(inflate);
            this.dContext = mContext;
            rv_desctibe_data = inflate.findViewById(R.id.rv_desctibe_data);
            tv_mscontent = inflate.findViewById(R.id.tv_mscontent);
            rv_desctibe_data.setLayoutManager(new LinearLayoutManager(dContext));
        }

        public void setData(OrderBean orderBean) {
            if (orderBean.getGoodsCasePictureVos().size() == 0) {
                tv_mscontent.setVisibility(View.GONE);
            }
            DescribeAdapter describeAdapter = new DescribeAdapter(dContext, orderBean);
            rv_desctibe_data.setAdapter(describeAdapter);
        }
    }



    public interface OnClickCameraListener {
        void OnClickCamera();
    }

    private OnClickCameraListener onClickCameraListener;

    public void setOnClickCameraListener(OnClickCameraListener onClickCameraListener) {
        this.onClickCameraListener = onClickCameraListener;
    }

    public interface OnClickChangeListener {
        void OnClickChange(List<OrderBean.GoodsCaseDetailsVosBean> goodsCaseDetailsVosBeen);
    }

    private OnClickChangeListener onClickChangeListener;

    public void setOnClickChangeListener(OnClickChangeListener onClickChangeListener) {
        this.onClickChangeListener = onClickChangeListener;
    }

//    public  interface OnRefressTimeerListener{
//        void OnRefressTimeeer(TimeAxisAdapter timeAxisViewHolder);
//    }
//    private OnRefressTimeerListener onRefressTimeerListener;
//
//    public void setOnRefressTimeerListener(OnRefressTimeerListener onRefressTimeerListener) {
//        this.onRefressTimeerListener = onRefressTimeerListener;
//    }


}
