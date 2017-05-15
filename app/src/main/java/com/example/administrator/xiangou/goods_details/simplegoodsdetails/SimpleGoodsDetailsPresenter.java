package com.example.administrator.xiangou.goods_details.simplegoodsdetails;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

import rx.Subscriber;
import rx.Subscription;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SimpleGoodsDetailsPresenter extends BasePresenterImpl<SimpleGoodsDetailsContract.View> implements SimpleGoodsDetailsContract.Presenter{

    @Override
    public void dealSimpleDetailsCall(int id) {
        Log.e("ddddd", "sendDataBeanToView: "+id );
        addSubscription(mApiService.callSimpleGoodsDetails(id,1),
                new BaseSubscriber<SimpleGoodsDetialBean>(mView.getContext()) {
                    @Override
                    public void onNext(SimpleGoodsDetialBean simpleGoodsDetialBean) {
                        if (simpleGoodsDetialBean.getState().getCode()==200) {
                            Log.e("ddddd", "onNext: " + simpleGoodsDetialBean.toString());
                            mView.sendDataBeanToView(simpleGoodsDetialBean);
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        mView.sendFialRequest(e.getMessage());
                    }
                });
    }
}
