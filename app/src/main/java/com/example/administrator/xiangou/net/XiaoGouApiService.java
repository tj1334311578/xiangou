package com.example.administrator.xiangou.net;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.login.LoginBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃     神兽保佑　　　　　　　　
//    ┃　　　┃     代码无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

public interface XiaoGouApiService {
    public static final String mBASEURL = "http://192.168.0.106/";
    //获取验证码--注册
    @POST("index.php/Api/Register/send_code")
    Observable<Captcha> getCapture(@Query("tel") String tel);
    //验证验证码--注册
    @POST("index.php/Api/Register/register1/")
    Observable<Captcha> goRegister(@Query("tel") String tel, @Query("code") String code);
    //完成--注册
    @POST("index.php/Api/Register/register/")
    Observable<Captcha> toRegister(@Query("tel") String tel, @Query("code") String code, @Query("password") String password);
    //账号登录
//    @FormUrlEncoded
    @POST("api/Login/login/")
    Observable<LoginBean> loginID(@Query("tel") String tel, @Query("password") String password);
    /**
     * 请求验证码（登录、找回密码）
     * @param tel
     * @param method 登录 login | 找回密码 findpsw
     * @return
     */
    @POST("index.php/Api/Login/verify")
    Observable<Captcha> sendCapture(@Query("tel") String tel, @Query("method") String method);

    //动态登录
    @POST("Api/Login/vlogin/")
    Observable<LoginBean> loginV(@Query("tel") String tel, @Query("code") String code);

    //验证验证码--找回密码
    @POST("Api/Login/check_code/")
    Observable<Captcha> verifyFindPwd(@Query("tel") String tel, @Query("code") String code);
    //设新密码--找回密码
    @POST("Api/Login/findpsw/")
    Observable<Captcha> resetPwd(@Query("tel") String tel, @Query("password") String password, @Query("code") String code);
}