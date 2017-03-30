package com.example.administrator.xiangou.net;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.login.LoginBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

public interface MyApiService {
    public static final String mBASEURL = "http://192.168.0.106/";
    //获取验证码
    @POST("index.php/Api/Register/send_code")
    Observable<Captcha> getCapture(@Query("tel") String tel);
    //验证注册
    @POST("index.php/Api/Register/register1/")
    Observable<Captcha> goRegister(@Query("tel") String tel, @Query("code") String code);
    //完成注册
    @POST("index.php/Api/Register/register/")
    Observable<Captcha> toRegister(@Query("tel") String tel, @Query("password") String password);
    //动态登录
    @POST("Api/Login/vlogin/")
    Observable<LoginBean> loginV(@Query("tel") String tel, @Query("code") String code);
    //账号登录
    @FormUrlEncoded
    @POST("api/Login/login/")
    Observable<LoginBean> loginID(@Field("tel") String tel, @Field("password") String password);

}