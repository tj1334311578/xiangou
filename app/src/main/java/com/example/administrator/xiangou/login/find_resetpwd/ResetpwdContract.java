package com.example.administrator.xiangou.login.find_resetpwd;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class ResetpwdContract {
    interface View extends BaseView {
        void resetPwdSuccess();
    }

    interface  Presenter extends BasePresenter<View> {
        void resetPwd(String tel,String password,String code);
    }
}
