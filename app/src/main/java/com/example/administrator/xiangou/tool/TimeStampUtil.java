package com.example.administrator.xiangou.tool;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者： Administrator on 2017/5/22.
 *
 * 功能：时间戳工具类
 *
 * 邮箱：1334311578@qq.com
 * osc git address：https://git.oschina.net/xiangou/Android.git
 */

public class TimeStampUtil {
    public static  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //时间戳转化为String
    public static String FormatTimeStampToString(long time){
        return format.format(time);
    }
    //时间戳转化为Date
    public static Date FormatTimeStampToDate(long time) throws ParseException {
          return format.parse(FormatTimeStampToString(time));
    }

    //String转化为时间戳
    public static long StringToFormatTimeStamp(String time) throws ParseException {
        Date date=format.parse(time);
        return DateToFormatTimeStamp(date);
    }
    //Date转化为时间戳
    public static long DateToFormatTimeStamp(Date time){
        return time.getTime();
    }
}
