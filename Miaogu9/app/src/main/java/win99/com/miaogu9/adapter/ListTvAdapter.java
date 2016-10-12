package win99.com.miaogu9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import win99.com.miaogu9.app.MiaoGuApplication;
import win99.com.miaogu9.domain.TvInfo;

/*
*
 * Created by pangweiwei on 16/7/21.

*/


public class ListTvAdapter extends BaseAdapter {

    private Context           context;
    private List<TvInfo>      mDatas;
    private TvInfo           tempListViewItem;
    private LayoutInflater    layoutInflater;
    private MiaoGuApplication mMiaoGuApp;

    private Map<String, String> tvLocal;
    private Map<String, String> goodTV;
    private Map<String, String> collectionTV;


    public ListTvAdapter(Context context, List<TvInfo> datas) {
        this.mDatas = datas;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        mMiaoGuApp = ((MiaoGuApplication) context.getApplicationContext());
    }

    @Override
    public int getCount() {

        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {

        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        /*ViewHolder holder;

        if (layoutInflater == null) {
            return convertView;
        }

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_tv_list, null);
            holder = new ViewHolder();

            holder.thumb = (ImageView) convertView.findViewById(R.id.iv_tv_thumb);
            holder.title = (TextView) convertView.findViewById(R.id.tv_tv_title);
            holder.goodButton = (ImageButton) convertView.findViewById(R.id.ib_tv_good);
            holder.goodNumber = (TextView) convertView.findViewById(R.id.tv_good_num);
            holder.saveButton = (ImageButton) convertView.findViewById(R.id.ib_tv_save);
            holder.saveNumber = (TextView) convertView.findViewById(R.id.tv_save_num);
            holder.avatar = (ImageView) convertView.findViewById(R.id.iv_tv_avatar);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.title.setText(null);
            holder.goodNumber.setText(null);
            holder.saveNumber.setText(null);
            holder.name.setText(null);
        }

        //
        TvInfo tvInfo = mDatas.get(position);
        TvInfo.AttachBean attachBean = tvInfo.getAttach().get(0);


        //加载图片
        Glide.with(context).load(Constant.IMAGE_URL+attachBean.getAttr_url()).into(holder.thumb);

        holder.title.setText(tvInfo.getTitle());




if (tempListViewItem.isGood) {
            holder.goodButton.setImageResource(R.mipmap.praise_iconpressed);
        } else {
            holder.goodButton.setImageResource(R.mipmap.praise_icon);
        }

        holder.goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if (mMiaoGuApp.isLogin()) {
                    tempListViewItem = mDatas.get(position);

                    String playTVId = tempListViewItem.infoId + "";

                    goodTV = mMiaoGuApp.getGoodData();

                    if (goodTV == null || goodTV.isEmpty()) {
                        Log.e("APP", "get good list empty ");
                        mMiaoGuApp.addGoodData(playTVId, playTVId);
                    } else {
                        Log.e("APP", "playTV Id :" + playTVId);

                        if (goodTV.containsKey(playTVId)) {
                            mMiaoGuApp.removeGoodData(playTVId);
                        } else {
                            mMiaoGuApp.addGoodData(playTVId, playTVId);
                        }
                    }


                    if (!tempListViewItem.isGood) {
                        tempListViewItem.isGood = true;
                        tempListViewItem.good++;
                        operateVideoAsync(tempListViewItem.infoId + "", FragmentTV.OpreatType.LIKE);
                    } else {
                        tempListViewItem.isGood = false;
                        tempListViewItem.good--;
                        operateVideoAsync(tempListViewItem.infoId + "", FragmentTV.OpreatType.UNLIKE);
                    }
                    mDatas.set(position, tempListViewItem);
                    notifyDataSetChanged();
                    //Toast.makeText(context, " Click on good " + position, Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }

            }
        });

        int count_numPV = tvInfo.getAnlysis().get(0).getCount_num();
        int count_numLike = tvInfo.getAnlysis().get(1).getCount_num();

        holder.goodNumber.setText(count_num+"");


        if (tempListViewItem.isSaved) {
            holder.saveButton.setImageResource(R.mipmap.tv_collect_iconpressed);
        } else {
            holder.saveButton.setImageResource(R.mipmap.tv_collect_icon);
        }
        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if (mMiaoGuApp.isLogin()) {

                    tempListViewItem = mDatas.get(position);

                    String playTVId = tempListViewItem.infoId + "";

                    collectionTV = mMiaoGuApp.getCollectionData();
                    tvLocal = mMiaoGuApp.getLocalData();

                    if (collectionTV == null || collectionTV.isEmpty()) {
                        Log.e("APP", "get collction list empty ");
                        mMiaoGuApp.addCollectionData(playTVId, tvLocal.get(playTVId));
                    } else {

                        Log.e("APP", "playTV Id :" + playTVId);

                        if (collectionTV.containsKey(playTVId)) {
                            mMiaoGuApp.removeCollectionData(playTVId);
                        } else {
                            mMiaoGuApp.addCollectionData(playTVId, tvLocal.get(playTVId));
                        }
                    }

                    if (!tempListViewItem.isSaved) {
                        tempListViewItem.isSaved = true;
                        tempListViewItem.save++;
                        operateVideoAsync(tempListViewItem.infoId + "", FragmentTV.OpreatType.FAVORITE);
                    } else {
                        tempListViewItem.isSaved = false;
                        tempListViewItem.save--;
                        operateVideoAsync(tempListViewItem.infoId + "", FragmentTV.OpreatType.UNFAVORITE);
                    }
                    mDatas.set(position, tempListViewItem);
                    notifyDataSetChanged();
                    //Toast.makeText(context, " Click on save", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }


            }
        });

        holder.saveNumber.setText(tempListViewItem.save + "");

        //holder.avatar.setImageBitmap(tempListViewItem.avatar);

        holder.name.setText(tempListViewItem.name);*/

        return convertView;
    }

    private class ViewHolder {
        public ImageView   thumb;
        public TextView    title;
        public ImageButton goodButton;
        public TextView    goodNumber;
        public ImageButton saveButton;
        public TextView    saveNumber;
        public ImageView   avatar;
        public TextView    name;
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
