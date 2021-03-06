package com.xyl.net;

import android.text.PrecomputedText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.xyl.base.BaseApplication;
import com.xyl.base.BaseNet;
import com.xyl.domain.CasesBean;
import com.xyl.domain.DataBean;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.GoBackBean;
import com.xyl.domain.HealthStatusBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.OrderBean;
import com.xyl.domain.ProductBean;
import com.xyl.domain.PurchaseBean;
import com.xyl.domain.SearchGoodsBean;
import com.xyl.domain.UpDataApkBean;
import com.xyl.domain.answer.AnswerBean;
import com.xyl.domain.answer.AnswerQuestionBean;
import com.xyl.domain.answer.DaTiListBean;
import com.xyl.domain.answer.DaTiRenDataBean;
import com.xyl.domain.personnel.JianKangQKBean;
import com.xyl.domain.personnel.QuFanChengQKBean;
import com.xyl.global.NetContacts;
import com.xyl.greendao.Cart;
import com.xyl.utils.SharedPreferencesUtil;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


public class AnswerManager extends BaseNet{


	public void getAnswerList(BaseCallback<AnswerBean> baseCallback){

		baseMethod( NetContacts.getInstance().DATIDATA, baseCallback,AnswerBean.class);
	}

	public void getDaTiFindbyid(Integer daTiId ,BaseCallback<AnswerQuestionBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("daTiId",daTiId+"");

		baseMethod( params,NetContacts.getInstance().DATIFINDBYID, baseCallback,AnswerQuestionBean.class);
	}
	public void saveOrupdatedati(String data  ,BaseCallback<DaTiRenDataBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("data",data);
		baseMethod( params,NetContacts.getInstance().SAVEORUPDATEDATI, baseCallback,DaTiRenDataBean.class);
	}
	public void getdatimydata(BaseCallback<DaTiListBean> baseCallback){
		baseMethod(NetContacts.getInstance().DATIMYDATA, baseCallback,DaTiListBean.class);
	}
	/**
	 * ????????????????????????
	 * ???????????????????????????????????????jessionid
	 * ??????????????????????????????
	 */
	public void createdCases(BaseCallback<CasesBean> baseCallback){
	
		baseMethod( NetContacts.getInstance().CREATEDCASES, baseCallback,CasesBean.class);
	}
	
