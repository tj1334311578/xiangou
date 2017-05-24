package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * 作者： tj on 2017/5/23.
 * <p>
 * 功能：fragment所依赖的activity
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class GoodsDetailsCommentActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout_style);
        GoodsDetailsCommentFragment fragment=new GoodsDetailsCommentFragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();
        ft.add(R.id.fragcontainer_ll,fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {

    }
}
