package com.example.administrator.xiangou.goods_sort.storehome.storehome;


import android.util.Log;
import android.widget.Toast;

import com.example.administrator.xiangou.goods_sort.storehome.HomePageBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class StoreHomePresenter extends BasePresenterImpl<StoreHomeContract.View> implements StoreHomeContract.Presenter{
    @Override
    public void dealStoreHomeCall(int did, int user_id) {

        Toast.makeText(mView.getContext(), "dealStoreHomeCall", Toast.LENGTH_SHORT).show();
        mView.showLoading();
        addSubscription(mApiService.callHomePagerData(did, user_id),
                new BaseSubscriber<HomePageBean>(mView.getContext()) {
                    @Override
                    public void onNext(HomePageBean dataBean) {
                        if (dataBean.getState().getCode()==200){
                            Log.e("storedata", "onNext: " + dataBean.getData().toString());
                            mView.sendDataBeanToView(dataBean);
                        }
                    }

                    @Override
                    public void onFinish() {
                            mView.hideLoading();
                        Toast.makeText(mView.getContext(), "onFinish", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                            mView.sendFialRequest(e.getMessage());
                    }
                }
        );
    }
}
