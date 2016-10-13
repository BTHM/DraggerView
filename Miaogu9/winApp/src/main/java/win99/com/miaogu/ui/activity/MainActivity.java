package win99.com.miaogu.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import win99.com.miaogu.R;
import win99.com.miaogu.ui.fragment.DraggerFragment;
import win99.com.miaogu.ui.fragment.HomeFragment;
import win99.com.miaogu.view.BaseActivity;


public class MainActivity extends BaseActivity {


    @Bind(R.id.main_fragment)
    FrameLayout                          mainFragment;
    @Bind(R.id.activity_main)
    RelativeLayout                       activityMain;
    private HomeFragment mHomeFragment;
    private DraggerFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mHomeFragment = new HomeFragment();
        mFragment = new DraggerFragment();

        /*public boolean equals(Object obj) {
            return (this == obj);
        }*/
        Object o = new Object();
        o.equals(mFragment);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment, mFragment)
                .commit();

    }


}
