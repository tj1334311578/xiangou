package com.example.administrator.xiangou.mine.followpage;

import android.util.Log;

import com.example.administrator.xiangou.login.Captcha;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class FollowPagePresenter extends BasePresenterImpl<FollowPageContract.View> implements FollowPageContract.Presenter{

    @Override
    public void cancelCollectGoodsApi(int user_id, String goods_id) {
        mView.showLoading();
        addSubscription(mApiService.cancelCollectGoodsApi(user_id, goods_id),
                new BaseSubscriber<Captcha>(mView.getContext()) {
                    @Override
                    public void onNext(Captcha captcha) {
                        switch (captcha.getState().getCode()){
                            case 200:
                                Log.e("FollowPagePresenter", " goods onNext: " + captcha.toString());
                                mView.cancelGoodsSuccess(captcha.getState().getMsg());
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
                        Log.e("FollowPagePresenter", "goods onError: " + e.toString());
                        mView.sendFialRequest(e.getMessage());
                    }
                });
    }

    @Override
    public void cancelCollectStoresApi(int user_id, String store_id) {
        mView.showLoading();
        addSubscription(mApiService.cancelCollectStoresApi(user_id, store_id),
                new BaseSubscriber<Captcha>(mView.getContext()) {
                    @Override
                    public void onNext(Captcha captcha) {
                        Log.e("FollowPagePresenter", " store onNext: " + captcha.toString());
                        switch (captcha.getState().getCode()){
                            case 200:
                                mView.cancelStoresSuccess(captcha.getState().getMsg());
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
                        Log.e("FollowPagePresenter", "store onError: " + e.toString());
                        mView.sendFialRequest(e.getMessage());
                    }
                });
    }
}
