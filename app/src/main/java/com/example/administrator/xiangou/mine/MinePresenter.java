package com.example.administrator.xiangou.mine;


import android.util.Log;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ContextUtils;

import java.io.IOException;

import okhttp3.ResponseBody;


public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter{

    @Override
    public void IDlogin(String userName, String password) {
        Log.e("minep", "enter：IDlogin "+userName+" p="+password+" --pwd: "+ ContextUtils.MD5(password));
        if (userName!=null&&password!=null) {
            addSubscription( mApiService.loginID(userName, ContextUtils.MD5(password)),
                    new BaseSubscriber<LoginBean>(mView.getContext()) {
                        @Override
                        public void onNext(LoginBean loginBean) {
                            switch (loginBean.getState().getCode()) {
                                case 200:
                                    if (loginBean.getData() != null) {
                                        if (!getSP().getString("user_info", null)
                                                .equals(loginBean.getData().toString())) {
                                            setbUserBySP(loginBean.getData().toString());
//                                            upDateUserInfo(loginBean.getData().toString());
//                                            Log.e("minep", "LoginidSuccess: buser" + bUser.toString());
                                            mView.ReLoginidSuccess(loginBean.getData());
                                        }
                                    }
                                    break;
//                                case 100:
//                                default:
//                                    mView.sendFialRequest(loginBean.getState().getMsg());
//                                    break;
                            }
                        }

                        @Override
                        public void onFinish() {
//                            mView.hideLoading();
                        }

                        @Override
                        public void onError(ExceptionHandle.ResponeThrowable e) {
                            Log.e("minep", e.code + " onError：" + e.getMessage());
                            if (e.code == 1000)
                                mView.sendFialRequest("账号或密码错误");
                        }
                    }
            );
        }
    }

    @Override
    public void ceshi(String channel, String order_sn, int amount) {
        addSubscription(mApiService.callceshiApi(channel,order_sn,amount),
                new BaseSubscriber<ResponseBody>(mView.getContext()) {

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.e("minep", "onNext: "+responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }
                });
    }
}
