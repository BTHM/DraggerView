package win99.com.miaogu9.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.domain.AccountInfo;
import win99.com.miaogu9.net.NetWorkApi;
import win99.com.miaogu9.util.LocalInfo;
import win99.com.miaogu9.util.SpUtil;
import win99.com.miaogu9.util.ToastUtil;

public class SetNameActivity extends BaseActivity {


    @Bind(R.id.topBack)
    ImageButton    topBack;
    @Bind(R.id.topTitle)
    TextView       topTitle;
    @Bind(R.id.topSave)
    Button         topSave;
    @Bind(R.id.layoutTopBar)
    LinearLayout   layoutTopBar;
    @Bind(R.id.editTextName)
    EditText       editTextName;
    @Bind(R.id.layoutName)
    RelativeLayout layoutName;
    @Bind(R.id.activity_name)
    LinearLayout   activityName;
    private Map<String, String> mMap;

    @Override
    public int initLayout() {
        return R.layout.activity_set_name;
    }

    @Override
    public void setData() {
        mMap = new HashMap<>();

    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.topBack,R.id.topSave})
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.topBack:
                this.finish();//结束本界面
                break;
            case R.id.topSave:
                postNewName();
                break;
            default:
        }
    }

    private void postNewName() {
        String name = editTextName.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            if (name.length() > 8) {
                ToastUtil.showShort(this,"超过长度");
            }else{
                String memberId = SpUtil.getString(SetNameActivity.this, LocalInfo.MEMBERID);
                String token = SpUtil.getString(SetNameActivity.this, LocalInfo.TOKEN);

                mMap.put("memberId",memberId);
                mMap.put("token",token);
                mMap.put("nikeName",name);
                NetWorkApi.reName(mMap, new NetWorkApi.OnResultListener<AccountInfo>() {
                    @Override
                    public void onSuccess(int code, AccountInfo result) {
                        if (code == 200) {
                            //TODO
                            ToastUtil.showShort(SetNameActivity.this,"修改成功");
                            SetNameActivity.this.finish();//返回
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        }

    }
}
