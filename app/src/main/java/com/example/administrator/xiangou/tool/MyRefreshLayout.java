package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/4/6.
 */

public class MyRefreshLayout extends ScrollView implements IPullable {

    public MyRefreshLayout(Context context) {
        super(context);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY()==0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
       if (getChildAt(0).getMeasuredHeight()<=getScrollY()+getHeight())
           return true;
        else
           return false;
    }
}
