package com.xyl.net;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.AssetBean;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.FindByCategoryNoBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.OutCaseBean;
import com.xyl.domain.ProductBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;

import org.apache.http.Header;

import java.util.List;

/**
 * 08 资产管理
 * 
 * @author peng
 * 
 */
public class AssetManager extends BaseNet {

	/**
	 * 有问题 1. 查询资产分类列表 数据获得需详细验证
	 * 
	 * @param baseCallback
	 */
	public void assets(final BaseCallback<List<AssetBean>> baseCallback) {

		// baseListMethod(null, NetContacts.getInstance().CATEGORIES, baseCallback,
		// CategoriesBean.class);

		RequestParams params = new RequestParams();

		// baseListMethod(null, NetContacts.getInstance().STAFFLIST, baseCallback,
		// StaffListBean.class);

		params.addHeader(
				"Cookie",
				SharedPreferencesUtil.getString(
						BaseApplication.getBaseApplication(), "cookie"));

		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.getInstance().USERPHYSICALASSET, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						baseCallback.connectFailure(new MessageBean(arg0
								.getExceptionCode(), arg1));
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						String successInfo = arg0.result;
						
						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
//				TODO:			if (!TextUtils.isEmpty(cookie)) {
//								SharedPreferencesUtil.setString(
//										BaseApplication.getBaseApplication(),
//										"cookie", cookie);
//							}
						}

						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}

						List<AssetBean> list = gson.fromJson(successInfo,
								new TypeToken<List<AssetBean>>() {
								}.getType());

						baseCallback.messageSuccess(list);
					}
				});
	}

	public void findByAssetNo(String assetNo,
			BaseCallback<FindByAssetNoBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("physicalAssetId", assetNo);
		baseMethod(params, NetContacts.getInstance().BYPHYSICALASSETID, baseCallback,
				FindByAssetNoBean.class);
	}
	
	/**
	 * 筛选方法
	 * @param categoryNo
	 * @param state
	 * @param baseCallback
	 */
	public void findSelectCategoryNo(String categoryNo,String state,BaseCallback<FindByCategoryNoBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("categoryNo", categoryNo);
		params.addBodyParameter("state", state);
		baseMethod(params, NetContacts.getInstance().SCREEN_FILTRATE, baseCallback,
				FindByCategoryNoBean.class);
	}
	
	
	/**
	 * 通过设备编号查询设备
	 * 
	 * @param baseCallback
	 * @param userEquipmentId
	 */
	public void findByAssetEquipmentNo(String userEquipmentId,
		BaseCallback<FindByAssetNoBean.RecordsBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("userEquipmentId", userEquipmentId);
		//TODO
		baseMethodone(params, NetContacts.getInstance().BYUSEREQUIPMENTID, baseCallback,
				FindByAssetNoBean.RecordsBean.class);
	}

	public void findAssetEquipmentNo(String url,
									   BaseCallback<FindByAssetNoBean.RecordsBean> baseCallback){
		RequestParams params = new RequestParams();
		//TODO
		baseMethodone(params, url, baseCallback,
				FindByAssetNoBean.RecordsBean.class);
	}
	public void editUpdate(

			               String userEquipmentId,
			               String inventoryResults,
						   String physicalAssetDes,
						   String physicalAssetStatus,
						   String staffId,
						   String userEquiAssetsUsed,
						   String statedLocality,
						   String description,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("userEquipmentId", userEquipmentId);
		params.addBodyParameter("inventoryResults", inventoryResults);
		params.addBodyParameter("physicalAssetDes", physicalAssetDes);
		params.addBodyParameter("physicalAssetStatus", physicalAssetStatus);
		params.addBodyParameter("staffId", staffId);
		params.addBodyParameter("userEquiAssetsUsed", userEquiAssetsUsed);
		params.addBodyParameter("statedLocality", statedLocality);
		params.addBodyParameter("description", description);

		entity(params, NetContacts.getInstance().EDITUPDATE, callback);
	}

	public void getCaseSimpleVos( BaseCallback<OutCaseBean> baseCallback) {
		RequestParams params = new RequestParams();
		baseMethod(params,NetContacts.getInstance().GETCASESIMPLEVOS, baseCallback, OutCaseBean.class);
	}


}
