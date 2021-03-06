package win99.com.miaogu.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import win99.com.miaogu.util.LogUtil;
import win99.com.miaogu.util.WindowUtil;

/**
 * @author sanshu
 * @data 2016/10/10 下午1:21
 * @ToDo ${TODO}
 */

public class DraggerView extends LinearLayout {



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
    private boolean isDismissAction;//是否是消失动作
    private Scroller mScroller;//只用来进行数据的模拟，而不做任何滚动操作


    public DraggerView(Context context) {
        this(context, null);
    }

    public DraggerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DraggerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mScroller = new Scroller(getContext());
        mMetricsWidth = WindowUtil.getMetricsWidth(getContext());
        mMetricsHeight = WindowUtil.getMetricsHeight(getContext());
        mEndPositionY = mMetricsHeight * 0.4f;//向下平移最底部的位置
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
                }else if (Math.abs(rawY-mRawY) < Math.abs(rawX-mRawX) && Math.abs(rawX-mRawX)>5) {
                    int[] location=new int[2];
                    WindowUtil.getScreenPositon(this, location);
                    LogUtil.dd("location[0]="+location[0]+"location[1]="+location[1]);
                    if(location[1] >= mEndPositionY-10){
                        //处于最低位置时，且x轴位移超过5
                        isDismissAction=true;
                        return true;
                    }
                }
                break;
            default:
        }
        isDismissAction=false;
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
                //此时的down事件已被 onIntercept事件获取
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getRawX();
                float moveY = event.getRawY();
                float dy = moveY - mRawY;
                LogUtil.dd("moveY=" + moveY);
               // LogUtil.dd("getScrollX()"+getScrollX()+"getScrollY()"+getScrollY());
                int[] location=new int[2];
                WindowUtil.getScreenPositon(this, location);
                int locationY = location[1];// 再次测量view所在位置
                if (dy<0 && (dy+locationY) < mLocationY) {
                    dy=mLocationY-locationY;//设置拖动的上限
                }else if ((dy+locationY) >= mEndPositionY) {
                    dy=mEndPositionY-locationY;//设置拖动的下限
                }
                LogUtil.dd("dy=" + dy);
                if (isDismissAction) {
                    //是水平动作
                    float dx = moveX - mRawX;
                    scrollHorzontical(dx,location);
                    mRawY = moveY;
                    mRawX = moveX;
                    return true;
                }
                mTotalDY = mTotalDY + dy;
                //LogUtil.dd("locationY="+locationY+"mEndPositionY="+mEndPositionY);

                if (dy < 0 || locationY <= mEndPositionY) {
                    //向下拖动超过Y轴设置终点为最大，当超过终点此时向上拖动时也可以
                    dispatchAction(mTotalDY);
                }
                if (locationY >= mEndPositionY-10) {
                   //处在最低位置

                }
                mRawY = moveY;
                mRawX = moveX;
                LogUtil.dd("mTotalDY=" + mTotalDY);

                break;
            case MotionEvent.ACTION_UP:
                //mTotalDY=0;
                //抬起后横向移动判断标志 设置清除
                //isDismissAction=false;

                //判断手抬起后view所处的位置
                int[] position=new int[2];
                WindowUtil.getScreenPositon(this,position);
                //scrollView(position);


                //setView(position);

                break;
            default:
        }

        return true;

    }


    private void setViewPosition(int[] position){
        int posY = position[1];
        if (posY <= mEndPositionY*0.5f) {
            while(mTotalDY > 1){
                SystemClock.sleep(2);
                LogUtil.dd("posY="+posY);
                dispatchAction(mTotalDY-=posY/100);
            }
        }else{
            while(mTotalDY <= mEndPositionY){
                SystemClock.sleep(2);
                LogUtil.dd("posY="+posY);
                dispatchAction(mTotalDY += posY/100);
            }
        }
    }

    private void setView(int[] position) {
        int posX = position[0];
        int posY = position[1];

        if (posY < mEndPositionY * 0.5f) {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(this, TRANSLATION_Y, 0,0, -(posY-mLocationY));
            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, TRANSLATION_X, 0,0, -posX);
            //ObjectAnimator translationX = ObjectAnimator.ofFloat(this, SCALE_X, 0,0, -posX);


            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(2000);
            animatorSet.playTogether(translationY,translationX);
            animatorSet.start();


        }
    }

    /**
     * @param position
     */
    private void scrollView(int[] position) {
        int posX = position[0];
        int posY = position[1];
        LogUtil.dd("posY="+posY+"mEndPositionY* 0.5f="+mEndPositionY* 0.5f);
        if (posY <= mEndPositionY* 0.5f) {
            int dx=mLocationX - posX;
            //int dy=mLocationY - posY;
            mScroller.startScroll(posX,posY,dx,0, Math.abs(dx)*2);//开始模拟数据
        }/*else{
            int dx= (int) ((getWidth()*0.7f) - posX);
            int dy= (int) (mEndPositionY - posY);
            mScroller.startScroll(posX,posY,dx,dy, Math.abs(dx)*2);//开始模拟数据
        }*/
        invalidate();
    }


    //与invalidate进行配合使用的方法,invalidate执行一次，computeScroll就执行一次
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            //获取当前的模拟的值，并且让界面滑动
            int currX = mScroller.getCurrX();//获取当前正在模拟的x轴的值
            //mScroller.getCurrX()
            int currY = mScroller.getCurrY();
            int[] position=new int[2];
            WindowUtil.getScreenPositon(this,position);/**/
            LogUtil.dd("currx:"+currX);
            scrollTo(position[0],0);//将界面不断得进行移动到绝对的位置上，来实现，界面的缓慢滑动效果
            invalidate();
        }
    }


    //水平移动View
    private void scrollHorzontical(float dx,int[] location) {
//        LogUtil.dd("scrollHorzontical"+"dx="+dx);
//        int posX = location[0];
//        int posY = location[1];
        if (dx > 0) {//向右移动
            //tween 动画 AlphaAnimation  实现不了 必须用属性动画

            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", 0, this.getWidth());
            ObjectAnimator alpha = ObjectAnimator.ofFloat(this, "Alpha",1.0f, 0.2f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translationX,alpha);
            animatorSet.setDuration(2000);
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //单次动画执行完毕才可进入
                    isDismissAction=false;
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                }
                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });
            LogUtil.dd("startAnimation"+"向右移动"+dx);
        }else{//向左移动
            LogUtil.dd("-this.getWidth()"+this.getWidth());


            ObjectAnimator translationX = ObjectAnimator.ofFloat(this, "translationX", 0, -this.getWidth());
            ObjectAnimator alpha = ObjectAnimator.ofFloat(this, "Alpha",1.0f, 0.2f);
            //tv2TranslateY.setStartDelay(2000);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translationX,alpha);
            animatorSet.setDuration(2000);
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //单次动画执行完毕才可进入
                    isDismissAction=false;
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            });

            LogUtil.dd("startAnimation"+"向左移动"+dx);
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
        this.setScaleX(evaluate(precent, 1.f, 0.2f));
        this.setScaleY(evaluate(precent, 1.f, 0.3f));
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
