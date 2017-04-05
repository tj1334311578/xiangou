package com.example.administrator.xiangou.login.register;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ContextUtils;

public class RegisterPresenter extends BasePresenterImpl<RegisterContract.View> implements RegisterContract.Presenter{

    @Override
    public void registerp(String tel, String code, String password) {
        mView.showLoading();
        addSubscription(mApiService.toRegister( tel, code, ContextUtils.MD5(password) ),
                new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                switch (captcha.getState().getCode()){
                    case 200:
                        mView.registerSuccess(captcha.getState().getMsg());
                        break;
                    case 100:
                    default:
                        mView.sendFialRequest(captcha.getState().getMsg());
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
