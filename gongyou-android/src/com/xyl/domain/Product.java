package com.xyl.domain;

import java.io.Serializable;

/*****************************************************
 * author:      wz
 * email:       wangzhong0116@foxmail.com
 * version:     1.0
 * date:        2017/1/10 15:12
 * description:
 *****************************************************/

public class Product implements Serializable {


    /**
     * ID : 101
     * foodName : 巧克力
     * foodPrice : 22
     * salesCount : 101
     * imageUrl : 1
     */

    private int ID;
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int    productId) {
        this.productId = productId;
    }

    private String type;
    private String foodName;
    private double foodPrice;
    private double salesCount;
    private double thisCount;
    private String imageUrl;
    private int seleteId;
    private double count;
    private boolean  stockTaking;
    private String unit;


    /** 编号 */
    private String bianHao;
    /** 规格 */
    private String guiGe;
    /** 型号 */
    private String xingHao;
    /** 生产日期 */
    /** 所属库房 公司库 部门库 */
    private Integer kuFangType;
    /** 厂商 */
    private String changShang;
    /** 厂商地址 */
    private String changShangDZ;
    /** 厂商电话 */
    private String changShangDH;

    private String kuFangTypeDisplay;

    public String getKuFangTypeDisplay() {
        return kuFangTypeDisplay;
    }

    public void setKuFangTypeDisplay(String kuFangTypeDisplay) {
        this.kuFangTypeDisplay = kuFangTypeDisplay;
    }

    public String getBianHao() {
        return bianHao;
    }

    public void setBianHao(String bianHao) {
        this.bianHao = bianHao;
    }

    public String getGuiGe() {
        return guiGe;
    }

    public void setGuiGe(String guiGe) {
        this.guiGe = guiGe;
    }

    public String getXingHao() {
        return xingHao;
    }

    public void setXingHao(String xingHao) {
        this.xingHao = xingHao;
    }

    public Integer getKuFangType() {
        return kuFangType;
    }

    public void setKuFangType(Integer kuFangType) {
        this.kuFangType = kuFangType;
    }

    public String getChangShang() {
        return changShang;
    }

    public void setChangShang(String changShang) {
        this.changShang = changShang;
    }

    public String getChangShangDZ() {
        return changShangDZ;
    }

    public void setChangShangDZ(String changShangDZ) {
        this.changShangDZ = changShangDZ;
    }

    public String getChangShangDH() {
        return changShangDH;
    }

    public void setChangShangDH(String changShangDH) {
        this.changShangDH = changShangDH;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isStockTaking() {
        return stockTaking;
    }

    public void setStockTaking(boolean stockTaking) {
        this.stockTaking = stockTaking;
    }

    public double getThisCount() {
        return thisCount;
    }

    public void setThisCount(double thisCount) {
        this.thisCount = thisCount;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public int getSeleteId() {
        return seleteId;
    }

    public void setSeleteId(int seleteId) {
        this.seleteId = seleteId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public double getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(double salesCount) {
        this.salesCount = salesCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
