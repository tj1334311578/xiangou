package com.example.administrator.xiangou.main.login;

import com.example.administrator.xiangou.tool.Constant;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/17.
 */

public interface RequestService {
    @POST(Constant.KEY_REGIST_INFO)
    Call<ResponseBody> getRegister(@Query("tel") String phoneNumber,
                                   @Query("code") String code,
                                   @Query("password") String psw);
    @POST(Constant.KEY_VERIFICATION_INFO)
    Call<ResponseBody> getCode(@Query("tel") String tel);
}
