package win99.com.miaogu.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import win99.com.miaogu.R;
import win99.com.miaogu.util.LogUtil;


/**
 * 专门用来实现轮播图功能
 * Created by xx on 2016/7/22.
 */
public class RollViewPager extends ViewPager {
    private List<ImageView> mDots;//轮播图的点的集合
    private TextView mTitle;//当轮播图滑动的时候，标题控件
    private List<String> mTitles;//轮播图滑动的时候，标题数据源
    private List<String> mImages;//轮播图的图片数据源
    private RollAdapter mRollAdapter;
    private int prevousPosition = 0;//记录上一个选中点


    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //将轮播图切换到下一个页面
            int currentItem = RollViewPager.this.getCurrentItem();
            int pos = (currentItem + 1)%mImages.size();
            RollViewPager.this.setCurrentItem(pos,false);
            mhandler.sendEmptyMessageDelayed(0, 2000);
        }
    };
    private int mDownX;
    private int mDownY;
    private long mCurrentTimeMillis;
    private OnRollClickListener mOnRollClickListener;//轮播图点击接口监听对象

    public RollViewPager(Context context, List<ImageView> dots) {
        super(context);
        init();
        this.mDots = dots;
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        //初始化的时候设置页面切换监听
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDots.get(prevousPosition).setBackgroundResource(R.mipmap.dot_normal);
                prevousPosition = position;
                //修改标题
                mTitle.setText(mTitles.get(position));
                //修改小圆点
                mDots.get(position).setBackgroundResource(R.mipmap.dot_focus);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //设置触摸监听
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //handler删除所有 的任务与消息
                        mhandler.removeCallbacksAndMessages(null);
                        //记录下当前的时间
                        mCurrentTimeMillis = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        //手指抬起的时候重启自动轮播
                        mhandler.sendEmptyMessageDelayed(0, 2000);
                        LogUtil.dd("ACTION_Up");
                        //获取当前的时间，并计算，如果时间差在500ms以内点击事件
                        long diffTime = System.currentTimeMillis() - mCurrentTimeMillis;
                        if (diffTime < 500) {
//                            Toast.makeText(getContext(), "热门新闻被点击了", Toast.LENGTH_SHORT).show();
                            if (mOnRollClickListener != null) {
                                mOnRollClickListener.onClick(prevousPosition);
                            }
                        }
                        break;
                    //当手指触摸viewpager，移动到这个控件之外，产生cancel事件，up事件就再也没有了
                    case MotionEvent.ACTION_CANCEL:
                        LogUtil.dd("ACTION_CANCEL");
                        mhandler.sendEmptyMessageDelayed(0, 2000);
                        break;
                }
                return false;
            }
        });
    }

    //将传递进来显示标题的控件进行记录，并且，记录标题数据集合
    public void setTitles(TextView top_news_title, List<String> topNewsTitles) {
        this.mTitle = top_news_title;
        this.mTitles = topNewsTitles;
        //设置默认显示
        this.mTitle.setText(mTitles.get(0));
    }

    //用来记录轮播图，图片数据集合
    public void setImageLists(List<String> imageLists) {
        this.mImages = imageLists;
    }

    public void start() {
        //根据当前的数据，实现轮播图功能
        if (mRollAdapter == null) {
            mRollAdapter = new RollAdapter();
            this.setAdapter(mRollAdapter);
        } else {
            mRollAdapter.notifyDataSetChanged();
        }


        //开启自动轮播
        mhandler.sendEmptyMessageDelayed(0, 2000);

    }

    //为了在下拉刷新重新进行start之前，先将之前的handler的消息循环给清除
    public void stop() {
        mhandler.removeCallbacksAndMessages(null);
    }

    private class RollAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = (ImageView) View.inflate(getContext(), R.layout.viewpager_item, null);
            Glide.with(getContext()).load(mImages.get(position)).into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) ev.getX();
                mDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getX();
                int moveY = (int) ev.getY();

                int diffX = moveX - mDownX;
                int diffY = moveY - mDownY;
                if (Math.abs(diffX) > Math.abs(diffY)) {//水平移动，请求父控件不要拦截自己
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {//垂直移动，父控件想拦截就拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public interface OnRollClickListener{
        public void onClick(int position);
    }

    public void setOnRollClickListener(OnRollClickListener listener) {
        this.mOnRollClickListener = listener;
    }
}
