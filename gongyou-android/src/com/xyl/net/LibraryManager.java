package com.xyl.net;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.BuildBean;
import com.xyl.domain.CategoryBean;
import com.xyl.domain.CheckListOrderBean;
import com.xyl.domain.LibraryGoodsBean;
import com.xyl.domain.LibraryListBean;
import com.xyl.domain.MessageBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;

import org.apache.http.Header;

import java.util.List;

/**
 * Created by 47500 on 2018/5/21.
 */

public class LibraryManager extends BaseNet {

    public void getCategorys(final BaseCallback<List<CategoryBean>> baseCallback) {
        RequestParams params = new RequestParams();
        params.addHeader("Cookie",SharedPreferencesUtil.getString(BaseApplication.getContext(), "cookie"));
        httpUtils.send(HttpRequest.HttpMethod.POST, NetContacts.getInstance().GETCATEGORYS, params,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String seccess = responseInfo.result;
                Header header = responseInfo.getFirstHeader("Set-Cookie");
                if (header != null){
                    String cookie = header.getValue();
                    if(!TextUtils.isEmpty(cookie)){
                        SharedPreferencesUtil.setString(BaseApplication.getContext(), "cookie", cookie);
                    }
                }
                List<CategoryBean> list = gson.fromJson(seccess,new TypeToken<List<CategoryBean>>(){}.getType());
                baseCallback.messageSuccess(list);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                baseCallback.connectFailure(new MessageBean(e.getExceptionCode(), s));
            }
        });
    }


    public void findAllBuilding(final BaseCallback<List<BuildBean>> baseCallback) {
        RequestParams params = new RequestParams();
        params.addHeader("Cookie",SharedPreferencesUtil.getString(BaseApplication.getContext(), "cookie"));
        httpUtils.send(HttpRequest.HttpMethod.POST, NetContacts.getInstance().FINDALLBUILDING, params,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String seccess = responseInfo.result;
                Header header = responseInfo.getFirstHeader("Set-Cookie");
                if (header != null){
                    String cookie = header.getValue();
                    if(!TextUtils.isEmpty(cookie)){
                        SharedPreferencesUtil.setString(BaseApplication.getContext(), "cookie", cookie);
                    }
                }
                List<BuildBean> list = gson.fromJson(seccess,new TypeToken<List<BuildBean>>(){}.getType());
                baseCallback.messageSuccess(list);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                baseCallback.connectFailure(new MessageBean(e.getExceptionCode(), s));
            }
        });
    }

    public void findAllGoods(String goodsCategoryId,final BaseCallback<List<LibraryGoodsBean>> baseCallback){
        RequestParams params = new RequestParams();
        params.addBodyParameter("goodsCategoryId",goodsCategoryId);
        params.addHeader("Cookie",SharedPreferencesUtil.getString(BaseApplication.getContext(), "cookie"));
        httpUtils.send(HttpRequest.HttpMethod.POST, NetContacts.getInstance().FINDALLGOODS, params,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String seccess = responseInfo.result;
                Header header = responseInfo.getFirstHeader("Set-Cookie");
                if (header != null){
                    String cookie = header.getValue();
                    if(!TextUtils.isEmpty(cookie)){
                        SharedPreferencesUtil.setString(BaseApplication.getContext(), "cookie", cookie);
                    }
                }
                List<LibraryGoodsBean> list = gson.fromJson(seccess,new TypeToken<List<LibraryGoodsBean>>(){}.getType());
                baseCallback.messageSuccess(list);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                baseCallback.connectFailure(new MessageBean(e.getExceptionCode(), s));
            }
        });
       // baseListMethod(NetContacts.getInstance().FINDALLGOODS,params,baseCallback,LibraryGoodsBean.class);

    }

    public void saveStockTaking(Integer goodsCategoryId,String description,String data,EntityCallback entityCallback){
        RequestParams params = new RequestParams();
        params.addBodyParameter("goodsCategoryId",goodsCategoryId+"");
        params.addBodyParameter("description",description);
        params.addBodyParameter("data",data);
        entity(params,NetContacts.getInstance().SAVESTOCKTAKING,entityCallback);
    }


    public void getAllStockTaking(BaseCallback<LibraryListBean> baseCallback){
        baseMethod(NetContacts.getInstance().GETALLSTOCKTAKING,baseCallback,LibraryListBean.class);
    }
    public void findByStockTakingId(Integer stockTakingId ,BaseCallback<CheckListOrderBean> baseCallback){
        RequestParams params = new RequestParams();
        params.addBodyParameter("stockTakingId",stockTakingId+"");
        //baseMethod(NetContacts.getInstance().GETALLSTOCKTAKING,baseCallback,CheckListBean.class);
        baseMethod(params,NetContacts.getInstance().FINDBYSTOCKTAKINGID,baseCallback,CheckListOrderBean.class);
    }
}
