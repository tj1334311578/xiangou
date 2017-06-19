package com.example.administrator.xiangou.mine.mystore.ordermanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public class OrderManagerActivity extends MVPBaseActivity<OrderManagerContract.View,OrderManagerPresenter> implements OrderManagerContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout_style);
        OrderManagerFragment fragment = new OrderManagerFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fragcontainer_ll,fragment);
        ft.commit();
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void sendFialRequest(String message) {

    }
}
