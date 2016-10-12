package win99.com.miaogu9.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import win99.com.miaogu9.R;
import win99.com.miaogu9.catloadinglibrary.CatLoadingView;
import win99.com.miaogu9.ui.MainActivity;

/**
 * @author sanshu
 * @data 2016/8/31 9:53
 * @ToDo ${TODO}
 */

public abstract class BaseFragment extends Fragment implements UIOparetion {


    public MainActivity mActivity;//mainActivity
    public Toolbar      mToolBar;    //mainActivity 的 toolbar

    public CatLoadingView mLoadingView;//进度条
    private View           mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取main界面的控件,要在initLayout()执行前
        getMianView();

        mView =  inflater.inflate(initLayout(), container, false);
        ButterKnife.bind(this, mView);

        return mView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //显示进度条
        showProgress();

        //设置数据
        setData();

        //设置监听
        setListener();

    }

    //获取main界面的控件
    private void getMianView() {
        mActivity = (MainActivity) getActivity();
        //mToolBar = mActivity.getToolBar();
    }


    /**
     * @param failView
     * @param susView
     */
    public void setSuccesView(View failView, View susView) {

        if (mLoadingView.isShowing()) {
            mLoadingView.dismiss();
        }
        if (failView.getVisibility() == View.VISIBLE) {
            failView.setVisibility(View.INVISIBLE);
        }
        if (susView.getVisibility() != View.VISIBLE) {
            susView.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 登陆失败界面
     */
    public void setFailView(final View failView, View susView){
        if (mLoadingView.isShowing()) {
            mLoadingView.dismiss();
        }
        if (failView.getVisibility() != View.VISIBLE) {
            failView.setVisibility(View.VISIBLE);
        }
        if (susView.getVisibility() == View.VISIBLE) {
            susView.setVisibility(View.INVISIBLE);
        }
        failView.findViewById(R.id.error_btn_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
                failView.setVisibility(View.INVISIBLE);
                setData();

            }
        });
    }


    /**
     * 显示进度条
     */
    public void showProgress() {
        if (mLoadingView == null) {
            mLoadingView = new CatLoadingView();
        }
        mLoadingView.show(getFragmentManager(), null);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



}
