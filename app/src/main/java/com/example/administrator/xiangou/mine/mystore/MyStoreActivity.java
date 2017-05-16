package com.example.administrator.xiangou.mine.mystore;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MyStoreActivity extends MVPBaseActivity<MyStoreContract.View, MyStorePresenter> implements MyStoreContract.View {
    private ImageButton back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_center);
        back=findContentView(R.id.seller_center_return);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {
        if (v==back){
            finish();
        }
    }
}
