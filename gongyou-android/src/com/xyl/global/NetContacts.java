package com.xyl.global;

import com.xyl.base.BaseApplication;
import com.xyl.utils.SharedPreferencesUtil;

/**
 * 进行各种字段的存储
 *
 * @author Administrator http://www.etiansoft.com/estate/
 */

public class NetContacts {

    private String baseUrl = "";

    private NetContacts() {
        setBaseUrl(SharedPreferencesUtil.getString(BaseApplication.getBaseApplication(), "BaseUrl"));
    }

    private static NetContacts net;

    public static NetContacts getInstance() {
        if (net == null) {
            net = new NetContacts();
        }
        return net;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String url) {
        baseUrl = url;
        SERVER_URL = baseUrl + "/estate";
        //	http://106.39.118.218:9011/images/2016-06-21/ed893ee043da485f86bd8860b5f5bb10.png
        BASE_IMAGE_URL = baseUrl + "/images";

        USER_LOGIN = SERVER_URL + "/mobile/login";

        USER_LOGOUT = SERVER_URL + "/mobile/logout";

        HEARTBEAT = SERVER_URL + "/mobile/heartbeat";

        STAFFINFF = SERVER_URL + "/mobile/staffInfo";

        REGISTER = SERVER_URL + "/mobile/register";

        RESETPASSWORD = SERVER_URL + "/mobile/resetPassword";

        UPLOADPHOTO = SERVER_URL + "/mobile/uploadPhoto";

        CREATEDCASES = SERVER_URL + "/mobile/case/createdCases";
        //客服已派发工单 英蓝请求不到
        ASSGINGROUPCASES = SERVER_URL + "/mobile/case/assginGroupCases";
        //组长派给工人工单  英蓝请求不到
        ASSGINSTAFFCASES = SERVER_URL + "/mobile/case/assginStaffCases";
        //pa001 已接受工单
        ACCEPTCASES = SERVER_URL + "/mobile/case/acceptCases";
        //已拒绝工单
        REJECTCASES = SERVER_URL + "/mobile/case/rejectCases";

        FIXEDCASES = SERVER_URL + "/mobile/case/fixedCases";

        CASES = SERVER_URL + "/mobile/case/cases";

        MYCASES = SERVER_URL + "/mobile/case/myCases";

        TEAMCASES = SERVER_URL + "/mobile/case/teamCases";

        DATA = SERVER_URL + "/mobile/case/data";

        UPLOADARRIVEPICTURE = SERVER_URL + "/mobile/case/uploadArrivePicture";

        UPLOADARRIVE = SERVER_URL + "/mobile/case/uploadArrive";

        CREATECUSTOMERANDASSIGNSTAFF = SERVER_URL
                + "/mobile/case/createCustomerAndAssignStaff";

        CREATEEQUIPMENTANDASSIGNSTAFF = SERVER_URL
                + "/mobile/case/createEquipmentAndAssignStaff";

        CREATEEQUIPMENTANDASSIGNGROUP = SERVER_URL
                + "/mobile/case/createEquipmentAndAssignGroup";

        ADDPARTNER = SERVER_URL + "/mobile/case/addPartner";

        DELETEPARTNER = SERVER_URL + "/mobile/case/deletePartner";

        ASSIGN = SERVER_URL + "/mobile/case/assign";

        ASSIGNTEAM = SERVER_URL + "/mobile/case/assignTeam";

        VIE = SERVER_URL + "/mobile/case/vie";

        FORWARD = SERVER_URL + "/mobile/case/forward";

        ACCEPT = SERVER_URL + "/mobile/case/accept";

        REJECT = SERVER_URL + "/mobile/case/reject";

        ARRIVE = SERVER_URL + "/mobile/case/arrive";

        FIX = SERVER_URL + "/mobile/case/fix";

        EVALUATE = SERVER_URL + "/mobile/case/evaluate";

        CLOSE = SERVER_URL + "/mobile/case/close";

        STAFFLIST = SERVER_URL + "/mobile/staffList";

        TEAMLIST = SERVER_URL + "/mobile/teamList";
        //获取工人工单权限的接口
        ACTIONS = SERVER_URL + "/mobile/workflow/actions";

        CREATESERVICE = SERVER_URL + "/mobile/service/createService";

        CREATESERVICENOTIMG = SERVER_URL + "/mobile/service/createServiceNotImg";

        SERVICES = SERVER_URL + "/mobile/service/services";

        MYSERVICES = SERVER_URL + "/mobile/service/myCreateServices";

        FINDBYID = SERVER_URL + "/mobile/service/findById";

        CATEGORIES = SERVER_URL + "/mobile/equipment/categories";

        FINDBYCATEGORYNO = SERVER_URL + "/mobile/equipment/findByCategoryNo";

        FINDBYEQUIPMENTNO = SERVER_URL + "/mobile/equipment/findByEquipmentNo";

        SCREEN_FILTRATE = SERVER_URL + "/mobile/equipment/findByExpiredOrNotExpired";

        MANTIONEQUIPMENTNO = SERVER_URL + ""
                + "/mobile/equipment/addMaintenance";

        PUSH = SERVER_URL + "/mobile/push";

        ASSIGNCASES = SERVER_URL + "/mobile/case/assignCases";

        SERVICEASSESSEDCASES = SERVER_URL + "/mobile/case/serviceAssessedCases";

        SERVICEASSIGNCASESSERVICEGOURP = SERVER_URL + "/mobile/case/serviceAssignCasesServiceGourp";

        ASSESSEDCASES = SERVER_URL + "/mobile/case/assessedCases";

        ALARMS = SERVER_URL + "/mobile/alarm/alarms";

        NOTICENET = SERVER_URL + "/mobile/notice/findNotice";

        FINDNOTICEINFO = SERVER_URL + "/mobile/notice/findNoticeInfo";

        SETPHONE = SERVER_URL + "/mobile/setPhone";

        DESCRIPTION = SERVER_URL + "/mobile/setDescription";

        ADDRESS = SERVER_URL + "/mobile/setAddress";

        UPDATEPASSWORD = SERVER_URL + "/mobile/updatePassword";

        SERVICESWORKER = SERVER_URL + "/mobile/service/servicesWorker";

        REMNANTCASEDATA = SERVER_URL + "/mobile/case/serviceCasesServiceAll";

        SEARCH = SERVER_URL + "/mobile/case/search";

        OwnerList = SERVER_URL + "/mobile/staffUserList";

        CREATEEQUIPMENTCASE = SERVER_URL + "/mobile/equipment/createEquipmentCase";

        REMOVESERVICE = SERVER_URL + "/mobile/service/removeService";

        GETVERSION = SERVER_URL + "/download/getVersion";

        AUDITOR = SERVER_URL + "/mobile/case/check";

        DELETEPICTURE = SERVER_URL + "/mobile/case/deletePicture";

        USERPHYSICALASSET = SERVER_URL + "/mobile/userEquipment/userPhysicalAsset";

        BYPHYSICALASSETID = SERVER_URL + "/mobile/userEquipment/findByPhysicalAssetId";

        BYUSEREQUIPMENTID = SERVER_URL + "/mobile/userEquipment/findByUserEquipmentId";

        EDITUPDATE = SERVER_URL + "/mobile/userEquipment/update";

        GETSTAFFLIST = SERVER_URL + "/mobile/userEquipment/getStaffList";

        USERICHART = SERVER_URL + "/mobile/userEquipment/getStatusData";

        GETGOODSCATEGORY = SERVER_URL + "/mobile/stockTaking/getGoodsCategory";

        GOODSCATEGORY = SERVER_URL + "/mobile/goodsCase/getGoodsCategory";

        FINDSTORAGE = SERVER_URL + "/mobile/goodsCase/findStorage";

        UPDATESTOCKTAKING = SERVER_URL + "/mobile/stockTaking/updateStockTaking";

        FINDBYGOODSCASEID = SERVER_URL + "/mobile/goodsCase/findByGoodsCaseId";
        EDITGOODSCASE = SERVER_URL + "/mobile/goodsCase/editGoodsCase";
        UPLOADDESCRIPTION = SERVER_URL + "/mobile/goodsCase/uploadDescription";
        CLOSED = SERVER_URL + "/mobile/goodsCase/closed";
        RESUBMIT = SERVER_URL + "/mobile/goodsCase/resubmit";
        verify = SERVER_URL + "/mobile/goodsCase/verify";
        STORAGE = SERVER_URL + "/mobile/goodsCase/storage";
        APPROVE = SERVER_URL + "/mobile/goodsCase/approve";
        EDITGOODSCASEID = SERVER_URL + "/mobile/goodsCase/editGoodsCaseId";
        SAVEAGREE = SERVER_URL + "/mobile/goodsCase/save";

        GOODSGETGOODSCATEGORY = SERVER_URL + "/mobile/goods/getGoodsCategory";

        CASEAPPLY = SERVER_URL + "/mobile/case/apply";

        CASEAPPROVE = SERVER_URL + "/mobile/case/approve";

        CASEDISAPPROVE = SERVER_URL + "/mobile/case/disapprove";

        FINDGOODSCASE = SERVER_URL + "/mobile/goodsCase/findGoodsCase";

        UPLOADPICTURE = SERVER_URL + "/mobile/goodsCase/uploadPicture";

        TUILIAO = SERVER_URL + "/mobile/goodsCase/tuiLiao";

        GETCASESIMPLEVOS = SERVER_URL + "/mobile/case/getCaseSimpleVos";

        GOODSSEARCH = SERVER_URL + "/mobile/goods/search";

        FINDALLBUILDING = SERVER_URL + "/mobile/building/findAllBuilding";

        QINGJIASAVE = SERVER_URL + "/mobile/renShi/qingJiaSave";

        SEALSAVE = SERVER_URL + "/mobile/renShi/yongYinShenQingSave";

        APPROVAL = SERVER_URL + "/mobile/renShi/tongYongShenPiSave";

        STANDBYSUBMIT = SERVER_URL + "/mobile/renShi/beiYongJinShenQingSave";


        PURCHASESUBMIT = SERVER_URL + "/mobile/renShi/caiGouSave";

        MEETPURCHASESUBMIT = SERVER_URL + "/mobile/renShi/yingJiCaiGouSave";

        REIMBURSEMENT = SERVER_URL + "/mobile/renShi/baoXiaoSave";

        GETAPPLYPEOPLE = SERVER_URL + "/mobile/getStaffs";

        SELECTALLPERSONAL = SERVER_URL + "/mobile/renShi/findAll";

        LEAVEDETAILS = SERVER_URL + "/mobile/renShi/findByIdQingJia";

        APPROVALDETAIL = SERVER_URL + "/mobile/renShi/findByIdTongYongShenPi";

        STANDBYDETAIL = SERVER_URL + "/mobile/renShi/findByIdBeiYongJinShenQing";

        SEALDETAIL = SERVER_URL + "/mobile/renShi/findByIdYongYinShenQing";

        EMERGENCYDETAIL = SERVER_URL + "/mobile/renShi/findByIdYingJiCaiGou";

        QINGJIASAVENOIMAG = SERVER_URL + "/mobile/renShi/qingJiaSaveNoImg";

        SEALSAVENOIMG = SERVER_URL + "/mobile/renShi/yongYinShenQingSaveNoImg";

        APPROVALNOIMG = SERVER_URL + "/mobile/renShi/tongYongShenPiSaveNoImg";

        STANDBYSUBMITNOIMG = SERVER_URL + "/mobile/renShi/beiYongJinShenQingSaveNoImg";

        PURCHASESUBMITNOIMG = SERVER_URL + "/mobile/renShi/caiGouSaveNoImg";

        MEETPURCHASESUBMITNOIMG = SERVER_URL + "/mobile/renShi/yingJiCaiGouSaveNoImg";

        REIMBURSEMENTNOIMG = SERVER_URL + "/mobile/renShi/baoXiaoSaveNoImg";

        PURCHASEDETAIL = SERVER_URL + "/mobile/renShi/findByIdCaiGou";

        REIMBURSEDETAIL = SERVER_URL + "/mobile/renShi/findByIdBaoXiao";

        WORKFLOW = SERVER_URL + "/mobile/renShi/findGongZuoLiu";

        RENSHIAPPROVAL = SERVER_URL + "/mobile/renShi/approval";

        QUFANCHENGQKFINDALL = SERVER_URL + "/mobile/quFanChengQK/findAll";

        QUFANCHENGQKFINDBYID = SERVER_URL + "/mobile/quFanChengQK/findById";

        QUFANCHENGQKSAVEORUPDATE = SERVER_URL + "/mobile/quFanChengQK/saveOrUpdate";

        QUFANCHENGQKREMOVE = SERVER_URL + "/mobile/quFanChengQK/remove";

        JIANKANGQKFINDALL = SERVER_URL + "/mobile/jianKangQK/findAll";

        JIANKANGQKFINDBYID = SERVER_URL + "/mobile/jianKangQK/findById";

        JIANKANGQKSAVEORUPDATE = SERVER_URL + "/mobile/jianKangQK/saveOrUpdate";

        JIANKANGQKREMOVE = SERVER_URL + "/mobile/jianKangQK/remove";

        FINDALLSTAFF = SERVER_URL + "/mobile/findAllStaff";


        DATIDATA = SERVER_URL + "/mobile/daTi/data";

        DATIFINDBYID = SERVER_URL + "/mobile/daTi/findById";

        SAVEORUPDATEDATI = SERVER_URL + "/mobile/daTi/saveOrUpdateDaTi";

        DATIMYDATA = SERVER_URL + "/mobile/daTi/myData";

        UPDATEBUILDING = SERVER_URL + "/mobile/updateBuilding";

        FINDALLBUILDINGVOS = SERVER_URL + "/mobile/building/findAllBuildingVos";

        CMSDATA = SERVER_URL + "/mobile/cms/data";

        FINDPERMISSIONS = SERVER_URL + "/role/findPermissions";
    }

