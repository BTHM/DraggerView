package win99.com.miaogu.presneter.interf;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import win99.com.miaogu.presneter.IBasePresenter;

/**
 * @author sanshu
 * @data 2016/10/10 上午9:59
 * @ToDo ${TODO}
 */

public class BasePresenter implements IBasePresenter {

    private CompositeSubscription mCompositeSubscription;

    //创建一个CompositeSubscription 持有所有订阅,然后将 unsubcrible()防置在view组件的
    // ondestroy()中
    protected void addSubscription(Subscription s) {
       /* 一旦你调用了 CompositeSubscription.unsubscribe()，
        这个CompositeSubscription对象就不可用了,
        如果你还想使用CompositeSubscription，就必须在创建一个新的对象了*/
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void unsubcrible() {

        // TODO: 16/8/17 find when unsubcrible
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
}
