package com.example.administrator.xiangou.base;

import android.content.Context;

import java.util.List;

/**
 * Created by zhouzongyao on 2017/3/7.
 */

public abstract class SimpleAdapter<T> extends BaseAdapter<T,BaseViewHolder>{
    public SimpleAdapter(Context context, List<T> mDatas) {
        super(context, mDatas);
    }

    public SimpleAdapter(Context context, int mLayoutResId, List<T> mDatas) {
        super(context, mLayoutResId, mDatas);
    }
}
