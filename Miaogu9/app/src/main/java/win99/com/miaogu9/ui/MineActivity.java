package win99.com.miaogu9.ui;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.OnClick;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.util.Constant;
import win99.com.miaogu9.util.LocalInfo;
import win99.com.miaogu9.util.SpUtil;

public class MineActivity extends BaseActivity {


    @Bind(R.id.tool_textView)
    TextView         toolTextView;
    @Bind(R.id.main_toolbar)
    Toolbar          mainToolbar;
    @Bind(R.id.imageViewMore)
    ImageView        imageViewMore;
    @Bind(R.id.imageViewAvatar)
    SimpleDraweeView imageViewAvatar;
    @Bind(R.id.layoutAvatar)
    RelativeLayout   layoutAvatar;
    @Bind(R.id.imageViewMoreName)
    ImageView        imageViewMoreName;
    @Bind(R.id.textViewName)
    TextView         textViewName;
    @Bind(R.id.layoutName)
    RelativeLayout   layoutName;
    @Bind(R.id.activity_personal)
    LinearLayout     activityPersonal;
    @Bind(R.id.toolbar_back)
    ImageView        toolbarBack;

    @Override
    public int initLayout() {
        return R.layout.activity_mine;
    }

    @Override
    public void setData() {

        setToolbar();


        String iconUrl = SpUtil.getString(this, LocalInfo.DISPLAYURL);
        String nikeName = SpUtil.getString(this, LocalInfo.NIKENAME);
        imageViewAvatar.setImageURI(Constant.IMAGE_URL + iconUrl);
        textViewName.setText(nikeName);
    }

    @Override
    public void setListener() {


    }

    private void setToolbar() {
        toolTextView.setText(R.string.mine_name);
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineActivity.this.finish();
            }
        });
    }

    @OnClick({R.id.layoutAvatar, R.id.layoutName})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutAvatar:
                //startActivity(new Intent(this,));
                //弹出对话框,更改头像
                break;
            case R.id.layoutName:
                //设置昵称
                startActivity(new Intent(this, SetNameActivity.class));
                break;
        }
    }


}
