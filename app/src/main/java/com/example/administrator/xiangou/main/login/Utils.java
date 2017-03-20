package com.example.administrator.xiangou.main.login;

/**
 * Created by Administrator on 2017/3/17.
 */
public class Utils {
    public static boolean isNumber(String str){
        int lenth=str.lastIndexOf(str);
        if (lenth==11)
            return true;
        else
            return false;
    }
}
