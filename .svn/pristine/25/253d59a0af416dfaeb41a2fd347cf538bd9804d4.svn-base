package com.xyl.net;

import com.xyl.base.BaseNet;
import com.xyl.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

public class ModifyInfoNet extends BaseNet {

	/**
	 * 修改联系方式
	 */
	public void setPhone(String phone,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("phone", phone);
		entity(params, NetContacts.getInstance().SETPHONE, callback);
	}
	/**
	 * 修改业务介绍
	 */
	public void setDescription(String description,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("description", description);
		entity(params, NetContacts.getInstance().DESCRIPTION, callback);
	}
	/**
	 * 修改当前位置
	 */
	public void setAddress(String address,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("address", address);
		entity(params, NetContacts.getInstance().ADDRESS, callback);
	}
	public void setPassword(String password,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("password", password);
		entity(params, NetContacts.getInstance().UPDATEPASSWORD, callback);
	}
}
