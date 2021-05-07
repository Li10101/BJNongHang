package com.xyl.net;

import com.xyl.base.BaseNet;
import com.xyl.domain.FindNoticeBean;
import com.xyl.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

public class NoticeNet extends BaseNet {

	/**
	 * 获取楼宇通知列表
	 */
	public void findNotice(final BaseCallback<FindNoticeBean> baseCallback) {
		baseMethod(NetContacts.getInstance().NOTICENET, baseCallback, FindNoticeBean.class);
		/*RequestParams params = new RequestParams();

		params.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));

		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);

		// 进行数据请求
		httpUtils.send(HttpMethod.POST, NetContacts.getInstance().NOTICENET, params,
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
							//TODO:NetContacts.getInstance().COOKIE = cookie;
						}

						String successInfo = arg0.result;

						if (isBackError(baseCallback, successInfo)) {
							goLogin();
							return;
						}

						ArrayList<FindNoticeBean> arrayList = new ArrayList<FindNoticeBean>();
						JSONArray jsonArray;
						try {
							jsonArray = new JSONArray(successInfo);
							for (int i = 0; i < jsonArray.length(); i++) {
								String info = jsonArray.getString(i);
										//toString();
								System.out.println("llllkkkk"+info);
								FindNoticeBean noticeBean = gson.fromJson(info,
										FindNoticeBean.class);
								arrayList.add(noticeBean);
							}
						} catch (JSONException e) {

							e.printStackTrace();
						}
						baseCallback.messageSuccess(arrayList);
					}

				});*/
	}

	/**
	 * 获取楼宇通知详情 失败不用管 因为ios 也失败
	 * 
	 * @param id
	 * @param baseCallback
	 */
	public void findNoticeInfo(String id,
			BaseCallback<FindNoticeBean.RecordsBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("buildingNoticeId", id);
		baseMethod(params, NetContacts.getInstance().FINDNOTICEINFO, baseCallback,
				FindNoticeBean.RecordsBean.class);
	}

}
