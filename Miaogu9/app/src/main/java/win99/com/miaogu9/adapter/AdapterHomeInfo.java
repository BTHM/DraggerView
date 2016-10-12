package win99.com.miaogu9.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import win99.com.miaogu9.R;
import win99.com.miaogu9.domain.ModelHomeInfo;
import win99.com.miaogu9.library.BaseBannerAdapter;
import win99.com.miaogu9.library.VerticalBannerView;
import win99.com.miaogu9.ui.WebActivity;
import win99.com.miaogu9.util.Constant;

/**
 * Created by AdapterHomeInfo 16/7/21.
 */

public class AdapterHomeInfo extends BaseBannerAdapter<ModelHomeInfo> {
    private List<ModelHomeInfo> mDatas;
    private Activity            mActivity;

    public AdapterHomeInfo(Activity activity, List<ModelHomeInfo> datas) {
        super(datas);
        mActivity = activity;
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_info,null);
    }

    @Override
    public void setItem(final View view, final ModelHomeInfo data) {
        TextView tv = (TextView) view.findViewById(R.id.textview);
        tv.setText(data.title);
        //你可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比如打开url
                Intent intent = new Intent(mActivity, WebActivity.class);
                intent.putExtra(Constant.SOURCE_FLAG,"AdapterHomeInfo");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra(Constant.INTENT_DATA,data);
                mActivity.startActivity(intent);
            }
        });
    }
}
