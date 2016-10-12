package win99.com.miaogu;

import android.app.Application;
import android.content.Context;

/**
 * @author sanshu
 * @data 16/10/8 下午2:54
 * @ToDo ${TODO}
 */

public class MiaoGuApp extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (sContext == null) {
            sContext = getApplicationContext();
        }

    }
}
