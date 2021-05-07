package com.xyl.ui.activity;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xyl.R;
import com.xyl.base.BaseNet;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.StaffListBean;
import com.xyl.net.AssetManager;
import com.xyl.net.MixNet;
import com.xyl.utils.ToastUtil;

import java.util.List;

import androidx.annotation.IdRes;

public class EditActivity extends Activity implements View.OnClickListener {
    private FindByAssetNoBean.RecordsBean recordsBean;
    private RadioGroup rgInventory;
    private RadioButton rbInventoryPz;
    private RadioButton rbInventoryPk;
    private RadioButton rbInventoryPy;
    private EditText etPhysicalDes;
    private RadioGroup etPhysicalStatus;
    private RadioButton etPhysicalStatusZy;
    private RadioButton etPhysicalStatusXz;
    private RadioButton etPhysicalStatusBs;
    private RadioButton etPhysicalStatusQt;
    private EditText etEquiAssetsUsed;
    private EditText etStatedLocality;
    private EditText etDescription;
    private Button btEditCommit;
    private Button btEditClear;
    private TextView tv_responsibility;
    private View inflate;
    private PopupWindow pp_group;
    private ListView lv_group;
    private int num;
    private FindByAssetNoBean.RecordsBean assetRecordsBean;
    private String sPhysicalDes;
    private String sEquiAssetsUsed;
    private String sStatedLocality;
    private Double sUnitPrice;
    private String sDescription;
    private String sResponsibility;
    private MyGroupAdater myGroupAdater;
    private List<StaffListBean> staffListBeen;
    private int panDnum;
    private int staffId;
    private ImageView iv_title_left;
    private TextView tv_title;
    private TextView tv_title_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        findViews();
        initdata();
        initListener();
    }

    private void initListener() {
        setAssetData();
    }

    private void initdata() {
        iv_title_left.setVisibility(View.VISIBLE);
        iv_title_left.setImageResource(R.drawable.rightmenu);
        tv_title.setText("可变更信息");
        new MixNet().getAssetStaffList(new BaseNet.BaseCallback<List<StaffListBean>>() {
            @Override
            public void messageSuccess(List<StaffListBean> bean) {
                staffListBeen = bean;
                myGroupAdater = new MyGroupAdater();
                lv_group.setAdapter(myGroupAdater);
            }

            @Override
            public void messageFailure(MessageBean backError) {

            }

            @Override
            public void connectFailure(MessageBean messageBean) {

            }
        });

        initPopGroup();
        recordsBean = (FindByAssetNoBean.RecordsBean) getIntent().getSerializableExtra("AssetRecords");
        switch (recordsBean.getInventoryResults()){
            case 0:
                rbInventoryPz.setChecked(true);
                break;
            case 1:
                rbInventoryPk.setChecked(true);
                break;
            case 2:
                rbInventoryPy.setChecked(true);
                break;
        }

        switch (recordsBean.getPhysicalAssetStatus()){
            case 0:
                etPhysicalStatusZy.setChecked(true);
                break;
            case 1:
                etPhysicalStatusXz.setChecked(true);
                break;
            case 2:
                etPhysicalStatusBs.setChecked(true);
                break;
            case 3:
                etPhysicalStatusQt.setChecked(true);
                break;
        }
        tv_responsibility.setText(recordsBean.getResponsibleName());
        staffId = recordsBean.getResponsibleId();
        etEquiAssetsUsed.setText(recordsBean.getUserEquiAssetsUsed());
        etStatedLocality.setText(recordsBean.getStatedLocality());
        etDescription.setText(recordsBean.getDescription());
        etPhysicalDes.setText(recordsBean.getPhysicalAssetDes());

    }

    private void setAssetData() {
        rgInventory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                radioGroup.check(i);
                int inventoryId = radioGroup.getCheckedRadioButtonId();
                switch (inventoryId){
                    case R.id.rb_inventory_pz:
                        panDnum = 0;
                        break;
                    case R.id.rb_inventory_pk:
                        panDnum = 1;
                        break;
                    case R.id.rb_inventory_py:
                        panDnum = 2;
                        break;
                }
            }
        });

        etPhysicalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int physicalStatus = radioGroup.getCheckedRadioButtonId();
                switch (physicalStatus){
                    case R.id.et_physicalStatus_zy:
                        num = 0;
                        break;
                    case R.id.et_physicalStatus_xz:
                        num = 1;
                        break;
                    case R.id.et_physicalStatus_bs:
                        num = 2;
                        break;
                    case R.id.et_physicalStatus_qt:
                        num = 3;
                        break;
                }
            }
        });
    }



    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-01-17 18:14:23 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        iv_title_left = (ImageView) this.findViewById(R.id.iv_title_left);
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        tv_title_right = (TextView) this.findViewById(R.id.tv_title_right);
        rgInventory = (RadioGroup)findViewById( R.id.rg_inventory );
        rbInventoryPz = (RadioButton)findViewById( R.id.rb_inventory_pz );
        rbInventoryPk = (RadioButton)findViewById( R.id.rb_inventory_pk );
        rbInventoryPy = (RadioButton)findViewById( R.id.rb_inventory_py );
        etPhysicalDes = (EditText)findViewById( R.id.et_physicalDes );
        etPhysicalStatus = (RadioGroup)findViewById( R.id.et_physicalStatus );
        etPhysicalStatusZy = (RadioButton)findViewById( R.id.et_physicalStatus_zy );
        etPhysicalStatusXz = (RadioButton)findViewById( R.id.et_physicalStatus_xz );
        etPhysicalStatusBs = (RadioButton)findViewById( R.id.et_physicalStatus_bs );
        etPhysicalStatusQt = (RadioButton)findViewById( R.id.et_physicalStatus_qt );
        etEquiAssetsUsed = (EditText)findViewById( R.id.et_equiAssetsUsed );
        etStatedLocality = (EditText)findViewById( R.id.et_statedLocality );
        etDescription = (EditText)findViewById( R.id.et_description );
        btEditCommit = (Button)findViewById( R.id.bt_edit_commit );
        btEditClear = (Button)findViewById( R.id.bt_edit_clear );
        tv_responsibility = (TextView) findViewById( R.id.tv_responsibility );
        inflate = View.inflate(EditActivity.this, R.layout.pp_group, null);
        lv_group = (ListView) inflate.findViewById(R.id.lv_group);
        rbInventoryPz.setOnClickListener( this );
        rbInventoryPk.setOnClickListener( this );
        rbInventoryPy.setOnClickListener( this );
        etPhysicalStatusZy.setOnClickListener( this );
        etPhysicalStatusXz.setOnClickListener( this );
        etPhysicalStatusBs.setOnClickListener( this );
        etPhysicalStatusQt.setOnClickListener( this );
        btEditCommit.setOnClickListener( this );
        btEditClear.setOnClickListener( this );
        iv_title_left.setOnClickListener( this );
        tv_responsibility.setOnClickListener( this );
        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                staffId = staffListBeen.get(i).staffId;
                tv_responsibility.setText(staffListBeen.get(i).name);
                pp_group.dismiss();
            }
        });
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-01-17 18:14:23 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btEditCommit ) {
            sPhysicalDes = etPhysicalDes.getText().toString();
            sEquiAssetsUsed = etEquiAssetsUsed.getText().toString();
            sStatedLocality = etStatedLocality.getText().toString();
            sDescription = etDescription.getText().toString();
            sResponsibility = this.tv_responsibility.getText().toString();
            new AssetManager().editUpdate(recordsBean.getUserEquipmentId()+"",panDnum+"",sPhysicalDes, num+"",staffId+"",sEquiAssetsUsed,sStatedLocality,sDescription,new BaseNet.EntityCallback() {
                @Override
                public void connectCallback(BaseNet.EntityrResult entityrResult) {
                    ToastUtil.showToast("成功");
                }
            });
            setResult(10);
            finish();
        } else if ( v == btEditClear ) {
            finish();
        }else if ( v == tv_responsibility ) {
                    if (pp_group.isShowing()) {
                        pp_group.dismiss();
                    } else {
                        pp_group.showAsDropDown(tv_responsibility);
                    }
        } else if(v == iv_title_left){
            finish();
        }

        }
    private void initPopGroup() {
        pp_group = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pp_group.setOutsideTouchable(false);
        pp_group.setContentView(inflate);
    }
    class MyGroupAdater extends BaseAdapter {

        @Override
        public int getCount() {
            return staffListBeen.size();
        }

        @Override
        public Object getItem(int i) {
            return staffListBeen.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = View.inflate(EditActivity.this, R.layout.item_select_group, null);
                viewHolder.tv_select_group = (TextView) view.findViewById(R.id.tv_select_group);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            StaffListBean staffListBean = staffListBeen.get(i);
            viewHolder.tv_select_group.setText(staffListBean.name);
            return view;
        }


    }

    class ViewHolder {
        private TextView tv_select_group;
    }
}
