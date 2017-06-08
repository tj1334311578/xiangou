package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author zhouzongyao
 * @Description 自己封装的共享参数
 * @email 18482195579@163.com
 * @Date 2017-03-31 12:04
 */
public class MySharedPreferences {
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSpEditor;
    public static final String STATUS_LOGIN = "staus_login";
    public static final String STATUS_GUIDE = "staus_guide";

    public MySharedPreferences(Context context,String name) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(name,Context.MODE_PRIVATE);
        mSpEditor = mSharedPreferences.edit();
    }

    //判断用户是否登录
    public boolean isLogined(){
        return mSharedPreferences.getBoolean(MySharedPreferences.STATUS_LOGIN,false);
    }
    //用户已登录
    public boolean hasLogined(){
        if (!mSharedPreferences.getBoolean(MySharedPreferences.STATUS_LOGIN,false)) {
            putBoolean(MySharedPreferences.STATUS_LOGIN, true);
        }
        return true;
    }
    //判断用户注销
    public void logout(){
        putBoolean(MySharedPreferences.STATUS_LOGIN,false);
    }

    //更新用户信息
    public void upDateUserInfo(String info){
//        if (mSharedPreferences.getString("user_info",null)!=null){
//            remove("user_info");
//        }
        putString("user_info",info);
        if (!mSharedPreferences.getBoolean(MySharedPreferences.STATUS_LOGIN,false)) {
           putBoolean(MySharedPreferences.STATUS_LOGIN, true);
        }
    }

    /**
     * 向SP存入指定key对应的数据
     * 其中value可以是String、boolean、float、int、long等各种基本类型的值
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        mSpEditor.putString(key, value);
        mSpEditor.commit();
    }

    public void putBoolean(String key, boolean value) {
        mSpEditor.putBoolean(key, value);
        mSpEditor.commit();
    }

    public void putFloat(String key, float value) {
        mSpEditor.putFloat(key, value);
        mSpEditor.commit();
    }

    public void putInt(String key, int value) {
        mSpEditor.putInt(key, value);
        mSpEditor.commit();
    }

    public void putLong(String key, long value) {
        mSpEditor.putLong(key, value);
        mSpEditor.commit();
    }

    public void putStringSet(String key, Set<String> strSet) {
        mSpEditor.putStringSet(key, strSet);
        mSpEditor.commit();
    }

    /**
     * 清空SP里所以数据
     */
    public void clear() {
        mSpEditor.clear();
        mSpEditor.commit();
    }

    /**
     * 删除SP里指定key对应的数据项
     * @param key
     */
    public void remove(String key) {
        mSpEditor.remove(key);
        mSpEditor.commit();
    }

    /**
     * 获取SP数据里指定key对应的value。如果key不存在，则返回默认值defValue。
     * @param key
     * @param defValue
     * @return
     */
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }
    public Set<String> getStringSet(String key, Set<String> strSet) {
        return mSharedPreferences.getStringSet(key, strSet);
    }

    public boolean contains(String key){
        // 判断SP是否包含特定key的数据
        return mSharedPreferences.contains(key);
    }

}
