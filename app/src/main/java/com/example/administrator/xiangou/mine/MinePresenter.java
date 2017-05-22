package com.example.administrator.xiangou.mine;


import android.util.Log;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ContextUtils;

import static com.example.administrator.xiangou.tool.BaseActivity.bSharedPreferences;
import static com.example.administrator.xiangou.tool.BaseActivity.bUser;


public class MinePresenter extends BasePresenterImpl<MineContract.View> implements MineContract.Presenter{@Override
public void IDlogin(String userName, String password) {
    Log.e("IDlogin", "enter：IDlogin"+ ContextUtils.MD5(password));

    addSubscription(
            mApiService.loginID(userName, ContextUtils.MD5(password)),

            new BaseSubscriber<LoginBean>(mView.getContext()) {
                @Override
                public void onNext(LoginBean loginBean) {
                    switch (loginBean.getState().getCode()){
                        case 200:
                            if (loginBean.getData()!=null){
                                mView.ReLoginidSuccess(loginBean.getData());
                                bSharedPreferences.putString("user_info",loginBean.getData().toString());
                                Log.e("User", "LoginidSuccess: "+ bUser.toString());
                                mView.ReLoginidSuccess(loginBean.getData());
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
                    Log.e("IDlogin", "onFinish：IDlogin");
                    mView.hideLoading();
                }
                @Override
                public void onError(ExceptionHandle.ResponeThrowable e) {
                    Log.e("IDlogin", e.code+"onError：" + e.getMessage());
                    if (e.code ==1000)
                        mView.sendFialRequest("账号或密码错误");
                }
            }
    );
}
}
