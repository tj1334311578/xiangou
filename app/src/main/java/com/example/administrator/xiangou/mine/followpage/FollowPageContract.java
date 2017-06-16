package com.example.administrator.xiangou.mine.followpage;

import com.example.administrator.xiangou.mvp.BasePresenter;
import com.example.administrator.xiangou.mvp.BaseView;

public class FollowPageContract {
    interface View extends BaseView {
        void cancelGoodsSuccess(String msg);
        void cancelStoresSuccess(String msg);
    }

    interface  Presenter extends BasePresenter<View> {
        void cancelCollectGoodsApi(int user_id, String goods_id);
        void cancelCollectStoresApi(int user_id, String store_id);
    }
}
