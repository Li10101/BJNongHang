package com.xyl.domain;

/**
 * Created by 47500 on 2017/8/16.
 */

public class UpDataApkBean {


    /**
     * IosVersion : 1.2.0
     * AndroidVersion : 1.0.6
     * apkUrl : http://182.92.96.25:9999/download/gongyou.apk
     * description : 解决一些bug, 优化网络请求!
     */

    private String IosVersion;
    private String AndroidVersion;
    private String apkUrl;
    private String description;

    public String getIosVersion() {
        return IosVersion;
    }

    public void setIosVersion(String IosVersion) {
        this.IosVersion = IosVersion;
    }

    public String getAndroidVersion() {
        return AndroidVersion;
    }

    public void setAndroidVersion(String AndroidVersion) {
        this.AndroidVersion = AndroidVersion;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
