package com.xyl.fragment.noticeFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.xyl.R;
import com.xyl.adapter.NoticeFragmentAdapter;
import com.xyl.base.BaseFragment;
import com.xyl.base.BaseNet;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.NoticeBean;
import com.xyl.domain.NoticeDateBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.ui.activity.NoticeDetailActivity;

import java.util.ArrayList;
import java.util.List;


/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/1/10 11:56
 * description:
 *****************************************************/

public class NoticesFragment3 extends BaseFragment {

    private ListView lv_purchase;
    private LoginBean loginBean;
    private int type;
    private XRefreshView xRefreshView;

    private boolean isGetData = false;
    private int firstPosition;
    private View childAt;
    private int posit;

    private List<NoticeDateBean> noticeDateBean;
    private List<NoticeDateBean> noticeDatesBean;
    private NoticeFragmentAdapter noticeFragmentAdapter;
    private Integer type1;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        type1 = getArguments().getInt("type");

    }

    @Override
    public View initView() {
        View view = View.inflate(mContext,R.layout.notice_fragement,null);
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
        noticeDateBean = new ArrayList<>();
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
                        getNoticeData();
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
                        getNoticeData();
                        xRefreshView.stopLoadMore();
                    }
                }, 1000);
            }
        });
        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState ==AbsListView.OnScrollListener.SCROLL_STATE_IDLE ){
                    firstPosition = lv_purchase.getFirstVisiblePosition();
                    childAt = lv_purchase.getChildAt(firstPosition);
                    posit = (childAt == null) ? 0 :childAt.getTop();
                }

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        NetContacts.getInstance().pageIndex = 1;
        //getPurchaseData();
        lv_purchase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoticeDateBean noticeDateBean = NoticesFragment3.this.noticeDateBean.get(i);

                NoticeDateBean recordsBean = noticeDatesBean.get(i);
                noticeDatesBean.get(i).setSee(true);
                Intent intent = new Intent(getContext(), NoticeDetailActivity.class);
                intent.putExtra("recordsBean", recordsBean);
                startActivity(intent);
//                Intent intent = new Intent(mContext, CheckListActivity.class);
//                intent.putExtra("StockTakingId",recordsBean.getStockTakingId());
//                intent.putExtra("LoginBean",loginBean);
//                intent.putExtra("type",type);
//                startActivity(intent);
            }
        });
    }



    private void getNoticeData() {

        new OrderManager().cmsdata(type1,new BaseNet.BaseCallback<NoticeBean>() {
            @Override
            public void messageSuccess(NoticeBean bean) {
                noticeDateBean = bean.getRecords();
                if (NetContacts.getInstance().pageIndex ==1){
                    noticeDatesBean = noticeDateBean;

                }
                else if (bean.getRecords().size()>1){
                    noticeDatesBean.addAll(noticeDateBean);
                }
                noticeFragmentAdapter = new NoticeFragmentAdapter(getContext(), noticeDatesBean);
                lv_purchase.setAdapter(noticeFragmentAdapter);
                lv_purchase.setSelectionFromTop(firstPosition,posit);
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
        if (enter &&isGetData){
            isGetData = true;
            getNoticeData();
        }else{
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isGetData){
            getNoticeData();
            isGetData = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }


}
