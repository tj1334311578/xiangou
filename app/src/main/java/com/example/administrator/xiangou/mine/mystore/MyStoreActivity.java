package com.example.administrator.xiangou.mine.mystore;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyStoreActivity extends MVPBaseActivity<MyStoreContract.View, MyStorePresenter> implements MyStoreContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_center);
    }

    @Override
    public void sendFialRequest(String message) {

    }
}
