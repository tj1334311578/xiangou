package com.example.administrator.xiangou.login.find_verifyphone;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class VerifyPhoneContract {
    interface View extends BaseView {
        void verifySuccess(String tel, String code);
    }
    interface  Presenter extends BasePresenter<View> {
        //重新获取验证码
        void regetCaptcha(String tel,String method);
        //验证验证码
        void verifyCaptchaFindPwd(String tel, String code);
    }
}
