package win99.com.miaogu9.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observer;
import rx.schedulers.Schedulers;
import win99.com.miaogu9.R;
import win99.com.miaogu9.adapter.AdapterHomeInfo;
import win99.com.miaogu9.adapter.AdapterHotAdviser;
import win99.com.miaogu9.adapter.ListRecyclerAdapter;
import win99.com.miaogu9.base.BaseFragment;
import win99.com.miaogu9.domain.Announcement;
import win99.com.miaogu9.domain.ContentBean;
import win99.com.miaogu9.domain.HomeInfo;
import win99.com.miaogu9.domain.MessageEvent;
import win99.com.miaogu9.domain.MessageEventToNews;
import win99.com.miaogu9.domain.ModelHomeInfo;
import win99.com.miaogu9.domain.ModelHotAdviser;
import win99.com.miaogu9.library.VerticalBannerView;
import win99.com.miaogu9.net.NetWorkApi;
import win99.com.miaogu9.pull2refresh.PullToRefreshLayout;
import win99.com.miaogu9.rx.NetApi;
import win99.com.miaogu9.ui.NewsListActivity;
import win99.com.miaogu9.ui.WebActivity;
import win99.com.miaogu9.util.Constant;
import win99.com.miaogu9.util.LogUtil;
import win99.com.miaogu9.view.RollViewPager;
import win99.com.miaogu9.view.WrapRecyclerView;

import static win99.com.miaogu9.util.Constant.INTENT_DATA;


/**
 * @author sanshu
 * @data 2016/9/6 10:16
 * @ToDo ${TODO}
 */
public class FragmentHome extends BaseFragment {


    @Bind(R.id.state_tv)
    TextView         stateTv;
    @Bind(R.id.pull_icon)
    ImageView        pullIcon;
    @Bind(R.id.refreshing_icon)
    ImageView        refreshingIcon;
    @Bind(R.id.state_iv)
    ImageView        stateIv;
    @Bind(R.id.head_view)
    RelativeLayout   headView;
    @Bind(R.id.home_rollViewPager)
    RollViewPager    homeRollViewPager;
    @Bind(R.id.gushenshuo)
    TextView         gushenshuo;
    @Bind(R.id.home_button_tv)
    RelativeLayout   homeButtonTv;
    @Bind(R.id.quanshang)
    TextView         quanshang;
    @Bind(R.id.home_button_securities)
    RelativeLayout   homeButtonSecurities;
    @Bind(R.id.imageViewHot)
    ImageView        imageViewHot;
    @Bind(R.id.home_hot_title)
    RelativeLayout   homeHotTitle;
    @Bind(R.id.home_recyclerview)
    WrapRecyclerView homeRecyclerview; //横拉的广告条
    @Bind(R.id.imageViewFinanceIcon)
    ImageView        imageViewFinanceIcon;
    @Bind(R.id.home_finance_title)
    RelativeLayout   homeFinanceTitle;
    @Bind(R.id.imageViewFinance)
    ImageView        imageViewFinance;
    @Bind(R.id.layoutFinaceItem)
    RelativeLayout   layoutFinaceItem;
    @Bind(R.id.loadstate_tv)
    TextView         loadstateTv;
    @Bind(R.id.pullup_icon)
    ImageView        pullupIcon;
    @Bind(R.id.loading_icon)
    ImageView        loadingIcon;
    @Bind(R.id.loadstate_iv)
    ImageView        loadstateIv;
    @Bind(R.id.loadmore_view)
    RelativeLayout   loadmoreView;


    @Bind(R.id.imageButtonMoreInfo)
    ImageView           imageButtonMoreInfo;
    @Bind(R.id.banner_info)
    VerticalBannerView  bannerInfo;
    @Bind(R.id.refresh_view)
    PullToRefreshLayout refreshView;
    @Bind(R.id.error_page_iv)
    ImageView           errorPageIv;
    @Bind(R.id.error_btn_reload)
    Button              errorBtnReload;
    @Bind(R.id.error_page)
    FrameLayout         errorPage;


    private String TAG = FragmentHome.class.getName();

