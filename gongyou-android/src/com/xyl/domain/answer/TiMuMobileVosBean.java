package com.xyl.domain.answer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TiMuMobileVosBean implements Serializable {
    /**
     * id : 1
     * type : 0
     * typeDisplay : 0
     * stem : 据考古资料显示，始于距今204万年前的（ ）是中国远古遗存中目前所知最早的人类。
     * zhengQueDA : 157
     * answer : ["157"]
     * analysis :
     * metas : [{"daAnId":157,"name":"巫山人","jieGuo":0,"jieGuoDisplay":"正确"},{"daAnId":158,"name":"元谋人","jieGuo":1,"jieGuoDisplay":"错误"},{"daAnId":159,"name":"北京洞人","jieGuo":1,"jieGuoDisplay":"错误"},{"daAnId":160,"name":"蓝田人","jieGuo":1,"jieGuoDisplay":"错误"}]
     * myAnser :
     */

    private int id;
    private int type;
    private String typeDisplay;
    private String stem;
    private String zhengQueDA;
    private String analysis;
    private String myAnser;
    private List<String> answer;
    private ArrayList<MetasBean> metas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getZhengQueDA() {
        return zhengQueDA;
    }

    public void setZhengQueDA(String zhengQueDA) {
        this.zhengQueDA = zhengQueDA;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getMyAnser() {
        return myAnser;
    }

    public void setMyAnser(String myAnser) {
        this.myAnser = myAnser;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public ArrayList<MetasBean> getMetas() {
        return metas;
    }

    public void setMetas(ArrayList<MetasBean> metas) {
        this.metas = metas;
    }

    public static class MetasBean {
        /**
         * daAnId : 157
         * name : 巫山人
         * jieGuo : 0
         * jieGuoDisplay : 正确
         */

        private int daAnId;
        private String name;
        private int jieGuo;
        private String jieGuoDisplay;

        public int getDaAnId() {
            return daAnId;
        }

        public void setDaAnId(int daAnId) {
            this.daAnId = daAnId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getJieGuo() {
            return jieGuo;
        }

        public void setJieGuo(int jieGuo) {
            this.jieGuo = jieGuo;
        }

        public String getJieGuoDisplay() {
            return jieGuoDisplay;
        }

        public void setJieGuoDisplay(String jieGuoDisplay) {
            this.jieGuoDisplay = jieGuoDisplay;
        }
    }

}
