package com.xyl.base;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xyl.domain.MessageBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/*
 * 
 * @author Administrator
 * 
 */
public class BaseNet {
	protected HttpUtils httpUtils;
	protected Gson gson;
	
	public BaseNet() {
		httpUtils = new HttpUtils();
		gson = new Gson();
	}

	//============================================================这里是返回结果为空时回掉===================================================
	public class EntityrResult {
		public boolean logout;
		public String host_url;

		/**
		 * 返回值的boolean 基本没用
		 */
		public boolean error;

		/**
		 * 这是请求返回的信息
		 */
		public String message;

		/**
		 * 枚举：空返回值的类型
		 */
		public EntityType entityType;
		
		public EntityrResult(EntityType entityType) {
			this.entityType = entityType;
		}

		public EntityrResult(boolean error, String message,
				EntityType entityType) {
			this.error = error;
			this.message = message;
			this.entityType = entityType;
		}

		@Override
		public String toString() {
			return "EntityrResult{" +
					"logout=" + logout +
					", host_url='" + host_url + '\'' +
					", error=" + error +
					", message='" + message + '\'' +
					", entityType=" + entityType +
					'}';
		}
	}

	/**
	 * 回返3种类型 那这里就用枚举吧
	 * 
	 * @author Administrator 空的返回类型 无参回调返回
	 */
	public enum EntityType {
		connectFailure, messagetrue, messagefalse
	}

	public interface EntityCallback {
		// 进行连接状态的回调
		public void connectCallback(EntityrResult entityrResult);
	}

	/**
	 * TODO:基方法 扩展 也可以切面编程 无大用 再写第三个类时添加 每次返回一个新的请求参数 也可以在里边加值
	 * 
	 * @return
	 */
	public RequestParams getParams() {
		return new RequestParams();
	}

	/**
	 * 空返回值空参数的基本方法
	 * 
	 * @param url
	 * @param callback
	 */
	public void entity(String url, final EntityCallback callback) {
		entity(null, url, callback);
	}

	/**
	 * 空的返回值基本方法 只是中转为了加cookie
	 * 
	 * @param params
	 * @param url
	 * @param callback
	 */
	protected void entity(RequestParams params, String url,
			final EntityCallback callback) {

		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader(
				"Cookie",
				SharedPreferencesUtil.getString(
						BaseApplication.getBaseApplication(), "cookie"));
		entityJSESSIONID(params, url, callback);
	}

