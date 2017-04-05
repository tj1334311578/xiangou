package com.example.administrator.xiangou.login.find_bytelephone;

import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class FindByTelephonePresenter extends BasePresenterImpl<FindByTelephoneContract.View> implements FindByTelephoneContract.Presenter{

    @Override
    public void getCaptcha(final String tel, String method) {
        addSubscription(mApiService.sendCapture(tel, method), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                switch (captcha.getState().getCode()){
                    case 200:
                        Log.e("sendCaptcha", "onNext: 成功" );
                        mView.sendCaptchaFindPwd(tel);
                        break;
                    default:
                        Log.e("sendCaptcha", "onNext: 失败" );
                        mView.sendFialRequest(captcha.getState().getMsg());//todo UI toast 失败
                        break;
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                mView.sendFialRequest(e.getMessage());
            }
        });
    }
}
