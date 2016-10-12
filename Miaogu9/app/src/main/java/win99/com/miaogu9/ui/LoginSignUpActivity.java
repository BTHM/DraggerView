package win99.com.miaogu9.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.domain.RegisterInfo;
import win99.com.miaogu9.domain.UserInfo;
import win99.com.miaogu9.net.NetWorkApi;
import win99.com.miaogu9.util.LocalInfo;
import win99.com.miaogu9.util.LogUtil;
import win99.com.miaogu9.util.PakageUtil;
import win99.com.miaogu9.util.SpUtil;
import win99.com.miaogu9.util.ToastUtil;


public class LoginSignUpActivity extends BaseActivity {


    @Bind(R.id.topTitle)
    TextView       topTitle;
    @Bind(R.id.layoutBanner)
    RelativeLayout layoutBanner;
    @Bind(R.id.buttonTabLogin)
    Button         buttonTabLogin;
    @Bind(R.id.tabBarLogin)
    View           tabBarLogin;
    @Bind(R.id.buttonTabRegister)
    Button         buttonTabRegister;
    @Bind(R.id.tabBarRegister)
    View           tabBarRegister;
    @Bind(R.id.layoutBannerBar)
    RelativeLayout layoutBannerBar;
    @Bind(R.id.imageViewPhone)
    ImageView      imageViewPhone;
    @Bind(R.id.editTextPhone)
    EditText       editTextPhone;
    @Bind(R.id.imageButtonClearPhone)
    ImageButton    imageButtonClearPhone;
    @Bind(R.id.phoneBar)
    View           phoneBar;
    @Bind(R.id.layoutPhone)
    RelativeLayout layoutPhone;
    @Bind(R.id.imageViewPassword)
    ImageView      imageViewPassword;
    @Bind(R.id.editTextPassword)
    EditText       editTextPassword;
    @Bind(R.id.imageButtonSeePassword)
    ImageButton    imageButtonSeePassword;
    @Bind(R.id.passwordBar)
    View           passwordBar;
    @Bind(R.id.layoutPassword)
    RelativeLayout layoutPassword;
    @Bind(R.id.forgetPassword)
    Button         forgetPassword;
    @Bind(R.id.login)
    Button         login;
    @Bind(R.id.imageViewSafe)
    ImageView      imageViewSafe;
    @Bind(R.id.layoutSafe)
    RelativeLayout layoutSafe;
    @Bind(R.id.layoutLogin)
    RelativeLayout layoutLogin;
    @Bind(R.id.imageViewPhoneReg)
    ImageView      imageViewPhoneReg;
    @Bind(R.id.editTextPhoneReg)
    EditText       editTextPhoneReg;
    @Bind(R.id.imageButtonClearPhoneReg)
    ImageButton    imageButtonClearPhoneReg;
    @Bind(R.id.phoneBarReg)
    View           phoneBarReg;
    @Bind(R.id.layoutPhoneReg)
    RelativeLayout layoutPhoneReg;
    @Bind(R.id.imageViewCode)
    ImageView      imageViewCode;
    @Bind(R.id.editTextCode)
    EditText       editTextCode;
    @Bind(R.id.buttonGetCode)
    Button         buttonGetCode;
    @Bind(R.id.imageButtonClearCode)
    ImageButton    imageButtonClearCode;
    @Bind(R.id.codeBar)
    View           codeBar;
    @Bind(R.id.layoutCode)
    RelativeLayout layoutCode;
    @Bind(R.id.imageViewPasswordReg)
    ImageView      imageViewPasswordReg;
    @Bind(R.id.editTextPasswordReg)
    EditText       editTextPasswordReg;
    @Bind(R.id.imageButtonSeePasswordReg)
    ImageButton    imageButtonSeePasswordReg;
    @Bind(R.id.passwordBarReg)
    View           passwordBarReg;
    @Bind(R.id.layoutPasswordReg)
    RelativeLayout layoutPasswordReg;
    @Bind(R.id.termText)
    TextView       termText;
    @Bind(R.id.buttonTermReg)
    Button         buttonTermReg;
    @Bind(R.id.layoutTerm)
    RelativeLayout layoutTerm;
    @Bind(R.id.register)
    Button         register;
    @Bind(R.id.imageViewSafeReg)
    ImageView      imageViewSafeReg;
    @Bind(R.id.layoutSafeReg)
    RelativeLayout layoutSafeReg;
    @Bind(R.id.layoutRegister)
    RelativeLayout layoutRegister;
    @Bind(R.id.topBack)
    ImageButton    topBack;
    @Bind(R.id.layoutParent)
    RelativeLayout layoutParent;
    @Bind(R.id.activity_name)
    LinearLayout   activityName;
    private Map<String, String> map;
    private Map<String, String> mapReg;
    private String TAG=LoginSignUpActivity.class.getName();

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setData() {
        if (map == null) {
            map=new HashMap<>();
        }
        if (mapReg == null) {
            mapReg=new HashMap<>();
        }
        mapReg.put("version",PakageUtil.getVersionCode(this)+"");
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.login,R.id.buttonTabRegister,R.id.buttonGetCode,
            R.id.imageButtonClearPhoneReg,R.id.imageButtonClearPhone,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                //登陆
                login();
            case R.id.buttonTabRegister:
                //切换到注册界面
                layoutRegister.setVisibility(View.VISIBLE);
                layoutLogin.setVisibility(View.INVISIBLE);
            case R.id.buttonTabLogin:
                //切换到登陆界面
                layoutLogin.setVisibility(View.VISIBLE);
                layoutRegister.setVisibility(View.INVISIBLE);
            case R.id.buttonGetCode:
                getSmsCode();
            case R.id.imageButtonClearPhoneReg:
                //注册 清除phone输入
                editTextPhoneReg.setText("");
            case R.id.imageButtonClearPhone:
                //登录 phone清除输入
                editTextPhone.setText("");
            case R.id.register:
                register();
            default:
        }
    }

    private void getSmsCode() {
        String phone = editTextPhone.getText().toString().trim();
        if (phone.length() == 11) {
            Map<String,String> smsMap=new HashMap();
                smsMap.put("mobileId","12134567890");
                smsMap.put("mobile",phone);
            NetWorkApi.getSmsCode(smsMap, new NetWorkApi.OnResultListener<RegisterInfo>() {
                @Override
                public void onSuccess(int code, RegisterInfo result) {
                    if (code == 200) {
                        //TODO
                        ToastUtil.showShort(LoginSignUpActivity.this,"已发送验证码");
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }else{
            ToastUtil.showShort(this,"输入正确格式的手机号");
        }
    }


    private void login() {
        String phone = editTextPhone.getText().toString().trim();
        final String pwd = editTextPassword.getText().toString().trim();
        if (phone.length() != 11) {
            ToastUtil.showShort(this,"请输入11位的手机号码");
        }else if (!TextUtils.isEmpty(pwd)) {
            map.put("mobile",phone);
            map.put("passwd",pwd);
            map.put("mobileId","1234567890");
            NetWorkApi.login(map, new NetWorkApi.OnResultListener<UserInfo>() {
                @Override
                public void onSuccess(int code, UserInfo result) {
                    if (code == 200) {
                        //网络成功访问
                        LogUtil.d(TAG,"result="+result);
                        SpUtil.putString(LoginSignUpActivity.this,LocalInfo.PASSWORD,pwd);
                        //保存用户信息
                        saveUserInfo(result);
                        //startActivity(new Intent(LoginSignUpActivity.this, FragmentMine.class));
                        LoginSignUpActivity.this.finish();//结束当前activity
                    }else{

                    }

                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }
    //注册
    private void register() {
        String phoneReg = editTextPhoneReg.getText().toString().trim();
        String codeReg = editTextCode.getText().toString().trim();
        String pwdReg = editTextPasswordReg.getText().toString().trim();
        mapReg.put("mobile",phoneReg);
        mapReg.put("passwd",pwdReg);
        mapReg.put("code",codeReg);
        mapReg.put("mobileId","123456890");

        String function="register";//netApi 功能解析
        if ((!TextUtils.isEmpty(phoneReg)) && (!TextUtils.isEmpty(codeReg))&& (!TextUtils.isEmpty(pwdReg))){
            NetWorkApi.register(function,mapReg, new NetWorkApi.OnResultListener<RegisterInfo>() {
                @Override
                public void onSuccess(int code, RegisterInfo result) {
                    if (code == 200) {
                        //TODO
                        ToastUtil.showShort(LoginSignUpActivity.this,result.getMessage());
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }




    /*//用户信息private String displayUrl;
    private String memberInfo;
    private String mobileId;
    private String expiractionTime;
    private String nikeName;
    private String state;
    private String message;
    private String memberId;
    private String token;*/
    private void saveUserInfo(UserInfo result) {
        SpUtil.putString(this,LocalInfo.DISPLAYURL,result.getDisplayUrl());
        SpUtil.putString(this,LocalInfo.MEMBERINFO,result.getMemberInfo());
        SpUtil.putString(this,LocalInfo.MOBILEID,result.getMobileId());
        SpUtil.putString(this,LocalInfo.EXPIRACTIONTIME,result.getExpiractionTime());
        SpUtil.putString(this,LocalInfo.NIKENAME,result.getNikeName());
        SpUtil.putString(this,LocalInfo.STATE,result.getState());
        SpUtil.putString(this,LocalInfo.MESSAGE,result.getMessage());
        SpUtil.putString(this,LocalInfo.MEMBERID,result.getMemberId());
        SpUtil.putString(this,LocalInfo.TOKEN,result.getToken());
    }
}
