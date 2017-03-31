package com.example.administrator.xiangou.login.dynamiclogin;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class DynamicLoginPresenter extends BasePresenterImpl<DynamicLoginContract.View> implements DynamicLoginContract.Presenter{

    @Override
    public void loginV(String tel, String code) {
        mView.showLoading();//加载等待

        addSubscription(mApiService.loginV(tel, code), new BaseSubscriber<LoginBean>(mView.getContext()) {
            @Override
            public void onNext(LoginBean loginBean) {
                switch (loginBean.getState().getCode()){
                    case 200:
                        User.setUser( loginBean.getData() );
                        mView.LoginvSuccess();
                    case 100:
                    default:
                        mView.sendFialRequest(loginBean.getState().getMsg());
                }
            }

            @Override
            public void onFinish() {
                mView.hideLoading();
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                mView.sendFialRequest(e.message);
            }

        });
    }
}
