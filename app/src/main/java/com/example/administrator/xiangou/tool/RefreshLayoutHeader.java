package com.example.administrator.xiangou.tool;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangou.R;

/**
 * Created by Administrator on 2017/4/5.
 */

public class RefreshLayoutHeader extends RefreshLoadingLayout {
    private TextView label;
    private ImageView dog;
    public RefreshLayoutHeader(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.layout_refresh_header,this);
        label= (TextView) findViewById(R.id.tv_refreshLabel);
        dog= (ImageView) findViewById(R.id.iv_dog);
    }

    public RefreshLayoutHeader(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    @Override
    public void setState(int state) {
        dog.setVisibility(GONE);
        switch (state){
            case HDRefreshLayout.STATE_AIDL:
                label.setText("");
                break;
            case HDRefreshLayout.STATE_PULL_REFRESH:
                label.setText("继续下拉以刷新");
                break;
            case HDRefreshLayout.STATE_RELEASH_TO_REFRESH:
                label.setText("松开以刷新");
                break;
            case HDRefreshLayout.STATE_REFRESHING:
                label.setText("正在刷新");
                dog.setVisibility(VISIBLE);
                dog.setBackgroundResource(R.drawable.show_down);
                AnimationDrawable anim = (AnimationDrawable) dog.getBackground();
                anim.start();
                break;
            case HDRefreshLayout.STATE_REFRESH_SUCCESS:
                label.setText("刷新成功");
                break;
            case HDRefreshLayout.STATE_REFRESH_FAIL:
                label.setText("刷新失败");
                break;
        }
    }
}
