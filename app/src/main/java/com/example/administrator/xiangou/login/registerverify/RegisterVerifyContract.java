package com.example.administrator.xiangou.login.registerverify;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class RegisterVerifyContract {

    //此接口内的方法为MVP中View层实现的接口方法
    interface View extends BaseView {
        //注册验证码Object
        void verifySuccess();
    }

    //此接口内的方法为MVP中P层实现的接口方法
    interface  Presenter extends BasePresenter<View> {
        void captcha(String tel);//验证码
        void registerv(String tel, String code);//注册-确认验证码
    }
}
