package com.xyl.domain.personnel;

import java.io.Serializable;
import java.util.List;

public class ApplyPeopleBean implements Serializable {

    /**
     * count : 74
     * records : [{"staffId":347,"staffCode":"cs006","name":"测试库管","sex":"0","phone":"","email":"","level":"7","type":"7","building":"农银大厦南楼","typeDisplay":"库管","sexDisplay":"男","levelDisplay":"库管"},{"staffId":287,"staffCode":"nl_xileri","name":"希乐日","sex":"1","phone":"85104499","email":"","level":"1","type":"3","building":"农银大厦南楼","typeDisplay":"客服","sexDisplay":"女","levelDisplay":"员工"},{"staffId":281,"staffCode":"nl_quwenchao","name":"曲文超","sex":"0","phone":"15750403597 17678038288","email":"","level":"0","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"客户"},{"staffId":280,"staffCode":"nl_sunwei","name":"孙伟","sex":"0","phone":"15144848659","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"员工"},{"staffId":278,"staffCode":"nl_wangjiangong","name":"王建宫","sex":"0","phone":"17635219176","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"员工"},{"staffId":277,"staffCode":"nl_chenmin","name":"陈敏","sex":"1","phone":"15147579151","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"女","levelDisplay":"员工"},{"staffId":276,"staffCode":"nl_weibao","name":"设备维护人","sex":"0","phone":"","email":"","level":"0","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"客户"},{"staffId":275,"staffCode":"nl_huangmiao","name":"黄淼","sex":"1","phone":"","email":"","level":"1","type":"3","building":"农银大厦南楼","typeDisplay":"客服","sexDisplay":"女","levelDisplay":"员工"},{"staffId":274,"staffCode":"nl_gaoshang","name":"高尚","sex":"0","phone":"","email":"","level":"1","type":"2","building":"农银大厦南楼","typeDisplay":"组长","sexDisplay":"男","levelDisplay":"员工"},{"staffId":271,"staffCode":"nl_mengguangli","name":"孟广利","sex":"0","phone":"18301209368","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"员工"},{"staffId":269,"staffCode":"nl_liangqingwu","name":"梁清武","sex":"0","phone":"13681425630","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"员工"},{"staffId":268,"staffCode":"nl_zhangshenglin","name":"张盛林","sex":"0","phone":"15011419411","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"员工"},{"staffId":265,"staffCode":"nl_fujingyuan","name":"傅静源","sex":"1","phone":"85104499","email":"","level":"1","type":"3","building":"农银大厦南楼","typeDisplay":"客服","sexDisplay":"女","levelDisplay":"员工"},{"staffId":260,"staffCode":"nl_zhaotao","name":"赵涛","sex":"0","phone":"13552194805","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"员工"},{"staffId":259,"staffCode":"nl_wangxiaofei","name":"王小飞","sex":"0","phone":"13269325166","email":"","level":"1","type":"1","building":"农银大厦南楼","typeDisplay":"工人","sexDisplay":"男","levelDisplay":"员工"},{"staffId":251,"staffCode":"nl_chengxiaojuan","name":"程晓娟","sex":"1","phone":"","email":"","level":"1","type":"3","building":"农银大厦南楼","typeDisplay":"客服","sexDisplay":"女","levelDisplay":"员工"},{"staffId":235,"staffCode":"nl_pengyajing","name":"nl_lizhenp","sex":"1","phone":"13520276524","email":"","level":"1","type":"2","building":"农银大厦南楼","typeDisplay":"组长","sexDisplay":"女","levelDisplay":"员工"},{"staffId":232,"staffCode":"nl_sunli","name":"孙丽","sex":"1","phone":"13811620185","email":"","level":"4","type":"4","building":"农银大厦南楼","typeDisplay":"物业经理","sexDisplay":"女","levelDisplay":"项目经理"},{"staffId":231,"staffCode":"dongwenhai","name":"董文海","sex":"0","phone":"","email":"","level":"6","type":"5","building":"农银大厦南楼","typeDisplay":"总经理","sexDisplay":"男","levelDisplay":"总经理"},{"staffId":230,"staffCode":"wenzhenwei","name":"文震威","sex":"0","phone":"","email":"","level":"6","type":"5","building":"农银大厦南楼","typeDisplay":"总经理","sexDisplay":"男","levelDisplay":"总经理"}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 2
     * totalPage : 4
     * startRecord : 1
     * endRecord : 20
     * error : false
     * voClass : com.etiansoft.estate.vo.StaffSimpleVo
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
    private List<RecordsBean> records;

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

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean implements Serializable {
        /**
         * staffId : 347
         * staffCode : cs006
         * name : 测试库管
         * sex : 0
         * phone :
         * email :
         * level : 7
         * type : 7
         * building : 农银大厦南楼
         * typeDisplay : 库管
         * sexDisplay : 男
         * levelDisplay : 库管
         */

        private int staffId;
        private String staffCode;
        private String name;
        private String sex;
        private String phone;
        private String email;
        private String level;
        private String type;
        private String building;
        private String typeDisplay;
        private String sexDisplay;
        private String levelDisplay;

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public String getStaffCode() {
            return staffCode;
        }

        public void setStaffCode(String staffCode) {
            this.staffCode = staffCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(String building) {
            this.building = building;
        }

        public String getTypeDisplay() {
            return typeDisplay;
        }

        public void setTypeDisplay(String typeDisplay) {
            this.typeDisplay = typeDisplay;
        }

        public String getSexDisplay() {
            return sexDisplay;
        }

        public void setSexDisplay(String sexDisplay) {
            this.sexDisplay = sexDisplay;
        }

        public String getLevelDisplay() {
            return levelDisplay;
        }

        public void setLevelDisplay(String levelDisplay) {
            this.levelDisplay = levelDisplay;
        }
    }
}
