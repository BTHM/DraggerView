package win99.com.miaogu9.ui;


import android.content.Intent;
import android.os.Handler;

import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    public int initLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void setData() {

    }

    @Override
    public void setListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.activity_right_enter,R.anim.activity_left_exit);

                //overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
            }
        },1000);
    }
}