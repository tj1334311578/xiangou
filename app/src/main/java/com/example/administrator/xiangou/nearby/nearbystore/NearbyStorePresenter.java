package com.example.administrator.xiangou.nearby.nearbystore;

import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.nearby.apimodel.NearbyStoreApiDataBean;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class NearbyStorePresenter extends BasePresenterImpl<NearbyStoreContract.View> implements NearbyStoreContract.Presenter{

    @Override
    public void dealNearbyStoreCall(String mapX, String mapY, int distance, int page_no) {
        mView.showLoading();
        addSubscription(mApiService.callNearbyStore(mapX, mapY, distance, page_no),
                new BaseSubscriber<NearbyStoreApiDataBean>(mView.getContext()) {
                    @Override
                    public void onNext(NearbyStoreApiDataBean nearbyStoreApiDataBean) {
                        if (nearbyStoreApiDataBean.getState().getCode()==200) {
                            Log.e("storedata", "onNext: " + nearbyStoreApiDataBean.getData().toString());
                            mView.sendStoreDataToView(nearbyStoreApiDataBean.getData());
                        }
                    }

                    @Override
                    public void onFinish() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("nearbystore", "onError: " +e.getMessage());
                        mView.sendFialRequest(e.getMessage());
                    }

                });
    }
}
