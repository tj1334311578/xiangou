package com.example.administrator.xiangou.login.dynamiclogin;


import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class DynamicLoginContract {
    interface View extends BaseView {
        //验证码登录login
        void LoginvSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        //验证码登录请求
        void loginV(String tel, String code);
    }
}
