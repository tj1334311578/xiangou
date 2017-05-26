package com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsdetailscomment;

import android.content.Context;
import android.util.Log;

import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.CommentBean;
import com.example.administrator.xiangou.goodsdetails.simplegoodsdetails.goodsbean.commenttempBean;
import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.nearby.apimodel.CommentDataBean;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class GoodsDetailsCommentPresenter extends BasePresenterImpl<GoodsDetailsCommentContract.View> implements GoodsDetailsCommentContract.Presenter{

    @Override
    public void dealCallComment(int goods_id, int page_no, String condition) {
        addSubscription(mApiService.callComments(goods_id,page_no,condition),
                new BaseSubscriber<commenttempBean>(mView.getContext()) {
                    @Override
                    public void onNext(commenttempBean commentBean) {
                        Log.e("onNext", "onNext: "+commentBean.toString() +"\nCode:"+commentBean.getState().getCode());
                        if (commentBean.getState().getCode()==200){
                            mView.sendDataBeanToView(commentBean);
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
