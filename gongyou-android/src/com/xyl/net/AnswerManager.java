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
	 * 查询新生成的工单
	 * 在网页上是不需要参数但是要jessionid
	 * 这里明确了泛型的类型
	 */
	public void createdCases(BaseCallback<CasesBean> baseCallback){
	
		baseMethod( NetContacts.getInstance().CREATEDCASES, baseCallback,CasesBean.class);
	}
	
	/**
	 * 查询派发给组的工单
	 * @param baseCallback
	 */
	public void assginGroupCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().ASSGINGROUPCASES, baseCallback, CasesBean.class);
	}
	
	/**
	 * 查询派发给工人的工单
	 * @param baseCallback
	 */
	public void assginStaffCases(BaseCallback<CasesBean> baseCallback){
		String s = NetContacts.getInstance().ASSGINSTAFFCASES;
		baseMethod(s, baseCallback, CasesBean.class);
	}
	
	/**
	 * 查询已接受的工单
	 * @param baseCallback
	 */
	public void acceptCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().ACCEPTCASES, baseCallback, CasesBean.class);
	}
	/**
	 *  查询已拒绝的工单
	 * @param baseCallback
	 */
	public void rejectCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().REJECTCASES, baseCallback, CasesBean.class);
	}
	/**
	 * 查询已维修的工单
	 * @param baseCallback
	 */
	public void fixedCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().FIXEDCASES, baseCallback, CasesBean.class);
	}
	/**
	 * 查询所有的工单
	 * @param baseCallback
	 */
	public void cases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().CASES, baseCallback,CasesBean.class);
	}
	/**
	 * 查询我的工单
	 * @param baseCallback
	 */
	public void myCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().MYCASES, baseCallback, CasesBean.class);
	}
	/**
	 * 查询组的工单
	 */
	public void teamCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().TEAMCASES, baseCallback, CasesBean.class);
	}
	
	//TODO:11 完成bean 
	/**
	 * 这是给焦玉斌的方法
	 * @param code
	 * @param baseCallback
	 */
	public void data(String code,BaseCallback<DataBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("code", code);
		baseMethod(params,NetContacts.getInstance().DATA, baseCallback, DataBean.class);
	}
	
	/**
	 * 上传现场图片
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
	//创建客户工单并派发给工人
	/**
	 * 
	 * @param baseCallback    回掉方法
	 * @param serviceRequestId 服务请求编号
	 * @param staffCode	维修组
	 * @param priority	优先级(0低、1中、2高)
	 */
	public void createCustomerAndAssignStaff(BaseCallback<MessageBean> baseCallback,String serviceRequestId ,String staffCode ,String priority ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("serviceRequestId", serviceRequestId);
		params.addBodyParameter("staffCode", staffCode);
		params.addBodyParameter("priority", priority);
		baseMethod(params, NetContacts.getInstance().CREATECUSTOMERANDASSIGNSTAFF, baseCallback, MessageBean.class);
	}
	
	//创建设备工单并派发给工人
	/**
	 * 创建设备工单并派发给工人
	 * @param baseCallback
	 * @param equipmentNo	设备编号
	 * @param staffCode	维修员工
	 * @param expectedFixTime	期望完成时间
	 * @param priority	 优先级(0低、1中、2高)
	 * @param description	 描述
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
     *  创建设备工单并派发给组
     *  参数同上
     * @param baseCallback
     * @param equipmentNo
     * @param teamId	 维修组
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
     * 添加，删除工单伙伴
     * @param caseCode	工单编号
     * @param staffCode 工单伙伴员工编号
     */
    public void basePartner(String url,EntityCallback callback,String caseCode,String staffCode){
    	RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("staffCode", staffCode);
		entity(params, url, callback);
    }
    /**
     * 添加工单伙伴
     */
    public void addPartner(EntityCallback callback,String caseCode,String staffCode){
    	basePartner(NetContacts.getInstance().ADDPARTNER, callback, caseCode, staffCode);
    }
    /**
     * 删除工单伙伴
     * @param callback
     * @param caseCode
     * @param staffCode
     */
    public void deletePartner(EntityCallback callback,String caseCode,String staffCode){
    	basePartner(NetContacts.getInstance().DELETEPARTNER, callback, caseCode, staffCode);
    }
    @Deprecated
    /**给的新接口方法有问题
     * 1.查询已抢单的工单	OrderStream 中已有 此方法	返回值不同  貌似有问题
     * 给的新接口方法有问题
     * @param baseCallback
     */
    public void vie(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().VIE, baseCallback, CasesBean.class);
	}

    @Deprecated
    /**
     * 给的新接口方法有问题
     * 2.查询已评价的工单	OrderStream 中已有 此方法
     * @param evaluateRate		客户评价等级
     * @param evaluateContent	客户评价内容
     * @param baseCallback
     */
    public void evaluate(String evaluateRate,String evaluateContent,BaseCallback<CasesBean> baseCallback){
    	RequestParams params = new RequestParams();
    	params.addBodyParameter("evaluateRate", evaluateRate);
    	params.addBodyParameter("evaluateContent", evaluateContent);
		baseMethod(params,NetContacts.getInstance().EVALUATE, baseCallback, CasesBean.class);
	}
    /**
     * t3查看已评价工单
     * @param baseCallback
     */
    public void assessedCases(BaseCallback<CasesBean> baseCallback){
  		baseMethod(NetContacts.getInstance().ASSESSEDCASES, baseCallback, CasesBean.class);
  	}
    
    public void servicesWork(BaseCallback<CasesBean> baseCallback){
   		baseMethod(NetContacts.getInstance().SERVICESWORKER, baseCallback, CasesBean.class);
   	}

	/**
	 * 组长查看已派发的工单		能行
	 * @param baseCallback
	 */
	public void AssignCasesServiceGourp(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().SERVICEASSIGNCASESSERVICEGOURP, baseCallback, CasesBean.class);
	}
   
    /**
     * 5.经理查看已派发的工单		能行
     * @param baseCallback
     */
    public void assignCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().ASSIGNCASES, baseCallback, CasesBean.class);
	}
    /**	能行
     * 6.客服查看已派发的工单(包含已派发、已接受、已拒绝)
     * @param baseCallback
     */
    public void serviceAssessedCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().SERVICEASSESSEDCASES, baseCallback, CasesBean.class);
	}
	/**
	 * 组长/客服/物业经理查看昨日遗留工单Remnant
	 */
	public void RemnantCases(BaseCallback<CasesBean> baseCallback){
		baseMethod(NetContacts.getInstance().REMNANTCASEDATA, baseCallback, CasesBean.class);
	}

	/**
	 * 查询工单信息
	 */
	public void Search(String state,BaseCallback<CasesBean> baseCallback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("data", state);
		baseMethod(params,NetContacts.getInstance().SEARCH, baseCallback, CasesBean.class);
	}



    /** 状态(created新建、assign-group派给组、assign-staff派给工人、vied已抢单、accepted已接受、rejected已拒绝、arrived已到达、fixed已维修、done已完成、closed已关闭) (Not Null) */
