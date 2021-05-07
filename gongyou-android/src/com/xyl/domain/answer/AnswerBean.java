package com.xyl.domain.answer;

import java.io.Serializable;
import java.util.List;

public class AnswerBean implements Serializable {

    /**
     * count : 1
     * records : [{"daTiId":4,"name":"每周答题卷第1期","dateTime":"2020-05-20","xudaTRS":207,"yiDaTRS":1,"weiDaTRS":206,"yiDaRYLBSearch":"[43:詹志勇]","weiDaRYLBSearch":"[李 杰],[5纪宏林],[6蒋茹月],[7杨金亮],[8陈文国],[12高松山],[1李 帅],[15乔金柱],[17宋 会],[18李学新],[22魏 然],[0朱小军],[],[7赵建迎],[155周浩岩],[156乔蝶],[157吕东芳],[158冯依然],[159周玉玉],[160杨蕾],[161常清],[162郭海],[168李国庆],[169刘丽],[170王英超],[171孙云飞],[17孙文杰],[17回运恒],[175王台振],[176李远],[177张万明],[178苗义],[179余宪锋],[180徐涛],[181郑罡],[18张立光],[18牛传岗],[185郭],[186邢献刚],[187丁国生],[188胡述新],[189王云龙],[190朱龙云],[191李永乐],[192孙小涛],[19闫超],[195尹刚],[197牛寅],[198侯星],[201宋代利],[202张德],[207张会民],[209孙庆彬],[21李海军],[217云霞],[218于学敏],[20文震威],[21董文海],[26张思雨],[21孔俊伟],[252高晴],[25董瑞兰],[260赵涛],[262宁书],[26王爱民],[266郭术宾],[267邴振虎],[268张盛林],[269梁清武],[276设备维护人],[278王建宫],[279凌路线],[280孙伟],[281曲文超],[286张军],[289杨伟],[290孟净雪],[291梁海隆],[292李春林],[29罗 干],[295王鹏鹏],[296旦增伦珠],[297屈维权],[298王进录],[299达娃次仁],[01次仁卓玛],[02旦 吉],[0次 珍],[0德吉曲珍],[05益西卓嘎],[06次央],[07旦增卓玛],[08刘琼],[09次旺琼达],[10央宗卓玛],[11包万军],[12吕涛],[1沈文斌],[1彭强],[15宋鹏飞],[16江永曲珍],[17德吉],[18次白珍],[19桑旦措姆],[20玛措],[21桑旦措姆],[22多布青],[2李强],[2黄建伟],[25杨秀英],[26扎西普尺],[27尼玛卓嘎],[28次旦],[29吴明东],[1扎西拉姆],[2白玛旺姆],[巴桑],[达瓦],[5廖朝艳],[6达娃],[7李玉英],[8平措卓玛],[9美忠],[0次珠拉姆],[1罗旦白姆],[2次仁德吉],[西罗],[拉姆],[5索朗],[6拉巴普赤],[9王丹],[51官正文],[52胡金平],[5徐姣],[55韩苏伟],[56杨小龙],[57马彩驰],[61宋玉峰],[62耿松],[6窦永波],[6毛秋虎],[65杨春],[68杨汉],[69次旺欧珠],[70巩玉生],[72段海霞],[7王军],[7王炳福],[75李振平],[76康乐],[77岳鸣涛],[78薄林],[79王汇英],[80梁思铭],[82马井龙],[8许晴艳],[8孙赫一],[85郭朏],[86郑莹],[87李美月],[88文果],[89胡士忠],[90李佳慧],[91周利萍],[61袁丽],[62张莹],[6冉利],[6巴桑卓嘎],[65扎西卓嘎],[66罗干],[67王飞],[68黄],[69梁崇俊],[70杨俊],[71其米多吉],[72巴桑],[7央金],[7次旦卓玛],[75曲尼卫色],[76桑珠卓玛],[77边旦],[78达瓦],[79扎西拉宗],[80卓玛],[81曲桑],[82达拉平措],[8贡桑卓玛],[8赵梦],[85程安平],[86曾如明],[87穷达],[88益西],[89吴贵仟],[90达 片],[91陈小丽],[92章敏],[9吴国富],[9郑玉玲],[95名玛普赤],[96韩春涛],[97阿旺次仁],[98尼夏],[99卓玛曲珍],[500扎西平措],[501普布],[502曲珍],[50索朗群达]","tiKuIds":"1,2,3,4,5,9,10,11,12,13","pingJunFen":10}]
     * pageSize : 20
     * pageIndex : 1
     * prePage : 1
     * nextPage : 1
     * totalPage : 1
     * startRecord : 1
     * endRecord : 1
     * error : false
     * voClass : com.etiansoft.estate.peixun.vo.DaTiTableVo
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

    public static class RecordsBean implements Serializable{
        /**
         * daTiId : 4
         * name : 每周答题卷第1期
         * dateTime : 2020-05-20
         * xudaTRS : 207
         * yiDaTRS : 1
         * weiDaTRS : 206
         * yiDaRYLBSearch : [43:詹志勇]
         * weiDaRYLBSearch : [李 杰],[5纪宏林],[6蒋茹月],[7杨金亮],[8陈文国],[12高松山],[1李 帅],[15乔金柱],[17宋 会],[18李学新],[22魏 然],[0朱小军],[],[7赵建迎],[155周浩岩],[156乔蝶],[157吕东芳],[158冯依然],[159周玉玉],[160杨蕾],[161常清],[162郭海],[168李国庆],[169刘丽],[170王英超],[171孙云飞],[17孙文杰],[17回运恒],[175王台振],[176李远],[177张万明],[178苗义],[179余宪锋],[180徐涛],[181郑罡],[18张立光],[18牛传岗],[185郭],[186邢献刚],[187丁国生],[188胡述新],[189王云龙],[190朱龙云],[191李永乐],[192孙小涛],[19闫超],[195尹刚],[197牛寅],[198侯星],[201宋代利],[202张德],[207张会民],[209孙庆彬],[21李海军],[217云霞],[218于学敏],[20文震威],[21董文海],[26张思雨],[21孔俊伟],[252高晴],[25董瑞兰],[260赵涛],[262宁书],[26王爱民],[266郭术宾],[267邴振虎],[268张盛林],[269梁清武],[276设备维护人],[278王建宫],[279凌路线],[280孙伟],[281曲文超],[286张军],[289杨伟],[290孟净雪],[291梁海隆],[292李春林],[29罗 干],[295王鹏鹏],[296旦增伦珠],[297屈维权],[298王进录],[299达娃次仁],[01次仁卓玛],[02旦 吉],[0次 珍],[0德吉曲珍],[05益西卓嘎],[06次央],[07旦增卓玛],[08刘琼],[09次旺琼达],[10央宗卓玛],[11包万军],[12吕涛],[1沈文斌],[1彭强],[15宋鹏飞],[16江永曲珍],[17德吉],[18次白珍],[19桑旦措姆],[20玛措],[21桑旦措姆],[22多布青],[2李强],[2黄建伟],[25杨秀英],[26扎西普尺],[27尼玛卓嘎],[28次旦],[29吴明东],[1扎西拉姆],[2白玛旺姆],[巴桑],[达瓦],[5廖朝艳],[6达娃],[7李玉英],[8平措卓玛],[9美忠],[0次珠拉姆],[1罗旦白姆],[2次仁德吉],[西罗],[拉姆],[5索朗],[6拉巴普赤],[9王丹],[51官正文],[52胡金平],[5徐姣],[55韩苏伟],[56杨小龙],[57马彩驰],[61宋玉峰],[62耿松],[6窦永波],[6毛秋虎],[65杨春],[68杨汉],[69次旺欧珠],[70巩玉生],[72段海霞],[7王军],[7王炳福],[75李振平],[76康乐],[77岳鸣涛],[78薄林],[79王汇英],[80梁思铭],[82马井龙],[8许晴艳],[8孙赫一],[85郭朏],[86郑莹],[87李美月],[88文果],[89胡士忠],[90李佳慧],[91周利萍],[61袁丽],[62张莹],[6冉利],[6巴桑卓嘎],[65扎西卓嘎],[66罗干],[67王飞],[68黄],[69梁崇俊],[70杨俊],[71其米多吉],[72巴桑],[7央金],[7次旦卓玛],[75曲尼卫色],[76桑珠卓玛],[77边旦],[78达瓦],[79扎西拉宗],[80卓玛],[81曲桑],[82达拉平措],[8贡桑卓玛],[8赵梦],[85程安平],[86曾如明],[87穷达],[88益西],[89吴贵仟],[90达 片],[91陈小丽],[92章敏],[9吴国富],[9郑玉玲],[95名玛普赤],[96韩春涛],[97阿旺次仁],[98尼夏],[99卓玛曲珍],[500扎西平措],[501普布],[502曲珍],[50索朗群达]
         * tiKuIds : 1,2,3,4,5,9,10,11,12,13
         * pingJunFen : 10
         */

