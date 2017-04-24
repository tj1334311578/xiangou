package com.example.administrator.xiangou.mine.setting.personal;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.mvp.MVPBaseActivity;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class PersonalActivity extends MVPBaseActivity<PersonalContract.View, PersonalPresenter> implements PersonalContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_personal);
    }

    @Override
    public void sendFialRequest(String message) {

    }

    @Override
    public void onClick(View v) {

    }
}
