package com.example.administrator.xiangou.login.find_resetpwd;

import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ContextUtils;

public class ResetpwdPresenter extends BasePresenterImpl<ResetpwdContract.View> implements ResetpwdContract.Presenter{

    @Override
    public void resetPwd(String tel, String password, String code) {
        mView.showLoading();
        addSubscription(
                mApiService.resetPwd(tel, ContextUtils.MD5(password) , code) ,
                new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                switch (captcha.getState().getCode()){
                    case 200:
                        mView.resetPwdSuccess();
                        Log.e("resetPwd", "onNext: -----------");
                        break;
                    default:
                            mView.sendFialRequest(captcha.getState().getMsg());
                        break;
                }
            }

            @Override
            public void onFinish() {
                mView.hideLoading();
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("resetPwd", "onError: -----------"+e.getMessage());
                mView.sendFialRequest(e.getMessage());
            }
        });
    }
}
