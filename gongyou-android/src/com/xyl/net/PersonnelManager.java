package com.xyl.net;

import com.lidroid.xutils.http.RequestParams;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.PurchaseBean;
import com.xyl.domain.personnel.ApplyPeopleBean;
import com.xyl.domain.personnel.ApprovalDetailBean;
import com.xyl.domain.personnel.EmergencyDetailBean;
import com.xyl.domain.personnel.ProcurementDetails;
import com.xyl.domain.personnel.ReimburseBean;
import com.xyl.domain.personnel.SealDetailBean;
import com.xyl.domain.personnel.SelectAllPersonalBean;
import com.xyl.domain.personnel.StandbyDetailBean;
import com.xyl.domain.personnel.WorkFlowBean;
import com.xyl.domain.personnel.leaveDetailBean;
import com.xyl.global.NetContacts;
import com.xyl.utils.SharedPreferencesUtil;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public class PersonnelManager extends BaseNet {

    private String urlPath;

    public void LeaveSubmit(String renShiId,String qingJiaId,Integer qingJiaLX, String startTime, String endTime, String tianShu, String shiYou, File[] files, final EntityCallback callback) throws FileNotFoundException {
        AsyncHttpClient client = new AsyncHttpClient();
        com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
        requestParams.add("renShiId", renShiId);
        requestParams.add("qingJiaId", qingJiaId);
        requestParams.add("qingJiaLX", qingJiaLX+"");
        requestParams.add("startTime", startTime);
        requestParams.add("endTime", endTime);
        requestParams.add("tianShu", tianShu+"");
        requestParams.add("shiYou", shiYou);
        if (files.length!=0){
            requestParams.put("picture", files);
            urlPath = NetContacts.getInstance().QINGJIASAVE;
        }else{
            urlPath = NetContacts.getInstance().QINGJIASAVENOIMAG;
        }



        client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
        client.post(urlPath, requestParams,
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



    public void SealSubmit(String renShiId,String yongYinShenQingId,String yongYinDW, String datTime, String yinZhangMC, String yongYinWJMC, String wenJianFS,String wenjianLB,String description,File[] files, final EntityCallback callback) throws FileNotFoundException {

        AsyncHttpClient client = new AsyncHttpClient();
        com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
        requestParams.add("renShiId",renShiId);
        requestParams.add("yongYinShenQingId",yongYinShenQingId);
        requestParams.add("yongYinDW", yongYinDW);
        requestParams.add("datTime", datTime);
        requestParams.add("yinZhangMC", yinZhangMC);
        requestParams.add("yongYinWJMC", yongYinWJMC);
        requestParams.add("wenJianFS", wenJianFS);
        requestParams.add("wenjianLB", wenjianLB);
        requestParams.add("description", description);
        if (files.length!=0){
            requestParams.put("picture", files);
            urlPath = NetContacts.getInstance().SEALSAVE;
        }else{
            urlPath = NetContacts.getInstance().SEALSAVENOIMG;
        }
        client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
        client.post(urlPath, requestParams,
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
    public void ApprovalSubmit(String renShiId,String tongYongShenPiId,String shenQingNR, String shenPiXQ, File[] files, final EntityCallback callback) throws FileNotFoundException {

        AsyncHttpClient client = new AsyncHttpClient();
        com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
        requestParams.add("renShiId",renShiId);
        requestParams.add("tongYongShenPiId",tongYongShenPiId);
        requestParams.add("shenQingNR", shenQingNR);
        requestParams.add("shenPiXQ", shenPiXQ);
        if (files.length!=0){
            requestParams.put("picture", files);
            urlPath = NetContacts.getInstance().APPROVAL;
        }else{
            urlPath = NetContacts.getInstance().APPROVALNOIMG;
        }
        client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
        client.post(urlPath, requestParams,
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

    public void meetpurchaseSubmit(String renShiId,String caiGouId,String data, File[] files, final EntityCallback callback) throws FileNotFoundException {

        AsyncHttpClient client = new AsyncHttpClient();
        com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
        requestParams.add("renShiId",renShiId);
        requestParams.add("caiGouId",caiGouId);
        requestParams.add("data", data);
        if (files.length!=0){
            requestParams.put("picture", files);
            urlPath = NetContacts.getInstance().MEETPURCHASESUBMIT;
        }else{
            urlPath = NetContacts.getInstance().MEETPURCHASESUBMITNOIMG;
        }
        client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
        client.post(urlPath, requestParams,
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

    public void purchaseSubmit(String renShiId, String  caiGouId, String shiYou, String caiGouLX, String qiWangJFRQ, String zhiFuFS, String description, String data, File[] files, final EntityCallback callback) throws FileNotFoundException {

        AsyncHttpClient client = new AsyncHttpClient();
        com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
        requestParams.add("renShiId",renShiId);
        requestParams.add("caiGouId", caiGouId);
        requestParams.add("shiYou", shiYou);
        requestParams.add("caiGouLX", caiGouLX);
        requestParams.add("qiWangJFRQ", qiWangJFRQ);
        requestParams.add("zhiFuFS", zhiFuFS);
        requestParams.add("description", description);
        requestParams.add("data", data);

        if (files.length!=0){
            requestParams.put("picture", files);
            urlPath = NetContacts.getInstance().PURCHASESUBMIT;
        }else{
            urlPath = NetContacts.getInstance().PURCHASESUBMITNOIMG;
        }
        client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
        client.post(urlPath, requestParams,
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
    public void reimburseSubmit(String renShiId,String  baoXiaoId,String data, File[] files, final EntityCallback callback) throws FileNotFoundException {

        AsyncHttpClient client = new AsyncHttpClient();
        com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
        requestParams.add("renShiId",renShiId);
        requestParams.add("baoXiaoId", baoXiaoId);
        requestParams.add("data", data);
        if (files.length!=0){
            requestParams.put("picture", files);
            urlPath = NetContacts.getInstance().REIMBURSEMENT;
        }else{
            urlPath = NetContacts.getInstance().REIMBURSEMENTNOIMG;
        }
        client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
        client.post(urlPath, requestParams,
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

    public void standbySubmit(String renShiId,String beiYongJinShenQingId,String staffId, String buMen, String shiYou, String shenQingJE,String shiYongSJ,String guiHuanSJ,String chuNa,String description, final EntityCallback callback) throws FileNotFoundException {
        AsyncHttpClient client = new AsyncHttpClient();
        com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
        requestParams.add("renShiId",renShiId);
        requestParams.add("beiYongJinShenQingId",beiYongJinShenQingId);
        requestParams.add("staffId",staffId);
        requestParams.add("buMen", buMen);
        requestParams.add("shiYou", shiYou);
        requestParams.add("shenQingJE", shenQingJE);
        requestParams.add("shiYongSJ", shiYongSJ);
        requestParams.add("guiHuanSJ", guiHuanSJ);
        requestParams.add("chuNa", chuNa);
        requestParams.add("description", description);

        client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
        client.post(NetContacts.getInstance().STANDBYSUBMITNOIMG, requestParams,
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

    public void getApplayPeople( BaseCallback<ApplyPeopleBean> baseCallback) {
        baseMethod(NetContacts.getInstance().GETAPPLYPEOPLE, baseCallback, ApplyPeopleBean.class);
    }

    public void selectAllPersonal( BaseCallback<SelectAllPersonalBean> baseCallback) {
        baseMethod(NetContacts.getInstance().SELECTALLPERSONAL, baseCallback, SelectAllPersonalBean.class);
    }





    public void leaveDetails( String renShiId , BaseCallback<leaveDetailBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("renShiId",renShiId);
        baseMethod(params,NetContacts.getInstance().LEAVEDETAILS, baseCallback, leaveDetailBean.class);
    }
    public void approvalDetail( String renShiId , BaseCallback<ApprovalDetailBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("renShiId",renShiId);
        baseMethod(params,NetContacts.getInstance().APPROVALDETAIL, baseCallback, ApprovalDetailBean.class);
    }
    public void standbyDetail( String renShiId , BaseCallback<StandbyDetailBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("renShiId",renShiId);
        baseMethod(params,NetContacts.getInstance().STANDBYDETAIL, baseCallback, StandbyDetailBean.class);
    }
    public void sealDetail( String renShiId , BaseCallback<SealDetailBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("renShiId",renShiId);
        baseMethod(params,NetContacts.getInstance().SEALDETAIL, baseCallback, SealDetailBean.class);
    }

    public void emergencyDetail( String renShiId , BaseCallback<EmergencyDetailBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("renShiId",renShiId);
        baseMethod(params,NetContacts.getInstance().EMERGENCYDETAIL, baseCallback, EmergencyDetailBean.class);
    }

    public void purchaseDetail( String renShiId , BaseCallback<EmergencyDetailBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("renShiId",renShiId);
        baseMethod(params,NetContacts.getInstance().PURCHASEDETAIL, baseCallback, EmergencyDetailBean.class);
    }
    public void reimburseDetail( String renShiId , BaseCallback<ReimburseBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("renShiId",renShiId);
        baseMethod(params,NetContacts.getInstance().REIMBURSEDETAIL, baseCallback, ReimburseBean.class);
    }

    public void workFlow( String  type , BaseCallback<WorkFlowBean> baseCallback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("type",type);
        baseMethod(params,NetContacts.getInstance().WORKFLOW, baseCallback, WorkFlowBean.class);
    }

}
