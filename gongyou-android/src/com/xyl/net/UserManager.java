package com.xyl.net;

import java.io.File;

import android.util.Log;

import com.xyl.base.BaseNet;
import com.xyl.domain.LoginBean;
import com.xyl.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

public class UserManager extends BaseNet{


	/**
	 * 这是进行登录的方法 由网页登录可以看出为post方式
	 * 
	 * @param staffCode
	 *            用户名
	 * @param password
	 *            密码 参考：http://www.open-open.com/lib/view/open1369637365753.html
	 *            http://blog.csdn.net/shineflowers/article/details/41309667
	 */
	public void userLoginIn(String staffCode, String password,BaseCallback<LoginBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("password", password);
		baseMethod(params, NetContacts.getInstance().USER_LOGIN, baseCallback, LoginBean.class);
		
	}
	
	/**
	 * 这是登出的接口
	 * 
	 * @param callback
	 */
	public void userLogout(final EntityCallback callback) {
		entity(NetContacts.getInstance().USER_LOGOUT, callback);
	}

	/**
	 * 心跳连接
	 * 
	 * @param callback
	 */
	public void heartbeat(final EntityCallback callback) {
		entity(NetContacts.getInstance().HEARTBEAT, callback);
	}
	
	
	
	

	/**
	 * 获取用户登录信息
	 * 
	 * @param code	员工编号(登录名)
	 */
	public void staffInfo(String code, BaseCallback<LoginBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("code", code);
		baseMethod(params, NetContacts.getInstance().STAFFINFF, baseCallback, LoginBean.class);
	}
	
	/**
	 * 这是注册时的方法为
	 * @param callback
	 * @param staffCode
	 * @param password
	 * @param name
	 * @param sex
	 * @param phone
	 */
	public void register(String staffCode ,String password,String name,int sex,String phone,EntityCallback callback){
		// post请求参数 拼接
		RequestParams params = new RequestParams();
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("password", password);
		params.addBodyParameter("name", name);
		params.addBodyParameter("sex", String.valueOf(sex));
		params.addBodyParameter("phone", phone);
		
		entity(params, NetContacts.getInstance().REGISTER, callback);
	}
	
	
	public void resetPassword(String oldPassword,String newPassword,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("oldPassword", oldPassword);
		params.addBodyParameter("newPassword", newPassword);
		entity(params,NetContacts.getInstance().RESETPASSWORD, callback);
	}
	
	
	//TODO:进行文件的上传	参数是图片文件   返回的路径用加吗？
	public void uploadPhoto(File imageFile,EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("picFile", imageFile, "image/png"); 
		entity(params,NetContacts.getInstance().UPLOADPHOTO, callback);
	}

}
