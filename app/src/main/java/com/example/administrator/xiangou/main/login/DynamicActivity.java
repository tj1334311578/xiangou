package com.example.administrator.xiangou.main.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.xiangou.R;
import com.example.administrator.xiangou.tool.BaseActivity;

/**
 * Created by Administrator on 2017/3/22.
 */

public class DynamicActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_login);
        initView();
    }

    private void initView() {

    }
}
