package win99.com.miaogu9.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import win99.com.miaogu9.R;
import win99.com.miaogu9.domain.ModelAdviser;

/**
 * Created by pangweiwei on 16/8/4.
 */

public class AdapterHomeHot extends RecyclerView.Adapter<AdapterHomeHot.ViewHolder> {
    private Context            context;
    //public String[] datas = null;
    private List<ModelAdviser> mDatas;

    public AdapterHomeHot(List<ModelAdviser> datas) {
        this.mDatas = datas;
        //this.context = context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_adviser, viewGroup, false);
        ViewHolder vh = new ViewHolder(view,mDatas);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextViewTitle.setText(mDatas.get(position).ask);
        viewHolder.mTextViewName.setText(mDatas.get(position).name);
        viewHolder.mTextViewCompany.setText(mDatas.get(position).company);
        viewHolder.mTextViewAnswer.setText(mDatas.get(position).answer);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {

        return mDatas.size() > 5 ? 5 : mDatas.size();
    }

//自定义的ViewHolder，持有每个Item的的所有界面元素

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewTitle;
        public TextView mTextViewName;
        public TextView mTextViewCompany;
        public TextView mTextViewAnswer;

        public ViewHolder(View view, final List<ModelAdviser> mDatas) {
            super(view);
            mTextViewTitle = (TextView) view.findViewById(R.id.tv_ask_title);
            mTextViewName = (TextView) view.findViewById(R.id.tv_name);
            mTextViewCompany = (TextView) view.findViewById(R.id.tv_company);
            mTextViewAnswer = (TextView) view.findViewById(R.id.tv_answer);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        Context c = itemView.getContext();
                        //TODO: startActivity or others
                        //Toast.makeText(view.getContext(), "click on hot list"+getAdapterPosition(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(c, AdviserDetailActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //intent.putExtra("title", mDatas.get(getAdapterPosition()).ask);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("adviser", mDatas.get(getAdapterPosition()));
                        intent.putExtras(mBundle);

                        c.startActivity(intent);
                    }*/

                }
            });
        }
    }

}