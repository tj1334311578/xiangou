package com.example.administrator.xiangou.nearby.nearbygoods;


import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.nearby.apimodel.NearbyGoodsDataBean;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class NearbyGoodsPresenter extends BasePresenterImpl<NearbyGoodsContract.View> implements NearbyGoodsContract.Presenter{

    @Override
    public void dealNearbyGoodsCall(String mapX, String mapY, int distance) {
//        mView.showLoading();
        addSubscription(mApiService.callNearbyGoods(mapX, mapY, distance),
                new BaseSubscriber<NearbyGoodsDataBean>(mView.getContext()) {
            @Override
            public void onNext(NearbyGoodsDataBean nearbyGoodsDataBean) {
                if (nearbyGoodsDataBean.getState().getCode()==200){
//                    Log.e("nearbygoods", "onNext: " + nearbyGoodsDataBean.toString());
                    mView.sendDataBeanToView(nearbyGoodsDataBean.getData());
                }
            }

            @Override
            public void onFinish() {
//                mView.hideLoading();
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                mView.sendFialRequest(e.getMessage());
            }
        });
    }

}
