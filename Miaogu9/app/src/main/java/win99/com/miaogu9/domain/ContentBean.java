package win99.com.miaogu9.domain;

import java.util.List;

/**
 * @author sanshu
 * @data 16/9/16 下午10:40
 * @ToDo ${TODO}
 */
public class ContentBean {
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
     * attr_createtime : 1472198134000
     * attr_name : ideaPKbanner.png
     * attr_type : attach
     * attr_url : /attach/20160826155531059/ideaPKbanner.png
     * attr_sort : 20160826155438
     */

    private List<AttachBean>  attach;
    /**
     * count_num : 6
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
    }
}