	/**
	 * ???????????????????????????
	 * @param baseCallback
	 */
	public void assginGroupCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().ASSGINGROUPCASES, baseCallback, CasesBean.class);
	}
	
	/**
	 * ??????????????????????????????
	 * @param baseCallback
	 */
	public void assginStaffCases(BaseCallback<CasesBean> baseCallback){
		String s = NetContacts.getInstance().ASSGINSTAFFCASES;
		baseMethod(s, baseCallback, CasesBean.class);
	}
	
	/**
	 * ????????????????????????
	 * @param baseCallback
	 */
	public void acceptCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().ACCEPTCASES, baseCallback, CasesBean.class);
	}
	/**
	 *  ????????????????????????
	 * @param baseCallback
	 */
	public void rejectCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().REJECTCASES, baseCallback, CasesBean.class);
	}
	/**
	 * ????????????????????????
	 * @param baseCallback
	 */
	public void fixedCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().FIXEDCASES, baseCallback, CasesBean.class);
	}
	/**
	 * ?????????????????????
	 * @param baseCallback
	 */
	public void cases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().CASES, baseCallback,CasesBean.class);
	}
	/**
	 * ??????????????????
	 * @param baseCallback
	 */
	public void myCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().MYCASES, baseCallback, CasesBean.class);
	}
	/**
	 * ??????????????????
	 */
	public void teamCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().TEAMCASES, baseCallback, CasesBean.class);
	}
	
	//TODO:11 ??????bean 
	/**
	 * ???????????????????????????
	 * @param code
	 * @param baseCallback
	 */
	public void data(String code,BaseCallback<DataBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("code", code);
		baseMethod(params,NetContacts.getInstance().DATA, baseCallback, DataBean.class);
	}
	
	/**
	 * ??????????????????
	 * @param callback
	 * @param caseCode
	 * @param description
	 * @param picture
	 */
	public void uploadArrivePicture(EntityCallback callback ,String caseCode ,String description,File picture ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("description", description);
		params.addBodyParameter("picture", picture, "image/png"); 
		entity(params, NetContacts.getInstance().UPLOADARRIVEPICTURE, callback);
	}

	public void uploadArriveCase(EntityCallback callback ,String caseCode ,String description ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("description", description);
		entity(params, NetContacts.getInstance().UPLOADARRIVE,callback);
	}
	
	public void uploadArrive(EntityCallback callback ,String equipmentNo,String fixStaffCode,String  description){
		RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		params.addBodyParameter("fixStaffCode", fixStaffCode);
		params.addBodyParameter("description", description); 
		entity(params, NetContacts.getInstance().MANTIONEQUIPMENTNO, callback);
	}
	//????????????????????????????????????
	/**
	 * 
	 * @param baseCallback    ????????????
	 * @param serviceRequestId ??????????????????
	 * @param staffCode	?????????
	 * @param priority	?????????(0??????1??????2???)
	 */
	public void createCustomerAndAssignStaff(BaseCallback<MessageBean> baseCallback,String serviceRequestId ,String staffCode ,String priority ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("serviceRequestId", serviceRequestId);
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("priority", priority);
		baseMethod(params, NetContacts.getInstance().CREATECUSTOMERANDASSIGNSTAFF, baseCallback, MessageBean.class);
	}
	
	//????????????????????????????????????
	/**
	 * ????????????????????????????????????
	 * @param baseCallback
	 * @param equipmentNo	????????????
	 * @param staffCode	????????????
	 * @param expectedFixTime	??????????????????
	 * @param priority	 ?????????(0??????1??????2???)
	 * @param description	 ??????
	 */
    public  void createEquipmentAndAssignStaff(BaseCallback<MessageBean> baseCallback,String equipmentNo,String staffCode,String expectedFixTime ,String priority,String description){ 
    	RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("expectedFixTime", expectedFixTime);
		params.addBodyParameter("priority", priority);
		params.addBodyParameter("description", description);
		baseMethod(params, NetContacts.getInstance().CREATECUSTOMERANDASSIGNSTAFF, baseCallback, MessageBean.class);
    }
	
    /**
     *  ?????????????????????????????????
     *  ????????????
     * @param baseCallback
     * @param equipmentNo
     * @param teamId	 ?????????
     * @param expectedFixTime
     * @param priority
     * @param description
     */
    public void createEquipmentAndAssignGroup(BaseCallback<MessageBean> baseCallback,String equipmentNo,String teamId,String expectedFixTime ,String priority,String description){ 
    	RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		params.addBodyParameter("teamId", teamId);
		params.addBodyParameter("expectedFixTime", expectedFixTime);
		params.addBodyParameter("priority", priority);
		params.addBodyParameter("description", description);
		baseMethod(params, NetContacts.getInstance().CREATECUSTOMERANDASSIGNSTAFF, baseCallback, MessageBean.class);
    }
    
    
    /**
     * ???????????????????????????
     * @param caseCode	????????????
     * @param staffCode ????????????????????????
     */
    public void basePartner(String url,EntityCallback callback,String caseCode,String staffCode){
    	RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("staffCode", staffCode);
		entity(params, url, callback);
    }
    /**
     * ??????????????????
     */
    public void addPartner(EntityCallback callback,String caseCode,String staffCode){
    	basePartner(NetContacts.getInstance().ADDPARTNER, callback, caseCode, staffCode);
    }
    /**
     * ??????????????????
     * @param callback
     * @param caseCode
     * @param staffCode
     */
    public void deletePartner(EntityCallback callback,String caseCode,String staffCode){
    	basePartner(NetContacts.getInstance().DELETEPARTNER, callback, caseCode, staffCode);
    }
    @Deprecated
    /**??????????????????????????????
     * 1.????????????????????????	OrderStream ????????? ?????????	???????????????  ???????????????
     * ??????????????????????????????
     * @param baseCallback
     */
    public void vie(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().VIE, baseCallback, CasesBean.class);
	}

    @Deprecated
    /**
     * ??????????????????????????????
     * 2.????????????????????????	OrderStream ????????? ?????????
     * @param evaluateRate		??????????????????
     * @param evaluateContent	??????????????????
     * @param baseCallback
     */
    public void evaluate(String evaluateRate,String evaluateContent,BaseCallback<CasesBean> baseCallback){
    	RequestParams params = new RequestParams();
    	params.addBodyParameter("evaluateRate", evaluateRate);
    	params.addBodyParameter("evaluateContent", evaluateContent);
		baseMethod(params,NetContacts.getInstance().EVALUATE, baseCallback, CasesBean.class);
	}
    /**
     * t3?????????????????????
     * @param baseCallback
     */
    public void assessedCases(BaseCallback<CasesBean> baseCallback){
  		baseMethod(NetContacts.getInstance().ASSESSEDCASES, baseCallback, CasesBean.class);
  	}
    
    public void servicesWork(BaseCallback<CasesBean> baseCallback){
   		baseMethod(NetContacts.getInstance().SERVICESWORKER, baseCallback, CasesBean.class);
   	}

	/**
	 * ??????????????????????????????		??????
	 * @param baseCallback
	 */
	public void AssignCasesServiceGourp(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().SERVICEASSIGNCASESSERVICEGOURP, baseCallback, CasesBean.class);
	}
   
    /**
     * 5.??????????????????????????????		??????
     * @param baseCallback
     */
    public void assignCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().ASSIGNCASES, baseCallback, CasesBean.class);
	}
    /**	??????
     * 6.??????????????????????????????(???????????????????????????????????????)
     * @param baseCallback
     */
    public void serviceAssessedCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().SERVICEASSESSEDCASES, baseCallback, CasesBean.class);
	}
	/**
	 * ??????/??????/????????????????????????????????????Remnant
	 */
	public void RemnantCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().REMNANTCASEDATA, baseCallback, CasesBean.class);
	}

	/**
	 * ??????????????????
	 */
	public void Search(String state,BaseCallback<CasesBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("data", state);
		baseMethod(params,NetContacts.getInstance().SEARCH, baseCallback, CasesBean.class);
	}



    /** ??????(created?????????assign-group????????????assign-staff???????????????vied????????????accepted????????????rejected????????????arrived????????????fixed????????????done????????????closed?????????) (Not Null) */
