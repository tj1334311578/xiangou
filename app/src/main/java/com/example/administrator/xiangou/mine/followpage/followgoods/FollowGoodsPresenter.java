package com.example.administrator.xiangou.mine.followpage.followgoods;

import android.util.Log;

import com.example.administrator.xiangou.mvp.BasePresenterImpl;
import com.example.administrator.xiangou.net.BaseSubscriber;
import com.example.administrator.xiangou.net.ExceptionHandle;

public class FollowGoodsPresenter extends BasePresenterImpl<FollowGoodsContract.View> implements FollowGoodsContract.Presenter{

    @Override
    public void getCollectGoodsList(int user_id, int page_no, String goods_name) {
        addSubscription(mApiService.getCollectGoodsListApi(user_id, page_no, goods_name)
                , new BaseSubscriber<FollowGoodsBean>(mView.getContext()) {
                    @Override
                    public void onNext(FollowGoodsBean followGoodsBean) {
                        switch (followGoodsBean.getState().getCode()){
                            case 101:
                                mView.sendFialRequest(followGoodsBean.getState().getMsg());
                                break;
                            case 200:
                                mView.getGoodsListSuccess(followGoodsBean);
                                break;
                            case 1:
                                mView.sendFialRequest("用户尚未登录！");
                                break;
                        }
                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        Log.e("gstorep", "onError: " + e.toString());
                        mView.sendFialRequest(e.getMessage());
                    }
                });
    }
}
