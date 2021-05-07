package com.xyl.fragment.inventoryfragment;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.xyl.R;
import com.xyl.adapter.ReviewFragmentAdapter;
import com.xyl.base.BaseFragment;
import com.xyl.base.BaseNet;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.PurchaseBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.ui.activity.OrderActivity;


import java.util.ArrayList;
import java.util.List;


/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/1/10 11:56
 * description:
 *****************************************************/

public class ReviewFragment extends BaseFragment {

    private ListView lv_purchase;
    private List<PurchaseBean.RecordsBean> recordsBeanList;
    private List<PurchaseBean.RecordsBean> recordsBeen;
    private LoginBean loginBean;
    private int type;
    private XRefreshView xRefreshView;
    private boolean isGetData = false;
    private int lv_position;
    private int pos;
    private int item_position;
    private List<PurchaseBean.RecordsBean> records;
    private ReviewFragmentAdapter reviewFragmentAdapter;
    private int piPosition = 0;
    private TextView tv_state;
    private String reviewtype;

    @Override
    public View initView() {
        View view = View.inflate(mContext,R.layout.review_view,null);
        lv_purchase = view.findViewById(R.id.lv_purchase);
        xRefreshView = view.findViewById(R.id.xrefreshview);
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        recordsBeen = new ArrayList<>();
        recordsBeanList = new ArrayList<>();
        initListener();
        return view;
    }

    private void initListener() {
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener(){
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NetContacts.getInstance().pageIndex = 1;
                        getPurchaseData();
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
                        getPurchaseData();
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });
        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    lv_position = lv_purchase.getFirstVisiblePosition();
                    View item=lv_purchase.getChildAt(0);
                    pos = (item == null) ? 0 :item.getTop();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        /*lv_purchase.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    lv_position = lv_purchase.getFirstVisiblePosition();
                    View item=lv_purchase.getChildAt(0);
                    pos = (item == null) ? 0 :item.getTop();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });*/
    }

    @Override
    public void initData() {
        super.initData();
        NetContacts.getInstance().pageIndex =1;
        //getPurchaseData();
        lv_purchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                item_position = position;
                PurchaseBean.RecordsBean recordsBean = recordsBeanList.get(position);
                Intent intent = new Intent(mContext, OrderActivity.class);
                intent.putExtra("PurchaseBean",recordsBean);
                intent.putExtra("LoginBean",loginBean);
                intent.putExtra("type",type);
                //startActivity(intent);
                startActivityForResult(intent,0);
                int firstVisiblePosition = lv_purchase.getFirstVisiblePosition(); //屏幕内当前可以看见的第一条数据
                if(position-firstVisiblePosition>=0){
                    //1.获取当前点击的条目的view
                    View itemView = lv_purchase.getChildAt(position - firstVisiblePosition);
                    //2.查找出相应的控件
                    tv_state = itemView.findViewById(R.id.tv_state);
                    /*//3.更新ui
                    textView.setText("我是更新后的数据"+position);
                    //4.更新数据源
                    mList.get(position).setName("我是更新后的数据"+position);*/
                }
            }
        });
    }

    private void getPurchaseData() {
        new OrderManager().findGoodsData(reviewtype, new BaseNet.BaseCallback<PurchaseBean>() {
            @Override
            public void messageSuccess(PurchaseBean bean) {
               /* int pageIndex = NetContacts.getInstance().pageIndex;
                Log.e("",pageIndex+"");*/
                records = bean.getRecords();
               if (NetContacts.getInstance().pageIndex ==1){
                   recordsBeanList = records;
                   reviewFragmentAdapter = new ReviewFragmentAdapter(mContext,recordsBeanList,reviewtype);
                   lv_purchase.setAdapter(reviewFragmentAdapter);
               }
                else if (records.size()>1){
                   recordsBeanList.addAll(records);
                   reviewFragmentAdapter.notifyDataSetChanged();
                }

                 /*if (!isGetData){
                     lv_purchase.setSelectionFromTop(lv_position,pos);
                 }*/

            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
       if (enter&&isGetData){
           isGetData = true;
           if (type == 1){
               reviewtype = "1,2,3,4";

           }else {
               reviewtype = type+"";
           }
           getPurchaseData();

       }else{
           isGetData = false;
       }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!isGetData){
            //在这添加需要的操作
            if (type == 1){
                reviewtype = "1,2,3,4";

            }else {
                reviewtype = type+"";
            }
            getPurchaseData();
            isGetData = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if (requestCode ==0&&resultCode==0){
            if (!isGetData){
                getPurchaseData();
                isGetData = true;
            }
        }*/
        if (requestCode==0){
            switch (resultCode){
                case 0:
                    if (!isGetData){
                        getPurchaseData();
                        isGetData = true;
                    }else{
                        isGetData = false;
                    }
                    break;
                case 1:
                    isGetData = true;
                    String status = data.getStringExtra("status");
//                    recordsBeanList.get(item_position).setProcessDisplay(status);
                    PurchaseBean.RecordsBean recordsBean = recordsBeanList.get(item_position);
                    Log.e("recordsBean",recordsBean+"");
                    reviewFragmentAdapter.notifyDataSetChanged();
                    Log.e("recordsBean",recordsBean+"");
                    break;
                case 2:
                    isGetData = true;
                    break;
            }
        }

    }

    public void setLoginData(LoginBean loginBean, int type) {
        this.loginBean = loginBean;
        this.type = type;
    }
}
