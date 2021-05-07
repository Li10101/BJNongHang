package com.xyl.net;

import java.util.List;

import org.apache.http.Header;

import com.google.gson.reflect.TypeToken;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.AlarmsBean;
import com.xyl.domain.CategoriesBean;
import com.xyl.domain.FindByCategoryNoBean;
import com.xyl.domain.FindByEquipmentNoBean;
import com.xyl.domain.MessageBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 08 设备管理
 * 
 * @author peng
 * 
 */
public class DeviceManager extends BaseNet {

	/**
	 * 有问题 1. 查询设备分类列表 数据获得需详细验证
	 * 
	 * @param baseCallback
	 */
	public void categories(final BaseCallback<List<CategoriesBean>> baseCallback) {

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
		httpUtils.send(HttpMethod.POST, NetContacts.getInstance().CATEGORIES, params,
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

						List<CategoriesBean> list = gson.fromJson(successInfo,
								new TypeToken<List<CategoriesBean>>() {
								}.getType());

						baseCallback.messageSuccess(list);
						/*System.out.println("kkkkkkkkkkk" + "测试===+=====+==="
								+ list.get(0).name);
						System.out.println("kkkkkkkkkkk" + "测试===+"
								+ successInfo + "+===" + list.get(0).name);*/
					}
				});

	}

	public void findByCategoryNo(String categoryNo,
			BaseCallback<FindByCategoryNoBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("categoryNo", categoryNo);
		baseMethod(params, NetContacts.getInstance().FINDBYCATEGORYNO, baseCallback,
				FindByCategoryNoBean.class);
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
	 * @param equipmentNo
	 */
	public void findByEquipmentNo(String equipmentNo,
		BaseCallback<FindByEquipmentNoBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		//TODO
		baseMethodone(params, NetContacts.getInstance().FINDBYEQUIPMENTNO, baseCallback,
				FindByEquipmentNoBean.class);
	}
	/**
	 * 通过设备编号查询设备
	 *
	 * @param baseCallback
	 *
	 */
	public void findEquipmentNo(String url ,BaseCallback<FindByEquipmentNoBean> baseCallback){
		RequestParams params = new RequestParams();
		//TODO
		baseMethodone(params, url, baseCallback,
				FindByEquipmentNoBean.class);
	}
	public void alarms(BaseCallback<AlarmsBean> baseCallback) {
		baseMethod(NetContacts.getInstance().ALARMS, baseCallback, AlarmsBean.class);
	}
}
