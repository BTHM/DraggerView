package win99.com.miaogu9.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author sanshu
 * @data 16/9/18 下午4:03
 * @ToDo ${TODO}
 */

public class MiaoGuApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //别忘了 fresco 一定要初始化
        Fresco.initialize(getApplicationContext());
    }
}
