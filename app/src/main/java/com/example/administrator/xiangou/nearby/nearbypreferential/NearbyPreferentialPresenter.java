package com.example.administrator.xiangou.nearby.nearbypreferential;

import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.nearby.apimodel.NearbyBenifitDataBean;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class NearbyPreferentialPresenter extends BasePresenterImpl<NearbyPreferentialContract.View> implements NearbyPreferentialContract.Presenter{

    @Override
    public void dealNearbyPreferentialCall(String mapX, String mapY) {
        Log.e("dealnearbystore", "dealNearbystoreCall: ");
        addSubscription(mApiService.callNearbyBenifit(mapX, mapY),
                new BaseSubscriber<NearbyBenifitDataBean>(mView.getContext()) {
                    @Override
                    public void onNext(NearbyBenifitDataBean nearbyBenifitDataBean) {
                        if (nearbyBenifitDataBean.getState().getCode()==200){
                            Log.e("NearbyPreferential", "onNext: " + nearbyBenifitDataBean.getData().toString());
                            mView.sendPreferentialDataToView(nearbyBenifitDataBean.getData());
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("错误", "onError: " + e.getMessage());
                    }
                });
    }
}