//	@Column(name = "STATUS")
//	private String status;

    public void statusCases(String status,BaseCallback<CasesBean> baseCallback){
    	RequestParams params = new RequestParams();
    	params.addBodyParameter("status", status);
    	baseMethod(params,NetContacts.getInstance().CASES, baseCallback, CasesBean.class);
    }

	/**
	 * 更新APK
	 * @param baseCallback
	 */
	public void upDataApk(BaseCallback<UpDataApkBean> baseCallback){

		baseMethod(NetContacts.getInstance().GETVERSION, baseCallback, UpDataApkBean.class);
	}

	/**
	 * 获取资产管理的数据
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

	//获取去返程列表
	public void quFanChengqkfindall( BaseCallback<GoBackBean> baseCallback) {
		RequestParams params = new RequestParams();
		baseMethod(params, NetContacts.getInstance().QUFANCHENGQKFINDALL, baseCallback, GoBackBean.class);

	}

	//保存去返程接口
	public void QuFanChengqksaveorUpdate(String data, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("data", data);
		entity(params, NetContacts.getInstance().QUFANCHENGQKSAVEORUPDATE, callback);

	}
	//删除去返程接口
	public void quFanChengqkRemove(String quFanChengQKId, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("quFanChengQKId", quFanChengQKId);
		entity(params, NetContacts.getInstance().QUFANCHENGQKREMOVE, callback);

	}
	//查看去返程详情
	public void quFanChengqkFindbyid(String quFanChengQKId, BaseCallback<QuFanChengQKBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("quFanChengQKId", quFanChengQKId);
		baseMethod(params, NetContacts.getInstance().QUFANCHENGQKFINDBYID, baseCallback, QuFanChengQKBean.class);

	}


	//获取健康列表
	public void jianKangqkfindall( BaseCallback<HealthStatusBean> baseCallback) {
		RequestParams params = new RequestParams();
		baseMethod(params, NetContacts.getInstance().JIANKANGQKFINDALL, baseCallback, HealthStatusBean.class);

	}
	//保存健康信息接口
	public void jianKangqkSaveorUpdate(String data, EntityCallback callback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("data", data);
		entity(params, NetContacts.getInstance().JIANKANGQKSAVEORUPDATE, callback);

	}

	//查看去返程详情
	public void jianKangqkFindbyid(String jianKangQKId, BaseCallback<JianKangQKBean> baseCallback) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("jianKangQKId", jianKangQKId);
		baseMethod(params, NetContacts.getInstance().JIANKANGQKFINDBYID, baseCallback, JianKangQKBean.class);

	}
    //删除去返程接口
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

		// 设置超时时间 进行数据请求
		//httpUtils.configTimeout(3000);

		// 进行数据请求
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
						/*System.out.println("kkkkkkkkkkk" + "测试===+=====+==="
								+ list.get(0).name);
						System.out.println("kkkkkkkkkkk" + "测试===+"
								+ successInfo + "+===" + list.get(0).name);*/
					}
				});

	}

}