    public static int pageIndex;

    public String SERVER_URL = baseUrl + "/estate";

    /**
     * 图片接口有问题
     */
    // public String BASE_IMAGE_URL =
    // "http://www.etiansoft.com/estate/picture/download/";

    public String BASE_IMAGE_URL = baseUrl + "/images";

    public String USER_LOGIN = SERVER_URL + "/mobile/login";

    public String USER_LOGOUT = SERVER_URL + "/mobile/logout";

    public String HEARTBEAT = SERVER_URL + "/mobile/heartbeat";

    public String STAFFINFF = SERVER_URL + "/mobile/staffInfo";

    public String REGISTER = SERVER_URL + "/mobile/register";

    public String RESETPASSWORD = SERVER_URL + "/mobile/resetPassword";

    public String UPLOADPHOTO = SERVER_URL + "/mobile/uploadPhoto";

    public String CREATEDCASES = SERVER_URL + "/mobile/case/createdCases";
    //派给组的工单
    public String ASSGINGROUPCASES = SERVER_URL
            + "/mobile/case/assginGroupCases";
    //派给工人的工单
    public String ASSGINSTAFFCASES = SERVER_URL
            + "/mobile/case/assginStaffCases";

    public String ACCEPTCASES = SERVER_URL + "/mobile/case/acceptCases";

