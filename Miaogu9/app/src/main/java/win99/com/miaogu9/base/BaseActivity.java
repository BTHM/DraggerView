package win99.com.miaogu9.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author sanshu
 * @data 2016/8/31 9:53
 * @ToDo ${TODO}
 */

public abstract class BaseActivity extends AppCompatActivity implements UIOparetion {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(initLayout());
        ButterKnife.bind(this);

        setData();

        setListener();
    }

    
    
}
