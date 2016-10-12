package win99.com.miaogu9.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import win99.com.miaogu9.R;
import win99.com.miaogu9.domain.ModelHotAdviser;
import win99.com.miaogu9.library.BaseBannerAdapter;
import win99.com.miaogu9.library.VerticalBannerView;
import win99.com.miaogu9.ui.AdviserDetailActivity;
import win99.com.miaogu9.util.Constant;
import win99.com.miaogu9.util.LogUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by AdapterHomeInfo 16/7/21.
 */

public class AdapterAdviserHot extends BaseBannerAdapter<ModelHotAdviser> {
    private List<ModelHotAdviser> mDatas;
    private Activity              mActivity;

    public AdapterAdviserHot(Activity activity, List<ModelHotAdviser> datas) {
        super(datas);
        mActivity = activity;
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_adviser, null);
    }

    @Override
    public void setItem(final View view, final ModelHotAdviser data) {
        ViewHolder holder = new ViewHolder(view);
        LogUtil.d(TAG, "holder.tvAskTitle=" + holder.tvAskTitle + "mDatas.get(position).getQ_content()=" + data.getQ_content());
        holder.tvAskTitle.setText(data.getQ_content());
        holder.tvName.setText(data.getWorker_name());
        holder.tvCompany.setText(data.getWorker_group());
        holder.tvAnswer.setText(data.getA_content());
        holder.itemLinearlatout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, AdviserDetailActivity.class);
                intent.putExtra(Constant.INTENT_DATA,data);
                mActivity.startActivity(intent);
            }
        });
    }



    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
