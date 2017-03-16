package com.example.administrator.xiangou.tool;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;

/**
 * Created by zhouzongyao on 2017/3/6.
 */

public class ContextUtils extends Application{

    public SharedPreferences mSharedPreferences;
    public static int phoneWidth,phoneHeight;//手机屏幕的宽高
    private static ContextUtils mContext;
    private static DisplayMetrics mDisplayMetrics;

    @Override
    public void onCreate() {
        mContext = this;
        super.onCreate();
        mDisplayMetrics = getResources().getDisplayMetrics();
        phoneWidth = mDisplayMetrics.widthPixels;
        Log.e("phoneWidth", "onCreate: "+phoneWidth );
        phoneHeight = mDisplayMetrics.heightPixels;
    }
    public static ContextUtils getInstance() {
        return mContext;
    }


    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public static int px2dp(float pxValue) {
        final float scale = mDisplayMetrics.density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dp2px(float dipValue) {
        final float scale = mDisplayMetrics.density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float pxValue) {
        final float fontScale = mDisplayMetrics.scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        final float fontScale = mDisplayMetrics.scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 保留2位小数值
     * @param num
     * @return
     */
    public static String S2places(float num){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }
    public static float F2places(float num){
        BigDecimal bd = new BigDecimal(num);
        return bd.setScale(2).floatValue();
    }
}