    public String REJECTCASES = SERVER_URL + "/mobile/case/rejectCases";

    public String FIXEDCASES = SERVER_URL + "/mobile/case/fixedCases";

    public String CASES = SERVER_URL + "/mobile/case/cases";

    public String MYCASES = SERVER_URL + "/mobile/case/myCases";

    public String TEAMCASES = SERVER_URL + "/mobile/case/teamCases";

    public String DATA = SERVER_URL + "/mobile/case/data";

    public String UPLOADARRIVEPICTURE = SERVER_URL
            + "/mobile/case/uploadArrivePicture";

    public String UPLOADARRIVE = SERVER_URL + "/mobile/case/uploadArrive";

    /*public String CREATECUSTOMERANDASSIGNSTAFF = SERVER_URL
            + "/mobile/case/createCcase/casesustomerAndAssignStaff";*/
    public String CREATECUSTOMERANDASSIGNSTAFF = SERVER_URL
            + "/mobile/case/createCustomerAndAssignStaff";

    public String CREATEEQUIPMENTANDASSIGNSTAFF = SERVER_URL
            + "/mobile/case/createEquipmentAndAssignStaff";

    public String CREATEEQUIPMENTANDASSIGNGROUP = SERVER_URL
            + "/mobile/case/createEquipmentAndAssignGroup";

