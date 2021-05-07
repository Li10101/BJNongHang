package com.xyl.domain;

import com.xyl.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class LoginBean extends BaseBean implements Serializable{
	/**
	 * staffId : 7
	 * staffCode : zlglyangjinliang
	 * name : 杨金亮
	 * sex : 0
	 * sexDisplay : 男
	 * contact :
	 * phone : 15810106015
	 * email :
	 * password : E10ADC3949BA59ABBE56E057F20F883E
	 * address : 河南省林州市东岗镇杨家寨村
	 * photo : head.png
	 * level : 2
	 * levelDisplay : 主管
	 * type : 2
	 * typeDisplay : 组长
	 * buildingName : 展览馆路办公楼
	 * buildingId : 2
	 * description :
	 * teams : []
	 * zhiWeiType : 0
	 * zhiWeiTypeDisplay : 在职
	 * chuShengRQ :
	 * shenFenZH :
	 * huJiDZ :
	 * minZu : -1
	 * minZuDisplay : 其他
	 * wenHuaCD : -1
	 * wenHuaCDDisplay : 其他
	 * zhengZhiMM : -1
	 * zhengZhiMMDisplay : 其他
	 * zhengShenQK :
	 * canJiaGZSJ :
	 * ruZhiSJ :
	 * heTongDQSJ :
	 * zhiWu :
	 * zhuanYeZS :
	 * dangAnPictures : []
	 * dangAnDescriptions : []
	 */

	public String staffId;
	public String staffCode;
	public String name;
	public String sex;
	public String sexDisplay;
	public String contact;
	public String phone;
	public String email;
	public String password;
	public String address;
	public String photo;
	public String level;
	public String levelDisplay;
	public String type;
	public String typeDisplay;
	public String buildingName;
	public int buildingId;
	public String description;
	public int zhiWeiType;
	public String zhiWeiTypeDisplay;
	public String chuShengRQ;
	public String shenFenZH;
	public String huJiDZ;
	public int minZu;
	public String minZuDisplay;
	public int wenHuaCD;
	public String wenHuaCDDisplay;
	public int zhengZhiMM;
	public String zhengZhiMMDisplay;
	public String zhengShenQK;
	public String canJiaGZSJ;
	public String ruZhiSJ;
	public String heTongDQSJ;
	public String zhiWu;
	public String zhuanYeZS;
	public List<Teams> teams;
	public List<?> dangAnPictures;
	public List<?> dangAnDescriptions;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSexDisplay() {
		return sexDisplay;
	}

	public void setSexDisplay(String sexDisplay) {
		this.sexDisplay = sexDisplay;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelDisplay() {
		return levelDisplay;
	}

	public void setLevelDisplay(String levelDisplay) {
		this.levelDisplay = levelDisplay;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeDisplay() {
		return typeDisplay;
	}

	public void setTypeDisplay(String typeDisplay) {
		this.typeDisplay = typeDisplay;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getZhiWeiType() {
		return zhiWeiType;
	}

	public void setZhiWeiType(int zhiWeiType) {
		this.zhiWeiType = zhiWeiType;
	}

	public String getZhiWeiTypeDisplay() {
		return zhiWeiTypeDisplay;
	}

	public void setZhiWeiTypeDisplay(String zhiWeiTypeDisplay) {
		this.zhiWeiTypeDisplay = zhiWeiTypeDisplay;
	}

	public String getChuShengRQ() {
		return chuShengRQ;
	}

	public void setChuShengRQ(String chuShengRQ) {
		this.chuShengRQ = chuShengRQ;
	}

	public String getShenFenZH() {
		return shenFenZH;
	}

	public void setShenFenZH(String shenFenZH) {
		this.shenFenZH = shenFenZH;
	}

	public String getHuJiDZ() {
		return huJiDZ;
	}

	public void setHuJiDZ(String huJiDZ) {
		this.huJiDZ = huJiDZ;
	}

	public int getMinZu() {
		return minZu;
	}

	public void setMinZu(int minZu) {
		this.minZu = minZu;
	}

	public String getMinZuDisplay() {
		return minZuDisplay;
	}

	public void setMinZuDisplay(String minZuDisplay) {
		this.minZuDisplay = minZuDisplay;
	}

	public int getWenHuaCD() {
		return wenHuaCD;
	}

	public void setWenHuaCD(int wenHuaCD) {
		this.wenHuaCD = wenHuaCD;
	}

	public String getWenHuaCDDisplay() {
		return wenHuaCDDisplay;
	}

	public void setWenHuaCDDisplay(String wenHuaCDDisplay) {
		this.wenHuaCDDisplay = wenHuaCDDisplay;
	}

	public int getZhengZhiMM() {
		return zhengZhiMM;
	}

	public void setZhengZhiMM(int zhengZhiMM) {
		this.zhengZhiMM = zhengZhiMM;
	}

	public String getZhengZhiMMDisplay() {
		return zhengZhiMMDisplay;
	}

	public void setZhengZhiMMDisplay(String zhengZhiMMDisplay) {
		this.zhengZhiMMDisplay = zhengZhiMMDisplay;
	}

	public String getZhengShenQK() {
		return zhengShenQK;
	}

	public void setZhengShenQK(String zhengShenQK) {
		this.zhengShenQK = zhengShenQK;
	}

	public String getCanJiaGZSJ() {
		return canJiaGZSJ;
	}

	public void setCanJiaGZSJ(String canJiaGZSJ) {
		this.canJiaGZSJ = canJiaGZSJ;
	}

	public String getRuZhiSJ() {
		return ruZhiSJ;
	}

	public void setRuZhiSJ(String ruZhiSJ) {
		this.ruZhiSJ = ruZhiSJ;
	}

	public String getHeTongDQSJ() {
		return heTongDQSJ;
	}

	public void setHeTongDQSJ(String heTongDQSJ) {
		this.heTongDQSJ = heTongDQSJ;
	}

	public String getZhiWu() {
		return zhiWu;
	}

	public void setZhiWu(String zhiWu) {
		this.zhiWu = zhiWu;
	}

	public String getZhuanYeZS() {
		return zhuanYeZS;
	}

	public void setZhuanYeZS(String zhuanYeZS) {
		this.zhuanYeZS = zhuanYeZS;
	}

	public List<Teams> getTeams() {
		return teams;
	}

	public void setTeams(List<Teams> teams) {
		this.teams = teams;
	}

	public List<?> getDangAnPictures() {
		return dangAnPictures;
	}

	public void setDangAnPictures(List<?> dangAnPictures) {
		this.dangAnPictures = dangAnPictures;
	}

	public List<?> getDangAnDescriptions() {
		return dangAnDescriptions;
	}

	public void setDangAnDescriptions(List<?> dangAnDescriptions) {
		this.dangAnDescriptions = dangAnDescriptions;
	}
		public class Teams implements Serializable{

	}

//	public String staffCode;
//	public String staffId;
//	public String sex;
//	public String phone;
//	public String sexDisplay;
//	public String typeDisplay;
//	public String type;
//	public String contact;
//	public String photo;
//	public String email;
//	public String address;
//	public String description;
//	public String name;
//	public String password;
//	public List<Teams> teams;
//
//

//
//	@Override
//	public String toString() {
//		return "LoginBean [staffCode=" + staffCode + ", sex=" + sex
//				+ ", phone=" + phone + ", sexDisplay=" + sexDisplay
//				+ ", typeDisplay=" + typeDisplay + ", type=" + type
//				+ ", contact=" + contact + ", photo=" + photo + ", email="
//				+ email + ", address=" + address + ", description="
//				+ description + ", name=" + name + ", teams=" + teams + ",password=" + password + "]";
//	}





}