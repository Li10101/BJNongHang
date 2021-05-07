package com.xyl.net;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.ActionsBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.StaffListBean;
import com.xyl.domain.TeamListBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 这个类是接口文档04-06工单
 * @author peng
 *
 */
public class MixNet extends BaseNet{

	public void getAssetStaffList(final BaseCallback<List<StaffListBean>> baseCallback){
		RequestParams params = new RequestParams();
		params.addHeader("Cookie",SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);
		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.getInstance().GETSTAFFLIST, params,
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
							if(!TextUtils.isEmpty(cookie)){
								SharedPreferencesUtil.setString(BaseApplication.getBaseApplication(), "cookie", cookie);
							}

						}
						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}
						List<StaffListBean> list = gson.fromJson(successInfo,
								new TypeToken<List<StaffListBean>>() {
								}.getType());
						baseCallback.messageSuccess(list);

					}
				});

	}



	/**
	 * 这是获得工人列表的方法	04
	 * @param baseCallback
	 */
	public void staffList(final BaseCallback<List<StaffListBean>> baseCallback){
		RequestParams params = new RequestParams();
		params.addHeader("Cookie",SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);
		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.getInstance().STAFFLIST, params,
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
							if(!TextUtils.isEmpty(cookie)){
								SharedPreferencesUtil.setString(BaseApplication.getBaseApplication(), "cookie", cookie);
							}
							
						}
						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}
						List<StaffListBean> list = gson.fromJson(successInfo,
								new TypeToken<List<StaffListBean>>() {
								}.getType());
						baseCallback.messageSuccess(list);

					}
				});	
		
	}
	
	
	
	public <T> Boolean isBackError(BaseCallback<T> baseCallback,
			String successInfo) {
		/**
		 * 8月16日上午修改 为了提高效率 判断是否成功 判断信息是否为错误信息 若是那么 就进行错误的回调
		 */
		MessageBean backError = null;

		try {
			JSONObject jsonObject = new JSONObject(successInfo);
			boolean error = jsonObject.getBoolean("error");
			if (error) {
				backError = gson.fromJson(successInfo, MessageBean.class);
				baseCallback.messageFailure(backError);
				return error;
			}
		} catch (JSONException e) {
			e.printStackTrace();

		}
		return false;
	}
	
	
	public void teamList(final BaseCallback<List<TeamListBean>> baseCallback){
		//baseListMethod(null, NetContacts.getInstance().TEAMLIST, baseCallback, TeamListBean.class);

		RequestParams params = new RequestParams();
		params.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);
		// 进行数据请求
		httpUtils.send(HttpMethod.POST,NetContacts.getInstance().TEAMLIST, params,
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
//	TODO:						NetContacts.getInstance().COOKIE = cookie;
						}
						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}
						List<TeamListBean> list = gson.fromJson(successInfo,
								new TypeToken<List<TeamListBean>>() {
								}.getType());
						baseCallback.messageSuccess(list);
					}
					
				});	
	}

	public void ownerList(final BaseCallback<List<LoginBean>> baseCallback){
		//baseListMethod(null, NetContacts.getInstance().TEAMLIST, baseCallback, TeamListBean.class);

		RequestParams params = new RequestParams();
		params.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);
		// 进行数据请求
		httpUtils.send(HttpMethod.POST,NetContacts.getInstance().OwnerList, params,
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
//	TODO:						NetContacts.getInstance().COOKIE = cookie;
						}
						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}
						List<LoginBean> list = gson.fromJson(successInfo,
								new TypeToken<List<LoginBean>>() {
								}.getType());
						baseCallback.messageSuccess(list);
					}

				});
	}
	
	/**
	 * 查询当前状态能执行的工作流操作
	 * @param baseCallback
	 */
	public void actions(String caseCode,final BaseCallback<List<ActionsBean>> baseCallback){
		//baseListMethod(null, NetContacts.getInstance().TEAMLIST, baseCallback, ActionsBean.class);

		RequestParams params = new RequestParams();
		params.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		params.addBodyParameter("caseCode", caseCode);
		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);
		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.getInstance().ACTIONS, params,
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
							//TODO:NetContacts.getInstance().COOKIE = cookie;
						}
						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}
						List<ActionsBean> list = gson.fromJson(successInfo,
								new TypeToken<List<ActionsBean>>() {
								}.getType());
						baseCallback.messageSuccess(list);

					}
				});	
		
	}
	
	
}
