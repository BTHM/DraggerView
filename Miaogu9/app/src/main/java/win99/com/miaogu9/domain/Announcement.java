package win99.com.miaogu9.domain;

import java.util.List;

/**
 * @author sanshu
 * @data 16/9/16 下午11:25
 * @ToDo ${TODO}  公告
 */

public class Announcement {

    /**
     * templateType : generic
     * createtime : 1472199570000
     * infoId : 10591
     * authorName : admin
     * outerUrl : http://mp.weixin.qq.com/s?__biz=MzAxMzc0Nzg1Nw==&mid=2649839024&idx=2&sn=a8b367c8f6a4507f53a5ca2912aafa74&scene=0#rd
     * effectiveTime : null
     * expiractionTime : null
     * share : http://m.miaogu8.com/app/share/video/10591
     * attach : []
     * title : 楼市限贷蔓延至一线城市
     * anlysis : [{"count_num":"0","detail":"","type":"FAVORITE"},{"count_num":"0","detail":"","type":"LIKE"},{"count_num":"0","detail":"","type":"PV"}]
     */

    private String templateType;
    private long    createtime;
    private int     infoId;
    private String  authorName;
    private String  outerUrl;
    private Object  effectiveTime;
    private Object  expiractionTime;
    private String  share;
    private String  title;
    private List<?> attach;
    /**
     * count_num : 0
     * detail :
     * type : FAVORITE
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

    public List<?> getAttach() {
        return attach;
    }

    public void setAttach(List<?> attach) {
        this.attach = attach;
    }

    public List<AnlysisBean> getAnlysis() {
        return anlysis;
    }

    public void setAnlysis(List<AnlysisBean> anlysis) {
        this.anlysis = anlysis;
    }

    public static class AnlysisBean {
        private String count_num;
        private String detail;
        private String type;

        public String getCount_num() {
            return count_num;
        }

        public void setCount_num(String count_num) {
            this.count_num = count_num;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
