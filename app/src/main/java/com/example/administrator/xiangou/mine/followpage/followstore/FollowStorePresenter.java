package com.example.administrator.xiangou.mine.followpage.followstore;

import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class FollowStorePresenter extends BasePresenterImpl<FollowStoreContract.View> implements FollowStoreContract.Presenter{

    @Override
    public void getCollectStoresListApi(int user_id, int page_no) {
        addSubscription(mApiService.getCollectStoresListApi(user_id, page_no),
                new BaseSubscriber<FollowStoreBean>(mView.getContext()) {
                    @Override
                    public void onNext(FollowStoreBean followStoreBean) {
                        Log.e("fstorep", "onNext: " + followStoreBean.getState().toString());
                        switch ( followStoreBean.getState().getCode() ){
                            case 101:
                                mView.sendFialRequest(followStoreBean.getState().getMsg());
                                break;
                            case 200:
                                mView.getStoresListSuccess(followStoreBean.getData());
                                break;
                            case 1:
                                mView.sendFialRequest("用户尚未登录！");

                        }
                    }

                    @Override
                    public void onFinish() {}

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("fstorep", "onError: " + e.toString());
                        mView.sendFialRequest(e.getMessage());
                    }
                });
    }
}
