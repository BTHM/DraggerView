package win99.com.miaogu9.domain;

import java.util.List;

/**
 * @author sanshu
 * @data 16/9/18 下午2:02
 * @ToDo ${TODO}
 */

public class MessageEventToNews {
    private List<ModelHomeInfo> lists;

    public MessageEventToNews(List<ModelHomeInfo> lists) {
        this.lists = lists;
    }

    public List<ModelHomeInfo> getLists() {
        return lists;
    }
}
