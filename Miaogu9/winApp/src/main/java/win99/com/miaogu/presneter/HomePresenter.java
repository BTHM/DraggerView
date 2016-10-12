package win99.com.miaogu.presneter;

import android.util.Log;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import win99.com.miaogu.api.ApiManage;
import win99.com.miaogu.bean.ContentBean;
import win99.com.miaogu.bean.HomeInfo;
import win99.com.miaogu.presneter.interf.BasePresenter;
import win99.com.miaogu.presneter.interf.IHomePresenter;
import win99.com.miaogu.util.Constant;
import win99.com.miaogu.view.interf.IHomeView;


/**
 * @author sanshu
 * @data 16/10/8 下午4:10
 * @ToDo ${TODO}
 */

public class HomePresenter extends BasePresenter implements IHomePresenter {

    private IHomeView mIHomeView;
    public HomePresenter(IHomeView iBaseView) {
        this.mIHomeView=iBaseView;
    }

    @Override
    public void getHeaderData() {

        mIHomeView.showProgress();

        Subscription subscribe = ApiManage.getInstence()
                .getZhihuApiService()
                .getTitle(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeInfo<ContentBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mIHomeView.hideProgress();
                        mIHomeView.showErrPage();
                    }

                    @Override
                    public void onNext(HomeInfo<ContentBean> contentBeanHomeInfo) {
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < contentBeanHomeInfo.getData().size(); i++) {
                            String url = contentBeanHomeInfo.getData().get(0).getAttach().get(i).getAttr_url();
                            Log.d("tag", "url=" + url);
                            list.add(Constant.IMAGE_URL + url);
                        }
                        mIHomeView.setRollPager(list);
                    }
                });
        //加进CompositeSubscription 中管理
        addSubscription(subscribe);
    }

    @Override
    public void getVerticalData() {

    }

    @Override
    public void getHorizontalData() {

    }
}
