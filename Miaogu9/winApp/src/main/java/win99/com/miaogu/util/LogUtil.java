package win99.com.miaogu.util;

/**
 * <p>
 * org.ancode.util.Log
 * </p>
 *
 * @author LU
 */
public class LogUtil {

    /*
     * Display: set showLog true Hide: set showLog false
     */
    public static boolean showLog = true;

    private static final String TAG = LogUtil.class.getName();

    public static void d(String tag, String msg) {
        if (showLog) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void dd(String msg) {
        if (showLog) {
            android.util.Log.d(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (showLog) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void ii(String msg) {
        if (showLog) {
            android.util.Log.i(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (showLog) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void ww(String msg) {
        if (showLog) {
            android.util.Log.w(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (showLog) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void ee( String msg) {
        if (showLog) {
            android.util.Log.e(TAG, msg);
        }
    }




    public static void vvv(boolean isLog, String tag, String msg) {
        if (isLog) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void iii(boolean isLog, String tag, String msg) {
        if (isLog) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void eee(boolean isLog, String tag, String msg) {
        if (isLog) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void www(boolean isLog, String tag, String msg) {
        if (isLog) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void ddd(boolean isLog, String tag, String msg) {
        if (isLog) {
            android.util.Log.d(tag, msg);
        }
    }

    public static class System {
        public static void out(String msg) {
            if (showLog)
                android.util.Log.i("System.out", msg);
        }
    }

}
