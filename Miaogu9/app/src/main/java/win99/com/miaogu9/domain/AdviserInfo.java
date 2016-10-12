package win99.com.miaogu9.domain;

import java.util.List;

/**
 * @author sanshu
 * @data 16/9/13 下午4:43
 * @ToDo ${TODO}   顶部广告条内容实体
 */

public class AdviserInfo<T> {


    private List<T> content;


    public List<T> getData() {
        return content;
    }

    public void setData(List<T> content) {
        this.content = content;
    }




}
