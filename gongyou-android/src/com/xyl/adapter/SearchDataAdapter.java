package com.xyl.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.Product;
import com.xyl.domain.SearchGoodsBean;
import com.xyl.fragment.inventoryfragment.ProductFragment;
import com.xyl.global.NetContacts;
import com.xyl.greendao.Cart;
import com.xyl.greendao.LibraryCartBean;
import com.xyl.greendao.TreasuryCartBean;
import com.xyl.utils.GreendaoHelper;
import com.xyl.utils.ToastUtil;

public class SearchDataAdapter extends RecyclerView.Adapter<SearchDataAdapter.ViewHolder> {

    private Context mContext;
    private SearchGoodsBean searchGoodsBean;
    private int flag;
    private double salecount;
    private double count;
    private double priceSum;

    public SearchDataAdapter(Context context, SearchGoodsBean bean,int flag) {
        this.mContext = context;
        this.searchGoodsBean = bean;
        this.flag = flag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, R.layout.product_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewProductHolder, int position) {
        final SearchGoodsBean.RecordsBean recordsBean = searchGoodsBean.getRecords().get(position);
        viewProductHolder.tvTitle.setText(recordsBean.getName());
        viewProductHolder.product_building.setVisibility(View.VISIBLE);
        viewProductHolder.product_building.setText(recordsBean.getBuildingName());
        viewProductHolder.tvPrice.setText("参考价￥" + recordsBean.getUnitPrice());
        String imageUrl = NetContacts.getInstance().BASE_IMAGE_URL + "/" + recordsBean.getPictureUrl();
        viewProductHolder.tvSalecount.setText("库存" + recordsBean.getAmount() + recordsBean.getUnit());
        viewProductHolder.tvSalecount.setTextColor(mContext.getResources().getColor(R.color.login_back));
        Glide.with(mContext).load(R.drawable.icon_stub).into(viewProductHolder.productImage);
        viewProductHolder.tvTag.setVisibility(View.GONE);
        if (flag == 0) {
            salecount = GreendaoHelper.saleCount(BaseApplication.getContext(), recordsBean.getGoodsId(), 0);
            initSalecount(viewProductHolder);
        } else if (flag == 1 || flag == 3) {
            salecount = GreendaoHelper.saleCountTreasury(BaseApplication.getContext(), recordsBean.getGoodsId(), 0);
            initSalecount(viewProductHolder);
        }
        viewProductHolder.addProductImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String edCont = viewProductHolder.countView.getText().toString();
                if (edCont.equals("")) {
                    ToastUtil.showToast("数量不能为空");
                    return;
                }
                count = Double.parseDouble(viewProductHolder.countView.getText().toString());
                if (count <= 0) {
                    ToastUtil.showToast("数量必须大于0");
                    return;
                } else {
                    ToastUtil.showToast("添加成功");
                }
                priceSum = priceSum + recordsBean.getUnitPrice();
                viewProductHolder.countView.setVisibility(View.VISIBLE);
                viewProductHolder.countView.setText(count + "");
                recordsBean.setAmount(count);
                updateCart(recordsBean);

            }
        });
    }
    private void updateCart(SearchGoodsBean.RecordsBean mProduct) {
        //TODO 这里需要进行判断购物车是否存在 如果存在   则显示动画，否则第一次不显示动画
        int productId = mProduct.getGoodsId();
        String name = mProduct.getName();
        double price = mProduct.getUnitPrice();
        if (flag == 0) {
            Cart cart = new Cart();
            cart.setName(name);
            cart.setProductId(productId);
            cart.setSaleCount(count);
            cart.setPrice(price);
            cart.setIsSelected(true);
            cart.setImageUrl(mProduct.getPictureUrl());
            GreendaoHelper.getQueryIdCart(cart, productId);
        } else if (flag == 1||flag ==3) {
            TreasuryCartBean treasuryCartBean = new TreasuryCartBean();
            treasuryCartBean.setName(name);
            treasuryCartBean.setProductId(productId);
            treasuryCartBean.setSaleCount(count);
            treasuryCartBean.setPrice(price);
            treasuryCartBean.setIsSelected(true);
            treasuryCartBean.setImageUrl(mProduct.getName());
            GreendaoHelper.getQueryIdTreasury(treasuryCartBean, productId);

        } else if (flag == 2) {
            LibraryCartBean libraryCartBean = new LibraryCartBean();
            libraryCartBean.setName(name);
            libraryCartBean.setProductId(productId);
            libraryCartBean.setSaleCount(count);
            libraryCartBean.setPrice(price);
            libraryCartBean.setIsSelected(true);
            libraryCartBean.setImageUrl(mProduct.getName());
            GreendaoHelper.getQueryIdLibrary(libraryCartBean, productId);
        }


    }
    @Override
    public int getItemCount() {
        return searchGoodsBean.getRecords().size();
    }


    private void initSalecount(ViewHolder viewProductHolder) {
        if (salecount > 0) {
            viewProductHolder.countView.setText(salecount + "");
            viewProductHolder.countView.setVisibility(View.VISIBLE);
        } else {
            viewProductHolder.countView.setVisibility(View.VISIBLE);
            viewProductHolder.removeProductImage.setVisibility(View.GONE);
            viewProductHolder.addProductImage.setVisibility(View.VISIBLE);
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTag;
        private TextView product_building;
        private TextView tvSalecount;
        private TextView tvTitle;//,tvPrice,countView
        private ImageView productImage;//,removeProductImage,addProductImage
        // private TextView countView;
        private EditText countView;
        private TextView tvPrice;
        private ImageView removeProductImage;
        private TextView addProductImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.cate_name);
            product_building = itemView.findViewById(R.id.product_building);
            tvSalecount = itemView.findViewById(R.id.product_salecount_view);
            tvTitle = itemView.findViewById(R.id.product_name_view);
            productImage = itemView.findViewById(R.id.product_imageView);
            //viewProductHolder.countView = convertView.findViewById(R.id.add_count_view);
            countView = itemView.findViewById(R.id.add_count_view);
            tvPrice = itemView.findViewById(R.id.product_price_view);
            removeProductImage = itemView.findViewById(R.id.delete_product_view);
            addProductImage = itemView.findViewById(R.id.add_product_view);
            //如果有该类型，则隐藏

        }
    }
}
