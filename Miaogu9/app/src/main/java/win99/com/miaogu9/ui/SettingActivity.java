package win99.com.miaogu9.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.domain.AccountInfo;
import win99.com.miaogu9.net.NetWorkApi;
import win99.com.miaogu9.util.LocalInfo;
import win99.com.miaogu9.util.SpUtil;

public class SettingActivity extends BaseActivity {


    @Bind(R.id.tool_textView)
    TextView       toolTextView;
    @Bind(R.id.main_toolbar)
    Toolbar        mainToolbar;
    @Bind(R.id.layoutChangePassword)
    RelativeLayout layoutChangePassword;
    @Bind(R.id.imageViewMoreQuestion)
    ImageView      imageViewMoreQuestion;
    @Bind(R.id.layoutSystemPermission)
    RelativeLayout layoutSystemPermission;
    @Bind(R.id.imageViewMoreCollection)
    ImageView      imageViewMoreCollection;
    @Bind(R.id.layoutRate)
    RelativeLayout layoutRate;
    @Bind(R.id.imageViewMoreSetting)
    ImageView      imageViewMoreSetting;
    @Bind(R.id.layoutAbout)
    RelativeLayout layoutAbout;
    @Bind(R.id.layoutLogout)
    RelativeLayout layoutLogout;
    @Bind(R.id.activity_name)
    LinearLayout   activityName;
    @Bind(R.id.toolbar_back)
    ImageView      toolbarBack;
    private HashMap<String, String> mHashMap;

    @Override
    public int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void setData() {
        mHashMap = new HashMap<>();
        String memberId = SpUtil.getString(this, LocalInfo.MEMBERID);
        String token = SpUtil.getString(this, LocalInfo.TOKEN);
        mHashMap.put("memberId", memberId);
        mHashMap.put("token", token);
    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.layoutChangePassword, R.id.layoutAbout, R.id.layoutLogout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutChangePassword:
                //修改密码
                startActivity(new Intent(this, ChangePwdActivity.class));
                break;
            case R.id.layoutAbout:
                //关于我们
                startActivity(new Intent(this, ChangePwdActivity.class));
                break;
            case R.id.layoutLogout:
                //退出登录
                logOut();
                break;
        }
    }

    private void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出登录");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //退出
                NetWorkApi.logout(mHashMap, new NetWorkApi.OnResultListener<AccountInfo>() {
                    @Override
                    public void onSuccess(int code, AccountInfo result) {
                        //TODO
                        startActivity(new Intent(SettingActivity.this, LoginSignUpActivity.class));
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });


            }
        });
        builder.create().show();//秀出对话框
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //dialog();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private void setToolbar() {
        toolTextView.setText("设置");
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });
    }


}
