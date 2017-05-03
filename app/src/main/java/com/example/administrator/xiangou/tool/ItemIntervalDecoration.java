package com.example.administrator.xiangou.tool;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhouzongyao on 2017/3/6.
 */

public class ItemIntervalDecoration extends RecyclerView.ItemDecoration{
    //RecyclerView的item的间隔
    private int mTopInterval;
    private int mLeftInterval;
    private int mRightInterval;
    private int mBottomInterval;

    public ItemIntervalDecoration(int interval) {
        interval = ContextUtils.dp2px(interval);
        mLeftInterval = interval;
        mTopInterval = interval;
        mRightInterval = interval;
        mBottomInterval = interval;
    }

    public ItemIntervalDecoration(int leftInterval, int topInterval, int rightInterval, int bottomInterval) {
        mLeftInterval = ContextUtils.dp2px(leftInterval);
        mTopInterval = ContextUtils.dp2px(topInterval);
        mRightInterval = ContextUtils.dp2px(rightInterval);
        mBottomInterval = ContextUtils.dp2px(bottomInterval);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getLayoutManager() instanceof GridLayoutManager){
            //网格布局item间隔，左、右边距只需设置左边距
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            outRect.top = mTopInterval;
            if (parent.getChildAdapterPosition(view) < layoutManager.getSpanCount())
            {
                outRect.top = 0;
            }
            outRect.left = mLeftInterval;
            if ( (parent.getChildAdapterPosition(view) % layoutManager.getSpanCount())==0){
                outRect.left = 0;
            }
            outRect.right = mRightInterval;
            outRect.bottom = mBottomInterval;
        }else if (parent.getLayoutManager() instanceof LinearLayoutManager){
            //判断item的position
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 0;
            }else {
                outRect.top = mTopInterval;
            }
            outRect.left = mLeftInterval;
            outRect.right = mRightInterval;
            outRect.bottom = mBottomInterval;
        }else {
            outRect.left = mLeftInterval;
            outRect.top = mTopInterval;
            outRect.right = mRightInterval;
            outRect.bottom = mBottomInterval;
        }
    }
}

//        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
//        //判断总的数量是否可以整除
//        int totalCount = layoutManager.getItemCount();
//        int surplusCount = totalCount % layoutManager.getSpanCount();
//        int childPosition = parent.getChildAdapterPosition(view);
//        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {//竖直方向的
//            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
//                //后面几项需要bottom
//                outRect.bottom =  mTopInterval;
//            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
//                outRect.bottom =  mTopInterval;
//            }
//            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要右边
//                outRect.right = mLeftInterval;
//            }
//            outRect.top = mBottomInterval;
//            outRect.left = mLeftInterval;
//        } else {
//            if (surplusCount == 0 && childPosition > totalCount - layoutManager.getSpanCount() - 1) {
//                //后面几项需要右边
//                outRect.right = mLeftInterval;
//            } else if (surplusCount != 0 && childPosition > totalCount - surplusCount - 1) {
//                outRect.right = mLeftInterval;
//            }
//            if ((childPosition + 1) % layoutManager.getSpanCount() == 0) {//被整除的需要下边
//                outRect.bottom = mTopInterval;
//            }
//            outRect.top = mBottomInterval;
//            outRect.left = mLeftInterval;
//        }