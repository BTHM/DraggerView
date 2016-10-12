package win99.com.miaogu.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * @author sanshu
 * @data 2016/10/11 上午11:05
 * @ToDo ${TODO}
 */

public class WindowUtil {

    /**
     * @param context 获取屏幕的宽
     *
     */
    public static int getMetricsWidth(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetris=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetris);
        return outMetris.widthPixels;
    }

    /**
     * @param context
     * @return  获取屏幕的高
     */
    public static int getMetricsHeight(Context context){
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetris=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetris);
        return outMetris.heightPixels;
    }
    /**
     * @param view
     * @param location  =new int[2];
     */
    public static void getScreenPositon(View view,int[] location){
        view.getLocationOnScreen(location);
    }
}
