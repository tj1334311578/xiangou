package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.xiangou.R;

/**
 * Created by Administrator on 2017/4/5.
 */
public class RefreshLayoutFooter extends RefreshLoadingLayout {
    private TextView label;
    public RefreshLayoutFooter(Context context) {
        super(context);
        init(context);
    }

    public RefreshLayoutFooter(Context context, AttributeSet attr) {
        super(context, attr);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.layout_refresh_footer,this);
        label= (TextView) findViewById(R.id.tv_refreshLabel);
    }

    @Override
    public void setState(int state) {
        switch (state) {
            case HDRefreshLayout.STATE_AIDL:
                label.setText("");
                break;
            case HDRefreshLayout.STATE_PULL_REFRESH:
                label.setText("继续上以刷新");
                break;
            case HDRefreshLayout.STATE_RELEASH_TO_REFRESH:
                label.setText("松开以刷新");
                break;
            case HDRefreshLayout.STATE_REFRESHING:
                label.setText("正在刷新");
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
