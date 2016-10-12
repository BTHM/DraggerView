package win99.com.miaogu9.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.util.PakageUtil;

public class AboutActivity extends BaseActivity {


    @Bind(R.id.tool_textView)
    TextView       toolTextView;
    @Bind(R.id.main_toolbar)
    Toolbar        mainToolbar;
    @Bind(R.id.icon)
    ImageView      icon;
    @Bind(R.id.appname)
    TextView       appname;
    @Bind(R.id.version)
    TextView       version;
    @Bind(R.id.wechat)
    TextView       wechat;
    @Bind(R.id.company)
    TextView       company;
    @Bind(R.id.companyen)
    TextView       companyen;
    @Bind(R.id.layoutAbout)
    RelativeLayout layoutAbout;
    @Bind(R.id.activity_about)
    LinearLayout   activityAbout;
    @Bind(R.id.toolbar_back)
    ImageView      toolbarBack;

    @Override
    public int initLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void setData() {

        toolTextView.setText(R.string.about_name);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutActivity.this.finish();//
            }
        });
        version.setText("v" + PakageUtil.getVersionCode(this));
    }

    @Override
    public void setListener() {

    }


}
