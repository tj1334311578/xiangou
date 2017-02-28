package com.example.administrator.xiangou.tool;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2017/2/28.
 */

public class ExitAppReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if (context != null) {

            if (context instanceof Activity) {

                ((Activity) context).finish();
            } else if (context instanceof FragmentActivity) {

                ((FragmentActivity) context).finish();
            } else if (context instanceof Service) {

                ((Service) context).stopSelf();
            }
        }
    }
}
