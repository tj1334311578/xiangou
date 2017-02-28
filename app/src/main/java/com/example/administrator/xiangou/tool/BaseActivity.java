package com.example.administrator.xiangou.tool;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/2/28.
 * 所有activity的基类
 */

public class BaseActivity extends AppCompatActivity {
    private  ExitAppReceiver exitReceiver=new ExitAppReceiver();
    //自定义退出程序action

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        registerExitReceiver();
    }
    //注册广播
    private void registerExitReceiver() {
        IntentFilter exitFilter = new IntentFilter();
        exitFilter.addAction(Constant.EXIT_APP_ACTION);
        registerReceiver(exitReceiver, exitFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterExitReceiver();
    }
        //广播注册注销
    private void unRegisterExitReceiver() {
        IntentFilter exitFilter = new IntentFilter();
        exitFilter.addAction(Constant.EXIT_APP_ACTION);
        registerReceiver(exitReceiver, exitFilter);
    }
    //可以写在退出控件监听中
    public void exit_app(){
        Intent intent =new Intent();
        intent.setAction(Constant.EXIT_APP_ACTION);
        sendBroadcast(intent);
    }
}
