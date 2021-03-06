package com.xyl.fragment.inventoryfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.xyl.R;
import com.xyl.adapter.LeftAdapter;
import com.xyl.adapter.ReviewFragmentAdapter;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.CasesBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.Product;
import com.xyl.domain.ProductBean;
import com.xyl.domain.PurchaseBean;
import com.xyl.global.NetContacts;
import com.xyl.greendao.Cart;
import com.xyl.greendao.LibraryCartBean;
import com.xyl.greendao.TreasuryCartBean;
import com.xyl.net.OrderManager;
import com.xyl.ui.activity.OrderActivity;
import com.xyl.ui.activity.ShoppingCartActivity;
import com.xyl.utils.GreendaoHelper;
import com.xyl.utils.ToastUtil;

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

public class ProductFragment extends Fragment implements SectionIndexer, View.OnClickListener {

    private ListView rightListView;  //????????????listview
    private ListView leftListView;   //??????--????????????listview
    private RightAdapter rightAdapter;   //??????adapter
    private LeftAdapter leftAdapter;  //??????adapter
    /*private ImageView shopCart;//?????????
    private View cartFrame;
    private TextView buyNumView;//???????????????????????????*/
    private TextView mPriceSumView;
    //private View cartView;
    private TextView selectedView;
    private View titleLayout;
    private TextView title;
    private ListView popuListView;
    /**
     * ????????????????????????????????????????????????????????????
     */
    private int lastFirstVisibleItem = -1;
    private List<Product> mProductList;
    private ViewGroup anim_mask_layout;//?????????
    private int buyNum = 0;//????????????


    private double count = 0;
    private double priceSum = 0.0;

    /**
     * ????????????????????????id??????????????????????????????????????????????????????
     */
    private int businessId = 0;

    /**
     * ???????????????????????????
     */
    private View lastView;
    private ArrayList products;
    private ProductBean productBean;

    private int flag;
    private double salecount;
    private TextView tv_goods_class;
    private ListView lv_purchase;
    private List<PurchaseBean.RecordsBean> recordsBeanList;
    private List<PurchaseBean.RecordsBean> recordsBeen;
    private LoginBean loginBean;
    private int type;
    private XRefreshView xRefreshView;
    private boolean isGetData = false;
    private int lv_position;
    private int pos;
    private List<PurchaseBean.RecordsBean> records;
    private ReviewFragmentAdapter reviewFragmentAdapter;
    private int piPosition = 0;
    private TextView tv_state;

    private int stockTakId;
    private List<Cart> mCartList = new ArrayList<Cart>();

    private CasesBean.Records caseRecord;
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
        title = view.findViewById(R.id.title_layout_catalog);
        rightListView = view.findViewById(R.id.menu_lvmenu);
       /* shopCart = ) view.findViewById(R.id.iv_add_cart);//?????????
        cartFrame = view.findViewById(R.id.cart_frame);
        buyNumView = (TextView) view.findViewById(R.id.tv_count_price);//???????????????????????????*/
        mPriceSumView = view.findViewById(R.id.price_sum_view);
        tv_goods_class = view.findViewById(R.id.tv_goods_class);
        selectedView = view.findViewById(R.id.selected_view);
        leftListView = view.findViewById(R.id.side_menu_lv);
        titleLayout.setVisibility(View.GONE);
        showSeleted();
        if (flag == 0 ) {
            selectedView.setText("??????");
        } else if (flag == 1) {
            selectedView.setText("??????");
        } else if (flag == 2) {
            selectedView.setText("??????");
        }else if (flag == 3) {
            selectedView.setText("??????");
        }


