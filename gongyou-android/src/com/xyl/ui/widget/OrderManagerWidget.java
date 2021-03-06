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
        // login(); // ???????????? ??????
    }

    // ???????????? ??????
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

    // ???????????? ??????
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

    // ??????????????????????????????void??????OrderManagerWidget

    /**
     * @param bean
     * @param genre ?????????0-5 0 ??????1 ?????? 2 ??????3 ??????4 ??????5 ??????
     * @param flag  1???serviceBean 2???DataBean
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
     * FindByEquipmentNoBean ?????????
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
     * ????????????
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
        textW2.setTitle("???????????????");
        textW2.setText(0, "???????????????" + assetBean.getInventoryResultsDisplay());
        textW2.setText(1, "?????????????????????" + assetBean.getPhysicalAssetDes());
        textW2.setText(2, "?????????????????????" + assetBean.getPhysicalAssetStatusDisplay());
        textW2.setText(3, "????????????" + assetBean.getResponsibleName());
        textW2.setText(4, "???????????????" + assetBean.getUserEquiAssetsUsed());
        textW2.setText(5, "???????????????" + assetBean.getStatedLocality());
        textW2.setText(6, "?????????" + assetBean.getUnitPrice());
        textW2.setText(7, "?????????" + assetBean.getDescription());
        addView(textW2);
    }

    private void ajibenxinxi() {
        textW1.setTitle("????????????");
        textW1.setText(0, "????????????????????????" + assetBean.getPhysicalAssetLabeNum());
        textW1.setText(1, "FMIS????????????" + assetBean.getFmisCardNo());
        textW1.setText(2, "??????????????????" + assetBean.getBlankLabel());
        textW1.setText(3, "????????????" + assetBean.getSerialNo());
        textW1.setText(4, "??????????????????ID???" + assetBean.getUserPhysicalAsset().getPhysicalAssetId());
        textW1.setText(5, "???????????????" + assetBean.getModelNo());
        textW1.setText(6, "???????????????" + assetBean.getPurchaseDate());
        textW1.setText(7, "???????????????" + assetBean.getSpecialtyAttr());
        textW1.setText(8, "?????????????????????" + assetBean.getUserEquiOrganizationId());
        textW1.setText(9, "?????????????????????" + assetBean.getUserEquiOrganizationName());
        textW1.setText(10, "?????????????????????" + assetBean.getUserEquiEmployerId());
        textW1.setText(11, "?????????????????????" + assetBean.getUserEquiEmployerName());
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
        if (flag == 1) {// ServiceBean ?????????
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
        } else {// databean ?????????
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
        textW2.setTitle("????????????");
		/*textW2.setText(0, "???????????????" + data.requestTime);
		textW2.setText(1, "???????????????" + data.requestTime);
		textW2.setText(2, "???????????????" + data.customerName);
		textW2.setText(3, "???????????????" + data.customerPhone);
		textW2.setText(4, "???????????????" + data.address);
		textW2.setText(5, "?????????????????????" + data.expectedFixTime);
		textW2.setText(6, "?????????" + data.statusDisplay);*/
        textW2.setText(0, "???????????????" + data.requestTime);
        textW2.setText(1, "???????????????" + data.customerName);
        textW2.setText(2, "???????????????" + data.customerPhone);
        textW2.setText(3, "???????????????" + data.address);
        textW2.setText(4, "?????????????????????" + data.building);
        textW2.setText(5, "?????????????????????" + data.expectedFixTime);
        textW2.setText(6, "?????????" + data.statusDisplay);
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

    // ------------????????????----------
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

    // ????????????
    private void gongdanhuoban1() {
        if (flag == 1)
            return;
        List<PartnersBean> partners = data.partners;
		/*
		 * if (partners.isEmpty()) return;
		 */
        textW6.setTitle("????????????");
        for (int i = 0; i < partners.size(); i++) {
            textW6.setText(i, "?????????" + partners.get(i).name);
        }
        addView(textW6);
    }

    // ???????????? ?????????
    private void gongdanhuoban2() {
        if (flag == 1)
            return;
        List<PartnersBean> partners = data.partners;

        textW6.setTitle("????????????");
        for (int i = 0; i < partners.size(); i++) {
            chooseWorkerWidget.setName(partners.get(i).name);
        }
        addView(chooseWorkerWidget);
    }

    // ????????????
    private void gongdanmiaoshu() {
        String repairCaseCode = data.repairCaseCode;
        String customerName = data.customerName;
        String address = data.address;
        if (isEmpty(repairCaseCode, customerName, address))
            return;
        textW1.setTitle("????????????");
        textW1.setText(0, "????????????:" + repairCaseCode);
        textW1.setText(1, "????????????:" + customerName);
        textW1.setText(2, "????????????:" + address);
        addView(textW1);
    }

    // ????????????
    private void jibenxinxi() {
        textW2.setTitle("????????????");
        textW2.setText(0, "????????????" + data.priorityDisplay);
        textW2.setText(1, "???????????????" + data.customerName);
        textW2.setText(2, "???????????????" + data.customerPhone);
        textW2.setText(3, "???????????????" + data.address);
        textW2.setText(4, "?????????????????????" + data.building);
        if (1 == Integer.parseInt(data.caseArea)) {
            textW2.setText(5, "???????????????" + "??????");
        } else if (0 == Integer.parseInt(data.caseArea)) {
            textW2.setText(5, "???????????????" + "??????");
        }
        if (0 == Integer.parseInt(data.caseMoneyType)) {
            textW2.setText(6, "???????????????" + "??????");
        } else {
            textW2.setText(6, "???????????????" + "??????");
        }
        textW2.setText(7, "?????????" + data.statusDisplay);
        textW2.setText(8, "???????????????" + data.fixStaffName);

        if (0 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "????????????" + "??????");
        } else if (1 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "????????????" + "??????");
        } else if (2 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "????????????" + "??????");
        } else if (3 == Integer.parseInt(data.caseProfession)) {
            textW2.setText(9, "????????????" + "??????");
        }
        textW2.setText(10, "???????????????" + data.requestTime);
        textW2.setText(11, "?????????????????????" + data.expectedFixTime);
        textW2.setText(12, "???????????????" + data.fixMoney);
        textW2.setText(13, "?????????" + data.contentAndNote);
        if (TextUtils.isEmpty(data.forwarReason)) {
            textW2.setText(14, "???????????????" + "");
        } else {
            textW2.setText(14, "???????????????" + data.forwarReason);
        }
        if (TextUtils.isEmpty(data.rejectReason)) {
            textW2.setText(15, "???????????????" + "");
        } else {
            textW2.setText(15, "???????????????" + data.rejectReason);
        }
        addView(textW2);
    }

    // ????????????
    private void kehumiaoshu() {
        String requestTime = data.requestTime;
        String description = data.description;
        List<ServicePictures> servicePictures = data.servicePictures;
        if (isEmpty(requestTime, description) && servicePictures.isEmpty())
            return;
        picW1.setTitle("????????????");
        picW1.setText(0, "?????????" + requestTime);
        picW1.setText(1, "???????????????" + description);
        picW1.setText(2, "????????????:");
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

    // ????????????
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
            process.setTitle("??????????????????");
            process.setText(i, sb.toString());
            i++;
            sb.delete(0, sb.length());
        }
        addView(process);
    }

    // ????????????
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

    // ????????????
    private void jinglimiaoshu() {
        String expectedFixTime = data.expectedFixTime;
        if (isEmpty(expectedFixTime))
            return;
        picW2.setTitle("????????????");
        picW2.setText(0, "???????????????" + expectedFixTime);
        addView(picW2);
    }

    // ???????????? ????????????
    private void gongrenmiaoshu() {

		/*
		 * String fixStaffName = data.fixStaffName; String fixStaffPhone =
		 * data.fixStaffPhone; List<Pictures> pictures = data.pictures; if
		 * (isEmpty(fixStaffName, fixStaffPhone) && pictures.isEmpty()) return;
		 * picW3.setTitle("????????????"); picW3.setText(0, "??????????????????:" + fixStaffName);
		 * picW3.setText(1, "??????????????????:" + fixStaffPhone); picW3.setText(2,
		 * "???????????????");
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

    // ??????????????????View
    private View workerDesView;
    private PingJiaWidget pingJia;

    //??????????????????

    public OrderManagerWidget setWorkDesView(View workerDesView) {
        this.workerDesView = workerDesView;
        return this;
    }
    //??????????????????
    private View assetView;
    public OrderManagerWidget setAssetUpdate(View assetView){
        this.assetView = assetView;
        return this;

    }

    // ????????????
    private void querenqianzi() {
        if (flag == 1)
            return;
        String signatureUrl = data.signatureUrl;
        if (isEmpty(signatureUrl)) {
            return;
        }
        String url = NetContacts.getInstance().BASE_IMAGE_URL + "/" + data.signatureUrl;
        picW4.setTitle("?????????");
        //picW4.setText(0, "?????????"+data.fixMoney);
        picW4.setSrc(0, R.drawable.write_name);
        picW4.setImage(0, url);
        picW4.setScaleType(0, ScaleType.CENTER_CROP);
        addView(picW4);
    }

    // ????????????
    private void weixiupingjia() {
        if (flag == 1)
            return;
        String evaluateRate = data.evaluateRate;
        String evaluateContent = data.evaluateContent;

		/*if (isEmpty(evaluateRate, evaluateContent)){
			return;
		}*/
        // textW3.setTitle("????????????");
        // textW3.setText(0, "??????????????????" + evaluateRateDisplay);
        // textW3.setText(1, "????????????:" + evaluateContent);
        if (TextUtils.isEmpty(evaluateRate)) {
            pingJia.setStart(0, data.evaluateRateDisplay);
        } else {
            pingJia.setStart(Float.parseFloat(evaluateRate), data.evaluateRateDisplay);
        }
        pingJia.setSuggest(evaluateContent);
        addView(pingJia);
    }

    // ------------????????????-----------
    // ????????????
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

        textW1.setTitle("????????????");
        textW1.setText(0, "???????????????" + equipmentNo);
        textW1.setText(1, "???????????????" + name);
        textW1.setText(2, "???????????????" + category);
        textW1.setText(3, "???????????????" + releaseDate);
        textW1.setText(4, "???????????????" + useDate);
        textW1.setText(5, "???????????????" + location);
        textW1.setText(6, "???????????????" + serviceArea);
        textW1.setText(7, "???????????????" + data.description);
        addView(textW1);
    }

    // ????????????
    private void sjianxiuxinxi() {
        String overhaulCycle = equip.overhaulCycle;
        String forwardMaintenanceDate = equip.forwardMaintenanceDate;
        String nextMaintenanceDate = equip.nextMaintenanceDate;
		/*
		 * if (isEmpty(overhaulCycle, forwardMaintenanceDate,
		 * nextMaintenanceDate)) return;
		 */
        textW2.setTitle("????????????");
        textW2.setText(0, "????????????:" + data.equipmentPmType);
        textW2.setText(1, "????????????(???):" + data.period);
        textW2.setText(2, "??????????????????:" + data.lastMaintainDate);
        textW2.setText(3, "??????????????????:" + data.nextMaintainDate);
        textW2.setText(4, "??????:" + data.description);
        textW2.setText(5, "??????????????????:" + data.maintenanceSop);
        addView(textW2);
    }

    // ??????????????????
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
        textW3.setTitle("??????????????????");
        textW3.setText(0, "????????????" + manufacturer);
        textW3.setText(1, "????????????" + vendorName);
        textW3.setText(2, "????????????" + vendorContact);
        textW3.setText(3, "???????????????" + vendorPhone);
        textW3.setText(4, "???????????????" + vendorAddress);
        addView(textW3);
    }

    // ??????????????????
    private void sbiaozhunweihuliuchen() {
        String maintenanceSop = data.equipment.maintenanceSop;
		/*
		 * if (isEmpty(maintenanceSop)) return;
		 */
        textW4.setTitle("??????????????????");
        textW4.setText(0, maintenanceSop);
        addView(textW4);
    }

    // ??????????????????
    private void sjianxiulishijilu() {
        List<Equipment.MaintainRecords> maintainRecords = equip.maintainRecords;
		/*
		 * if (maintainRecords.isEmpty()) return;
		 */
        process2.setTitle("??????????????????");
        for (int i = 0; i < maintainRecords.size() && i < 9; i++) {
            Equipment.MaintainRecords maintainRecords2 = maintainRecords.get(i);
			/*textW5.setText(i, "???????????????" + maintainRecords2.fixStaffName
					+ "\n???????????????" + maintainRecords2.maintainDate + "\n?????????"
					+ maintainRecords2.description);*/
            process2.setText(i, maintainRecords2.fixStaffName + ":" + maintainRecords2.maintainDate);
        }
        addView(process2);
    }

    // ????????????
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

    // ????????????
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

    // --------------??????------------------------
    private void ejibenxingxi() {
        textW1.setTitle("????????????");
        textW1.setText(0, "???????????????" + equmentBean.equipmentNo);
        textW1.setText(1, "???????????????" + equmentBean.name);
        textW1.setText(2, "???????????????" + equmentBean.category);
        textW1.setText(3, "???????????????" + equmentBean.releaseDate);
        textW1.setText(4, "???????????????" + equmentBean.useDate);
        textW1.setText(5, "???????????????" + equmentBean.location);
        textW1.setText(6, "???????????????" + equmentBean.serviceArea);
        addView(textW1);
    }

    private void eshebeicanshu() {
        textW2.setTitle("????????????");
        textW2.setText(0, "?????????" + equmentBean.modelNo);
        textW2.setText(1, "???????????????" + equmentBean.serialNo);
        textW2.setText(2, "???????????????" + equmentBean.technicalParameters);
        addView(textW2);
    }

    private void ejianxiuxinxi() {
        textW3.setTitle("????????????");
        String announcement;
        List<FindByEquipmentNoBean.EquipmentPm> equipmentPms = equmentBean.equipmentPmVos;

        for (int i = 0; i < equipmentPms.size(); i++) {
            FindByEquipmentNoBean.EquipmentPm equipmentPm = equipmentPms.get(i);
            if (TextUtils.isEmpty(equipmentPm.announcement)) {
                announcement = "";
            } else {
                announcement = equipmentPm.announcement;
            }
            textW3.setText(i, "????????????:" + equipmentPm.pmName
                    + "\n???????????????:" + equipmentPm.fixStaffName
                    + "\n????????????:" + equipmentPm.period
                    + "\n?????????????????????:" + equipmentPm.lastMaintainDate
                    + "\n?????????????????????:" + equipmentPm.nextMaintainDate
                    + "\n????????????:" + announcement
                    + "\n????????????:" + equipmentPm.description
                    + "\n??????????????????:" + equipmentPm.maintenanceSop
                    + "\n"
            );
        }
        addView(textW3);
    }

    private void echangjiajijingxiaoshang() {
        textW4.setTitle("??????????????????");
        textW4.setText(0, "????????????" + equmentBean.manufacturer);
        textW4.setText(1, "????????????" + equmentBean.vendorName);
        textW4.setText(2, "????????????" + equmentBean.vendorContact);
        textW4.setText(3, "???????????????" + equmentBean.vendorPhone);
        textW4.setText(4, "???????????????" + equmentBean.vendorAddress);
        addView(textW4);
    }

    private void ebiaozhunweihuliucheng() {
        textW5.setTitle("??????????????????");
        textW5.setText(0, equmentBean.maintenanceSop);
        addView(textW5);
    }

    private void eshebeibaojingjilu() {
        if (equmentBean.alarmVos == null) {
            equmentBean.alarmVos = new ArrayList<AlarmsBean.Records>();
        }
        if (equmentBean.alarmVos.size() > 0) {
            textW7.setTitle(">>??????????????????????????????<<");
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
            textW7.setTitle(">>????????????????????????<<");
            textW7.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast("??????????????????");
                }
            });
        }
        addView(textW7);
    }

    private void ejianxiulishijilu() {

        if (equmentBean.maintainRecords.size() > 0) {
            textW6.setTitle(">>??????????????????????????????<<");
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
            textW6.setTitle(">>????????????????????????<<");
            textW6.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToast("????????????????????????");
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
