package com.xyl.domain.personnel;

import java.io.Serializable;

public class ReimburseDetail implements Serializable {
    private String leiBie = "";//申请事由
    private String  money = "";//金额
    private String feiYongMX = "";//明细

    public String getLeiBie() {
        return leiBie;
    }

    public void setLeiBie(String leiBie) {
        this.leiBie = leiBie;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFeiYongMX() {
        return feiYongMX;
    }

    public void setFeiYongMX(String feiYongMX) {
        this.feiYongMX = feiYongMX;
    }
}
