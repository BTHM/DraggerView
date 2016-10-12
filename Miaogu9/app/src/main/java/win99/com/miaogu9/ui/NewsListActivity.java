package win99.com.miaogu9.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import win99.com.miaogu9.R;
import win99.com.miaogu9.adapter.AdapterNewsList;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.domain.Announcement;
import win99.com.miaogu9.domain.HomeInfo;
import win99.com.miaogu9.domain.MessageEventToNews;
import win99.com.miaogu9.domain.ModelHomeInfo;
import win99.com.miaogu9.net.NetWorkApi;
import win99.com.miaogu9.pull2refresh.PullToRefreshLayout;
import win99.com.miaogu9.pull2refresh.PullableListView;
import win99.com.miaogu9.util.LogUtil;

public class NewsListActivity extends BaseActivity {

    @Bind(R.id.newslist_listView)
    PullableListView    newslistView;
    @Bind(R.id.activity_news_list)
    PullToRefreshLayout activityNewsListLayout;

    private static final String TAG = NewsListActivity.class.getName();
    @Bind(R.id.toolbar_back)
    ImageView toolbarBack;
    @Bind(R.id.tool_textView)
    TextView  toolTextView;
    @Bind(R.id.main_toolbar)
    Toolbar   mainToolbar;
    //接收到的消息
    private List<ModelHomeInfo>     mModelHomeInfos;
    private AdapterNewsList         mNewsList;
    private HashMap<String, String> mHashMap;

    @Override
    public int initLayout() {
        //先发送后注册的事件
        EventBus.getDefault().register(this);
        return R.layout.activity_news_list;
    }

    @Override
    public void setData() {
        setToolbar();

        mNewsList = new AdapterNewsList(this, mModelHomeInfos);
        newslistView.setAdapter(mNewsList);

        mHashMap = new HashMap<>();

        String mobileId = "";
        mHashMap.put("mobileId", mobileId);//登陆用户必填

        mHashMap.put("labelId", 4 + "");


    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void helloEventBus(MessageEventToNews message) {
        mModelHomeInfos = message.getLists();
        LogUtil.d(TAG, "收到消息:messageLists=" + mModelHomeInfos);

        // 该消息的接收 优先于布局文件的加载,所以不能设置布局
    }


    @Override
    public void setListener() {
        activityNewsListLayout.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                NetWorkApi.getAnnconce(1, mModelHomeInfos.size(), mHashMap, new NetWorkApi.OnResultListener<HomeInfo<Announcement>>() {
                    @Override
                    public void onSuccess(int code, HomeInfo<Announcement> result) {
                        Announcement announcement = null;
                        if (result.getData().size() > 0) {
                            announcement = result.getData().get(0);
                            ModelHomeInfo info = new ModelHomeInfo(announcement.getTitle(),
                                    announcement.getOuterUrl());
                        }

                        if (!mModelHomeInfos.get(0).getUrl().equals(announcement.getOuterUrl())) {
                            for (int i = 0; i < result.getData().size(); i++) {
                                Announcement info = result.getData().get(i);
                                mModelHomeInfos.add(0, new ModelHomeInfo(info.getTitle(), info.getOuterUrl()));
                            }
                            mNewsList.notifyDataSetChanged();
                        }
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }

            @Override
            public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
                NetWorkApi.getAnnconce(mModelHomeInfos.size(), 10, mHashMap, new NetWorkApi.OnResultListener<HomeInfo<Announcement>>() {
                    @Override
                    public void onSuccess(int code, HomeInfo<Announcement> result) {
                        Announcement announcement = null;
                        if (result.getData().size() > 0) {
                            for (int i = 0; i < result.getData().size(); i++) {
                                announcement = result.getData().get(i);
                                ModelHomeInfo info = new ModelHomeInfo(announcement.getTitle(),
                                        announcement.getOuterUrl());
                                mModelHomeInfos.add(info);
                            }
                            mNewsList.notifyDataSetChanged();
                            newslistView.setSelection(mModelHomeInfos.size() - result.getData().size() - 1);
                        }
                        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

            }
        });
    }


    private void setToolbar() {
        toolTextView.setText(R.string.newslist_name);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsListActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }


}
