package com.xyl.net;

import com.xyl.base.BaseNet;
import com.xyl.domain.MessageBean;
import com.xyl.global.NetContacts;
import com.lidroid.xutils.http.RequestParams;

public class NewNet extends BaseNet {
	// 创建客户工单并派发给工人
	/**
	 * 
	 * @param baseCallback
	 *            回掉方法
	 * @param serviceRequestId
	 *            服务请求编号
	 * @param staffCode
	 *            维修组
	 * @param priority
	 *            优先级(0低、1中、2高)
	 */
	//caseMoneyType, caseArea, contentAndNote, fixMoney, caseProfession
	public void createCustomerAndAssignStaff(
			BaseCallback<MessageBean> baseCallback, String serviceRequestId,
			String staffCode, String priority, String caseMoneyType, String caseArea,
			String caseProfession, String contentAndNote, String fixMoney) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("serviceRequestId", serviceRequestId + "");
		params.addBodyParameter("staffCode", staffCode + "");
		params.addBodyParameter("priority", priority + "");
		params.addBodyParameter("caseMoneyType", caseMoneyType + "");
		params.addBodyParameter("caseArea", caseArea + "");
		params.addBodyParameter("contentAndNote", contentAndNote + "");
		params.addBodyParameter("fixMoney", fixMoney + "");
		params.addBodyParameter("caseProfession", caseProfession + "");
		baseMethod(params, NetContacts.getInstance().CREATECUSTOMERANDASSIGNSTAFF,
				baseCallback, MessageBean.class);
	}

	// 创建客户工单并派发给组
	/**
	 * 
	 * @param baseCallback
	 *            回掉方法
	 * @param serviceRequestId
	 *            服务请求编号
	 * @param staffCode
	 *            维修组
	 * @param priority
	 *            优先级(0低、1中、2高)
	 */
	public void createCustomerAndAssignGroup(
			BaseCallback<MessageBean> baseCallback, String serviceRequestId,
			String teamId, String priority, String caseMoneyType, String caseArea,
			String caseProfession, String contentAndNote, String fixMoney){
		RequestParams params = new RequestParams();
		params.addBodyParameter("serviceRequestId", serviceRequestId + "");
		params.addBodyParameter("teamId", teamId + "");
		params.addBodyParameter("priority", priority + "");
		params.addBodyParameter("caseMoneyType", caseMoneyType + "");
		params.addBodyParameter("caseArea", caseArea + "");
		params.addBodyParameter("contentAndNote", contentAndNote + "");
		params.addBodyParameter("fixMoney", fixMoney + "");
		params.addBodyParameter("caseProfession", caseProfession + "");
		baseMethod(params, NetContacts.getInstance().SERVER_URL
				+ "/mobile/case/createCustomerAndAssignGroup", baseCallback,
				MessageBean.class);
	}
	
	public void updateWorkOrder(
			BaseCallback<MessageBean> baseCallback,String repairCaseCode, 
			String priority, String caseMoneyType, String caseArea,
			String caseProfession, String fixMoney , String contentAndNote){
		RequestParams params = new RequestParams();
		params.addBodyParameter("repairCaseCode", repairCaseCode + "");
		params.addBodyParameter("priority", priority+ "");
		params.addBodyParameter("caseMoneyType", caseMoneyType + "");
		params.addBodyParameter("caseArea", caseArea + "");
		params.addBodyParameter("caseProfession", caseProfession + "");
		params.addBodyParameter("fixMoney", fixMoney + "");
		params.addBodyParameter("contentAndNote", contentAndNote + "");
		baseMethod(params, NetContacts.getInstance().SERVER_URL
				+ "/mobile/case/update", baseCallback,
				MessageBean.class);
		
	}
	 
}
