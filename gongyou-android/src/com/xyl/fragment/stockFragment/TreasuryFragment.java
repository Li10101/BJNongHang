package com.xyl.fragment.stockFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.adapter.LeftAdapter;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.domain.Product;
import com.xyl.domain.ProductBean;
import com.xyl.global.NetContacts;
import com.xyl.greendao.Cart;
import com.xyl.net.OrderManager;
import com.xyl.utils.GreendaoHelper;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/1/10 11:54
 * description:
 *****************************************************/

public class TreasuryFragment extends Fragment
        implements SectionIndexer, View.OnClickListener {

    private ListView rightListView;  //右侧商品listview
    private ListView leftListView;   //左侧--商品类型listview
    private RightAdapter rightAdapter;   //右侧adapter
    private LeftAdapter leftAdapter;  //左侧adapter
    /*private ImageView shopCart;//购物车
    private View cartFrame;
    private TextView buyNumView;//购物车上的数量标签*/
    private TextView mPriceSumView;
    //private View cartView;
    private TextView selectedView;
    private View titleLayout;
    private TextView title;
    private ListView popuListView;
    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;
    private List<Product> mProductList;
    private ViewGroup anim_mask_layout;//动画层
    private int buyNum = 0;//购买数量


    private int count = 0;
    private double priceSum = 0.0;

    /**
     * 在这里虚构个商家id，其主要目的是不想让大家走更多的弯路
     */
    private int businessId = 0;

    /**
     * 上次选中的左侧菜单
     */
    private View lastView;
    private ArrayList products;
    private ProductBean productBean;

    private int flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list, null);
        initView(view);
        initClick();
        initData();
        return view;
    }

    private void initView(View view) {

        titleLayout = view.findViewById(R.id.title_layout);
        title = (TextView) view.findViewById(R.id.title_layout_catalog);
        rightListView = (ListView) view.findViewById(R.id.menu_lvmenu);
       /* shopCart = (ImageView) view.findViewById(R.id.iv_add_cart);//购物车
        cartFrame = view.findViewById(R.id.cart_frame);
        buyNumView = (TextView) view.findViewById(R.id.tv_count_price);//购物车上的数量标签*/
        mPriceSumView = (TextView) view.findViewById(R.id.price_sum_view);
        selectedView = view.findViewById(R.id.selected_view);
        leftListView =  view.findViewById(R.id.side_menu_lv);
        showSeleted();
        if (flag == 1){
            selectedView.setText("查看采购商品");
        } else if (flag == 2){
            selectedView.setText("查看出库商品");
        }
    }

    private void initClick() {
        //cartFrame.setOnClickListener(this);
        selectedView.setOnClickListener(this);
        //左侧类型点击对应右侧商品
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (lastView != null) {
                    //上次选中的view变回灰色
                    lastView.setBackgroundColor(getResources().getColor(R.color.frament_tab_color));
                }
                //设置选中颜色为白色
                view.setBackgroundColor(getResources().getColor(R.color.white));
                //点击左侧，右侧滚动到对应的位置
                TextView section_tv = view.findViewById(R.id.section_tv);
                int location = rightAdapter.getPositionForSection(
                        (Integer.parseInt(section_tv.getText().toString())));
                if (location != -1) {
                    rightListView.setSelection(location);
                }
                lastView = view;
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //mProductList = Parser.getCateProductList(getActivity());
        getCateProductList();
        rightListView.setOnScrollListener(mOnScrollListener);
        //此处为判断是否为空
//        if (mProductList==null){
//        }
    }

    private List<Product> getCateProductList() {
        new OrderManager().goodsCategory(1,new BaseNet.BaseCallback<ProductBean>() {
            @Override
            public void messageSuccess(ProductBean bean) {
                if (bean == null) {
                    bean = new ProductBean();
                }
                productBean = bean;
                mProductList = new ArrayList<Product>();
                for (int i = 0; i < productBean.getAddGoodsCategoryVos().size(); i++) {
                    for (int j = 0; j < productBean.getAddGoodsCategoryVos().get(i).getGoodsList().size(); j++) {
                        ProductBean.AddGoodsCategoryVosBean.GoodsListBean food = productBean.getAddGoodsCategoryVos().get(i).getGoodsList().get(j);
                        Product item = new Product();
                        item.setProductId(food.getGoodsId());
                        item.setType(productBean.getAddGoodsCategoryVos().get(i).getName());
                        item.setFoodName(food.getName());
                        item.setFoodPrice(food.getUnitPrice());
                        item.setSalesCount(food.getAmount());
                        item.setImageUrl(food.getPictureUrl());
                        item.setSeleteId(i);
                        mProductList.add(item);
                    }
                }
                rightAdapter = new RightAdapter(getActivity(), mProductList);
                leftAdapter = new LeftAdapter(getActivity(), mProductList);
                rightListView.setAdapter(rightAdapter);
                leftListView.setAdapter(leftAdapter);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
        return products;
    }


    /**
     * 右侧商品滑动对于左侧类型
     */
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            try {
                //获取屏幕第一个item的section
                int section = getSectionForPosition(firstVisibleItem);
                int nextSection = 0;
                if (mProductList.size() > 1) {
                    nextSection = getSectionForPosition(firstVisibleItem + 1);
                }

                int nextSecPosition = getPositionForSection(+nextSection);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(mProductList.get(getPositionForSection(section)).getType());
                    if (lastView != null) {
                        lastView.setBackgroundColor(getResources().getColor(R.color.frament_tab_color));
                    }
                    lastView = leftListView.getChildAt(section);
                    lastView.setBackgroundColor(getResources().getColor(R.color.white));

                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < mProductList.size(); i++) {
            int section = mProductList.get(i).getSeleteId();
            if (section == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int i) {
        return mProductList.get(i).getSeleteId();
    }

    /**
     * 创建动画层
     *
     * @return
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v,
                startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        //TODO 这里需要进行判断购物车是否存在
        //shopCart.getLocationInWindow(endLocation);// shopCart是那个购物车

        // 计算位移
        int endX = 0 - startLocation[0] + 20;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标
        System.out.println("=====x===" + endX);
        System.out.println("=====y===" + endY);
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator()); //让动画已均匀的速度改变
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true); //执行完毕，利用视图setLayoutParams来重新定位

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                addCart();
            }
        });

    }

    private void addCart() {
        buyNum++;//让购买数量+1

    }

    public void removeCart() {
        buyNum--;//让购买数量-1
    }

    private void showSeleted() {
       /* if (buyNum > 0) {
           *//* buyNumView.setVisibility(View.VISIBLE);
            buyNumView.setText(buyNum + "");*//*
            *//*mPriceSumView.setText("共￥:" + convert(priceSum));
            mPriceSumView.setTextColor(getResources().getColor(R.color.big_red));
            if (priceSum > 1) {
                selectedView.setEnabled(true);
                selectedView.setText("选好了");
                selectedView.setBackgroundResource(R.drawable.bg_choice_press_round);
            }*//*
           *//* shopCart.setImageResource(R.drawable.cart13);
            cartFrame.setEnabled(true);*//*
            //selectedView.setVisibility(View.VISIBLE);
        } else {
            mPriceSumView.setText("选择商品~");
            mPriceSumView.setTextColor(getResources().getColor(R.color.cart_choice_color));
            selectedView.setEnabled(false);
            selectedView.setText("查看采购商品");
            selectedView.setBackgroundResource(R.drawable.bg_choice_round);
        }*/
        mPriceSumView.setText("选择商品~");
        mPriceSumView.setTextColor(getResources().getColor(R.color.cart_choice_color));
        selectedView.setEnabled(true);
        selectedView.setBackgroundResource(R.drawable.bg_choice_round);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selected_view:
                //網絡請求 傳遞 mCartList
                startFragment();

                break;
           /* case R.id.iv_add_cart:
                initPopWindow();
                break;
            case R.id.cart_frame:
                initPopWindow();
                break;*/
            case R.id.cart_clear_view:
                //clear cart  === hide cart list

                break;
            default:
                break;
        }
    }

    private void startFragment() {
//        Intent intent = new Intent(getActivity(), ShoppingCartActivity.class);
//        startActivityForResult(intent, 0);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0:
                rightAdapter.notifyDataSetChanged();
                break;

        }
    }

    private void updateCart(Product mProduct) {
        //TODO 这里需要进行判断购物车是否存在 如果存在   则显示动画，否则第一次不显示动画
        int productId = mProduct.getProductId();
        String name = mProduct.getFoodName();
        double price = mProduct.getFoodPrice();
        Cart cart = new Cart();
        cart.setName(name);
        cart.setProductId(productId);
        cart.setSaleCount(count);
        cart.setPrice(price);
        cart.setIsSelected(true);
        cart.setImageUrl(mProduct.getImageUrl());
        GreendaoHelper.getQueryIdCart(cart, productId);

    }

    private void deleteProductId(int productId) {
        GreendaoHelper.deleteCart(productId);
    }

    public void setFlag(int treasury) {
        this.flag = treasury;
    }

    /////////////////////////////////////product adapter////////////////////////////////////////////////

    class RightAdapter extends BaseAdapter implements SectionIndexer {
        private List<Product> list = null;
        private Context mContext;

        public RightAdapter(Context mContext, List<Product> list) {
            this.mContext = mContext;
            this.list = list;
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return this.list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ViewProductHolder viewProductHolder;
            final Product mProduct = list.get(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.product_item, null);
                viewProductHolder = new ViewProductHolder();
                viewProductHolder.tvTag = convertView.findViewById(R.id.cate_name);
                viewProductHolder.tvSalecount =  convertView.findViewById(R.id.product_salecount_view);
                viewProductHolder.tvTitle =  convertView.findViewById(R.id.product_name_view);
                viewProductHolder.productImage =  convertView.findViewById(R.id.product_imageView);
                viewProductHolder.countView =  convertView.findViewById(R.id.add_count_view);
                viewProductHolder.tvPrice =  convertView.findViewById(R.id.product_price_view);
                viewProductHolder.removeProductImage = convertView.findViewById(R.id.delete_product_view);
                viewProductHolder.addProductImage = convertView.findViewById(R.id.add_product_view);
                convertView.setTag(viewProductHolder);
            } else {
                viewProductHolder = (ViewProductHolder) convertView.getTag();
            }
            //如果有该类型，则隐藏
            int section = getSectionForPosition(position);
            if (position == getPositionForSection(section)) {
                viewProductHolder.tvTag.setVisibility(View.VISIBLE);
                viewProductHolder.tvTag.setText(mProduct.getType());
            } else {
                viewProductHolder.tvTag.setVisibility(View.GONE);
            }
            viewProductHolder.tvTitle.setText(mProduct.getFoodName());
            viewProductHolder.tvPrice.setText("参考价￥" + mProduct.getFoodPrice());
            viewProductHolder.tvSalecount.setText("库存" + mProduct.getSalesCount() + "份");
            String imageUrl = NetContacts.getInstance().BASE_IMAGE_URL + "/" + mProduct.getImageUrl();
            Glide.with(mContext).load(imageUrl).into(viewProductHolder.productImage);
            double salecount = GreendaoHelper.saleCount(BaseApplication.getContext(), mProduct.getProductId(), businessId);
            if (salecount > 0) {
                viewProductHolder.countView.setText(salecount + "");
                viewProductHolder.countView.setVisibility(View.VISIBLE);
                viewProductHolder.removeProductImage.setVisibility(View.VISIBLE);
            } else {
                viewProductHolder.countView.setText(0 + "");
                viewProductHolder.countView.setVisibility(View.GONE);
                viewProductHolder.removeProductImage.setVisibility(View.GONE);
            }



            viewProductHolder.removeProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count = Integer.parseInt(viewProductHolder.countView.getText().toString());
                    count--;
                    priceSum = priceSum - mProduct.getFoodPrice();
                    if (count < 1) {
                        viewProductHolder.removeProductImage.setVisibility(View.GONE);
                        viewProductHolder.countView.setVisibility(View.GONE);
                    }
                    viewProductHolder.countView.setText(count + "");
                    mProduct.setCount(count);
                    if (count == 0) {
                        deleteProductId(mProduct.getProductId());
                    } else {
                        updateCart(mProduct);
                    }
                    removeCart();
                    leftAdapter.notifyDataSetChanged();
                    //cartTotal(priceSum);
                }
            });

            viewProductHolder.addProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count = Integer.parseInt(viewProductHolder.countView.getText().toString());
                    count++;
                    priceSum = priceSum + mProduct.getFoodPrice();
                    viewProductHolder.countView.setVisibility(View.VISIBLE);
                    viewProductHolder.removeProductImage.setVisibility(View.VISIBLE);
                    viewProductHolder.countView.setText(count + "");
                    mProduct.setCount(count);
                    int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                    v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                    ImageView ball = new ImageView(mContext);// buyImg是动画的图片，我的是一个小球
                    ball.setImageResource(R.drawable.icon_fansnum_like_on);// 设置buyImg的图片
                    updateCart(mProduct);
                    //setAnim(ball, startLocation);
                    leftAdapter.notifyDataSetChanged();
                    //cartTotal(priceSum);
                }
            });
            return convertView;
        }


        private class ViewProductHolder {
            private TextView tvTag;
            private TextView tvSalecount;
            private TextView tvTitle;//,tvPrice,countView
            private ImageView productImage;//,removeProductImage,addProductImage
            private TextView countView;
            private TextView tvPrice;
            private ImageView removeProductImage;
            private ImageView addProductImage;


        }

        @Override
        public int getSectionForPosition(int position) {
            return list.get(position).getSeleteId();
        }

        /**
         * 查询api可以得知
         * 根据分类列的索引号获得该序列的首个位置
         *
         * @param sectionIndex
         * @return
         */
        @Override
        public int getPositionForSection(int sectionIndex) {
            for (int i = 0; i < getCount(); i++) {
                int section = list.get(i).getSeleteId();
                if (section == sectionIndex) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public Object[] getSections() {
            // TODO 自动生成的方法存根
            return null;
        }

    }

    /////////////////////////////////////cart adapter////////////////////////////////////////////////



    static double convert(double value) {
        long l1 = Math.round(value * 100); //四舍五入
        double ret = l1 / 100.0; //注意：使用 100.0 而不是 100
        return ret;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //GreendaoHelper.clearCart(getActivity(), businessId);
    }
}
