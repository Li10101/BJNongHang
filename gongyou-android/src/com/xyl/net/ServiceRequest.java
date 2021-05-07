package com.xyl.net;

import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.DataBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.Pictures;
import com.xyl.domain.ServiceBeanRecords;
import com.xyl.domain.ServiceBeanRecordsTraces;
import com.xyl.domain.ServicePictures;
import com.xyl.domain.ServicesBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * 接口0707 服务请求
 * 
 * @author peng
 * 
 */
public class ServiceRequest extends BaseNet {

	public void createService(String description, String expectedFixTime,
			File[] serviceFiles, String address,String staffCode,final EntityCallback callback) throws FileNotFoundException {
        if(serviceFiles.length==0){
        	createService(description, expectedFixTime,address,staffCode, callback);
        	return;
        }
		AsyncHttpClient client = new AsyncHttpClient();
		com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
		requestParams.add("description", description);
		requestParams.add("address", address);
		requestParams.add("expectedFixTime", expectedFixTime);
		requestParams.add("customerCode", staffCode);
		requestParams.put("serviceFiles", serviceFiles);

		client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		client.post(NetContacts.getInstance().CREATESERVICE, requestParams,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						EntityrResult entityBean = gson.fromJson(new String(
								responseBody), EntityrResult.class);

						if (entityBean.error == false) {
							entityBean.entityType = EntityType.messagetrue;
							callback.connectCallback(entityBean);
							return;
						}

						entityBean.entityType = EntityType.messagefalse;
						callback.connectCallback(entityBean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						callback.connectCallback(new EntityrResult(
								EntityType.connectFailure));
						error.printStackTrace(System.out);
					}
				});
	}

	
	public void createService(String description, String expectedFixTime,String address,String staffCode,
			 final EntityCallback callback) throws FileNotFoundException {

		AsyncHttpClient client = new AsyncHttpClient();
		com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();

		requestParams.add("description", description);
		requestParams.add("address", address);
		requestParams.add("expectedFixTime", expectedFixTime);
		requestParams.add("customerCode", staffCode);
		client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		client.post(NetContacts.getInstance().CREATESERVICENOTIMG, requestParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						EntityrResult entityBean = gson.fromJson(new String(
								responseBody), EntityrResult.class);
						if (entityBean.error == false) {
							entityBean.entityType = EntityType.messagetrue;
							callback.connectCallback(entityBean);
							return;
						}

						entityBean.entityType = EntityType.messagefalse;
						callback.connectCallback(entityBean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						callback.connectCallback(new EntityrResult(
								EntityType.connectFailure));
						error.printStackTrace(System.out);
					}
				});
	}
	/*
	 * // TODO: 方法没有经过验证
	 *//**
	 * 
	 * 创建用户请求服务(相当于创建工单) createService
	 * 
	 * @param description
	 * @param expectedFixTime
	 * @param serviceFiles
	 */
	/*
	 * @Deprecated private void createService2222222222(String description,
	 * String expectedFixTime, File[] serviceFiles, EntityCallback callback) {
	 * RequestParams params = new RequestParams();
	 * params.addBodyParameter("description", description);
	 * params.addBodyParameter("expectedFixTime", expectedFixTime);
	 * 
	 * params.addBodyParameter("serviceFiles", serviceFiles[0]);
	 * 
	 * for (int i = 0; i < serviceFiles.length; i++) {
	 * params.addBodyParameter("serviceFiles", serviceFiles[i], "image/png"); }
	 * entity(params, NetContacts.getInstance().CREATESERVICE, callback); }
	 */
	/**
	 * 查询服务请求列表
	 * 
	 * @param baseCallback
	 */
	public void services(BaseCallback<ServicesBean> baseCallback) {

		baseMethod1(null, NetContacts.getInstance().SERVICES, baseCallback,
				ServicesBean.class);
	}

	/**
	 * 查看自己报修的工单
	 * @param baseCallback
     */
	public void myServices(BaseCallback<ServicesBean> baseCallback) {

		baseMethod1(null, NetContacts.getInstance().MYSERVICES, baseCallback,
				ServicesBean.class);
	}
   /**
    * 工人查询自己报修的工单
    * @param baseCallback
    */
	public void servicesWorker(BaseCallback<ServicesBean> baseCallback) {
		baseMethod1(null, NetContacts.getInstance().SERVICESWORKER, baseCallback,
				ServicesBean.class);
	}
	public void baseMethod1(RequestParams params, String url,
			final BaseCallback<ServicesBean> baseCallback,
			final Class<ServicesBean> bean) {
		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		params.addBodyParameter("pageIndex", NetContacts.getInstance().pageIndex+"");
		baseMethodJSESSIONID1(params, url, baseCallback, bean);
	}

	public void baseMethodJSESSIONID1(RequestParams params, String url,
			final BaseCallback<ServicesBean> baseCallback,
			final Class<ServicesBean> bean) {

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

						ServicesBean servicesBean = new ServicesBean();
						servicesBean.records = new ArrayList<ServiceBeanRecords>();

						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
//							NetContacts.getInstance().COOKIE = cookie;
						}

						String successInfo = arg0.result;

						try {
							JSONObject jsonObject = new JSONObject(successInfo);
							servicesBean.count = jsonObject.getInt("count");
							servicesBean.pageSize = jsonObject
									.getInt("pageSize");
							servicesBean.prePage = jsonObject.getInt("prePage");
							servicesBean.nextPage = jsonObject
									.getInt("nextPage");
							servicesBean.totalPage = jsonObject
									.getInt("totalPage");
							servicesBean.startRecord = jsonObject
									.getInt("startRecord");
							servicesBean.endRecord = jsonObject
									.getInt("endRecord");
							servicesBean.error = jsonObject.getBoolean("error");
							servicesBean.voClass = jsonObject
									.getString("voClass");

							// 这里是得到集合的方法
							JSONArray records = jsonObject
									.getJSONArray("records");
							for (int i = 0; i < records.length(); i++) {
								ServiceBeanRecords jsonrecord = new ServiceBeanRecords();
								JSONObject record = records.getJSONObject(i);
								String aa = record
										.getString("serviceRequestId");
								jsonrecord.serviceRequestId = aa;
								System.out.println("gggggggaa" + aa);
								jsonrecord.customerCode = record
										.getString("customerCode");
								jsonrecord.customerName = record
										.getString("customerName");
								jsonrecord.customerPhone = record
										.getString("customerPhone");
								jsonrecord.address = record
										.getString("address");
								jsonrecord.description = record
										.getString("description");
								jsonrecord.requestTime = record
										.getString("requestTime");
								jsonrecord.expectedFixTime = record
										.getString("expectedFixTime");
								jsonrecord.status = record.getString("status");
								jsonrecord.caseCode = record
										.getString("caseCode");
								//这是加的代码
								jsonrecord.statusDisplay =  record
										.getString("statusDisplay");
								jsonrecord.customerType =  record
										.getString("customerType");
								jsonrecord.customerTypeDisplay =  record
										.getString("customerTypeDisplay");
								
								
								jsonrecord.traces = new ArrayList<ServiceBeanRecordsTraces>();
								JSONArray traces = record
										.getJSONArray("traces");

								for (int i1 = 0; i1 < traces.length(); i1++) {

									JSONObject trace = traces.getJSONObject(i1);
									// 这是加的类
									ServiceBeanRecordsTraces jsontrace = new ServiceBeanRecordsTraces();
									jsontrace.caseTraceId = trace
											.getString("caseTraceId");
									jsontrace.staffCode = trace
											.getString("staffCode");
									jsontrace.staffName = trace
											.getString("staffName");
									jsontrace.action = trace
											.getString("action");
									jsontrace.status = trace
											.getString("status");
									jsontrace.executeTime = trace
											.getString("executeTime");
									// jsontrace.actionDisplay = trace
									// .getString("actionDisplay");
									jsontrace.statusDisplay = trace
											.getString("statusDisplay");

									jsonrecord.traces.add(jsontrace);

								}

								// JSONArray pictures =
								// record.getJSONArray("pictures");
								// for (int j = 0; j < pictures.length(); j++) {
								//
								// }
								record.getString("statusDisplay");

								// System.out
								// .println("gggggggggggggghhserviceRequestId"
								// + serviceRequestId);
								servicesBean.records.add(jsonrecord);
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}

						// if (isBackError(baseCallback, successInfo)) {
						// return;
						// }
						// System.out.println("ggggggggggggggggggggggggggrr"
						// + bean.getName() + "------" + successInfo);
						//
						// ServicesBean fromJson = (ServicesBean) gson.fromJson(
						// successInfo, bean);
						System.out.println("rrrrrrrrrrrrrrrrrrrrrrgg"
								+ servicesBean.toString());

						baseCallback.messageSuccess(servicesBean);

					}

				});
	}

	/**
	 * 按ID查询服务请求	手动解析
	 * 
	 * @param serviceRequestId
	 *            id
	 * @param baseCallback
	 *            服务对象返回这里是复用了上一个列表
	 */
	public void findById(String serviceRequestId,
			BaseCallback<DataBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("serviceRequestId", serviceRequestId);
		baseMethodfindById(params, NetContacts.getInstance().FINDBYID, baseCallback, DataBean.class);
	}
	
	/**
	 * 这是请求的基本方法 可不可以继续优化
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	private  void baseMethodfindById(RequestParams params, String url,
			final BaseCallback<DataBean> baseCallback, final Class<DataBean> bean) {
		if (params == null) {
			params = new RequestParams();
		}
		params.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		baseMethodJSESSIONIDfindById(params, url, baseCallback, bean);
	}

	/**
	 * 这是请求的基本方法 可不可以继续优化
	 * 
	 * @param params
	 * @param url
	 * @param baseCallback
	 * @param bean
	 */
	private  void baseMethodJSESSIONIDfindById(RequestParams params, String url,
			final BaseCallback<DataBean> baseCallback, final Class<DataBean> bean) {

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

						Header header = arg0.getFirstHeader("Set-Cookie");
						if (header != null) {
							String cookie = header.getValue();
//							NetContacts.getInstance().COOKIE = cookie;
						}

						String successInfo = arg0.result;
						String allMessage  =successInfo;
						int start = successInfo.indexOf("[\"");
						if (successInfo.equals("")){
							return;
						}
						//这里就是字符串截取的方法
						if(start!=-1){
							int end = successInfo.indexOf("\"]")+2;
							String substring = successInfo.substring(start, end);
							System.out.println("kkkkkkkkkllllll---"+substring);
							successInfo = successInfo.replace(",\"pictures\":"+substring, "");
						}

						if (isBackError(baseCallback, successInfo)) {
							return;
						}

						DataBean fromJson = (DataBean) gson.fromJson(successInfo, bean);
						
						try {
							JSONObject jsonObject = new JSONObject(allMessage);
							JSONArray jsonArray = jsonObject.getJSONArray("pictures");
							fromJson.pictures = new ArrayList<Pictures>();
							fromJson.servicePictures = new ArrayList<ServicePictures>();
							for (int i = 0; i < jsonArray.length(); i++) {
								Pictures picture = new Pictures();
								ServicePictures servicePictures = new ServicePictures();
								String str = jsonArray.getString(i);
								picture.pictureUrl = str;
								servicePictures.pictureUrl = str;
								System.out.println("kkkkkkkllllll"+str);
								fromJson.pictures.add(picture);
								fromJson.servicePictures.add(servicePictures);
							}
							
						} catch (JSONException e) {
							
							e.printStackTrace();
						}
						baseCallback.messageSuccess(fromJson);
					}

				});
	}

}
