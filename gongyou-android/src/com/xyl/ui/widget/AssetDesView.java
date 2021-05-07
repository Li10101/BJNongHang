  package com.xyl.ui.widget;

  import android.app.Activity;
  import android.content.Context;
  import android.util.AttributeSet;
  import android.view.View;
  import android.widget.AdapterView;
  import android.widget.ListView;

  import com.xyl.R;
  import com.xyl.adapter.AssetDesAdapter;
  import com.xyl.domain.FindByAssetNoBean;
  import com.xyl.domain.LoginBean;
  import com.xyl.utils.UIUtils;
  public class AssetDesView extends View {

      private Context mContext;
      private View view_asset_des;
      private ListView lv_workdes;
      private FindByAssetNoBean.RecordsBean recordsBean;
      private AssetDesAdapter assetDesAdapter;

      public AssetDesView(Context context, AttributeSet attrs, int defStyle) {
          super(context, attrs, defStyle);
          mContext = context;
          initView();
          initListener();
      }

      public AssetDesView(Context context, AttributeSet attrs) {
          super(context, attrs);
          mContext = context;
          initView();
          initListener();
      }

      public AssetDesView(Context context) {
          super(context);
          mContext = context;
          initView();
          initListener();
      }

      private void initView() {
          view_asset_des = View.inflate(mContext, R.layout.view_asset_des, null);
          lv_workdes = (ListView) view_asset_des
                  .findViewById(R.id.lv_assetdes);
      }

      private void initListener() {
          LoginBean loginBean = (LoginBean)(((Activity)mContext).getIntent().getSerializableExtra("LoginBean"));
          lv_workdes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  if (onItemClickListener!=null){
                      onItemClickListener.OnItemClick(recordsBean,i);
                  }
              }
          });

      }

      private boolean isEmpty = false;
      public AssetDesView setData(String type, FindByAssetNoBean.RecordsBean recordsBean, int flag) {
          if (recordsBean !=null){
              this.recordsBean = recordsBean;
              assetDesAdapter = new AssetDesAdapter(recordsBean,mContext);

          }
          lv_workdes.setAdapter(assetDesAdapter);
          UIUtils.setListViewHeightBasedOnChildren(lv_workdes);
          isEmpty = false;
          return this;
      }

      public View getView() {
          if(isEmpty){
              return null;
          }
          return view_asset_des;
      }

      public ListView getListView() {

          return lv_workdes;
      }

      public interface OnItemClickListener{
          void OnItemClick(FindByAssetNoBean.RecordsBean recordsBean,int i);
      }

      private OnItemClickListener onItemClickListener;

      public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
          this.onItemClickListener = onItemClickListener;
      }
  }
