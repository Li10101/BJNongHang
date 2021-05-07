package com.xyl.greendao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/*****************************************************
 * author:       wz
 * email:        wangzhong0116@foxmail.com
 * version:      1.0
 * date:         2017/1/10 15:13
 * description:
 *****************************************************/
@Entity
public class Cart  {

    @Id
    private Long id;
    private String name;
    private int productId;
    private double saleCount;
    private double price;
    private String productType;
    private int businessId;
    private boolean isSelected;
    private String imageUrl;
    @Generated(hash = 2070340674)
    public Cart(Long id, String name, int productId, double saleCount, double price,
            String productType, int businessId, boolean isSelected,
            String imageUrl) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.saleCount = saleCount;
        this.price = price;
        this.productType = productType;
        this.businessId = businessId;
        this.isSelected = isSelected;
        this.imageUrl = imageUrl;
    }
    @Generated(hash = 1029823171)
    public Cart() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getProductId() {
        return this.productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public double getSaleCount() {
        return this.saleCount;
    }
    public void setSaleCount(double saleCount) {
        this.saleCount = saleCount;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getProductType() {
        return this.productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public int getBusinessId() {
        return this.businessId;
    }
    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }
    public boolean getIsSelected() {
        return this.isSelected;
    }
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
   
}
