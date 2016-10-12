package win99.com.miaogu9.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.pedrovgs.DraggableListener;
import com.github.pedrovgs.DraggableView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import win99.com.miaogu9.R;
import win99.com.miaogu9.adapter.TvListAdapter;
import win99.com.miaogu9.base.BaseFragment;
import win99.com.miaogu9.domain.HomeInfo;
import win99.com.miaogu9.domain.LocalInfo;
import win99.com.miaogu9.domain.TvInfo;
import win99.com.miaogu9.net.NetWorkApi;
import win99.com.miaogu9.pull2refresh.PullToRefreshLayout;
import win99.com.miaogu9.pull2refresh.PullableListView;
import win99.com.miaogu9.util.DateUtil;
import win99.com.miaogu9.util.LogUtil;
import win99.com.miaogu9.util.ToastUtil;
import win99.com.miaogu9.view.JCVideoPlayerStandardShowShareButton;


/**
 * @author sanshu
 * @data 2016/9/6 10:16
 * @ToDo ${TODO}
 */
public class FragmentTV extends BaseFragment {

    private static final String TAG = FragmentTV.class.getName();
    @Bind(R.id.tv_pullableListView)
    PullableListView                     tvPullableListView;
    @Bind(R.id.refresh_view)
    PullToRefreshLayout                  refreshView;
    @Bind(R.id.tv_play_title)
    TextView                             tvPlayTitle;
    @Bind(R.id.tv_play_time)
    TextView                             tvPlayTime;
    @Bind(R.id.ib_tv_good)
    ImageButton                          ibTvGood;
    @Bind(R.id.tv_good_num)
    TextView                             tvGoodNum;
    @Bind(R.id.ib_tv_save)
    ImageButton                          ibTvSave;
    @Bind(R.id.tv_save_num)
    TextView                             tvSaveNum;
    @Bind(R.id.tv_see_num)
    TextView                             tvSeeNum;
    @Bind(R.id.iv_tv_avatar)
    ImageView                            ivTvAvatar;
    @Bind(R.id.tv_name)
    TextView                             tvName;
    @Bind(R.id.lv_play_recommend)
    ListView                             lvPlayRecommend;
    @Bind(R.id.lv_episodes)
    ScrollView                           lvEpisodes;
    @Bind(R.id.iv_fan_art)
    JCVideoPlayerStandardShowShareButton ivFanArt;
    @Bind(R.id.draggable_view)
    DraggableView                        draggableView;
    @Bind(R.id.error_page_iv)
    ImageView                            errorPageIv;
    @Bind(R.id.error_btn_reload)
    Button                               errorBtnReload;
    @Bind(R.id.error_page)
    FrameLayout                          errorPage;


    private List<TvInfo>  mTvInfos;
    private List<TvInfo>  mResultData;
    private TvListAdapter mTvlistAdapter;
    private Map           mHashMap;
    private int mPageSize = 10;



    @Override
    public int initLayout() {
        ShareSDK.initSDK(getContext(),"178065270a0e0");
        mActivity.getToobar().setVisibility(View.GONE);
        return R.layout.fragment_tv;
    }

    @Override
    public void setData() {
        initializeDraggableView();
        //
        int startPositon = 1;
        getTvListData(startPositon);
    }

