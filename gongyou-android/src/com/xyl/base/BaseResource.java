package com.xyl.base;

import com.xyl.R;
import com.xyl.domain.WorkInfoBean;
import com.xyl.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseResource {

    private static HashMap<String, Integer> stateMap;
    private ArrayList<WorkInfoBean> gridList;
    private ArrayList<WorkInfoBean> menuList;

    public static HashMap<String, Integer> getResourceState() {
        if (stateMap == null) {
            stateMap = new HashMap<String, Integer>();
        }
        stateMap.put("done", R.drawable.state_finish);
        stateMap.put("assign-staff", R.drawable.state_group);
        stateMap.put("assign-group", R.drawable.state_group);
        //stateMap.put("close", R.drawable.state_delete);
        //stateMap.put("已作废", R.drawable.state_delete);
        stateMap.put("fixed", R.drawable.state_allot);
        stateMap.put("arrived", R.drawable.state_group);
        stateMap.put("rejected", R.drawable.state_group);
        stateMap.put("accepted", R.drawable.state_group);
        stateMap.put("checkd", R.drawable.state_finish);
        stateMap.put("vied", R.drawable.state_group);
        stateMap.put("closed", R.drawable.state_delete);
        return stateMap;
    }

    public ArrayList<WorkInfoBean> getResourceGrid(String type) {
        if (gridList == null) {
            gridList = new ArrayList<WorkInfoBean>();
        }
        gridList.clear();
        int t = StringUtil.str2Int(type);
        switch (t) {
            case 0:
                addInfoUser();
                break;
            case 1:
                addInfoWorker();
                break;
            case 2:
                addInfoLeader();
                break;
            case 3:
                addInfoService();
                break;
            case 4:
                addInfoManager();
                break;
            case 5:
                addInfoBoss();
                break;
            case 6:
                addStorage();
                break;
            case 7:
                addHealth();
                break;
            case 12:
                addOperation();
                break;
        }
        return gridList;
    }


    public ArrayList<WorkInfoBean> getResourceMenu(String type) {
        if (menuList == null) {
            menuList = new ArrayList<WorkInfoBean>();
        }
        menuList.clear();
        int t = StringUtil.str2Int(type);
        switch (t) {
            case 1:
                addMenuWorker();
                break;
            case 2:
                addMenuGroup();
                break;
            case 3:
                addMenuService();
                break;
            case 4:
                addMenuLeader();
                break;
            case 5:
                addMenuLeader();
                break;
            case 12:
                addMenuLeader();
                break;


        }
        return menuList;
    }


    private void addMenuWorker() {
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "我的单"));
        menuList.add(new WorkInfoBean(R.drawable.group_work, "组工单"));
        //menuList.add(new WorkInfoBean(R.drawable.accept_work, "已接受工单"));
        menuList.add(new WorkInfoBean(R.drawable.finish_work, "维修工单"));
        //menuList.add(new WorkInfoBean(R.drawable.accept_work, "资产管理"));
        menuList.add(new WorkInfoBean(R.drawable.pre_protext, "预防维保"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_warg, "设备报警"));
        menuList.add(new WorkInfoBean(R.drawable.menu_service_finish, "报修工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
    }

    private void addMenuGroup() {
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "我的单"));
        menuList.add(new WorkInfoBean(R.drawable.group_work, "派给组"));
        menuList.add(new WorkInfoBean(R.drawable.accept_work, "派发工单"));
        menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "拒绝工单"));
        //menuList.add(new WorkInfoBean(R.drawable.accept_work, "资产管理"));
        menuList.add(new WorkInfoBean(R.drawable.pre_protext, "预防维保"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_warg, "设备报警"));
        menuList.add(new WorkInfoBean(R.drawable.menu_service_finish, "报修工单"));
        menuList.add(new WorkInfoBean(R.drawable.state_allot, "遗留工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
    }

    private void addMenuService() {
        menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "客户报修"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "我的单"));
        menuList.add(new WorkInfoBean(R.drawable.group_work, "派发工单"));
        menuList.add(new WorkInfoBean(R.drawable.finish_work, "维修工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_service_finish, "已评价"));
        menuList.add(new WorkInfoBean(R.drawable.menu_service_finish, "报修工单"));
        menuList.add(new WorkInfoBean(R.drawable.state_allot, "遗留工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
    }

    private void addMenuLeader() {
        menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "客户报修"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "我的单"));
        menuList.add(new WorkInfoBean(R.drawable.group_work, "派发工单"));
        menuList.add(new WorkInfoBean(R.drawable.accept_work, "接受工单"));
        menuList.add(new WorkInfoBean(R.drawable.user_request_fix, "拒绝工单"));
        //menuList.add(new WorkInfoBean(R.drawable.accept_work, "资产管理"));
        menuList.add(new WorkInfoBean(R.drawable.pre_protext, "预防维保"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_warg, "设备报警"));
        menuList.add(new WorkInfoBean(R.drawable.menu_service_finish, "报修工单"));
        menuList.add(new WorkInfoBean(R.drawable.state_allot, "遗留工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_all, "全部工单"));
        menuList.add(new WorkInfoBean(R.drawable.menu_work_me, "创建工单"));
    }

    private void addInfoUser() {
        gridList.add(new WorkInfoBean(R.drawable.home_user_request, "日常报修"));
        gridList.add(new WorkInfoBean(R.drawable.home_user_state, "维修状态"));
        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "维修评价"));
        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "出入库"));
        gridList.add(new WorkInfoBean(R.drawable.home_user_notify, "楼宇通知"));
    }

    private void addInfoWorker() {
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "我的单"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "组工单"));
        //gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "已接受工单"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_service, "维修工单"));
        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "出入库"));
        gridList.add(new WorkInfoBean(R.drawable.qingjia, "请假"));
        gridList.add(new WorkInfoBean(R.drawable.shenpi, "通用审批"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "资产管理"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "预防维保"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_service, "健康表"));
        gridList.add(new WorkInfoBean(R.drawable.home_boss_shequ, "培训"));
    }

    private void addInfoLeader() {
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "我的单"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "派给组"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "派发工单"));
        gridList.add(new WorkInfoBean(R.drawable.outin, "出入库"));
        gridList.add(new WorkInfoBean(R.drawable.qingjia, "请假"));
        gridList.add(new WorkInfoBean(R.drawable.shenpi, "通用审批"));
        gridList.add(new WorkInfoBean(R.drawable.caigou, "应急采购"));
        gridList.add(new WorkInfoBean(R.drawable.caigou, "采购"));
        gridList.add(new WorkInfoBean(R.drawable.yongyin, "用印申请"));
        gridList.add(new WorkInfoBean(R.drawable.beiyong, "备用金"));
        gridList.add(new WorkInfoBean(R.drawable.beiyong, "报销"));
        //gridList.add(new WorkInfoBean(R.drawable.home_group_none, "已拒绝工单"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "资产管理"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "预防维保"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_service, "健康表"));
        gridList.add(new WorkInfoBean(R.drawable.home_boss_shequ, "培训"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "通知"));
    }

    private void addInfoService() {
        gridList.add(new WorkInfoBean(R.drawable.user_function_request, "客户报修"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "派发工单"));
        gridList.add(new WorkInfoBean(R.drawable.outin, "出入库"));
        gridList.add(new WorkInfoBean(R.drawable.qingjia, "请假"));
        gridList.add(new WorkInfoBean(R.drawable.shenpi, "通用审批"));
        // gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "采购"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "出库"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "盘库"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_service, "已维修"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_comment, "已评价"));
        gridList.add(new WorkInfoBean(R.drawable.home_boss_shequ, "培训"));
    }

    private void addInfoManager() {
        gridList.add(new WorkInfoBean(R.drawable.user_function_request, "客户报修"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "派发工单"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "我的单"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "组工单"));
        gridList.add(new WorkInfoBean(R.drawable.outin, "出入库"));
        gridList.add(new WorkInfoBean(R.drawable.qingjia, "请假"));
        gridList.add(new WorkInfoBean(R.drawable.shenpi, "通用审批"));
        gridList.add(new WorkInfoBean(R.drawable.caigou, "应急采购"));
        gridList.add(new WorkInfoBean(R.drawable.caigou, "采购"));
        gridList.add(new WorkInfoBean(R.drawable.yongyin, "用印申请"));
        gridList.add(new WorkInfoBean(R.drawable.beiyong, "备用金"));
        gridList.add(new WorkInfoBean(R.drawable.beiyong, "报销"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "出入库"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "请假"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "通用审批"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "应急采购"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "采购"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "用印申请"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "备用金"));
        //gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "已接受工单"));
        gridList.add(new WorkInfoBean(R.drawable.home_group_none, "拒绝工单"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "资产管理"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "预防维保"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_service, "健康表"));
        gridList.add(new WorkInfoBean(R.drawable.home_boss_shequ, "培训"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "通知"));
        // gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "培训"));
    }

    private void addInfoBoss() {
        gridList.add(new WorkInfoBean(R.drawable.home_boss_workinfo, "工单"));
        gridList.add(new WorkInfoBean(R.drawable.home_boss_nengyuan, "能源管理"));
        gridList.add(new WorkInfoBean(R.drawable.outin, "出入库"));
        gridList.add(new WorkInfoBean(R.drawable.qingjia, "请假"));
        gridList.add(new WorkInfoBean(R.drawable.shenpi, "通用审批"));
        gridList.add(new WorkInfoBean(R.drawable.caigou, "应急采购"));
        gridList.add(new WorkInfoBean(R.drawable.caigou, "采购"));
        gridList.add(new WorkInfoBean(R.drawable.yongyin, "用印申请"));
        gridList.add(new WorkInfoBean(R.drawable.beiyong, "备用金"));
        gridList.add(new WorkInfoBean(R.drawable.beiyong, "报销"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "出入库"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "请假"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "通用审批"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "应急采购"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "采购"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "用印申请"));
//        gridList.add(new WorkInfoBean(R.drawable.home_user_pj, "备用金"));
        //gridList.add(new WorkInfoBean(R.drawable.home_boss_kongqi, "空气质量"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "设备维护"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_allow, "资产管理"));
        //gridList.add(new WorkInfoBean(R.drawable.home_boss_shequ, "楼宇通知"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "设备报警"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_service, "健康表"));
        gridList.add(new WorkInfoBean(R.drawable.home_boss_shequ, "培训"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_warg, "通知"));
    }


    //添加到出入路
    private void addStorage() {
        gridList.add(new WorkInfoBean(R.drawable.ruku, "入库"));
        gridList.add(new WorkInfoBean(R.drawable.chuku, "出库"));
        gridList.add(new WorkInfoBean(R.drawable.panku, "盘库"));
    }


    private void addHealth() {

        gridList.add(new WorkInfoBean(R.drawable.ruku, "去反情况"));
        gridList.add(new WorkInfoBean(R.drawable.chuku, "健康情况"));

    }

    private void addOperation() {

        gridList.add(new WorkInfoBean(R.drawable.home_user_request, "日常报修"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_request, "客户报修"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "派发工单"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "我的单"));
        gridList.add(new WorkInfoBean(R.drawable.user_function_allot, "组工单"));
        gridList.add(new WorkInfoBean(R.drawable.home_work_pre, "预防维保"));

    }

}
