package com.example.administrator.xiangou.goods_details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Goods_rankingActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        Goods_rankingFragment fragment=new Goods_rankingFragment();
        transaction.add(fragment,"fragment");
        transaction.commit();
    }
}
