package com.xyl.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

public class ServicePictures implements Serializable {
    public String pictureUrl;
    public String typeDisplay;
    public String uploader;
    public String uploadTime;
    public String type;

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
                + (arr.length == 0 ? s : s.substring(0, s.length() - 1))
                + "]";
    }
}
