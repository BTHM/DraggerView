package win99.com.miaogu9.domain;

import java.util.List;

/**
 * @author sanshu
 * @data 16/9/19 下午4:05
 * @ToDo ${TODO}
 */

public class TvInfo {


    /**
     * templateType : video
     * createtime : 1473777483000
     * infoId : 10612
     * authorName : 刘军
     * outerUrl : http://2016sep-10033716.video.myqcloud.com/%E6%AF%8F%E6%97%A5%E8%A7%A3%E7%9B%98%EF%BC%8D%E5%88%98%E5%86%9B0913.mp4
     * effectiveTime : null
     * expiractionTime : null
     * share : http://m.miaogu8.com/app/share/video/10612
     * attach : [{"attr_createtime":1473782406000,"attr_name":"liujun_jp.jpg","attr_type":"attach","attr_url":"/attach/20160914000006293/liujun_jp.jpg","attr_sort":20160914000006292}]
     * title : 每日解盘－刘军0913
     * anlysis : [{"count_num":12,"type":"PV"},{"count_num":"0","detail":"","type":"FAVORITE"},{"count_num":"0","detail":"","type":"LIKE"}]
     */


    private String            templateType;
    private long              createtime;
    private int               infoId;
    private String            authorName;
    private String            outerUrl;
    private Object            effectiveTime;
    private Object            expiractionTime;
    private String            share;
    private String            title;
    /**
     * attr_createtime : 1473782406000
     * attr_name : liujun_jp.jpg
     * attr_type : attach
     * attr_url : /attach/20160914000006293/liujun_jp.jpg
     * attr_sort : 20160914000006292
     */

    private List<AttachBean>  attach;
    /**
     * count_num : 12
     * type : PV
     */

    private List<AnlysisBean> anlysis;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getOuterUrl() {
        return outerUrl;
    }

    public void setOuterUrl(String outerUrl) {
        this.outerUrl = outerUrl;
    }

    public Object getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Object effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Object getExpiractionTime() {
        return expiractionTime;
    }

    public void setExpiractionTime(Object expiractionTime) {
        this.expiractionTime = expiractionTime;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AttachBean> getAttach() {
        return attach;
    }

    public void setAttach(List<AttachBean> attach) {
        this.attach = attach;
    }

    public List<AnlysisBean> getAnlysis() {
        return anlysis;
    }

    public void setAnlysis(List<AnlysisBean> anlysis) {
        this.anlysis = anlysis;
    }

    public static class AttachBean {
        private long   attr_createtime;
        private String attr_name;
        private String attr_type;
        private String attr_url;
        private long   attr_sort;

        public long getAttr_createtime() {
            return attr_createtime;
        }

        public void setAttr_createtime(long attr_createtime) {
            this.attr_createtime = attr_createtime;
        }

        public String getAttr_name() {
            return attr_name;
        }

        public void setAttr_name(String attr_name) {
            this.attr_name = attr_name;
        }

        public String getAttr_type() {
            return attr_type;
        }

        public void setAttr_type(String attr_type) {
            this.attr_type = attr_type;
        }

        public String getAttr_url() {
            return attr_url;
        }

        public void setAttr_url(String attr_url) {
            this.attr_url = attr_url;
        }

        public long getAttr_sort() {
            return attr_sort;
        }

        public void setAttr_sort(long attr_sort) {
            this.attr_sort = attr_sort;
        }
    }

    public static class AnlysisBean {
        private int    count_num;
        private String    detail;//手动添加试一下
        private String type;

        public int getCount_num() {
            return count_num;
        }

        public void setCount_num(int count_num) {
            this.count_num = count_num;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

}
