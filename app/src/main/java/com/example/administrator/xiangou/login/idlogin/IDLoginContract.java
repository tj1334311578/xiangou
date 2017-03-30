package com.example.administrator.xiangou.login.idlogin;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class IDLoginContract {
    interface View extends BaseView {
        void LoginidSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        //id登录
        void IDlogin(String userName, String password);
    }
}
