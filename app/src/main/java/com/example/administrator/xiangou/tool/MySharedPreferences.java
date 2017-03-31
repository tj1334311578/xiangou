package com.example.administrator.xiangou.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author zhouzongyao
 * @Description 自己封装的共享参数
 * @email 18482195579@163.com
 * @Date 2017-03-31 12:04
 */
public class MySharedPreferences {
    private Context mContext;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mSpEditor;
    private static final int SPTYPE_String = 1;
    private static final int SPTYPE_Boolean = 2;
    private static final int SPTYPE_Float = 3;
    private static final int SPTYPE_Int = 4;
    private static final int SPTYPE_Long = 5;
    private static final int SPTYPE_Set = 6;

    public MySharedPreferences(Context context,String name) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(name,Context.MODE_PRIVATE);
        mSpEditor = mSharedPreferences.edit();
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

    public boolean contains(String key){
        // 判断SP是否包含特定key的数据
        return mSharedPreferences.contains(key);
    }
}
