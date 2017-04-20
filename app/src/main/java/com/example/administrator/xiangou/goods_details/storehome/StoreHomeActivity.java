package com.example.administrator.xiangou.goods_details.storehome;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreHomeActivity extends MVPBaseActivity<StoreHomeContract.View, StoreHomePresenter> implements StoreHomeContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_homefragment);
    }

    @Override
    public void sendFialRequest(String message) {

    }
}