	/**
	 * 这是空返回值的基本方法
	 * @param url
	 * @param callback
	 */
	private void entityJSESSIONID(RequestParams params, String url,
			final EntityCallback callback) {
		//httpUtils.configTimeout(3000);
		// 进行数据请求
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						callback.connectCallback(new EntityrResult(
								EntityType.connectFailure));
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {

						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
							SharedPreferencesUtil.setString(
									BaseApplication.getBaseApplication(),
									"cookie", cookie);
						}
						EntityrResult entityBean = gson.fromJson(arg0.result,
								EntityrResult.class);

						if (entityBean.error == false) {
							entityBean.entityType = EntityType.messagetrue;
							callback.connectCallback(entityBean);
							return;
						}

						entityBean.entityType = EntityType.messagefalse;
						callback.connectCallback(entityBean);
						goLogin();
					}

				});
	}

	// =======================这里是返回一个对象时回调====================================

	/**
	 * 基本接口
	 * 
	 * @author Administrator
	 * 
	 * @param <T>
	 */
	public interface BaseCallback<T> {
		/**
		 * 当登录成功时回调
		 * 
		 * @param bean
		 */
		void messageSuccess(T bean);

		/**
		 * 当链接成功但是返回消息失败的时候回调失败时回调
		 * 
		 * @param backError
		 *            返回错误信息状态
		 */
		void messageFailure(MessageBean backError);
        
		/**
		 * 当连接失败时回调
		 * 
		 * @param messageBean
		 */
		void connectFailure(MessageBean messageBean);
	}

	protected <T> void baseMethod(String url,
			final BaseCallback<T> baseCallback, final Class<T> t) {
		baseMethod(null, url, baseCallback, t);
	}

	/**
	 * 这是请求的基本方法 可不可以继续优化
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	protected <T> void baseMethod(RequestParams params, String url,
			final BaseCallback<T> baseCallback, final Class<T> bean) {
		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader(
				"Cookie",
				SharedPreferencesUtil.getString(
				BaseApplication.getBaseApplication(), "cookie"));
		params.addBodyParameter("pageIndex", NetContacts.getInstance().pageIndex+"");
		baseMethodJSESSIONID(params, url, baseCallback, bean);
	}
    
	protected <T> void baseMethodone(RequestParams params, String url,
			final BaseCallback<T> baseCallback, final Class<T> bean) {
		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader(
				"Cookie",
				SharedPreferencesUtil.getString(
						BaseApplication.getBaseApplication(), "cookie"));
	//	params.addBodyParameter("pageIndex", NetContacts.getInstance().pageIndex + "");
		baseMethodJSESSIONID2(params, url, baseCallback, bean);
	}
	/**
	 * 这是请求的基本方法 可不可以继续优化
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	protected <T> void baseMethodJSESSIONID(RequestParams params, String url,
			final BaseCallback<T> baseCallback, final Class<T> bean) {

		// 设置超时时间 进行数据请求
//		//httpUtils.configTimeout(3000);
		// 进行数据请求
		httpUtils.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						baseCallback.connectFailure(new MessageBean(arg0.getExceptionCode(), arg1));
					}
					
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
							if (!TextUtils.isEmpty(cookie)) {
								SharedPreferencesUtil.setString(
										BaseApplication.getBaseApplication(),
										"cookie", cookie);
							}
						}
						
						String successInfo = arg0.result;
						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}
						T fromJson = (T) gson.fromJson(successInfo, bean);
						baseCallback.messageSuccess(fromJson);
					}
				});
	}
	private Integer goodsCaseId;
	/** 单类型 入库(0), 工单出库(1), 调拨出库(2), 盘点(3) */
	private Integer type;
	private String typeDisplay;
	/** 报单人 */
	private String personName;
	/** 报单人电话 */
	private String personPhone;
	/** 状态 申领中(0),已通过(1),未通过(2), 已领取(3),退料(4),已完成(5), 未知(-1); */
	private String status;
	private String statusDisplay;
	/** 生成时间 */
	private String dateTime;
	
	protected <T> void baseMethodJSESSIONID2(RequestParams params, String url,
			final BaseCallback<T> baseCallback, final Class<T> bean) {
				httpUtils.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {
					 
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						baseCallback.connectFailure(new MessageBean(arg0.getExceptionCode(), arg1));
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
							if (!TextUtils.isEmpty(cookie)) {
								SharedPreferencesUtil.setString(
										BaseApplication.getBaseApplication(),
										"cookie", cookie);
							}
						}
						String successInfo = arg0.result;
						if (isBackError(baseCallback, successInfo)){
							goLogin();
							return;
						}
						T fromJson = (T) gson.fromJson(successInfo, bean);
						baseCallback.messageSuccess(fromJson);
					}
				});
	}

	// ==============================Gson转化有泛型的list04-06======================
	/**
	 * 判断链接信息是否错误的方法
	 * 
	 * @param <T>
	 * @param baseCallback
	 * @param successInfo
	 * @return 当不报异常时返回 MessageBean 否则返回null
	 */
	public <T> Boolean isBackError(BaseCallback<T> baseCallback,
			String successInfo) {
		/**
		 * 8月16日上午修改 为了提高效率 判断是否成功 判断信息是否为错误信息 若是那么
		 *  就进行错误的回调
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

	protected <T> void baseListMethod(String url,
			final BaseCallback<List<T>> baseCallback, final Class<T> bean) {
		baseListMethod(null, url, baseCallback, bean);
	}

	/**
	 * json转化有泛型的list集合
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	protected <T> void baseListMethod(RequestParams params, String url,
			final BaseCallback<List<T>> baseCallback, final Class<T> bean) {
		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader(
				"Cookie",
				SharedPreferencesUtil.getString(
						BaseApplication.getBaseApplication(), "cookie"));
		baseListMethodJSESSIONID(params, url, baseCallback);
	}

	/**
	 * json转化有泛型的list集合
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param
	 */
	private <T> void baseListMethodJSESSIONID(RequestParams params, String url,
			final BaseCallback<List<T>> baseCallback) {

		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, url, params,
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
							SharedPreferencesUtil.setString(
									BaseApplication.getBaseApplication(),
									"cookie", cookie);
						}

						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}

						List<T> list = gson.fromJson(successInfo,
								new TypeToken<List<T>>() {
								}.getType());

						baseCallback.messageSuccess(list);
					}
				});
	}
	
	protected void goLogin() {
//		Intent loginIntent = new Intent(BaseApplication.getBaseApplication(), LoginActivity.class); 
//		loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
//		BaseApplication.getBaseApplication().startActivity(loginIntent); 
	}
}
