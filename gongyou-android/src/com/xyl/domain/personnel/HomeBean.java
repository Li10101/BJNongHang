package com.xyl.domain.personnel;

import java.io.Serializable;

public class HomeBean implements Serializable {

    /**
     * mobilePermissionId : 03
     * parentId : 0
     * code : Paifagongdan
     * name : 派发工单
     * imgUrl : static/images/mobile/wodedan.png
     * type : 0
     * sort : 4
     * description : null
     */

    private String mobilePermissionId;
    private String parentId;
    private String code;
    private String name;
    private String imgUrl;
    private int type;
    private int sort;
    private String description;

    public String getMobilePermissionId() {
        return mobilePermissionId;
    }

    public void setMobilePermissionId(String mobilePermissionId) {
        this.mobilePermissionId = mobilePermissionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
