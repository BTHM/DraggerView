package win99.com.miaogu9.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import win99.com.miaogu9.R;
import win99.com.miaogu9.domain.ModelHotAdviser;
import win99.com.miaogu9.ui.AdviserDetailActivity;
import win99.com.miaogu9.util.Constant;
import win99.com.miaogu9.util.LogUtil;


/**
 * Created by pangweiwei on 16/8/4.
 */

public class AdapterHotAdviser extends RecyclerView.Adapter<AdapterHotAdviser.ViewHolder> {

    public static final String TAG = AdapterHotAdviser.class.getName();
    private  Context mContext;
    private List<ModelHotAdviser> mDatas;

    public AdapterHotAdviser(Context context,List<ModelHotAdviser> datas) {
        this.mContext=context;
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_adviser, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        LogUtil.d(TAG, "holder.tvAskTitle=" + holder.tvAskTitle + "mDatas.get(position).getQ_content()=" + mDatas.get(position).getQ_content());

        holder.tvAskTitle.setText(mDatas.get(position).getQ_content());
        holder.tvName.setText(mDatas.get(position).getWorker_name());
        holder.tvCompany.setText(mDatas.get(position).getWorker_group());
        holder.tvAnswer.setText(mDatas.get(position).getA_content());
        holder.itemLinearlatout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, AdviserDetailActivity.class);
                intent.putExtra(Constant.INTENT_DATA,mDatas.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }




    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_ask)
        ImageView    ivAsk;
        @Bind(R.id.tv_ask_title)
        TextView     tvAskTitle;
        @Bind(R.id.item_adviser_qtime)
        TextView     itemAdviserQtime;
        @Bind(R.id.iv_answer)
        ImageView    ivAnswer;
        @Bind(R.id.tv_name)
        TextView     tvName;
        @Bind(R.id.tv_company)
        TextView     tvCompany;
        @Bind(R.id.tv_answer)
        TextView     tvAnswer;
        @Bind(R.id.item_adviser_atime)
        TextView     itemAdviserAtime;
        @Bind(R.id.ll_answer)
        LinearLayout llAnswer;
        @Bind(R.id.item_linearlatout)
        LinearLayout itemLinearlatout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}