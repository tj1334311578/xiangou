package com.example.administrator.xiangou.mine.followpage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;

public class FollowPageActivity extends MVPBaseActivity<FollowPageContract.View, FollowPagePresenter> implements FollowPageContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout_style);
        FollowPageFragment fragment = new FollowPageFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fragcontainer_ll,fragment);
        ft.commit();
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {

    }
}
