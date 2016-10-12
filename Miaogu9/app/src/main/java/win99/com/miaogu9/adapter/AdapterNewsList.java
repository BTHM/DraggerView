package win99.com.miaogu9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import win99.com.miaogu9.R;
import win99.com.miaogu9.domain.ModelHomeInfo;


public class AdapterNewsList extends BaseAdapter {

	private Context             context;
	private List<ModelHomeInfo> mDatas;
	private ModelHomeInfo       tempListViewItem;
	private LayoutInflater      layoutInflater;

	public AdapterNewsList(Context context, List<ModelHomeInfo> datas) {
		this.mDatas = datas;
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
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

		ViewHolder holder;

		if (layoutInflater == null) {
			return convertView;
		}

		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item_news_list, null);
			holder = new ViewHolder();

			holder.title = (TextView) convertView.findViewById(R.id.textviewTitle);
			holder.date = (TextView) convertView.findViewById(R.id.textviewTime);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
			holder.title.setText(null);
			holder.date.setText(null);
		}

		tempListViewItem = mDatas.get(position);

		holder.title.setText(tempListViewItem.title);

		holder.date.setText(tempListViewItem.date);

		return convertView;
	}

	private class ViewHolder {
		public TextView title;
		public TextView date;
	}
}
