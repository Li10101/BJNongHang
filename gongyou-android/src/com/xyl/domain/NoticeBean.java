package com.xyl.domain;

import java.io.Serializable;
import java.util.List;

public class NoticeBean implements Serializable {

    /**
     * count : 3
     * records : [{"cmsId":1,"biaoTi":"测试文章","type":2,"typeDisplay":"文章","status":1,"statusDisplay":"已发布","startTime":"2020-06-17","endTime":"2020-08-31","createTime":"2020-06-17","staffId":43,"staffName":"詹志勇","showBuildingId":2,"showBuildingName":"展览路物业项目","buildingId":5,"buildingName":"中心"},{"cmsId":2,"biaoTi":"测试文章","type":2,"typeDisplay":"文章","status":1,"statusDisplay":"已发布","startTime":"2020-06-17","endTime":"2020-08-31","createTime":"2020-06-17","staffId":43,"staffName":"詹志勇","showBuildingId":2,"showBuildingName":"展览路物业项目","buildingId":5,"buildingName":"中心"},{"cmsId":3,"biaoTi":"测试3","type":0,"typeDisplay":"通知公告","status":1,"statusDisplay":"已发布","startTime":"2020-06-17","endTime":"","createTime":"2020-06-17","staffId":43,"staffName":"詹志勇","showBuildingId":2,"showBuildingName":"展览路物业项目","buildingId":5,"buildingName":"中心"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 3
     * error : false
     * voClass : com.etiansoft.estate.cms.vo.CmsTabVo
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
    private List<NoticeDateBean> records;

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

    public List<NoticeDateBean> getRecords() {
        return records;
    }

    public void setRecords(List<NoticeDateBean> records) {
        this.records = records;
    }


}