    public String ADDPARTNER = SERVER_URL + "/mobile/case/addPartner";

    public String DELETEPARTNER = SERVER_URL + "/mobile/case/deletePartner";

    public String ASSIGN = SERVER_URL + "/mobile/case/assign";

    public String ASSIGNTEAM = SERVER_URL + "/mobile/case/assignTeam";

    public String VIE = SERVER_URL + "/mobile/case/vie";

    public String FORWARD = SERVER_URL + "/mobile/case/forward";

    public String ACCEPT = SERVER_URL + "/mobile/case/accept";

    public String REJECT = SERVER_URL + "/mobile/case/reject";

    public String ARRIVE = SERVER_URL + "/mobile/case/arrive";

    public String FIX = SERVER_URL + "/mobile/case/fix";

    public String EVALUATE = SERVER_URL + "/mobile/case/evaluate";

    public String CLOSE = SERVER_URL + "/mobile/case/close";
    //获取工人列表的接口
    public String STAFFLIST = SERVER_URL + "/mobile/staffList";

    public String TEAMLIST = SERVER_URL + "/mobile/teamList";

    public String ACTIONS = SERVER_URL + "/mobile/workflow/actions";

    public String CREATESERVICE = SERVER_URL + "/mobile/service/createService";

    public String CREATESERVICENOTIMG = SERVER_URL + "/mobile/service/createServiceNotImg";

