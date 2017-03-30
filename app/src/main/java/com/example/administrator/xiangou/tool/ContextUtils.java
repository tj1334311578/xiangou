package com.example.administrator.xiangou.tool;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.DisplayMetrics;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zhouzongyao on 2017/3/6.
 */

public class ContextUtils extends Application{
    public SharedPreferences mSharedPreferences;
    public static int phoneWidth,phoneHeight;//手机屏幕的宽高
    private static ContextUtils mContext;
    private static DisplayMetrics mDisplayMetrics;

    // DES--密钥
    private final static String secretKey = "xg101888";
    private static byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    public void onCreate() {
        mContext = this;
        super.onCreate();
        mDisplayMetrics = getResources().getDisplayMetrics();
        phoneWidth = mDisplayMetrics.widthPixels;
//        Log.e("phoneWidth", "onCreate: "+phoneWidth );
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

    /**
     * 字符串转MD5
     */
    public static String MD5(String tag) {
        try {
            byte[] btInput = tag.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * DES加密
     * @param plainText
     * @return Base64再加密后返回string类型
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(plainText.getBytes());//DES加密
        return Base64.encodeToString(encryptedData, 1);//Base64再加密
    }
    public static String decode(String encryptText) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText, 1));
        return new String(decryptData);
    }
}