//	@Column(name = "STATUS")
//	private String status;

    public void statusCases(String status,BaseCallback<CasesBean> baseCallback){
    	RequestParams params = new RequestParams();
    	params.addBodyParameter("status", status);
    	baseMethod(params,NetContacts.getInstance().CASES, baseCallback, CasesBean.class);
    }

	/**
	 * ??????APK
	 * @param baseCallback
	 */
	public void upDataApk(BaseCallback<UpDataApkBean> baseCallback){

		baseMethod(NetContacts.getInstance().GETVERSION, baseCallback, UpDataApkBean.class);
	}

	/**
	 * ???????????????????????????
	 */

	public void getZiChanData(String status , BaseCallback<FindByAssetNoBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("status", status);
		baseMethod(params,NetContacts.getInstance().USERICHART, baseCallback, FindByAssetNoBean.class);
	}


	public void getGoodsCategory(int stockTakingId, BaseCallback<ProductBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("stockTakingId", stockTakingId + "");
		baseMethod(params, NetContacts.getInstance().GETGOODSCATEGORY, baseCallback, ProductBean.class);
	}


	public void goodsCategory(int type ,BaseCallback<ProductBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("kuFangType",type+"");
		baseMethod(NetContacts.getInstance().GOODSGETGOODSCATEGORY, baseCallback, ProductBean.class);
	}

	public void goodsGetgoodscategory(int type,BaseCallback<ProductBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("kuFangType",type+"");
		baseMethod(params,NetContacts.getInstance().GOODSGETGOODSCATEGORY, baseCallback, ProductBean.class);
	}
	public void findStorageData(String type, BaseCallback<PurchaseBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("type", type);
		baseMethod(params, NetContacts.getInstance().FINDSTORAGE, baseCallback, PurchaseBean.class);

	}

	public void goodsSearch(String buildingId,String data
			, BaseCallback<SearchGoodsBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("buildingId", buildingId);
		params.addBodyParameter("data", data);
		baseMethod(params, NetContacts.getInstance().GOODSSEARCH, baseCallback, SearchGoodsBean.class);

	}

	public void updateStocktaking(int stockTakingId, String data, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("stockTakingId", stockTakingId + "");
		params.addBodyParameter("data", data);
		entity(params, NetContacts.getInstance().UPDATESTOCKTAKING, callback);
	}

	public void findByGoodsCaseId(int goodsCaseId, BaseCallback<OrderBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId + "");
		baseMethod(params, NetContacts.getInstance().FINDBYGOODSCASEID, baseCallback, OrderBean.class);

	}


	public void editGoodsCase(String json, Integer goodsCaseId, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("data", json);
		params.addBodyParameter("goodsCaseId", goodsCaseId + "");

		entity(params, NetContacts.getInstance().EDITGOODSCASE, callback);

	}

	public void uploadDescription(String goodsCaseId, String description, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId);
		params.addBodyParameter("description", description);
		entity(params, NetContacts.getInstance().UPLOADDESCRIPTION, callback);

	}

	public void closed(String goodsCaseId, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId);
		entity(params, NetContacts.getInstance().CLOSED, callback);
	}

	public void resubmit(String goodsCaseId, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId);
		entity(params, NetContacts.getInstance().RESUBMIT, callback);

	}

	public void verify(String goodsCaseId, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId);
		entity(params, NetContacts.getInstance().verify, callback);

	}

	public void storage(String goodsCaseId, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId);
		entity(params, NetContacts.getInstance().STORAGE, callback);
	}

	public void approve(String goodsCaseId, String status, String rejectReason, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId+"");
		params.addBodyParameter("status", status);
		params.addBodyParameter("rejectReason", rejectReason);
		entity(params, NetContacts.getInstance().APPROVE, callback);
	}

	public void renshiApproval(String renShiId, String status, String description, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("renShiId", renShiId+"");
		params.addBodyParameter("status", status);
		params.addBodyParameter("description", description);
		entity(params, NetContacts.getInstance().RENSHIAPPROVAL, callback);

	}

	public void editGoodsCaseid(Integer goodsCaseId, OrderBean.GoodsCaseDetailsVosBean caseDetailsVosBean, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("goodsCaseId", goodsCaseId + "");
		params.addBodyParameter("goodsCaseDetailsId", caseDetailsVosBean.getGoodsCaseDetailsId() + "");
		params.addBodyParameter("amount", caseDetailsVosBean.getAmount() + "");
		params.addBodyParameter("unitPrice", caseDetailsVosBean.getUnitPrice() + "");
		entity(params, NetContacts.getInstance().EDITGOODSCASEID, callback);

	}

