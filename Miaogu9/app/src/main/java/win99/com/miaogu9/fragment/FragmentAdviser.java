package win99.com.miaogu9.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import win99.com.miaogu9.R;
import win99.com.miaogu9.adapter.AdapterAdviserHot;
import win99.com.miaogu9.base.BaseFragment;
import win99.com.miaogu9.domain.MessageEvent;
import win99.com.miaogu9.domain.ModelHotAdviser;
import win99.com.miaogu9.library.VerticalBannerView;
import win99.com.miaogu9.pull2refresh.PullToRefreshLayout;
import win99.com.miaogu9.util.LogUtil;


/**
 * @author sanshu
 * @data 2016/9/6 10:16
 * @ToDo ${TODO}
 */

public class FragmentAdviser extends BaseFragment {


    private static final String TAG = FragmentAdviser.class.getName();
    @Bind(R.id.imageViewHot)
    ImageView           imageViewHot;
    @Bind(R.id.imageButtonMoreAdviser)
    ImageView           imageButtonMoreAdviser;
    @Bind(R.id.adviser_hot_title)
    RelativeLayout      adviserHotTitle;
    @Bind(R.id.banner_hot_ask)
    VerticalBannerView  bannerHotAsk;
    @Bind(R.id.hotQuestion)
    LinearLayout        hotQuestion;
    @Bind(R.id.imageViewConsultant)
    ImageView           imageViewConsultant;
    @Bind(R.id.imageButtonMoreConsultant)
    ImageView           imageButtonMoreConsultant;
    @Bind(R.id.consultant_title)
    RelativeLayout      consultantTitle;
    @Bind(R.id.gridviewConsultant)
    GridView            gridviewConsultant;
    @Bind(R.id.consultant)
    LinearLayout        consultant;
    @Bind(R.id.my_image_view)
    SimpleDraweeView    myImageView;
    @Bind(R.id.layoutAsk)
    RelativeLayout      layoutAsk;
    @Bind(R.id.freeToAsk)
    LinearLayout        freeToAsk;
    @Bind(R.id.imageViewFinance)
    ImageView           imageViewFinance;
    @Bind(R.id.imageButtonMoreMyQuestion)
    ImageView           imageButtonMoreMyQuestion;
    @Bind(R.id.my_question)
    RelativeLayout      myQuestion;
    @Bind(R.id.imageViewNodata)
    ImageView           imageViewNodata;
    @Bind(R.id.textViewNodata)
    TextView            textViewNodata;
    @Bind(R.id.nodata)
    RelativeLayout      nodata;
    @Bind(R.id.iv_ask)
    ImageView           ivAsk;
    @Bind(R.id.tv_ask_title)
    TextView            tvAskTitle;
    @Bind(R.id.layoutMyAsk)
    LinearLayout        layoutMyAsk;
    @Bind(R.id.iv_bar)
    View                ivBar;
    @Bind(R.id.iv_answer)
    ImageView           ivAnswer;
    @Bind(R.id.tv_name)
    TextView            tvName;
    @Bind(R.id.tv_company)
    TextView            tvCompany;
    @Bind(R.id.tv_answer)
    TextView            tvAnswer;
    @Bind(R.id.layoutAnswer)
    LinearLayout        layoutAnswer;
    @Bind(R.id.buttonSimilarAnswer)
    Button              buttonSimilarAnswer;
    @Bind(R.id.layoutNoAnswer)
    LinearLayout        layoutNoAnswer;
    @Bind(R.id.tv_time)
    TextView            tvTime;
    @Bind(R.id.myAsk)
    LinearLayout        myAsk;
    @Bind(R.id.layout_my_question)
    LinearLayout        layoutMyQuestion;
    @Bind(R.id.refresh_view)
    PullToRefreshLayout refreshView;
    @Bind(R.id.error_page_iv)
    ImageView           errorPageIv;
    @Bind(R.id.error_btn_reload)
    Button              errorBtnReload;
    @Bind(R.id.error_page)
    FrameLayout         errorPage;



    private List<ModelHotAdviser> mHotAdvisers;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public int initLayout() {
       // Toolbar toobar = mActivity.getToobar();
        mActivity.getToolBarTextView().setText(R.string.adviser_page_name);
        mActivity.getToolBarImageView().setVisibility(View.INVISIBLE);

        return R.layout.fragment_adviser;
    }

    @Override
    public void setData() {

        //设置toolbar
        setToolbar();

        //投顾热评 滚动条
        setHotAdviser();


    }

    private void setHotAdviser() {


        if (mHotAdvisers != null && mHotAdvisers.size() > 0) {
            AdapterAdviserHot adviserHot = new AdapterAdviserHot(getActivity(), mHotAdvisers);
            LogUtil.d(TAG, "bannerHotAsk+mHotAdvisers+adviserHot..." + bannerHotAsk + ".." + mHotAdvisers + "......" + adviserHot);
            bannerHotAsk.setAdapter(adviserHot);

            bannerHotAsk.start();
           // LogUtil.d(TAG,"mLoadingView.isShowing()="+mLoadingView.isShowing());
            //mLoadingView.dismiss();
            setSuccesView(errorPage,refreshView);
            mLoadingView.dismiss();
            refreshView.setVisibility(View.VISIBLE);
        }


    }

    //eventbus 接收者

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void helloEventBus(MessageEvent message) {
        mHotAdvisers = message.getLists();
        LogUtil.d(TAG, "mHotAdvisers=" + mHotAdvisers);

        //投顾热评 滚动条,不能放到这里 ,优先于布局文件的加载,所以不能设置布局

    }


    private void setToolbar() {



    }

    @Override
    public void setListener() {
        //
    }

    @Override
    public void onDestroy() {


        EventBus.getDefault().unregister(this);
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.my_question)
    public void onClick() {
    }

}