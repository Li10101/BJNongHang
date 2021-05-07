package com.xyl.net;

import java.io.File;
import java.util.List;

import com.xyl.base.BaseNet;
import com.xyl.domain.GoodsCaseDetailsBean;
import com.xyl.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

/**
 * 工单工作流管理接口
 * @author peng
String caseCode
RequestParams params = new RequestParams();
params.addBodyParameter("", file)
 */
public class OrderStream extends BaseNet{

	public void assign(EntityCallback callback,String serviceRequestId,String fixStaffCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("priority", "0");
		params.addBodyParameter("caseCode", serviceRequestId);
		params.addBodyParameter("fixStaffCode", fixStaffCode);
		entity(params, NetContacts.getInstance().ASSIGN, callback);
	}
	
	public void assignTeam(EntityCallback callback,String caseCode,String fixTeamId ){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("fixTeamId", fixTeamId);
		entity(params, NetContacts.getInstance().ASSIGNTEAM, callback);
	}
	
	public void vie(EntityCallback callback,String caseCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		entity(params, NetContacts.getInstance().VIE, callback);
	}
	
	public void forward(EntityCallback callback,String caseCode,String fixStaffCode, String forwarReason){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("fixStaffCode", fixStaffCode);
		params.addBodyParameter("forwarReason", forwarReason);
		entity(params, NetContacts.getInstance().FORWARD, callback);
	}
	
	/**
	 * 因为工单编号 的请求太多就写了一个基方法
	 *  空的回掉方法
	 * @param caseCode 工单编号
	 * @return	请求参数
	 */
	public RequestParams baseCodeFirst(String caseCode, String rejectReason){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		params.addBodyParameter("rejectReason", rejectReason);
		return params;
	}
	
	public RequestParams baseCodeFirst(String caseCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode", caseCode);
		return params;
	}
	
	/**
	 * 接受工单
	 * @param callback
	 * @param caseCode
	 */
	public void accept(EntityCallback callback,String caseCode){
		entity(baseCodeFirst(caseCode), NetContacts.getInstance().ACCEPT, callback);
	}
	
	public void reject(EntityCallback callback,String caseCode, String rejectReason){
		entity(baseCodeFirst(caseCode, rejectReason), NetContacts.getInstance().REJECT, callback);
	}
	
	public void arrive(EntityCallback callback,String caseCode){

		entity(baseCodeFirst(caseCode), NetContacts.getInstance().ARRIVE, callback);


	}
	
	/**
	 *  确认工单已被修理
	 * @param callback
	 * @param caseCode
	 * @param signature 签名图片文件
	 */
	public void fix(EntityCallback callback,String caseCode,File signature ,String money){
		RequestParams params = baseCodeFirst(caseCode);
		params.addBodyParameter("signature", signature, "image/png");
		params.addBodyParameter("money", money);
		entity(params, NetContacts.getInstance().FIX, callback);
	}
	
	//客户评价工单
	public void evaluate(EntityCallback callback,String caseCode,String evaluateRate ,String evaluateContent){
		RequestParams params = baseCodeFirst(caseCode);
		params.addBodyParameter("evaluateRate", evaluateRate);
		params.addBodyParameter("evaluateContent", evaluateContent);
		entity(params, NetContacts.getInstance().EVALUATE, callback);
	}
	
	public void close(EntityCallback callback,String caseCode,String description  ){
		RequestParams params = baseCodeFirst(caseCode);
		params.addBodyParameter("description", description);
		entity(params, NetContacts.getInstance().CLOSE, callback);
	}

	public void createEquipmentCase(EntityCallback callback,String equipmentNo,String equipmentPmType) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("equipmentNo", equipmentNo);
		params.addBodyParameter("equipmentPmType", equipmentPmType);
		entity(params, NetContacts.getInstance().CREATEEQUIPMENTCASE, callback);
	}

	public void removeService(EntityCallback callback,String serviceRequestId) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("serviceRequestId", serviceRequestId);
		entity(params, NetContacts.getInstance().REMOVESERVICE, callback);
	}

	public void Auditor(EntityCallback callback,String caseCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode",caseCode);
		entity(params, NetContacts.getInstance().AUDITOR, callback);
	}
	public void caseApprove(EntityCallback callback,String caseCode){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode",caseCode);
		entity(params, NetContacts.getInstance().CASEAPPROVE, callback);
	}
	public void caseDisapprove(EntityCallback callback,String caseCode,String content){
		RequestParams params = new RequestParams();
		params.addBodyParameter("caseCode",caseCode);
		params.addBodyParameter("rejectReason",content);
		entity(params, NetContacts.getInstance().CASEDISAPPROVE, callback);
	}

	public void DeletePicture(EntityCallback callback,String pictureId){
		RequestParams params = new RequestParams();
		params.addBodyParameter("pictureId",pictureId);
		entity(params,NetContacts.getInstance().DELETEPICTURE,callback);

	}
	public void TUILIAO(Integer kuFangType, String caseCode, List<GoodsCaseDetailsBean> data, EntityCallback callback){
		RequestParams params = new RequestParams();
		String json = gson.toJson(data);
		params.addBodyParameter("caseCode",caseCode);
		params.addBodyParameter("kuFangType",kuFangType+"");
		params.addBodyParameter("data",json);
		entity(params,NetContacts.getInstance().TUILIAO,callback);

	}

	public void updatebuilding(Integer buildingId, EntityCallback callback){
		RequestParams params = new RequestParams();
		params.addBodyParameter("buildingId",buildingId+"");

		entity(params,NetContacts.getInstance().UPDATEBUILDING,callback);

	}

}
