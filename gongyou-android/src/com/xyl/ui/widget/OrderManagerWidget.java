package com.xyl.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.xyl.R;
import com.xyl.adapter.OrderShopAdapter;
import com.xyl.base.BaseNet.BaseCallback;
import com.xyl.domain.AlarmsBean;
import com.xyl.domain.DataBean;

import com.xyl.domain.Equipment;
import com.xyl.domain.FindByAssetNoBean;
import com.xyl.domain.FindByEquipmentNoBean;
import com.xyl.domain.FindNoticeBean;
import com.xyl.domain.LoginBean;
import com.xyl.domain.MessageBean;
import com.xyl.domain.PartnersBean;
import com.xyl.domain.ServicePictures;
import com.xyl.domain.Traces;
import com.xyl.global.NetContacts;
import com.xyl.net.OrderManager;
import com.xyl.net.UserManager;
import com.xyl.ui.activity.OrderStateActivity;
import com.xyl.ui.activity.OverhaulActivity;
import com.xyl.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderManagerWidget extends LinearLayout {
    private final Context mContext;
    private Equipment equip;
    private MessageWidget textW1;
    private MessageWidget textW2;
    private MessageWidget textW3;
    private MessageWidget textW4;
    private MessageWidget textW5;
    private MessageWidget textW6;
    private MessageWidget textW7;
    private LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    private MessagePicWidget picW1;
    private MessagePicWidget picW2;
    private MessagePicWidget picW3;
    private MessageProcessWidget process;
    private MessageProcessWidget process2;

    private DataBean data;
    private MessagePicWidget picW4;
    private MessagePicWidget findNotice;
    private ChooseWorkerWidget chooseWorkerWidget;
    private int flag;
    private String status;
    private String genre;
    private FindByEquipmentNoBean equmentBean;
    private LoginBean loginBean;
    private FindByAssetNoBean.RecordsBean assetBean;

    public OrderManagerWidget(Context context) {
        super(context);
        this.setOrientation(VERTICAL);
        this.mContext = context;
        initView(context);
        // login(); // 测试方法 勿动
    }

    // 测试方法 勿动
    private void login() {
        new UserManager().userLoginIn("t21", "1",
                new BaseCallback<LoginBean>() {

                    @Override
                    public void messageSuccess(LoginBean bean) {
                        initBean();
                    }

                    @Override
                    public void messageFailure(MessageBean backError) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void connectFailure(MessageBean messageBean) {
                        // TODO Auto-generated method stub

                    }
                });
    }

    // 测试方法 勿动
    private void initBean() {
        new OrderManager().data("CUS_0000155", new BaseCallback<DataBean>() {
            @Override
            public void messageSuccess(DataBean bean) {
                data = bean;
                flag = 2;
                genre = "1";
                status = data.status;
                equip = data.equipment;

                //jibenxinxi();
                // kehumiaoshu();
                // gongdanzhuangtai();
                // gongrenmiaoshu();
                gongdanhuoban();
                // querenqianzi();
                // weixiupingjia();
                /*
				 * if (data.type.equals("0")) { selectTypeCus(); } else {
				 * selectTypeEQU(); }
				 */
            }

            @Override
            public void messageFailure(MessageBean backError) {
                System.out.println("");
            }

            @Override
            public void connectFailure(MessageBean messageBean) {
                // TODO Auto-generated method stub
                System.out.println("");
            }
        });
    }

    // 改动：返回值由原来的void改为OrderManagerWidget

    /**
     * @param bean
     * @param genre 字符串0-5 0 客户1 工人 2 组长3 客服4 经理5 老板
     * @param flag  1为serviceBean 2为DataBean
     * @return
     */
    public OrderManagerWidget setData(DataBean bean, String genre, int flag) {
        removeAllViews();
        data = bean;
        this.flag = flag;
        this.genre = genre;
        status = data.status;
        equip = data.equipment;
        if (TextUtils.isEmpty(data.type)) {
            selectTypeCus();
            return this;
        }
        if (data.type.equals("0")) {
            selectTypeCus();
        } else {
            selectTypeEQU();
        }
        return this;
    }

    /**
     * FindByEquipmentNoBean 设备的
     *
     * @param equmentBean
     * @param orderStateActivity
     * @param loginBean
     */
    public OrderManagerWidget setEqumentData(FindByEquipmentNoBean equmentBean, OrderStateActivity orderStateActivity, LoginBean loginBean) {
        removeAllViews();
        this.equmentBean = equmentBean;
        this.loginBean = loginBean;
        selectEqument();
        return this;
    }

    /**
     * 资产管理
     *
     * @param assetBean
     * @param orderStateActivity
     * @param loginBean
     */
    public View setAssetData(FindByAssetNoBean.RecordsBean assetBean, OrderStateActivity orderStateActivity, LoginBean loginBean) {
        removeAllViews();
        this.assetBean = assetBean;
        this.loginBean = loginBean;
        selectAsset();
        return this;
    }

    private void selectAsset() {
        ajibenxinxi();
        akebiangengxinxi();
        azichanpiangeng();
    }

    private void azichanpiangeng() {
        if (assetView !=null){
            addView(assetView);
        }

    }

    private void akebiangengxinxi() {
        textW2.setTitle("可变更信息");
        textW2.setText(0, "盘亏结果：" + assetBean.getInventoryResultsDisplay());
        textW2.setText(1, "实物资产描述：" + assetBean.getPhysicalAssetDes());
        textW2.setText(2, "实物资产状态：" + assetBean.getPhysicalAssetStatusDisplay());
        textW2.setText(3, "责任人：" + assetBean.getResponsibleName());
        textW2.setText(4, "资产用途：" + assetBean.getUserEquiAssetsUsed());
        textW2.setText(5, "地点描述：" + assetBean.getStatedLocality());
        textW2.setText(6, "单价：" + assetBean.getUnitPrice());
        textW2.setText(7, "备注：" + assetBean.getDescription());
        addView(textW2);
    }

    private void ajibenxinxi() {
        textW1.setTitle("基本信息");
        textW1.setText(0, "实物资产标签号：" + assetBean.getPhysicalAssetLabeNum());
        textW1.setText(1, "FMIS卡片号：" + assetBean.getFmisCardNo());
        textW1.setText(2, "空白标签号：" + assetBean.getBlankLabel());
        textW1.setText(3, "序列号：" + assetBean.getSerialNo());
        textW1.setText(4, "实物资产类别ID：" + assetBean.getUserPhysicalAsset().getPhysicalAssetId());
        textW1.setText(5, "规格型号：" + assetBean.getModelNo());
        textW1.setText(6, "购置日期：" + assetBean.getPurchaseDate());
        textW1.setText(7, "专业属性：" + assetBean.getSpecialtyAttr());
        textW1.setText(8, "所属机构编号：" + assetBean.getUserEquiOrganizationId());
        textW1.setText(9, "所属机构名称：" + assetBean.getUserEquiOrganizationName());
        textW1.setText(10, "所在机构编号：" + assetBean.getUserEquiEmployerId());
        textW1.setText(11, "所在机构名称：" + assetBean.getUserEquiEmployerName());
        addView(textW1);
    }

    private void selectEqument() {
        ejibenxingxi();
        eshebeicanshu();
        ejianxiuxinxi();
        echangjiajijingxiaoshang();
        ebiaozhunweihuliucheng();
        ejianxiulishijilu();
        eshebeibaojingjilu();
        //eweixiuwancheng();
    }


    private void selectTypeCus() {
        if (flag == 1) {// ServiceBean 的状态
            if (status.equals("0")) {
                ssjibenxinxi();
                kehumiaoshu();
                ssgongdanzhuangtai();
            }
            if (status.equals("1")) {
                ssjibenxinxi();
                kehumiaoshu();
                ssgongdanzhuangtai();
            }
            if (status.equals("2")) {
                ssjibenxinxi();
                kehumiaoshu();
                ssgongdanzhuangtai();
            }
            if (status.equals("3")) {
                ssjibenxinxi();
                kehumiaoshu();
                ssgongdanzhuangtai();
                //sjianxiulishijilu();
            }
        } else {// databean 的状态
            if (status.equals("created")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
            }
            if (status.equals("assign-group")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
            }
            if (status.equals("assign-staff")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
            }
            if (status.equals("vied")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban();
            }
            if (status.equals("accepted")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban();
            }
            if (status.equals("rejected")) {
                jibenxinxi();
                kehumiaoshu();
                gongrenmiaoshu();
                gongdanzhuangtai();
            }
            if (status.equals("arrived")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban();
            }
            if (status.equals("wait_approve")) {
                jibenxinxi();
                kehumiaoshu();
                beijianshenqng();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban();
            }
            if (status.equals("approved")) {
                jibenxinxi();
                kehumiaoshu();
                beijianshenqng();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban();
            }
            if (status.equals("fixed")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban1();
                querenqianzi();
            }
            if (status.equals("checkd")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban1();
                querenqianzi();
            }
            if (status.equals("done")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
                gongrenmiaoshu();
                gongdanhuoban1();
                querenqianzi();
                weixiupingjia();
            }
            if (status.equals("closed")) {
                jibenxinxi();
                kehumiaoshu();
                gongdanzhuangtai();
            }
        }
    }

    private void beijianshenqng() {
        View inflate = View.inflate(mContext, R.layout.order_item_shop, null);
        RecyclerView recyclerView =  inflate.findViewById(R.id.rv_order_item_shop);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        OrderShopAdapter orderShopAdapter = new OrderShopAdapter(mContext,data.goodsCaseDetailsVos);
        recyclerView.setAdapter(orderShopAdapter);
        addView(inflate);




    }

    private void ssjibenxinxi() {
        textW2.setTitle("基本信息");
		/*textW2.setText(0, "报修时间：" + data.requestTime);
		textW2.setText(1, "报修时间：" + data.requestTime);
		textW2.setText(2, "客户名称：" + data.customerName);
		textW2.setText(3, "客户电话：" + data.customerPhone);
		textW2.setText(4, "故障地点：" + data.address);
		textW2.setText(5, "期望完成时间：" + data.expectedFixTime);
		textW2.setText(6, "状态：" + data.statusDisplay);*/
        textW2.setText(0, "报修时间：" + data.requestTime);
        textW2.setText(1, "客户名称：" + data.customerName);
        textW2.setText(2, "客户电话：" + data.customerPhone);
        textW2.setText(3, "故障地点：" + data.address);
        textW2.setText(4, "工单所属楼宇：" + data.building);
        textW2.setText(5, "期望到场时间：" + data.expectedFixTime);
        textW2.setText(6, "状态：" + data.statusDisplay);
        addView(textW2);

    }


    private void selectTypeEQU() {
        sjibenxinxi();
        sjianxiuxinxi();
        schangjiajijingxiaoshang();
        sbiaozhunweihuliuchen();
        //sjianxiulishijilu();
        sgongdanzhuangtai();
        sgongrenmiaoshu();
        gongdanhuoban();
        querenqianzi();
        //weixiupingjia();
    }

    // ------------客户订单----------
    private void initView(Context context) {
        textW1 = new MessageWidget(context);
        textW2 = new MessageWidget(context);
        textW3 = new MessageWidget(context);
        textW4 = new MessageWidget(context);
        textW5 = new MessageWidget(context);
        textW6 = new MessageWidget(context);
        textW7 = new MessageWidget(context);
        picW1 = new MessagePicWidget(context);
        picW2 = new MessagePicWidget(context);
        picW3 = new MessagePicWidget(context);
        picW4 = new MessagePicWidget(context);
        findNotice = new MessagePicWidget(context);
        process = new MessageProcessWidget(context);
        process2 = new MessageProcessWidget(context);

        chooseWorkerWidget = new ChooseWorkerWidget(context);
        pingJia = new PingJiaWidget(context);
    }

    private void gongdanhuoban() {
//		if (genre.equals("2")) {
        gongdanhuoban2();
//		} else {
//			gongdanhuoban1();
//		}
    }

    // 工单伙伴
    private void gongdanhuoban1() {
        if (flag == 1)
            return;
        List<PartnersBean> partners = data.partners;
		/*
		 * if (partners.isEmpty()) return;
		 */
        textW6.setTitle("工单伙伴");
        for (int i = 0; i < partners.size(); i++) {
            textW6.setText(i, "姓名：" + partners.get(i).name);
        }
        addView(textW6);
    }

    // 工单伙伴 选择版
    private void gongdanhuoban2() {
        if (flag == 1)
            return;
        List<PartnersBean> partners = data.partners;

        textW6.setTitle("工单伙伴");
        for (int i = 0; i < partners.size(); i++) {
            chooseWorkerWidget.setName(partners.get(i).name);
        }
        addView(chooseWorkerWidget);
    }

    // 工单描述
    private void gongdanmiaoshu() {
        String repairCaseCode = data.repairCaseCode;
        String customerName = data.customerName;
        String address = data.address;
        if (isEmpty(repairCaseCode, customerName, address))
            return;
        textW1.setTitle("工单描述");
        textW1.setText(0, "工单编号:" + repairCaseCode);
        textW1.setText(1, "客户名称:" + customerName);
        textW1.setText(2, "障碍位置:" + address);
        addView(textW1);
    }

    // 基本信息
    private void jibenxinxi() {
        textW2.setTitle("基本信息");
        textW2.setText(0, "优先级：" + data.priorityDisplay);
        textW2.setText(1, "客户名称：" + data.customerName);
        textW2.setText(2, "客户电话：" + data.customerPhone);
        textW2.setText(3, "故障地点：" + data.address);
        textW2.setText(4, "工单所属楼宇：" + data.building);
        if (1 == Integer.parseInt(data.caseArea)) {
            textW2.setText(5, "工单区域：" + "公共");
        } else if (0 == Integer.parseInt(data.caseArea)) {
            textW2.setText(5, "工单区域：" + "客户");
        }
        if (0 == Integer.parseInt(data.caseMoneyType)) {
            textW2.setText(6, "工单类型：" + "有偿");
        } else {
            textW2.setText(6, "工单类型：" + "无偿");
        }
        textW2.setText(7, "状态：" + data.statusDisplay);
        textW2.setText(8, "执行工人：" + data.fixStaffName);

        if (0 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "工单组：" + "强电");
        } else if (1 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "工单组：" + "弱电");
        } else if (2 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "工单组：" + "暖通");
        } else if (3 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "工单组：" + "综修");
        }
        textW2.setText(10, "报修时间：" + data.requestTime);
        textW2.setText(11, "期望到场时间：" + data.expectedFixTime);
        textW2.setText(12, "维修费用：" + data.fixMoney);
        textW2.setText(13, "描述：" + data.contentAndNote);
        if (TextUtils.isEmpty(data.forwarReason)) {
            textW2.setText(14, "转发理由：" + "");
        } else {
            textW2.setText(14, "转发理由：" + data.forwarReason);
        }
        if (TextUtils.isEmpty(data.rejectReason)) {
            textW2.setText(15, "拒绝理由：" + "");
        } else {
            textW2.setText(15, "拒绝理由：" + data.rejectReason);
        }
        addView(textW2);
    }

    // 客户描述
    private void kehumiaoshu() {
        String requestTime = data.requestTime;
        String description = data.description;
        List<ServicePictures> servicePictures = data.servicePictures;
        if (isEmpty(requestTime, description) && servicePictures.isEmpty())
            return;
        picW1.setTitle("客户描述");
        picW1.setText(0, "日期：" + requestTime);
        picW1.setText(1, "维修内容：" + description);
        picW1.setText(2, "现场状况:");
        if (servicePictures.size() == 0) {
            picW1.setImageVisibleGone(0);
        } else {
            for (int i = 0; i < 10 && i < servicePictures.size(); i++) {
                picW1.setImageVisible(i);
                picW1.setImage(i, NetContacts.getInstance().BASE_IMAGE_URL
                        + "/" + servicePictures.get(i).pictureUrl);
            }
        }
        addView(picW1);
    }

    // 工单状态
    private void ssgongdanzhuangtai() {
        List<Traces> traces = data.traces;
        if (traces.isEmpty())
            return;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Traces trace : traces) {
            sb.append(trace.actionDisplay);
            sb.append(": ");
            sb.append(trace.staffName);
            sb.append(" ");
            sb.append(trace.executeTime);
				/*sb.append(b)*/
            process.setTitle("检修历史记录");
            process.setText(i, sb.toString());
            i++;
            sb.delete(0, sb.length());
        }
        addView(process);
    }

    // 工单状态
    private void gongdanzhuangtai() {
        List<Traces> traces = data.traces;
        if (traces.isEmpty())
            return;
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Traces trace : traces) {
            sb.append(trace.actionDisplay);
            sb.append(": ");
            sb.append(trace.staffName);
            sb.append(" ");
            sb.append(trace.executeTime);
            process.setText(i, sb.toString());
            i++;
            sb.delete(0, sb.length());
        }
        addView(process);
    }

    // 经理描述
    private void jinglimiaoshu() {
        String expectedFixTime = data.expectedFixTime;
        if (isEmpty(expectedFixTime))
            return;
        picW2.setTitle("经理描述");
        picW2.setText(0, "期望时间：" + expectedFixTime);
        addView(picW2);
    }

    // 工人描述 利强写的
    private void gongrenmiaoshu() {

		/*
		 * String fixStaffName = data.fixStaffName; String fixStaffPhone =
		 * data.fixStaffPhone; List<Pictures> pictures = data.pictures; if
		 * (isEmpty(fixStaffName, fixStaffPhone) && pictures.isEmpty()) return;
		 * picW3.setTitle("工人描述"); picW3.setText(0, "修理工人姓名:" + fixStaffName);
		 * picW3.setText(1, "修理工人电话:" + fixStaffPhone); picW3.setText(2,
		 * "操作描述：");
		 * 
		 * for (int i = 0; i < 3 && i < pictures.size(); i++) { String url =
		 * NetContacts.getInstance().BASE_IMAGE_URL + pictures.get(i).pictureUrl; if
		 * (!TextUtils.isEmpty(url)) { picW3.setImage(i, url); Log.e("test",
		 * url); } }
		 */
        if (workerDesView != null) {
            addView(workerDesView);
        }
    }

    // 添加工人描述View
    private View workerDesView;
    private PingJiaWidget pingJia;

    //资产变更记录

    public OrderManagerWidget setWorkDesView(View workerDesView) {
        this.workerDesView = workerDesView;
        return this;
    }
    //资产变更记录
    private View assetView;
    public OrderManagerWidget setAssetUpdate(View assetView){
        this.assetView = assetView;
        return this;

    }

    // 确认签字
    private void querenqianzi() {
        if (flag == 1)
            return;
        String signatureUrl = data.signatureUrl;
        if (isEmpty(signatureUrl)) {
            return;
        }
        String url = NetContacts.getInstance().BASE_IMAGE_URL + "/" + data.signatureUrl;
        picW4.setTitle("签字栏");
        //picW4.setText(0, "费用："+data.fixMoney);
        picW4.setSrc(0, R.drawable.write_name);
        picW4.setImage(0, url);
        picW4.setScaleType(0, ScaleType.CENTER_CROP);
        addView(picW4);
    }

    // 维修评价
    private void weixiupingjia() {
        if (flag == 1)
            return;
        String evaluateRate = data.evaluateRate;
        String evaluateContent = data.evaluateContent;

		/*if (isEmpty(evaluateRate, evaluateContent)){
			return;
		}*/
        // textW3.setTitle("客户评价");
        // textW3.setText(0, "满意度评价：" + evaluateRateDisplay);
        // textW3.setText(1, "宝贵意见:" + evaluateContent);
        if (TextUtils.isEmpty(evaluateRate)) {
            pingJia.setStart(0, data.evaluateRateDisplay);
        } else {
            pingJia.setStart(Float.parseFloat(evaluateRate), data.evaluateRateDisplay);
        }
        pingJia.setSuggest(evaluateContent);
        addView(pingJia);
    }

    // ------------设备订单-----------
    // 基本信息
    private void sjibenxinxi() {
        Equipment equip = data.equipment;
        String equipmentNo = equip.equipmentNo;
        String name = equip.name;
        String releaseDate = equip.releaseDate;
        String useDate = equip.useDate;
        String location = equip.location;
        String serviceArea = equip.serviceArea;
        String category = equip.category;
		/*
		 * if (isEmpty(equipmentNo, name, releaseDate, useDate, location,
		 * serviceArea, category)) return;
		 */

        textW1.setTitle("基本信息");
        textW1.setText(0, "设备编号：" + equipmentNo);
        textW1.setText(1, "设备名称：" + name);
        textW1.setText(2, "设备类别：" + category);
        textW1.setText(3, "出场日期：" + releaseDate);
        textW1.setText(4, "使用日期：" + useDate);
        textW1.setText(5, "安装地点：" + location);
        textW1.setText(6, "服务区域：" + serviceArea);
        textW1.setText(7, "维修描述：" + data.description);
        addView(textW1);
    }

    // 检修信息
    private void sjianxiuxinxi() {
        String overhaulCycle = equip.overhaulCycle;
        String forwardMaintenanceDate = equip.forwardMaintenanceDate;
        String nextMaintenanceDate = equip.nextMaintenanceDate;
		/*
		 * if (isEmpty(overhaulCycle, forwardMaintenanceDate,
		 * nextMaintenanceDate)) return;
		 */
        textW2.setTitle("检修信息");
        textW2.setText(0, "维保类型:" + data.equipmentPmType);
        textW2.setText(1, "检修周期(天):" + data.period);
        textW2.setText(2, "上一检修日期:" + data.lastMaintainDate);
        textW2.setText(3, "下一检修日期:" + data.nextMaintainDate);
        textW2.setText(4, "描述:" + data.description);
        textW2.setText(5, "检修标准流程:" + data.maintenanceSop);
        addView(textW2);
    }

    // 厂家及经销商
    private void schangjiajijingxiaoshang() {
        String manufacturer = equip.manufacturer;
        String vendorName = equip.vendorName;
        String vendorContact = equip.vendorContact;
        String vendorPhone = equip.vendorPhone;
        String vendorAddress = equip.vendorAddress;
		/*
		 * if (isEmpty(manufacturer, vendorName, vendorContact, vendorPhone,
		 * vendorAddress)) return;
		 */
        textW3.setTitle("厂家及经销商");
        textW3.setText(0, "出厂商：" + manufacturer);
        textW3.setText(1, "经销商：" + vendorName);
        textW3.setText(2, "联系人：" + vendorContact);
        textW3.setText(3, "联系电话：" + vendorPhone);
        textW3.setText(4, "联系地址：" + vendorAddress);
        addView(textW3);
    }

    // 标准维护流程
    private void sbiaozhunweihuliuchen() {
        String maintenanceSop = data.equipment.maintenanceSop;
		/*
		 * if (isEmpty(maintenanceSop)) return;
		 */
        textW4.setTitle("标准维护流程");
        textW4.setText(0, maintenanceSop);
        addView(textW4);
    }

    // 检修历史记录
    private void sjianxiulishijilu() {
        List<Equipment.MaintainRecords> maintainRecords = equip.maintainRecords;
		/*
		 * if (maintainRecords.isEmpty()) return;
		 */
        process2.setTitle("检修历史记录");
        for (int i = 0; i < maintainRecords.size() && i < 9; i++) {
            Equipment.MaintainRecords maintainRecords2 = maintainRecords.get(i);
			/*textW5.setText(i, "维护人员：" + maintainRecords2.fixStaffName
					+ "\n检修日期：" + maintainRecords2.maintainDate + "\n描述："
					+ maintainRecords2.description);*/
            process2.setText(i, maintainRecords2.fixStaffName + ":" + maintainRecords2.maintainDate);
        }
        addView(process2);
    }

    // 工单状态
    private void sgongdanzhuangtai() {
        List<Traces> traces = data.traces;
		/*
		 * if (traces.isEmpty()) return;
		 */
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Traces trace : traces) {
            sb.append(trace.actionDisplay);
            sb.append(": ");
            sb.append(trace.staffName);
            sb.append(" ");
            sb.append(trace.executeTime);
            process.setText(i, sb.toString());
            i++;
            sb.delete(0, sb.length());
        }
        addView(process);
    }

    // 工人描述
    private void sgongrenmiaoshu() {
        addView(workerDesView);
    }

    private boolean isEmpty(String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
        }
        return TextUtils.isEmpty(sb.toString());
    }

    public void addContentView(View view) {
        addView(view);
    }

    // --------------设备------------------------
    private void ejibenxingxi() {
        textW1.setTitle("基本信息");
        textW1.setText(0, "设备编号：" + equmentBean.equipmentNo);
        textW1.setText(1, "设备名称：" + equmentBean.name);
        textW1.setText(2, "设备类别：" + equmentBean.category);
        textW1.setText(3, "出厂日期：" + equmentBean.releaseDate);
        textW1.setText(4, "使用日期：" + equmentBean.useDate);
        textW1.setText(5, "安装地点：" + equmentBean.location);
        textW1.setText(6, "服务区域：" + equmentBean.serviceArea);
        addView(textW1);
    }

    private void eshebeicanshu() {
        textW2.setTitle("设备参数");
        textW2.setText(0, "型号：" + equmentBean.modelNo);
        textW2.setText(1, "系统编号：" + equmentBean.serialNo);
        textW2.setText(2, "技术参数：" + equmentBean.technicalParameters);
        addView(textW2);
    }

    private void ejianxiuxinxi() {
        textW3.setTitle("检修信息");
        String announcement;
        List<FindByEquipmentNoBean.EquipmentPm> equipmentPms = equmentBean.equipmentPmVos;

        for (int i = 0; i < equipmentPms.size(); i++) {
            FindByEquipmentNoBean.EquipmentPm equipmentPm = equipmentPms.get(i);
            if (TextUtils.isEmpty(equipmentPm.announcement)) {
                announcement = "";
            } else {
                announcement = equipmentPm.announcement;
            }
            textW3.setText(i, "保养类型:" + equipmentPm.pmName
                    + "\n设备负责人:" + equipmentPm.fixStaffName
                    + "\n检修周期:" + equipmentPm.period
                    + "\n上一次检修日期:" + equipmentPm.lastMaintainDate
                    + "\n下一次检修日期:" + equipmentPm.nextMaintainDate
                    + "\n特种作业:" + announcement
                    + "\n保养描述:" + equipmentPm.description
                    + "\n维修标准流程:" + equipmentPm.maintenanceSop
                    + "\n"
            );
        }
        addView(textW3);
    }

    private void echangjiajijingxiaoshang() {
        textW4.setTitle("厂家及经销商");
        textW4.setText(0, "出厂商：" + equmentBean.manufacturer);
        textW4.setText(1, "经销商：" + equmentBean.vendorName);
        textW4.setText(2, "联系人：" + equmentBean.vendorContact);
        textW4.setText(3, "联系电话：" + equmentBean.vendorPhone);
        textW4.setText(4, "联系地址：" + equmentBean.vendorAddress);
        addView(textW4);
    }

    private void ebiaozhunweihuliucheng() {
        textW5.setTitle("标准维护流程");
        textW5.setText(0, equmentBean.maintenanceSop);
        addView(textW5);
    }

    private void eshebeibaojingjilu() {
        if (equmentBean.alarmVos == null) {
            equmentBean.alarmVos = new ArrayList<AlarmsBean.Records>();
        }
        if (equmentBean.alarmVos.size() > 0) {
            textW7.setTitle(">>点击查看设备报警记录<<");
            textW7.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, OverhaulActivity.class);
                    intent.putExtra("maintainRecords", equmentBean);
                    intent.putExtra("LoginBean", loginBean);
                    mContext.startActivity(intent);
                }
            });
        } else {
            textW7.setTitle(">>暂无设备报警记录<<");
            textW7.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast("暂无设备报警");
                }
            });
        }
        addView(textW7);
    }

    private void ejianxiulishijilu() {

        if (equmentBean.maintainRecords.size() > 0) {
            textW6.setTitle(">>点击查看历史设备工单<<");
            textW6.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, OverhaulActivity.class);
                    intent.putExtra("maintainRecords", equmentBean);
                    intent.putExtra("LoginBean", loginBean);
                    mContext.startActivity(intent);
                }
            });
        } else {
            textW6.setTitle(">>暂无历史设备工单<<");
            textW6.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast("暂无历史设备工单");
                }
            });
        }
        addView(textW6);
    }

    public View setFindNoticeData(FindNoticeBean.RecordsBean bean, String type) {
        findNotice.setTitle(bean.getTitle());
        findNotice.setTitleSize(20);
        findNotice.setText(0, "     " + bean.getContent());
        if (bean.getPictureUrl().equals("")) {
            findNotice.setImageLoadGone(0);
        } else {
            findNotice.setImage(0, NetContacts.getInstance().BASE_IMAGE_URL + "/" + bean.getPictureUrl());
        }
        return findNotice;
    }


}
