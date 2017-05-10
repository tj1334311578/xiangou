package com.example.administrator.xiangou.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author zhouzongyao
 * @Description 给RecyclerView添加头部或底部
 * @email 18482195579@163.com
 * @Date 2017-05-09 18:31
 */
public class WrapRecyclerView extends RecyclerView{
    // 包裹了一层的头部底部Adapter
    private RVBaseAdapter mWrapRecyclerAdapter;
    // 这个是列表数据的Adapter
    private RecyclerView.Adapter mAdapter;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
    }


}