    /**
     * Initialize DraggableView.
     */
    private void initializeDraggableView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                draggableView.setVisibility(View.GONE);
                draggableView.closeToRight();
            }
        }, 10);
    }


    /**
     *   retrofit中使用的 是异步请求 ,结果的只能在回调中保持 一致,
     * 不能使用这个函数去 做返回值做什么
     *
     * @param position
     */
    //不能从0开始
    private void getTvListData(int position) {
        mHashMap = new HashMap();
        mHashMap.put("mobileId", "");
        mHashMap.put("labelId", "3");
        //不能从0开始
        NetWorkApi.getTvData(position, mPageSize, mHashMap, new NetWorkApi.OnResultListener<HomeInfo<TvInfo>>() {
            @Override
            public void onSuccess(int code, HomeInfo<TvInfo> result) {
                //设置列表
                if (result.getData() != null && result.getData().size() > 0) {
                    mTvInfos = result.getData();
                    mTvlistAdapter = new TvListAdapter(getContext(), mTvInfos);
                    tvPullableListView.setAdapter(mTvlistAdapter);
                } else {
                    //没有加载到数据
                    ToastUtil.showShort(getContext(), "没有数据");
                }
                setSuccesView(errorPage,refreshView);
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO
                LogUtil.d(TAG, "t=" + t);
            }
        });
    }


    //暂停视频播放
    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public void setListener() {

        //视频条目事件设置
        tvPullableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //点击条目条 显示可拉动布局 进行视频播放
                playTvOnDragView(position);
                //点击条目 设置播放布局的内容
                loadPlayingView(position);
            }
        });

        //拖拽布局的拖动回调监听
        DragHookListeners();

        //上下拉布局的监听

        refreshView.setOnPullListener(new PullToRefreshLayout.OnPullListener() {
            @Override
            public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
                // 下拉刷新操作
                NetWorkApi.getTvData(1, mPageSize, mHashMap, new NetWorkApi.OnResultListener<HomeInfo<TvInfo>>() {
                    @Override
                    public void onSuccess(int code, HomeInfo<TvInfo> result) {

                        TvInfo info = result.getData().get(0);
                        String outerUrl = mTvInfos.get(0).getOuterUrl();
                        if (!info.getOuterUrl().equals(outerUrl)) {

                            for (TvInfo tvInfo : result.getData()) {
                                //将加载到的数据 添加到头部
                                mTvInfos.add(0,tvInfo);
                            }
                            mTvlistAdapter.notifyDataSetChanged();
                            //跳至指定位置
                            tvPullableListView.setSelection(0);
                        } else {
                            //没有加载到数据
                            ToastUtil.showShort(getContext(), "已是最新数据");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        //TODO
                    }
                });
                //告诉刷新结束了
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                //上拉加载更多
                NetWorkApi.getTvData(mTvInfos.size(), mPageSize, mHashMap, new NetWorkApi.OnResultListener<HomeInfo<TvInfo>>() {
                    @Override
                    public void onSuccess(int code, HomeInfo<TvInfo> result) {
                        if (result.getData() != null && result.getData().size() > 0) {
                            //将加载到的数据
                            mTvInfos.addAll(result.getData());
                            mTvlistAdapter.notifyDataSetChanged();
                            //跳至指定位置
                            tvPullableListView.setSelection(mTvInfos.size() - result.getData().size() - 1);
                        } else {
                            //没有加载到数据
                            ToastUtil.showShort(getContext(), "没有更多数据");
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        //TODO
                    }
                });

                //告诉刷新结束了
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);

            }
        });

    }


    private void DragHookListeners() {
        draggableView.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
            }

            @Override
            public void onMinimized() {
                draggableView.setClickToMaximizeEnabled(true);
            }

            @Override
            public void onClosedToLeft() {
                //停止播放
                ivFanArt.release();
                draggableView.setVisibility(View.GONE);
            }

            @Override
            public void onClosedToRight() {
                ivFanArt.release();
                draggableView.setVisibility(View.GONE);
            }
        });
    }


    private void playTvOnDragView(int position) {
        //显示拖拽布局,隐藏上下拉布局


        TvInfo tvInfo = mTvInfos.get(position);
        ivFanArt.setUp(tvInfo.getOuterUrl(),tvInfo.getTitle());
        ivFanArt.backButton.setImageResource(R.mipmap.tv_downarrows_icon);
        ivFanArt.backButton.setVisibility(View.VISIBLE);

        draggableView.setVisibility(View.VISIBLE);
        draggableView.maximize();

        ivFanArt.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draggableView.minimize();//视频最小化
            }
        });

        //视频分享
        ivFanArt.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShareClicked();
            }
        });

        //视频播放
        //ivFanArt.startButton.performClick();
    }

    //分享
    private void onShareClicked() {
        showShare();
    }

    private void loadPlayingView(int position) {
        TvInfo tvInfo = mTvInfos.get(position);
        tvPlayTitle.setText(tvInfo.getTitle());
        tvPlayTime.setText(DateUtil.LongToDate(tvInfo.getCreatetime()));
        tvGoodNum.setText(tvInfo.getAnlysis().get(1).getCount_num() + "");//Good
        tvSaveNum.setText(tvInfo.getAnlysis().get(2).getCount_num() + "");//Save
        tvSeeNum.setText(tvInfo.getAnlysis().get(0).getCount_num() + "次观看");
        tvName.setText(tvInfo.getAuthorName());

        if (LocalInfo.isGood) {
            ibTvGood.setImageResource(R.mipmap.praise_iconpressed);
        } else {
            ibTvGood.setImageResource(R.mipmap.praise_icon);
        }

        if (LocalInfo.isSaved) {
            ibTvSave.setImageResource(R.mipmap.tv_collect_iconpressed);
        } else {
            ibTvSave.setImageResource(R.mipmap.tv_collect_icon);
        }
    }


        private void showShare() {
            ShareSDK.initSDK(getContext());
            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();

             // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
            oks.setTitle("标题");
            // titleUrl是标题的网络链接，QQ和QQ空间等使用
            oks.setTitleUrl("http://sharesdk.cn");
           // text是分享文本，所有平台都需要这个字段
            oks.setText("我是分享文本");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl("http://sharesdk.cn");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl("http://sharesdk.cn");

            // 启动分享GUI
            oks.show(getContext());
        }
}