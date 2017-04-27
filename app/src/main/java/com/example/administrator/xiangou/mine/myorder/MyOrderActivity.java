package com.example.administrator.xiangou.mine.myorder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mine.myorder.myorder.MyOrderFragment;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyOrderActivity extends MVPBaseActivity<MyOrderContract.View, MyOrderPresenter> implements MyOrderContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        MyOrderFragment fragment = new MyOrderFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fragcontainer_ll,fragment);
        ft.commit();

    }

    @Override
    public void sendFialRequest(String message) {

    }
}
