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

        //保存登录页面信息和用户信息
        void saveInfo(String TelKey, String Tel, String PwdKey, String Pwd);
    }
}
