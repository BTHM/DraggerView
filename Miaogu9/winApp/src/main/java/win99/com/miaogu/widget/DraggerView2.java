package win99.com.miaogu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import win99.com.miaogu.util.LogUtil;
import win99.com.miaogu.util.WindowUtil;

/**
 * @author sanshu
 * @data 2016/10/10 下午1:21
 * @ToDo ${TODO}
 */

public class DraggerView2 extends LinearLayout {



    private float mDownY;//触摸时的view所处的位置
    private float mDownX;//....
    private float mTotalDY;//移动总距离
    private int   mLocationX;//最初的view所在位置 相对屏幕
    private int   mLocationY;//最初的view所在位置 相对屏幕

    private int mMetricsWidth;//设备屏幕的宽
    private int mMetricsHeight;//。。。高
    private float mEndPositionY;//Y轴终点
    private float mRawX;
    private float mRawY;


    public DraggerView2(Context context) {
        this(context, null);
    }

    public DraggerView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DraggerView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        mMetricsWidth = WindowUtil.getMetricsWidth(getContext());
        mMetricsHeight = WindowUtil.getMetricsHeight(getContext());
        mEndPositionY = mMetricsHeight * 0.6f;//向下平移最底部的位置
        LogUtil.dd("mMetricsHeight="+mMetricsHeight);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量之后获取
        int[] location = new int[2];
        WindowUtil.getScreenPositon(this, location);
        //getLocationOnScreen(location);
        mLocationX = location[0];
        mLocationY = location[1];
        LogUtil.dd("mLocationY="+mLocationY);

    }
    //负责分发消息 ，可以直接写代替onTouchEvent代码
   /* @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.dd("dispatchTouchEvent");
        *//*switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getRawX();
                mDownY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveXf = ev.getRawX();
                float moveYf = ev.getRawY();
                float dy = moveYf - mDownY;
                LogUtil.dd("ev.getRawY()=" + ev.getRawY());

                int[] location=new int[2];
                WindowUtil.getScreenPositon(this, location);
                int locationY = location[1];// 再次测量view所在位置
                if (dy<0 && (dy+locationY) < mLocationY) {
                    dy=mLocationY-locationY;//设置拖动的上限
                }else if ((dy+locationY) >= mEndPositionY) {
                    dy=mEndPositionY-locationY;//设置拖动的下限
                }
                LogUtil.dd("dy=" + dy);
                mTotalDY = mTotalDY + dy;
                LogUtil.dd("locationY="+locationY+"mEndPositionY="+mEndPositionY);

                if (dy < 0 || locationY <= mEndPositionY) {
                                //向下拖动超过Y轴设置终点为最大，当超过终点此时向上拖动时也可以
                    dispatchAction(mTotalDY);
                }

                mDownY = moveYf;
                LogUtil.dd("mTotalDY=" + mTotalDY);

                break;
            case MotionEvent.ACTION_UP:
                //mTotalDY=0;
                break;
            default:
        }*//*

        //return true;
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mRawX = ev.getRawX();
                mRawY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float rawX = ev.getRawX();
                float rawY = ev.getRawY();
                LogUtil.dd("dispatch:rawX="+rawX+"mRawY"+mRawY);
                if (Math.abs(rawY-mRawY) >= Math.abs(rawX-mRawX)) {
                    mRawY=rawY;
                    mRawX=rawX;
                    return true;
                }
                break;
            default:
        }
        //return false;
        return super.dispatchTouchEvent(ev);
    }
*/
    /*//,只能负责拦截与否，不进行onTouchEvent代码处理
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.dd("onInterceptTouchEvent");
        return true;
    }*/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.dd("onInterceptTouchEvent");
        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mRawX = ev.getRawX();
                mRawY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float rawX = ev.getRawX();
                float rawY = ev.getRawY();
                LogUtil.dd("onIntercept:rawX="+rawX+"mRawY"+mRawY);
                if (Math.abs(rawY-mRawY) >= Math.abs(rawX-mRawX)) {
                    mRawY=rawY;
                    mRawX=rawX;
                    return true;
                }else if (Math.abs(rawX-mRawX)>5) {
                    int[] location=new int[2];
                    WindowUtil.getScreenPositon(this, location);
                    if(location[1] >= mEndPositionY-10){
                        //处于最低位置时，且x轴位移超过5
                        return true;
                    }
                }
                break;
            default:
        }
        return false;
    }


     //只有dispatchTouchEvent  true +onTouchEvent是不行的
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.dd("mRawX"+mRawX);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getRawX();
                mDownY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getRawX();
                float moveY = event.getRawY();
                float dy = moveY - mRawY;
                LogUtil.dd("moveY=" + moveY);

                int[] location=new int[2];
                WindowUtil.getScreenPositon(this, location);
                int locationY = location[1];// 再次测量view所在位置
                if (dy<0 && (dy+locationY) < mLocationY) {
                    dy=mLocationY-locationY;//设置拖动的上限
                }else if ((dy+locationY) >= mEndPositionY) {
                    dy=mEndPositionY-locationY;//设置拖动的下限
                }
                LogUtil.dd("dy=" + dy);
                mTotalDY = mTotalDY + dy;
                LogUtil.dd("locationY="+locationY+"mEndPositionY="+mEndPositionY);

                if (dy < 0 || locationY <= mEndPositionY) {
                    //向下拖动超过Y轴设置终点为最大，当超过终点此时向上拖动时也可以
                    dispatchAction(mTotalDY);
                }
                if (locationY >= mEndPositionY-10) {
                   //处在最低位置
                    float dx = moveX - mRawX;
                    if (Math.abs(dx) >= 5) {
                        //水平移动View
                        scrollHorzontical(dx);
                    }
                }
                mRawY = moveY;
                mRawX = moveX;
                LogUtil.dd("mTotalDY=" + mTotalDY);

                break;
            case MotionEvent.ACTION_UP:
                //mTotalDY=0;
                break;
            default:
        }

        return true;

    }

    //水平移动View
    private void scrollHorzontical(float dx) {
        if (dx > 0) {//向右移动
            this.setTranslationX(getWidth());
            this.setAlpha(0.4f);
        }else{
            //向左移动
            this.setTranslationX(-getWidth());
            this.setAlpha(0.4f);
        }
    }

    // 动画执行
    private void dispatchAction(float totalY) {


        //WindowUtil.getMetrics(getContext(),screenWidth,screenHeigth);

        float precent = totalY / mEndPositionY;
        //最好不要修改百分比 修改了后会导致跟手的动作不准确
        /*if (precent <= 0.f) {
            precent=0.f;
        }else if (precent >= 0.8f) {
            precent=0.8f;
        }*/
        LogUtil.dd("mMetricsHeight=" + mMetricsHeight);
        LogUtil.dd("precent=" + precent);
        this.setTranslationY(evaluate(precent, 0.f, mEndPositionY));
        this.setScaleX(evaluate(precent, 1.f, 0.5f));
        this.setScaleY(evaluate(precent, 1.f, 0.5f));
        //缩小了多少就平移／2
        LogUtil.dd("mMetricsWidth"+mMetricsWidth);
        //LogUtil.dd("(1-this.getScaleX())*getWidth()*0.5f="+(1-this.getScaleX())*getWidth()*0.5f);
        //this.setTranslationX(evaluate(precent, 0, (mMetricsWidth - this.getWidth()) * 0.5f));
       // LogUtil.dd("this.getScaleX()="+this.getScaleX());
        this.setTranslationX((1-this.getScaleX())*getWidth()*0.5f);

        //this.setFilterTouchesWhenObscured(false);

    }

    //估值计算方法
    public Float evaluate(float fraction, Number startValue, Number endValue) {
        float startFloat = startValue.floatValue();
        return startFloat + fraction * (endValue.floatValue() - startFloat);
    }

}
