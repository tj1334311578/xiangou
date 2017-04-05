package com.example.administrator.xiangou.login.find_verifyphone;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class VerifyPhonePresenter extends BasePresenterImpl<VerifyPhoneContract.View> implements VerifyPhoneContract.Presenter{

    @Override
    public void regetCaptcha(String tel, String method) {
        addSubscription(mApiService.sendCapture(tel, method), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                switch (captcha.getState().getCode()){
                    case 200:
                        mView.sendFialRequest(captcha.getState().getMsg());//提示--发送成功
                        break;
                    default:
                        mView.sendFialRequest(captcha.getState().getMsg());
                        break;
                }
            }

            @Override
            public void onFinish() {}

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                mView.sendFialRequest(e.getMessage());
            }
        });
    }

    @Override
    public void verifyCaptchaFindPwd(final String tel, final String code) {
        mView.showLoading();
        addSubscription(mApiService.verifyFindPwd(tel, code), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                switch (captcha.getState().getCode()){
                    case 200:
                        mView.verifySuccess(tel,code);
                        break;
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
                mView.sendFialRequest(e.getMessage());
            }
        });
    }
}
