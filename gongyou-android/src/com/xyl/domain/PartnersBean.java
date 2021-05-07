package com.xyl.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

public class PartnersBean implements Serializable {
    public String staffCode;
    public String name;

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
