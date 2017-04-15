package com.example.administrator.xiangou.login.idlogin;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class IDLoginContract {
    interface View extends BaseView {
        void LoginidSuccess(LoginBean.DataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
        //id登录
        void IDlogin(String userName, String password);
    }
}
