package com.xyl.domain.answer;



import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yinzh
 * @Date 2019/8/13 14:18
 * @Description
 */
public class HomeworkQuestionBean  {

    private int id;
    public HomeworkQuestionTypeBean type; //题型
    private String stem; //题干
    private String answer; //正确答案
    private String analysis; //解析
    private ArrayList<String> metas; //选项
    private String myAnser;//选择答案



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public HomeworkQuestionTypeBean getType() {
        return type;
    }

    public void setType(HomeworkQuestionTypeBean type) {
        this.type = type;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public String getStem() {
        return this.stem;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMyAnser() {
        return myAnser;
    }

    public void setMyAnser(String myAnser) {
        this.myAnser = myAnser;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnalysis() {
        return this.analysis;
    }

    public void setMetas(ArrayList<String> metas) {
        this.metas = metas;
    }

    public ArrayList<String> getMetas() {
        return this.metas;
    }

//    public void setDifficulty(String difficulty) {
//        this.difficulty = difficulty;
//    }
//
//    public String getDifficulty() {
//        return this.difficulty;
//    }

//    @Override
//    public int getItemType() {
//        return itemType;
//    }



}
