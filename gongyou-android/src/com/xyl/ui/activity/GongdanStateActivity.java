package com.xyl.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bumptech.glide.Glide;
import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.base.BaseResource;
import com.xyl.domain.CasesBean;
import com.xyl.domain.CasesBean.Records;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.ui.widget.PullToRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GongdanStateActivity extends Activity implements PullToRefreshLayout.OnRefreshListener {
    private ListView gdcontent_view_order;
    private MyBaseAdapter baseAdapter;
    private List<Records> LtjsBean;
    private LoginBean loginBean;
    private CasesBean cases;
    private ImageView iv_title_left;
    private TextView tv_title;
    private PullToRefreshLayout pullToRefreshLayout;

    private HashMap<String, Integer> resourceState;
    private ArrayList<FindByAssetNoBean.RecordsBean> ltAssetBean;
    private int flage;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongdanorder);
        gdcontent_view_order = (ListView) findViewById(R.id.gdcontent_view_order);
        iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
        tv_title = (TextView) findViewById(R.id.tv_title);
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.refresh_view_order);

        initData();
        initListener();
    }

    private void initListener() {
        gdcontent_view_order.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                if (flage==1){
                    Intent intent = new Intent(GongdanStateActivity.this, OrderStateActivity.class);
                    intent.putExtra("LoginBean", getIntent().getSerializableExtra("LoginBean"));
                    intent.putExtra("CasesBean", LtjsBean.get(position));
                    intent.putExtra("flag", 2);
                    startActivity(intent);
                }else if (flage==2){
                    Intent intent = new Intent(GongdanStateActivity.this, OrderStateActivity.class);
                    intent.putExtra("LoginBean", getIntent().getSerializableExtra("LoginBean"));
                    intent.putExtra("FindByAssetNoBean", ltAssetBean.get(position));
                    intent.putExtra("flag", 10);
                    startActivity(intent);
                }
            }

        });
        iv_title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pullToRefreshLayout.setOnRefreshListener(this);


        gdcontent_view_order.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    //判断滚动到底部
                    if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                        NetContacts.getInstance().pageIndex++;
                        //修改了6.22
                        setAssectData();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
            }
        });

    }

    private void initData() {
        iv_title_left.setImageResource(R.drawable.rightmenu);
        iv_title_left.setVisibility(View.VISIBLE);
        loginBean = (LoginBean) getIntent().getSerializableExtra("LoginBean");
        flage = getIntent().getIntExtra("flag",1);
        resourceState = BaseResource.getResourceState();
        ltAssetBean = new ArrayList<>();
        baseAdapter = new MyBaseAdapter();
        NetContacts.getInstance().pageIndex = 1;
        if (flage ==1){
            String data = (String) getIntent().getSerializableExtra("DataString");
            JSONObject jsonobject = JSONObject.fromObject(data);
            setCaseData(jsonobject);
        }else if (flage == 2){
            status = (String) getIntent().getSerializableExtra("status");
            setAssectData();
        }
        tv_title.setText("");
    }

    private void setAssectData() {
        new OrderManager().getZiChanData(status, new BaseNet.BaseCallback<FindByAssetNoBean>() {
            @Override
            public void messageSuccess(FindByAssetNoBean bean) {
                if (NetContacts.getInstance().pageIndex == 1){
                    ltAssetBean = (ArrayList<FindByAssetNoBean.RecordsBean>) bean.getRecords();
                    gdcontent_view_order.setAdapter(baseAdapter);
                }else if (bean.getRecords().size() > 1) {
                    ltAssetBean.addAll(bean.getRecords());
                }
                if (bean.getRecords().size() <= 1) {
                    Toast.makeText(GongdanStateActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_SUCCEED);
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void messageFailure(MessageBean backError) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.REFRESH_FAIL);
            }
        });
    }


    private void setCaseData(JSONObject jsonobject) {
        CasesBean cases = new CasesBean();
        Records record = null;
        //获取一个json数组
        JSONArray array = jsonobject.getJSONArray("records");
        //将json数组 转换成 List<PassPortForLendsEntity>泛型
        LtjsBean = new ArrayList<Records>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = (JSONObject) array.get(i);
            record = cases.new Records();
            record.address = object.getString("address");
            record.repairCaseCode = object.getString("repairCaseCode");
            record.priorityDisplay = object.getString("priorityDisplay");
            record.status = object.getString("status");
            record.requestTime = object.getString("requestTime");
            record.description = object.getString("description");
            record.statusDisplay = object.getString("statusDisplay");
            record.caseAreaDisplay = object.getString("caseAreaDisplay");
            LtjsBean.add(i, record);
        }
        gdcontent_view_order.setAdapter(baseAdapter);
    }

    @Override
    public void onRefresh() {
        setAssectData();

    }

    private void setZiChanData() {

    }

    class MyBaseAdapter extends BaseAdapter {
        Integer resId;

        @Override
        public int getCount() {
            if (flage==1){
                return LtjsBean.size();
            }else if (flage == 2){
                return ltAssetBean.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(GongdanStateActivity.this,
                        R.layout.item_search_result, null);
                holder.ivState = (ImageView) convertView
                        .findViewById(R.id.iv_search_state);
                holder.tvNumber = (TextView) convertView
                        .findViewById(R.id.tv_search_priority);
                holder.tvAddress = (TextView) convertView
                        .findViewById(R.id.tv_search_address);
                holder.tvTime = (TextView) convertView
                        .findViewById(R.id.tv_search_time);
                holder.caseArea = (TextView) convertView
                        .findViewById(R.id.tv_search_caseArea);
                holder.tvDes = (TextView) convertView
                        .findViewById(R.id.tv_search_des);
                holder.tvState = (TextView) convertView
                        .findViewById(R.id.tv_search_state);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (flage==1){
                Records records = LtjsBean.get(position);
                holder.tvNumber.setText(records.repairCaseCode);
                holder.tvAddress.setText("地址：" + records.address);
                holder.tvTime.setText("时间：" + records.requestTime);
                holder.caseArea.setText("工单区域："+records.caseAreaDisplay);
                holder.tvDes.setText("描述：" + records.description);
                holder.tvState.setText(records.statusDisplay);
                resId = resourceState.get(records.status);
                if (resId != null) {
                    holder.ivState.setImageResource(resId);
                }
            }else if (flage ==2){
                FindByAssetNoBean.RecordsBean recordsBean = ltAssetBean.get(position);
                holder.tvNumber.setText(recordsBean.getUserEquipmentId()+"");
                holder.tvAddress.setText("名称：" + recordsBean.getPhysicalAssetDes());
                holder.tvTime.setVisibility(View.GONE);
                holder.caseArea.setVisibility(View.GONE);
                holder.tvDes.setText("类别:" + recordsBean.getUserPhysicalAssetName());
                holder.tvState.setText(recordsBean.getInventoryResultsDisplay());
                String omage_url = NetContacts.getInstance()
                        .SERVER_URL+"/"+recordsBean.getUserPhysicalAsset().getPhysicalAssetImg();
                if (!recordsBean.getUserPhysicalAsset().getPhysicalAssetImg().equals("")){
                    Glide.with(getApplicationContext()).load(omage_url).into(holder.ivState);
                }else{
                    holder.ivState.setImageResource(R.drawable.worker);
                }
                /*resId = resourceState.get(records.status);
                if (resId != null) {
                    holder.ivState.setImageResource(resId);
                }*/
            }

            return convertView;
        }
    }
    class ViewHolder {
        public ImageView ivState;
        public TextView tvNumber;
        public TextView tvAddress;
        public TextView tvTime;
        public TextView caseArea;
        public TextView tvDes;
        public TextView tvState;
    }
}
