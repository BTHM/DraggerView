package win99.com.miaogu9.domain;

/**
 * Created by sanshu on 2016/7/30.
 */
public class ResultWrapper<T> {

    private int res;
    private T data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
