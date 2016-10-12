package win99.com.miaogu9.domain;

import win99.com.miaogu9.R;
import win99.com.miaogu9.fragment.FragmentAdviser;
import win99.com.miaogu9.fragment.FragmentHome;
import win99.com.miaogu9.fragment.FragmentMine;
import win99.com.miaogu9.fragment.FragmentTV;

/**
 * @author sanshu
 * @data 2016/9/6 10:16
 * @ToDo ${TODO}
 */

public enum MainTab {

    HOME(0, R.string.main_tab_home, R.drawable.tab_icon_home,
            FragmentHome.class),

    ADVISER(1, R.string.main_tab_adviser, R.drawable.tab_icon_adviser,
            FragmentAdviser.class),

    TV(2, R.string.main_tab_tv, R.drawable.tab_icon_tv,
            FragmentTV.class),

    MINE(3, R.string.main_tab_mine, R.drawable.tab_icon_mine,
            FragmentMine.class);

    private int idx;//顺序
    private int resName;//标签名
    private int resIcon;//图标资源
    private Class<?> clz;//fragment


    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
