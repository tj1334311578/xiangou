package com.example.administrator.xiangou.login.registerverify;


import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.main.User;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class RegisterVerifyPresenter extends BasePresenterImpl<RegisterVerifyContract.View> implements RegisterVerifyContract.Presenter{
    /**
     * 这里做数据操作，并通过对应的接口方法loginSuccess、loginFailed传入处理后的数据或信息
     * 用法：先在UI 通过LoginPresenter对象调用下面的方法传值(即填入方法的参数)，在通过实现了的对应的接口方法等去做相应的UI操作
     */
    @Override
    public void captcha(String tel) {
        addSubscription(mApiService.getCapture(tel), new BaseSubscriber<Captcha>(mView.getContext()) {
            @Override
            public void onNext(Captcha captcha) {
                Log.e("captcha", "onNext: "+captcha.getState().getCode()+"    "+captcha.getState().getMsg() );
                switch (captcha.getState().getCode()){
                    case 100:
                        Log.e("captcha", "onNext: 失败" );
                        mView.sendFialRequest(captcha.getState().getMsg());//todo UI toast 失败
                        break;
                    case 200:
                        Log.e("captcha", "onNext: " );
                        break;
                }
            }

            @Override
            public void onFinish() {Log.e("captcha", "onFinish: ");}

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("captcha", "onError: "+e.message+" /\n"+e.getMessage());
                mView.sendFialRequest(e.getMessage());
            }
        });
    }

    @Override
    public void registerv(final String tel, String code) {
        mView.showLoading();
        addSubscription( mApiService.goRegister(tel, code), new BaseSubscriber<Captcha>(mView.getContext()) {

            @Override
            public void onNext(Captcha captcha) {
                Log.e("IDlogin", "onNext：IDlogin" + captcha);
                switch (captcha.getState().getCode()){
                    case 100:
                        mView.sendFialRequest(captcha.getState().getMsg());// TODO: 2017/3/22 ui toast 验证失败，再重新输入
                        break;
                    case 200:
                        User.getUser().setMobile(tel);
                        mView.verifySuccess();// TODO: 2017/3/22 进入下一页->输入密码
                        break;
                }
            }

            @Override
            public void onFinish() {
                mView.hideLoading();
                Log.e("IDlogin", "onFinish：IDlogin");
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("IDlogin", "onError：IDlogin");
                Log.e("this ExceptionHandle", "onError: "+e.message +"/\n"+e.getMessage());
                mView.sendFialRequest(e.message);
            }
        });
    }
}