        lv_purchase = view.findViewById(R.id.lv_purchase);
        xRefreshView = view.findViewById(R.id.xrefreshview);
        //???????????????????????????headerview???????????????
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        recordsBeen = new ArrayList<>();
        recordsBeanList = new ArrayList<>();
    }

    private void initClick() {
        //cartFrame.setOnClickListener(this);
        selectedView.setOnClickListener(this);
        //????????????????????????????????????
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                lv_purchase.setVisibility(View.GONE);
//                xRefreshView.setVisibility(View.GONE);
                rightListView.setVisibility(View.VISIBLE);
                titleLayout.setVisibility(View.VISIBLE);
                if (lastView != null) {
                    //???????????????view????????????
                    lastView.setBackgroundColor(getResources().getColor(R.color.frament_tab_color));
                }
                //???????????????????????????
                view.setBackgroundColor(getResources().getColor(R.color.white));
                //?????????????????????????????????????????????
                TextView section_tv = view.findViewById(R.id.section_tv);
                int location = rightAdapter.getPositionForSection((Integer.parseInt(section_tv.getText().toString())));
                if (location != -1) {
                    rightListView.setSelection(location);
                }
                lastView = view;
            }
        });


//        tv_goods_class.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lv_purchase.setVisibility(View.VISIBLE);
////                xRefreshView.setVisibility(View.VISIBLE);
//                titleLayout.setVisibility(View.GONE);
//                rightListView.setVisibility(View.GONE);
//                getCateProductList();
//            }
//        });


        lv_purchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PurchaseBean.RecordsBean recordsBean = recordsBeanList.get(position);
                //TODO
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("PurchaseBean", recordsBean);
                intent.putExtra("LoginBean", loginBean);
                intent.putExtra("type", type);
                //startActivity(intent);
                startActivityForResult(intent, 0);
                int firstVisiblePosition = lv_purchase.getFirstVisiblePosition(); //?????????????????????????????????????????????
                if (position - firstVisiblePosition >= 0) {
                    //1.??????????????????????????????view
                    View itemView = lv_purchase.getChildAt(position - firstVisiblePosition);
                    //2.????????????????????????
                    tv_state = itemView.findViewById(R.id.tv_state);
                    /*//3.??????ui
                    textView.setText("????????????????????????"+position);
                    //4.???????????????
                    mList.get(position).setName("????????????????????????"+position);*/
                }
            }
        });

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NetContacts.getInstance().pageIndex = 1;
                        //getPurchaseData();
                        xRefreshView.stopRefresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        NetContacts.getInstance().pageIndex++;
                        piPosition = NetContacts.getInstance().pageIndex;
                        //getPurchaseData();
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });
        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    lv_position = lv_purchase.getFirstVisiblePosition();
                    View item = lv_purchase.getChildAt(0);
                    pos = (item == null) ? 0 : item.getTop();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    /**
     * ???????????????
     */
    private void initData() {

        if (flag == 0) {
            tv_goods_class.setEnabled(false);
            xRefreshView.setVisibility(View.GONE);
            rightListView.setVisibility(View.VISIBLE);
            //getCateProductList();
            getGoodsProductList();
        } else if (flag == 1) {
            //tv_goods_class.setText("?????????");
            //getPurchaseData();
            tv_goods_class.setEnabled(false);
            xRefreshView.setVisibility(View.GONE);
            rightListView.setVisibility(View.VISIBLE);
            getGoodsProductList();
        } else if (flag == 2) {
            tv_goods_class.setEnabled(false);
            xRefreshView.setVisibility(View.GONE);
            titleLayout.setVisibility(View.VISIBLE);
            rightListView.setVisibility(View.VISIBLE);
            getGoodsCategory();
        }else if(flag == 3){
            tv_goods_class.setEnabled(false);
            xRefreshView.setVisibility(View.GONE);
            rightListView.setVisibility(View.VISIBLE);
            getGoodsProductList();
        }
        //mProductList = Parser.getCateProductList(getActivity());


        rightListView.setOnScrollListener(mOnScrollListener);
        //???????????????????????????
        //if (mProductList==null){
        //        }
    }

    private void getGoodsCategory() {
        new OrderManager().getGoodsCategory(stockTakId, new BaseNet.BaseCallback<ProductBean>() {
            @Override
            public void messageSuccess(ProductBean bean) {
                goTransform(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private List<Product> getCateProductList() {
        new OrderManager().goodsCategory( type ,new BaseNet.BaseCallback<ProductBean>() {
            @Override
            public void messageSuccess(ProductBean bean) {
                goTransform(bean);
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
    private List<Product> getGoodsProductList() {
        new OrderManager().goodsGetgoodscategory(type,new BaseNet.BaseCallback<ProductBean>() {
            @Override
            public void messageSuccess(ProductBean bean) {
                goTransform(bean);
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
    private void goTransform(ProductBean bean) {
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
                item.setThisCount(food.getThisAmount());
                item.setStockTaking(food.isStockTaking());
                item.setUnit(food.getUnit());
                mProductList.add(item);
            }
        }
        rightAdapter = new RightAdapter(getActivity(), mProductList);
        leftAdapter = new LeftAdapter(getActivity(), mProductList);
        rightListView.setAdapter(rightAdapter);
        leftListView.setAdapter(leftAdapter);
    }


//    private void getPurchaseData() {
//        new OrderManager().findStorageData(type + "", new BaseNet.BaseCallback<PurchaseBean>() {
//            @Override
//            public void messageSuccess(PurchaseBean bean) {
//               /* int pageIndex = NetContacts.getInstance().pageIndex;
//                Log.e("",pageIndex+"");*/
//                records = bean.getRecords();
//                if (NetContacts.getInstance().pageIndex == 1) {
//                    recordsBeanList = records;
//                    reviewFragmentAdapter = new ReviewFragmentAdapter(getActivity(), recordsBeanList, reviewtype);
//                    lv_purchase.setAdapter(reviewFragmentAdapter);
//                } else if (records.size() > 1) {
//                    recordsBeanList.addAll(records);
//                    reviewFragmentAdapter.notifyDataSetChanged();
//                }
//
//                 /*if (!isGetData){
//                     lv_purchase.setSelectionFromTop(lv_position,pos);
//                 }*/
//
//            }
//
//            @Override
//            public void messageFailure(MessageBean backError) {
//
//            }
//
//            @Override
//            public void connectFailure(MessageBean messageBean) {
//
//            }
//        });
//    }

    /**
     * ????????????????????????????????????
     */
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            try {
                //?????????????????????item???section
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
     * ???????????????
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
        anim_mask_layout.addView(v);//?????????????????????????????????
        final View view = addViewToAnimLayout(anim_mask_layout, v,
                startLocation);
        int[] endLocation = new int[2];// ???????????????????????????X???Y??????
        //TODO ?????????????????????????????????????????????
        //shopCart.getLocationInWindow(endLocation);// shopCart??????????????????

        // ????????????
        int endX = 0 - startLocation[0] + 20;// ???????????????X??????
        int endY = endLocation[1] - startLocation[1];// ???????????????y??????
        System.out.println("=====x===" + endX);
        System.out.println("=====y===" + endY);
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator()); //?????????????????????????????????
        translateAnimationX.setRepeatCount(0);// ???????????????????????????
        translateAnimationX.setFillAfter(true); //???????????????????????????setLayoutParams???????????????

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// ???????????????????????????
        translateAnimationX.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// ?????????????????????
        view.startAnimation(set);
        // ??????????????????
        set.setAnimationListener(new Animation.AnimationListener() {
            // ???????????????
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // ???????????????
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                addCart();
            }
        });

    }

    private void addCart() {
        buyNum++;//???????????????+1
    }

    public void removeCart() {
        buyNum--;//???????????????-1
    }

    private void showSeleted() {
       /* if (buyNum > 0) {
           *//* buyNumView.setVisibility(View.VISIBLE);
            buyNumView.setText(buyNum + "");*//*
            *//*mPriceSumView.setText("??????:" + convert(priceSum));
            mPriceSumView.setTextColor(getResources().getColor(R.color.big_red));
            if (priceSum > 1) {
                selectedView.setEnabled(true);
                selectedView.setText("?????????");
                selectedView.setBackgroundResource(R.drawable.bg_choice_press_round);
            }*//*
           *//* shopCart.setImageResource(R.drawable.cart13);
            cartFrame.setEnabled(true);*//*
            //selectedView.setVisibility(View.VISIBLE);
        } else {
            mPriceSumView.setText("????????????~");
            mPriceSumView.setTextColor(getResources().getColor(R.color.cart_choice_color));
            selectedView.setEnabled(false);
            selectedView.setText("??????????????????");
            selectedView.setBackgroundResource(R.drawable.bg_choice_round);
        }*/
        mPriceSumView.setText("????????????~");
        mPriceSumView.setTextColor(getResources().getColor(R.color.cart_choice));
        selectedView.setEnabled(true);
        selectedView.setBackgroundResource(R.drawable.bg_choice_round);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selected_view:
                //???????????? ?????? mCartList
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
        if (flag == 2) {
            getLibrarydata();
            final String data = new Gson().toJson(mCartList);
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("????????????");
            builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialogInterface, int i) {
                    new OrderManager().updateStocktaking(stockTakId, data, new BaseNet.EntityCallback() {
                        @Override
                        public void connectCallback(BaseNet.EntityrResult entityrResult) {
                            if (entityrResult.entityType == BaseNet.EntityType.messagetrue) {
                                initData();
                                dialogInterface.dismiss();
                                ToastUtil.showToast("????????????");
                            }
                        }
                    });
                }
            });
            builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();


        } else {
            //TODO
            Intent intent = new Intent(getActivity(), ShoppingCartActivity.class);
            intent.putExtra("flag", flag);
            if (flag == 1){
                type = 2;
            }else if (flag ==0){
                type = 0;
            }
            intent.putExtra("LoginBean",loginBean);
            intent.putExtra("type",type);
            intent.putExtra("caseBean",caseRecord);
            startActivityForResult(intent, 0);
        }
    }

    public void getLibrarydata() {
        List<LibraryCartBean> libraryCartBeen = GreendaoHelper.getLibraryCartList(businessId);
        for (int i = 0; i < libraryCartBeen.size(); i++) {
            LibraryCartBean libraryCartBean = libraryCartBeen.get(i);
            Cart cart = new Cart();
            cart.setName(libraryCartBean.getName());
            cart.setProductId(libraryCartBean.getProductId());
            cart.setSaleCount(libraryCartBean.getSaleCount());
            cart.setPrice(libraryCartBean.getPrice());
            cart.setProductType(libraryCartBean.getProductType());
            cart.setBusinessId(libraryCartBean.getBusinessId());
            cart.setIsSelected(libraryCartBean.getIsSelected());
            cart.setImageUrl(libraryCartBean.getImageUrl());
            mCartList.add(cart);
            GreendaoHelper.deleteLibraryCart(libraryCartBean.getProductId());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            switch (resultCode) {

                case 0:

                    break;
                case 1:
                    //getPurchaseData();
                    initData();
                    //reviewFragmentAdapter.notifyDataSetChanged();
                    break;

            }
        }

    }

    private void updateCart(Product mProduct) {
        //TODO ????????????????????????????????????????????? ????????????   ????????????????????????????????????????????????
        int productId = mProduct.getProductId();
        String name = mProduct.getFoodName();
        double price = mProduct.getFoodPrice();
        if (flag == 0) {
            Cart cart = new Cart();
            cart.setName(name);
            cart.setProductId(productId);
            cart.setSaleCount(count);
            cart.setPrice(price);
            cart.setIsSelected(true);
            cart.setImageUrl(mProduct.getImageUrl());
            GreendaoHelper.getQueryIdCart(cart, productId);
        } else if (flag == 1||flag ==3) {
            TreasuryCartBean treasuryCartBean = new TreasuryCartBean();
            treasuryCartBean.setName(name);
            treasuryCartBean.setProductId(productId);
            treasuryCartBean.setSaleCount(count);
            treasuryCartBean.setPrice(price);
            treasuryCartBean.setIsSelected(true);
            treasuryCartBean.setImageUrl(mProduct.getImageUrl());
            GreendaoHelper.getQueryIdTreasury(treasuryCartBean, productId);

        } else if (flag == 2) {
            LibraryCartBean libraryCartBean = new LibraryCartBean();
            libraryCartBean.setName(name);
            libraryCartBean.setProductId(productId);
            libraryCartBean.setSaleCount(count);
            libraryCartBean.setPrice(price);
            libraryCartBean.setIsSelected(true);
            libraryCartBean.setImageUrl(mProduct.getImageUrl());
            GreendaoHelper.getQueryIdLibrary(libraryCartBean, productId);
        }


    }

    private void deleteProductId(int productId) {
        if (flag == 0) {
            GreendaoHelper.deleteCart(productId);
        } else if (flag == 1 || flag == 3) {
            GreendaoHelper.deleteTreasury(productId);
        } else if (flag == 2) {
            GreendaoHelper.deleteLibraryCart(productId);
        }

    }

    /*public void setFlag(int treasury) {
        this.flag = treasury;
    }*/

    public void setData(LoginBean loginBean, int type, int flag) {
        this.loginBean = loginBean;
        this.type = type;
        this.flag = flag;
    }

    public void setTakingId(LoginBean loginBean, int stockTakingId, int type, int flag) {
        this.loginBean = loginBean;
        stockTakId = stockTakingId;
        this.type = type;
        this.flag = flag;
    }

    public void setData(LoginBean loginBean, int type, int flag, CasesBean.Records caseRecord) {
        this.loginBean = loginBean;
        this.type = type;
        this.flag = flag;
        this.caseRecord = caseRecord;

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
           /* if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.product_item, null);
                viewProductHolder = new ViewProductHolder();
                viewProductHolder.tvTag = convertView.findViewById(R.id.cate_name);
                viewProductHolder.tvSalecount = convertView.findViewById(R.id.product_salecount_view);
                viewProductHolder.tvTitle = convertView.findViewById(R.id.product_name_view);
                viewProductHolder.productImage = convertView.findViewById(R.id.product_imageView);
                //viewProductHolder.countView = convertView.findViewById(R.id.add_count_view);
                viewProductHolder.countView = convertView.findViewById(R.id.add_count_view);
                viewProductHolder.tvPrice = convertView.findViewById(R.id.product_price_view);
                viewProductHolder.removeProductImage = convertView.findViewById(R.id.delete_product_view);
                viewProductHolder.addProductImage = convertView.findViewById(R.id.add_product_view);
                convertView.setTag(viewProductHolder);
            } else {
                viewProductHolder = (ViewProductHolder) convertView.getTag();
            }*/
            convertView = LayoutInflater.from(mContext).inflate(R.layout.product_item, null);
            viewProductHolder = new ViewProductHolder();
            viewProductHolder.tvTag = convertView.findViewById(R.id.cate_name);
            viewProductHolder.tvSalecount = convertView.findViewById(R.id.product_salecount_view);
            viewProductHolder.tvTitle = convertView.findViewById(R.id.product_name_view);
            viewProductHolder.productImage = convertView.findViewById(R.id.product_imageView);
            //viewProductHolder.countView = convertView.findViewById(R.id.add_count_view);
            viewProductHolder.countView = convertView.findViewById(R.id.add_count_view);
            viewProductHolder.tvPrice = convertView.findViewById(R.id.product_price_view);
            viewProductHolder.removeProductImage = convertView.findViewById(R.id.delete_product_view);
            viewProductHolder.addProductImage = convertView.findViewById(R.id.add_product_view);
            viewProductHolder.addProductImage = convertView.findViewById(R.id.add_product_view);
            viewProductHolder.product_specifi = convertView.findViewById(R.id.product_specifi);
            viewProductHolder.product_model = convertView.findViewById(R.id.product_model);
            viewProductHolder.product_brand = convertView.findViewById(R.id.product_brand);
            //??????????????????????????????
            int section = getSectionForPosition(position);
            if (position == getPositionForSection(section)) {
                viewProductHolder.tvTag.setVisibility(View.VISIBLE);
                viewProductHolder.tvTag.setText(mProduct.getType());
            } else {
                viewProductHolder.tvTag.setVisibility(View.GONE);
            }
            String imageUrl = NetContacts.getInstance().BASE_IMAGE_URL + "/" + mProduct.getImageUrl();
            viewProductHolder.tvSalecount.setTextColor(getResources().getColor(R.color.login_back));
            Glide.with(mContext).load(R.drawable.icon_stub).into(viewProductHolder.productImage);
            viewProductHolder.tvTitle.setText(mProduct.getFoodName());
            viewProductHolder.tvPrice.setText("????????????:" + mProduct.getFoodPrice());
            viewProductHolder.tvSalecount.setText("??????:" + mProduct.getSalesCount() + mProduct.getUnit());
            viewProductHolder.product_brand.setText("??????:" + mProduct.getChangShang());
            viewProductHolder.product_specifi.setText("??????:" + mProduct.getGuiGe());
            viewProductHolder.product_model.setText("??????:" + mProduct.getXingHao());
            if (flag == 0) {
                salecount = GreendaoHelper.saleCount(BaseApplication.getContext(), mProduct.getProductId(), businessId);
                initSalecount(viewProductHolder);
            } else if (flag == 1 || flag == 3) {
                salecount = GreendaoHelper.saleCountTreasury(BaseApplication.getContext(), mProduct.getProductId(), businessId);
                initSalecount(viewProductHolder);
            } else if (flag == 2) {
                //salecount = mProduct.getThisCount();
                salecount = GreendaoHelper.saleLibraryCount(BaseApplication.getContext(), mProduct.getProductId(), businessId);
                if (mProduct.isStockTaking()) {
                    viewProductHolder.tvTitle.setTextColor(getResources().getColor(R.color.login_back));
                    viewProductHolder.removeProductImage.setVisibility(View.GONE);
                    viewProductHolder.addProductImage.setVisibility(View.GONE);
                    viewProductHolder.countView.setVisibility(View.VISIBLE);
                    viewProductHolder.countView.setText("?????????" + mProduct.getThisCount() + "");
                } else {
                    viewProductHolder.tvTitle.setTextColor(getResources().getColor(R.color.white));
                    initSalecount(viewProductHolder);
                }
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

                    String edCont = viewProductHolder.countView.getText().toString();
                    if (edCont.equals("")) {
                        ToastUtil.showToast("??????????????????");
                        return;
                    }
                    count = Double.parseDouble(viewProductHolder.countView.getText().toString());
                    if (count <= 0) {
                        ToastUtil.showToast("??????????????????0");
                        return;
                    } else {
                        ToastUtil.showToast("????????????");
                    }
                    priceSum = priceSum + mProduct.getFoodPrice();
                    viewProductHolder.countView.setVisibility(View.VISIBLE);
                    viewProductHolder.countView.setText(count + "");
                    mProduct.setCount(count);
                    updateCart(mProduct);
                    leftAdapter.notifyDataSetChanged();
                }
            });

            return convertView;
        }


        private void initSalecount(ViewProductHolder viewProductHolder) {
            if (salecount > 0) {
                viewProductHolder.countView.setText(salecount + "");
                viewProductHolder.countView.setVisibility(View.VISIBLE);
            } else {
                viewProductHolder.countView.setVisibility(View.VISIBLE);
                viewProductHolder.removeProductImage.setVisibility(View.GONE);
                viewProductHolder.addProductImage.setVisibility(View.VISIBLE);
            }
        }

        private class ViewProductHolder {
            private TextView tvTag;
            private TextView tvSalecount;
            private TextView tvTitle;//,tvPrice,countView
            private ImageView productImage;//,removeProductImage,addProductImage
            // private TextView countView;
            private EditText countView;
            private TextView tvPrice;
            private ImageView removeProductImage;
            private TextView addProductImage;

            private TextView product_specifi;
            private TextView product_model;
            private TextView product_brand;
        }

        @Override
        public int getSectionForPosition(int position) {
            return list.get(position).getSeleteId();
        }

        /**
         * ??????api????????????
         *
         * @param sectionIndex`?????????????????????????????????????????????????????????
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
            // TODO ???????????????????????????
            return null;
        }

    }

    /////////////////////////////////////cart adapter////////////////////////////////////////////////


    static double convert(double value) {
        long l1 = Math.round(value * 100); //????????????
        double ret = l1 / 100.0; //??????????????? 100.0 ????????? 100
        return ret;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //GreendaoHelper.clearCart(getActivity(), businessId);
    }

}
