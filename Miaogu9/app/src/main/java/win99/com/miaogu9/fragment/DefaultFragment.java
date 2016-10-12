package win99.com.miaogu9.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import win99.com.miaogu9.R;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link DefaultFragment#} factory method to create an instance of
 * this fragment.
 * 
 */
public class DefaultFragment extends Fragment {
	private String mParam1;

	@Bind(R.id.tv_content)
	TextView tv_content;

	public DefaultFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*if (getArguments() != null) {
			mParam1 = getArguments().getString("key");
		}*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_default, container, false);
		ButterKnife.bind(this, view);
		return view;
	}
	
	/*@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		ButterKnife.bind(this, view);
		System.out.println(mParam1 + " - 创建完毕");
		//initView(view);

//		new Thread(){
//			@Override
//			public void run() {
//				super.run();
//				String path = "http://188.188.4.100:8080/oschina/list/news/page0.xml";
//
//				try {
//					URL url = new URL(path);
//					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//					conn.setRequestMethod("GET");
//					conn.setConnectTimeout(3000);
//
//					// 执行网络请求
//					int code = conn.getResponseCode();
//					if(code == 200){
//						InputStream is = conn.getInputStream();
//
//						// 字符串, 字节数据, 数据流 -> Bean -> 展示界面
//						NewsList newsList = XmlUtils.toBean(NewsList.class, is);
//						List<News> list = newsList.getList();
//						for (News news : list) {
//							System.out.println("news: " + news.toString());
//						}
//
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}.start();

	}*/

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView(View view) {
		if(TextUtils.isEmpty(mParam1)){
			tv_content.setText("我是一个测试用的Fragment, 我创建的时候没有传进来Bundle, 所以显示这个内容.");
		}else {
			tv_content.setText(mParam1);
		}
				
	}
	private void initView() {
		if(TextUtils.isEmpty(mParam1)){
			tv_content.setText("我是一个测试用的Fragment, 我创建的时候没有传进来Bundle, 所以显示这个内容.");
		}else {
			tv_content.setText(mParam1);
		}

	}

}
