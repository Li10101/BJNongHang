package com.xyl.domain.personnel;

import java.io.Serializable;

public class RenShiPictures implements Serializable {

    /**
     * pictureId : 24
     * uploader : 李振平
     * type : 2
     * pictureUrl : 2019-09-25/633163839ae44ae0944f2d5fc5004995.png
     * description :
     * uploadTime : 2019-09-25 14:53:06
     * typeDisplay : 组长
     */

    private int pictureId;
    private String uploader;
    private String type;
    private String pictureUrl;
    private String description;
    private String uploadTime;
    private String typeDisplay;

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
    }
}
