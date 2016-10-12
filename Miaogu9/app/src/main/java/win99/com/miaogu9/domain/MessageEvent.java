package win99.com.miaogu9.domain;

import java.util.List;

/**
 * @author sanshu
 * @data 16/9/18 下午2:02
 * @ToDo ${TODO}
 */

public class MessageEvent {
    private List<ModelHotAdviser> lists;

    public MessageEvent(List<ModelHotAdviser> lists) {
        this.lists = lists;
    }

    public List<ModelHotAdviser> getLists() {
        return lists;
    }
}
