package com.example.administrator.xiangou.login.find_resetpwd;

import com.example.administrator.xiangou.login.LoginBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;
import com.example.administrator.xiangou.tool.ContextUtils;

import static com.example.administrator.xiangou.tool.ContextUtils.gUser;

public class ResetpwdPresenter extends BasePresenterImpl<ResetpwdContract.View> implements ResetpwdContract.Presenter{

    @Override
    public void resetPwd(final String tel, final String password, String code) {
        mView.showLoading();
        addSubscription(mApiService.resetPwd(tel, ContextUtils.MD5(password), code),
                new BaseSubscriber<LoginBean>(mView.getContext()) {
            @Override
            public void onNext(LoginBean loginBean) {
                switch (loginBean.getState().getCode()){
                    case 200:
                        gUser.setUser(loginBean.getData());
                        mView.resetPwdSuccess();
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
