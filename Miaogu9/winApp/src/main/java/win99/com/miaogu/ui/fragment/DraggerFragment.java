package win99.com.miaogu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import win99.com.miaogu.R;
import win99.com.miaogu.util.LogUtil;

/**
 * @author sanshu
 * @data 2016/10/10 下午2:58
 * @ToDo ${TODO}
 */

public class DraggerFragment extends Fragment {


    @Bind(R.id.dragger_text)
    TextView draggerText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dragger, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.dragger_text)
    public void onClick() {
        LogUtil.dd("我被点击了");
    }
}
