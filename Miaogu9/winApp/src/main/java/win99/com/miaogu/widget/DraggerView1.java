package win99.com.miaogu.widget;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import win99.com.miaogu.util.LogUtil;
import win99.com.miaogu.util.WindowUtil;

/**
 * @author sanshu
 * @data 2016/10/10 下午1:21
 * @ToDo ${TODO}
 */

public class DraggerView1 extends LinearLayout {


    private int   mDownY;
    private int   mDownx;
    private int   totalY;
    private float mDownXf;
    private float mDownYf;
    private float totalYf;
    private int   mLocationX;//最初的view所在位置 相对屏幕
    private int   mLocationY;//最初的view所在位置 相对屏幕
    private float mInitDownY;
    private int screenWidth;

    public DraggerView1(Context context) {
        this(context, null);
    }

    public DraggerView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DraggerView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {



    }

    //this.on

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int[] location = new int[2];
        WindowUtil.getScreenPositon(this, location);
        //getLocationOnScreen(location);
        mLocationX = location[0];
        mLocationY = location[1];
        LogUtil.dd("mLocationY="+mLocationY);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownXf = ev.getRawX();
                mDownYf = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveXf = ev.getRawX();
                float moveYf = ev.getRawY();
                float dy = moveYf - mDownYf;
                LogUtil.dd("ev.getRawY()=" + ev.getRawY());
                LogUtil.dd("dy=" + dy);
                int[] location=new int[2];
                WindowUtil.getScreenPositon(this, location);
                int locationY = location[1];// 再次测量屏幕
                LogUtil.dd("locationY=" + locationY);
                if (dy<0 && dy+locationY < mLocationY) {
                    dy=mLocationY-locationY;
                }
                totalYf = totalYf + dy;
                dispatchAction(totalYf);
                /*if (locationY <= ) {
                    //往回拉的时候，确认下不要超出屏幕


                }*/

                mDownYf = moveYf;
                LogUtil.dd("totalYf=" + totalYf);

                break;
            case MotionEvent.ACTION_UP:
                //totalYf=0;
                break;
            default:
        }

        return true;
        // return super.dispatchTouchEvent(ev);
    }

    //不起作用的
    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.dd("onInterceptTouchEvent");
        return true;
    }*/

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownx = (int) event.getX();
                mDownY = (int)event.getY();
                LogUtil.dd("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.dd("ACTION_MOVE");
                int moveX = (int) event.getX();
                int moveY = (int)event.getY();
                int dy = moveY - mDownY;
                totalY+=dy;
                *//*if (dy > 0) {
                    //向下移动了

                }*//*
                mDownY=moveY;
                LogUtil.dd("totalY="+totalY);
                dispatchAction(totalY);

                break;
            case MotionEvent.ACTION_UP:
                //totalY=0;
                break;
            default:
        }
        return true;
    }*/

    // 动画执行
    private void dispatchAction(float totalY) {


        //WindowUtil.getMetrics(getContext(),screenWidth,screenHeigth);
        LogUtil.dd("screenWidth="+screenWidth);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int height = outMetrics.heightPixels;
        int width = outMetrics.heightPixels;

        float precent = totalY / height * 0.8f;
        //最好不要修改百分比 修改了后会导致跟手的动作不准确
        if (precent <= 0.f) {
            precent=0.f;
        }else if (precent >= 0.8f) {
            precent=0.8f;
        }
        LogUtil.dd("height=" + height);
        LogUtil.dd("precent=" + precent);
        this.setTranslationY(evaluate(precent, 0.f, height * 0.8f));
        this.setScaleX(evaluate(precent, 1.f, 0.5f));
        this.setScaleY(evaluate(precent, 1.f, 0.5f));
        //缩小了多少就平移／2
        LogUtil.dd("width="+width);
        LogUtil.dd("this.getScaleX()="+this.getScaleX());
        LogUtil.dd("(1-getScrollX()())*getWidth()="+((1-this.getScaleX()))*0.5f);

        this.setTranslationX((1-this.getScaleX())*getWidth()*0.5f);

        //this.setTranslationX(evaluate(precent, 0, (width - this.getWidth()) * 0.5f));
    }

    //估值计算方法
    public Float evaluate(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }

    private void initView1() {
        ViewDragHelper mDragger;
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            //private int mDx;

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                //dx 往左为-，往右为+；


                int leftBound = getPaddingLeft();

                int rightBound = getWidth() - child.getWidth() - leftBound;

                int newLeft = Math.min(Math.max(left, leftBound), rightBound);
//                LogUtil.dd("rightBound="+rightBound);
//
//                LogUtil.dd("leftBound="+leftBound+"left="+left+"dx="+dx+"newLeft"+newLeft);
                //int nowleft = newLeft - dx;
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int topBound = getPaddingTop();
                int bottomBound = getHeight() - child.getHeight() - getPaddingBottom();

                int newTop = Math.min(Math.max(top, topBound), bottomBound);
                return newTop;
            }

            /**
             * @param changedView
             * @param left
             * @param top
             * @param dx  左移为-，
             * @param dy  下移为+；
             */
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                //super.onViewPositionChanged(changedView, left, top, dx, dy);

                //mLeft = left;
                /**/

                LogUtil.dd("dx=" + dx + "dy=" + dy);

                ViewGroup.LayoutParams params = changedView.getLayoutParams();
                /*params.width=changedView.getWidth()-dx;
                params.height=changedView.getHeight()-dy;*/
                LogUtil.dd("params.width=" + params.width + "params.height=" + params.height);
                // changedView.setLayoutParams(params);


                FloatEvaluator floatEvaluator = new FloatEvaluator();

                floatEvaluator.evaluate(0.5f, 0.f, 1.f);
                ValueAnimator.ofObject(floatEvaluator, changedView).start();

                //;ViewCompat.animate(changedView).scaleX()
            }

            /*@Override
            public int getViewVerticalDragRange(View child) {

                return mLeft;
            }*/

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //super.onViewReleased(releasedChild, xvel, yvel);
            }
        });
    }


    /**  下面这两个方法 拦截住触摸事件
     * @param ev
     * @return
     */
    /*@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }*/

}
