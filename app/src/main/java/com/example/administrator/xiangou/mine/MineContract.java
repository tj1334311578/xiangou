package com.example.administrator.xiangou.mine;


import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class MineContract {
    interface View extends BaseView {
//        void dealApplyReturnData(List<ToApplyStoreBean.DataBean> data);
        void ReLoginidSuccess(LoginBean.DataBean data);
    }

    interface  Presenter extends BasePresenter<View> {
//        void callToApplyStore(int user_id);

        //id登录
        void IDlogin(String userName, String password);
    }
}
