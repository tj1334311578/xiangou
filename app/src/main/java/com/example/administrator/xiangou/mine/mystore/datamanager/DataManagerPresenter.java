package com.example.administrator.xiangou.mine.mystore.datamanager;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DataManagerPresenter extends BasePresenterImpl<DataManagerContract.View> implements DataManagerContract.Presenter{

    @Override
    public void requestDataInfo(int did, String Time) {
        addSubscription(mApiService.callTotalDataApi(did,Time),
                new BaseSubscriber<TotalDataBean>(mView.getContext()) {
                    @Override
                    public void onNext(TotalDataBean totalDataBean) {
                        Log.e("total", "onNext: "+totalDataBean.toString() );
                        if (totalDataBean.getState().getCode()==200){
                            mView.dataToView(totalDataBean);
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("onError", "onError: "+e.toString());
                    }
                });
    }
}
