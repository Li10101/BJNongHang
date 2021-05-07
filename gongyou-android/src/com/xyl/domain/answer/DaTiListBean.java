package com.xyl.domain.answer;

import java.io.Serializable;
import java.util.List;

public class DaTiListBean implements Serializable {

    /**
     * count : 1
     * records : [{"daTiRenListId":3,"daTiId":4,"daTiName":"每周答题卷第1期","fenShu":70,"maxFenShu":70,"zhengQueSL":7,"cuoWuSL":3,"dateTime":"2020-05-21","staffId":231,"staffName":"董文海","buildingName":"中心办公室"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 1
     * error : false
     * voClass : com.etiansoft.estate.peixun.vo.DaTiRenListVo
     */

    private int count;
    private int pageSize;
    private int pageIndex;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int startRecord;
    private int endRecord;
    private boolean error;
    private String voClass;
    private List<DaTiRenDataBean> records;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getEndRecord() {
        return endRecord;
    }

    public void setEndRecord(int endRecord) {
        this.endRecord = endRecord;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getVoClass() {
        return voClass;
    }

    public void setVoClass(String voClass) {
        this.voClass = voClass;
    }

    public List<DaTiRenDataBean> getRecords() {
        return records;
    }

    public void setRecords(List<DaTiRenDataBean> records) {
        this.records = records;
    }


}
