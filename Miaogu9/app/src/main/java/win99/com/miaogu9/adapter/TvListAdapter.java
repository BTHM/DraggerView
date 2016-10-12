package win99.com.miaogu9.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import win99.com.miaogu9.R;
import win99.com.miaogu9.domain.TvInfo;
import win99.com.miaogu9.util.Constant;

/*
*
 * Created by pangweiwei on 16/7/21.

*/


public class TvListAdapter extends BaseAdapter {

    private List<TvInfo> mDatas;
    private Context      mContent;


    public TvListAdapter(Context context, List<TvInfo> datas) {
        this.mDatas = datas;
        this.mContent = context;
    }

    @Override
    public int getCount() {

        return mDatas.size();
    }

    @Override
    public TvInfo getItem(int position) {

        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContent, R.layout.item_tv_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        TvInfo tvInfo = mDatas.get(position);
        //设置图片
        Glide.with(mContent).load(Constant.IMAGE_URL+tvInfo.getAttach()
                .get(0).getAttr_url()).into(holder.ivTvThumb);
        holder.tvTvTitle.setText(tvInfo.getTitle());
        //点喜欢个数
        holder.tvGoodNum.setText(tvInfo.getAnlysis().get(1).getCount_num()+"");
        //显示喜欢的图 应该由用户登陆后 取本地信息来实现同步 和点击事件

        //收藏的个数
        holder.tvSaveNum.setText(tvInfo.getAnlysis().get(2).getCount_num()+"");
        //视频讲解人
        holder.tvName.setText(tvInfo.getAuthorName());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.iv_tv_thumb)
        ImageView   ivTvThumb;
        @Bind(R.id.tv_tv_title)
        TextView    tvTvTitle;
        @Bind(R.id.ib_tv_good)
        ImageButton ibTvGood;
        @Bind(R.id.tv_good_num)
        TextView    tvGoodNum;
        @Bind(R.id.ib_tv_save)
        ImageButton ibTvSave;
        @Bind(R.id.tv_save_num)
        TextView    tvSaveNum;
        @Bind(R.id.iv_tv_avatar)
        ImageView   ivTvAvatar;
        @Bind(R.id.tv_name)
        TextView    tvName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /*private void operateVideoAsync(String infoId, FragmentTV.OpreatType type) {
        SharedPreferences settings = context.getSharedPreferences("shared", MODE_PRIVATE);
        Boolean isDeviceRegisted = settings.getBoolean("isDeviceRegisted", false);
        String mobilId;
        if (isDeviceRegisted) {
            mobilId = settings.getString("mobileId", "");
        } else {
            return;
        }

        String opreteType = "PV";
        switch (type) {
            case LIKE:
                opreteType = "LIKE";
                break;
            case UNLIKE:
                opreteType = "UNLIKE";
                break;
            case FAVORITE:
                opreteType = "FAVORITE";
                break;
            case UNFAVORITE:
                opreteType = "UNFAVORITE";
                break;
            case PV:
                opreteType = "PV";
                break;
        }

        RequestParams params = new RequestParams();
        params.put("mobileId", mobilId);
        params.put("contentId", infoId);
        if (mMiaoGuApp.isLogin()) {
            params.put("memberId", ((MiaoGuApplication) context.getApplicationContext()).getMemberId());
        }
        params.put("work", opreteType);
        params.put("version", Utility.getVersionName(context));

        Log.e("RESTFULAPI", "get operate video");

        MiaoGuInfoRestClient.get("/group/lr/sj", params, new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.e("RESTFULAPI", "success json object:" + response);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("RESTFULAPI", "failure 1");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("RESTFULAPI", "failure 2" + statusCode + headers + responseString + throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("RESTFULAPI", "failure 3" + statusCode + headers + throwable + errorResponse);
            }
        });

    }*/
}
