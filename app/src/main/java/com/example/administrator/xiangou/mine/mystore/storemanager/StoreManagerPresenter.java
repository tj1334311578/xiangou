package com.example.administrator.xiangou.mine.mystore.storemanager;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreManagerPresenter extends BasePresenterImpl<StoreManagerContract.View> implements StoreManagerContract.Presenter{

    @Override
    public void callStoreInfo(int store_id) {
        Log.e("fjeif", "进入callStoreInfo: " );
        addSubscription(mApiService.callStoreInfo(store_id),
                new BaseSubscriber<StoreManagerInfoBean>(mView.getContext()) {
                    @Override
                    public void onNext(StoreManagerInfoBean storeManagerInfoBean) {
                        if (storeManagerInfoBean.getState().getCode()==200){
                            mView.infoDataToView(storeManagerInfoBean);
                            Log.e("next", "onNext: "+storeManagerInfoBean.toString() );
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("error", "onError: "+e.toString() );
                    }
                });
    }

    @Override
    public void callEditStoreInfo(int did, String map_x, String map_y, String address, int province_id, int city_id, int district_id, String synopsis, String tel, MultipartBody.Part logo) {
        Log.e("callEditStoreInfo", "进入callEditStoreInfo: " );
            addSubscription(mApiService.callEditStoreInfo(did,map_x,map_y,address,province_id,city_id,district_id,synopsis,tel,logo),
                    new BaseSubscriber<ResponseBody>(mView.getContext()) {
                        @Override
                        public void onNext(ResponseBody responseBody) {
                                if (responseBody!=null){
                                    try {
                                        Log.e("xiu", "onNext: "+responseBody.string());
                                        mView.modifySuccessd();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onError(ExceptionHandle.ResponeThrowable e) {
                            Log.e("error", "onError: "+e.toString() );
                        }
                    });
    }
}
