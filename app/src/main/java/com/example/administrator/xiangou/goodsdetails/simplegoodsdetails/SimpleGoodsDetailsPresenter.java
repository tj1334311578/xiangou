package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails;

import android.util.Log;

import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.SimpleGoodsDetialBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;


/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class SimpleGoodsDetailsPresenter extends BasePresenterImpl<SimpleGoodsDetailsContract.View> implements SimpleGoodsDetailsContract.Presenter{
    @Override
    public void dealSimpleDetailsCall(int id,int user_id,String map_x,String map_y,int type) {
        //用于请求的接收类服务器数据不全无法获取code：1000未知错误
        addSubscription(mApiService.callSimpleGoodsDetails(126,type),
                new BaseSubscriber<SimpleGoodsDetialBean>(mView.getContext()) {
            @Override
            public void onNext(SimpleGoodsDetialBean tempBean) {
                Log.e("onNext", "onNext: "+tempBean.toString() +"\nCode:"+tempBean.getState().getCode());
                if (tempBean.getState().getCode()==200){
                    mView.sendDataBeanToView(tempBean);
                }
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                Log.e("onError", "onError: "+e.toString() );

            }
        });
    }
}