    public String SERVICES = SERVER_URL + "/mobile/service/services";

    public String MYSERVICES = SERVER_URL + "/mobile/service/myCreateServices";

    public String FINDBYID = SERVER_URL + "/mobile/service/findById";
    //预防性维保
    public String CATEGORIES = SERVER_URL + "/mobile/equipment/categories";

    public String FINDBYCATEGORYNO = SERVER_URL
            + "/mobile/equipment/findByCategoryNo";

    public String FINDBYEQUIPMENTNO = SERVER_URL
            + "/mobile/equipment/findByEquipmentNo";
    public String SCREEN_FILTRATE = SERVER_URL + "/mobile/equipment/findByExpiredOrNotExpired";

    public String MANTIONEQUIPMENTNO = SERVER_URL + ""
            + "/mobile/equipment/addMaintenance";
    public String PUSH = SERVER_URL + "/mobile/push";
    //物业经理查看已派发工单
    public String ASSIGNCASES = SERVER_URL + "/mobile/case/assignCases";
    //客服查看已派发工单
    public String SERVICEASSESSEDCASES = SERVER_URL + "/mobile/case/serviceAssessedCases";

    public String SERVICEASSIGNCASESSERVICEGOURP = SERVER_URL + "/mobile/case/serviceAssignCasesServiceGourp";

    public String ASSESSEDCASES = SERVER_URL + "/mobile/case/assessedCases";
    //设备报警
    public String ALARMS = SERVER_URL + "/mobile/alarm/alarms";

    public String NOTICENET = SERVER_URL + "/mobile/notice/findNotice";

    public String FINDNOTICEINFO = SERVER_URL + "/mobile/notice/findNoticeInfo";

    public String SETPHONE = SERVER_URL + "/mobile/setPhone";

    public String DESCRIPTION = SERVER_URL + "/mobile/setDescription";

    public String ADDRESS = SERVER_URL + "/mobile/setAddress";

    public String UPDATEPASSWORD = SERVER_URL + "/mobile/updatePassword";

    public String SERVICESWORKER = SERVER_URL + "/mobile/service/servicesWorker";

    public String REMNANTCASEDATA = SERVER_URL + "/mobile/case/serviceCasesServiceAll";

    public String SEARCH = SERVER_URL + "/mobile/case/search";

    public String OwnerList = SERVER_URL + "/mobile/staffUserList";

    public String CREATEEQUIPMENTCASE = SERVER_URL + "/mobile/equipment/createEquipmentCase";

    public String REMOVESERVICE = SERVER_URL + "/mobile/service/removeService";

    public String GETVERSION = SERVER_URL + "/download/getVersion";

    public String AUDITOR = SERVER_URL + "/mobile/case/check";

