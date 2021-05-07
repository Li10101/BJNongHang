package com.xyl.ui.activity;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.adapter.SearchDataAdapter;
import com.xyl.base.BaseActivity;
import com.xyl.base.BaseNet;
import com.xyl.domain.BuildBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.SearchGoodsBean;
import com.xyl.global.NetContacts;
import com.xyl.net.AssetManager;
import com.xyl.net.LibraryManager;
import com.xyl.net.OrderManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.toolbar_left_btn)
    Button toolbarLeftBtn;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search_click)
    ImageView ivSearchClick;
    @BindView(R.id.tv_search_cancel)
    TextView tvSearchCancel;
    @BindView(R.id.tv_select_building)
    TextView tvSelectBuilding;
    @BindView(R.id.lv_search)
    RecyclerView lvSearch;
    private int flag;
    private String seachText;
    private String buildingId = "1";
    private List<BuildBean> buildBeans;
    @Override
    protected void initData() {
        NetContacts.getInstance().pageIndex = 1;
        getGoodsData();
    }

    private void getBuildingId() {
        new LibraryManager().findAllBuilding(new BaseNet.BaseCallback<List<BuildBean>>() {
            @Override
            public void messageSuccess(List<BuildBean> bean) {
                buildBeans = bean;
                final LinearLayout inflate = (LinearLayout) View.inflate(SearchActivity.this, R.layout.pp_group, null);
                final PopupWindow popupWindow = new PopupWindow(inflate, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(tvSelectBuilding, Gravity.CENTER_HORIZONTAL, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                ListView lv_group = inflate.findViewById(R.id.lv_group);
                lv_group.setAdapter(new BuildingAdapter());
                lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        BuildBean buildBean = buildBeans.get(position);
                        tvSelectBuilding.setText(buildBean.getBuildingName()+"项目");
                        buildingId = buildBean.getBuildingId()+"";
                        popupWindow.dismiss();
                        getGoodsData();
                    }
                });
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });
    }

    private void getGoodsData() {
        new OrderManager().goodsSearch(buildingId, seachText, new BaseNet.BaseCallback<SearchGoodsBean>() {
            @Override
            public void messageSuccess(SearchGoodsBean bean) {
                SearchDataAdapter searchDataAdapter = new SearchDataAdapter(SearchActivity.this, bean, flag);
                lvSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                lvSearch.setAdapter(searchDataAdapter);

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
    protected void initView() {
        flag = getIntent().getIntExtra("flag", 0);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public boolean isOpenEventBus() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.toolbar_left_btn, R.id.iv_search_click, R.id.tv_search_cancel, R.id.tv_select_building})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_btn:
                finish();
                break;
            case R.id.iv_search_click:
                break;
            case R.id.tv_search_cancel:
                break;
            case R.id.tv_select_building:
                getBuildingId();
                break;
        }
    }

    @OnClick(R.id.iv_search_click)
    public void onViewClicked() {
        seachText = etSearch.getText().toString();
        getGoodsData();
    }


    class BuildingAdapter extends BaseAdapter {

        private TextView tvBuilding;

        @Override
        public int getCount() {
            return buildBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return buildBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(SearchActivity.this, R.layout.item_text, null);
            tvBuilding = convertView.findViewById(R.id.tv_content_building);
            tvBuilding.setText(buildBeans.get(position).getBuildingName());
            return convertView;
        }
    }
}
