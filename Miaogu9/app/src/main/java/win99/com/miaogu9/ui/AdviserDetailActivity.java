package win99.com.miaogu9.ui;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.Bind;
import win99.com.miaogu9.R;
import win99.com.miaogu9.base.BaseActivity;
import win99.com.miaogu9.domain.ModelHotAdviser;
import win99.com.miaogu9.util.Constant;

import static win99.com.miaogu9.R.id.tool_textView;

public class AdviserDetailActivity extends BaseActivity {


    @Bind(R.id.topBack)
    ImageButton  topBack;
    @Bind(R.id.topTitle)
    TextView     topTitle;
    @Bind(R.id.topSave)
    ImageButton  topSave;
    @Bind(R.id.layoutTopBar)
    LinearLayout layoutTopBar;
    @Bind(R.id.iv_ask)
    ImageView    ivAsk;
    @Bind(R.id.tv_ask_title)
    TextView     tvAskTitle;
    @Bind(R.id.layoutAsk)
    LinearLayout layoutAsk;
    @Bind(R.id.iv_bar)
    View         ivBar;
    @Bind(R.id.iv_answer)
    ImageView    ivAnswer;
    @Bind(R.id.tv_name)
    TextView     tvName;
    @Bind(R.id.tv_company)
    TextView     tvCompany;
    @Bind(R.id.tv_answer)
    TextView     tvAnswer;
    @Bind(R.id.tv_time)
    TextView     tvTime;
    @Bind(R.id.layoutAnswer)
    LinearLayout layoutAnswer;
    @Bind(R.id.listViewGuess)
    ListView     listViewGuess;
    @Bind(R.id.layoutGuess)
    LinearLayout layoutGuess;
    @Bind(R.id.activity_adviser_detail)
    LinearLayout activityAdviserDetail;
    @Bind(R.id.toolbar_back)
    ImageView    toolbarBack;
    @Bind(tool_textView)
    TextView     toolTextView;
    @Bind(R.id.main_toolbar)
    Toolbar      mainToolbar;

    @Override
    public int initLayout() {
        return R.layout.activity_adviser_detail;
    }

    @Override
    public void setData() {
        setToolbar();

        Intent intent = getIntent();
        ModelHotAdviser hotAdviser = (ModelHotAdviser) intent.getSerializableExtra(Constant.INTENT_DATA);
        tvAskTitle.setText(hotAdviser.getQ_content());
        tvAnswer.setText(hotAdviser.getA_content());
        tvCompany.setText(hotAdviser.getWorker_group());

    }

    private void setToolbar() {
        toolTextView.setText("热评详情");
        toolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdviserDetailActivity.this.finish();
            }
        });
    }

    @Override
    public void setListener() {

    }


}
