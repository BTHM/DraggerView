package win99.com.miaogu9.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import win99.com.miaogu9.R;

/**
 * Created by pangweiwei on 16/8/20.
 */

public class JCVideoPlayerStandardShowShareButton extends JCVideoPlayerStandard {
    public ImageView shareButton;

    public JCVideoPlayerStandardShowShareButton(Context context) {
        super(context);
    }

    public JCVideoPlayerStandardShowShareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        if (isInEditMode()) { return; }
        super.init(context);
        shareButton = (ImageView) findViewById(R.id.share);
        shareButton.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.jc_layout_standard_with_share_button;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.share) {
            Toast.makeText(getContext(), "Whatever the icon means", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void setStateAndUi(int state) {
        super.setStateAndUi(state);
        if (mIfCurrentIsFullscreen) {
            shareButton.setVisibility(View.VISIBLE);
        } else {
            shareButton.setVisibility(View.VISIBLE);
        }
    }
}
