package win99.com.miaogu9.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.OnClick;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseFragment;
import win99.com.miaogu9.ui.LoginSignUpActivity;
import win99.com.miaogu9.ui.MineActivity;
import win99.com.miaogu9.ui.SettingActivity;
import win99.com.miaogu9.util.Constant;
import win99.com.miaogu9.util.LocalInfo;
import win99.com.miaogu9.util.SpUtil;


/**
 * @author sanshu
 * @data 2016/9/6 10:16
 * @ToDo ${TODO}
 */
public class FragmentMine extends BaseFragment {

    @Bind(R.id.topBack)
    ImageButton      topBack;
    @Bind(R.id.topTitle)
    TextView         topTitle;
    @Bind(R.id.topMessage)
    ImageButton      topMessage;
    @Bind(R.id.layoutTopBar)
    LinearLayout     layoutTopBar;
    @Bind(R.id.imageViewAvatar)
    SimpleDraweeView imageViewAvatar;
    @Bind(R.id.textViewName)
    TextView         textViewName;
    @Bind(R.id.imageViewMore)
    ImageView        imageViewMore;
    @Bind(R.id.layoutPersonal)
    RelativeLayout   layoutPersonal;
    @Bind(R.id.imageViewQuestion)
    ImageView        imageViewQuestion;
    @Bind(R.id.imageViewMoreQuestion)
    ImageView        imageViewMoreQuestion;
    @Bind(R.id.imageViewNotify)
    ImageView        imageViewNotify;
    @Bind(R.id.layoutMyQuestion)
    RelativeLayout   layoutMyQuestion;
    @Bind(R.id.imageViewCollection)
    ImageView        imageViewCollection;
    @Bind(R.id.imageViewMoreCollection)
    ImageView        imageViewMoreCollection;
    @Bind(R.id.layoutMyCollection)
    RelativeLayout   layoutMyCollection;
    @Bind(R.id.imageViewSetting)
    ImageView        imageViewSetting;
    @Bind(R.id.imageViewMoreSetting)
    ImageView        imageViewMoreSetting;
    @Bind(R.id.layoutSetting)
    RelativeLayout   layoutSetting;
    private String TAG = FragmentMine.class.getName();
    private String mMemberId;
    private String mToken;


    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册接收
        EventBus.getDefault().register(UserInfo.class);
    }*/

    @Override
    public int initLayout() {
        mActivity.getToobar().setVisibility(View.GONE);

        return R.layout.fragment_mine;
    }

    @Override
    public void setData() {
        mLoadingView.dismiss();

        getLocalUserInfo();
        //setSuccesView();

    }

    private void getLocalUserInfo() {
        mMemberId = SpUtil.getString(getContext(), LocalInfo.MEMBERID);
        mToken = SpUtil.getString(getContext(), LocalInfo.TOKEN);
        if (!(TextUtils.isEmpty(mMemberId) && TextUtils.isEmpty(mToken))) {
            String iconUrl = SpUtil.getString(getContext(), LocalInfo.DISPLAYURL);
            //设置头像
            if (!TextUtils.isEmpty(iconUrl)) {
                imageViewAvatar.setImageURI(Constant.IMAGE_URL + iconUrl);
            }

            String nikeName = SpUtil.getString(getContext(), LocalInfo.NIKENAME);
            textViewName.setText(nikeName);


        }


    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.layoutPersonal, R.id.layoutMyQuestion,
            R.id.layoutMyCollection, R.id.layoutSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutPersonal:
                //登陆
                if (mMemberId == null) {
                    logIn();
                } else {
                    //跳转至 个人资料
                    startActivity(new Intent(getActivity(), MineActivity.class));
                }


                break;
            case R.id.layoutMyQuestion:
                //跳转至 我的提问
                break;
            case R.id.layoutMyCollection:
                //跳转值收藏
                break;
            case R.id.layoutSetting:
                if (mMemberId == null) {
                    logIn();
                } else {
                    startActivity(new Intent(getActivity(), SettingActivity.class));
                }
                break;
            default:
        }
    }


    private void logIn() {
        startActivity(new Intent(getContext(), LoginSignUpActivity.class));
    }


    //eventbus 接收者

    /*@Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveUserInfo(UserInfo info) {

        LogUtil.d(TAG, "info=" + info);


        //投顾热评 滚动条,不能放到这里 ,优先于布局文件的加载,所以不能设置布局
    }*/

}