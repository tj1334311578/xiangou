package com.example.administrator.xiangou.goodsdetails;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.xiangou.mvp.MVPBaseActivity;


public class GoodsDetailsActivity extends MVPBaseActivity<GoodsDetailsContract.View, GoodsDetailsPresenter> implements GoodsDetailsContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void sendFialRequest(String message) {

    }
}
