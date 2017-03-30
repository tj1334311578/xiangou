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
        Log.e("IDlogin", "enter：IDlogin");

        addSubscription(
                mApiService.loginID(userName, ContextUtils.MD5(password)),

                new BaseSubscriber<LoginBean>(mView.getContext()) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        Log.e("IDlogin", "onNext：IDlogin" + loginBean.getData() );
                    }
                    @Override
                    public void onFinish() {
                        Log.e("IDlogin", "onFinish：IDlogin");
                        mView.showLoading();
                    }
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("IDlogin", "onError：IDlogin" + e.message);
                    }

                }
        );
    }
}
/**new BaseSubscriber<LoginBean>(mView.getContext()) {
@Override
public void onNext(LoginBean loginBean) {
Log.e("IDlogin", "onNext：IDlogin" );
switch (loginBean.getState().getCode()){
case 200:
Log.e("loginBean.getData()", "onNext: "+loginBean.getData() );
User.setUser( loginBean.getData() );
case 100:
default:
mView.sendFialRequest(loginBean.getState().getMsg());
}
}

@Override
public void onFinish() {
Log.e("IDlogin", "onFinish：IDlogin" );
mView.hideLoading();
mView.LoginidSuccess();
}

@Override
public void onError(ExceptionHandle.ResponeThrowable e) {
Log.e("loginBean.getData()", "onError: ");
mView.sendFialRequest(e.message);
}

}
 */