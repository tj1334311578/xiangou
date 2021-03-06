package com.example.administrator.xiangou.login.idlogin;

import android.util.Log;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ContextUtils;

public class IDLoginPresenter extends BasePresenterImpl<IDLoginContract.View> implements IDLoginContract.Presenter{

    @Override
    public void IDlogin(String userName, String password) {
        mView.showLoading();//加载等待
//        Log.e("IDlogin", "enter：IDlogin"+ContextUtils.MD5(password));
        addSubscription(
                mApiService.loginID(userName, ContextUtils.MD5(password)),

                new BaseSubscriber<LoginBean>(mView.getContext()) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        Log.e("IDloginAt", "onNext: " +loginBean.getData().toString()+"\n"+loginBean.getState().toString());
                        switch (loginBean.getState().getCode()){
                            case 200:
                                if (loginBean.getData()!=null){
                                    //更新用户信息
                                    setbUserBySP(loginBean.getData().toString());
                                    getSP().upDateUserInfo(loginBean.getData().toString());
                                    mView.LoginidSuccess(loginBean.getData());
                                }
                                break;
                            case 100:
                            default:
                                mView.sendFialRequest(loginBean.getState().getMsg());
                                break;
                        }
                    }
                    @Override
                    public void onFinish() {
//                        Log.e("IDlogin", "onFinish：IDlogin");
                        mView.hideLoading();
                    }
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("IDloginAt", e.code+"onError：" + e.getMessage());
                        if (e.code ==1000)
                        mView.sendFialRequest("账号或密码错误");
                    }
                }
        );
    }

    @Override
    public void saveInfo(String TelKey, String Tel, String PwdKey, String Pwd) {
        getSP().putString(TelKey,Tel);
        getSP().putString(PwdKey,Pwd);
    }
}