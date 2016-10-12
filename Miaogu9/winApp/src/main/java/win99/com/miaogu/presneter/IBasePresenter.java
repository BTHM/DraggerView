package win99.com.miaogu.presneter;


/**
 * 取消订阅 解决observer引用context 内存泄露问题
 */
public interface IBasePresenter {
    void unsubcrible();

}
