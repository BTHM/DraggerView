package win99.com.miaogu9.ui;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import butterknife.Bind;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.domain.MainTab;

public class MainActivity extends BaseActivity {


    @Bind(R.id.main_container)
    FrameLayout     mainContainer;
    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    @Bind(R.id.tab_layout)
    LinearLayout    tabLayout;
    @Bind(R.id.toolbar_back)
    ImageView       toolbarBack;
    @Bind(R.id.tool_textView)
    TextView        toolTextView;
    @Bind(R.id.main_toolbar)
    Toolbar         mainToolbar;


    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setData() {

        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_container);
        //FragmentTabHost初始化  关联容器布局

        //获取当前标签数
        MainTab[] tabs = MainTab.values();

        for (int i = 0; i < tabs.length; i++) {
            MainTab mainTab = tabs[i];
            // 1. 创建一个新的选项卡,这里的的setIndicator仅是为了区分
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName())).setIndicator(getTabItemView(i));
            //最后一个buddle可携带数据
            mTabHost.addTab(tab, mainTab.getClz(), null);

            //;
        }

        //获取当前用户是否已登陆

    }


    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_content, null);

        MainTab[] tabs = MainTab.values();
        MainTab mainTab = tabs[index];

        ImageView imageView = (ImageView) view.findViewById(R.id.tab_imageview);
        imageView.setImageResource(mainTab.getResIcon());

        TextView textView = (TextView) view.findViewById(R.id.tab_textview);
        textView.setText(getString(mainTab.getResName()));

        return view;
    }

    @Override
    public void setListener() {

    }

    //获取toolbar
    public Toolbar getToobar(){
        return mainToolbar;
    }
    //获取toolbar中文字
    public TextView getToolBarTextView(){
        return toolTextView;
    }
    //获取toolbar中图标
    public ImageView getToolBarImageView(){
        return toolbarBack;
    }
}
