package com.example.administrator.xiangou.tool;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhouzongyao on 2017/3/6.
 */

public class ItemIntervalDecoration extends RecyclerView.ItemDecoration{
    //RecyclerView的item的间隔
    private float mTopInterval;
    private float mLeftInterval;
    private float mRightInterval;
    private float mBottomInterval;

    public ItemIntervalDecoration(float interval) {
        interval = ContextUtils.dp2px(interval);
        mLeftInterval = interval;
        mTopInterval = interval;
        mRightInterval = interval;
        mBottomInterval = interval;
    }

    public ItemIntervalDecoration(float leftInterval, float topInterval, float bottomInterval) {
        mTopInterval = ContextUtils.dp2px(topInterval);
        mLeftInterval = ContextUtils.dp2px(leftInterval);
        mBottomInterval = ContextUtils.dp2px(bottomInterval);
    }

    public ItemIntervalDecoration(float topInterval, float bottomInterval, float leftInterval, float rightInterval) {
        mRightInterval = ContextUtils.dp2px(rightInterval);
        mTopInterval = ContextUtils.dp2px(topInterval);
        mLeftInterval = ContextUtils.dp2px(leftInterval);
        mBottomInterval = ContextUtils.dp2px(bottomInterval);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //判断item的position
        if (parent.getChildAdapterPosition(view) ==0 ) {
            outRect.left = 0;
//            outRect.right = (int) mRightInterval;
        }
            outRect.left = (int) mLeftInterval;
            outRect.top = (int) mTopInterval;
            outRect.bottom = (int) mBottomInterval;
    }
}
