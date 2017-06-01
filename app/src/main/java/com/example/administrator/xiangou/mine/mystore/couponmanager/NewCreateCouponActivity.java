package com.example.administrator.xiangou.mine.mystore.couponmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

/**
 * 作者： tj on 2017/5/31.
 * <p>
 * 功能：
 * <p>
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class NewCreateCouponActivity extends MVPBaseActivity<CouponManagerContract.View,CouponManagerPresenter> implements  CouponManagerContract.View{
    private ImageView backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewcoupon);
        initView();
    }

    private void initView() {
        backBtn=findContentView(R.id.newCreateCouponBackBtn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newCreateCouponBackBtn:
                finish();
        }

    }

    @Override
    public void sendFialRequest(String message) {

    }
}
