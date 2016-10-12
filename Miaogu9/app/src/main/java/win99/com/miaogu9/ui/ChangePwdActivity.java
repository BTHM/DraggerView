package win99.com.miaogu9.ui;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import win99.com.miaogu9.util.PakageUtil;
import win99.com.miaogu9.util.SpUtil;

public class ChangePwdActivity extends BaseActivity {


    @Bind(R.id.tool_textView)
    TextView       toolTextView;
    @Bind(R.id.main_toolbar)
    Toolbar        mainToolbar;
    @Bind(R.id.editTextOldPassword)
    EditText       editTextOldPassword;
    @Bind(R.id.layoutOldPassword)
    RelativeLayout layoutOldPassword;
    @Bind(R.id.editTextNewPassword)
    EditText       editTextNewPassword;
    @Bind(R.id.layoutNewPassword)
    RelativeLayout layoutNewPassword;
    @Bind(R.id.editTextCheckPassword)
    EditText       editTextCheckPassword;
    @Bind(R.id.layoutCheckPassword)
    RelativeLayout layoutCheckPassword;
    @Bind(R.id.done)
    Button         done;
    @Bind(R.id.activity_name)
    LinearLayout   activityName;
    @Bind(R.id.toolbar_back)
    ImageView      toolbarBack;
    private String mMemberId;
    private String mToken;
    private int    mVersionCode;
    private String mPwd;

    @Override
    public int initLayout() {
        return R.layout.activity_change_pwd;
    }

    @Override
    public void setData() {
        setToolbar();

        mMemberId = SpUtil.getString(this, LocalInfo.MEMBERID);
        mToken = SpUtil.getString(this, LocalInfo.TOKEN);
        mPwd = SpUtil.getString(this, LocalInfo.PASSWORD);
        mVersionCode = PakageUtil.getVersionCode(this);
    }

    @Override
    public void setListener() {
        editTextCheckPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String checkPwd = editTextCheckPassword.getText().toString().trim();
                    String newPwd = editTextNewPassword.getText().toString().trim();
                    if ((!TextUtils.isEmpty(checkPwd)) && TextUtils.equals(checkPwd, newPwd)) {
                        //使按钮生效
                        done.setClickable(true);
                    }
                }
            }
        });

    }


    private void setToolbar() {
        toolTextView.setText("热评详情");
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePwdActivity.this.finish();
            }
        });
    }

    @OnClick(R.id.done)
    public void onClick() {
        String oldPwd = editTextOldPassword.getText().toString().trim();
        String newPasswd = editTextNewPassword.getText().toString().trim();
        String checkPasswd = editTextCheckPassword.getText().toString().trim();
        if (TextUtils.isEmpty(oldPwd) && TextUtils.isEmpty(newPasswd)
                && TextUtils.equals(newPasswd, checkPasswd)) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("newPasswd", newPasswd);
            hashMap.put("passwd", oldPwd);
            hashMap.put("memberId", mMemberId);
            hashMap.put("version", mVersionCode + "");
            hashMap.put("token", mToken);

            NetWorkApi.changePwd(hashMap, new NetWorkApi.OnResultListener<AccountInfo>() {
                @Override
                public void onSuccess(int code, AccountInfo result) {
                    if (code == 200) {
                        /*try {
                            String string = result.string();
                            JSONObject jsonObject = new JSONObject();
                            String state = (String) jsonObject.get("State");
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }


    }

}
