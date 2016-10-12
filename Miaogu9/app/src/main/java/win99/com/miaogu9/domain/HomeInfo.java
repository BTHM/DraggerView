package win99.com.miaogu9.domain;

import java.util.List;

/**
 * @author sanshu
 * @data 16/9/13 下午4:43
 * @ToDo ${TODO}   顶部广告条内容实体
 */

public class HomeInfo<T> {

    /**
     * versionToken : e47385dc64a1b105774a247dec25ba52
     * content : [{"templateType":"generic","createtime":1472198134000,"infoId":10571,"authorName":"admin","outerUrl":"http://form.mikecrm.com/o4PdvP","effectiveTime":null,"expiractionTime":null,"share":"http://m.miaogu8.com/app/share/video/10571","attach":[{"attr_createtime":1472198134000,"attr_name":"ideaPKbanner.png","attr_type":"attach","attr_url":"/attach/20160826155531059/ideaPKbanner.png","attr_sort":20160826155438}],"title":"股神观点大碰撞","anlysis":[{"count_num":6,"type":"PV"},{"count_num":"0","detail":"","type":"FAVORITE"},{"count_num":"0","detail":"","type":"LIKE"}]}]
     */

    private String            versionToken;
    /**
     * templateType : generic
     * createtime : 1472198134000
     * infoId : 10571
     * authorName : admin
     * outerUrl : http://form.mikecrm.com/o4PdvP
     * effectiveTime : null
     * expiractionTime : null
     * share : http://m.miaogu8.com/app/share/video/10571
     * attach : [{"attr_createtime":1472198134000,"attr_name":"ideaPKbanner.png","attr_type":"attach","attr_url":"/attach/20160826155531059/ideaPKbanner.png","attr_sort":20160826155438}]
     * title : 股神观点大碰撞
     * anlysis : [{"count_num":6,"type":"PV"},{"count_num":"0","detail":"","type":"FAVORITE"},{"count_num":"0","detail":"","type":"LIKE"}]
     */

    private List<T> content;

    public String getVersionToken() {
        return versionToken;
    }

    public void setVersionToken(String versionToken) {
        this.versionToken = versionToken;
    }

    public List<T> getData() {
        return content;
    }

    public void setData(List<T> content) {
        this.content = content;
    }




}
