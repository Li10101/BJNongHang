package com.xyl.domain;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class DataBean implements Serializable {
	
	public String requestTime;
	public String evaluateRateDisplay;
	public String fixStaffCode;
	public String customerPhone;
	public String type;
	public String fixStaffPhone;
	public int pushTag;
	public Double fixMoney;
	public String priority;
	public String description;
	public String customerCode; 
	public String signatureUrl;
	public String repairCaseCode;
	public String customerName;
	public String evaluateContent;
	public String status;
	public String typeDisplay;
	public String priorityDisplay;
	public String statusDisplay;
	public String fixStaffName;
	public String address;
	public String expectedFixTime;
	public String evaluateRate;
	public String caseMoneyType;
	public String caseProfession;
	public String caseArea;
	public String contentAndNote;
	public Bitmap bitmap;//
	public String forwarReason;
	public String rejectReason;
	public String equipmentPmType;
	public String lastMaintainDate;
	public String nextMaintainDate;
	public String maintenanceSop;
	public int period;
    public String building;
	public Equipment equipment;
	public List<Pictures> getPictures() {
		return pictures;
	}

	public void setPictures(List<Pictures> pictures) {
		this.pictures = pictures;
	}

	public List<Pictures> pictures;

	public List<Traces> traces;

	public List<GoodsCaseDetailsBean> goodsCaseDetailsVos;

	public List<PartnersBean> partners;

	public List<ServicePictures> servicePictures;


	@Override
	public String toString() {
		String s = "";
		Field[] arr = this.getClass().getFields();
		for (Field f : getClass().getFields()) {
			try {
				s += f.getName() + "=" + f.get(this) + "\n,";
			} catch (Exception e) {
			}
		}
		return getClass().getSimpleName() + "["
				+ (arr.length == 0 ? s : s.substring(0, s.length() - 1)) + "]";
	}
}