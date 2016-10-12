package win99.com.miaogu.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import win99.com.miaogu.R;
import win99.com.miaogu.presneter.HomePresenter;
import win99.com.miaogu.view.interf.IHomeView;
import win99.com.miaogu.widget.RollViewPager;

/**
 * @author sanshu
 * @data 2016/10/10 上午10:25
 * @ToDo ${TODO}
 */

public class HomeFragment extends Fragment implements IHomeView {


    @Bind(R.id.home_rollViewPager)
    RollViewPager homeRollViewPager;
    @Bind(R.id.home_btn)
    Button        homeBtn;
    private HomePresenter mHomePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomePresenter = new HomePresenter(this);
        loadHeader();
    }

    private void loadHeader() {
        mHomePresenter.getHeaderData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void setRollPager(List<String> list) {
        homeRollViewPager.setImageLists(list);
        homeRollViewPager.start();
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrPage() {

    }

    @OnClick(R.id.home_btn)
    public void onClick() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment,new DraggerFragment())
                .hide(this)
                .commit();
    }
}
