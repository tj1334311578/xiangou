package com.example.administrator.xiangou.home;


import android.util.Log;

import com.example.administrator.xiangou.home.model.HomeDataBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter{

    @Override
    public void getHomeData(String map_x, String map_y, int cityid) {
        addSubscription(mApiService.getHomePageData(map_x, map_y, cityid), new BaseSubscriber<HomeDataBean>(mView.getContext()) {
            @Override
            public void onNext(HomeDataBean homeDataBean) {
                Log.e("homeDataBean", "onNext: " +homeDataBean.toString() );
                if (homeDataBean.getState().getCode()==200){
                    mView.getHomeDataSuccess(homeDataBean.getData());
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("homeerror", "onError: " +e.getMessage());
                mView.sendFialRequest(e.getMessage());
            }
        });
    }
}