    private List<ModelHomeInfo> mLists;
    private ListRecyclerAdapter mAdapter;
    private String mobileId = "";//登陆用户必填
    public ArrayList<ModelHotAdviser> mModelHotAdvisers;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            //LogUtil.w(TAG, "mLoadingView.isShowing" + mLoadingView.isShowing());
            setSuccesView(errorPage, refreshView);
        }
    };
    private AdapterHomeInfo     mAdapterHomeInfo;
    private List<ContentBean>   mContentBeens;
    private List<ModelHomeInfo> mModelHomeInfos;

    @Override
    public int initLayout() {
        Toolbar toobar = mActivity.getToobar();
        toobar.setVisibility(View.GONE);
        return R.layout.fragment_home;
    }


    @Override
    public void setData() {
        // LogUtil.w(TAG, "mLoadingView.isShowing()=" + mLoadingView.isShowing());
        //设置顶部轮播图
       // setHeaderViewPager();

        setRxHeaderView();

        //垂直跑马灯
        setVerticalRollBanner();

        /***
         * 想到了实现滚动竖直广告条的实现使用 recyclerView,其适合对item添加动画,
         * 水平的recyclerView 要对其实现 右拉 加载更多
         */
        setHorzontalRecyclerView();


    }

    private void setRxHeaderView() {

       // Observable<HomeInfo<ContentBean>> observable =

        NetApi.sRxService.getTitle(2).subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<HomeInfo<ContentBean>>() {
            @Override
            public void onCompleted() {
                LogUtil.w(TAG, "contentBeanHomeInfo=over");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.w(TAG, "e=" + e);

            }

            @Override
            public void onNext(HomeInfo<ContentBean> contentBeanHomeInfo) {
                LogUtil.w(TAG, "contentBeanHomeInfo=" + contentBeanHomeInfo);
                LogUtil.w(TAG, "contentBeanHomeInfo=" + contentBeanHomeInfo.getData().size());

            }
        });
    }


    private void setHeaderViewPager() {
        NetWorkApi.getHomeTitle(2, new NetWorkApi.OnResultListener<HomeInfo<ContentBean>>() {
            @Override
            public void onSuccess(int code, HomeInfo<ContentBean> result) {
                LogUtil.w(TAG, "code=" + code);

                //同步方法
                List<String> lists = null;
                if (lists == null) {
                    lists = new ArrayList<String>();
                }
                mContentBeens = result.getData();
                for (int i = 0; i < mContentBeens.size(); i++) {
                    List<ContentBean.AttachBean> attachBeanList = mContentBeens.get(i).getAttach();
                    for (int j = 0; j < attachBeanList.size(); j++) {
                        String attr_url = attachBeanList.get(j).getAttr_url();
                        lists.add(Constant.IMAGE_URL + attr_url);
                    }
                }
                if (lists.size() > 0) {

                    homeRollViewPager.setImageLists(lists);
                    homeRollViewPager.start();//启动,开始加载图片
                }

            }

            @Override
            public void onFailure(Throwable t) {
                LogUtil.w(TAG, "t=" + t);
            }
        });


    }

    private void setVerticalRollBanner() {
        HashMap<String, String> map = new HashMap<>();

        map.put("mobileId", mobileId);//登陆用户必填

        map.put("labelId", 4 + "");
        NetWorkApi.getAnnconce(1, 10, map, new NetWorkApi.OnResultListener<HomeInfo<Announcement>>() {
            @Override
            public void onSuccess(int code, HomeInfo<Announcement> result) {
                List<Announcement> announcements = result.getData();
                if (mModelHomeInfos == null) {
                    mModelHomeInfos = new ArrayList<ModelHomeInfo>();
                }
                mModelHomeInfos.clear();
                for (int i = 0; i < announcements.size(); i++) {
                    String outerUrl = announcements.get(i).getOuterUrl();
                    String title = announcements.get(i).getTitle();
                    ModelHomeInfo modelHomeInfo = new ModelHomeInfo(title, outerUrl);
                    mModelHomeInfos.add(modelHomeInfo);
                }
                if (mAdapterHomeInfo == null) {
                    mAdapterHomeInfo = new AdapterHomeInfo(getActivity(), mModelHomeInfos);
                }
                bannerInfo.setAdapter(mAdapterHomeInfo);
                bannerInfo.start();
                sendEventBusToNewsDetail(mModelHomeInfos);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    //发送给新闻详情界面
    private void sendEventBusToNewsDetail(List<ModelHomeInfo> result) {
        EventBus.getDefault().postSticky(new MessageEventToNews(result));
    }

    private void setHorzontalRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        homeRecyclerview.setLayoutManager(linearLayoutManager);

        NetWorkApi.getHotAdviser(new NetWorkApi.OnResultListener<ArrayList<ModelHotAdviser>>() {
            @Override
            public void onSuccess(int code, ArrayList<ModelHotAdviser> result) {
                if (code == 200) {
                    LogUtil.e("tag", "onSuccess=" + result);
                    mModelHotAdvisers = result;//为了传递给fragmentAdviser
                    //homeRecyclerview.setHasFixedSize(true);
                    AdapterHotAdviser adapter = new AdapterHotAdviser(getContext(), result);
                    homeRecyclerview.setAdapter(adapter);
                    mHandler.sendEmptyMessageDelayed(0, 0);
                } else {
                    setFailView(errorPage, refreshView);
                }

                // mLoadingView.dismiss();
                // errorPage.setVisibility(View.VISIBLE);

                sendEventBusMsg(result);//获取后发送出去
            }


            @Override
            public void onFailure(Throwable t) {
                //TODO
                LogUtil.e("tag", "onFailure=...." + t);
                setFailView(errorPage, refreshView);
            }
        });

    }

    private void sendEventBusMsg(ArrayList<ModelHotAdviser> result) {
        EventBus.getDefault().postSticky(new MessageEvent(result));//发送给需要
    }


    @Override
    public void setListener() {

        //头布局
        homeRollViewPager.setOnRollClickListener(new RollViewPager.OnRollClickListener() {
            @Override
            public void onClick(int position) {

                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra(Constant.SOURCE_FLAG, "FragmentHome");

                intent.putExtra(INTENT_DATA, mContentBeens.get(position).getTitle());
                intent.putExtra(Constant.HEADCONTENT_URL, mContentBeens.get(position).getOuterUrl());
                startActivity(intent);

            }
        });

        //垂直跑马灯 的设置在adpter里

        refreshView.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        });
    }

    @OnClick({R.id.imageButtonMoreInfo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonMoreInfo:// 更多热点新闻
                startActivity(new Intent(getContext(), NewsListActivity.class));
        }
    }


   /* //获取垂直跑马灯的数据
    public List<ModelHomeInfo> getHomeInfos(){
        return mModelHomeInfos;
    }*/
}