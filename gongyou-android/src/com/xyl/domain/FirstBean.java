package com.xyl.domain;

import java.io.Serializable;

public class FirstBean  {
    String name;
    int flag;

    public FirstBean(String name, int flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