        private int daTiId;
        private String name;
        private String dateTime;
        private int xudaTRS;
        private int yiDaTRS;
        private int weiDaTRS;
        private String yiDaRYLBSearch;
        private String weiDaRYLBSearch;
        private String tiKuIds;
        private int pingJunFen;

        public int getDaTiId() {
            return daTiId;
        }

        public void setDaTiId(int daTiId) {
            this.daTiId = daTiId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public int getXudaTRS() {
            return xudaTRS;
        }

        public void setXudaTRS(int xudaTRS) {
            this.xudaTRS = xudaTRS;
        }

        public int getYiDaTRS() {
            return yiDaTRS;
        }

        public void setYiDaTRS(int yiDaTRS) {
            this.yiDaTRS = yiDaTRS;
        }

        public int getWeiDaTRS() {
            return weiDaTRS;
        }

        public void setWeiDaTRS(int weiDaTRS) {
            this.weiDaTRS = weiDaTRS;
        }

        public String getYiDaRYLBSearch() {
            return yiDaRYLBSearch;
        }

        public void setYiDaRYLBSearch(String yiDaRYLBSearch) {
            this.yiDaRYLBSearch = yiDaRYLBSearch;
        }

        public String getWeiDaRYLBSearch() {
            return weiDaRYLBSearch;
        }

        public void setWeiDaRYLBSearch(String weiDaRYLBSearch) {
            this.weiDaRYLBSearch = weiDaRYLBSearch;
        }

        public String getTiKuIds() {
            return tiKuIds;
        }

        public void setTiKuIds(String tiKuIds) {
            this.tiKuIds = tiKuIds;
        }

        public int getPingJunFen() {
            return pingJunFen;
        }

        public void setPingJunFen(int pingJunFen) {
            this.pingJunFen = pingJunFen;
        }
    }
}
