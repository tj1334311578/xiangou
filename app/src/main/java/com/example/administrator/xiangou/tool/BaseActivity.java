package com.example.administrator.xiangou.tool;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/3/1.
 */

public class BaseActivity extends Activity {
    private ExitReceiver exitReceiver=new ExitReceiver();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerExitReceiver();
    }

    private void registerExitReceiver() {
        IntentFilter intentfilter=new IntentFilter();
        Log.e("TGA", "registerExitReceiver: "+Constant.EXIT_APP_ACTION);
        intentfilter.addAction(Constant.EXIT_APP_ACTION);
        registerReceiver(exitReceiver,intentfilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterExitReceiver();
    }

    private void unregisterExitReceiver() {
        unregisterReceiver(exitReceiver);
        Log.e("TGA", "unregisterExitReceiver: onDestroy");
    }
    public  void  exit_app(){
        Intent intent=new Intent();
        intent.setAction(Constant.EXIT_APP_ACTION);
        sendBroadcast(intent);
    }
}
