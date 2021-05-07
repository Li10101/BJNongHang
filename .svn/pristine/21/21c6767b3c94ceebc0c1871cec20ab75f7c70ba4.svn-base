package com.xyl.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.domain.FindByAssetNoBean;

public class SeeAssetContentActivity extends Activity {
    private TextView tv_title;
    private TextView tvPdjieguo;
    private TextView tvSwzc;
    private TextView tvSwzczt;
    private TextView tvZrr;
    private TextView tvZcyt;
    private TextView tvDdms;
    private TextView tvDj;
    private TextView tvBz;
    private FindByAssetNoBean.RecordsBean.UserEquiHistoryVosBean assetrecordsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_asset_content);
        findViews();
        assetrecordsBean = (FindByAssetNoBean.RecordsBean.UserEquiHistoryVosBean) getIntent().getSerializableExtra("AssetrecordsBean");
        initData();
    }

    private void initData() {
        tv_title.setText("变更结果");
        tvPdjieguo.setText("盘亏结果:"+assetrecordsBean.getInventoryResultsDisplay());
        tvSwzc.setText("实物资产描述"+assetrecordsBean.getPhysicalAssetDes());
        tvSwzczt.setText("实物资产状态:"+assetrecordsBean.getPhysicalAssetStatusDisplay());
        tvZrr.setText("责任人:"+assetrecordsBean.getLoginUserName());
        tvZcyt.setText("资产用途:"+assetrecordsBean.getUserEquiAssetsUsed());
        tvDdms.setText("地点描述:"+assetrecordsBean.getStatedLocality());
        tvBz.setText("备注:"+assetrecordsBean.getDescription());
    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-01-18 17:27:25 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tvPdjieguo = (TextView)findViewById( R.id.tv_pdjieguo );
        tvSwzc = (TextView)findViewById( R.id.tv_swzc );
        tvSwzczt = (TextView)findViewById( R.id.tv_swzczt );
        tvZrr = (TextView)findViewById( R.id.tv_zrr );
        tvZcyt = (TextView)findViewById( R.id.tv_zcyt );
        tvDdms = (TextView)findViewById( R.id.tv_ddms );
        tvDj = (TextView)findViewById( R.id.tv_dj );
        tvBz = (TextView)findViewById( R.id.tv_bz );
    }

}
