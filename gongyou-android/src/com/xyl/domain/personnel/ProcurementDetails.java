package com.xyl.domain.personnel;

import java.io.Serializable;

public class ProcurementDetails implements Serializable {

    private String name = "";
    private String guiGe = "";
    private String money = "";
    private String number = "";
    private String danWei = "";
    private String zongJia = "";
    private String yongTu = "";


    public String getDanWei() {
        return danWei;
    }

    public void setDanWei(String danWei) {
        this.danWei = danWei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuiGe() {
        return guiGe;
    }

    public void setGuiGe(String guiGe) {
        this.guiGe = guiGe;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZongJia() {
        return zongJia;
    }

    public void setZongJia(String zongJia) {
        this.zongJia = zongJia;
    }

    public String getYongTu() {
        return yongTu;
    }

    public void setYongTu(String yongTu) {
        this.yongTu = yongTu;
    }
}