    public String DELETEPICTURE = SERVER_URL + "/mobile/case/deletePicture";
    //获取资产类型列表
    public String USERPHYSICALASSET = SERVER_URL + "/mobile/userEquipment/userPhysicalAsset";

    public String BYPHYSICALASSETID = SERVER_URL + "/mobile/userEquipment/findByPhysicalAssetId";

    public String BYUSEREQUIPMENTID = SERVER_URL + "/mobile/userEquipment/findByUserEquipmentId";

    public String EDITUPDATE = SERVER_URL + "/mobile/userEquipment/update";

    public String GETSTAFFLIST = SERVER_URL + "/mobile/userEquipment/getStaffList";

    public String USERICHART = SERVER_URL + "/mobile/userEquipment/getStatusData";

    public String GETGOODSCATEGORY = SERVER_URL + "/mobile/stockTaking/getGoodsCategory";

    public String GOODSCATEGORY = SERVER_URL + "/mobile/goodsCase/getGoodsCategory";

    public String GOODSGETGOODSCATEGORY = SERVER_URL + "/mobile/goods/getGoodsCategory";

    public String FINDSTORAGE = SERVER_URL + "/mobile/goodsCase/findStorage";

    public String UPDATESTOCKTAKING = SERVER_URL + "/mobile/stockTaking/updateStockTaking";

    public String FINDBYGOODSCASEID = SERVER_URL + "/mobile/goodsCase/findByGoodsCaseId";

    public String EDITGOODSCASE = SERVER_URL + "/mobile/goodsCase/editGoodsCase";

    public String UPLOADDESCRIPTION = SERVER_URL + "/mobile/goodsCase/uploadDescription";

    public String CLOSED = SERVER_URL + "/mobile/goodsCase/closed";

    public String RESUBMIT = SERVER_URL + "/mobile/goodsCase/resubmit";

    public String verify = SERVER_URL + "/mobile/goodsCase/verify";

    public String STORAGE = SERVER_URL + "/mobile/goodsCase/storage";

    public String APPROVE = SERVER_URL + "/mobile/goodsCase/approve";

    public String EDITGOODSCASEID = SERVER_URL + "/mobile/goodsCase/editGoodsCaseId";

    public String GETCATEGORYS = SERVER_URL + "/mobile/goodsCase/getCategorys";

    public String FINDALLGOODS = SERVER_URL + "/mobile/goodsCase/findAllGoods";

    public String SAVESTOCKTAKING = SERVER_URL + "/mobile/stockTaking/saveStockTaking";

    public String GETALLSTOCKTAKING = SERVER_URL + "/mobile/stockTaking/getAllStockTaking";

    public String FINDBYSTOCKTAKINGID = SERVER_URL + "/mobile/stockTaking/findByStockTakingId";

    //生成入库单、出库单、盘点单接口
    public String SAVEAGREE = SERVER_URL + "/mobile/goodsCase/save";

    public String CASEAPPLY = SERVER_URL + "/mobile/case/apply";

    public String CASEAPPROVE = SERVER_URL + "/mobile/case/approve";

    public String CASEDISAPPROVE = SERVER_URL + "/mobile/case/disapprove";

    public String FINDGOODSCASE = SERVER_URL + "/mobile/goodsCase/findGoodsCase";

    public String UPLOADPICTURE = SERVER_URL + "/mobile/goodsCase/uploadPicture";

    public String TUILIAO = SERVER_URL + "/mobile/goodsCase/tuiLiao";

    public String GETCASESIMPLEVOS = SERVER_URL + "/mobile/case/getCaseSimpleVos";

    public String GOODSSEARCH = SERVER_URL + "/mobile/goods/search";

    public String FINDALLBUILDING = SERVER_URL + "/mobile/building/findAllBuilding";


    public String QINGJIASAVE = SERVER_URL + "/mobile/renShi/qingJiaSave";

    public String SEALSAVE = SERVER_URL + "/mobile/renShi/yongYinShenQingSave";


    public String APPROVAL = SERVER_URL + "/mobile/renShi/tongYongShenPiSave";


    public String STANDBYSUBMIT = SERVER_URL + "/mobile/renShi/beiYongJinShenQingSave";


