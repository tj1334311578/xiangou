package com.example.administrator.xiangou.tool;

/**
 * Created by Administrator on 2017/4/5.
 * 判断控件当前是否可刷新
 */

public interface IPullable {
    boolean canPullDown();
    boolean canPullUp();
}