//	public void commitCartData(List<Cart> staffCode, int type, int goodsCaseType, EntityCallback callback) {
//		Gson gson = new Gson();
//		String json = gson.toJson(staffCode);
//		RequestParams params = new RequestParams();
//		params.addBodyParameter("type", type + "");
//		params.addBodyParameter("data", json);
//		params.addBodyParameter("goodsCaseType", goodsCaseType + "");
//		entity(params, NetContacts.getInstance().SAVEAGREE, callback);
//	}
 public void commitCartData(String data, int type, String caseCode
		 ,EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("type", type + "");
		params.addBodyParameter("data", data);
		params.addBodyParameter("caseCode", caseCode);
		entity(params, NetContacts.getInstance().SAVEAGREE, callback);
	}

	public void caseapply(List<Cart> data, String caseCode, EntityCallback callback) {
		Gson gson = new Gson();
		String json = gson.toJson(data);
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode );
		params.addBodyParameter("data", json);
		entity(params, NetContacts.getInstance().CASEAPPLY, callback);
	}


	public void findGoodsData(String type, BaseCallback<PurchaseBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("type", type);
		baseMethod(params, NetContacts.getInstance().FINDGOODSCASE, baseCallback, PurchaseBean.class);

	}

	//?????????????????????
	public void quFanChengqkfindall( BaseCallback<GoBackBean> baseCallback) {
		RequestParams params = new RequestParams();
		baseMethod(params, NetContacts.getInstance().QUFANCHENGQKFINDALL, baseCallback, GoBackBean.class);

	}

	//?????????????????????
	public void QuFanChengqksaveorUpdate(String data, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("data", data);
		entity(params, NetContacts.getInstance().QUFANCHENGQKSAVEORUPDATE, callback);

	}
	//?????????????????????
	public void quFanChengqkRemove(String quFanChengQKId, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("quFanChengQKId", quFanChengQKId);
		entity(params, NetContacts.getInstance().QUFANCHENGQKREMOVE, callback);

	}
	//?????????????????????
	public void quFanChengqkFindbyid(String quFanChengQKId, BaseCallback<QuFanChengQKBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("quFanChengQKId", quFanChengQKId);
		baseMethod(params, NetContacts.getInstance().QUFANCHENGQKFINDBYID, baseCallback, QuFanChengQKBean.class);

	}


	//??????????????????
	public void jianKangqkfindall( BaseCallback<HealthStatusBean> baseCallback) {
		RequestParams params = new RequestParams();
		baseMethod(params, NetContacts.getInstance().JIANKANGQKFINDALL, baseCallback, HealthStatusBean.class);

	}
	//????????????????????????
	public void jianKangqkSaveorUpdate(String data, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("data", data);
		entity(params, NetContacts.getInstance().JIANKANGQKSAVEORUPDATE, callback);

	}

	//?????????????????????
	public void jianKangqkFindbyid(String jianKangQKId, BaseCallback<JianKangQKBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("jianKangQKId", jianKangQKId);
		baseMethod(params, NetContacts.getInstance().JIANKANGQKFINDBYID, baseCallback, JianKangQKBean.class);

	}
    //?????????????????????
    public void jianKangqkRemove(String quFanChengQKId, EntityCallback callback) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("quFanChengQKId", quFanChengQKId);
        entity(params, NetContacts.getInstance().JIANKANGQKREMOVE, callback);

    }



	public void uploadArrivePicture(String goodsCaseId, String describe,
									File[] serviceFiles, final EntityCallback callback) throws FileNotFoundException {

		AsyncHttpClient client = new AsyncHttpClient();
		com.loopj.android.http.RequestParams requestParams = new com.loopj.android.http.RequestParams();
		requestParams.add("goodsCaseId", goodsCaseId);
		requestParams.add("description", describe);
		requestParams.put("pictures", serviceFiles);

		client.addHeader("Cookie", SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "cookie"));
		client.post(NetContacts.getInstance().UPLOADPICTURE, requestParams,
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


	public void getFindAllStaff(final BaseCallback<List<LoginBean>> baseCallback) {

		// baseListMethod(null, NetContacts.getInstance().CATEGORIES, baseCallback,
		// CategoriesBean.class);

		RequestParams params = new RequestParams();

		// baseListMethod(null, NetContacts.getInstance().STAFFLIST, baseCallback,
		// StaffListBean.class);

		params.addHeader(
				"Cookie",
				SharedPreferencesUtil.getString(
						BaseApplication.getBaseApplication(), "cookie"));

		// ?????????????????? ??????????????????
		//httpUtils.configTimeout(3000);

		// ??????????????????
		httpUtils.send(HttpRequest.HttpMethod.POST, NetContacts.getInstance().FINDALLSTAFF, params,
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

						List<LoginBean> list = gson.fromJson(successInfo,
								new TypeToken<List<LoginBean>>() {
								}.getType());

						baseCallback.messageSuccess(list);
						/*System.out.println("kkkkkkkkkkk" + "??????===+=====+==="
								+ list.get(0).name);
						System.out.println("kkkkkkkkkkk" + "??????===+"
								+ successInfo + "+===" + list.get(0).name);*/
					}
				});

	}

}