    public String PURCHASESUBMIT = SERVER_URL + "/mobile/renShi/caiGouSave";


    public String MEETPURCHASESUBMIT = SERVER_URL + "/mobile/renShi/yingJiCaiGouSave";


    public String REIMBURSEMENT = SERVER_URL + "/mobile/renShi/baoXiaoSave";

    public String PURCHASESUBMITNOIMG = SERVER_URL + "/mobile/renShi/caiGouSaveNoImg";

    public String MEETPURCHASESUBMITNOIMG = SERVER_URL + "/mobile/renShi/yingJiCaiGouSaveNoImg";


    public String REIMBURSEMENTNOIMG = SERVER_URL + "/mobile/renShi/baoXiaoSaveNoImg";


    public String GETAPPLYPEOPLE = SERVER_URL + "/mobile/getStaffs";


    public String SELECTALLPERSONAL = SERVER_URL + "/mobile/renShi/findAll";


    public String LEAVEDETAILS = SERVER_URL + "/mobile/renShi/findByIdQingJia";


    public String APPROVALDETAIL = SERVER_URL + "/mobile/renShi/findByIdTongYongShenPi";

    public String STANDBYDETAIL = SERVER_URL + "/mobile/renShi/findByIdBeiYongJinShenQing";

    public String SEALDETAIL = SERVER_URL + "/mobile/renShi/findByIdYongYinShenQing";

    public String PURCHASEDETAIL = SERVER_URL + "/mobile/renShi/findByIdCaiGou";

    public String EMERGENCYDETAIL = SERVER_URL + "/mobile/renShi/findByIdYingJiCaiGou";

    public String REIMBURSEDETAIL = SERVER_URL + "/mobile/renShi/findByIdBaoXiao";

    public String QINGJIASAVENOIMAG = SERVER_URL + "/mobile/renShi/qingJiaSaveNoImg";

    public String SEALSAVENOIMG = SERVER_URL + "/mobile/renShi/yongYinShenQingSaveNoImg";

    public String APPROVALNOIMG = SERVER_URL + "/mobile/renShi/tongYongShenPiSaveNoImg";

    public String STANDBYSUBMITNOIMG = SERVER_URL + "/mobile/renShi/beiYongJinShenQingSaveNoImg";


    public String WORKFLOW = SERVER_URL + "/mobile/renShi/findGongZuoLiu";


    public String RENSHIAPPROVAL = SERVER_URL + "/mobile/renShi/approval";


    public String QUFANCHENGQKFINDALL = SERVER_URL + "/mobile/quFanChengQK/findAll";

    public String QUFANCHENGQKFINDBYID = SERVER_URL + "/mobile/quFanChengQK/findById";

    public String QUFANCHENGQKSAVEORUPDATE = SERVER_URL + "/mobile/quFanChengQK/saveOrUpdate";

    public String QUFANCHENGQKREMOVE = SERVER_URL + "/mobile/quFanChengQK/remove";


    public String JIANKANGQKFINDALL = SERVER_URL + "/mobile/jianKangQK/findAll";

    public String JIANKANGQKFINDBYID = SERVER_URL + "/mobile/jianKangQK/findById";

    public String JIANKANGQKSAVEORUPDATE = SERVER_URL + "/mobile/jianKangQK/saveOrUpdate";

    public String JIANKANGQKREMOVE = SERVER_URL + "/mobile/jianKangQK/remove";


    public String FINDALLSTAFF = SERVER_URL + "/mobile/findAllStaff";

    public String DATIDATA = SERVER_URL + "/mobile/daTi/data";

    public String DATIFINDBYID = SERVER_URL + "/mobile/daTi/findById";

    public String SAVEORUPDATEDATI = SERVER_URL + "/mobile/daTi/saveOrUpdateDaTi";

    public String DATIMYDATA = SERVER_URL + "/mobile/daTi/myData";

    public String UPDATEBUILDING = SERVER_URL + "/mobile/updateBuilding";


    public String FINDALLBUILDINGVOS = SERVER_URL + "/mobile/building/findAllBuildingVos";

    public String CMSDATA = SERVER_URL + "/mobile/cms/data";

    public String FINDPERMISSIONS = SERVER_URL + "/role/findPermissions";




}
