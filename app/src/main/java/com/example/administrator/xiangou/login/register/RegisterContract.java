package com.example.administrator.xiangou.login.register;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class RegisterContract {
    interface View extends BaseView {
        //完成注册
        void registerSuccess(String message);
    }

    interface  Presenter extends BasePresenter<View> {
        //完成注册的请求
        void registerp(String tel, String password);
    }
}
