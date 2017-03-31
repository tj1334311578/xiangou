package com.example.administrator.xiangou.login.dynamiclogin;

import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class DynamicLoginPresenter extends BasePresenterImpl<DynamicLoginContract.View> implements DynamicLoginContract.Presenter{

    /**
     * 验证码请求
     * @param tel
     */
    @Override
    public void sendCaptcha(String tel, String method) {
        addSubscription(mApiService.sendCapture(tel,method), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                Log.e("sendCaptcha", "onNext: "+captcha.getState().getCode()+"    "+captcha.getState().getMsg() );
                switch (captcha.getState().getCode()){
                    case 100:
                        Log.e("sendCaptcha", "onNext: 失败" );
                        mView.sendFialRequest(captcha.getState().getMsg());//todo UI toast 失败
                        break;
                    case 200:
                        Log.e("sendCaptcha", "onNext: 成功" );
                        break;
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("sendCaptcha", "onError: "+e.message+" /\n"+e.getMessage());
                mView.sendFialRequest(e.getMessage());
            }
        });
    }

    /**
     * 登录请求
     * @param tel
     * @param code
     */
    @Override
    public void loginV(String tel, String code) {
        mView.showLoading();//加载等待

        addSubscription(mApiService.loginV(tel, code), new BaseSubscriber<LoginBean>(mView.getContext()) {
            @Override
            public void onNext(LoginBean loginBean) {
                switch (loginBean.getState().getCode()){
                    case 200:
                        if (loginBean.getData()!=null){
                            User.setUser( loginBean.getData() );
                            Log.e("User", "LoginVSuccess: "+ User.getUser().toString());
                            mView.LoginvSuccess();
                        }
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
                mView.sendFialRequest(e.getMessage());
            }

        });
    }
}
