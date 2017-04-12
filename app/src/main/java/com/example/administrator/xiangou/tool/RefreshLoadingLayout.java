package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/4/5.
 * 定义了基本状态设施方法
 */

public abstract class RefreshLoadingLayout extends RelativeLayout {

    public RefreshLoadingLayout(Context context){
        super(context);
    }

    public RefreshLoadingLayout(Context context, AttributeSet attr){
        super(context,attr);
    }
    public abstract void setState(int state);
}
