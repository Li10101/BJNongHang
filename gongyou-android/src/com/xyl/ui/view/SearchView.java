package com.xyl.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.base.BaseResource;
import com.xyl.domain.CasesBean;
import com.xyl.domain.CasesBean.Records;
import com.xyl.domain.DataBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.net.PersonnelManager;
import com.xyl.ui.activity.ApprolvalAllActivity;
import com.xyl.ui.activity.ApprovalActivity;
import com.xyl.ui.activity.EmergencyActivity;
import com.xyl.ui.activity.HomeActivity;
import com.xyl.ui.activity.LeaveActivity;
import com.xyl.ui.activity.OrderStateActivity;
import com.xyl.ui.activity.PurchaseActivity;
import com.xyl.ui.activity.ReimbursementActivity;
import com.xyl.ui.activity.SealActivity;
import com.xyl.ui.activity.StandbyActivity;
import com.xyl.ui.widget.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchView extends View implements PullToRefreshLayout.OnRefreshListener {

    private View searchView;
    private Context mContext;
    private EditText et_search;
    private ImageView iv_search_click;
    private List<Records> mWorkList;
    private List<Records> mSearchList = new ArrayList<Records>();
    private List<Records> mCopyList = new ArrayList<Records>();
    private AbsListView lv_search_result;
    private RotateAnimation loadingAnimation;
    private PullToRefreshLayout refreshLayout;
    private HashMap<String, Integer> resourceState;
    private MyBaseAdapter baseAdapter;
    private BaseCallback<CasesBean> baseCallback;
    private BaseCallback<SelectAllPersonalBean> allPersonnalCallback;
    private Handler mHandler = new Handler();
    private CasesBean casesBean;
    private OrderManager orderManager;
    private int searchFlag = 0;
    private DataBean dataBean;
    private TextView tv_search_cancel;

    private String state = "";
    private MySearchAdapter searchAdapter;
    private View headView;
    private int unble;
    private SelectAllPersonalBean allPersonalData;
    private List<SelectAllPersonalBean.RecordsBean> personalDataRecords;
    private PersonnelManager personnelManager;


    public SearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        searchView = View.inflate(context, R.layout.view_search, null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        searchView = View.inflate(context, R.layout.view_search, null);
    }

    public SearchView(Context context) {
        super(context);
        mContext = context;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        searchView = View.inflate(mContext, R.layout.view_search, null);
        initListView();
        et_search = (EditText) searchView.findViewById(R.id.et_search);
        iv_search_click = (ImageView) searchView.findViewById(R.id.iv_search_click);
        tv_search_cancel = (TextView) searchView.findViewById(R.id.tv_search_cancel);
    }

    public void initListView() {
        lv_search_result = (AbsListView) searchView.findViewById(R.id.content_view);
        ((ListView) lv_search_result).setDivider(null);

        refreshLayout = (PullToRefreshLayout) searchView.findViewById(R.id.refresh_view);
        refreshLayout.setOnRefreshListener(this);

        headView = View.inflate(mContext, R.layout.search_head, null);
        headView.setVisibility(VISIBLE);
        ((ListView) lv_search_result).addHeaderView(headView, null, false);

        loadingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(mContext,
                R.anim.rotating);
        // 添加匀速转动动画
        LinearInterpolator lir = new LinearInterpolator();
        loadingAnimation.setInterpolator(lir);
    }

    public void setFlag(int flag) {
        this.searchFlag = flag;
    }

    private void initListener() {
        iv_search_click.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                unble = 1;
                state = et_search.getText().toString();
                if (TextUtils.isEmpty(state)) {
                    Toast.makeText(mContext, "请输入查询内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                toggleInputState(false);
                loadSearchData();

            }
        });

        lv_search_result.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {

                SelectAllPersonalBean.RecordsBean recordsBean = personalDataRecords.get(position - 1);
                Intent leaveIntent = new Intent(mContext, ApprolvalAllActivity.class);
                leaveIntent.putExtra("LoginBean", ((HomeActivity) mContext).getIntent().getSerializableExtra("LoginBean"));
                switch (recordsBean.getRenShiType()) {
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        //请假
                        leaveIntent.putExtra("recordsBean", recordsBean);
                        leaveIntent.putExtra("type", recordsBean.getRenShiType());
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        //采购  PurchaseActivity
                        leaveIntent.putExtra("recordsBean", recordsBean);
                        leaveIntent.putExtra("type", recordsBean.getRenShiType());
                        break;
                    case 10:
                        //应急采购 EmergencyActivity

                        leaveIntent.putExtra("recordsBean", recordsBean);
                        leaveIntent.putExtra("type", recordsBean.getRenShiType());
                        break;
                    case 11:
                        //报销 ReimbursementActivity

                        leaveIntent.putExtra("recordsBean", recordsBean);
                        leaveIntent.putExtra("type", recordsBean.getRenShiType());

                    case 12:

                        //通用审批 ApprovalActivity
                        leaveIntent.putExtra("recordsBean", recordsBean);
                        leaveIntent.putExtra("type", recordsBean.getRenShiType());


                        break;
                    case 14:
                        //用印申请 SealActivity
                        leaveIntent.putExtra("recordsBean", recordsBean);
                        leaveIntent.putExtra("type", recordsBean.getRenShiType());
                        break;

                    case 16:
                        //备用金 StandbyActivity
                        leaveIntent.putExtra("recordsBean", recordsBean);
                        leaveIntent.putExtra("type", recordsBean.getRenShiType());
                        break;
                }
                mContext.startActivity(leaveIntent);
//                Intent intent = new Intent(mContext, OrderStateActivity.class);
//                intent.putExtra("flag", 2);
//                switch (searchFlag) {
//                    case 1:
//                        intent.putExtra("CasesBean", mWorkList.get(position - 1));
//                        break;
//                    case 4:
//                        intent.putExtra("CasesBean", mSearchList.get(position - 1));
//                        break;
//                }
//                intent.putExtra("LoginBean", ((HomeActivity) mContext).getIntent().getSerializableExtra("LoginBean"));
//                mContext.startActivity(intent);
//                Intent intent = new Intent(mContext, OrderStateActivity.class);
//                intent.putExtra("flag", 2);
//                switch (searchFlag) {
//                    case 1:
//                        intent.putExtra("CasesBean", mWorkList.get(position - 1));
//                        break;
//                    case 4:
//                        intent.putExtra("CasesBean", mSearchList.get(position - 1));
//                        break;
//                }
//                intent.putExtra("LoginBean", ((HomeActivity) mContext).getIntent().getSerializableExtra("LoginBean"));
//                mContext.startActivity(intent);


            }
        });

        tv_search_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                unble = 0;
                setFlag(1);
                lv_search_result.setAdapter(baseAdapter);
                et_search.setText("");
                iv_search_click.setVisibility(View.VISIBLE);
                tv_search_cancel.setVisibility(View.GONE);
                baseAdapter.notifyDataSetChanged();
                toggleInputState(false);
            }
        });

        lv_search_result.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当不滚动时
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    //判断滚动到底部
                    if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                        NetContacts.getInstance().pageIndex++;
                        if (et_search.length() > 0 && unble == 1) {
                            loadSearchData();
                        } else {
                            loadPersonalData();
                            //loadCaseData();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {

            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
            }

            @Override
            public void afterTextChanged(Editable et) {
                if (et.length() == 0) {
                    iv_search_click.setVisibility(View.VISIBLE);
                    tv_search_cancel.setVisibility(View.GONE);
                    return;
                }
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                et_search.requestFocus();

            }
        });
    }

    private void loadPersonalData() {
        new PersonnelManager().selectAllPersonal(new BaseCallback<SelectAllPersonalBean>() {
            @Override
            public void messageSuccess(SelectAllPersonalBean bean) {
                if (NetContacts.getInstance().pageIndex == 1) {
                    personalDataRecords = bean.getRecords();
                    lv_search_result.setAdapter(baseAdapter);
                } else if (bean.getRecords().size() > 1) {
                    personalDataRecords.addAll(bean.getRecords());
                }
                if (bean.getRecords().size() <= 1) {
                    Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                //添加
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private void loadCaseData() {
        new OrderManager().cases(new BaseCallback<CasesBean>() {
            @Override
            public void messageSuccess(CasesBean bean) {
                if (NetContacts.getInstance().pageIndex == 1) {
                    mWorkList = bean.records;
                    lv_search_result.setAdapter(baseAdapter);
                } else if (bean.records.size() > 1) {
                    mWorkList.addAll(bean.records);
                }
                if (bean.records.size() <= 1) {
                    Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                //添加
                baseAdapter.notifyDataSetChanged();

            }

            @Override
            public void connectFailure(MessageBean messageBean) {
            }

            @Override
            public void messageFailure(MessageBean backError) {
            }
        });
    }

    private void loadSearchData() {
        new OrderManager().Search(state.trim(), new BaseCallback<CasesBean>() {
            @Override
            public void messageSuccess(CasesBean bean) {
                setFlag(4);
                setSearchData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }

        });
    }

    private void setSearchData(CasesBean bean) {
        if (NetContacts.getInstance().pageIndex == 1) {
            mSearchList = bean.records;
            searchAdapter = new MySearchAdapter();
            lv_search_result.setAdapter(searchAdapter);
        } else if (bean.records.size() > 1) {
            mSearchList.addAll(bean.records);
        }
        if (bean.records.size() <= 1) {
            Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
        }
        //添加
        baseAdapter.notifyDataSetChanged();
        searchAdapter.notifyDataSetChanged();
    }

    public void initData() {
        resourceState = BaseResource.getResourceState();
        personnelManager = new PersonnelManager();
        allPersonnalCallback = new BaseCallback<SelectAllPersonalBean>() {
            @Override
            public void messageSuccess(SelectAllPersonalBean bean) {
                setAllPersonalData(bean);
            }

            @Override
            public void messageFailure(MessageBean backError) {
            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        };


//        orderManager = new OrderManager();
//        baseCallback = new BaseCallback<CasesBean>() {
//            @Override
//            public void messageSuccess(CasesBean bean) {
//                setCasesData(bean);
//            }
//
//            @Override
//            public void messageFailure(MessageBean backError) {
//            }
//
//            @Override
//            public void connectFailure(MessageBean messageBean) {
//
//            }
//        };
    }

    private void getAllPersion() {
        new PersonnelManager().selectAllPersonal(new BaseCallback<SelectAllPersonalBean>() {
            @Override
            public void messageSuccess(SelectAllPersonalBean bean) {
                et_search.setText("");
                setFlag(5);
                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_SUCCEED);
                if (NetContacts.getInstance().pageIndex == 1) {
                    personalDataRecords = bean.getRecords();
                    lv_search_result.setAdapter(baseAdapter);
                    if (personalDataRecords.size() == 0) {
                        Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                    }
                    baseAdapter.notifyDataSetChanged();
                }

                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    public void setData(CasesBean bean) {
        setFlag(1);
        NetContacts.getInstance().pageIndex = 0;
        iv_search_click.setVisibility(View.VISIBLE);
        tv_search_cancel.setVisibility(View.GONE);

        mWorkList = bean.records;
        baseAdapter = new MyBaseAdapter();
        lv_search_result.setAdapter(baseAdapter);

        getAllBack();
    }

    public void getAllBack() {
        NetContacts.getInstance().pageIndex++;
        new OrderManager().cases(new BaseCallback<CasesBean>() {
            @Override
            public void messageSuccess(CasesBean bean) {
                if (bean.records.size() <= 1) {
                    NetContacts.getInstance().pageIndex = 0;
                    return;
                }
                mWorkList.addAll(bean.records);
                if (baseAdapter != null) {
                    baseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
            }

            @Override
            public void messageFailure(MessageBean backError) {
            }
        });
    }

    public View getView() {
        searchView.setTag(this);
        return searchView;
    }

    public void setCasesData(CasesBean bean) {
        et_search.setText("");
        setFlag(1);
        mWorkList = bean.records;
        baseAdapter = new MyBaseAdapter();
        lv_search_result.setAdapter(baseAdapter);
        if (mWorkList.size() == 0) {
            Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
        }
        baseAdapter.notifyDataSetChanged();
    }

    public void setAllPersonalData(SelectAllPersonalBean allPersonalData) {
        et_search.setText("");
        setFlag(5);
        personalDataRecords = allPersonalData.getRecords();
        baseAdapter = new MyBaseAdapter();
        lv_search_result.setAdapter(baseAdapter);
        if (personalDataRecords.size() == 0) {
            Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
        }
        baseAdapter.notifyDataSetChanged();

    }

    public SelectAllPersonalBean getAllPersonalData() {
        return allPersonalData;
    }


    public void toggleInputState(boolean isShow) {
        final InputMethodManager imm = (InputMethodManager) (mContext.getApplicationContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
//		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        if (isShow) {
            //显示输入法
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imm.showSoftInput(et_search, 0);
                }
            }, 300);
        } else {
            //隐藏输入法
            imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
        }
    }

    public void refreshData() {
        new OrderManager().cases(new BaseCallback<CasesBean>() {
            @Override
            public void messageSuccess(CasesBean bean) {
                mWorkList = bean.records;
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
            }

            @Override
            public void messageFailure(MessageBean backError) {
            }
        });
    }

    @Override
    public void onRefresh() {
        getAllPersion();
//        new OrderManager().cases(new BaseCallback<CasesBean>() {
//            @Override
//            public void messageSuccess(CasesBean bean) {
//                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_SUCCEED);
//                if (NetContacts.getInstance().pageIndex == 1) {
//                    mWorkList = bean.records;
//                    lv_search_result.setAdapter(baseAdapter);
//                } else if (bean.records.size() > 1) {
//                    mWorkList.addAll(bean.records);
//                }
//                if (bean.records.size() <= 1) {
//                    Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                baseAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void connectFailure(MessageBean messageBean) {
//                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
//            }
//
//            @Override
//            public void messageFailure(MessageBean backError) {
//                refreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
//            }
//        });

    }


    class MySearchAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return mSearchList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return mSearchList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup arg2) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_search_result, null);
                holder.ivState = (ImageView) convertView.findViewById(R.id.iv_search_state);
                holder.tvPriority = (TextView) convertView.findViewById(R.id.tv_search_priority);
                holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_search_address);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_search_time);
                holder.tv_search_caseArea = (TextView) convertView.findViewById(R.id.tv_search_caseArea);
                holder.tvDes = (TextView) convertView.findViewById(R.id.tv_search_des);
                holder.tvState = (TextView) convertView.findViewById(R.id.tv_search_state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Integer resId = resourceState.get("已维修");
            Records rec = mSearchList.get(position);

            if (rec != null) {
                holder.tvPriority.setText(rec.repairCaseCode + "(" + rec.priorityDisplay + ")");
                holder.tvAddress.setText("地址：" + rec.address);
                holder.tvTime.setText("时间：" + rec.requestTime);
                holder.tv_search_caseArea.setText("工单区域：" + rec.caseAreaDisplay);
                holder.tvDes.setText("描述：" + rec.description);
                holder.tvState.setText(rec.statusDisplay);
                resId = resourceState.get(rec.statusDisplay);
            }

            if (resId != null) {
                holder.ivState.setImageResource(resId);
            }

            return convertView;
        }
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            int count = 0;
            switch (searchFlag) {
                case 1:
                    count = mWorkList.size();
                    break;
                case 2:
                    count = 1;
                    break;
                case 3:
                    count = mSearchList.size();
                    break;
                case 5:
                    count = personalDataRecords.size();
                    break;
            }
            return count;
        }

        @Override
        public Object getItem(int position) {
            return mWorkList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_search_result, null);
                holder.ivState = (ImageView) convertView.findViewById(R.id.iv_search_state);
                holder.tvPriority = (TextView) convertView.findViewById(R.id.tv_search_priority);
                holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_search_address);
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_search_time);
                holder.tv_search_caseArea = (TextView) convertView.findViewById(R.id.tv_search_caseArea);
                holder.tvDes = (TextView) convertView.findViewById(R.id.tv_search_des);
                holder.tvState = (TextView) convertView.findViewById(R.id.tv_search_state);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Integer resId = resourceState.get("fix");
            //Records records = mWorkList.get(position);
            //Records records = personalDataRecords.get(position);
            switch (searchFlag) {
                case 1:
                    Records records = mWorkList.get(position);
                    if (records != null) {
                        holder.tvPriority.setText(records.repairCaseCode + "(" + records.priorityDisplay + ")");
                        holder.tvAddress.setText("地址：" + records.address);
                        holder.tvTime.setText("时间：" + records.requestTime);
                        holder.tvDes.setText("描述：" + records.description);
                        holder.tvState.setText(records.statusDisplay);
                        holder.tv_search_caseArea.setText("区域：" + records.caseAreaDisplay);
                        resId = resourceState.get(records.status);
                    }

                    break;
                case 2:
                    holder.tvPriority.setText(dataBean.repairCaseCode + "(" + dataBean.priorityDisplay + ")");
                    holder.tvAddress.setText("地址：" + dataBean.address);
                    holder.tvTime.setText("时间：" + dataBean.requestTime);
                    holder.tvDes.setText("描述：" + dataBean.description);
                    holder.tvState.setText(dataBean.statusDisplay);
                    resId = resourceState.get(dataBean.status);
                    break;
                case 3:
                    Records rec = mWorkList.get(position);
                    if (rec != null) {
                        holder.tvPriority.setText(rec.repairCaseCode + "(" + rec.priorityDisplay + ")");
                        holder.tvAddress.setText("地址：" + rec.address);
                        holder.tvTime.setText("时间：" + rec.requestTime);
                        holder.tv_search_caseArea.setText("区域：" + rec.caseAreaDisplay);
                        holder.tvDes.setText("描述：" + rec.description);
                        holder.tvState.setText(rec.statusDisplay);
                        resId = resourceState.get(rec.status);
                    }
                    break;
                case 5:
                    SelectAllPersonalBean.RecordsBean recordsBean = personalDataRecords.get(position);
                    if (recordsBean != null) {
                        holder.tvPriority.setText("申请人:"+recordsBean.getShenQingRen());
                        holder.tvAddress.setText("类型:"+recordsBean.getRenShiTypeDisplay());
                        holder.tvTime.setText("时间:"+recordsBean.getCreateTime());
                        holder.tv_search_caseArea.setVisibility(GONE);
                        holder.tvDes.setVisibility(GONE);
                        holder.ivState.setImageResource(R.drawable.search_top);
                        holder.tvState.setText(recordsBean.getStatusDisplay());
                    }
                    break;
            }
            if (resId != null) {
                holder.ivState.setImageResource(resId);
            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView tv_search_caseArea;
        ImageView ivState;
        TextView tvPriority;
        TextView tvAddress;
        TextView tvTime;
        TextView tvDes;
        TextView tvState;
    }

}