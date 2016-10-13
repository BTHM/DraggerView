package win99.com.miaogu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by xx on 2016/6/11.
 */
public class CustomViewPager extends ViewGroup {

    private GestureDetector mDetector;//手势识别器
    private Scroller mScroller;//只用来进行数据的模拟，而不做任何滚动操作
    private OnPageChangeListener mOnPageChangeListener;//页面切换监听接口对象
    private int mStartX;
    private int mStartY;

    public CustomViewPager(Context context) {
        super(context);
        init();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化方法
    private void init() {
        //当手指滑动的时候调用该方法
        mDetector = new GestureDetector(getContext(), new GestureDetector
                .SimpleOnGestureListener() {
            //当手指滑动的时候调用该方法

            /**
             *
             * @param e1    手指按下事件
             * @param e2    当前的move事件
             * @param distanceX 上一次move事件与当前move事件之间的间距 = 上一次的move事件的getx - 当前的move事件的getx
             * @param distanceY 上一次move事件与当前move事件之间的间距 = 上一次的move事件的gety - 当前的move事件的gety
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float
                    distanceY) {
                //scrollto（绝对位置）
                //scrollby(相对位置)

                //移动位置(在x轴进行移动)
                scrollBy((int) distanceX,0);

                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });

        mScroller = new Scroller(getContext());
    }


    //实现排版操作，才能将子控件显示出来
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = this.getChildAt(i);
            childView.layout(0 + i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //把触摸事件交给手势识别器处理
        mDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //获取当前屏幕显示的位置
                int scrollX = getScrollX();//获取当前屏幕左上角的x轴的位置
                System.out.println("scrollX:"+scrollX);
                //根据当前屏幕显示的位置计算出将要移动到哪一个界面
                int postion = (scrollX + getWidth()/2)/getWidth();
                System.out.println("postion:"+postion);

                // 设置右边界
                if (postion > (this.getChildCount() - 1)) {
                    postion = this.getChildCount() - 1;
                }

                //跳转到对应的position位置
                setCurrentItem(postion);

                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(postion);
                }

                break;
        }
        return true;//自己处理手指触摸事件
    }

    //与invalidate进行配合使用的方法,invalidate执行一次，computeScroll就执行一次
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            //获取当前的模拟的值，并且让界面滑动
            int currX = mScroller.getCurrX();//获取当前正在模拟的x轴的值
            System.out.println("currx:"+currX);
            scrollTo(currX,0);//将界面不断得进行移动到绝对的位置上，来实现，界面的缓慢滑动效果
            invalidate();
        }
    }

    public void addOnPageChangeListener(OnPageChangeListener listener) {
        this.mOnPageChangeListener = listener;
    }





    //自定义viewpager的页面切换监听
    public interface OnPageChangeListener {
        public void onPageSelected(int position);
    }

    //让界面切换到对应的位置
    public void setCurrentItem(int positon) {
        //通过scrollto跳转到对应的界面
//                scrollTo(postion*getWidth(),0);
        /**
         * startX :x轴的起始点
         * startY ：y轴的起始点
         * dx   ：偏移值 = x轴的结束点-起始点
         * dy   ：偏移值 = y轴的结束点-起始点
         * duration 模拟数据的执行时间
         */
        int startX = getScrollX();
        int endX = positon*getWidth();
        int dx = endX - startX;
        mScroller.startScroll(startX,0,dx,0, Math.abs(dx)*2);//开始模拟数据
        invalidate();
    }

    //测量方法

    /**
     *
     * @param widthMeasureSpec 宽度测量规格
     * @param heightMeasureSpec 高度测量规格
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < this.getChildCount(); i++) {
            View childView = this.getChildAt(i);
            //让对应的子控件进行测量一下，用来将子控件内部的控件进行正确的显示
            childView.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //将按下事件提供给手势识别器
                mDetector.onTouchEvent(ev);
                mStartX = (int) ev.getX();
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                int diffX = endX -mStartX;
                int diffY = endY - mStartY;

                if (Math.abs(diffX) > Math.abs(diffY)) {//左右滑动
                    return true;
                } else {//上下滑动
                    return false;
                }

        }
        return false;
    }
}
