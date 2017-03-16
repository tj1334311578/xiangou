package com.example.administrator.xiangou.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/2/28.
 * 所有activity的基类
 */

public class BaseActivity extends AppCompatActivity {
    private BroadcastReceiver exitReceiver=new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            System.exit(0);
        }
    };
    //注册广播
    private void registerExitReceiver() {
        IntentFilter exitFilter = new IntentFilter();
        exitFilter.addAction(Constant.EXIT_APP_ACTION);
        this.registerReceiver(this.exitReceiver, exitFilter);
    }
    //广播注册注销
    private void unRegisterExitReceiver() {
        if (exitReceiver != null) {
            this.unregisterReceiver(this.exitReceiver);
        }
    }

    //退出程序--可以写在退出控件监听中
    public void exit_app(){
        Intent intent =new Intent();
        intent.setAction(Constant.EXIT_APP_ACTION);
        this.sendBroadcast(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerExitReceiver();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        registerExitReceiver();
    }
    //    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterExitReceiver();
    }

}
