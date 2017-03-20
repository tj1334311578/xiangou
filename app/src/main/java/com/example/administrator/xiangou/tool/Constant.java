package com.example.administrator.xiangou.tool;

/**
 * Created by Administrator on 2017/2/28.
 * app用到的公共类
 */

public class Constant {
    //退出意图
    public static final String EXIT_APP_ACTION="com.exit_app";
    //登录注册接口头
    public static final String KEY_INFO="http://192.168.0.106/index.php/";
    //公钥
    public static final String PUBLIC_KEY="";
    //登录接口尾
    public static final String KEY_LOGIN_INFO=KEY_INFO+"Api/Login/vlogin";
    //注册接口尾
    public static final String KEY_REGIST_INFO=KEY_INFO+"Api/Register/send_code";
    //获取验证码接口
    public static final String KEY_VERIFICATION_INFO=KEY_INFO+"Api/Login/verify";

}
