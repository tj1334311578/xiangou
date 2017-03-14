package com.example.administrator.xiangou.tool;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/1.
 */

public class ExitReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("TGA", "onReceive: "+Constant.EXIT_APP_ACTION);
        if (intent.getAction().equals(Constant.EXIT_APP_ACTION)){
//            ((Activity)context).finish();
            System.exit(0);
            if (((Activity)context).isFinishing())
            Log.e("TGA", "onReceive: 执行ｅｘｉｔ" );
            else{
                Log.e("TGA", "onReceive: 未finish掉" );
            }
        }
        else{
            Log.e("TGA", "onReceive: 未执行exit" );
            Toast.makeText(context, "未执行exit", Toast.LENGTH_SHORT).show();
        }
    }
}
